package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.RefferingInstanceGateway;
import nl.m4jit.tradetoday.domainlogic.members.RefferingInstancesModel;

public class RefferingInstancesIFrame extends TableDialog<RefferingInstanceGateway>{

    public RefferingInstancesIFrame(){

        super(new RefferingInstancesModel());
        enableNew();
        enableChange();
        setUI();
    }
    
    @Override
    public String getScreenTitle() {

        return "Verwijzende instanties";
    }

    @Override
    public void newRecord() {

        new RefferingInstanceIFrame(this);
    }

    @Override
    protected void changeRecord(RefferingInstanceGateway record) {

        new RefferingInstanceIFrame(record, this);
    }
}