package nl.m4jit.framework.presentation.swing.abstractdialogs;

import javax.swing.JTabbedPane;

/**
 *
 * @author Joris
 */
public abstract class TabbedDialog extends ContentDialog{
 
    protected JTabbedPane tabbedPane;
    
    public TabbedDialog() {

        tabbedPane = new JTabbedPane();
        addToCenter(tabbedPane);
    }
    
    public void addPanel(ContentDialog holderdialog) {

        tabbedPane.add(holderdialog.getContentPane(), holderdialog.getScreenTitle());
    }
}