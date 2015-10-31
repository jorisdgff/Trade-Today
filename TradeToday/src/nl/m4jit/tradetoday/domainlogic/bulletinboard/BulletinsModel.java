package nl.m4jit.tradetoday.domainlogic.bulletinboard;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.bulletinboard.*;

public class BulletinsModel extends IATableModel<BulletinGateway> {

    public BulletinsModel() {

        super(new String[]{"Richting", "Type", "Omschrijving"}, new Class[]{String.class, String.class, String.class});
    }

    @Override
    public Object getColValue(BulletinGateway item, int col) {

        switch (col) {

            case 0:
                return BulletinModule.getInstance().getDirectionText(item);

            case 1:
                return BulletinModule.getInstance().getTypeText(item);

            case 2:
                return item.getDescription();

            default:
                return null;
        }
    }

    @Override
    protected List<BulletinGateway> getItems(Object filter) {

        return BulletinTable.getInstance().findOpen();
    }
}
