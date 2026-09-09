package dev.bieelg.Biblioteca.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findByLivroContainingIgnoreCase(String nome);

    List<Livro> findByStatus(StatusLivro status);

    List<Livro> findByAutorContainingIgnoreCase(String autor);

}
