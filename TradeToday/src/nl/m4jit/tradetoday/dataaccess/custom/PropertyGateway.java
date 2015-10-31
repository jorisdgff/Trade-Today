package nl.m4jit.tradetoday.dataaccess.custom;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "Property")
@Table(name = "tblProperty")
public class PropertyGateway extends AbstractGateway{
    
    @Id
    protected String name;
    protected String value;
    
    public String getValue() {
    
        return value;
    }
    
    public void setValue(String value) {
        
        this.value = value;
    }
    
    public void setValue(int value) {
        
        this.value = value + "";
    }
}
