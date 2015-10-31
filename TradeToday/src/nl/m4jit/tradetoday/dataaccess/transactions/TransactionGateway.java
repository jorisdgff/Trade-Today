package nl.m4jit.tradetoday.dataaccess.transactions;

import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.users.*;

import java.util.*;
import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "Transaction")
@Table(name = "tblTransaction")
public class TransactionGateway extends AbstractGateway{
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number; //TODO zet op auto increment in bestaande databases ?? Welke ??
    private String type;
    @JoinColumn(name = "NumberMember")
    @ManyToOne
    private MemberGateway member;
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "NumberInsertAccount")
    @ManyToOne
    private UseraccountGateway insertAccount;
    // </editor-fold>

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MemberGateway getMember() {
        return member;
    }

    public void setMember(MemberGateway member) {
        this.member = member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UseraccountGateway getInsertAccount() {
        return insertAccount;
    }

    public void setInsertAccount(UseraccountGateway insertAccount) {
        this.insertAccount = insertAccount;
    }
}