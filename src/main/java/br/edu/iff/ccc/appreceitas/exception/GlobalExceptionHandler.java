package br.edu.iff.ccc.appreceitas.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Manipulador global de exceções exclusivo da camada MVC (Thymeleaf).
 * <p>
 * É restrito ao pacote {@code br.edu.iff.ccc.appreceitas.controller} para não
 * interceptar as exceções lançadas pelos controllers REST do pacote
 * {@code apirest}, que são tratadas por {@link ApiExceptionHandler} seguindo
 * o padrão RFC 9457 (Problem Details).
 * <p>
 * Cada exceção é traduzida para uma página HTML amigável: 404.html para
 * recursos não encontrados, 500.html para erros genéricos/inesperados do
 * sistema, e a página genérica "erro.html" para as demais situações de
 * negócio (conflito e violação de regra).
 */
@ControllerAdvice(basePackages = "br.edu.iff.ccc.appreceitas.controller")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex, Model model) {
        model.addAttribute("titulo", "Não encontrado");
        model.addAttribute("mensagem", ex.getMessage());
        model.addAttribute("codigo", 404);
        return "404";
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
    public String tratarRegraDeNegocio(RegraDeNegocioException ex, Model model) {
        model.addAttribute("titulo", "Regra de negócio violada");
        model.addAttribute("mensagem", ex.getMessage());
        model.addAttribute("codigo", 400);
        return "erro";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String tratarErroGenerico(Exception ex, Model model) {
        log.error("Erro inesperado no fluxo MVC", ex);
        model.addAttribute("titulo", "Erro interno do servidor");
        model.addAttribute("mensagem", "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        model.addAttribute("codigo", 500);
        return "500";
    }
}
