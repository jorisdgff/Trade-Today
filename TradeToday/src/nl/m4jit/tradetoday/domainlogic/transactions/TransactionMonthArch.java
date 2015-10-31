package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import nl.m4jit.framework.sqlaccess.TableManager;

public class TransactionMonthArch {

    private int month;
    private int year;
    private static final String[] names = new String[]{"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};

    public TransactionMonthArch(long month, long year){
        
        this.month = (int) month;
        this.year = (int) year;
    }
    
    public int getMonth() {

        return month;
    }

    public int getYear() {

        return year;
    }

    public String getShortNotation() {

        return month + "/" + year;
    }

    public String toString() {

        return names[month - 1] + " - " + year;
    }
    
    public static List<TransactionMonthArch> findByMember(int numbermember) {

        String sql = "SELECT DISTINCT month(Date) as Month, year(Date) as Year FROM  tblTransactionRuleArch  tr, tblTransactionArch t WHERE tr.NumberTransaction = t.Number";
        sql += " AND (t.NumberMember = " + numbermember + " OR tr.NumberMember = " + numbermember + ") order by Date DESC";

        ArrayList<TransactionMonthArch> tMonths = new ArrayList<TransactionMonthArch>();
        
        EntityManager em = TableManager.getInstance().getEntityManager();
        Query query = em.createNativeQuery(sql);
        
        for(Object[] array : (List<Object[]>) query.getResultList()){

            Object month = array[0];
            Object year = array[1];
            int imonth, iyear;
            
            if(month instanceof Integer){
             
                imonth = (Integer) month;
                iyear = (Integer) year;
            }else{
                
                long lmonth = (Long) month;
                long lyear = (Long) year;
                
                imonth = (int) lmonth;
                iyear = (int) lyear;
            }
            
            TransactionMonthArch tm = new TransactionMonthArch(imonth, iyear);
            tMonths.add(tm);
        }
        
        return tMonths;
    }
}
