package nl.m4jit.tradetoday.dataaccess.transactions;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(TransactionGateway.class)
public class TransactionGateway_ { 

    public static volatile SingularAttribute<TransactionGateway, Date> date;
    public static volatile SingularAttribute<TransactionGateway, Integer> number;
    public static volatile SingularAttribute<TransactionGateway, UseraccountGateway> insertAccount;
    public static volatile SingularAttribute<TransactionGateway, MemberGateway> member;
    public static volatile SingularAttribute<TransactionGateway, String> type;

}