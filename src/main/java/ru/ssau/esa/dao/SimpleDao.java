package ru.ssau.esa.dao;

import java.util.List;

public interface SimpleDao<E, K> {
    E findById(K id);
    E add(E entity);
    void delete(E entity);
    List<E> findAll();
}
