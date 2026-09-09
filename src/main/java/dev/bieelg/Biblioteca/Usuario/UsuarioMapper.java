package dev.bieelg.Biblioteca.Usuario;

import dev.bieelg.Biblioteca.Livro.LivroMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LivroMapper.class)
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

}
