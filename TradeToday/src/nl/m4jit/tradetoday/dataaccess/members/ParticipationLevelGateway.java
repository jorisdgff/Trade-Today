package nl.m4jit.tradetoday.dataaccess.members;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

/**
 *
 * @author joris
 */
@Entity(name = "ParticipationLevel")
@Table(name = "tblParticipationLevel")
public class ParticipationLevelGateway extends AbstractGateway {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private int indexnumber;
    private String name;
    
    public int getNumber() {
        
        return number;
    }

    public int getIndexnumber() {
        
        return indexnumber;
    }
    
    public void setIndexnumber(int indexnumber) {
        this.indexnumber = indexnumber;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {

        return getIndexnumber()+ " - " + getName();
    }
}