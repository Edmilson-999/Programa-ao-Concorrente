import java.util.*;
import java.io.*;

public class RegistroDeNotas {

    private Map<Integer, Double> medias = new HashMap<>();
    private Map<Integer, Integer> contadores = new HashMap<>();

    public synchronized void atualizarMedias(int id, double novaNota){
        double mediaAtual = medias.getOrDefault(id, 0.0);
        int contadorAtual = contadores.getOrDefault(id, 0);

        double novaMedia = ((mediaAtual * contadorAtual) + novaNota) / (contadorAtual + 1);

        medias.put(id, novaMedia);
        contadores.put(id, contadorAtual + 1);
        System.out.println("Media do aluno " + id + " atualizado para " + novaMedia );
    }
    public synchronized Map<Integer, Double> getMedias(){
        return new HashMap<>(medias);
    }

}
