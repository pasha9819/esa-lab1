package ru.ssau.esa.dao;

import java.util.List;

public interface CrudRepository<E, K> {
    E findById(K id);
    void save(E entity);
    void delete(E entity);
    List<E> findAll();
}
