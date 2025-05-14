package model.sistema;

import java.io.*;

/**
 * Classe responsável pela serialização e desserialização de objetos.
 */
public class Serializador {
    public static void salvar(Object objeto, String arquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(objeto);
        }
    }

    public static Object carregar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return in.readObject();
        }
    }
}