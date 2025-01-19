import java.util.*;
import java.util.concurrent.locks.*;

public class Biblioteca {

    private final Map<String, Livro> catalogoLivros = new HashMap<>();
    private final ReentrantLock catalogoLock = new ReentrantLock();
    private final ReadWriteLock emprestimoLock = new ReentrantReadWriteLock();
    private final StampedLock disponibilidadeLock = new StampedLock();
    private final Condition livroDisponivel = emprestimoLock.writeLock().newCondition();

    // Cadastro de Livros
    public void adicionarLivro(Livro livro) {
        catalogoLock.lock();
        try {
            catalogoLivros.put(livro.getTitulo(), livro);
            System.out.println("Livro adicionado: " + livro.getTitulo());
        } finally {
            catalogoLock.unlock();
        }
    }

    public void removerLivro(String titulo) {
        catalogoLock.lock();
        try {
            catalogoLivros.remove(titulo);
            System.out.println("Livro removido: " + titulo);
        } finally {
            catalogoLock.unlock();
        }
    }

    // Empréstimo de Livros
    public void emprestarLivro(String titulo) {
        emprestimoLock.readLock().lock();
        try {
            Livro livro = catalogoLivros.get(titulo);
            if (livro != null && livro.isDisponivel()) {
                emprestimoLock.writeLock().lock();
                try {
                    livro.emprestar();
                    System.out.println("Livro emprestado: " + titulo);
                } finally {
                    emprestimoLock.writeLock().unlock();
                }
            } else {
                System.out.println("Livro não disponível para empréstimo: " + titulo);
            }
        } finally {
            emprestimoLock.readLock().unlock();
        }
    }

    // Devolução de Livros
    public void devolverLivro(String titulo) {
        emprestimoLock.writeLock().lock();
        try {
            Livro livro = catalogoLivros.get(titulo);
            if (livro != null && !livro.isDisponivel()) {
                livro.devolver();
                livroDisponivel.signalAll();
                System.out.println("Livro devolvido: " + titulo);
            }
        } finally {
            emprestimoLock.writeLock().unlock();
        }
    }

    // Consulta de Disponibilidade
    public boolean consultarDisponibilidade(String titulo) {
        long stamp = disponibilidadeLock.tryOptimisticRead();
        Livro livro = catalogoLivros.get(titulo);
        if (!disponibilidadeLock.validate(stamp)) {
            stamp = disponibilidadeLock.readLock();
            try {
                livro = catalogoLivros.get(titulo);
            } finally {
                disponibilidadeLock.unlockRead(stamp);
            }
        }
        return livro != null && livro.isDisponivel();
    }

    // Gerenciamento de Usuários
    private final Map<String, String> usuarios = new HashMap<>();
    private final ReentrantLock usuariosLock = new ReentrantLock();
    private final Condition usuarioAdicionado = usuariosLock.newCondition();

    public void adicionarUsuario(String id, String nome) {
        usuariosLock.lock();
        try {
            usuarios.put(id, nome);
            usuarioAdicionado.signalAll();
            System.out.println("Usuário adicionado: " + nome);
        } finally {
            usuariosLock.unlock();
        }
    }

    public void removerUsuario(String id) {
        usuariosLock.lock();
        try {
            usuarios.remove(id);
            System.out.println("Usuário removido: " + id);
        } finally {
            usuariosLock.unlock();
        }
    }
}
