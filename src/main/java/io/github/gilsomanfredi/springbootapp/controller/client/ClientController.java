package io.github.gilsomanfredi.springbootapp.controller.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.gilsomanfredi.springbootapp.domain.Client;
import io.github.gilsomanfredi.springbootapp.service.client.ClientHibernateService;
import io.github.gilsomanfredi.springbootapp.service.client.ClientJdbcService;
import lombok.RequiredArgsConstructor;

/**
 * Controller for {@link Client}.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

//    private final ClientHibernateService clientService;
    private final ClientJdbcService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findOne(@PathVariable Long id) {

        return ResponseEntity.of(clientService.findOne(id));
    }

    @GetMapping
    public List<Client> findAll() {

        return clientService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client insert(@Valid @RequestBody Client client) {

        return clientService.insert(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@Valid @PathVariable Long id, @RequestBody Client client) {

        return clientService.existsById(id)
                ? ResponseEntity.ok(clientService.update(id, client))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {

        if (clientService.existsById(id)) {
            clientService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
