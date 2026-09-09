package dev.bieelg.Biblioteca.Usuario;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    //Criando usuario
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Listando todos os usuarios
    public List<Usuario> usuarios(){
        return usuarioRepository.findAll();
    }

    //Buscar usuario por e-mail
    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    //Deletando usuario por id
    public void deletarPorId(Integer id){
        usuarioRepository.deleteById(id);
    }

    //Alterando dados do usuario
    public Usuario atualizarUsuario(Integer id, Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()){
            Usuario user = usuarioExistente.get();
            if (usuario.getNome() != null){
                user.setNome(usuario.getNome());
            }
            if (usuario.getEmail() != null){
                user.setEmail(usuario.getEmail());
            }
            Usuario userSalvo = usuarioRepository.save(user);
            return userSalvo;
        }
        return null;
    }

}
