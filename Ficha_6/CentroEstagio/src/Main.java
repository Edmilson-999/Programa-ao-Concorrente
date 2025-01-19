    public class Main {
    public static void main(String[] args) {
        GestorCentroEstagiario gestor = new GestorCentroEstagiario(3); // Cria 3 computadores
        gestor.iniciarOperacoes();

        // Simula 15 segundos de operação
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gestor.encerrarOperacoes();
    }
}