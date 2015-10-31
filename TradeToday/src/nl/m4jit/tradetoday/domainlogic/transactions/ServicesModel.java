package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;

public class ServicesModel extends IATableModel<TransactionruleGateway>{
    
    private MemberGateway member;
    
    public ServicesModel(MemberGateway member){
        
        super(new String[]{"Datum", "Omschrijving", "Punten"}, new Class[]{Date.class, String.class, Integer.class}, new int[]{0, 250, 0}, false);
        this.member = member;
        retreiveData();
    }

    @Override
    public Object getColValue(TransactionruleGateway item, int col) {
        
        switch (col) {

            case 0:
                return item.getTransaction().getDate();

            case 1:
                return  item.getDescription();

            case 2:
                return 0 - item.getPoints();

            default:
                return null;
        }
    }

    @Override
    protected List<TransactionruleGateway> getItems(Object filter) {
        
        return TransactionruleTable.getInstance().findServicesByMember(member.getNumber());
    }
}