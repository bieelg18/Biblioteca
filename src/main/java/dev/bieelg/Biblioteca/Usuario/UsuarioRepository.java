package dev.bieelg.Biblioteca.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //Método para buscar usuario por email
    Optional<Usuario> findByEmail(String email);

}
