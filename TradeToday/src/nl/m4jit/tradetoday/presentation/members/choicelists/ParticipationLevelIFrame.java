package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.members.ParticipationLevelGateway;
import nl.m4jit.tradetoday.domainlogic.members.ParticipationLevelModule;

public class ParticipationLevelIFrame extends RecordDialog<ParticipationLevelGateway> {

    public ParticipationLevelIFrame(ParticipationLevelsIFrame dialog) {

        this(null, dialog);
    }

    public ParticipationLevelIFrame(ParticipationLevelGateway record, ParticipationLevelsIFrame dialog) {

        super(new String[]{"75dlu"}, 2, record, dialog);

        addLabel("Nummer:");
        addTextField("index", 1);

        addLabel("Naam:");
        addTextField("name", 1);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Participatieniveau toevoegen";
        } else {

            return "Participatieniveau aanpassen";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Participatieniveau toegevoegd";
        } else {

            return "Participatieniveau aangepast";
        }
    }

    @Override
    public Object getValue(String name) {

        if (record == null) {

            return "";
        } else if (name.equals("name")) {

            return record.getName();
        } else if (name.equals("index")) {

            return record.getIndexnumber();
        } else {

            return "";
        }
    }

    @Override
    protected ParticipationLevelGateway create() {

        try {

            String index = getTextValue("index");
            String name = getTextValue("name");
            return ParticipationLevelModule.getInstance().create(index, name);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no index")) {

                giveMessageAndSetFocus("Geen nummer opgegeven", "index");
            } else if (message.equals("no number")) {

                giveMessageAndSetFocus("Geen getal bij nummer opgegeven", "index");
            } else if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
            
            return null;
        }
    }

    @Override
    protected void update() {
        try {
            String index = getTextValue("index");
            String name = getTextValue("name");
            ParticipationLevelModule.getInstance().update(record, index, name);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no index")) {

                giveMessageAndSetFocus("Geen nummer opgegeven", "index");
            } else if (message.equals("no number")) {

                giveMessageAndSetFocus("Geen getal bij nummer opgegeven", "index");
            } else if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
        }
    }
}
