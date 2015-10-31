package nl.m4jit.tradetoday.dataaccess.loanservice;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "LoanItemType")
@Table(name = "tblToolType")
public class LoanItemTypeGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private String name;
    private String description;
    private int points;
    
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        
        return getName();
    }
}