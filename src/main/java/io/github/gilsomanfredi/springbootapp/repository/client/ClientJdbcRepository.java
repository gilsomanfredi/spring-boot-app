package io.github.gilsomanfredi.springbootapp.repository.client;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.gilsomanfredi.springbootapp.domain.Client;
import io.github.gilsomanfredi.springbootapp.repository.template.RepositoryTemplate;
import io.github.gilsomanfredi.springbootapp.sql.client.ClientSaveSql;
import io.github.gilsomanfredi.springbootapp.sql.client.ClientSelectSql;
import io.github.gilsomanfredi.springbootapp.repository.client.mapper.ClientRowMapper;

/**
 * Repository JDBC for {@link Client}.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Repository
public class ClientJdbcRepository {

    @Autowired
    RepositoryTemplate repositoryTemplate;

    public Optional<Client> findById(Long id) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", id);

        return Optional.ofNullable(repositoryTemplate.queryForObject(ClientSelectSql.find_by_id, parameters, new ClientRowMapper()));
    }

    public List<Client> findAll() {

        return repositoryTemplate.query(ClientSelectSql.find_all, new ClientRowMapper());
    }

    public Client insert(Client client) {

        Map<String, Object> parameters = getParameters(client);

        Long id = repositoryTemplate.insert(ClientSaveSql.insert, parameters, Client.class);

        client.setId(id);

        return client;
    }

    public Client update(Long id, Client client) {

        Map<String, Object> parameters = getParameters(client);
        parameters.put("id", id);

        repositoryTemplate.update(ClientSaveSql.update, parameters);

        return client;
    }

    public void deleteById(Long id) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", id);

        repositoryTemplate.delete(ClientSaveSql.delete, parameters);
    }

    public boolean existsById(Long id) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", id);

        return repositoryTemplate.queryForObject(ClientSelectSql.exists, parameters, Long.class) > 0;
    }

    private Map<String, Object> getParameters(Client client) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("name", client.getName());
        parameters.put("fone", client.getFone());
        parameters.put("email", client.getEmail());

        return parameters;
    }
}
