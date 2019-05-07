package ru.karelin.tmwebspring.repository;

import ru.karelin.tmwebspring.entity.AbstractEntity;

import java.util.List;

public interface EntityRepository<T extends AbstractEntity> {

    List<T> findAllByUserId(String userId);

    T find(String id);

    T findByIdAndUserId(String id, String userId);

    void save(T item);

    void remove(T item);
}
