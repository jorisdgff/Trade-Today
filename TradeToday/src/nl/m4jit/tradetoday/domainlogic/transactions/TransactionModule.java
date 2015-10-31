package nl.m4jit.tradetoday.domainlogic.transactions;

import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;
import nl.m4jit.tradetoday.domainlogic.*;

import java.text.*;
import java.util.*;
import nl.m4jit.framework.ValidationException;

public class TransactionModule {

    private final TransactionTable table;
    private final MemberTable memberTable;
    private final TransactionruleTable transactionruleTable;
    private static TransactionModule instance;

    private TransactionModule() {

        table = TransactionTable.getInstance();
        memberTable = MemberTable.getInstance();
        transactionruleTable = TransactionruleTable.getInstance();
    }

    public ArrayList<TransactionruleGateway> createRules(boolean intransaction) {

        ArrayList<TransactionruleGateway> rules = new ArrayList<TransactionruleGateway>();

        MemberGateway storeMember = memberTable.retreive(1);

        for (int i = 0; i < 10; i++) {

            TransactionruleGateway transactionrule = new TransactionruleGateway();
            transactionrule.setMember(storeMember);

            if (i == 0 && intransaction) {

                transactionrule.setDescription("Diverse artikelen");
            }

            rules.add(transactionrule);
        }

        return rules;
    }

    public void saveTransaction(String type, MemberGateway member, Date date, ArrayList<TransactionruleGateway> rules) {

        if (member == null) {

            throw new ValidationException("no member");
        }

        TransactionGateway transaction = new TransactionGateway();
        transaction.setType(type);
        transaction.setMember(member);
        transaction.setDate(date);
        transaction.setInsertAccount(Application.getInstance().getAccount());
        table.insert(transaction);
        
        boolean intransaction = transaction.getType().equals("I");
        int totalpoints = 0;

        for (TransactionruleGateway rule : rules) {

            if (rule.getDescription() != null) {

                rule.setTransaction(transaction);
                transactionruleTable.insert(rule);

                int points = rule.getPoints();
                int oldbalance = rule.getMember().getBalance();
                rule.getMember().setBalance(intransaction ? oldbalance - points : oldbalance + points);
                memberTable.update(rule.getMember());
                totalpoints += points;
            } else {

                break;
            }
        }

        int oldbalance = transaction.getMember().getBalance();
        transaction.getMember().setBalance(!intransaction ? oldbalance - totalpoints : oldbalance + totalpoints);
        memberTable.update(transaction.getMember());

        /*Corrector.calculateBalance(member, pm, record.getNumber());

         for (TransactionRule rule : rules) {

         if (rule.getDescription() != null) {

         Member member = rule.getMember();

         Corrector.calculateBalance(member, pm, record.getNumber());
         } else {

         break;
         }
         }*/
    }

    public TransactionGateway makeSimpleTransaction(MemberGateway fromMember, MemberGateway toMember, int points, String type, String description, Date date) {

        if (type.equals("S")) {

            if (fromMember == null) {

                throw new ValidationException("no client");
            }

            if (toMember == null) {

                throw new ValidationException("no provider");
            }
        } else {

            if (fromMember == null) {

                fromMember = memberTable.retreive(1);
            }

            if (toMember == null) {

                toMember = memberTable.retreive(1);
            }
        }

        if (description.isEmpty()) {

            throw new ValidationException("no description");
        }

        TransactionGateway transaction = new TransactionGateway();
        transaction.setType(type);
        transaction.setMember(fromMember);
        transaction.setDate(date);
        transaction.setInsertAccount(Application.getInstance().getAccount());
        table.insert(transaction);
        
        TransactionruleGateway transactionrule = new TransactionruleGateway();
        transactionrule.setTransaction(transaction);
        transactionrule.setDescription(description);
        transactionrule.setPoints(points);
        transactionrule.setMember(toMember);
        transactionruleTable.insert(transactionrule);
        
        if (type.equals("I")) {

            int oldbalance = fromMember.getBalance();
            fromMember.setBalance(oldbalance + points);
            memberTable.update(fromMember);

            oldbalance = toMember.getBalance();
            toMember.setBalance(oldbalance - points);
            memberTable.update(toMember);
        } else {

            int oldbalance = fromMember.getBalance();
            fromMember.setBalance(oldbalance - points);
            memberTable.update(fromMember);

            oldbalance = toMember.getBalance();
            toMember.setBalance(oldbalance + points);
            memberTable.update(toMember);
        }

        return transaction;
    }

