package nl.m4jit.tradetoday.domainlogic.members;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class RefferingInstancesModel extends IATableModel<RefferingInstanceGateway> {

    public RefferingInstancesModel() {

        super(new String[]{"Naam"}, new Class[]{String.class});
    }

    @Override
    public Object getColValue(RefferingInstanceGateway item, int col) {

        switch (col) {

            case 0:
                return item.getName();

            default:
                return null;
        }
    }

    @Override
    protected List<RefferingInstanceGateway> getItems(Object filter) {
        
        return RefferingInstanceTable.getInstance().findAll();
    }
}
