package nl.m4jit.tradetoday.presentation.transactions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.framework.presentation.swing.improvedcomponents.ITable;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberTable;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleGateway;
import nl.m4jit.tradetoday.domainlogic.Application;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;
import nl.m4jit.tradetoday.domainlogic.transactions.InTransactionModel;
import nl.m4jit.tradetoday.domainlogic.transactions.OutTransactionModel;
import nl.m4jit.tradetoday.domainlogic.transactions.TransactionModule;

public class TransactionIFrame extends RecordDialog<TransactionGateway> implements KeyListener{

    private boolean inTransaction;
    private MemberGateway member;
    private ArrayList<TransactionruleGateway> rules;
    
    public TransactionIFrame(boolean inTransaction){

        super(new String[]{"25dlu", "75dlu"}, 3);

        this.inTransaction = inTransaction;
        rules = TransactionModule.getInstance().createRules(inTransaction);
        
        addLabel("Lid:");
        addTextField("membernumber", 1);
        
        JTextField nameField = addTextField("membername", 1);
        nameField.setEditable(false);
        nameField.addFocusListener(this);

        addLabel("Datum:");
        addFormattedTextField("date", new SimpleDateFormat("d-M-yyyy"), 2);

        ITable table = addTable("rules");
        table.addKeyListener(this);
        
        setUI();
    }
    
    @Override
    public String getScreenTitle(boolean isnew) {

        if (inTransaction) {

            return "Inname invoeren";
        } else {

            return "Uitgifte invoeren";
        }
    }

    @Override
    public String getNotificationMessge(boolean isnew) {

        return "Transactie toegevoegd";
    }

    @Override
    public Object getValue(String name) {

        if (name.equals("date")) {

            return Application.getInstance().getDefaultDate();
        } else {

            return "";
        }
    }

    @Override
    public IATableModel getTableModel(String name) {

        if (name.equals("rules")) {

            if (inTransaction) {

                return new InTransactionModel(rules);
            } else {

                return new OutTransactionModel(rules);
            }
        } else {

            return null;
        }
    }

    @Override
    public void focusGainedEvent(String name) {

        if (name.equals("membername")) {

            int number = Integer.parseInt(getTextValue("membernumber"));
            MemberGateway tempMember = MemberTable.getInstance().retreive(number);

            if (tempMember == null) {

                setFocusOnComponent("membernumber");
                JOptionPane.showMessageDialog(this, "Lid niet gevonden");
            } else if (tempMember.getDeregistrationdate() == null) {

                member = tempMember;
                setTextValue("membername", MemberModule.getFullname(member));
                setFocusOnComponent("date");
            } else {

                setFocusOnComponent("membernumber");
                JOptionPane.showMessageDialog(this, "Lid is uitgeschreven");
            }
        }
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {

        if (ke.getKeyChar() == ke.VK_ENTER) {

            ok();
        }
    }

    @Override
    protected TransactionGateway create() {
        
        String type = inTransaction ? "I" : "O";
        Date date = (Date) getFormattedTextFieldValue("date");
        
        try {

            TransactionModule.getInstance().saveTransaction(type, member, date, rules);
            new TransactionIFrame(inTransaction);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no member")) {

                giveMessageAndSetFocus("Geen lid opgegeven", "membernumber");
            }
        }
        
        return null;
    }

    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
