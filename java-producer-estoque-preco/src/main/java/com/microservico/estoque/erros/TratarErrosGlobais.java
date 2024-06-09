package com.microservico.estoque.erros;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * Classe para tratar erros globais da aplicação.
 */
@ControllerAdvice
public class TratarErrosGlobais {

    /**
     * Trata a exceção IllegalArgumentException.
     * @param ex A exceção IllegalArgumentException lançada.
     * @param request O objeto WebRequest contendo detalhes da solicitação.
     * @return ResponseEntity com status HTTP 400 Bad Request e um objeto ErrorResponse.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false), ""));
    }

    /**
     * Trata a exceção HttpMessageNotReadableException.
     * @param ex A exceção HttpMessageNotReadableException lançada.
     * @param request O objeto WebRequest contendo detalhes da solicitação.
     * @return ResponseEntity com status HTTP 400 Bad Request e um objeto ErrorResponse.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        String errorMessage = "Erro de formatação JSON: Verifique seu JSON para que ele possua formatos adequados!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, request.getDescription(false), ex.getMostSpecificCause().getMessage()));
    }

    /**
     * Trata qualquer outra exceção não tratada pelas anotações @ExceptionHandler acima.
     * @param ex A exceção lançada.
     * @param request O objeto WebRequest contendo detalhes da solicitação.
     * @return ResponseEntity com status HTTP 500 Internal Server Error e um objeto ErrorResponse.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false), ""));
    }

    /**
     * Classe interna para representar a resposta de erro padronizada.
     */
    static class ErrorResponse {
        private HttpStatus status;
        private String message;
        private String path;
        private String error;

        public ErrorResponse(HttpStatus status, String message, String path, String error) {
            this.status = status;
            this.message = message;
            this.path = path;
            this.error = error;
        }

        /**
         * Getters
         */
        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public String getError() {
            return error;
        }
    }
}
