package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author joris
 */
public class IPopupMenu extends JPopupMenu{

    private ActionListener listener;

    public IPopupMenu(ActionListener listener){

        this.listener = listener;
    }

    public void addMenuItem(String text, String action){

        JMenuItem tempItem = new JMenuItem(text);
        tempItem.setActionCommand(action);
        tempItem.addActionListener(listener);
        add(tempItem);
    }
}
