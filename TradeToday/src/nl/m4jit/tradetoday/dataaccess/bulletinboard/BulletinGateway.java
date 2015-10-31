package nl.m4jit.tradetoday.dataaccess.bulletinboard;

import nl.m4jit.tradetoday.dataaccess.members.*;
import java.util.*;
import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "Bulletin")
@Table(name = "tblBulletin")
public class BulletinGateway extends AbstractGateway{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    @ManyToOne
    @JoinColumn(name = "NumberMember")
    private MemberGateway member;
    private String direction;
    private String type;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String isOpen; 

    public int getNumber() {
        return number;
    }

    public MemberGateway getMember() {
        return member;
    }

    public void setMember(MemberGateway member) {
        this.member = member;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
    
    
    
    
}
