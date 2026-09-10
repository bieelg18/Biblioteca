package dev.bieelg.Biblioteca.Livro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface LivroControllerDocs {

    @Operation(summary = "Cria um livro no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado"),
            @ApiResponse(responseCode = "404", description = "Livro não criado")
    })
    LivroDTO criarLivro(LivroDTO livroDTO);

    @Operation(summary = "Deleta um livro no banco de dados através do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro deletado"),
            @ApiResponse(responseCode = "404", description = "Livro não deletado")
    })
    void deletarLivro(Integer id);

    @Operation(summary = "Atualiza um livro no banco de dados", description = "Busca o livro a ser atualizado através do id enviado na URL e atualiza com os dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    LivroDTO atualizarLivro(Integer id, LivroDTO livroDTO);

    @Operation(summary = "Empresta um livro que está com status disponivel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro emprestado"),
            @ApiResponse(responseCode = "409", description = "O livro já está emprestado")
    })
    LivroDTO emprestarLivro(Integer idLivro, Integer idUser);

    @Operation(summary = "Devolve um livro que está emprestado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro devolvido"),
            @ApiResponse(responseCode = "409", description = "O livro não estava emprestado a nenhum usuário")
    })
    LivroDTO devolverLivro(Integer idLivro);

}
