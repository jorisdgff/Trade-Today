package nl.m4jit.tradetoday.presentation.users;

import nl.m4jit.tradetoday.domainlogic.Application;
import java.util.Date;
import nl.m4jit.framework.presentation.swing.abstractdialogs.OkCancelDialog;
import org.jdesktop.swingx.JXMonthView;

/**
 *
 * @author joris
 */
public class DefDateIFrame extends OkCancelDialog{

    private JXMonthView calendar;

    public DefDateIFrame(){

        calendar = new JXMonthView();
        calendar.setSelectionDate(Application.getInstance().getDefaultDate());
        calendar.setTraversable(true);
        calendar.setShowingLeadingDays(true);
        calendar.setShowingTrailingDays(true);
        calendar.setOpaque(false);

        addToCenter(calendar);
        setUI();
    }

    @Override
    public String getScreenTitle() {

        return "Verander standaard datum";
    }

    @Override
    public void ok() {

        Date defDate = calendar.getSelectionDate();
        Application.getInstance().setDefaultDate(defDate);
        dispose();
    }
}
