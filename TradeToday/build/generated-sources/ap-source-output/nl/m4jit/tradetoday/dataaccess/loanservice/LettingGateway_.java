package nl.m4jit.tradetoday.dataaccess.loanservice;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(LettingGateway.class)
public class LettingGateway_ { 

    public static volatile SingularAttribute<LettingGateway, Integer> number;
    public static volatile SingularAttribute<LettingGateway, Date> returnDate;
    public static volatile SingularAttribute<LettingGateway, Boolean> checkedIn;
    public static volatile SingularAttribute<LettingGateway, MemberGateway> member;
    public static volatile SingularAttribute<LettingGateway, Date> loanDate;
    public static volatile SingularAttribute<LettingGateway, Date> submissionDate;
    public static volatile SingularAttribute<LettingGateway, Integer> points;

}