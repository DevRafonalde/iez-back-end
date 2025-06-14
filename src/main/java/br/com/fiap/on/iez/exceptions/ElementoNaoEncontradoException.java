package br.com.fiap.on.iez.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementoNaoEncontradoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ElementoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}