package dev.bieelg.Biblioteca.Exception;

public class LivroIndisponivelException extends RuntimeException{

    public LivroIndisponivelException(String mensagem){
        super(mensagem);
    }

}
