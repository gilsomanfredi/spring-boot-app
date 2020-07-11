package io.github.gilsomanfredi.springbootapp.service.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gilsomanfredi.springbootapp.domain.Client;
import io.github.gilsomanfredi.springbootapp.repository.client.ClientHibernateRepository;
import lombok.RequiredArgsConstructor;

/**
 * Service Hibernate for {@link Client}.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientHibernateService {

    private final ClientHibernateRepository clientRepository;

    public Optional<Client> findOne(Long id) {

        return clientRepository.findById(id);
    }

    public List<Client> findAll() {

        return clientRepository.findAll();
    }

    public Client insert(Client client) {

        return clientRepository.save(client);
    }

    public Client update(Long id, Client client) {

        client.setId(id);
        return clientRepository.save(client);
    }

    public void delete(Long id) {

        clientRepository.deleteById(id);
    }

    public boolean existsById(Long id) {

        return clientRepository.existsById(id);
    }
}
