import java.util.Timer;
import java.util.TimerTask;

public class Relatorio {
    private final int userId;
    private final long timestamp;
    private final String conteudo;

    public Relatorio(int userId, long timestamp, String conteudo) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.conteudo = conteudo;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public String toString() {
        return "Relatório [UserID: " + userId + ", Timestamp: " + timestamp + ", Conteúdo: " + conteudo + "]";
    }
}