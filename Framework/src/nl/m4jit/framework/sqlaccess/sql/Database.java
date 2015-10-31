package nl.m4jit.framework.sqlaccess.sql;

import java.sql.SQLException;

/**
 *
 * @author joris
 */
public interface Database {

    public String getDriver();

    public String getURL();

    public Exception createException(SQLException ex);
}
