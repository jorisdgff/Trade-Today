package nl.m4jit.tradetoday.presentation.loanservice;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LettingGateway;
import nl.m4jit.tradetoday.domainlogic.loanservice.LettingsModel;

public class LettingsIFrame extends TableDialog<LettingGateway>{

    public LettingsIFrame(){

        super(new LettingsModel()); 
        enableChange("Inchekken");
        setUI();
    }
    
    @Override
    public String getScreenTitle() {

        return "Lopende uitleningen";
    }

    @Override
    protected void changeRecord(LettingGateway record) {

        new CheckInIFrame(record, this);
    }
}
