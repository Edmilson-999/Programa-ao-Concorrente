import java.util.ArrayList;
import java.util.List;

public class GestorCentroEstagiario {
    private final List<Thread> threads;

    public GestorCentroEstagiario(int numComputadores) {
        threads = new ArrayList<>();
        for (int i = 0; i < numComputadores; i++) {
            Computador computador = new Computador(i);
            Thread thread = new Thread(computador);
            threads.add(thread);
        }
    }

    public void iniciarOperacoes() {
        threads.forEach(Thread::start);
    }

    public void encerrarOperacoes() {
        threads.forEach(Thread::interrupt);
        System.out.println("Encerrando a fábrica...");
    }

    public void liberarComputadores() {
        synchronized (this) {
            threads.forEach(t -> {
                synchronized (t) {
                    t.notify();
                }
            });
        }
        System.out.println("Liberando computadores para o próximo ciclo...");
    }
}
