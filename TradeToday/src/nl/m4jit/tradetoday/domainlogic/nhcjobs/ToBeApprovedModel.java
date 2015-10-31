package nl.m4jit.tradetoday.domainlogic.nhcjobs;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;

public class ToBeApprovedModel extends IATableModel<NHCJobGateway> {

    public ToBeApprovedModel() {

        super(new String[]{"Lidnummer", "Omschrijving", "Uiterste reactiedatum"}, new Class[]{Integer.class, String.class, Date.class});
    }

    @Override
    public Object getColValue(NHCJobGateway item, int col) {

        switch (col) {

            case 0:
                return item.getMember().getNumber();

            case 1:
                return item.getDescription();
                
            case 2:
                return item.getReactionDateDeadline();
                
            default:
                return null;
        }
    }

    @Override
    protected List<NHCJobGateway> getItems(Object filter) {
        
        return NHCJobTable.getInstance().findToBeApproved();
    }
}
