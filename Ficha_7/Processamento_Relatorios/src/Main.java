public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println("Exceção capturada no thread " + t.getName() + ": " + e.getMessage());
        });

        RelatorioTimer.agendarRelatorios();

        // Simulando uma exceção em um thread
        new Thread(() -> {
            ProcessadorDeRelatorios.setUserId(0); // Usuário não configurado
            new ProcessadorDeRelatorios(0).run();
        }).start();

        // Esperar a conclusão dos threads (opcional)
        try {
            Thread.sleep(30000); // Esperar 30 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}