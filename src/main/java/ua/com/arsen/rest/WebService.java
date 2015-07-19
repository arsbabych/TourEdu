package ua.com.arsen.rest;

import org.springframework.stereotype.Service;
import ua.com.arsen.dao.DataAccessObject;

/**
 * Created by Thor on 12.07.2015.
 */
@Service
public abstract class WebService {

    protected static DataAccessObject dao;

    protected abstract void initDao();

}
