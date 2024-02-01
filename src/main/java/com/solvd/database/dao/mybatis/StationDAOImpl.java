package com.solvd.database.dao.mybatis;

import com.solvd.database.dao.IStationDAO;
import com.solvd.database.model.Station;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class StationDAOImpl implements IStationDAO {
    private static final Logger LOGGER = LogManager.getLogger(StationDAOImpl.class);
    private static SqlSession sqlSession;
    private static final SqlSessionFactory sqlSessionFactory;
    private static Reader reader = null;
    private static IStationDAO stationMapper;

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
     * Retrieves a Station entity from the database based on the provided ID.
     *
     * @param id The unique identifier of the Station entity to retrieve.
     * @return The Station entity with the specified ID, or null if not found.
     */
    @Override
    public Station getEntityById(int id) {
        stationMapper = sqlSessionFactory.openSession().getMapper(IStationDAO.class);
        return stationMapper.getEntityById(id);
    }

    /**
     * Retrieves a list of all Station entities from the database.
     *
     * @return A List containing all Station entities in the database.
     */
    @Override
    public List<Station> getEntities() {
        List<Station> stations;
        try {
            sqlSession = sqlSessionFactory.openSession();
            stations = sqlSession.selectList("getAllStations");
        } finally {
            sqlSession.close();
        }
        return stations;
    }

    /**
     * Saves a new Station entity to the database.
     *
     * @param entity The Station entity to be saved.
     */
    @Override
    public void saveEntity(Station entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertStation", entity);
            sqlSession.commit();
        } catch (Exception e){
            sqlSession.rollback();
            LOGGER.error("Error saving the station " + e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Updates an existing Station entity in the database.
     *
     * @param entity The Station entity with updated information.
     */
    @Override
    public void updateEntity(Station entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("updateStation", entity);
            sqlSession.commit();
        } catch (Exception e){
            sqlSession.rollback();
            LOGGER.error("Error updating the station " + e);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Removes a Station entity from the database.
     *
     * @param entity The Station entity to be removed.
     */
    @Override
    public void removeEntity(Station entity) {
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("deleteStation", entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
