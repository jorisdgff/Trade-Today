package nl.m4jit.tradetoday.presentation.users;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import nl.m4jit.tradetoday.domainlogic.Application;
import nl.m4jit.tradetoday.domainlogic.users.UseraccountModule;

public class ChangePasswordIFrame extends RecordDialog<UseraccountGateway>{

    public ChangePasswordIFrame(){

        super(new String[]{"75dlu"}, 3, Application.getInstance().getAccount());

        addLabel("Oud wachtwoord:");
        addPasswordField("oldpass", 1);

        addLabel("Nieuw wachtwoord:");
        addPasswordField("newpass", 1);

        addLabel("Bevestiging:");
        addPasswordField("validation", 1);

        setUI();
    }
    
    @Override
    public String getNotificationMessge(boolean isnew) {

        return "Wachtwoord aangepast";
    }

    @Override
    public String getScreenTitle(boolean isnew) {

        return "Wachtwoord aanpassen";
    }

    @Override
    protected UseraccountGateway create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void update() {
        
        String newPass = getTextValue("newpass");
        String oldPass = getTextValue("oldpass");
        String validation = getTextValue("validation");

        try {

            UseraccountModule.getInstance().changePassword(record, oldPass, newPass, validation);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("wrong password")) {

                giveMessageAndSetFocus("Fout wachtwoord", "oldpass");
            } else if (message.equals("no password")) {

                giveMessageAndSetFocus("Geen wachtwoord opgegeven", "newpass");
            } else if (message.equals("no validation")) {

                giveMessageAndSetFocus("Wachtwoorden komen niet overeen", "validation");
            }
        }
    }
}