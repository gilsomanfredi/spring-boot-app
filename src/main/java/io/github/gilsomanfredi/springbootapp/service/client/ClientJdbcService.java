package io.github.gilsomanfredi.springbootapp.service.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gilsomanfredi.springbootapp.domain.Client;
import io.github.gilsomanfredi.springbootapp.repository.client.ClientJdbcRepository;
import lombok.RequiredArgsConstructor;

/**
 * Service JDBC for {@link Client}.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientJdbcService {

    private final ClientJdbcRepository clientRepository;

    public Optional<Client> findOne(Long id) {

        return clientRepository.findById(id);
    }

    public List<Client> findAll() {

        return clientRepository.findAll();
    }

    public Client insert(Client client) {

        return clientRepository.insert(client);
    }

    public Client update(Long id, Client client) {

        return clientRepository.update(id, client);
    }

    public void delete(Long id) {

        clientRepository.deleteById(id);
    }

    public boolean existsById(Long id) {

        return clientRepository.existsById(id);
    }
}
