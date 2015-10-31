package templates;

import nl.m4jit.framework.sqlaccess.*;

public class Table extends AbstractTable<AbstractGateway>{
    
    private final String SELECTSTRING = "";
    private static Table instance;
    
    private Table(){}
    
    public static Table getInstance(){
        
        if(instance == null){
            
            instance = new Table();
        }
        
        return instance;
    }
}