package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;

public class InTransactionModel extends IATableModel<TransactionruleGateway> {

    private ArrayList<TransactionruleGateway> rules;
    
    public InTransactionModel(ArrayList<TransactionruleGateway> rules) {

        super(new String[]{"Omschrijving", "Punten"}, new Class[]{String.class, Integer.class}, new boolean[]{true, true, true}, false);
        this.rules = rules;
        retreiveData();
    }

    @Override
    public Object getColValue(TransactionruleGateway item, int col) {

        switch (col) {

            case 0:
                return item.getDescription();

            case 1:
                return item.getPoints();

            default:
                return null;
        }
    }

    @Override
    protected void setColValue(Object value, TransactionruleGateway item, int col) {

        switch (col) {

            case 0:
                item.setDescription("" + value);
                break;

            case 1:
                item.setPoints(Integer.parseInt("" + value));
                break;
        }
    }

    @Override
    protected List<TransactionruleGateway> getItems(Object filter) {
        
        return rules;
    }
}
