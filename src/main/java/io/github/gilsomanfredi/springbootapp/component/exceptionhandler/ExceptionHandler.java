package io.github.gilsomanfredi.springbootapp.component.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception Handler to resolve exceptions.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        var fields = new ArrayList<Error.Field>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {

            var field = new Error.Field();
            field.setFielName(((FieldError)objectError).getField());
            field.setMessage(objectError.getDefaultMessage());

            fields.add(field);
        });

        var error = new Error();
        error.setStatus(status.value());
        error.setMessage("Um ou mais campos estão inválidos");
        error.setDateTime(LocalDateTime.now());
        error.setFields(fields);

        return super.handleExceptionInternal(ex, error, headers, status, request);
    }
}
