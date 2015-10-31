package nl.m4jit.tradetoday.presentation.nhcjobs.types;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobTypeGateway;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.NHCJobTypeModule;

public class NHCJobTypeIFrame extends RecordDialog<NHCJobTypeGateway> {

    public NHCJobTypeIFrame(NHCJobTypesIFrame incomeTypesIFrame) {

        this(null, incomeTypesIFrame);
    }

    public NHCJobTypeIFrame(NHCJobTypeGateway incomeType, NHCJobTypesIFrame incomeTypesIFrame) {

        super(new String[]{"75dlu"}, 1, incomeType, incomeTypesIFrame);

        addLabel("Naam:");
        addTextField("name", 1);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Klustype toevoegen";
        } else {

            return "Klustype aanpassen";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Klustype toegevoegd";
        } else {

            return "Klustype aangepast";
        }
    }

    @Override
    public Object getValue(String name) {

        if (record != null && name.equals("name")) {

            return record.getName();
        } else {

            return null;
        }
    }

    @Override
    protected NHCJobTypeGateway create() {

        try {

            String name = getTextValue("name");
            return NHCJobTypeModule.getInstance().create(name);
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
            NHCJobTypeModule.getInstance().update(record, name);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
        }
    }
}
