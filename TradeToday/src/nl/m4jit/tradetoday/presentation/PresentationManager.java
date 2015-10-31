package nl.m4jit.tradetoday.presentation;

import nl.m4jit.framework.Util;
import nl.m4jit.framework.presentation.swing.compositecomponents.SplashScreen;
import nl.m4jit.tradetoday.domainlogic.*;
import nl.m4jit.tradetoday.presentation.users.*;

public class PresentationManager {

    public PresentationManager(String[] args) {

        boolean dev = findArgument(args, "dev");
        String installDir = dev ? "" : Util.getInstalDir();
        SplashScreen splashScreen = new SplashScreen(installDir + "files/ssimg.jpg", "Trade Today", 60, "M4J - IT");

        Application.getInstance();
        boolean store = findArgument(args, "store");
        boolean intake = findArgument(args, "intake");
        
        splashScreen.setVisible(false);
        
        if (store) {

            new StoreFrame().createFrame(false);
        } else if(intake){
            
            new IntakeFrame().createFrame(true);
        }
        else{

            new LoginFrame().createFrame(false);
        }
    }

    protected final boolean findArgument(String[] args, String name) {

        for (String param : args) {

            if (param.equals(name)) {

                return true;
            }
        }

        return false;
    }
    
    public static void main(String[] args) {

        new PresentationManager(args);
    }
}