package nl.m4jit.tradetoday.domainlogic.loanservice;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;

public class LettingsModel extends IATableModel<LettingGateway> {

    public LettingsModel() {

        super(new String[]{"Lid", "Datum in"}, new Class[]{String.class, Date.class});
    }

    @Override
    public Object getColValue(LettingGateway item, int col) {

        switch (col) {

            case 0:
                return item.getMember();

            case 1:
                return item.getSubmissionDate();

            default:
                return null;
        }
    }

    @Override
    protected List<LettingGateway> getItems(Object filter) {
        
        return LettingTable.getInstance().findRunning();
    }
}
