package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.awt.event.FocusEvent;
import java.text.Format;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Joris
 */
public class IFormattedTextField extends JFormattedTextField {

    public IFormattedTextField(Format format) {

        super(format);
    }

    @Override
    protected void processFocusEvent(final FocusEvent e) {

        if (e.isTemporary()) {
            return;
        }

        if (e.getID() == FocusEvent.FOCUS_LOST) {
            if (getText() == null || getText().isEmpty()) {
                setValue(null);
            }
        }
        super.processFocusEvent(e);
    }
}
