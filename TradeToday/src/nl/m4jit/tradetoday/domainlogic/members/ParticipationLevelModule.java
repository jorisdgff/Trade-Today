package nl.m4jit.tradetoday.domainlogic.members;

import nl.m4jit.framework.Util;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class ParticipationLevelModule  {

    private final ParticipationLevelTable table;
    private static ParticipationLevelModule instance;
    
    private ParticipationLevelModule(){
    
        table = ParticipationLevelTable.getInstance();
    }

    public ParticipationLevelGateway create(String index, String name){
        
        if (index.isEmpty()) {

            throw new ValidationException("no index");
        } else if (!Util.isInt(index)) {

            throw new ValidationException("no number");
        } else if (name.isEmpty()) {

            throw new ValidationException("no name");
        } else {
            
            int indexnumber = Integer.parseInt(index);
            
            ParticipationLevelGateway participationLevel = new ParticipationLevelGateway();
            participationLevel.setIndexnumber(indexnumber);
            participationLevel.setName(name);
            table.insert(participationLevel);
            
            return participationLevel;
        }
    }
    
    public void update(ParticipationLevelGateway participationLevel, String index, String name){
        
        if (index.isEmpty()) {

            throw new ValidationException("no index");
        } else if (!Util.isInt(index)) {

            throw new ValidationException("no number");
        } else if (name.isEmpty()) {

            throw new ValidationException("no name");
        } else {
            
            int indexnumber = Integer.parseInt(index);
            
            participationLevel.setIndexnumber(indexnumber);
            participationLevel.setName(name);
            table.update(participationLevel);
        }
    }
    
    public static ParticipationLevelModule getInstance(){
        
        if(instance == null){
            
            instance = new ParticipationLevelModule();
        }
        
        return instance;
    }
}