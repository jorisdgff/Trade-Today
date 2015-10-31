package nl.m4jit.tradetoday.dataaccess.members;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.HeardOfTypeGateway;
import nl.m4jit.tradetoday.dataaccess.members.IncomeTypeGateway;
import nl.m4jit.tradetoday.dataaccess.members.ParticipationLevelGateway;
import nl.m4jit.tradetoday.dataaccess.members.RefferingInstanceGateway;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(MemberGateway.class)
public class MemberGateway_ { 

    public static volatile SingularAttribute<MemberGateway, String> newsletter;
    public static volatile SingularAttribute<MemberGateway, String> firstname;
    public static volatile SingularAttribute<MemberGateway, Date> birthdate;
    public static volatile SingularAttribute<MemberGateway, String> prefix;
    public static volatile SingularAttribute<MemberGateway, Date> deregistrationdate;
    public static volatile SingularAttribute<MemberGateway, Date> registrationdate;
    public static volatile SingularAttribute<MemberGateway, String> telephoneHome;
    public static volatile SingularAttribute<MemberGateway, Integer> number;
    public static volatile SingularAttribute<MemberGateway, Integer> balance;
    public static volatile SingularAttribute<MemberGateway, String> street;
    public static volatile SingularAttribute<MemberGateway, String> postalcode;
    public static volatile SingularAttribute<MemberGateway, String> extra;
    public static volatile SingularAttribute<MemberGateway, String> place;
    public static volatile SingularAttribute<MemberGateway, String> email;
    public static volatile SingularAttribute<MemberGateway, HeardOfTypeGateway> heardOfType;
    public static volatile SingularAttribute<MemberGateway, UseraccountGateway> controller;
    public static volatile SingularAttribute<MemberGateway, IncomeTypeGateway> incomeType;
    public static volatile SingularAttribute<MemberGateway, String> initials;
    public static volatile SingularAttribute<MemberGateway, String> telephoneMobile;
    public static volatile SingularAttribute<MemberGateway, String> sex;
    public static volatile SingularAttribute<MemberGateway, RefferingInstanceGateway> refferingInstance;
    public static volatile SingularAttribute<MemberGateway, String> agree;
    public static volatile SingularAttribute<MemberGateway, UseraccountGateway> processor;
    public static volatile SingularAttribute<MemberGateway, String> lastname;
    public static volatile SingularAttribute<MemberGateway, String> idChecked;
    public static volatile SingularAttribute<MemberGateway, String> housenumber;
    public static volatile SingularAttribute<MemberGateway, ParticipationLevelGateway> participationLevel;

}