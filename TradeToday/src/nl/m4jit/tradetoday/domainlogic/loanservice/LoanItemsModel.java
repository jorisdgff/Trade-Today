package nl.m4jit.tradetoday.domainlogic.loanservice;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;

public class LoanItemsModel extends IATableModel<LoanItemGateway> {

    public LoanItemsModel() {

        super(new String[]{"Nummer", "Type"}, new Class[]{Integer.class, String.class});
    }

    @Override
    public Object getColValue(LoanItemGateway item, int col) {

        switch (col) {

            case 0:
                return item.getNumber();

            case 1:
                return item.getType().getName();
                
            default:
                return null;
        }
    }

    @Override
    protected List<LoanItemGateway> getItems(Object filter) {
        
        return LoanItemTable.getInstance().findAll();
    }
}
