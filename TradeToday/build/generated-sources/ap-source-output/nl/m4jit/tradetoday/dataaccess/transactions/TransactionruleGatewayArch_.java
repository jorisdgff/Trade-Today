package nl.m4jit.tradetoday.dataaccess.transactions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGatewayArch;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(TransactionruleGatewayArch.class)
public class TransactionruleGatewayArch_ { 

    public static volatile SingularAttribute<TransactionruleGatewayArch, Integer> number;
    public static volatile SingularAttribute<TransactionruleGatewayArch, MemberGateway> member;
    public static volatile SingularAttribute<TransactionruleGatewayArch, String> description;
    public static volatile SingularAttribute<TransactionruleGatewayArch, TransactionGatewayArch> transaction;
    public static volatile SingularAttribute<TransactionruleGatewayArch, Integer> points;
    public static volatile SingularAttribute<TransactionruleGatewayArch, Boolean> corrected;

}