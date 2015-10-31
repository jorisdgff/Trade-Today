package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.members.RefferingInstanceGateway;
import nl.m4jit.tradetoday.domainlogic.members.RefferingInstanceModule;

public class RefferingInstanceIFrame extends RecordDialog<RefferingInstanceGateway> {

    public RefferingInstanceIFrame(RefferingInstancesIFrame dialog) {

        this(null, dialog);
    }

    public RefferingInstanceIFrame(RefferingInstanceGateway record, RefferingInstancesIFrame dialog) {

        super(new String[]{"75dlu"}, 2, record, dialog);

        addLabel("Naam:");
        addTextField("name", 1);

        addLabel("Volgnummer:");
        addTextField("ordering", 1);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Verwijzende organisatie toevoegen";
        } else {

            return "Verwijzende organisatie aanpassen";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Verwijzende organisatie toegevoegd";
        } else {

            return "Verwijzende organisatie aangepast";
        }
    }

    @Override
    public Object getValue(String name) {

        if (record != null) {

            if (name.equals("ordering")) {

                return record.getOrdering() + "";
            } else if (name.equals("name")) {

                return record.getName();
            } else {

                return "Fout";
            }
        } else {

            return null;
        }
    }

    @Override
    protected RefferingInstanceGateway create() {

        try {

            String name = getTextValue("name");
            String ordering = getTextValue("ordering");
            return RefferingInstanceModule.getInstance().create(name, ordering);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
            
            return null;
        }
    }

    @Override
    protected void update() {

        try {

            String name = getTextValue("name");
            String ordering = getTextValue("ordering");
            RefferingInstanceModule.getInstance().update(record, name, ordering);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
        }

    }
}
