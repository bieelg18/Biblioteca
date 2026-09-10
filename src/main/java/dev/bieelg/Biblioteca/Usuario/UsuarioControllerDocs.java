package dev.bieelg.Biblioteca.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Component;

public interface UsuarioControllerDocs {

    @Operation(summary = "Cria um usuário no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado"),
            @ApiResponse(responseCode = "400", description = "Usuário não criado")
    })
    UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO);

    @Operation(summary = "Busca um usuário no banco de dados através do e-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    UsuarioDTO buscarPorEmail(String email);

    @Operation(summary = "Atualiza um usuário no banco de dados", description = "Busca o usuário a ser atualizado através do id enviado na URL e atualiza com os dados enviados no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    UsuarioDTO atualizarUser(Integer id, UsuarioDTO usuarioDTO);

    @Operation(summary = "Deleta um usuário no banco de dados através do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    void deletarUsuario(Integer id);

}
