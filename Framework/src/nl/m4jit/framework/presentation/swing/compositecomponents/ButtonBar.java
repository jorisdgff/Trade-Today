package nl.m4jit.framework.presentation.swing.compositecomponents;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;

/**
 *
 * @author joris
 */
public class ButtonBar extends Box {

    private ActionListener listener;
    private boolean horizontal, buttonAdded;
    private Dimension buttonDimension;

    public ButtonBar(boolean horizontal, int barSize, int buttonSize, ActionListener listener) {

        super(horizontal ? BoxLayout.X_AXIS : BoxLayout.Y_AXIS);
        this.horizontal = horizontal;
        this.listener = listener;

        buttonDimension = new Dimension(buttonSize, buttonSize);
        setBorder(BorderFactory.createEtchedBorder());

        if (horizontal) {

            setPreferredSize(new Dimension(0, barSize));
        } else {

            setPreferredSize(new Dimension(barSize, 0));
        }
    }

    public JButton addButton(String text, String actionCommand) {

        if (!buttonAdded) {

            addStrut();
            buttonAdded = true;
        }

        JButton tempButton = new JButton("<html><center>" + text);
        tempButton.setMargin(new Insets(0, 0, 0, 0));
        tempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tempButton.setMaximumSize(buttonDimension);
        tempButton.setPreferredSize(buttonDimension);
        tempButton.setActionCommand(actionCommand);
        tempButton.addActionListener(listener);

        add(tempButton);
        addStrut();

        return tempButton;
    }

    private void addStrut(){

        if (horizontal) {

                add(createHorizontalStrut(10));
            } else {

                add(createVerticalStrut(10));
            }
    }

    public void addGlue() {

        if (horizontal) {

            add(createHorizontalGlue());
        } else {

            add(createVerticalGlue());
        }
    }
}