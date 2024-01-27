package com.solvd.database.dao.mybatis;

import com.solvd.database.dao.IConnectionDAO;
import com.solvd.database.model.Connection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ConnectionDAOImpl implements IConnectionDAO {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionDAOImpl.class);
    private static SqlSession sqlSession;
    private static final SqlSessionFactory sqlSessionFactory;
    private static Reader reader = null;
    private static IConnectionDAO connectionMapper;

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
     * Retrieves a Connection entity from the database based on the provided ID.
     *
     * @param id The unique identifier of the Connection entity to retrieve.
     * @return The Connection entity with the specified ID, or null if not found.
     */
    @Override
    public Connection getEntityById(int id) {
        try {
            connectionMapper = sqlSessionFactory.openSession().getMapper(IConnectionDAO.class);
            return connectionMapper.getEntityById(id);
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error getting the connection by id " + e);
        } finally {
            sqlSession.close();
        }
        return null;
    }

    /**
     * Retrieves a list of all Connection entities from the database.
     *
     * @return A List containing all Connection entities in the database.
     */
    @Override
    public List<Connection> getEntities() {
        try {
            connectionMapper = sqlSessionFactory.openSession().getMapper(IConnectionDAO.class);
            return connectionMapper.getEntities();
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error getting all the connections " + e);
        } finally {
            sqlSession.close();
        }
        return null;
    }

    /**
     * Saves a new Connection entity to the database.
     *
     * @param entity The Connection entity to be saved.
     */
    @Override
    public void saveEntity(Connection entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertEntity", entity);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error saving the connection " + e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Updates an existing Connection entity in the database.
     *
     * @param entity The Connection entity with updated information.
     */
    @Override
    public void updateEntity(Connection entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateEntity", entity);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error updating the connection " + e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Removes a Connection entity from the database.
     *
     * @param entity The Connection entity to be removed.
     */
    @Override
    public void removeEntity(Connection entity) {
        try {
            connectionMapper = sqlSessionFactory.openSession().getMapper(IConnectionDAO.class);
            connectionMapper.removeEntity(entity);
        } catch (Exception e) {
            sqlSession.rollback();
            LOGGER.error("Error removing the connection " + e);
        } finally {
            sqlSession.close();
        }
    }
}
