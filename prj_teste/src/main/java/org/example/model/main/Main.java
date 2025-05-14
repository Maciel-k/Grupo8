package org.example.model.main;


import model.sistema.GerenciadorSistema;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.example.model.Barraca;
import org.example.model.pessoa.Voluntario;

public class Main {
    private static GerenciadorSistema sistema = new GerenciadorSistema();
    private static Scanner scanner = new Scanner(System.in);
    private static void menuRelatorios() {
        sistema.gerarRelatorioClassificacao();
    }


    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestão de Barracas da Queima das Fitas ===");



        // Autenticação
        boolean autenticado = false;
        while (!autenticado) {
            System.out.print("Número de identificação: ");
            int numero = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            System.out.print("Password: ");
            String password = scanner.nextLine();

            autenticado = sistema.autenticar(numero, password);
            if (!autenticado) {
                System.out.println("Autenticação falhou. Tente novamente.");
            }
        }

        // Menu principal
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Visualizar dados");
            System.out.println("2. Adicionar dados");
            System.out.println("3. Realizar venda");
            System.out.println("4. Listagens ordenadas");
            System.out.println("5. Relatórios");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        visualizarDados();
                        break;
                    case 2:
                        adicionarDados();
                        break;
                    case 3:
                        realizarVenda();
                        break;
                    case 4:
                        exibirListagensOrdenadas();
                        break;

                    case 5: menuRelatorios(); break;
                    case 6:
                        sair = true;
                        System.out.println("A sair do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine(); // Limpar buffer
            }
        }
    }

    private static void visualizarDados() {
        System.out.println("\n=== VISUALIZAR DADOS ===");
        System.out.println("1. Barracas");
        System.out.println("2. Voluntários");
        System.out.println("3. Produtos");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                listarBarracas();
                break;
            case 2:
                listarVoluntariosPorNumero();
                break;
            case 3:
                sistema.listarProdutos();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarBarracas() {
        System.out.println("\n=== BARRACAS ===");
        for (Barraca barraca : sistema.getBarracas()) {
            System.out.println("Nome: " + barraca.getNome());
            System.out.println("Instituição: " + barraca.getInstituicao());
            System.out.println("Classificação: " + barraca.getClassificacao());
            System.out.println("Vendas totais: " + barraca.getVendasTotais() + "€");
            System.out.println("Stock final diário: " + barraca.getStockFinalDiario());
            System.out.println("---");
        }
    }

    private static void adicionarDados() {
        // Implementação similar para adicionar diferentes tipos de dados
    }

    private static void realizarVenda() {
        // Implementação para processar uma venda
    }

    private static void exibirListagensOrdenadas() {
        System.out.println("\n=== LISTAGENS ORDENADAS ===");
        System.out.println("1. Voluntários por número");
        System.out.println("2. Barracas por classificação");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                listarVoluntariosPorNumero();
                break;
            case 2:
                sistema.listarBarracasPorClassificacao();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarVoluntariosPorNumero() {
        System.out.println("\n=== VOLUNTÁRIOS ORDENADOS POR NÚMERO ===");
        for (Voluntario voluntario : sistema.listarVoluntariosPorNumero()) {
            System.out.println("Número: " + voluntario.getNumeroIdentificacao());
            System.out.println("Nome: " + voluntario.getNome());
            System.out.println("Tipo: " + voluntario.getTipo());
            System.out.println("---");
        }
    }
}