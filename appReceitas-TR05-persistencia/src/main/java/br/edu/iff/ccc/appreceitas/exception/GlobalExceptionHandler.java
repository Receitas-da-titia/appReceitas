package br.edu.iff.ccc.appreceitas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.iff.ccc.appreceitas.exception.EntidadeDuplicadaException;
import br.edu.iff.ccc.appreceitas.exception.RecursoNaoEncontradoException;

@ControllerAdvice(annotations = Controller.class)

public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex, Model model) {
        model.addAttribute("titulo", "Não encontrado");
        model.addAttribute("mensagem", ex.getMessage());
        model.addAttribute("codigo", 404);
        return "erro";
    }

    @ExceptionHandler(EntidadeDuplicadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String tratarEntidadeDuplicada(EntidadeDuplicadaException ex, Model model) {
        model.addAttribute("titulo", "Registro duplicado");
        model.addAttribute("mensagem", ex.getMessage());
        model.addAttribute("codigo", 409);
        return "erro";
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String tratarRegraDeNegocio(RegraDeNegocioException ex, Model model){
        model.addAttribute("titulo", "Regra de negócio violada");
        model.addAttribute("mensagem", ex.getMessage());
        model.addAttribute("codigo", 400);
        return "erro";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String tratarErroGenerico(Exception ex, Model model) {
        model.addAttribute("titulo", "Erro interno do servidor");
        model.addAttribute("mensagem", "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        model.addAttribute("codigo", 500);
        return "erro";
    }
}
