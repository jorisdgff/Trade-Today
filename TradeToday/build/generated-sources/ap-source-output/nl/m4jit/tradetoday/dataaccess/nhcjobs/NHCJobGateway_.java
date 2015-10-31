package nl.m4jit.tradetoday.dataaccess.nhcjobs;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobTypeGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(NHCJobGateway.class)
public class NHCJobGateway_ { 

    public static volatile SingularAttribute<NHCJobGateway, String> helpSignalsAction;
    public static volatile SingularAttribute<NHCJobGateway, Date> reactionDateDeadline;
    public static volatile SingularAttribute<NHCJobGateway, Date> reactionDate;
    public static volatile SingularAttribute<NHCJobGateway, Date> executionDate;
    public static volatile SingularAttribute<NHCJobGateway, String> description;
    public static volatile SingularAttribute<NHCJobGateway, Integer> jstate;
    public static volatile SingularAttribute<NHCJobGateway, String> agree;
    public static volatile SingularAttribute<NHCJobGateway, NHCJobTypeGateway> type;
    public static volatile SingularAttribute<NHCJobGateway, Date> creationDate;
    public static volatile SingularAttribute<NHCJobGateway, Integer> number;
    public static volatile SingularAttribute<NHCJobGateway, MemberGateway> member;
    public static volatile SingularAttribute<NHCJobGateway, Date> executionDateDeadline;
    public static volatile SingularAttribute<NHCJobGateway, String> rejectionReason;
    public static volatile SingularAttribute<NHCJobGateway, TransactionGateway> transaction;
    public static volatile SingularAttribute<NHCJobGateway, String> helpSignals;

}