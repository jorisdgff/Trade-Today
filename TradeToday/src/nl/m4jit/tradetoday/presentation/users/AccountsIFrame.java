package nl.m4jit.tradetoday.presentation.users;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import nl.m4jit.tradetoday.domainlogic.users.AccountsModel;

public class AccountsIFrame extends TableDialog<UseraccountGateway> {

    public AccountsIFrame() {

        super(new AccountsModel());
        enableNew();
        enableChange();
        setUI();
    }

    @Override
    public String getScreenTitle() {

        return "Gebruikersoverzicht";
    }

    @Override
    protected void newRecord() {

        new AccountIFrame(this);
    }

    @Override
    protected void changeRecord(UseraccountGateway record) {

        new AccountIFrame(getSelectedRecord(), this);
    }
}
