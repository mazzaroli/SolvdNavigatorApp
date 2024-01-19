package com.solvd.database.dao;

import com.solvd.database.model.Station;

import java.util.List;

public interface IStationDAO extends IBaseDao<Station>{
    @Override
    Station getEntityById(int id);

    @Override
    List<Station> getEntities();

    @Override
    void saveEntity(Station entity);

    @Override
    void updateEntity(Station entity);

    @Override
    void removeEntity(Station entity);
}
