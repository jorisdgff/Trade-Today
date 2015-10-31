package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGatewayArch;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleGatewayArch;

public class ArchiveModel extends IATableModel<TransactionruleGatewayArch> {

    private MemberGateway member;
    
    public ArchiveModel(MemberGateway member) {

        super(new String[]{"Datum", "Omschrijving", "Punten"}, new Class[]{Date.class, String.class, Integer.class}, false);
        this.member = member;
        retreiveData();
    }

    @Override
    public Object getColValue(TransactionruleGatewayArch item, int col) {

        switch (col) {

            case 0:

                TransactionGatewayArch transaction = item.getTransaction();
                
                if (transaction != null) {

                    return transaction.getDate();
                } else {

                    return null;
                }
                
            case 1:
                return item.getDescription();

            case 2:
                return item.getViewpoints();

            default:
                return null;
        }
    }

    @Override
    protected List getFilterItems() {
        
        return TransactionMonthArch.findByMember(member.getNumber());
    }

    @Override
    protected List<TransactionruleGatewayArch> getItems(Object filter) {
        
        TransactionMonthArch selectedMonth = (TransactionMonthArch) filter;
        return TransactionModule.getInstance().findTransactionRulesArchByMemberAndMonth(member.getNumber(), selectedMonth.getMonth(), selectedMonth.getYear());
    }
}
