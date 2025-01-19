import java.util.Queue;

public class Consumidor implements Runnable {
    private final Queue<String> buffer;
    private final String nomeProcurado;

    public Consumidor(Queue<String> buffer, String nomeProcurado) {
        this.buffer = buffer;
        this.nomeProcurado = nomeProcurado;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String linha;
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        buffer.wait();
                    }

                    linha = buffer.poll();
                    buffer.notifyAll();
                }

                if (linha == null) break; // Sinal de término

                if (processarLinha(linha)) {
                    Main.marcarNomeEncontrado();
                    System.out.println("Nome encontrado no ficheiro: " + linha);
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Consumidor interrompido.");
        }
    }

    private boolean processarLinha(String linha) {
        return linha.contains(nomeProcurado);
    }
}
