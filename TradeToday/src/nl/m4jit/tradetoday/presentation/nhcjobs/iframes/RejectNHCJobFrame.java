package nl.m4jit.tradetoday.presentation.nhcjobs.iframes;

import java.util.Date;
import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobGateway;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobModule;

public class RejectNHCJobFrame extends AbstractNHCJobIFrame{
    
    public RejectNHCJobFrame(NHCJobGateway job, TableDialog tablehandler, TableDialog nextdialog){
        
        super(2, job, tablehandler, nextdialog);
        addRejectSection();
        setUI();
    }
    
    private void addRejectSection() {

        //Afkeurdatum
        addLabel("Afkeurdatum:");
        addDatePicker("reactiondate", 2, false);
        
        //Reden
        addLabel("Reden:");
        addTextPane("rejectionreason", 2, 100);
    }

    @Override
    public String getScreenTitle(boolean isNew) {
        
        return "Klus afkeuren";
    }

    @Override
    public String getNotificationMessge(boolean isnew) {
        
        return "Klus is afgekeurd";
    }

    @Override
    protected void save() {
        
        if (getTextValue("rejectionreason").equals("")) {

            giveMessageAndSetFocus("Geen een reden op", "rejectionreason");
        }

        Date rejectiondate = getDatepickerValue("reactiondate");
        String reason = getTextValue("rejectionreason");
        NHCJobModule.getInstance().reject(record, rejectiondate, reason);
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

            return new Date();
        } else if (name.equals("agree")) {

            return record.getAgree();
        } else {

            return null;
        }
    }
}