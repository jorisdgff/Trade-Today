package nl.m4jit.tradetoday.dataaccess.members;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

/**
 *
 * @author joris
 */
@Entity(name = "RefferingInstance")
@Table(name = "tblRefferingInstance")
public class RefferingInstanceGateway extends AbstractGateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private String name;
    private int ordering;
    
    public int getNumber() {
        return number;
    }

    public int getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }
    
    public String toString(){

        return getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
