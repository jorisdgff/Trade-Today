package nl.m4jit.tradetoday.dataaccess.members;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

@Entity(name = "IncomeType")
@Table(name = "tblIncomeType")
public class IncomeTypeGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int number;
    protected String name;

    public int getNumber() {

        return number;
    }

    public String getName() {
        
        return name;
    }
    
    public void setName(String val){
        
        name = val;
    }
    
    public String toString(){

        return name;
    }
}