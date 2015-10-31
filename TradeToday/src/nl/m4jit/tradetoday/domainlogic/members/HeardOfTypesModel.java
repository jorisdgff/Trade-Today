package nl.m4jit.tradetoday.domainlogic.members;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class HeardOfTypesModel extends IATableModel<HeardOfTypeGateway> {

    public HeardOfTypesModel() {

        super(new String[]{"Naam"}, new Class[]{String.class});
    }

    @Override
    public Object getColValue(HeardOfTypeGateway item, int col) {

        switch (col) {

            case 0:
                return item.getName();

            default:
                return null;
        }
    }

    @Override
    protected List<HeardOfTypeGateway> getItems(Object filter) {
        
        return HeardOfTypeTable.getInstance().findAll();
    }
}
