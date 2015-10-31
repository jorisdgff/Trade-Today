package nl.m4jit.framework.sqlaccess;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

public class TableManager {

    private final String PERSISTENCE_UNIT_NAME = "CommonPU";
    private EntityManager em;
    private static TableManager instance;
    
    private TableManager(){
    
        try {

            Properties properties  = new Properties();
            properties.load(new FileInputStream("files/props.properties"));
            
            String host = properties.getProperty("host");
            String database = properties.getProperty("database");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");

            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + host + ":" + port + "/" + database + "?zeroDateTimeBehavior=convertToNull");
            properties.put("javax.persistence.jdbc.user", user);
            properties.put("javax.persistence.jdbc.password", password);
            
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
            em = factory.createEntityManager();
        } catch (IOException ex) {
            
            throw new Error(ex);
        }
    }
    
    public EntityManager getEntityManager(){
        
        return em;
    }
    
    public static TableManager getInstance(){
        
        if(instance == null){
            
            instance = new TableManager();
        }
        
        return instance;
    }
}
