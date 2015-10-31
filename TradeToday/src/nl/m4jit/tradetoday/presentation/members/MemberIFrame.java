package nl.m4jit.tradetoday.presentation.members;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultStyledDocument;
import nl.m4jit.framework.ValidationException;

import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.tradetoday.dataaccess.custom.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.domainlogic.*;
import nl.m4jit.tradetoday.domainlogic.members.*;
import nl.m4jit.tradetoday.presentation.*;
import nl.m4jit.tradetoday.presentation.members.transactions.*;

public class MemberIFrame extends RecordDialog<MemberGateway> implements KeyListener {

    private JTextField balanceField;
    private JTextField placeField;
    private TransactionsPanel tp;
    
    private MainFrame mainHandler;
    private int number;

    public MemberIFrame(){
        
        this(null, null);
        number = MemberTable.getInstance().getMaxMemberNumber() + 1;
        setUp();
    }
    
    public MemberIFrame(MemberGateway member, MainFrame mainHandler) {

        super(new String[]{"40dlu", "40dlu", "40dlu", "40dlu"}, 17, member);
        this.mainHandler = mainHandler;
        
        if(member != null){
            
            setUp();
        }
    }

    private void setUp(){
     
        addLabel("Nummer + Saldo:");

        JTextField numberField = addTextField("number", 2);
        numberField.setEditable(false);
        numberField.setFont(new Font("Dialog", Font.BOLD, 16));

        balanceField = addTextField("balance", 2);
        balanceField.setEditable(false);
        balanceField.setFont(new Font("Dialog", Font.BOLD, 16));

        if (negativeBalance()) {

            balanceField.setForeground(Color.RED);
        }

        addLabel("Geslacht:");
        addRadioButton("sex", "M", "Man", 1);
        addRadioButton("sex", "F", "Vrouw", 3);

        addLabel("Vrlttrs. + Vrnm.:");
        addTextField("initials", 1);
        addTextField("firstname", 3);

        addLabel("Tssnvgsl. + Achtrnm.:");
        addTextField("prefix", 1);
        addTextField("lastname", 3);

        addLabel("Straat + Huisnummer:");
        addTextField("street", 3);
        addTextField("housenumber", 1);

        addLabel("Postcode + Plaats:");

        JTextField postalcodeField = addTextField("postalcode", 1);
        postalcodeField.addFocusListener(this);

        placeField = addACTextField("place", 3);
        placeField.addKeyListener(this);

        addLabel("Geboortedatum:");
        addFormattedTextField("birthdate", new SimpleDateFormat("d-M-yyyy"), 4);

        addLabel("Telefoon + Mobiel:");
        addTextField("telephonehome", 2);
        addTextField("telephonemobile", 2);

        addLabel("E-mail:");
        addTextField("email", 4);

        addLabel("<html>Inkomsten /<br>Participatieniveau");
        addComboBox("incometype", 2);
        addComboBox("participationlevel", 2);

        addLabel("<html>Gehoord via /<br>Doorverwezen door");
        addComboBox("heardof", 2);
        addComboBox("refferencedby", 2);

        addLabel("<html>ID getoond /<br>Inschrijven nieuwsbrief /<br>Akkoord<br>lidmaatschapsvoorwaarden:");
        //addLabel("Donatie betaald:");
        addCheckBox("idchecked", 1);
        addCheckBox("newsletter", 1);
        addCheckBox("agree", 2);

        addSeparator("");

        addLabel("Datum inschrijving:");
        addFormattedTextField("registrationdate", new SimpleDateFormat("d-M-yyyy"), 4);

        if (!isNew()) {

            addLabel("Datum uitschrijving:");
            addFormattedTextField("deregistrationdate", new SimpleDateFormat("d-M-yyyy"), 4);
        }

        addLabel("Verwerkt door:");

        JTextField processorField = addTextField("processor", 4);
        processorField.setEditable(false);

        addLabel("Extra:");
        addTextPane("extra", 4, 100);

        if (!isNew()) {

            tp = new TransactionsPanel(getRecord(), this);
            addPanel(tp);
            
            addPanel(new ServicesPanel(getRecord()));
            addPanel(new HelpSignalsPanel(getRecord()));
        }

        if (!isNew()) {

            addButton("Vorige", "prev", LEFT);
            addButton("Volgende", "next", LEFT);
        }

        setUI();
    }
    
