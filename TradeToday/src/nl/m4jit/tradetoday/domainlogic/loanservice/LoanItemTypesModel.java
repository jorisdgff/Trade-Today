package nl.m4jit.tradetoday.domainlogic.loanservice;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;

public class LoanItemTypesModel extends IATableModel<LoanItemTypeGateway> {

    public LoanItemTypesModel() {

        super(new String[]{"Naam", "Punten"}, new Class[]{String.class, Integer.class});
    }

    @Override
    public Object getColValue(LoanItemTypeGateway item, int col) {

        switch (col) {

            case 0:
                return item.getName();

            case 1:
                return item.getPoints();

            default:
                return null;
        }
    }

    @Override
    protected List<LoanItemTypeGateway> getItems(Object filter) {
        
        return LoanItemTypeTable.getInstance().findAll();
    }
}
