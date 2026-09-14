package br.edu.iff.ccc.appreceitas.exception;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manipulador global de exceções exclusivo da API REST (pacote apirest).
 * <p>
 * Todas as respostas de erro seguem o padrão RFC 9457 (Problem Details),
 * representado no Spring pela classe {@link ProblemDetail}, garantindo um
 * corpo de resposta consistente: type, title, status, detail, instance e
 * propriedades customizadas.
 * <p>
 * Esta classe é restrita ao pacote {@code br.edu.iff.ccc.appreceitas.apirest}
 * para não conflitar com o {@link GlobalExceptionHandler}, responsável pelas
 * páginas de erro amigáveis (HTML) da camada MVC.
 */
@RestControllerAdvice(basePackages = "br.edu.iff.ccc.appreceitas.apirest")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TYPE_BASE = "https://receitasdatitia.iff.edu.br/erros/";

    private ProblemDetail construirProblemDetail(HttpStatus status, String tipo, String titulo,
                                                  String detalhe, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detalhe);
        problemDetail.setTitle(titulo);
        problemDetail.setType(URI.create(TYPE_BASE + tipo));
        problemDetail.setProperty("timestamp", Instant.now());
        if (request != null) {
            problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        }
        return problemDetail;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ProblemDetail> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex,
                                                                     WebRequest request) {
        ProblemDetail problemDetail = construirProblemDetail(HttpStatus.NOT_FOUND, "recurso-nao-encontrado",
                "Recurso não encontrado", ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(EntidadeDuplicadaException.class)
    public ResponseEntity<ProblemDetail> tratarEntidadeDuplicada(EntidadeDuplicadaException ex,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = construirProblemDetail(HttpStatus.CONFLICT, "entidade-duplicada",
                "Registro duplicado", ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ProblemDetail> tratarRegraDeNegocio(RegraDeNegocioException ex, WebRequest request) {
        ProblemDetail problemDetail = construirProblemDetail(HttpStatus.BAD_REQUEST, "regra-de-negocio",
                "Regra de negócio violada", ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> tratarViolacaoDeIntegridade(DataIntegrityViolationException ex,
                                                                      WebRequest request) {
        ProblemDetail problemDetail = construirProblemDetail(HttpStatus.CONFLICT, "violacao-de-integridade",
                "Violação de integridade de dados",
                "A operação viola uma restrição do banco de dados (campo obrigatório ou valor duplicado).",
                request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    /**
     * Trata erros de validação de Bean Validation (@Valid) nos DTOs recebidos
     * pela API REST, detalhando cada campo inválido na propriedade
     * customizada "invalid_params", conforme recomendado pela RFC 9457.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpHeaders headers,
                                                                    HttpStatusCode status,
                                                                    WebRequest request) {
        ProblemDetail problemDetail = construirProblemDetail(HttpStatus.BAD_REQUEST, "erro-de-validacao",
                "Erro de validação", "Um ou mais campos enviados são inválidos.", request);

        List<InvalidParam> invalidParams = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        problemDetail.setProperty("invalid_params", invalidParams);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> tratarErroGenerico(Exception ex, WebRequest request) {
        ProblemDetail problemDetail = construirProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "erro-interno",
                "Erro interno do servidor",
                "Ocorreu um erro inesperado ao processar a requisição. Tente novamente mais tarde.", request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    /**
     * Representa um parâmetro inválido dentro da resposta de erro RFC 9457,
     * relacionando o nome do campo com a mensagem de validação associada.
     */
    public record InvalidParam(String campo, String mensagem) {
    }
}
