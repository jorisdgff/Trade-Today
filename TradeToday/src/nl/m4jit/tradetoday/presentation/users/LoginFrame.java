package nl.m4jit.tradetoday.presentation.users;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import nl.m4jit.tradetoday.domainlogic.users.UseraccountModule;
import nl.m4jit.tradetoday.presentation.MainFrame;

public class LoginFrame extends RecordDialog<UseraccountGateway>{

    public LoginFrame(){

        super(new String[]{"75dlu"}, 2, true);

        addLabel("Gebruikersnaam:");
        addTextField("username", 1);

        addLabel("Wachtwoord:");
        JPasswordField passwordField = addPasswordField("password", 1);
        passwordField.addActionListener(this);
        passwordField.setActionCommand("password");

        setUI();
    }
    
    @Override
    public String getNotificationMessge(boolean isnew) {

         return null;
    }

    @Override
    public String getScreenTitle(boolean isnew) {

        return "Trade Today";
    }

    @Override
    protected void processUnknownActionCommand(String ac) {

        ok();
    }

    @Override
    public void cancel() {

        System.exit(0);
    }

    @Override
    protected UseraccountGateway create() {
        
        String username = getTextValue("username");
        String password = getTextValue("password");

        try{
            
            UseraccountModule.getInstance().logIn(username, password);
            frame.dispose();
            new MainFrame().createFrame(true);
            
        }catch(ValidationException ex){
            
            JOptionPane.showMessageDialog(this, "Ongeldige gegevens opgegeven");
        }
        
        return null;
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
