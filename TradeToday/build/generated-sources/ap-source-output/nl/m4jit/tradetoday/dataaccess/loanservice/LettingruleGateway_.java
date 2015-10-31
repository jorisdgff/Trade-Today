package nl.m4jit.tradetoday.dataaccess.loanservice;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.loanservice.LettingGateway;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(LettingruleGateway.class)
public class LettingruleGateway_ { 

    public static volatile SingularAttribute<LettingruleGateway, Integer> number;
    public static volatile SingularAttribute<LettingruleGateway, LoanItemGateway> item;
    public static volatile SingularAttribute<LettingruleGateway, LettingGateway> letting;

}