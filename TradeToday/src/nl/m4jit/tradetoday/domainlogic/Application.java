package nl.m4jit.tradetoday.domainlogic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import nl.m4jit.tradetoday.dataaccess.users.*;
import nl.m4jit.tradetoday.domainlogic.users.*;

public class Application  {

    private boolean dev;
    private JFrame mainFrame;
    private static Properties properties;
    private String installDir, puName;
    
    private Date defaultDate;
    private boolean store;
    private HashMap<Character, Right> rights;
    private UseraccountGateway account;
    private static Application instance;

    private Application() {

        try{
            
            properties = new Properties();
            properties.load(new FileInputStream("files/props.properties"));
        }catch(IOException ex){
            
        }
        
        rights = new HashMap<Character, Right>();
        rights.put('A', new Right('A', "Beheerder"));
        rights.put('U', new Right('U', "Gebruiker"));
        rights.put('R', new Right('R', "Onbekend"));

        defaultDate = new Date();
    }

    public JFrame getMainFrame() {

        return mainFrame;
    }

    public void manageException(String ex){

        JOptionPane.showMessageDialog (mainFrame, ex);
    }

    public void manageException(Exception ex){

        JOptionPane.showMessageDialog (mainFrame, ex.getMessage());
    }
    
    public Date getDefaultDate() {

        return defaultDate;
    }

    public void setDefaultDate(Date val) {

        defaultDate = val;
    }

    public Collection<Right> getRights() {

        return rights.values();
    }

    public Right findRight(char character) {

        return rights.get(character);
    }

    public void setAccount(UseraccountGateway val) {

        account = val;
    }

    public UseraccountGateway getAccount() {

        return account;
    }
    
    public Properties getProperties() {
        
        return properties;
    }
    
    public static Application getInstance(){
        
        if(instance == null){
            
            instance = new Application();
        }
        
        return instance;
    }
}