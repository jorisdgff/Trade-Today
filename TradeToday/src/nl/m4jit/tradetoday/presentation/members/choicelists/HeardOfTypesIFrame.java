package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.HeardOfTypeGateway;
import nl.m4jit.tradetoday.domainlogic.members.HeardOfTypesModel;

public class HeardOfTypesIFrame extends TableDialog<HeardOfTypeGateway>{

    public HeardOfTypesIFrame(){

        super(new HeardOfTypesModel());
        enableNew();
        enableChange();
        setUI();
    }

    @Override
    public String getScreenTitle() {

        return "Gehoord via typen beheren";
    }
    
    @Override
    public void newRecord() {

        new HeardOfTypeIFrame(this);
    }

    @Override
    protected void changeRecord(HeardOfTypeGateway record) {

        new HeardOfTypeIFrame(record, this);
    }
}