    public void correctTransaction(TransactionruleGateway originalrule, Date date, String reason) {

        String dateString = new SimpleDateFormat("d-M-yyyy").format(originalrule.getTransaction().getDate().getTime());
        String description = "C: " + dateString + ", " + originalrule.getDescription() + ": " + reason;

        originalrule.setCorrected(true);

        MemberGateway transactionMember = originalrule.getTransaction().getMember();
        MemberGateway ruleMember = originalrule.getMember();
        int points = 0 - originalrule.getPoints();
        String type = originalrule.getTransaction().getType();

        makeSimpleTransaction(transactionMember, ruleMember, points, type, description, date);
    }

    public int calculateMemberPoints(MemberGateway member) {

        List<TransactionruleGateway> rules = TransactionruleTable.getInstance().findByMember(member.getNumber());
                
        int totalpoints = 0;

        for (TransactionruleGateway rule : rules) {

            MemberGateway transactionMember = rule.getTransaction().getMember();
            boolean isintransaction = rule.getTransaction().getType().equals("I");

            if (isintransaction) {

                transactionMember = rule.getMember();
            }

            int viewpoints = rule.getPoints();

            if (member.getNumber() == transactionMember.getNumber()) {

                viewpoints = 0 - rule.getPoints();
            }

            totalpoints += viewpoints;
        }

        return totalpoints;
    }
    
    public List<TransactionruleGateway> findTransactionRulesByMemberAndMonth(MemberGateway member, int month, int year, boolean intake) {

        List<TransactionruleGateway> rules = TransactionruleTable.getInstance().findByMemberAndMonth(member.getNumber(), month, year, intake);
                
        int totalpoints = 0;

        for (TransactionruleGateway rule : rules) {

            MemberGateway transactionMember = rule.getTransaction().getMember();
            boolean isintransaction = rule.getTransaction().getType().equals("I");

            if (isintransaction) {

                transactionMember = rule.getMember();
            }

            int viewpoints = rule.getPoints();

            if (member.getNumber() == transactionMember.getNumber()) {

                viewpoints = 0 - rule.getPoints();
            }

            rule.setViewpoints(viewpoints);
            totalpoints += viewpoints;
        }

        if(!intake){
            
            TransactionruleGateway totalrule = new TransactionruleGateway();
            totalrule.setDescription("Totaal");
            totalrule.setViewpoints(totalpoints);
            rules.add(totalrule);
        }
        
        return rules;
    }

    public List<TransactionruleGatewayArch> findTransactionRulesArchByMember(int numbermember) {

        List<TransactionruleGatewayArch> rules = TransactionruleTableArch.getInstance().findByMemberAndMonth(numbermember);
                
        int totalpoints = 0;

        for (TransactionruleGatewayArch rule : rules) {

            MemberGateway transactionMember = rule.getTransaction().getMember();
            boolean isintransaction = rule.getTransaction().getType().equals("I");

            if (isintransaction) {

                transactionMember = rule.getMember();
            }

            int viewpoints = rule.getPoints();

            if (numbermember == transactionMember.getNumber()) {

                viewpoints = 0 - rule.getPoints();
            }

            rule.setViewpoints(viewpoints);
            totalpoints += viewpoints;
        }

        TransactionruleGatewayArch totalrule = new TransactionruleGatewayArch();
        totalrule.setDescription("Totaal");
        totalrule.setViewpoints(totalpoints);
        rules.add(totalrule);
        
        System.out.println(totalpoints);

        return rules;
    }
    
    public List<TransactionruleGatewayArch> findTransactionRulesArchByMemberAndMonth(int numbermember, int month, int year) {

        List<TransactionruleGatewayArch> rules = TransactionruleTableArch.getInstance().findByMemberAndMonth(numbermember, month, year);
                
        int totalpoints = 0;

        for (TransactionruleGatewayArch rule : rules) {

            MemberGateway transactionMember = rule.getTransaction().getMember();
            boolean isintransaction = rule.getTransaction().getType().equals("I");

            if (isintransaction) {

                transactionMember = rule.getMember();
            }

            int viewpoints = rule.getPoints();

            if (numbermember == transactionMember.getNumber()) {

                viewpoints = 0 - rule.getPoints();
            }

            rule.setViewpoints(viewpoints);
            totalpoints += viewpoints;
        }

        TransactionruleGatewayArch totalrule = new TransactionruleGatewayArch();
        totalrule.setDescription("Totaal");
        totalrule.setViewpoints(totalpoints);
        rules.add(totalrule);

        return rules;
    }

    public static TransactionModule getInstance() {

        if (instance == null) {

            instance = new TransactionModule();
        }

        return instance;
    }
}
