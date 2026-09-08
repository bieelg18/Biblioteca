package dev.bieelg.Biblioteca.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    Optional<Livro> findByLivro(String nome);

}
