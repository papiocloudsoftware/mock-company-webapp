package com.mockcompany.webapp.data;

import com.mockcompany.webapp.model.ProductItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This Spring pattern is Java/Spring magic.  At runtime, spring will generate an implementation of this class based on
 * the name/arguments of the method signatures defined in the interface.  See this link for details on doing data access:
 *
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 *
 * It's also possible to do specific queries using the @Query annotation:
 *
 * https://www.baeldung.com/spring-data-jpa-query
 */
@Repository
public interface ProductItemRepository extends CrudRepository<ProductItem, Long> {
    @Query("SELECT p FROM ProductItem p " +
            "WHERE LOWER((CONCAT('\"', p.name, '\"'))) = LOWER(:query) " +
            "OR LOWER((CONCAT('\"', p.description, '\"'))) = LOWER(:query) " +
            "OR LOWER(p.name) LIKE LOWER((CONCAT('%', :query, '%'))) " +
            "OR LOWER(p.description) LIKE LOWER((CONCAT('%', :query, '%')))")
    List<ProductItem> findByNameOrDescriptionEqualsOrContainsIgnoreCase(@Param("query") String query);
}
