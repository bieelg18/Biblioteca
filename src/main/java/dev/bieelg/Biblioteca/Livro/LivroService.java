package dev.bieelg.Biblioteca.Livro;

import dev.bieelg.Biblioteca.Usuario.Usuario;
import dev.bieelg.Biblioteca.Usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroMapper livroMapper;


    //Criando um livro novo
    public LivroDTO criarLivro(LivroDTO livroDTO){
        Livro livro = livroMapper.toEntity(livroDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroMapper.toDTO(livroSalvo);
    }

    //Listando todos os livros
    public List<LivroDTO> livros(){
        List<Livro> livros = livroRepository.findAll();

        return livros.stream()
                .map(livroMapper::toDTO)
                .toList();
    }

    //Buscando livro por nome
    public List<LivroDTO> livroPorNome(String livro){
        List<Livro> livros = livroRepository.findByLivroContainingIgnoreCase(livro);

        return livros.stream()
                .map(livroMapper::toDTO)
                .toList();
    }

    //Deletando livro por id
    public void deletarLivro(Integer id){
        livroRepository.deleteById(id);
    }

    //Listando apenas livros disponiveis
    public List<LivroDTO> disponiveis(){
        List<Livro> livros = livroRepository.findByStatus(StatusLivro.DISPONIVEL);

        return livros.stream()
                .map(livroMapper::toDTO)
                .toList();
    }

    //Listando apenas livros emprestados
    public List<LivroDTO> emprestados(){
        List<Livro> livros = livroRepository.findByStatus(StatusLivro.EMPRESTADO);

        return livros.stream()
                .map(livroMapper::toDTO)
                .toList();
    }

    //Alterando dados dos livros
    public LivroDTO atualizarLivro(Integer id, LivroDTO livroDTO){
        Optional<Livro> livroExistente = livroRepository.findById(id);
        if (livroExistente.isPresent()){
            Livro livro1 = livroExistente.get();
            if (livroDTO.getLivro() != null){
                livro1.setLivro(livroDTO.getLivro());
            }
            if (livroDTO.getAutor() != null){
                livro1.setAutor(livroDTO.getAutor());
            }

            Livro livroSalvo = livroRepository.save(livro1);
            return livroMapper.toDTO(livroSalvo);
        }
        return null;
    }

    //Buscando livros pelo nome do autor
    public List<LivroDTO> buscarPorAutor(String autor){
        List<Livro> livros = livroRepository.findByAutorContainingIgnoreCase(autor);

        return livros.stream()
                .map(livroMapper::toDTO)
                .toList();
    }

    //Emprestando um livro que está disponivel
    public LivroDTO emprestarLivro(Integer idLivro, Integer idUsuario){
        Optional<Livro> livroExistente = livroRepository.findById(idLivro);
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
        if (livroExistente.isPresent() && usuarioExistente.isPresent()){
            Livro livro = livroExistente.get();
            Usuario user = usuarioExistente.get();
            if (livro.getStatus() == StatusLivro.DISPONIVEL){
                livro.setUsuario(user);
                livro.setStatus(StatusLivro.EMPRESTADO);
                Livro livroSalvo = livroRepository.save(livro);
                return livroMapper.toDTO(livroSalvo);
            }
            return null;
        }
        return null;
    }

    //Devolvendo um livro emprestado
    public LivroDTO devolverLivro(Integer idLivro){
        Optional<Livro> livroExistente = livroRepository.findById(idLivro);
        if (livroExistente.isPresent()){
            Livro livro = livroExistente.get();
            if (livro.getStatus() == StatusLivro.EMPRESTADO){
                livro.setUsuario(null);
                livro.setStatus(StatusLivro.DISPONIVEL);
                Livro livroSalvo = livroRepository.save(livro);
                return livroMapper.toDTO(livroSalvo);
            }
            return null;
        }
        return null;
    }
}
