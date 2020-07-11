package io.github.gilsomanfredi.springbootapp.repository.client.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.github.gilsomanfredi.springbootapp.domain.Client;

/**
 * Mappper for {@link Client}.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int i) throws SQLException {

        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setFone(rs.getString("fone"));
        client.setEmail(rs.getString("email"));

        return client;
    }
}
