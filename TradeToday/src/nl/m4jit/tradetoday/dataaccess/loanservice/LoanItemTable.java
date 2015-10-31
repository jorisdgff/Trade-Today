package nl.m4jit.tradetoday.dataaccess.loanservice;

import java.util.List;
import nl.m4jit.framework.sqlaccess.*;

public class LoanItemTable extends AbstractTable<LoanItemGateway>{
    
    private final String SELECTSTRING = "SELECT li FROM LoanItem li ";
    private static LoanItemTable instance;
    
    private LoanItemTable(){}
    
    public List<LoanItemGateway> findAll() {

        return executeQuery(SELECTSTRING, "");
    }
    
    public LoanItemGateway retreive(int number) {

        return (LoanItemGateway) executeQuery(SELECTSTRING, " WHERE li.number = " + number).get(0);
    }
    
    public int findMaxNumber() {

        return (Integer) executeQuery("SELECT max(li.number) LoanItem li", "").get(0);
    }
    
    public static LoanItemTable getInstance(){
        
        if(instance == null){
            
            instance = new LoanItemTable();
        }
        
        return instance;
    }
}