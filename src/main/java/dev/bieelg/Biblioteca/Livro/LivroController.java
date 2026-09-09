package dev.bieelg.Biblioteca.Livro;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    //Endpoint para cria um livro novo
    @PostMapping
    public LivroDTO criarLivro(@RequestBody LivroDTO livro){
        return livroService.criarLivro(livro);
    }

    //Endpoint para listar todos os livros
    @GetMapping
    public List<LivroDTO> listarLivros(){
        return livroService.livros();
    }

    //Endpoint para buscar livros pelo nome do livro
    @GetMapping("/titulo")
    public List<LivroDTO> buscarPorTitulo(@RequestParam String titulo){
        return livroService.livroPorNome(titulo);
    }

    //Endpoint para buscar livros pelo nome do autor
    @GetMapping("/autor")
    public List<LivroDTO> buscarPorAutor(@RequestParam String autor){
        return livroService.buscarPorAutor(autor);
    }

    //Endpoint para deletar livro por id
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Integer id){
        livroService.deletarLivro(id);
    }

    //Endpoint para atualizar um livro
    @PatchMapping("/{id}")
    public LivroDTO atualizarLivro(@PathVariable Integer id, @RequestBody LivroDTO livro){
        return livroService.atualizarLivro(id, livro);
    }

    //Endpoint para emprestar um livro
    @PostMapping("/emprestar/{idLivro}/{idUser}")
    public LivroDTO emprestarLivro(@PathVariable Integer idLivro, @PathVariable Integer idUser){
        return livroService.emprestarLivro(idLivro, idUser);
    }

    //Endpoint para devolver um livro
    @PostMapping("/devolver/{idLivro}")
    public LivroDTO devolverLivro(@PathVariable Integer idLivro){
        return livroService.devolverLivro(idLivro);
    }

    //Endpoint para listar todos os livros com status disponível
    @GetMapping("/disponivel")
    public List<LivroDTO> livrosDisponiveis(){
        return livroService.disponiveis();
    }

    //Endpoint para listar todos os livros com status emprestado
    @GetMapping("/emprestado")
    public List<LivroDTO> livrosEmprestados(){
        return livroService.emprestados();
    }
}
