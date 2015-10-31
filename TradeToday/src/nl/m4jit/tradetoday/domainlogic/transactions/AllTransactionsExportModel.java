package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;

public class AllTransactionsExportModel extends IATableModel<TransactionruleGateway> {

    public AllTransactionsExportModel(List<TransactionruleGateway> rules){
        
        //t.Date AS Datum, t.Type as Type, t.NumberMember AS Transactielid, tr.NumberMember AS Regellid, tr.Description AS Omschrijving, Points as Punten
        
        super(new String[]{"Datum", "Type", "Transactielid", "Regellid", "Omschrijving", "Punten"}, null, false);
        this.items = rules;
    }
    
    @Override
    public Object getColValue(TransactionruleGateway item, int col) {

        System.out.println(item.getMember());
        
        switch (col) {

            case 0:
                return item.getTransaction().getDate();
                
            case 1:
                return item.getTransaction().getType();

            case 2:
                return item.getTransaction().getNumber();

            case 3:
                return item.getMember().getNumber();
                
            case 4:
                return item.getDescription();
                
            case 5:
                return item.getPoints();
                
            default:
                return null;
        }
    }

    @Override
    protected List<TransactionruleGateway> getItems(Object filter) {
        
        return null;
    }
}