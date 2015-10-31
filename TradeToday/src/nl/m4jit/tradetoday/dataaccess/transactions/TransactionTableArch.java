package nl.m4jit.tradetoday.dataaccess.transactions;

import nl.m4jit.framework.sqlaccess.AbstractTable;

public class TransactionTableArch extends AbstractTable<TransactionGateway>{
 
    private final String SELECTSTRING = "SELECT t FROM TransactionArch t";
    private static TransactionTableArch instance;
    
    private TransactionTableArch(){}
    
    public TransactionGateway retreive(int number) {
        
        return (TransactionGateway)executeQuery(SELECTSTRING, "WHERE t.number = " + number).get(0);      
    }
    
    public static TransactionTableArch getInstance(){
        
        if(instance == null){
            
            instance = new TransactionTableArch();
        }
        
        return instance;
    }
}
