package nl.m4jit.tradetoday.dataaccess.custom;

import nl.m4jit.framework.sqlaccess.AbstractTable;

public class PropertyTable extends AbstractTable<PropertyGateway>{
    
    private final String SELECTSTRING = "SELECT p FROM Property p";
    private static PropertyTable instance;
    
    private PropertyTable(){}
    
    public PropertyGateway retreive(String name) {

        return (PropertyGateway) executeQuery(SELECTSTRING, "where p.name = '"+ name +"'").get(0);
    }
    
    public static PropertyTable getInstance(){
        
        if(instance == null){
            
            instance = new PropertyTable();
        }
        
        return instance;
    }
}
