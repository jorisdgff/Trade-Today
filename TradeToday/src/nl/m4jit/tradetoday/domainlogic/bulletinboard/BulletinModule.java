package nl.m4jit.tradetoday.domainlogic.bulletinboard;

import nl.m4jit.tradetoday.dataaccess.bulletinboard.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import java.util.*;
import nl.m4jit.framework.ValidationException;

public class BulletinModule  {

    private final BulletinTable table;
    private static BulletinModule instance;
    
    private BulletinModule(){
    
        table = BulletinTable.getInstance();
    }
    
    public BulletinGateway create(MemberGateway member, String direction, String type, String description, Date date) {

        if (member == null) {

            throw new ValidationException("no member");
        } else if (direction.equals("U")) {

            throw new ValidationException("no direction");
        } else if (type.equals("U")) {

            throw new ValidationException("no type");
        } else if (description.equals("")) {

            throw new ValidationException("no description");
        } else {

            BulletinGateway bulletin = new BulletinGateway();
            bulletin.setMember(member);
            bulletin.setDirection(direction);
            bulletin.setType(type);
            bulletin.setDescription(description);
            bulletin.setDate(date);
            table.insert(bulletin);
            
            return bulletin;
        }
    }
    
    public void update(BulletinGateway bulletin, MemberGateway member, String direction, String type, String description, Date date) {

        if (member == null) {

            throw new ValidationException("no member");
        } else if (direction.equals("U")) {

            throw new ValidationException("no direction");
        } else if (type.equals("U")) {

            throw new ValidationException("no type");
        } else if (description.equals("")) {

            throw new ValidationException("no description");
        } else {

            bulletin.setMember(member);
            bulletin.setDirection(direction);
            bulletin.setType(type);
            bulletin.setDescription(description);
            bulletin.setDate(date);
            table.update(bulletin);
        }
    }
    
    public String getDirectionText(BulletinGateway record){
        
        String direction = record.getDirection();
        
        if(direction.equals("Q")){
            
            return "Vraag";
        }else if(direction.equals("D")){
            
            return "Aanbod";
        }else{
            
            return null;
        }
    }
    
    public String getTypeText(BulletinGateway record){
        
        String type = record.getType();
        
        if(type.equals("P")){
            
            return "Product";
        }else if(type.equals("S")){
            
            return "Dienst";
        }else{
            
            return null;
        }
    }
    
    public static BulletinModule getInstance(){
        
        if(instance == null){
            
            instance = new BulletinModule();
        }
        
        return instance;
    }
}
