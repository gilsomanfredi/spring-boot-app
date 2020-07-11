package io.github.gilsomanfredi.springbootapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain for clients.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Size(max = 15)
    private String fone;

    @Email
    @Size(max = 50)
    private String email;

}
