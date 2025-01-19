import java.util.Timer;
import java.util.TimerTask;

class RelatorioTimer {
    public static void agendarRelatorios() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int userId = 1;

            @Override
            public void run() {
                Thread thread = new Thread(new ProcessadorDeRelatorios(userId));
                thread.start();
                userId++;
                if (userId > 2) {
                    timer.cancel();
                    System.out.println("Agendamento de relatórios encerrado.");
                }
            }
        }, 0, 5000);
    }
}