package nl.m4jit.tradetoday.domainlogic.statistics;

import java.util.HashMap;

/**
 *
 * @author joris
 */
public class QueryCategory {
 
    private String name;
    private String text;
    private HashMap<String, Query> queries;

    public QueryCategory(String name, String text) {
        
        this.name = name;
        this.text = text;
        queries = new HashMap<String, Query>();
    }

    public String getName() {

        return name;
    }

    public String getText() {

        return text;
    }

    public HashMap<String, Query> getQueries() {

        return queries;
    }
    
    public Query addQuery(String name, String text, String sql){
        
        Query query = new Query(name, text, sql);
        queries.put(name, query);
        return query;
    }
}