import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessadorDeNotas  implements Runnable{
    private RegistroDeNotas registro;
    private String arquivo;

    public ProcessadorDeNotas(RegistroDeNotas registro, String arquivo){
        this.registro = registro;
        this.arquivo = arquivo;
    }

    @Override
    public void run(){
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            while ((linha = br.readLine()) != null && !Thread.currentThread().isInterrupted()){
                String[] dados = linha.split(", ");
                int id = Integer.parseInt(dados[0].split(":")[1].trim());
                double nota = Double.parseDouble(dados[3].split(":")[1].trim());
                registro.atualizarMedias(id, nota);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " interrompida. ");
    }

}
