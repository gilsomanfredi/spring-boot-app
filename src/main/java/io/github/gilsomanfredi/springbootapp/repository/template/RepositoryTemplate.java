package io.github.gilsomanfredi.springbootapp.repository.template;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Class that centralizes the JdbcTemplate methods.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Repository
public class RepositoryTemplate {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public <T> T queryForObject(String sql, Map<String, Object> parameters, RowMapper<T> rowMapper) {
        try {
            return getJdbcTemplate().queryForObject(sql, parameters, rowMapper);
        } catch (EmptyResultDataAccessException var5) {
            return null;
        }
    }

    public <T> T queryForObject(String sql, Map<String, Object> parameters, Class<T> clazz) {
        try {
            return getJdbcTemplate().queryForObject(sql, parameters, clazz);
        } catch (EmptyResultDataAccessException var5) {
            return null;
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return query(sql, new HashMap<>(), rowMapper);
    }

    public <T> List<T> query(String sql, Map<String, Object> parameters, RowMapper<T> rowMapper) {
        return getJdbcTemplate().query(sql, parameters, rowMapper);
    }

    public <T> Long insert(String sql, Map<String, Object> parameters, Class<T> clazz) {

        Long generatedId = null;

        Optional<Field> findFieldId = Stream.of(clazz.getDeclaredFields())
                .filter(field -> Stream.of(field.getDeclaredAnnotations())
                        .anyMatch(annotation -> annotation.annotationType().getName().equals(Id.class.getName())))
                .findFirst();

        if (findFieldId.isPresent()) {
            generatedId = insert(sql, parameters, findFieldId.get().getName());
        } else {
            update(sql, parameters);
        }

        return generatedId;
    }

    public Long insert(String sql, Map<String, Object> parameters, String generatedId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(sql, parameterSource, keyHolder, new String[]{generatedId});
        return this.getKeyHolder(keyHolder);
    }

    public Long getKeyHolder(KeyHolder keyHolder) {
        return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    }

    public void update(String sql, Map<String, Object> parameters) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(parameters);
        this.getJdbcTemplate().update(sql, parameterSource);
    }

    public void delete(String sql, Map<String, Object> parameters) {
        this.update(sql, parameters);
    }
}
