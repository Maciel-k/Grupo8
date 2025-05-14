package model.sistema;



import org.example.model.Barraca;
import org.example.model.Produto;
import org.example.model.exceptions.AssociacaoInvalidaException;
import org.example.model.exceptions.StockInsuficienteException;
import org.example.model.pessoa.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe principal que gerencia todo o sistema.
 */
public class GerenciadorSistema {
    private List<Pessoa> pessoas;
    private List<Barraca> barracas;
    private List<model.Venda> vendas;
    private Pessoa usuarioLogado;
    private List<model.Venda> vendas;
    public GerenciadorSistema() {
        this.pessoas = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.vendas = new ArrayList<>();
        inicializarDadosTeste();
    }

    private void inicializarDadosTeste() {
        // Adicionar dados iniciais para teste
        Administrador admin = new Administrador("Admin", 1, "LES", "admin123", "FAP");
        pessoas.add(admin);

        VoluntarioVendas volVendas1 = new VoluntarioVendas("João Vendas", 1001, "LES", "joao123", "FEUP");
        VoluntarioVendas volVendas2 = new VoluntarioVendas("Maria Vendas", 1002, "LEI", "maria123", "FEUP");
        VoluntarioStock volStock1 = new VoluntarioStock("Carlos Stock", 1003, "LES", "carlos123", "FEUP");

        pessoas.add(volVendas1);
        pessoas.add(volVendas2);
        pessoas.add(volStock1);

        Barraca barraca1 = new Barraca("Barraca FEUP", "FEUP");
        barraca1.adicionarProduto(new Produto("Cerveja", 2.5, 100));
        barraca1.adicionarProduto(new Produto("Hambúrguer", 5.0, 50));

        try {
            barraca1.adicionarVoluntario(volVendas1);
            barraca1.adicionarVoluntario(volVendas2);
            barraca1.adicionarVoluntario(volStock1);
        } catch (AssociacaoInvalidaException e) {
            System.out.println("Erro ao associar voluntários: " + e.getMessage());
        }

        barracas.add(barraca1);
    }

    // Métodos de autenticação
    public boolean autenticar(int numeroIdentificacao, String password) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNumeroIdentificacao() == numeroIdentificacao &&
                    pessoa.getPassword().equals(password)) {
                usuarioLogado = pessoa;
                return true;
            }
        }
        return false;
    }

    public Pessoa getUsuarioLogado() {
        return usuarioLogado;
    }

    // Métodos para adicionar entidades
    public void adicionarBarraca(Barraca barraca) {
        barracas.add(barraca);
    }

    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    // Métodos para listagens
    public List<Voluntario> listarVoluntariosPorNumero() {
        List<Voluntario> voluntarios = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Voluntario) {
                voluntarios.add((Voluntario) pessoa);
            }
        }

        Collections.sort(voluntarios, Comparator.comparingInt(Pessoa::getNumeroIdentificacao));
        return voluntarios;
    }
    public void listarProdutos() {
        System.out.println("\n=== LISTA DE TODOS OS PRODUTOS ===");
        for (Barraca barraca : barracas) {
            System.out.println("\nBarraca: " + barraca.getNome());
            for (Produto produto : barraca.getProdutos()) {
                System.out.printf("• %s (Preço: %.2f€ | Stock: %d)\n",
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getStock());
            }
        }
    }
    public List<Barraca> listarBarracasPorClassificacao() {
        List<Barraca> copiaBarracas = new ArrayList<>(barracas);
        copiaBarracas.sort((b1, b2) -> b2.getClassificacao().compareTo(b1.getClassificacao()));
        return copiaBarracas;
    }

    // Métodos para operações de venda
    public void realizarVenda(Barraca barraca, Produto produto, int quantidade, VoluntarioVendas voluntario)
            throws StockInsuficienteException {
        produto.reduzirStock(quantidade);
        model.Venda venda = new model.Venda(produto, quantidade, voluntario);
        vendas.add(venda);
        barraca.adicionarVenda(venda.getValorTotal());
        voluntario.adicionarVenda(venda.getValorTotal());
        barraca.setStockFinalDiario(produto.getStock());
    }

    // Métodos para serialização
    public void salvarDados(String arquivo) throws IOException {
        model.sistema.Serializador.salvar(this, arquivo);
    }

    public static GerenciadorSistema carregarDados(String arquivo) throws IOException, ClassNotFoundException {
        return (GerenciadorSistema) model.sistema.Serializador.carregar(arquivo);
    }
    public void realizarVenda(String nomeBarraca, String nomeProduto, int quantidade, VoluntarioVendas voluntario)
            throws StockInsuficienteException {

        Barraca barraca = barracas.stream()
                .filter(b -> b.getNome().equalsIgnoreCase(nomeBarraca))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Barraca não encontrada"));

        Produto produto = barraca.getProdutos().stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomeProduto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.reduzirStock(quantidade);
        double valorVenda = produto.getPreco() * quantidade;

        // Atualiza estatísticas
        Barraca.registrarVenda(valorVenda);
        barraca.registrarVenda(valorVenda);

        // Registra a venda
        model.Venda venda = new model.Venda(produto, quantidade, voluntario);
        vendas.add(venda);

        System.out.printf("Venda registrada: %d x %s = %.2f€\n",
                quantidade, produto.getNome(), valorVenda);
    }
    public Barraca[] getBarracas() {
        return null;
    }
}

