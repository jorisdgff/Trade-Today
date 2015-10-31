package nl.m4jit.tradetoday.presentation.nhcjobs.types;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobTypeGateway;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobTypesModel;

public class NHCJobTypesIFrame extends TableDialog<NHCJobTypeGateway>{

    public NHCJobTypesIFrame(){

        super(new NHCJobTypesModel());
        enableNew();
        enableChange();
        setUI();
    }
    
    @Override
    public void newRecord() {

        new NHCJobTypeIFrame(this);
    }

    @Override
    protected void changeRecord(NHCJobTypeGateway record) {

        new NHCJobTypeIFrame(record, this);
    }

    @Override
    public String getScreenTitle() {
        
        return "Klustypen beheren";
    }
}