package com.solvd.database.util.abstractFactory;

import com.solvd.database.dao.IBaseDao;
import com.solvd.enums.DAOType;

/**
 * An abstract factory class defining the contract for getting specific DAO factories.
 */
public abstract class AbstractFactory {

    /**
     * Retrieves the specific DAO factory based on the provided DAOType.
     *
     * @param factory The type of DAO factory to retrieve (CPU, GPU, or RAM).
     * @return An instance of IBaseDao based on the provided DAOType.
     */
    public abstract IBaseDao getFactory(DAOType factory);
}