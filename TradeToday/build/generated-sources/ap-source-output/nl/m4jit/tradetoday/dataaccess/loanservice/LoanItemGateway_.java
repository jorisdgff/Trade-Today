package nl.m4jit.tradetoday.dataaccess.loanservice;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemTypeGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(LoanItemGateway.class)
public class LoanItemGateway_ { 

    public static volatile SingularAttribute<LoanItemGateway, Integer> number;
    public static volatile SingularAttribute<LoanItemGateway, String> description;
    public static volatile SingularAttribute<LoanItemGateway, LoanItemTypeGateway> type;

}