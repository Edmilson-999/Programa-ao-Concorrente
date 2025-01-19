public class Main {
    public static void main(String[] args) {
        final int CAPACIDADE_FILA = 6;
        FilaDePedidos fila = new FilaDePedidos(CAPACIDADE_FILA);

        Thread[] cozinheiros = new Thread[3];
        Thread[] garcons = new Thread[3];

        for (int i = 0; i < cozinheiros.length; i++) {
            cozinheiros[i] = new Thread(new Cozinheiro(fila, i + 1));
            cozinheiros[i].start();
        }

        for (int i = 0; i < garcons.length; i++) {
            garcons[i] = new Thread(new Garcom(fila, i + 1));
            garcons[i].start();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Thread t : cozinheiros) {
            t.interrupt();
        }
        for (Thread t : garcons) {
            t.interrupt();
        }

        System.out.println("Sistema encerrado.");
    }
}