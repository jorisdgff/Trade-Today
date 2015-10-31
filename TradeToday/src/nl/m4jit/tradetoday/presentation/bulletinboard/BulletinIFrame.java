package nl.m4jit.tradetoday.presentation.bulletinboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.bulletinboard.BulletinGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberTable;
import nl.m4jit.tradetoday.domainlogic.Application;
import nl.m4jit.tradetoday.domainlogic.bulletinboard.BulletinModule;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;

public class BulletinIFrame extends RecordDialog<BulletinGateway> {

    private MemberGateway member;

    public BulletinIFrame() {

        this(null);
    }

    public BulletinIFrame(BulletinGateway record) {

        super(new String[]{"40dlu", "60dlu"}, 5, record);

        addLabel("Lid:");
        addTextField("membernumber", 1);
        JTextField fromNameField = addTextField("membername", 1);
        fromNameField.setEditable(false);
        fromNameField.addFocusListener(this);

        addLabel("Richting:");
        addRadioButton("direction", "Q", "Vraag", 1);
        addRadioButton("direction", "D", "Aanbod", 1);

        addLabel("Type:");
        addRadioButton("type", "P", "Product", 1);
        addRadioButton("type", "S", "Dienst", 1);

        addLabel("Omschrijving:");
        addTextPane("description", 2, 100);

        addLabel("Aanmaakdatum:");
        addFormattedTextField("date", new SimpleDateFormat("d-M-yyyy"), 2);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Prikborditem aanmaken";
        } else {

            return "Prikborditem aanpassen";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Prikborditem aangemaakt";
        } else {

            return "Prikborditem aangepast";
        }
    }

    @Override
    public Object getValue(String name) {

        if (isNew) {

            if (name.equals("date")) {

                return Application.getInstance().getDefaultDate();
            } else {

                return "";
            }
        }else{
            
            if(name.equals("membernumber")){
                
                //return record.
            }else if(name.equals("membername")){
                
            }else if(name.equals("direction")){
                
            }else if(name.equals("type")){
                
            }else if(name.equals("description")){
                
            }else if(name.equals("date")){
                
            }else{
                
                return null;
            }return null;
        }

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
                setFocusOnComponent("description");
            } else {

                setFocusOnComponent("fromnumber");
                JOptionPane.showMessageDialog(this, "Lid is uitgeschreven");
            }
        }
    }

    @Override
    protected BulletinGateway create() {

        String direction = getButtonGroupValue("direction", "U");
        String type = getButtonGroupValue("type", "U");
        String description = getTextValue("description");
        Date date = (Date) getFormattedTextFieldValue("date");

        try {

            return BulletinModule.getInstance().create(member, direction, type, description, date);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no member")) {

                giveMessageAndSetFocus("Geen lid opgegeven", "membernumber");
            } else if (message.equals("no direction")) {

                giveMessageAndSetFocus("Geen richting opgegeven", "direction");
            } else if (message.equals("no type")) {

                giveMessageAndSetFocus("Geen type opgegeven", "type");
            } else if (message.equals("no description")) {

                giveMessageAndSetFocus("Geen omschrijving opgegeven", "description");
            }
            
            return null;
        }
    }

    @Override
    protected void update() {
        
        String direction = getButtonGroupValue("direction", "U");
        String type = getButtonGroupValue("type", "U");
        String description = getTextValue("description");
        Date date = (Date) getFormattedTextFieldValue("date");

        try {

            BulletinModule.getInstance().update(record, member, direction, type, description, date);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no member")) {

                giveMessageAndSetFocus("Geen lid opgegeven", "membernumber");
            } else if (message.equals("no direction")) {

                giveMessageAndSetFocus("Geen richting opgegeven", "direction");
            } else if (message.equals("no type")) {

                giveMessageAndSetFocus("Geen type opgegeven", "type");
            } else if (message.equals("no description")) {

                giveMessageAndSetFocus("Geen omschrijving opgegeven", "description");
            }
        }
    }
}
