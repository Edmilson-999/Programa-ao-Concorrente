public class Computador  implements Runnable{
    private final int id;

    public Computador(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Computador " + id + " está operando...");
            try {
                Thread.sleep(2000);
                System.out.println("Computador " + id + " aguardando liberação para o próximo ciclo.");
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                System.out.println("Computador " + id + " foi interrompido.");
                return;
            }
        }
        System.out.println("Computador " + id + " foi encerrado.");
    }
}
