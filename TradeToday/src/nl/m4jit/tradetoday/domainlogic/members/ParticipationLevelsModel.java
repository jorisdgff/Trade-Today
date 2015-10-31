package nl.m4jit.tradetoday.domainlogic.members;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class ParticipationLevelsModel extends IATableModel<ParticipationLevelGateway> {

    public ParticipationLevelsModel() {

        super(new String[]{"Nummer", "Naam"}, new Class[]{Integer.class, String.class});
    }

    @Override
    public Object getColValue(ParticipationLevelGateway item, int col) {

        switch (col) {

            case 0:
                return item.getIndexnumber();
            
            case 1:
                return item.getName();

            default:
                return null;
        }
    }

    @Override
    protected List<ParticipationLevelGateway> getItems(Object filter) {
        
        return ParticipationLevelTable.getInstance().findAll();
    }
}
