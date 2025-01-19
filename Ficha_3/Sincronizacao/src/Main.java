public class Main {
    public static void main(String[] args   ) throws InterruptedException {
        RegistroDeNotas  registro = new RegistroDeNotas();

        Thread t1 = new Thread(new ProcessadorDeNotas(registro, "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_3/notas1.txt"));
        Thread t2 = new Thread(new ProcessadorDeNotas(registro, "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_3/notas2.txt"));
        Thread t3 = new Thread(new ProcessadorDeNotas(registro, "/home/edmilson-alves/EISC/3º Ano/1º Semestre/Programação Concorrente (2)/Exercicios/Ficha_3/notas3.txt"));

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(10000);
        t1.interrupt();
        t2.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Medias finais: ");
        System.out.println(registro.getMedias());
    }
}