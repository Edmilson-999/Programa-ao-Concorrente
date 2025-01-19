import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;

public class Produtor implements Runnable {
    private final String ficheiro;
    private final Queue<String> buffer;
    private final int bufferSize;

    public Produtor(String ficheiro, Queue<String> buffer, int bufferSize) {
        this.ficheiro = ficheiro;
        this.buffer = buffer;
        this.bufferSize = bufferSize;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                synchronized (buffer) {
                    while (buffer.size() >= bufferSize) {
                        buffer.wait();
                    }

                    buffer.add(ficheiro + ";" + linha);
                    buffer.notifyAll();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao processar o ficheiro: " + ficheiro + " - " + e.getMessage());
        }
    }
}
