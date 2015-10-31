package nl.m4jit.tradetoday.dataaccess.members;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

@Entity(name = "HeardOfType")
@Table(name = "tblHeardOfType")
public class HeardOfTypeGateway extends AbstractGateway{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private String name;
    private String refferingInstanceNeeded;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRefferingInstanceNeeded() {
        
        return refferingInstanceNeeded.equals("Y");
    }

    public void setRefferingInstanceNeeded(boolean refferingInstanceNeeded) {
        
        this.refferingInstanceNeeded = refferingInstanceNeeded ? "Y" : "N";
    }
    
    public String toString(){

        return getName();
    }
}