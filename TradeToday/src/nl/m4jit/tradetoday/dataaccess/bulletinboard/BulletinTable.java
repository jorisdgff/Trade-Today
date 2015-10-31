package nl.m4jit.tradetoday.dataaccess.bulletinboard;

import java.util.List;
import nl.m4jit.framework.sqlaccess.*;

public class BulletinTable extends AbstractTable<BulletinGateway>{
    
    private final String SELECTSTRING = "SELECT b FROM Bulletin b";
    private static BulletinTable instance;
    
    private BulletinTable(){}
    
    public List<BulletinGateway> findOpen() {

        return executeQuery(SELECTSTRING, "WHERE b.isOpen = 'Y'");
    }
    
    public static BulletinTable getInstance(){
        
        if(instance == null){
            
            instance = new BulletinTable();
        }
        
        return instance;
    }
}