package nl.m4jit.tradetoday.domainlogic.statistics;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import nl.m4jit.framework.io.poi.excel.ExcelWriter;
import nl.m4jit.framework.sqlaccess.sql.SQLConnection;
import nl.m4jit.tradetoday.domainlogic.Application;

public class StatisticsModule  {

    private static StatisticsModule instance;
    private ArrayList<QueryCategory> categories;
    private HashMap<String, Query> queries;
    private QueryCategory currentCategory;
    private SQLConnection connection;

    private ResultSet resultSet;
    private Query currentQuery;

    private TreeSet<Query> savedQueries;

    private ResultSetModel resultSetModel;

    public static StatisticsModule getInstance() {

        if (instance == null) {

            instance = new StatisticsModule();
        }

        return instance;
    }

    private StatisticsModule() {

        categories = new ArrayList<QueryCategory>();
        queries = new HashMap<String, Query>();
        savedQueries = new TreeSet<Query>();
        resultSetModel = new ResultSetModel();

        setQueries();
        setUpSQL();
    }

    private void setQueries() {

        addCategory("main", "Algemeen");
        addQuery("members", "Aantal leden", "SELECT count(Number) as Aantal FROM tblMember WHERE Deregistrationdate is null");

        addCategory("visitors", "Bezoekers");

        String subtablesql = "(SELECT DISTINCT Date, NumberMember FROM tblTransaction)";
        addQuery("vday", "Per dag", "SELECT Date AS Dag, count(*) AS Bezoekers FROM " + subtablesql + " t GROUP BY t.Date ORDER BY Date DESC");

        String subsubtablesql = "(SELECT DISTINCT week(Date) AS Week, year(Date) As Year, NumberMember FROM tblTransaction)";
        subtablesql = "(SELECT Week, Year, count(*) as Bezoekers from " + subsubtablesql + " t group by Week, Year)";

        String subsubsubtable2sql = "(SELECT DISTINCT Date, NumberMember FROM tblTransaction)";
        String subsubtable2sql = "(SELECT week(Date) AS Week, year(Date) AS Year FROM " + subsubsubtable2sql + " t GROUP BY NumberMember, week(Date), year(Date) HAVING count(*) > 1)";
        String subtable2sql = "(SELECT *, count(*) as Herhalend FROM " + subsubtable2sql + " t GROUP BY Week, Year)";

        addQuery("vweek", "Per week", "SELECT w1.Week, w1.Year as Jaar, Bezoekers, Herhalend from " + subtablesql + " w1 LEFT OUTER JOIN " + subtable2sql + " w2 ON w1.Week = w2.Week AND w1.Year = w2.Year order by w1.Year DESC, w2.Week DESC");

        subtablesql = "(SELECT DISTINCT month(Date) AS Month, year(Date) As Year, NumberMember FROM tblTransaction)";
        addQuery("vmonth", "Per maand", "SELECT Month AS Maand, Year AS Jaar, count(*) AS Bezoekers FROM " + subtablesql + " t GROUP BY Month, Year ORDER BY Year DESC, Month DESC");

        subtablesql = "(SELECT DISTINCT year(Date) As Year, NumberMember FROM tblTransaction)";
        addQuery("vyear", "Per jaar", "SELECT Year AS Jaar, count(*) AS Bezoekers FROM " + subtablesql + " t GROUP BY Year ORDER BY Year DESC");

        String samesql = ", count(*) AS Inschrijvingen FROM tblMember WHERE Number > 1 GROUP BY";

        addCategory("registrations", "Inschrijvingen");
        addQuery("rday", "Per dag", "SELECT Registrationdate AS Dag " + samesql + " Registrationdate ORDER BY Registrationdate DESC");
        addQuery("rweek", "Per week", "SELECT week(Registrationdate) as Week, year(Registrationdate) AS Jaar " + samesql + " week(Registrationdate), year(Registrationdate) ORDER BY Registrationdate DESC");
        addQuery("rmonth", "Per maand", "SELECT month(Registrationdate) AS Maand, year(Registrationdate) AS Jaar " + samesql + " month(Registrationdate), year(Registrationdate) ORDER BY Registrationdate DESC");
        addQuery("ryear", "Per jaar", "SELECT year(Registrationdate) AS Jaar " + samesql + " year(Registrationdate) ORDER BY Registrationdate DESC");
        
        samesql = "SELECT month(j.ExecutionDate) AS Maand, year(j.ExecutionDate) AS Jaar, jt.Name as Type, count(*) AS Aantal";
        samesql += " FROM tblNHCJob j JOIN tblNHCJobType jt ON j.NumberType = jt.Number";
        samesql += " GROUP BY month(j.ExecutionDate), year(j.ExecutionDate)";
        
        String samesql2;
        
        samesql2 = "SELECT year(j.ExecutionDate) AS Jaar, jt.Name as Type, count(*) AS Aantal";
        samesql2 += " FROM tblNHCJob j JOIN tblNHCJobType jt ON j.NumberType = jt.Number";
        samesql2 += " GROUP BY year(j.ExecutionDate)";
        
        addCategory("jobs", "Klussen");
        addQuery("jmonth", "Per maand", samesql);
        addQuery("jyear", "Per jaar", samesql2);
    }

    private void setUpSQL() {

        Properties properties = Application.getInstance().getProperties();

        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String database = properties.getProperty("database");

        try {

            connection = new SQLConnection(SQLConnection.DatabaseType.MYSQLDB, host, port, user, password, database);
        } catch (Exception ex) {

            //application.manageException(ex);
        }
    }

    private void addCategory(String name, String text) {

        currentCategory = new QueryCategory(name, text);
        categories.add(currentCategory);
    }

    private void addQuery(String name, String text, String sql) {

        Query query = currentCategory.addQuery(name, text, sql);
        queries.put(name, query);
    }

    public ArrayList<QueryCategory> getCategories() {
        return categories;
    }

    public void processQuery(String name) {

        try {

            Query query = queries.get(name);
            currentQuery = query;
            resultSet = connection.executeQuery(query.getQuery());
            resultSetModel.setResultSet(resultSet);
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    public void addQuery() {

        savedQueries.add(currentQuery);
        currentQuery.setResultSetModel(resultSetModel.copy());
    }

    public void export(File file) {

        String filename = file.toString() + ".xls";

        try {

            ExcelWriter writer = new ExcelWriter();

            for (Query savedQuery : savedQueries) {

                writer.addTableSheet(savedQuery.getResultSetModel(), savedQuery.getText());
            }

            writer.write(filename);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(filename));
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Bestand kon niet worden weggeschreven\n" + ex);
        }
    }

    public ResultSetModel getResultSetModel() {

        return resultSetModel;
    }
}
