package nl.m4jit.tradetoday.dataaccess.loanservice;

import nl.m4jit.tradetoday.dataaccess.members.*;
import java.util.*;
import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.AbstractGateway;

@Entity(name = "Letting")
@Table(name = "tblLetting")
public class LettingGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @JoinColumn(name = "NumberMember")
    @ManyToOne
    private MemberGateway member;
    @Temporal(TemporalType.DATE)
    private Date loanDate;
    @Temporal(TemporalType.DATE)
    private Date submissionDate;
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private int points;
    private boolean checkedIn;

    public int getNumber() {
        return number;
    }

    public MemberGateway getMember() {
        return member;
    }

    public void setMember(MemberGateway member) {
        this.member = member;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}