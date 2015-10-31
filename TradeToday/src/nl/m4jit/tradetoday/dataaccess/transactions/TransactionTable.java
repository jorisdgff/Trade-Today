package nl.m4jit.tradetoday.dataaccess.transactions;

import nl.m4jit.framework.sqlaccess.AbstractTable;

public class TransactionTable extends AbstractTable<TransactionGateway>{
 
    private final String SELECTSTRING = "SELECT t FROM Transaction t";
    private static TransactionTable instance;
    
    private TransactionTable(){}
    
    public TransactionGateway retreive(int number) {
        
        return (TransactionGateway)executeQuery(SELECTSTRING, "WHERE t.number = " + number).get(0);      
    }
    
    public static TransactionTable getInstance(){
        
        if(instance == null){
            
            instance = new TransactionTable();
        }
        
        return instance;
    }
}
