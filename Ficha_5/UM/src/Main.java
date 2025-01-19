import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final Queue<String> buffer = new LinkedList<>();
    private static final int BUFFER_SIZE = 100;
    private static volatile boolean nomeEncontrado = false;
    private static final String nomeProcurado = "Guilherme Antunes";

    public static void main(String[] args) {
        String[] ficheiros = {
                "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha1.txt", "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha2.txt",
                "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha3.txt", "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha4.txt",
                "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha5.txt", "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha6.txt",
                "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha7.txt", "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha8.txt",
                "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha9.txt", "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_5/ficha10.txt"
        };

        Thread[] produtores = new Thread[ficheiros.length];
        for (int i = 0; i < ficheiros.length; i++) {
            String ficheiro = ficheiros[i];
            produtores[i] = new Thread(new Produtor(ficheiro, buffer, BUFFER_SIZE));
            produtores[i].start();
        }

        Thread consumidor = new Thread(new Consumidor(buffer, nomeProcurado));
        consumidor.start();

        try {
            for (Thread produtor : produtores) {
                produtor.join();
            }
            synchronized (buffer) {
                buffer.add(null);
                buffer.notifyAll();
            }
            consumidor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (!nomeEncontrado) {
            System.out.println("Nome não encontrado em nenhum dos ficheiros.");
        }
    }

    public static void marcarNomeEncontrado() {
        nomeEncontrado = true;
    }
}
