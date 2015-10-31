package nl.m4jit.tradetoday.presentation.loanservice;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemTypeGateway;
import nl.m4jit.tradetoday.domainlogic.loanservice.LoanItemTypeModule;

public class LoanItemTypeIFrame extends RecordDialog<LoanItemTypeGateway> {

    public LoanItemTypeIFrame(LoanItemTypesPanel dialog) {

        this(null, dialog);
    }

    public LoanItemTypeIFrame(LoanItemTypeGateway record, LoanItemTypesPanel dialog) {

        super(new String[]{"100dlu"}, 3, record, dialog);

        addLabel("Naam:");
        addTextField("name", 1);

        addLabel("Omschrijving:");
        addTextPane("description", 1, 50);

        addLabel("Punten:");
        addTextField("points", 1);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Gereedschapstype toevoegen";
        } else {

            return "Gereedschaptype aanpassen";
        }
    }

    @Override
    public Object getValue(String name) {

        boolean isNew = record == null;

        if (isNew) {

            return "";
        } else if (name.equals("name")) {

            return record.getName();
        } else if (name.equals("description")) {

            return record.getDescription();
        } else if (name.equals("points")) {

            return record.getPoints();
        } else {

            return null;
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Gereedschapstype toegevoegd";
        } else {

            return "Gereedschaptype aangepast";
        }
    }

    @Override
    protected LoanItemTypeGateway create() {

        try {

            String name = getTextValue("name");
            String description = getTextValue("description");
            int points = Integer.parseInt(getTextValue("points"));
            return LoanItemTypeModule.getInstance().create(name, description, points);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            } else if (message.equals("no points")) {

                giveMessageAndSetFocus("Geen punten opgegeven", "points");
            }
            
            return null;
        }
    }

    @Override
    protected void update() {

        try {

            String name = getTextValue("name");
            String description = getTextValue("description");
            int points = Integer.parseInt(getTextValue("points"));
            LoanItemTypeModule.getInstance().update(record, name, description, points);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            } else if (message.equals("no points")) {

                giveMessageAndSetFocus("Geen punten opgegeven", "points");
            }
        }
    }
}
