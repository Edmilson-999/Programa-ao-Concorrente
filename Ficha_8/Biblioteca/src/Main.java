public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro("O Senhor dos Anéis");
        Livro livro2 = new Livro("1984");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        System.out.println("Disponibilidade de 'O Senhor dos Anéis': " + biblioteca.consultarDisponibilidade("O Senhor dos Anéis"));
        System.out.println("Disponibilidade de '1984': " + biblioteca.consultarDisponibilidade("1984"));

        System.out.println("\nTentando emprestar 'O Senhor dos Anéis'...");
        biblioteca.emprestarLivro("O Senhor dos Anéis");

        System.out.println("Disponibilidade de 'O Senhor dos Anéis': " + biblioteca.consultarDisponibilidade("O Senhor dos Anéis"));

        System.out.println("\nTentando emprestar 'O Senhor dos Anéis' novamente...");
        biblioteca.emprestarLivro("O Senhor dos Anéis");

        System.out.println("\nDevolvendo 'O Senhor dos Anéis'...");
        biblioteca.devolverLivro("O Senhor dos Anéis");

        System.out.println("Disponibilidade de 'O Senhor dos Anéis': " + biblioteca.consultarDisponibilidade("O Senhor dos Anéis"));

        System.out.println("\nVerificando disponibilidade de um livro inexistente:");
        System.out.println("Disponibilidade de 'Harry Potter': " + biblioteca.consultarDisponibilidade("Harry Potter"));
    }
}
