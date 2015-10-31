package nl.m4jit.tradetoday.presentation.members.choicelists;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.members.IncomeTypeGateway;
import nl.m4jit.tradetoday.domainlogic.members.IncomeTypeModule;

public class IncomeTypeIFrame extends RecordDialog<IncomeTypeGateway> {

    public IncomeTypeIFrame(IncomeTypesIFrame incomeTypesIFrame) {

        this(null, incomeTypesIFrame);
    }

    public IncomeTypeIFrame(IncomeTypeGateway incomeType, IncomeTypesIFrame incomeTypesIFrame) {

        super(new String[]{"75dlu"}, 1, incomeType, incomeTypesIFrame);

        addLabel("Naam:");
        addTextField("name", 1);

        setUI();
    }

    @Override
    public String getScreenTitle(boolean isNew) {

        if (isNew) {

            return "Inkomenstype toevoegen";
        } else {

            return "Inkomenstype aanpassen";
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        if (isNew) {

            return "Inkomenstype toegevoegd";
        } else {

            return "Inkomenstype aangepast";
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
    protected IncomeTypeGateway create() {

        try {

            String name = getTextValue("name");
            return IncomeTypeModule.getInstance().create(name);
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
            IncomeTypeModule.getInstance().update(record, name);

        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no name")) {

                giveMessageAndSetFocus("Geen naam opgegeven", "name");
            }
        }
    }
}
