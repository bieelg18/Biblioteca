package dev.bieelg.Biblioteca.Livro;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroDTO toDTO(Livro livro);

    Livro toEntity(LivroDTO livroDTO);

}
