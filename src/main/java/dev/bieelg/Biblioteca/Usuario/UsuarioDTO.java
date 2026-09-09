package dev.bieelg.Biblioteca.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bieelg.Biblioteca.Livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private Integer id;
    private String nome;
    private String email;
    private List<Livro> livros;

}
