package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.IncomeTypeGateway;
import nl.m4jit.tradetoday.domainlogic.members.IncomeTypesModel;

public class IncomeTypesIFrame extends TableDialog<IncomeTypeGateway>{

    public IncomeTypesIFrame(){

        super(new IncomeTypesModel());
        enableNew();
        enableChange();
        setUI();
    }
    
    @Override
    public void newRecord() {

        new IncomeTypeIFrame(this);
    }

    @Override
    protected void changeRecord(IncomeTypeGateway record) {

        new IncomeTypeIFrame(record, this);
    }

    @Override
    public String getScreenTitle() {
        
        return "Inkomenstypen beheren";
    }
}