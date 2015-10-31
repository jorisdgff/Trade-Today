package nl.m4jit.tradetoday.presentation.members.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleGateway;
import nl.m4jit.tradetoday.domainlogic.transactions.TransactionMonth;
import nl.m4jit.tradetoday.domainlogic.transactions.ViewTransactionsModel;
import nl.m4jit.tradetoday.presentation.members.MemberIFrame;

public class TransactionsPanel extends TableDialog<TransactionruleGateway> {

    private MemberGateway member;
    private MemberIFrame memberIFrame;

    public TransactionsPanel(MemberGateway member, MemberIFrame memberIFrame) {

        super(new ViewTransactionsModel(member, false, new int[]{0, 250, 0}), true);
        
        this.member = member;
        this.memberIFrame = memberIFrame;
        
        enablePrint();
        addButton("Correctie", "correction", RIGHT);
        addButton("Archief", "archive", RIGHT);
        addButton("Aanbieder", "provider", RIGHT);
        
        setUI();
    }
    
    @Override
    public String getScreenTitle() {

        return "Transacties";
    }
    
    @Override
    protected String getHeader() {

        TransactionMonth selectedMonth = (TransactionMonth) tableModel.getFilterstate();
        return member + ", " + selectedMonth.getShortNotation() + ", Saldo: " + member.getBalance();
    }

    @Override
    protected String getFooter() {

        SimpleDateFormat format = new SimpleDateFormat("d-M-yyyy");
        return "Afgedrukt op: " + format.format(new Date());
    }
    
    @Override
    protected void processUnknowActionCommand(String ac) {

        if (ac.equals("correction")) {

            TransactionruleGateway rule = getSelectedRecord();

            if (!rule.isCorrected()) {

                new CorrectionIFrame(this, getSelectedRecord(), memberIFrame);
            } else {

                JOptionPane.showMessageDialog(this, "Transactie is al gecorigeerd");
            }
        }else if(ac.equals("archive")){
            
            new ArchiveIFrame(member);
        }else if(ac.equals("provider")){
            
            TransactionruleGateway rule = getSelectedRecord();
            JOptionPane.showMessageDialog(this, rule.getMember().getNumber());
        }
    }
    
    public void update(){
        
        tableModel.updateData();
        table.updateUI();
    }
}
