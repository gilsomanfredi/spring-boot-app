package io.github.gilsomanfredi.springbootapp.component.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Returns all erros of app.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Getter
@Setter
public class Error {

    private Integer status;

    private String message;

    private LocalDateTime dateTime;

    private List<Field> fields;

    @Getter
    @Setter
    static class Field {

        private String fielName;

        private String message;
    }

}
