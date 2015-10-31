package nl.m4jit.framework.sqlaccess.sql.databases;

import com.mysql.jdbc.exceptions.jdbc4.*;
import java.sql.*;
import nl.m4jit.framework.sqlaccess.sql.*;
import nl.m4jit.framework.sqlaccess.sql.exceptions.*;

public class MySQLDB implements Database{

    private String host;
    private String port;
    private String database;

    public MySQLDB(String host, String port, String database){

        this.host = host;
        this.port = port;
        this.database = database;
    }

    public String getDriver() {

        return "com.mysql.jdbc.Driver";
    }

    public String getURL() {

        if(database.equals("")){

            return "jdbc:mysql://" + host + ":"+ port;
        }else{

            return "jdbc:mysql://" + host + ":"+ port +"/" + database;
        }
    }

    public Exception createException(SQLException ex) {

        if(ex instanceof CommunicationsException){

            return new ServiceNotFoundException(ex.getMessage());
        }else if(ex.getErrorCode() == 1045){

            return new AccessDeniedException(ex.getMessage());
        }else{

            return ex;
        }
    }
}
