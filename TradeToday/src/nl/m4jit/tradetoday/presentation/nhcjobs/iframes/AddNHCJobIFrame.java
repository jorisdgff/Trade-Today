package nl.m4jit.tradetoday.presentation.nhcjobs.iframes;

import nl.m4jit.tradetoday.dataaccess.custom.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;
import nl.m4jit.tradetoday.domainlogic.members.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobModule;

public class AddNHCJobIFrame extends AbstractNHCJobIFrame {

    private MemberGateway member;

    public AddNHCJobIFrame() {

        super(1, null, null, null);
        addReactiondateDeadlineSection();
        setUI();
    }

    private void addReactiondateDeadlineSection() {

        //Uiterste reactiedatum
        addLabel("Uiterste reactiedatum:");

        JFormattedTextField reactiondatedeadlinefield = addFormattedTextField("reactiondatedeadline", new SimpleDateFormat("d-M-yyyy"), 2);
        reactiondatedeadlinefield.setEditable(false);
    }

    @Override
    public Object getValue(String name) {

        if (name.equals("creationdate")) {

            return new Date();
        } else if (name.equals("reactiondatedeadline")) {

            PropertyGateway ptime = PropertyTable.getInstance().retreive("nhcjobapprovaltime");
            int time = Integer.parseInt(ptime.getValue());

            GregorianCalendar today = new GregorianCalendar();
            today.add(Calendar.DAY_OF_MONTH, time);
            return today.getTime();
        } else {

            return null;
        }
    }

    @Override
    public void focusGainedEvent(String name) {

        if (name.equals("membername")) {

            int number = Integer.parseInt(getTextValue("membernumber"));
            MemberGateway tempMember = MemberTable.getInstance().retreive(number);

            if (tempMember != null) {

                if (tempMember.getDeregistrationdate() == null) {

                    member = tempMember;
                    setTextValue("membername", MemberModule.getFullname(member));
                    setFocusOnComponent("description");
                } else {

                    setFocusOnComponent("membernumber");
                    JOptionPane.showMessageDialog(this, "Lid is uitgeschreven");
                }
            }else{
                
                setFocusOnComponent("membernumber");
                JOptionPane.showMessageDialog(this, "Lid niet gevonden");
            }
        }
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        return "Klus aanmaken";
    }

    @Override
    public String getNotificationMessge(boolean isnew) {

        return "Klus is aangemaakt";
    }

    @Override
    protected void save() {

        if (member == null) {

            giveMessageAndSetFocus("Geef lid op", "membernumber");
        } else if (getSelectedComboBoxItem("type") == null) {

            giveMessageAndSetFocus("Selecteer een type", null);
        } else if (getTextValue("description").equals("")) {
            giveMessageAndSetFocus("Geef een omschrijving", "description");
        } else {

            NHCJobTypeGateway type = (NHCJobTypeGateway) getSelectedComboBoxItem("type");
            String description = getTextValue("description");
            boolean agree = getCheckBoxValue("agree");
            Date creationdate = getDatepickerValue("creationdate");
            Date reactiondatedeadline = (Date) getFormattedTextFieldValue("reactiondatedeadline");

            NHCJobModule.getInstance().create(member, type, description, agree, creationdate, reactiondatedeadline);
            
            
            dispose();
        }
    }

    @Override
    public void stateChangeEvent(Object source) {

        if (source == getDatepickerModel("creationdate")) {

            PropertyGateway ptime = PropertyTable.getInstance().retreive("nhcjobapprovaltime");
            int time = Integer.parseInt(ptime.getValue());
            JFormattedTextField reactiondatedeadlinefield = (JFormattedTextField) getComponent("reactiondatedeadline");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(getDatepickerValue("creationdate"));
            calendar.add(Calendar.DAY_OF_MONTH, time);
            reactiondatedeadlinefield.setValue(calendar.getTime());
        }
    }
}
