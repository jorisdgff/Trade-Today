package nl.m4jit.tradetoday.dataaccess.members;

import java.util.*;
import nl.m4jit.framework.sqlaccess.*;

public class MemberTable extends AbstractTable<MemberGateway>{

    private final String SELECTSTRING = "SELECT m FROM Member m";
    private static MemberTable instance;
    
    private MemberTable(){}
    
    public MemberGateway retreive(int number) {

        try{
            
            return (MemberGateway) executeQuery(SELECTSTRING, "WHERE m.number = " + number).get(0);
        }catch(IndexOutOfBoundsException ex){
            
            return null;
        }
    }
    
    public List<MemberGateway> findAll() {

        return executeQuery(SELECTSTRING, "WHERE m.number > 1 ORDER BY m.number");
    }

    public List<MemberGateway> findByName(String name) {

        return executeQuery(SELECTSTRING, "WHERE m.lastname LIKE '" + name + "%' ORDER BY m.lastname");
    }

    public List<MemberGateway> findByBirthdate(Date birthdate) {

        return executeQuery(SELECTSTRING, "WHERE m.birthdate = :birthdate ORDER BY m.lastname", new Object[]{"birthdate", birthdate});
    }

    public List<String> findPlaces() {

        return executeNativeQuery("SELECT Place FROM tblMember GROUP BY Place ORDER BY count(*) DESC");
    }

    public int getMaxMemberNumber() {

        return (Integer) executeNativeQuery("SELECT max(Number) FROM tblMember").get(0);
    }
    
    public static MemberTable getInstance(){
        
        if(instance == null){
            
            instance = new MemberTable();
        }
        
        return instance;
    }
}
