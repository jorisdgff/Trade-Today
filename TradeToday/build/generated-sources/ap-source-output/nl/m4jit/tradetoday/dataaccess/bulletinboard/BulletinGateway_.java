package nl.m4jit.tradetoday.dataaccess.bulletinboard;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-07T09:46:57")
@StaticMetamodel(BulletinGateway.class)
public class BulletinGateway_ { 

    public static volatile SingularAttribute<BulletinGateway, Date> date;
    public static volatile SingularAttribute<BulletinGateway, Integer> number;
    public static volatile SingularAttribute<BulletinGateway, String> isOpen;
    public static volatile SingularAttribute<BulletinGateway, MemberGateway> member;
    public static volatile SingularAttribute<BulletinGateway, String> description;
    public static volatile SingularAttribute<BulletinGateway, String> type;
    public static volatile SingularAttribute<BulletinGateway, String> direction;

}