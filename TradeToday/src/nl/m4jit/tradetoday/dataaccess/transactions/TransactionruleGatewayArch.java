package nl.m4jit.tradetoday.dataaccess.transactions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

import nl.m4jit.tradetoday.dataaccess.members.*;

@Entity(name = "TransactionRuleArch")
@Table(name = "tblTransactionRuleArch")
public class TransactionruleGatewayArch extends AbstractGateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @JoinColumn(name = "NumberTransaction")
    @ManyToOne
    private TransactionGatewayArch transaction;
    private String description;
    private int points;
    @JoinColumn(name = "NumberMember")
    @ManyToOne
    private MemberGateway member;
    private boolean corrected;
    @Transient
    private boolean memberIsSet;
    @Transient
    private int viewpoints;

    public int getNumber() {
        return number;
    }

    public TransactionGatewayArch getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionGatewayArch transaction) {
        this.transaction = transaction;
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

    public MemberGateway getMember() {
        return member;
    }

    public void setMember(MemberGateway member) {
        this.member = member;
    }

    public boolean isCorrected() {
        return corrected;
    }

    public void setCorrected(boolean corrected) {
        this.corrected = corrected;
    }

    public boolean isMemberIsSet() {
        return memberIsSet;
    }

    public void setMemberIsSet(boolean memberIsSet) {
        this.memberIsSet = memberIsSet;
    }

    public int getViewpoints() {
        return viewpoints;
    }

    public void setViewpoints(int viewpoints) {
        this.viewpoints = viewpoints;
    }
}