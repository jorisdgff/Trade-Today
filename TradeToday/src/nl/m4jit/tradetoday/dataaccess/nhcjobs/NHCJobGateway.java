package nl.m4jit.tradetoday.dataaccess.nhcjobs;

import java.util.*;
import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;


@Entity(name = "NHCJob")
@Table(name = "tblNHCJob")
public class NHCJobGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private int jstate;
    @JoinColumn(name = "NumberMember")
    @ManyToOne
    private MemberGateway member;
    @JoinColumn(name = "NumberType")
    @ManyToOne
    private NHCJobTypeGateway type;
    private String description;
    private String agree;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date reactionDateDeadline;

    //Approved
    @Temporal(TemporalType.DATE)
    private Date reactionDate;
    @Temporal(TemporalType.DATE)
    private Date executionDateDeadline;

    //Planned
    @Temporal(TemporalType.DATE)
    private Date executionDate;

    //Settled
    @ManyToOne
    @JoinColumn(name = "NumberTransaction")
    private TransactionGateway transaction;

    //Rejected
    private String rejectionReason;
    
    private String helpSignals;
    private String helpSignalsAction;

    public int getNumber() {
        return number;
    }

    public int getJstate() {
        return jstate;
    }

    public void setState(int state) {
        this.jstate = state;
    }

    public MemberGateway getMember() {
        return member;
    }

    public void setMember(MemberGateway member) {
        this.member = member;
    }

    public NHCJobTypeGateway getType() {
        return type;
    }

    public void setType(NHCJobTypeGateway type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getAgree() {
        
        return agree.equals("Y");
    }

    public void setAgree(boolean agree) {
        
        this.agree = agree ? "Y" : "N";
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getReactionDateDeadline() {
        return reactionDateDeadline;
    }

    public void setReactionDateDeadline(Date reactionDateDeadline) {
        this.reactionDateDeadline = reactionDateDeadline;
    }

    public Date getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    public Date getExecutionDateDeadline() {
        return executionDateDeadline;
    }

    public void setExecutionDateDeadline(Date executionDateDeadline) {
        this.executionDateDeadline = executionDateDeadline;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public TransactionGateway getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionGateway transaction) {
        this.transaction = transaction;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getHelpSignals() {
        return helpSignals;
    }

    public void setHelpSignals(String helpSignals) {
        this.helpSignals = helpSignals;
    }

    public String getHelpSignalsAction() {
        return helpSignalsAction;
    }

    public void setHelpSignalsAction(String helpSignalsAction) {
        this.helpSignalsAction = helpSignalsAction;
    }
}