package io.github.gilsomanfredi.springbootapp.repository.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.gilsomanfredi.springbootapp.domain.Client;

/**
 * Repository JPA(Hibernate) for {@link Client}.
 *
 * @author Gilso Manfredi
 * @since 1.0 (09/07/2020)
 */
@Repository
public interface ClientHibernateRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContaining(String name);

}
