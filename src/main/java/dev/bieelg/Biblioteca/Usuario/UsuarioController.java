package dev.bieelg.Biblioteca.Usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    //Endpoint para criar usuarios
    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody UsuarioDTO usuario){
        return usuarioService.criarUsuario(usuario);
    }

    //Endpoint para listar todos os usuarios
    @GetMapping
    public List<UsuarioDTO> buscarUsuarios(){
        return usuarioService.usuarios();
    }

    //Endpoint para buscar usuario por email
    @GetMapping("/buscar")
    public UsuarioDTO buscarPorEmail(@RequestParam String email){
        return usuarioService.buscarPorEmail(email);
    }

    //Endpoint para atualizar usuario
    @PatchMapping("/{id}")
    public UsuarioDTO atualizarUser(@PathVariable Integer id, @RequestBody UsuarioDTO usuario){
        return usuarioService.atualizarUsuario(id, usuario);
    }

    //Endpoint para deletar usuario por id
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarPorId(id);
    }
}
