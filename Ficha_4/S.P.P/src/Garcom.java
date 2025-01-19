class Garcom implements Runnable {
    private final FilaDePedidos fila;
    private final int id;

    public Garcom(FilaDePedidos fila, int id) {
        this.fila = fila;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000); 
                String pedido = fila.retirarPedido();
                System.out.println("Garçom " + id + " serviu: " + pedido);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
