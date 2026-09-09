package dev.bieelg.Biblioteca.Livro;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    //Endpoint para cria um livro novo
    @PostMapping
    public Livro criarLivro(@RequestBody Livro livro){
        return livroService.criarLivro(livro);
    }

    //Endpoint para listar todos os livros
    @GetMapping
    public List<Livro> listarLivros(){
        return livroService.livros();
    }

    //Endpoint para buscar livros pelo nome do livro
    @GetMapping("/titulo")
    public List<Livro> buscarPorTitulo(@RequestParam String titulo){
        return livroService.livroPorNome(titulo);
    }

    //Endpoint para buscar livros pelo nome do autor
    @GetMapping("/autor")
    public List<Livro> buscarPorAutor(@RequestParam String autor){
        return livroService.buscarPorAutor(autor);
    }

    //Endpoint para deletar livro por id
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Integer id){
        livroService.deletarLivro(id);
    }

    //Endpoint para atualizar um livro
    @PatchMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Integer id, @RequestBody Livro livro){
        return livroService.atualizarLivro(id, livro);
    }

    //Endpoint para emprestar um livro
    @PostMapping("/emprestar/{idLivro}/{idUser}")
    public Livro emprestarLivro(@PathVariable Integer idLivro, @PathVariable Integer idUser){
        return livroService.emprestarLivro(idLivro, idUser);
    }

    //Endpoint para devolver um livro
    @PostMapping("/devolver/{idLivro}")
    public Livro devolverLivro(@PathVariable Integer idLivro){
        return livroService.devolverLivro(idLivro);
    }
}
