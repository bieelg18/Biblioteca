package dev.bieelg.Biblioteca.Usuario;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    //Criando usuario
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioSalvo);
    }

    //Listando todos os usuarios
    public List<UsuarioDTO> usuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    //Buscar usuario por e-mail
    public UsuarioDTO buscarPorEmail(String email){
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);

        if (usuarioExistente.isPresent()){
            Usuario usuario = usuarioExistente.get();
            return usuarioMapper.toDTO(usuario);
        }
        return null;
    }

    //Deletando usuario por id
    public void deletarPorId(Integer id){
        usuarioRepository.deleteById(id);
    }

    //Alterando dados do usuario
    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()){
            Usuario user = usuarioExistente.get();
            if (usuarioDTO.getNome() != null){
                user.setNome(usuarioDTO.getNome());
            }
            if (usuarioDTO.getEmail() != null){
                user.setEmail(usuarioDTO.getEmail());
            }
            Usuario userSalvo = usuarioRepository.save(user);
            return usuarioMapper.toDTO(userSalvo);
        }
        return null;
    }

}
