package nl.m4jit.framework.sqlaccess.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import nl.m4jit.framework.sqlaccess.sql.databases.MySQLDB;

public class SQLConnection {

    public enum DatabaseType {

        MYSQLDB, ORACLEDB, SQLITEDB, ACCESSDB
    };
    
    private Connection connection;
    private Statement statement;

    /**
     * Voor connectie met een service based database
     * @param databaseType
     * @param host
     * @param port
     * @param username
     * @param password
     * @param database
     */
    public SQLConnection(DatabaseType databaseType, String host, String port, String username, String password, String databasename) throws Exception{

        Database database;

        if(databaseType == DatabaseType.MYSQLDB){

            database = new MySQLDB(host, port, databasename);
        }else{
            
            database = null;
        }

        try{

            Class.forName(database.getDriver());
            connection = DriverManager.getConnection(database.getURL(), username, password);
            statement = connection.createStatement();
        }/*catch(ClassNotFoundException ex){

            throw new DriverNotFoundException(ex.getMessage());
        }*/catch(SQLException ex){

            throw database.createException(ex);
        }
    }

    public void execute(String sql) throws SQLException{

        statement.execute(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException{

        return statement.executeQuery(sql);
    }
}
