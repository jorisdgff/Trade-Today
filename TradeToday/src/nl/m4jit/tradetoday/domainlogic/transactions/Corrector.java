package nl.m4jit.tradetoday.domainlogic.transactions;

public class Corrector {

    /*public static void calculateBalance(Member member, PersistenceManager pm, int trnumber) {

        if (member.getNumber() != 1) {

            int balance = 0;

            pm.prepareQuery("Transaction.findByMember");
            pm.setQueryParameter("member", member);
            List<Transaction> transactions = pm.runQuery();

            for (Transaction transaction : transactions) {

                String type = transaction.getType();

                pm.prepareQuery("TransactionRule.findByTransaction");
                pm.setQueryParameter("transaction", transaction);
                List<TransactionRule> transactionRules = pm.runQuery();

                for (TransactionRule transactionRule : transactionRules) {

                    if (type.equals("I")) {

                        balance += transactionRule.getPoints();
                    } else {

                        balance -= transactionRule.getPoints();
                    }
                }
            }

            pm.prepareQuery("TransactionRule.findByMember");
            pm.setQueryParameter("member", member);
            List<TransactionRule> transactionRules = pm.runQuery();

            for (TransactionRule transactionRule : transactionRules) {

                String type = transactionRule.getTransaction().getType();

                if (type.equals("I")) {

                    balance -= transactionRule.getPoints();
                } else {

                    balance += transactionRule.getPoints();
                }
            }

            if (member.getBalance() != balance) {

                member.setBalance(balance);
                pm.merge(member);
            }
        }
    }*/
}