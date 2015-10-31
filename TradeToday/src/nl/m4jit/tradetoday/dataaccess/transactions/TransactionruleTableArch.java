package nl.m4jit.tradetoday.dataaccess.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nl.m4jit.framework.sqlaccess.AbstractTable;

public class TransactionruleTableArch extends AbstractTable<TransactionruleGateway>{
 
    private final String SELECTSTRING = "SELECT tr FROM TransactionRuleArch tr";
    private static TransactionruleTableArch instance;

    private TransactionruleTableArch(){}
    
    public List<TransactionruleGatewayArch> findInsByTimePeriod(Date startdate, Date enddate){
        
        return executeQuery(SELECTSTRING, "WHERE tr.transaction.type = 'I' AND tr.description <> 'Startpunten' AND tr.transaction.date BETWEEN :startdate AND :enddate", new Object[]{"startdate", startdate}, new Object[]{"enddate", enddate});
    }
    
    public List<TransactionruleGatewayArch> findByTimePeriod(Date startdate, Date enddate){
        
        return executeQuery(SELECTSTRING, "WHERE tr.transaction.date BETWEEN :startdate AND :enddate", new Object[]{"startdate", startdate}, new Object[]{"enddate", enddate});
    }
    
    public TransactionruleGatewayArch retreive(int number) {

        return (TransactionruleGatewayArch) executeQuery(SELECTSTRING, "WHERE tr.number = " + number).get(0);
    }

    public List<TransactionruleGatewayArch> findByMemberAndMonth(int numbermember) {

        String sql = "SELECT tr.Number FROM tblTransactionRuleArch tr JOIN tblTransactionArch t ON tr.NumberTransaction = t.Number ";
        sql += " WHERE (tr.NumberMember = " + numbermember + " OR t.NumberMember = " + numbermember + ")";

        List<Integer> list = executeNativeQuery(sql);

        ArrayList<TransactionruleGatewayArch> transactionRules = new ArrayList<TransactionruleGatewayArch>();

        for (int number : list) {

            TransactionruleGatewayArch rule = retreive(number);
            transactionRules.add(rule);
        }

        return transactionRules;
    }
    
    public List<TransactionruleGatewayArch> findByMemberAndMonth(int numbermember, int month, int year) {

        String sql = "SELECT tr.Number FROM tblTransactionRuleArch tr JOIN tblTransactionArch t ON tr.NumberTransaction = t.Number ";
        sql += " WHERE (tr.NumberMember = " + numbermember + " OR t.NumberMember = " + numbermember + ") AND month(t.Date) = "+ month +" AND year(t.Date) = " + year;

        List<Integer> list = executeNativeQuery(sql);

        ArrayList<TransactionruleGatewayArch> transactionRules = new ArrayList<TransactionruleGatewayArch>();

        for (int number : list) {

            TransactionruleGatewayArch rule = retreive(number);
            transactionRules.add(rule);
        }

        return transactionRules;
    }

    public List<TransactionruleGatewayArch> findServicesByMember(int numbermember) {

        return executeQuery(SELECTSTRING, "WHERE tr.transaction.member.number = " + numbermember + " AND tr.transaction.type in('S', 'L', 'J')");
    }
    
    public static TransactionruleTableArch getInstance(){
        
        if(instance == null){
            
            instance = new TransactionruleTableArch();
        }
        
        return instance;
    }
}