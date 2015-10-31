package nl.m4jit.tradetoday.dataaccess.users;

import javax.persistence.*;
import nl.m4jit.framework.sqlaccess.*;

@Entity(name = "Useraccount")
@Table(name = "tblAccount")
public class UseraccountGateway extends AbstractGateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private String username;
    private String password;
    private char rights;

    public int getNumber() {
        return number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getRights() {
        return rights;
    }

    public void setRights(char rights) {
        this.rights = rights;
    }
}