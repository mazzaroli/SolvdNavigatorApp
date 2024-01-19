package com.solvd.database.dao;

import com.solvd.database.model.Bus;

import java.util.List;

public interface IBusDAO extends IBaseDao<Bus>{
    @Override
    Bus getEntityById(int id);

    @Override
    List<Bus> getEntities();

    @Override
    void saveEntity(Bus entity);

    @Override
    void updateEntity(Bus entity);

    @Override
    void removeEntity(Bus entity);
}
