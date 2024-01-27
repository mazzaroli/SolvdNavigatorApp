package com.solvd.database.dao.mybatis;

import com.solvd.database.dao.ICarDAO;
import com.solvd.database.model.Car;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/*
 * Implementation of the ICarDAO interface using MyBatis for database operations related to the Car entity.
 */
public class CarDAOImpl implements ICarDAO {
    private static final Logger LOGGER = LogManager.getLogger(CarDAOImpl.class);
    private static SqlSession sqlSession;
    private static final SqlSessionFactory sqlSessionFactory;
    private static Reader reader = null;
    private static ICarDAO carMapper;

    // Static block to initialize the SqlSessionFactory
    static {
        try {
            reader = Resources.getResourceAsReader("mybatis.config.xml");
        } catch (IOException e) {
            LOGGER.info(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * Retrieves a Car entity from the database based on the provided ID.
     *
     * @param id The unique identifier of the Car entity to retrieve.
     * @return The Car entity with the specified ID, or null if not found.
     */
    @Override
    public Car getEntityById(int id) {
        carMapper = sqlSessionFactory.openSession().getMapper(ICarDAO.class);
        return carMapper.getEntityById(id);
    }

    /**
     * Retrieves a list of all Car entities from the database.
     *
     * @return A List containing all Car entities in the database.
     */
    @Override
    public List<Car> getEntities() {
        carMapper = sqlSessionFactory.openSession().getMapper(ICarDAO.class);
        return carMapper.getEntities();
    }

    /**
     * Saves a new Car entity to the database.
     *
     * @param entity The Car entity to be saved.
     */
    @Override
    public void saveEntity(Car entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertEntity", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Updates an existing Car entity in the database.
     *
     * @param entity The Car entity with updated information.
     */
    @Override
    public void updateEntity(Car entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateEntity", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Removes a Car entity from the database.
     *
     * @param entity The Car entity to be removed.
     */
    @Override
    public void removeEntity(Car entity) {
        carMapper = sqlSessionFactory.openSession().getMapper(ICarDAO.class);
        carMapper.removeEntity(entity);
    }
}
