package nl.m4jit.tradetoday.dataaccess.loanservice;

import java.util.List;
import nl.m4jit.framework.sqlaccess.*;

public class LoanItemTypeTable extends AbstractTable<LoanItemTypeGateway>{
    
    private final String SELECTSTRING = "SELECT lit FROM LoanItemType lit";
    private static LoanItemTypeTable instance;
    
    private LoanItemTypeTable(){}
    
    public LoanItemTypeGateway retreive(int number) {

        return (LoanItemTypeGateway) executeQuery(SELECTSTRING, "WHERE lit.number = " + number).get(0);
    }
    
    public List<LoanItemTypeGateway> findAll() {

        return executeQuery(SELECTSTRING, "");
    }
    
    public static LoanItemTypeTable getInstance(){
        
        if(instance == null){
            
            instance = new LoanItemTypeTable();
        }
        
        return instance;
    }
}