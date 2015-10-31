package templates;

public class Module {

    private final Table table;
    private static Module instance;
    
    private Module(){
    
        table = Table.getInstance();
    }
    
    public static Module getInstance(){
        
        if(instance == null){
            
            instance = new Module();
        }
        
        return instance;
    }
}
