package dev.bieelg.Biblioteca.Livro;

import dev.bieelg.Biblioteca.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroDTO {

    private Integer id;
    private String livro;
    private String autor;
    private StatusLivro status;
    private Integer usuarioId;

}
