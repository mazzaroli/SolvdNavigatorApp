package com.solvd.database.dao;

import com.solvd.database.model.Route;

import java.util.List;

public interface IRouteDAO extends IBaseDao<Route>{
    @Override
    Route getEntityById(int id);

    @Override
    List<Route> getEntities();

    @Override
    void saveEntity(Route entity);

    @Override
    void updateEntity(Route entity);

    @Override
    void removeEntity(Route entity);
}