    public void updateBalance() {

        balanceField.setText("" + getValue("balance"));
        tp.update();
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {

        if (ke.getKeyCode() == 17) {

            placeField.setDocument(new DefaultStyledDocument());
        }
    }
    
    @Override
    public String getScreenTitle(boolean isnew) {

        return isnew ? "Nieuw lid" : "Verander lid";
    }

    @Override
    public String getNotificationMessge(boolean isnew) {

        return isnew ? "Lid toegevoegd" : "Lid aangepast";
    }

    @Override
    public Object getValue(String name) {

        if (record == null) {

            if (name.equals("number")) {

                return number;
            } else if (name.equals("balance")) {

                return MemberModule.getInstance().getStartpoints();
            } else if (name.equals("incometype")) {

                return IncomeTypeTable.getInstance().retreive(10); //TODO Geen antwoord in bestaande databases aanpassen ?? Welke ??
            } else if (name.equals("participationlevel")) {

                return ParticipationLevelTable.getInstance().retreive(1);
            } else if (name.equals("heardof")) {

                return HeardOfTypeTable.getInstance().retreive(1);
            } else if (name.equals("registrationdate")) {

                return Application.getInstance().getDefaultDate();
            } else {

                return null;
            }
        } else {

            if (name.equals("number")) {

                return record.getNumber();
            } else if (name.equals("balance")) {

                return record.getBalance();
            } else if (name.equals("sex")) {

                return record.getSex();
            } else if (name.equals("initials")) {

                return record.getInitials();
            } else if (name.equals("firstname")) {

                return record.getFirstname();
            } else if (name.equals("prefix")) {

                return record.getPrefix();
            } else if (name.equals("lastname")) {

                return record.getLastname();
            } else if (name.equals("street")) {

                return record.getStreet();
            } else if (name.equals("housenumber")) {

                return record.getHousenumber();
            } else if (name.equals("postalcode")) {

                return record.getPostalcode();
            } else if (name.equals("place")) {

                return record.getPlace();
            } else if (name.equals("birthdate")) {

                return record.getBirthdate();
            } else if (name.equals("telephonehome")) {

                return record.getTelephoneHome();
            } else if (name.equals("telephonemobile")) {

                return record.getTelephoneMobile();
            } else if (name.equals("email")) {

                return record.getEmail();
            } else if (name.equals("incometype")) {

                return record.getIncomeType();
            } else if (name.equals("participationlevel")) {

                return record.getParticipationLevel();
            } else if (name.equals("heardof")) {

                return record.getHeardOfType();
            } else if (name.equals("refferencedby")) {

                return record.getRefferingInstance();
            } else if (name.equals("registrationdate")) {

                return record.getRegistrationdate();
            } else if (name.equals("deregistrationdate")) {

                return record.getDeregistrationdate();
            } else if (name.equals("processor")) {

                return record.getProcessor().getUsername();
            } else if (name.equals("extra")) {

                return record.getExtra();
            } else if (name.equals("idchecked")) {

                return record.getIdChecked();
            } else if (name.equals("newsletter")) {

                return record.getNewsletter();
            } else if (name.equals("agree")) {

                return record.getAgree();
            } else {

                return null;
            }
        }
    }

    @Override
    public List getCollection(String name) {

        if (name.equals("incometype")) {

            return IncomeTypeTable.getInstance().findAll();
        } else if (name.equals("participationlevel")) {

            return ParticipationLevelTable.getInstance().findAll();
        } else if (name.equals("heardof")) {

            return HeardOfTypeTable.getInstance().findAll();
        } else if (name.equals("refferencedby")) {

            return RefferingInstanceTable.getInstance().findAll();
        } else if (name.equals("place")) {

            return MemberTable.getInstance().findPlaces();
        } else {

            return null;
        }
    }

    public boolean negativeBalance() {

        if (record != null) {

            return record.getBalance() < 0;
        } else {

            return false;
        }

    }

    @Override
    public void focusLostEvent(String name) {

        if (name.equals("postalcode")) {

            String oldText = getTextValue(name);
            String newText = oldText.toUpperCase();
            setTextValue(name, newText);
        }
    }

    private void doAction(boolean isNew) {

        String initials = getTextValue("initials");
        String firstname = getTextValue("firstname");
        String prefix = getTextValue("prefix");
        String lastname = getTextValue("lastname");
        String sex = getButtonGroupValue("sex", "U");

        String street = getTextValue("street");
        String housenumber = getTextValue("housenumber");
        String postalcode = getTextValue("postalcode");
        String place = getTextValue("place");

        String telephoneHome = getTextValue("telephonehome");
        String telephoneMobile = getTextValue("telephonemobile");
        String email = getTextValue("email");

        Date birthdate = (Date) getFormattedTextFieldValue("birthdate");

        Date registrationdate = (Date) getFormattedTextFieldValue("registrationdate");
        Date deregistrationdate = null;
        
        if(!isNew){
            
            deregistrationdate = (Date) getFormattedTextFieldValue("deregistrationdate");
        }
        
        IncomeTypeGateway incomeType = (IncomeTypeGateway) getSelectedComboBoxItem("incometype");
        ParticipationLevelGateway participationLevel = (ParticipationLevelGateway) getSelectedComboBoxItem("participationlevel");
        HeardOfTypeGateway heardOfType = (HeardOfTypeGateway) getSelectedComboBoxItem("heardof");
        RefferingInstanceGateway refferingInstance = (RefferingInstanceGateway) getSelectedComboBoxItem("refferencedby");

        boolean idChecked = getCheckBoxValue("idchecked");
        boolean newsletter = getCheckBoxValue("newsletter");
        boolean agree = getCheckBoxValue("agree");

        String extra = getTextValue("extra");

        try {

            if(record == null){
                
                record = MemberModule.getInstance().create(number, initials, firstname, prefix, lastname, sex, street, housenumber, postalcode, place, telephoneHome, telephoneMobile, email, birthdate, registrationdate, incomeType, participationLevel, heardOfType, refferingInstance, idChecked, newsletter, agree, extra);
            }else{

                MemberModule.getInstance().update(record, initials, firstname, prefix, lastname, sex, street, housenumber, postalcode, place, telephoneHome, telephoneMobile, email, birthdate, registrationdate, deregistrationdate, incomeType, participationLevel, heardOfType, refferingInstance, idChecked, newsletter, agree, extra);
            }

            allowMemberAdding();
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no sex")) {

                giveMessageAndSetFocus("Geen geslacht opgegeven", "sex:F");
            } else if (message.equals("no initials")) {

                giveMessageAndSetFocus("Geen initialen opgegeven", "initials");
            } else if (message.equals("no lastname")) {

                giveMessageAndSetFocus("Geen achternaam opgegeven", "lastname");
            } else if (message.equals("no street")) {

                giveMessageAndSetFocus("Geen straat opgegeven", "street");
            } else if (message.equals("no housenumber")) {

                giveMessageAndSetFocus("Geen huisnummer opgegeven", "housenumber");
            } else if (message.equals("no postalcode")) {

                giveMessageAndSetFocus("Geen postcode opgegeven", "postalcode");
            } else if (message.equals("no place")) {

                giveMessageAndSetFocus("Geen plaats opgegeven", "place");
            } else if (message.equals("no birthdate")) {

                giveMessageAndSetFocus("Geen geboortedatum opgegeven", "birthdate");
            } else if (message.equals("future birthdate")) {

                giveMessageAndSetFocus("Geboortedatum in de toekomst", "birthdate");
            } else if (message.equals("future registrationdate")) {

                giveMessageAndSetFocus("Inschrijvingsdatum in de toekomst", "registrationdate");
            } else if (message.equals("refferinginstance mandatory")) {

                giveMessageAndSetFocus("Geef een verwijzende instantie op", "refferencedby");
            }
        }
    }

    @Override
    public void cancel() {
        
        super.cancel(); 
        allowMemberAdding();
    }

    @Override
    protected void closed() {
        
        super.closed(); 
        allowMemberAdding();
    }
    
    private void allowMemberAdding(){
        
        PropertyGateway paddingmember = PropertyTable.getInstance().retreive("addingmember");
        paddingmember.setValue(0);
        PropertyTable.getInstance().update(paddingmember);
    }
    
    @Override
    protected void processUnknownActionCommand(String ac) {

        cancel();

        int membernumber = record.getNumber();

        if (ac.equals("prev")) {

            membernumber--;
            showMember(membernumber);
        } else if (ac.equals("next")) {

            membernumber++;
            showMember(membernumber);
        }
    }

    private void showMember(int number) {

        MemberGateway member = MemberTable.getInstance().retreive(number);
        
        if(member !=  null){
            
            mainHandler.showMember(member);
        }else{
            
            JOptionPane.showMessageDialog(this, "Het gaat niet verder");
        }
    }

    public boolean isNew() {

        return record == null;
    }

    @Override
    protected MemberGateway create() {
        
        doAction(true);
        return null;
    }

    @Override
    protected void update() {
        
        doAction(false);
    }
}