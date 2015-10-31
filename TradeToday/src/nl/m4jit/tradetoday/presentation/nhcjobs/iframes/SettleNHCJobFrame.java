package nl.m4jit.tradetoday.presentation.nhcjobs.iframes;

import java.util.Date;
import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.custom.PropertyGateway;
import nl.m4jit.tradetoday.dataaccess.custom.PropertyTable;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.members.MemberTable;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;
import nl.m4jit.tradetoday.domainlogic.members.MemberModule;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobModule;
import nl.m4jit.tradetoday.domainlogic.transactions.TransactionModule;


/**
 *
 * @author Joris
 */
public class SettleNHCJobFrame extends AbstractNHCJobIFrame{

    public SettleNHCJobFrame(NHCJobGateway job, TableDialog tablehandler, TableDialog nextdialog){
        
        super(5, job, tablehandler, nextdialog);
        addApprovalDateSection(false);
        addExecutiondateSection(false);
        addSettleSection();
        setUI();
    }
    
    private void addApprovalDateSection(boolean enabled) {

        //Goedkeurdatum
        addLabel("Goedkeurdatum:");
        addDatePicker("reactiondate", 2, enabled);
    }

    private void addExecutiondateSection(boolean enabled) {

        //Uitvoerdatum
        addLabel("Uitvoerdatum:");
        addDatePicker("executiondate", 2, enabled);
    }

    private void addSettleSection() {

        //Afrekendatum
        addLabel("Afrekendatum:");
        addDatePicker("settlementdate", 2, true);
        
        //Punten
        addLabel("Punten:");
        addTextField("points", 2);

        //Hulpsignalen
        addLabel("Hulpsignalen:");
        addTextPane("helpsignals", 2, 100);
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {
        
        return "Klus afrekenen";
    }

    @Override
    public String getNotificationMessge(boolean isnew) {
        
        return "Klus is afgerekend";
    }

    @Override
    protected void save() {
        
        try {

            Date settlementdate = getDatepickerValue("settlementdate");
            int points = Integer.parseInt(getTextValue("points"));
            String helpsignals = getTextValue("helpsignals");

            int nhcmembernumber = Integer.parseInt(PropertyTable.getInstance().retreive("nhcmember").getValue());
            MemberGateway nhcMember = MemberTable.getInstance().retreive(nhcmembernumber);
            MemberGateway member = record.getMember();
            TransactionGateway transaction = TransactionModule.getInstance().makeSimpleTransaction(member, nhcMember, points, "J", "Klus: " + record.getDescription(), settlementdate);
        
            NHCJobModule.getInstance().settle(record, helpsignals, transaction);
            
        } catch (NumberFormatException ex) {

            giveMessageAndSetFocus("Geen punten op", "points");
        }
    }

    @Override
    public Object getValue(String name) {
        
        if (name.equals("membernumber")) {

            return record.getMember().getNumber();
        } else if (name.equals("membername")) {

            return MemberModule.getFullname(record.getMember());
        } else if (name.equals("type")) {

            return record.getType();
        } else if (name.equals("description")) {

            return record.getDescription();
        } else if (name.equals("creationdate")) {

            return record.getCreationDate();
        } else if (name.equals("reactiondate")) {

            return record.getReactionDate();
        } else if (name.equals("executiondate")) {

            return record.getExecutionDate();
        } else if (name.equals("settlementdate")) {

            return new Date();
        } else if (name.equals("agree")) {

            return record.getAgree();
        } else {

            return null;
        }
    }
}