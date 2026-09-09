package dev.bieelg.Biblioteca.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bieelg.Biblioteca.Livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Livro> livros;

}
