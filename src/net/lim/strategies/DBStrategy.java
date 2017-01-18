package net.lim.strategies;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Limmy on 09.01.2017.
 */
public class DBStrategy implements Strategy, Closeable {


    private static DBStrategy instance;
    private static final Logger DBLogger = Logger.getLogger(DBStrategy.class.getName());
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/projectb_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "root";

    private DBStrategy() {
        //connect to a database, if it isn't success, send information to controller (for using a XLSStrategy)
        try {
            connection = DriverManager.getConnection(url, user, password);
            DBLogger.log(Level.INFO, "Connection is created");
        }
        catch (SQLException e) {
            DBLogger.log(Level.SEVERE, "Can't create a connection to the Database");
            e.printStackTrace();
        }

    }

    public static DBStrategy getInstance() throws SQLException {
        if (instance == null) instance = new DBStrategy();
        return instance;
    }

    public void create(String name) {
        try (Statement createStatement = connection.createStatement()){
            createStatement.execute("INSERT INTO users (userName, attempts) VALUES (\"" + name + "\" , 1)");
        } catch (SQLException e) {
            DBLogger.log(Level.WARNING, "Can't write a user row into the database");
            e.printStackTrace();
        }
    }

    public int read(String name) {
        try (Statement selectStatement = connection.createStatement()) {
            ResultSet set = selectStatement.executeQuery("SELECT attempts FROM users WHERE userName = \"" + name + "\";");
            if (set.next()) {
                return set.getInt(1);
            }
            else DBLogger.log(Level.INFO, "This user is not exist");
        }
        catch (SQLException e) {
            DBLogger.log(Level.WARNING, "Can't read a number of attempts. Return 0");
            e.printStackTrace();
        }
        return 0;
    }

    public void update(String name) {
        try (Statement updateStatement = connection.createStatement()) {
            updateStatement.execute("UPDATE users\n" +
                    "SET attempts = attempts + 1\n" +
                    "WHERE userName = \"" + name + "\";");
        }
        catch (SQLException e) {
            DBLogger.log(Level.WARNING, "Can't update information about user " + name);
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        }
        catch (SQLException e) {
            DBLogger.log(Level.SEVERE, "Can't close JDBC connection");
            e.printStackTrace();
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.close();
    }
}
