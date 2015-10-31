package nl.m4jit.tradetoday.domainlogic.users;

import nl.m4jit.tradetoday.dataaccess.users.UseraccountTable;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.domainlogic.*;

/**
 *
 * @author joris
 */
public class AccountsModel extends IATableModel<UseraccountGateway> {

    public AccountsModel() {

        super(new String[]{"Gebruikersnaam", "Rechten"}, new Class[]{String.class, String.class});
    }

    @Override
    public Object getColValue(UseraccountGateway item, int col) {

        switch (col) {

            case 0:
                return item.getUsername();

            case 1:
                return Application.getInstance().findRight(item.getRights()).getName();

            default:
                return null;
        }
    }

    @Override
    protected List<UseraccountGateway> getItems(Object filter) {
        
        return UseraccountTable.getIntance().findAll();
    }
}
