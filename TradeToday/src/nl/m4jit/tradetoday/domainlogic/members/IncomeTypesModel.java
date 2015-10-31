package nl.m4jit.tradetoday.domainlogic.members;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.members.*;

/**
 *
 * @author joris
 */
public class IncomeTypesModel extends IATableModel<IncomeTypeGateway> {

    public IncomeTypesModel() {

        super(new String[]{"Naam"}, new Class[]{String.class});
    }

    @Override
    public Object getColValue(IncomeTypeGateway item, int col) {

        switch (col) {

            case 0:
                return item.getName();

            default:
                return null;
        }
    }

    @Override
    protected List<IncomeTypeGateway> getItems(Object filter) {
        
        return IncomeTypeTable.getInstance().findAll();
    }
}
