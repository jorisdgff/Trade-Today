package nl.m4jit.tradetoday.presentation.loanservice;

import java.util.List;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemGateway;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemTypeGateway;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemTypeTable;
import nl.m4jit.tradetoday.domainlogic.loanservice.LoanItemModule;

public class LoanItemIFrame extends RecordDialog<LoanItemGateway> {

    public LoanItemIFrame(LoanItemsPanel panel) {

        this(null, panel);
    }

    public LoanItemIFrame(LoanItemGateway record, LoanItemsPanel panel) {

        super(new String[]{"100dlu"}, 2, record, panel);

        /*panel.addLabel("Nummer:");

         JTextField numberField = panel.addTextField("number", 1);
         numberField.setEditable(false);*/
        addLabel("Type:");
        addComboBox("type", 1);

        addLabel("Omschrijving:");
        addTextPane("description", 1, 50);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Gereedschap toevoegen";
        } else {

            return "Gereedschap aanpassen";
        }
    }

    @Override
    public Object getValue(String name) {

        boolean isNew = record == null;

        if (isNew) {

            return null;
        } else {

            if (name.equals("number")) {

                return record.getNumber();
            } else if (name.equals("type")) {

                return record.getType();
            } else if (name.equals("description")) {

                return record.getDescription();
            } else {

                return null;
            }
        }
    }

    @Override
    public List<LoanItemTypeGateway> getCollection(String name) {

        if (name.equals("type")) {

            return LoanItemTypeTable.getInstance().findAll();
        } else {

            return null;
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Gereedschap toegevoegd";
        } else {

            return "Gereedschap aangepast";
        }
    }

    @Override
    protected LoanItemGateway create() {

        try {

            LoanItemTypeGateway loanType = (LoanItemTypeGateway) getSelectedComboBoxItem("type");
            String description = getTextValue("description");
            return LoanItemModule.getInstance().create(loanType, description);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no type")) {

                giveMessageAndSetFocus("Geen type opgegeven", "type");
            }
            
            return null;
        }
    }

    @Override
    protected void update() {

        try {

            LoanItemTypeGateway loanType = (LoanItemTypeGateway) getSelectedComboBoxItem("type");
            String description = getTextValue("description");
            LoanItemModule.getInstance().update(record, loanType, description);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no type")) {

                giveMessageAndSetFocus("Geen type opgegeven", "type");
            }
        }
    }
}
