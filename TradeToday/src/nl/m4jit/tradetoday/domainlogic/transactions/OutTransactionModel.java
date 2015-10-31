package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import javax.swing.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;

public class OutTransactionModel extends IATableModel<TransactionruleGateway> {

    public OutTransactionModel(ArrayList<TransactionruleGateway> rules) {

        super(new String[]{"Omschrijving", "Punten", "Van lidnr."}, new Class[]{String.class, Integer.class, String.class}, new boolean[]{true, true, true}, false);
        this.items = rules;
    }

    @Override
    public Object getColValue(TransactionruleGateway item, int col) {

        switch (col) {

            case 0:
                return item.getDescription();

            case 1:
                return item.getPoints();

            case 2:

                MemberGateway member = item.getMember();

                if (member.getNumber() == 1) {

                    if (item.isMemberIsSet()) {

                        return "1. Ruilwinkel";
                    } else {

                        return "";
                    }
                } else {

                    return item.getMember();
                }

            default:
                return null;
        }
    }

    @Override
    protected void setColValue(Object value, TransactionruleGateway item, int col) {

        switch (col) {

            case 0:
                item.setDescription("" + value);
                break;

            case 1:
                item.setPoints(Integer.parseInt("" + value));
                break;

            case 2:

                int number = Integer.parseInt((String) value);
                MemberGateway member = MemberTable.getInstance().retreive(number);

                if (member == null) {

                    JOptionPane.showMessageDialog(null, "Lid niet gevonden");
                    throw new ValidationException("member not found");
                } else if (member.getDeregistrationdate() == null) {

                    item.setMember(member);
                } else {

                    JOptionPane.showMessageDialog(null, "Lid is uitgeschreven");
                    throw new ValidationException("member is out");
                }

                break;
        }
    }

    @Override
    protected List<TransactionruleGateway> getItems(Object filter) {
        
        return null;
    }
}
