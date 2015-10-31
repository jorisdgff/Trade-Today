package nl.m4jit.tradetoday.presentation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import nl.m4jit.framework.presentation.swing.abstractdialogs.ContentDialog;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.domainlogic.*;
import nl.m4jit.tradetoday.domainlogic.members.*;
import nl.m4jit.tradetoday.presentation.members.*;
import nl.m4jit.framework.presentation.swing.improvedcomponents.ITable;
import nl.m4jit.tradetoday.domainlogic.transactions.ViewTransactionsModel;

public class IntakeFrame extends ContentDialog implements UIMethods{

    private JTextField inputField;
    private JTextPane dataPane;
    private ITable table;
    
    public IntakeFrame(){
        
        super(true);

        Box centerBox = Box.createVerticalBox();

        Box inputBox = Box.createHorizontalBox();
        centerBox.add(inputBox);

        inputBox.add(new JLabel("Lidnummer:"));
        inputBox.add(Box.createHorizontalGlue());

        inputField = new JTextField();
        inputField.addActionListener(this);
        inputBox.add(inputField);

        centerBox.add(Box.createVerticalStrut(5));

        dataPane = new JTextPane();
        dataPane.setContentType("text/html");
        dataPane.setPreferredSize(new Dimension(300, 300));
        dataPane.setBorder(inputField.getBorder());
        centerBox.add(dataPane);
        
        
        
        table = new ITable(null);
        centerBox.add(table.getPane());

        addToCenter(centerBox);

        setUI();
    }

    public void setData(String data) {

        dataPane.setText(data);
    }

    @Override
    public String getScreenTitle() {

        return "Trade Today";
    }

    public void actionPerformed(ActionEvent ae) {

        String input = inputField.getText();
        inputField.setText("");
        
        if(!input.equals("")){
            
            MemberModule.findMember(input, this);
        }else{

            setData("");
        }
    }

    private void showData(MemberGateway member) {

        ViewTransactionsModel m = new ViewTransactionsModel(member, true, new int[]{0, 750, 0});
        table.setModel(m);

        String text = "<font size=5>Nummer: " + member.getNumber() + "<br>";
        text += "Naam: " + MemberModule.getFullname(member) + "<br>";

        Date birthdate = member.getBirthdate();

        if (birthdate != null) {

            text += "Geboortedatum: " + birthdate.getDate() + "-" + (birthdate.getMonth() + 1) + "-" + (birthdate.getYear() + 1900) + "<br>";
        } else {

            text += "Geboortedatum: Onbekend<br>";
        }

        //TODO aanzetten voor Tilburg
        if(false){
            
            text += "</><br><br>Extra: " + member.getExtra();
        }
        
        setData(text);
    }

    @Override
    public void showMember(MemberGateway member) {

        showData(member);
    }

    @Override
    public void showMemberResult(List<MemberGateway> members) {
        
        new MembersDialog(members, this);
    }

    @Override
    public void showMessage(String message) {
        
        setData(message);
    }
}