package dev.bieelg.Biblioteca.Exception;

public class LivroJaDisponivelException extends RuntimeException{

    public LivroJaDisponivelException(String mensagem){
        super(mensagem);
    }

}
