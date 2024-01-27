package com.solvd.database.dao.mybatis;

import com.solvd.database.dao.IBusDAO;
import com.solvd.database.model.Bus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class BusDAOImpl implements IBusDAO {
    private static final Logger LOGGER = LogManager.getLogger(BusDAOImpl.class);
    private static SqlSession sqlSession;
    private static final SqlSessionFactory sqlSessionFactory;
    private static Reader reader = null;
    private static IBusDAO busMapper;

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
     * Retrieves a Bus entity from the database based on the provided ID.
     *
     * @param id The unique identifier of the Bus entity to retrieve.
     * @return The Bus entity with the specified ID, or null if not found.
     */
    @Override
    public Bus getEntityById(int id) {
        try{
            busMapper = sqlSessionFactory.openSession().getMapper(IBusDAO.class);
            return busMapper.getEntityById(id);
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error getting the bus by id "+e);
        }finally {
            sqlSession.close();
        }
        return null;
    }

    /**
     * Retrieves a list of all Bus entities from the database.
     *
     * @return A List containing all Bus entities in the database.
     */
    @Override
    public List<Bus> getEntities() {
        try{
            busMapper = sqlSessionFactory.openSession().getMapper(IBusDAO.class);
            return busMapper.getEntities();
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error getting all the buses "+e);
        }finally {
            sqlSession.close();
        }
        return null;
    }

    /**
     * Saves a new Bus entity to the database.
     *
     * @param entity The Bus entity to be saved.
     */
    @Override
    public void saveEntity(Bus entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertEntity", entity);
            sqlSession.commit();
        } catch (Exception e){
            sqlSession.rollback();
            LOGGER.error("Error saving the bus "+e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Updates an existing Bus entity in the database.
     *
     * @param entity The Bus entity with updated information.
     */
    @Override
    public void updateEntity(Bus entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateEntity", entity);
            sqlSession.commit();
        } catch (Exception e){
            sqlSession.rollback();
            LOGGER.error("Error updating the bus "+e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Removes a Bus entity from the database.
     *
     * @param entity The Bus entity to be removed.
     */
    @Override
    public void removeEntity(Bus entity) {
        try {
            busMapper = sqlSessionFactory.openSession().getMapper(IBusDAO.class);
            busMapper.removeEntity(entity);
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error removing the bus "+e);
        }
    }
}
