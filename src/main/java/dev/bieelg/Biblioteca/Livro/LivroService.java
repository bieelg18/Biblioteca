package dev.bieelg.Biblioteca.Livro;

import dev.bieelg.Biblioteca.Exception.LivroIndisponivelException;
import dev.bieelg.Biblioteca.Exception.LivroJaDisponivelException;
import dev.bieelg.Biblioteca.Exception.RecursoNaoEncontradoException;
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
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Livro com o ID " + id + " não encontrado"
                ));
        livroRepository.delete(livro);
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
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Livro com o ID " + id + " não encontrado"
                ));

        if (livroDTO.getLivro() != null){
            livro.setLivro(livroDTO.getLivro());
        }
        if (livroDTO.getAutor() != null){
            livro.setAutor(livroDTO.getAutor());
        }
        Livro livroSalvo = livroRepository.save(livro);

        return livroMapper.toDTO(livroSalvo);
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
        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Livro com o ID " + idLivro + " não encontrado"
                ));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Usuário com o ID " + idUsuario + " não encontrado"
                ));

        if (livro.getStatus() != StatusLivro.DISPONIVEL){
            throw new LivroIndisponivelException(
                    "O livro já está emprestado"
            );
        }
        livro.setUsuario(usuario);
        livro.setStatus(StatusLivro.EMPRESTADO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroMapper.toDTO(livroSalvo);
    }

    //Devolvendo um livro emprestado
    public LivroDTO devolverLivro(Integer idLivro) {
        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "O livro com o ID " + idLivro  + " não foi encontrado"
                ));

        if (livro.getStatus() != StatusLivro.EMPRESTADO) {
            throw new LivroJaDisponivelException(
                    "O livro não está emprestado para ninguém"
            );
        }
        livro.setUsuario(null);
        livro.setStatus(StatusLivro.DISPONIVEL);
        Livro livroSalvo = livroRepository.save(livro);
        return livroMapper.toDTO(livroSalvo);
    }
}
