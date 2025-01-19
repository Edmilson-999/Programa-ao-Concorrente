import java.util.LinkedList;
import java.util.Queue;

class FilaDePedidos {
    private final Queue<String> fila = new LinkedList<>();
    private final int capacidade;

    public FilaDePedidos(int capacidade) {
        this.capacidade = capacidade;
    }

    public synchronized void adicionarPedido(String pedido) throws InterruptedException {
        while (fila.size() == capacidade) {
            System.out.println("Fila cheia! Cozinheiro aguardando...");
            wait();
        }
        fila.add(pedido);
        System.out.println("Pedido adicionado: " + pedido);
        notifyAll();
    }

    public synchronized String retirarPedido() throws InterruptedException {
        while (fila.isEmpty()) {
            System.out.println("Fila vazia! Garçom aguardando...");
            wait();
        }
        String pedido = fila.poll();
        System.out.println("Pedido retirado: " + pedido);
        notifyAll();
        return pedido;
    }
}
