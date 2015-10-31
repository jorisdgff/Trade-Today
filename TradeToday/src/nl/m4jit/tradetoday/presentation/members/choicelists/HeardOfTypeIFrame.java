package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.members.HeardOfTypeGateway;
import nl.m4jit.tradetoday.domainlogic.members.HeardOfTypeModule;

public class HeardOfTypeIFrame extends RecordDialog<HeardOfTypeGateway> {

    public HeardOfTypeIFrame(HeardOfTypesIFrame heardOfTypesIFrame) {

        this(null, heardOfTypesIFrame);
    }

    public HeardOfTypeIFrame(HeardOfTypeGateway record, HeardOfTypesIFrame heardOfTypesIFrame) {

        super(new String[]{"75dlu"}, 2, record, heardOfTypesIFrame);

        addLabel("Naam:");
        addTextField("name", 1);

        addLabel("Verwijzingsinstantie verplicht:");
        addCheckBox("refferinginstanceneeded", 1);

        setUI();
        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Gehoord via type toevoegen";
        } else {

            return "Gehoord via type aanpassen";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Gehoord via type toegevoegd";
        } else {

            return "Gehoord via type aangepast";
        }
    }

    @Override
    public Object getValue(String name) {

        boolean isNew = record == null;

        if (name.equals("name")) {

            if (isNew) {

                return "";
            } else {

                return record.getName();
            }
        } else if (name.equals("refferinginstanceneeded")) {

            if (isNew) {

                return false;
            } else {

                return record.getRefferingInstanceNeeded();
            }
        } else {

            return null;
        }
    }

    @Override
    protected HeardOfTypeGateway create() {

        try {

            String name = getTextValue("name");
            boolean refferinginstanceneeded = getCheckBoxValue("refferinginstanceneeded");
            return HeardOfTypeModule.getInstance().create(name, refferinginstanceneeded);

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
            boolean refferinginstanceneeded = getCheckBoxValue("refferinginstanceneeded");
            HeardOfTypeModule.getInstance().update(record, name, refferinginstanceneeded);

        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
        }
    }
}
