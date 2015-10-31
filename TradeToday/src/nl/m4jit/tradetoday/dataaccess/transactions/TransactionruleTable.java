package nl.m4jit.tradetoday.dataaccess.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.m4jit.framework.sqlaccess.AbstractTable;

public class TransactionruleTable extends AbstractTable<TransactionruleGateway>{
 
    private final String SELECTSTRING = "SELECT tr FROM TransactionRule tr";
    private static TransactionruleTable instance;

    private TransactionruleTable(){}
    
    public List<TransactionruleGateway> findInsByTimePeriod(Date startdate, Date enddate){
        
        return executeQuery(SELECTSTRING, "WHERE tr.transaction.type = 'I' AND tr.description <> 'Startpunten' AND tr.transaction.date BETWEEN :startdate AND :enddate", new Object[]{"startdate", startdate}, new Object[]{"enddate", enddate});
    }
    
    public List<TransactionruleGateway> findByTimePeriod(Date startdate, Date enddate){
        
        return executeQuery(SELECTSTRING, "WHERE tr.transaction.date BETWEEN :startdate AND :enddate", new Object[]{"startdate", startdate}, new Object[]{"enddate", enddate});
    }
    
    public TransactionruleGateway findByNumber(int number) {

        return (TransactionruleGateway) executeQuery(SELECTSTRING, "WHERE tr.number = " + number).get(0);
    }

    public List<TransactionruleGateway> findByMember(int numbermember) {

        String sql = "SELECT tr.Number FROM tblTransactionRule tr JOIN tblTransaction t ON tr.NumberTransaction = t.Number ";
        sql += " WHERE (tr.NumberMember = " + numbermember + " OR t.NumberMember = " + numbermember + ")";

        List<Integer> list = executeNativeQuery(sql);

        ArrayList<TransactionruleGateway> transactionRules = new ArrayList<TransactionruleGateway>();

        for (int number : list) {

            transactionRules.add(findByNumber(number));
        }

        return transactionRules;
    }
    
    public List<TransactionruleGateway> findByMemberAndMonth(int numbermember, int month, int year, boolean intake) {

        String sql = "SELECT tr.Number FROM tblTransactionRule tr JOIN tblTransaction t ON tr.NumberTransaction = t.Number ";
        sql += " WHERE (tr.NumberMember = " + numbermember + " OR t.NumberMember = " + numbermember + ") AND month(t.Date) = "+ month +" AND year(t.Date) = " + year;

        if(intake){
            
            sql += " AND t.Type = 'I' ORDER BY t.Date DESC";
        }
        
        List<Integer> list = executeNativeQuery(sql);

        ArrayList<TransactionruleGateway> transactionRules = new ArrayList<TransactionruleGateway>();

        for (int number : list) {

            transactionRules.add(findByNumber(number));
        }

        return transactionRules;
    }

    public List<TransactionruleGateway> findServicesByMember(int numbermember) {

        return executeQuery(SELECTSTRING, "WHERE tr.transaction.member.number = " + numbermember + " AND tr.transaction.type in('S', 'L', 'J')");
    }
    
    public static TransactionruleTable getInstance(){
        
        if(instance == null){
            
            instance = new TransactionruleTable();
        }
        
        return instance;
    }
}