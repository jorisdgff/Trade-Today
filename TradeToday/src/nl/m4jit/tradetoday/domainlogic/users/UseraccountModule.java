package nl.m4jit.tradetoday.domainlogic.users;

import nl.m4jit.tradetoday.dataaccess.users.UseraccountTable;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import nl.m4jit.framework.Util;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.tradetoday.domainlogic.*;

public class UseraccountModule {

    private final UseraccountTable table;
    private static UseraccountModule instance;

    private UseraccountModule() {

        table = UseraccountTable.getIntance();
    }

    public UseraccountGateway create(String username, String password, String pwvalidation, char rights) {

        if (username.isEmpty()) {

            throw new ValidationException("no username");
        } else if (password.isEmpty()) {

            throw new ValidationException("no password");
        } else if (!password.equals(pwvalidation)) {

            throw new ValidationException("no validation");
        } else {

            String md5password = Util.createMD5Hash(password);

            UseraccountGateway useraccount = new UseraccountGateway();
            useraccount.setUsername(username);
            useraccount.setPassword(md5password);
            useraccount.setRights(rights);
            table.insert(useraccount);

            return useraccount;
        }
    }

    public void update(UseraccountGateway useraccount, String username, String password, String pwvalidation, char rights) {

        if (username.isEmpty()) {

            throw new ValidationException("no username");
        }else if (!password.isEmpty() && !password.equals(pwvalidation)) {

            throw new ValidationException("no validation");
        } else {

            useraccount.setUsername(username);
            useraccount.setRights(rights);
            
            if(!password.isEmpty()){
                
                String md5password = Util.createMD5Hash(password);
                useraccount.setPassword(md5password);
            }
            
            table.update(useraccount);
        }
    }

    public void changePassword(UseraccountGateway useraccount, String oldpassword, String newpassword, String pwvalidation) {

        String md5oldpassword = Util.createMD5Hash(oldpassword);
        String md5newpassword = Util.createMD5Hash(newpassword);

        if (!useraccount.getPassword().equals(md5oldpassword)) {

            throw new ValidationException("wrong password");
        } else if (newpassword.isEmpty()) {

            throw new ValidationException("no password");
        } else if (!newpassword.equals(pwvalidation)) {

            throw new ValidationException("no validation");
        } else {

            useraccount.setPassword(md5newpassword);
            table.update(useraccount);
        }
    }

    public void logIn(String username, String password) {

        String md5password = Util.createMD5Hash(password);

        UseraccountGateway useraccount = table.findByUsernameAndPassword(username, md5password);

        if (useraccount == null) {

            throw new ValidationException("usernotfound");
        } else {

            Application.getInstance().setAccount(useraccount);
        }
    }

    public static UseraccountModule getInstance() {

        if (instance == null) {

            instance = new UseraccountModule();
        }

        return instance;
    }
}
