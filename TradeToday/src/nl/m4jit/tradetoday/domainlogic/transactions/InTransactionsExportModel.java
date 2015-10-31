package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;

public class InTransactionsExportModel extends IATableModel<TransactionruleGateway> {

    public InTransactionsExportModel(List<TransactionruleGateway> rules){
        
        super(new String[]{"Lid", "Omschrijving", "Datum"}, null, false);
        this.items = rules;
    }
    
    @Override
    public Object getColValue(TransactionruleGateway item, int col) {

        switch (col) {

            case 0:
                return item.getTransaction().getMember().getNumber();
                
            case 1:
                return item.getDescription();

            case 2:
                return item.getTransaction().getDate();

            default:
                return null;
        }
    }

    @Override
    protected List<TransactionruleGateway> getItems(Object filter) {
        
        return null;
    }
}