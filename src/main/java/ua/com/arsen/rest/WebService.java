package ua.com.arsen.rest;

import ua.com.arsen.dao.DataAccessObject;

/**
 * Created by Thor on 12.07.2015.
 */
public abstract class WebService {

    protected DataAccessObject dao;

    public abstract void initDao();

}
