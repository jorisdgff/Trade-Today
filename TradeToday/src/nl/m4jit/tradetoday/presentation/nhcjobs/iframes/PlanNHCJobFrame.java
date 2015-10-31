package nl.m4jit.tradetoday.presentation.nhcjobs.iframes;

import java.util.Date;
import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobGateway;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobModule;

public class PlanNHCJobFrame extends AbstractNHCJobIFrame{
 
    public PlanNHCJobFrame(NHCJobGateway job, TableDialog tablehandler, TableDialog nextdialog){
        
        super(2, job, tablehandler, nextdialog);
        addApprovalDateSection(false);
        addExecutiondateSection(true);
        setUI();
    }

    private void addApprovalDateSection(boolean enabled) {

        //Goedkeurdatum
        addLabel("Goedkeurdatum:");
        addDatePicker("reactiondate", 2, enabled);
    }
    
    private void addExecutiondateSection(boolean enabled) {

        //Uitvoerdatum
        addLabel("Uitvoerdatum:");
        addDatePicker("executiondate", 2, enabled);
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {
        
        return "Klus inplannen";
    }

    @Override
    public String getNotificationMessge(boolean isnew) {
        
        return "Klus is ingepland";
    }

    @Override
    public Object getValue(String name) {
        
        if (name.equals("membernumber")) {

            return record.getMember().getNumber();
        } else if (name.equals("membername")) {

            return MemberModule.getFullname(record.getMember());
        } else if (name.equals("type")) {

            return record.getType();
        } else if (name.equals("description")) {

            return record.getDescription();
        } else if (name.equals("creationdate")) {

            return record.getCreationDate();
        } else if (name.equals("reactiondate")) {

            return record.getReactionDate();
        } else if (name.equals("agree")) {

            return record.getAgree();
        } else {

            return null;
        }
    }
    
    

    @Override
    protected void save() {
        
        Date executiondate = getDatepickerValue("executiondate");
        NHCJobModule.getInstance().plan(record, executiondate);
    }
}