package nl.m4jit.tradetoday.dataaccess.nhcjobs;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "NHCJobType")
@Table(name = "tblNHCJobType")
public class NHCJobTypeGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){

        return getName();
    }
}