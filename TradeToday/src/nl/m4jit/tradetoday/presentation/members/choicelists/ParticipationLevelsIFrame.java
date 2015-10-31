package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.ParticipationLevelGateway;
import nl.m4jit.tradetoday.domainlogic.members.ParticipationLevelsModel;

public class ParticipationLevelsIFrame extends TableDialog<ParticipationLevelGateway>{

    public ParticipationLevelsIFrame(){

        super(new ParticipationLevelsModel());
        enableNew();
        enableChange();
        setUI();
    }
    
    @Override
    public String getScreenTitle() {

        return "Participatielevels";
    }

    @Override
    public void newRecord() {

        new ParticipationLevelIFrame(this);
    }

    @Override
    protected void changeRecord(ParticipationLevelGateway record) {

        new ParticipationLevelIFrame(record, this);
    }
}