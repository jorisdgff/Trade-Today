package nl.m4jit.tradetoday.presentation.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberTable;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;
import nl.m4jit.tradetoday.domainlogic.Application;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;
import nl.m4jit.tradetoday.domainlogic.transactions.TransactionModule;

public class ServiceTransactionIFrame extends RecordDialog<TransactionGateway>{

    private MemberGateway fromMember, toMember;
    
    public ServiceTransactionIFrame(){

        super(new String[]{"25dlu", "75dlu"}, 5);

        addLabel("Afnemer:");
        addTextField("fromnumber", 1);

        JTextField fromNameField = addTextField("fromname", 1);
        fromNameField.setEditable(false);
        fromNameField.addFocusListener(this);

        addLabel("Datum:");
        addFormattedTextField("date", new SimpleDateFormat("d-M-yyyy"), 2);

        addLabel("Dienst:");
        addTextField("service", 2);

        addLabel("Aanbieder:");
        addTextField("tonumber", 1);
        
        JTextField toNameField = addTextField("toname", 1);
        toNameField.setEditable(false);
        toNameField.addFocusListener(this);

        addLabel("Punten:");
        addTextField("points", 1);

        setUI();
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {

        return "Dienstverling invoeren";
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        return "Dienstverling ingevoerd";
    }

    @Override
    public Object getValue(String name) {

        if (name.equals("date")) {

            return Application.getInstance().getDefaultDate();
        } else {

            return "";
        }
    }

    @Override
    public void focusGainedEvent(String name) {

        if (name.equals("fromname")) {

            int number = Integer.parseInt(getTextValue("fromnumber"));
            MemberGateway tempMember = MemberTable.getInstance().retreive(number);

            if (tempMember == null) {

                setFocusOnComponent("fromnumber");
                JOptionPane.showMessageDialog(this, "Lid niet gevonden");
            } else if (tempMember.getDeregistrationdate() == null) {

                fromMember = tempMember;
                setTextValue("fromname", MemberModule.getFullname(fromMember));
                setFocusOnComponent("date");
            } else {

                setFocusOnComponent("fromnumber");
                JOptionPane.showMessageDialog(this, "Lid is uitgeschreven");

            }

        } else if (name.equals("toname")) {

            int number = Integer.parseInt(getTextValue("tonumber"));
            MemberGateway tempMember = MemberTable.getInstance().retreive(number);

            if (tempMember == null) {

                setFocusOnComponent("tonumber");
                JOptionPane.showMessageDialog(this, "Lid niet gevonden");
            } else if (tempMember.getDeregistrationdate() == null) {

                toMember = tempMember;
                setTextValue("toname", MemberModule.getFullname(toMember));
                setFocusOnComponent("points");
            } else {

                setFocusOnComponent("tonumber");
                JOptionPane.showMessageDialog(this, "Lid is uitgeschreven");
            }

        }
    }

    @Override
    protected TransactionGateway create() {
        
        String service = "Dienst: " + getTextValue("service");
        Date date = (Date) getFormattedTextFieldValue("date");
        int points = Integer.parseInt(getTextValue("points"));

        try{
            
            return TransactionModule.getInstance().makeSimpleTransaction(fromMember, toMember, points, "S", service, date);
        }catch(ValidationException ex){

            String message = ex.getMessage();
            
            if(message.equals("no client")){

                giveMessageAndSetFocus("Geen afnemer opgegeven", "fromnumber");
            }else if(message.equals("no description")){

                giveMessageAndSetFocus("Geen omschrijving opgegeven", "service");
            }else if(message.equals("no provider")){

                giveMessageAndSetFocus("Geen aanbieder opgegeven", "tonumber");
            }
            
            return null;
        }
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}