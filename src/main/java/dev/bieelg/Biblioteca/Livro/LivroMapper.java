package dev.bieelg.Biblioteca.Livro;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    LivroDTO toDTO(Livro livro);

    @Mapping(source = "usuarioId", target = "usuario.id")
    Livro toEntity(LivroDTO livroDTO);

}
