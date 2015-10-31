package nl.m4jit.tradetoday.dataaccess.members;

import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import java.util.*;
import javax.persistence.*;

import nl.m4jit.framework.sqlaccess.*;
import nl.m4jit.tradetoday.dataaccess.members.HeardOfTypeGateway;
import nl.m4jit.tradetoday.dataaccess.members.IncomeTypeGateway;
import nl.m4jit.tradetoday.dataaccess.members.ParticipationLevelGateway;
import nl.m4jit.tradetoday.dataaccess.members.RefferingInstanceGateway;

@Entity(name = "Member")
@Table(name = "tblMember")
public class MemberGateway extends AbstractGateway {


    private String initials;

    private String firstname;
    private String prefix;

    private String lastname;
    private String sex;

    private String street;
    
    private String housenumber;
    
    private String postalcode;
    
    private String place;
    
    private String telephoneHome;

    private String telephoneMobile;

    private String email;

    @Temporal(TemporalType.DATE)
    private Date birthdate;
    // </editor-fold>
    @Id
    private int number;
    private int balance;
    @Temporal(value = TemporalType.DATE)
    private Date registrationdate;
    @Temporal(value = TemporalType.DATE)
    private Date deregistrationdate;
    @JoinColumn(name = "NumberIncomeType")
    @ManyToOne
    private IncomeTypeGateway incomeType;
    @JoinColumn(name = "NumberParticipationLevel")
    @ManyToOne
    private ParticipationLevelGateway participationLevel;
    @JoinColumn(name = "NumberHeardOfType")
    @ManyToOne
    private HeardOfTypeGateway heardOfType;
    @JoinColumn(name = "NumberRefferingInstance")
    @ManyToOne
    private RefferingInstanceGateway refferingInstance;
    @Column(name = "IDChecked")
    private String idChecked;
    private String newsletter;
    private String agree;
    @JoinColumn(name = "NumberController")
    @ManyToOne
    private UseraccountGateway controller;
    @JoinColumn(name = "NumberProcessor")
    @ManyToOne
    private UseraccountGateway processor;
    private String extra;

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTelephoneHome() {
        return telephoneHome;
    }

    public void setTelephoneHome(String telephoneHome) {
        this.telephoneHome = telephoneHome;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    public Date getDeregistrationdate() {
        return deregistrationdate;
    }

    public void setDeregistrationdate(Date deregistrationdate) {
        this.deregistrationdate = deregistrationdate;
    }

    public IncomeTypeGateway getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeTypeGateway incomeType) {
        this.incomeType = incomeType;
    }

    public ParticipationLevelGateway getParticipationLevel() {
        return participationLevel;
    }

    public void setParticipationLevel(ParticipationLevelGateway participationLevel) {
        this.participationLevel = participationLevel;
    }

    public HeardOfTypeGateway getHeardOfType() {
        return heardOfType;
    }

    public void setHeardOfType(HeardOfTypeGateway heardOfType) {
        this.heardOfType = heardOfType;
    }

    public RefferingInstanceGateway getRefferingInstance() {
        return refferingInstance;
    }

    public void setRefferingInstance(RefferingInstanceGateway refferingInstance) {
        this.refferingInstance = refferingInstance;
    }

    public boolean getIdChecked() {
        
        return idChecked.equals("Y");
    }

    public void setIdChecked(boolean idChecked) {
        
        this.idChecked = idChecked ? "Y" : "N";
    }

    public boolean getNewsletter() {
        
        return newsletter.equals("Y");
    }

    public void setNewsletter(boolean newsletter) {
        
        this.newsletter = newsletter ? "Y" : "N";
    }

    public boolean getAgree() {
        
        return agree.equals("Y");
    }

    public void setAgree(boolean agree) {
        
        this.agree = agree ? "Y" : "N";
    }

    public UseraccountGateway getController() {
        return controller;
    }

    public void setController(UseraccountGateway controller) {
        this.controller = controller;
    }

    public UseraccountGateway getProcessor() {
        return processor;
    }

    public void setProcessor(UseraccountGateway processor) {
        this.processor = processor;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    

    @Override
    public String toString() {

        if (getFirstname() != null && !firstname.equals("")) {

            if (getPrefix() == null) {

                return getNumber() + " " + getFirstname() + " " + getLastname();
            } else {

                return getNumber() + " " + getFirstname() + " " + getPrefix() + " " + getLastname();
            }
        } else {

            if (getPrefix() == null) {

                return getNumber() + " " + getInitials() + " " + getLastname();
            } else {

                return getNumber() + " " + getInitials() + " " + getPrefix() + " " + getLastname();
            }
        }
    }

    
}