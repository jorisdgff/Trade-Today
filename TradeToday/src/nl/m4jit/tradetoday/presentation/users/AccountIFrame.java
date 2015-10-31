package nl.m4jit.tradetoday.presentation.users;

import java.util.Collection;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import nl.m4jit.tradetoday.domainlogic.Application;
import nl.m4jit.tradetoday.domainlogic.users.Right;
import nl.m4jit.tradetoday.domainlogic.users.UseraccountModule;

public class AccountIFrame extends RecordDialog<UseraccountGateway>{

    public AccountIFrame(AccountsIFrame dialog){
        
        this(null, dialog);
    }
    
    public AccountIFrame(UseraccountGateway record, AccountsIFrame dialog){

        super(new String[]{"75dlu"}, 4, record, dialog);

        addLabel("Gebruikersnaam:");
        addTextField("username", 1);

        addLabel("Rechten:");
        addComboBox("rights", 1);

        addLabel("Wachtwoord:");
        addPasswordField("password", 1);

        addLabel("Bevestiging:");
        addPasswordField("validation", 1);

        setUI();
    }
    
    @Override
    public Object getValue(String name) {

        boolean isNew = record == null;

        if (name.equals("rights")) {

            if (isNew) {

                return Application.getInstance().findRight('U');
            } else {

                return Application.getInstance().findRight(record.getRights());
            }
        } else if (isNew) {

            return "";
        } else if (name.equals("username")) {

            return record.getUsername();
        } else {

            return null;
        }
    }

    @Override
    public Collection getCollection(String name) {

        if (name.equals("rights")) {

            return Application.getInstance().getRights();
        } else {

            return null;
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Gebruiker toegevoegd";
        } else {

            return "Gebruiker aangepast";
        }
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Gebruiker toevoegen";
        } else {

            return "Gebruiker aanpassen";
        }
    }

    @Override
    protected UseraccountGateway create() {
        
        String username = getTextValue("username");
        Right rights = (Right) getSelectedComboBoxItem("rights");
        String password = getTextValue("password");
        String validation = getTextValue("validation");
        
        return UseraccountModule.getInstance().create(username, password, validation, rights.getCharacter());
    }

    @Override
    protected void update() {
        
        String username = getTextValue("username");
        Right rights = (Right) getSelectedComboBoxItem("rights");
        String password = getTextValue("password");
        String validation = getTextValue("validation");
        
        UseraccountModule.getInstance().update(record, username, password, validation, rights.getCharacter());
    }
}