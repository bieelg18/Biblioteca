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


    //Criando um livro novo
    public Livro criarLivro(Livro livro){
        return livroRepository.save(livro);
    }

    //Listando todos os livros
    public List<Livro> livros(){
        return livroRepository.findAll();
    }

    //Buscando livro por nome
    public List<Livro> livroPorNome(String livro){
        return livroRepository.findByLivroContainingIgnoreCase(livro);
    }

    //Deletando livro por id
    public void deletarLivro(Integer id){
        livroRepository.deleteById(id);
    }

    //Listando apenas livros disponiveis
    public List<Livro> disponiveis(){
        return livroRepository.findByStatus(StatusLivro.DISPONIVEL);
    }

    //Listando apenas livros emprestados
    public List<Livro> emprestados(){
        return livroRepository.findByStatus(StatusLivro.EMPRESTADO);
    }

    //Alterando dados dos livros
    public Livro atualizarLivro(Integer id, Livro livro){
        Optional<Livro> livroExistente = livroRepository.findById(id);
        if (livroExistente.isPresent()){
            Livro livro1 = livroExistente.get();
            if (livro.getLivro() != null){
                livro1.setLivro(livro.getLivro());
            }
            if (livro.getAutor() != null){
                livro1.setAutor(livro.getAutor());
            }
            if (livro.getStatus() != null){
                livro1.setStatus(livro.getStatus());
            }
            if (livro.getUsuario() != null){
                livro1.setUsuario(livro.getUsuario());
            }
            Livro livroSalvo = livroRepository.save(livro1);
            return livroSalvo;
        }
        return null;
    }

    //Buscando livros pelo nome do autor
    public List<Livro> buscarPorAutor(String autor){
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    //Emprestando um livro que está disponivel
    public Livro emprestarLivro(Integer idLivro, Integer idUsuario){
        Optional<Livro> livroExistente = livroRepository.findById(idLivro);
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
        if (livroExistente.isPresent() && usuarioExistente.isPresent()){
            Livro livro1 = livroExistente.get();
            Usuario user = usuarioExistente.get();
            if (livro1.getStatus() == StatusLivro.DISPONIVEL){
                livro1.setUsuario(user);
                livro1.setStatus(StatusLivro.EMPRESTADO);
                Livro livroSalvo = livroRepository.save(livro1);
                return livroSalvo;
            }
            return null;
        }
        return null;
    }

    //Devolvendo um livro emprestado
    public Livro devolverLivro(Integer idLivro){
        Optional<Livro> livroExistente = livroRepository.findById(idLivro);
        if (livroExistente.isPresent()){
            Livro livro = livroExistente.get();
            if (livro.getStatus() == StatusLivro.EMPRESTADO){
                livro.setUsuario(null);
                livro.setStatus(StatusLivro.DISPONIVEL);
                Livro livroSalvo = livroRepository.save(livro);
                return livroSalvo;
            }
            return null;
        }
        return null;
    }
}
