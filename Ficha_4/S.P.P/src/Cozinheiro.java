class Cozinheiro implements Runnable {
    private final FilaDePedidos fila;
    private final int id;

    public Cozinheiro(FilaDePedidos fila, int id) {
        this.fila = fila;
        this.id = id;
    }

    @Override
    public void run() {
        int count = 1;
        try {
            while (true) {
                Thread.sleep(2000); // Simula o tempo para preparar o prato
                String pedido = "Prato " + count + " (Cozinheiro " + id + ")";
                fila.adicionarPedido(pedido);
                count++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}