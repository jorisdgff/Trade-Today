package nl.m4jit.tradetoday.domainlogic.nhcjobs;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;

public class NHCJobTypesModel extends IATableModel<NHCJobTypeGateway> {

    public NHCJobTypesModel() {

        super(new String[]{"Naam"}, new Class[]{String.class});
    }

    @Override
    public Object getColValue(NHCJobTypeGateway item, int col) {

        switch (col) {

            case 0:
                return item.getName();

            default:
                return null;
        }
    }

    @Override
    protected List<NHCJobTypeGateway> getItems(Object filter) {
        
        return NHCJobTypeTable.getInstance().findAll();
    }
}
