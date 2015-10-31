package nl.m4jit.tradetoday.dataaccess.transactions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(TransactionruleGateway.class)
public class TransactionruleGateway_ { 

    public static volatile SingularAttribute<TransactionruleGateway, Integer> number;
    public static volatile SingularAttribute<TransactionruleGateway, MemberGateway> member;
    public static volatile SingularAttribute<TransactionruleGateway, String> description;
    public static volatile SingularAttribute<TransactionruleGateway, TransactionGateway> transaction;
    public static volatile SingularAttribute<TransactionruleGateway, Integer> points;
    public static volatile SingularAttribute<TransactionruleGateway, Boolean> corrected;

}