package net.lim.strategies;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Limmy on 09.01.2017.
 */
public class DBStrategy implements Strategy, Closeable {

    private static DBStrategy instance;
    private static final Logger DBLogger = Logger.getLogger(DBStrategy.class.getName());
    //add a connection here

    private DBStrategy() {
        //connect to a database, if it isn't success, send information to controller (for using a XLSStrategy)
    }

    public static DBStrategy getInstance() {
        if (instance == null) instance = new DBStrategy();
        return instance;
    }

    public void create(String name) {
        //INSERT INTO
    }

    public int read(String name) {
        //SELECT attempts FROM USERS
        //WHERE userName = name
        return 0;
    }

    public void update(String name) {
        
    }

    @Override
    public void close() throws IOException {

    }
}
