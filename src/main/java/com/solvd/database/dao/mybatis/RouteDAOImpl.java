package com.solvd.database.dao.mybatis;

import com.solvd.database.dao.IRouteDAO;
import com.solvd.database.model.Route;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class RouteDAOImpl implements IRouteDAO {
    private static final Logger LOGGER = LogManager.getLogger(RouteDAOImpl.class);
    private static SqlSession sqlSession;
    private static final SqlSessionFactory sqlSessionFactory;
    private static Reader reader = null;
    private static IRouteDAO routeMapper;

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
     * Retrieves a Route entity from the database based on the provided ID.
     *
     * @param id The unique identifier of the Route entity to retrieve.
     * @return The Route entity with the specified ID, or null if not found.
     */
    @Override
    public Route getEntityById(int id) {
        routeMapper = sqlSessionFactory.openSession().getMapper(IRouteDAO.class);
        return routeMapper.getEntityById(id);
    }

    /**
     * Retrieves a list of all Route entities from the database.
     *
     * @return A List containing all Route entities in the database.
     */
    @Override
    public List<Route> getEntities() {
        List<Route> route;
        try {
            sqlSession = sqlSessionFactory.openSession();
            route = sqlSession.selectList("getAllRoutes");
        } finally {
            sqlSession.close();
        }
        return route;
    }

    /**
     * Saves a new Route entity to the database.
     *
     * @param entity The Route entity to be saved.
     */
    @Override
    public void saveEntity(Route entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertRoute", entity);
            sqlSession.commit();
        } catch (Exception e){
            sqlSession.rollback();
            LOGGER.error("Error saving the route " + e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Updates an existing Route entity in the database.
     *
     * @param entity The Route entity with updated information.
     */
    @Override
    public void updateEntity(Route entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateRoute", entity);
            sqlSession.commit();
        } catch (Exception e){
            sqlSession.rollback();
            LOGGER.error("Error updating the route " + e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Removes a Route entity from the database.
     *
     * @param entity The Route entity to be removed.
     */
    @Override
    public void removeEntity(Route entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("deleteRoute", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
