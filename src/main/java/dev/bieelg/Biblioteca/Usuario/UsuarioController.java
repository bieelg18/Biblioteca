package dev.bieelg.Biblioteca.Usuario;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Endpoint para criar usuarios
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.criarUsuario(usuario);
    }

    //Endpoint para listar todos os usuarios
    @GetMapping
    public List<Usuario> buscarUsuarios(){
        return usuarioService.usuarios();
    }

    //Endpoint para buscar usuario por email
    @GetMapping("/buscar")
    public Usuario buscarPorEmail(@RequestParam String email){
        return usuarioService.buscarPorEmail(email);
    }

    //Endpoint para atualizar usuario
    @PatchMapping("/{id}")
    public Usuario atualizarUser(@PathVariable Integer id, @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(id, usuario);
    }

    //Endpoint para deletar usuario por id
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarPorId(id);
    }
}
