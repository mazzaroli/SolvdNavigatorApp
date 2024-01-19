package com.solvd.database.dao;

import java.util.List;

public interface IBaseDao<T> {
    T getEntityById(int id);

    List<T> getEntities();

    void saveEntity(T entity);

    void updateEntity(T entity);

    void removeEntity(T entity);
}
