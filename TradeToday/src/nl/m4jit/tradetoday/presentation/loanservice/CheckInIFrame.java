package nl.m4jit.tradetoday.presentation.loanservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LettingGateway;
import nl.m4jit.tradetoday.domainlogic.loanservice.LettingModule;

public class CheckInIFrame extends RecordDialog<LettingGateway> {

    private int points = -1;
    
    public CheckInIFrame(LettingGateway record, LettingsIFrame dialog) {

        super(new String[]{"75dlu"}, 3, record, dialog);

        addLabel("Datum");
        addFormattedTextField("date", new SimpleDateFormat("d-M-yyyy"), 1);

        addLabel("Dagdelen");
        addTextField("days", 1);

        addLabel("Punten");

        JTextField pointsField = addTextField("points", 1);
        pointsField.setEditable(false);
        pointsField.addFocusListener(this);

        setUI();
    }
    
    @Override
    public String getScreenTitle(boolean isNew) {

        return "Uitlening inchekken";
    }

    @Override
    public Object getValue(String name) {

        if (name.equals("date")) {

            return new Date();
        } else {

            return null;
        }
    }

    @Override
    public String getNotificationMessge(boolean isNew) {

        return "Uitlening ingecheckt";
    }

    @Override
    public void focusGainedEvent(String name) {

        if (name.equals("points")) {

            int days = Integer.parseInt(getTextValue("days"));
            points = LettingModule.getInstance().calculatePoints(record, days);
            setTextValue("points", "" + points);
        }
    }

    @Override
    protected LettingGateway create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void update() {
        
        Date returnDate = (Date) getFormattedTextFieldValue("date");

        try {

            LettingModule.getInstance().checkIn(record, returnDate, points);
        } catch (ValidationException ex) {

            String message = ex.getMessage();

            if (message.equals("no days")) {

                giveMessageAndSetFocus("Geen aantal dagdelen opgegeven", "days");
            }
        }
    }
}
