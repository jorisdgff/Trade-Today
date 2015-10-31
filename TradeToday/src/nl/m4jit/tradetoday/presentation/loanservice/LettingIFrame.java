package nl.m4jit.tradetoday.presentation.loanservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LettingGateway;
import nl.m4jit.tradetoday.dataaccess.loanservice.LettingruleGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberTable;
import nl.m4jit.tradetoday.domainlogic.loanservice.LettingModel;
import nl.m4jit.tradetoday.domainlogic.loanservice.LettingModule;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;

public class LettingIFrame extends RecordDialog<LettingGateway>{

    private MemberGateway member;
    private ArrayList<LettingruleGateway> lettingRules;
    
    public LettingIFrame(){

        super(new String[]{"25dlu", "50dlu"}, 4);

        addLabel("Lid:");
        addTextField("membernumber", 1);
        
        JTextField nameField = addTextField("membername", 1);
        nameField.setEditable(false);
        nameField.addFocusListener(this);

        addLabel("Datum uit:");
        addFormattedTextField("loandate", new SimpleDateFormat("d-M-yyyy"), 2);

        addLabel("Datum in:");
        addFormattedTextField("submissiondate", new SimpleDateFormat("d-M-yyyy"), 2);

        addTable("rules");

        setUI();
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {

        return "Nieuwe uitlening";
    }

    @Override
    public Object getValue(String name) {

        if (name.equals("loandate")) {

            return new Date();
        } else {

            return null;
        }
    }

    @Override
    public IATableModel getTableModel(String name) {

        lettingRules = new ArrayList<LettingruleGateway>();

        for (int i = 0; i < 10; i++) {

            lettingRules.add(new LettingruleGateway());
        }

        return new LettingModel(lettingRules);
    }

    @Override
    public void focusGainedEvent(String name) {

        if (name.equals("membername")) {

            int number = Integer.parseInt(getTextValue("membernumber"));
            MemberGateway tempMember = MemberTable.getInstance().retreive(number);

            if (tempMember == null) {

                setFocusOnComponent("membernumber");
                JOptionPane.showMessageDialog(this, "Lid niet gevonden");
            } else if (tempMember.getDeregistrationdate() == null) {

                member = tempMember;
                setTextValue("membername", MemberModule.getFullname(member));
                setFocusOnComponent("loandate");
            } else {

                setFocusOnComponent("membernumber");
                JOptionPane.showMessageDialog(this, "Lid is uitgeschreven");
            }
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        return "Uitlening ingevoerd";
    }

    @Override
    protected LettingGateway create() {
        
        Date loanDate = (Date) getFormattedTextFieldValue("loandate");
        Date submissionDate = (Date) getFormattedTextFieldValue("submissiondate");

        try {

            LettingModule.getInstance().create(member, loanDate, submissionDate, lettingRules);
        } catch (ValidationException ex) {

            String message = ex.getMessage();
            
            if (message.equals("no member")) {

                giveMessageAndSetFocus("Geen lid opgegeven", "membernumber");
            } else if (message.equals("no submissiondate")) {

                giveMessageAndSetFocus("Geen datum in opgegeven", "submissiondate");
            }
        }
        
        return null;
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}