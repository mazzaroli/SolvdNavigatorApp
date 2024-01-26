package com.solvd.database.dao;

import com.solvd.database.model.Connection;

import java.util.List;

public interface IConnectionDAO extends IBaseDao<Connection>{
    @Override
    Connection getEntityById(int id);

    @Override
    List<Connection> getEntities();

    @Override
    void saveEntity(Connection entity);

    @Override
    void updateEntity(Connection entity);

    @Override
    void removeEntity(Connection entity);
}
