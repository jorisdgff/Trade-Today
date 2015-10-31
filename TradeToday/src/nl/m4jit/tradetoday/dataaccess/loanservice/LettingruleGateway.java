package nl.m4jit.tradetoday.dataaccess.loanservice;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

@Entity(name = "Lettingrule")
@Table(name = "tblLettingRule")
public class LettingruleGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    
    @ManyToOne
    @JoinColumn(name = "NumberLetting")
    private LettingGateway letting;
    
    @ManyToOne
    @JoinColumn(name = "NumberTool")
    private LoanItemGateway item;

    public int getNumber() {
        return number;
    }

    public LettingGateway getLetting() {
        return letting;
    }

    public void setLetting(LettingGateway letting) {
        this.letting = letting;
    }

    public LoanItemGateway getItem() {
        return item;
    }

    public void setItem(LoanItemGateway item) {
        this.item = item;
    }
}