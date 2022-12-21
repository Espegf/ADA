package egf.myshop.persistence.dao;

import java.util.Optional;

/**
 * @author espeg
 */
public interface GenericDAO<T> {
    void create(T entity);

    void save(T entity);

    void deleteById(Long id);

    void delete(T entity);

    Optional<T> findById(Long id);
}
