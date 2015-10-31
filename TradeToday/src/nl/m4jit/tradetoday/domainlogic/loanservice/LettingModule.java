package nl.m4jit.tradetoday.domainlogic.loanservice;

import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;
import nl.m4jit.tradetoday.domainlogic.transactions.*;

import java.util.*;
import nl.m4jit.framework.*;

public class LettingModule {

    private final LettingTable table;
    private final LettingruleTable lettingruleTable;
    private static LettingModule instance;
    
    private LettingModule(){
    
        table = LettingTable.getInstance();
        lettingruleTable = LettingruleTable.getInstance();
    }
    
    public int calculatePoints(LettingGateway letting, int days) {

        int pointsperday = 0;

        for (LettingruleGateway rule : LettingruleTable.getInstance().findByLetting(letting.getNumber())) {  

            pointsperday += rule.getItem().getType().getPoints();
        }

        return days * pointsperday;
    }

    public void create(MemberGateway member, Date loanDate, Date submissionDate, ArrayList<LettingruleGateway> lettingRules) {

        if (member == null) {

            throw new ValidationException("no member");
        } else {

            LettingGateway letting = new LettingGateway();
            letting.setMember(member);
            letting.setLoanDate(loanDate);
            letting.setSubmissionDate(submissionDate);
            table.insert(letting);

            for (LettingruleGateway lettingRule : lettingRules) {

                if (lettingRule.getItem() != null) {

                    lettingRule.setLetting(letting);
                    lettingruleTable.insert(lettingRule);
                } else {

                    break;
                }
            }
        }
    }

    public void checkIn(LettingGateway letting, Date returnDate, int points) {

        if (points == -1) {

            throw new ValidationException("no days");
        } else {

            MemberGateway member = letting.getMember();
            MemberGateway storeMember = MemberTable.getInstance().retreive(1);

            letting.setReturnDate(returnDate);
            letting.setPoints(points);
            table.update(letting);
            
            TransactionModule.getInstance().makeSimpleTransaction(member, storeMember, points, "L", "Uitlening van gereedschap", returnDate);
        }
    }
    
    public static LettingModule getInstance(){
        
        if(instance == null){
            
            instance = new LettingModule();
        }
        
        return instance;
    }
}
