package nl.m4jit.tradetoday.presentation.nhcjobs.iframes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFormattedTextField;
import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.custom.PropertyGateway;
import nl.m4jit.tradetoday.dataaccess.custom.PropertyTable;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobGateway;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobModule;

public class ApproveNHCJobIFrame extends AbstractNHCJobIFrame{

    public ApproveNHCJobIFrame(NHCJobGateway job, TableDialog tablehandler, TableDialog nextdialog){
        
        super(2, job, tablehandler, nextdialog);
        addApprovalDateSection(true);
        addExecutionateDeadlineSection();
        setUI();
    }
    
    private void addApprovalDateSection(boolean enabled) {

        //Goedkeurdatum
        addLabel("Goedkeurdatum:");
        addDatePicker("reactiondate", 2, enabled);
    }
    
    private void addExecutionateDeadlineSection() {

        //Uiterste uitvoerdatum
        addLabel("Uiterste uitvoerdatum:");
        JFormattedTextField executiondatedeadlinefield = addFormattedTextField("executiondatedeadline", new SimpleDateFormat("d-M-yyyy"), 2);
        executiondatedeadlinefield.setEditable(false);
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
        } else if (name.equals("executiondatedeadline")) {

            PropertyGateway ptime = PropertyTable.getInstance().retreive("nhcjobexecutiontime");
            int time = Integer.parseInt(ptime.getValue());

            GregorianCalendar today = new GregorianCalendar();
            today.add(Calendar.DAY_OF_MONTH, time);
            return today.getTime();
        } else if (name.equals("agree")) {

            return record.getAgree();
        } else {

            return null;
        }
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {
        
        return "Klus goedkeuren";
    }

    @Override
    public String getNotificationMessge(boolean isnew) {
        
        return "Klus is goedgekeurd";
    }

    @Override
    protected void save() {
        
        Date approvaldate = getDatepickerValue("reactiondate");
        Date executiondatedeadline = (Date) getFormattedTextFieldValue("executiondatedeadline");
        NHCJobModule.getInstance().approve(record, approvaldate, executiondatedeadline);
    }
    
    @Override
    public void stateChangeEvent(Object source) {

         if (source == getDatepickerModel("reactiondate")) {
            
            PropertyGateway ptime = PropertyTable.getInstance().retreive("nhcjobexecutiontime");
            int time = Integer.parseInt(ptime.getValue());
            JFormattedTextField executiondatedeadlinefield = (JFormattedTextField) getComponent("executiondatedeadline");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(getDatepickerValue("reactiondate"));
            calendar.add(Calendar.DAY_OF_MONTH, time);
            executiondatedeadlinefield.setValue(calendar.getTime());
        }
    }
}