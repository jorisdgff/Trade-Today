package nl.m4jit.tradetoday.domainlogic.statistics;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joris
 */
public class Query implements Comparable<Query>{

    private String name;
    private String text;
    private String query;
    private DefaultTableModel resultSetModel;

    public Query(String name, String text, String query){

        this.name = name;
        this.text = text;
        this.query = query;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    public DefaultTableModel getResultSetModel(){

        return resultSetModel;
    }

    public void setResultSetModel(DefaultTableModel val) {

        resultSetModel = val;
    }

    public int compareTo(Query q) {

        return q.name.compareTo(name);
    }
}