package com.solvd.database.dao;

import com.solvd.database.model.Car;

import java.util.List;

public interface ICarDAO extends IBaseDao<Car>{
    @Override
    Car getEntityById(int id);

    @Override
    List<Car> getEntities();

    @Override
    void saveEntity(Car entity);

    @Override
    void updateEntity(Car entity);

    @Override
    void removeEntity(Car entity);
}
