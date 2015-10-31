package nl.m4jit.tradetoday.presentation.members.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleGateway;
import nl.m4jit.tradetoday.domainlogic.transactions.TransactionModule;
import nl.m4jit.tradetoday.presentation.members.MemberIFrame;

public class CorrectionIFrame extends RecordDialog<TransactionruleGateway>{

    private TransactionruleGateway originalRule;
    private MemberIFrame memberIFrame;
    
    public CorrectionIFrame(TransactionsPanel transactionsHandler, TransactionruleGateway originalRule, MemberIFrame memberIFrame){

        super(new String[]{"100dlu"}, 2, transactionsHandler);
        
        this.originalRule = originalRule;
        this.memberIFrame = memberIFrame;

        addLabel("Datum");
        addFormattedTextField("date", new SimpleDateFormat("d-M-yyyy"), 1);

        addLabel("Reden");
        addTextField("description", 1);

        setUI();
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {

        return "Transactie corrigeren";
    }

    @Override
    public Object getValue(String name) {

        if (name.equals("date")) {

            return new Date();
        } else {

            return "";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        return "Transactie gecorrigeerd";
    }

    @Override
    protected TransactionruleGateway create() {
        
        if (!getTextValue("description").isEmpty()) {

            Date date = (Date) getFormattedTextFieldValue("date");
            String reason = getTextValue("description");
            
            TransactionModule.getInstance().correctTransaction(originalRule, date, reason);
        
            memberIFrame.updateBalance();
        } else {

            JOptionPane.showMessageDialog(this, "Geen reden opgegeven");
            throw new ValidationException("no reason");
        }
        
        return null;
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}