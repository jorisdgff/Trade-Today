package nl.m4jit.tradetoday.dataaccess.loanservice;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "LoanItem")
@Table(name = "tblTool")
public class LoanItemGateway extends AbstractGateway{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @JoinColumn(name = "numberType")
    @ManyToOne
    private LoanItemTypeGateway type;
    
    
    private String description;

    public int getNumber() {
        return number;
    }

    public LoanItemTypeGateway getType() {
        
        return type;
    }

    public void setType(LoanItemTypeGateway type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}