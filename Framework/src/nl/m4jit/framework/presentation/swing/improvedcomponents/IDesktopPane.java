package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

/**
 *
 * @author joris
 */
public class IDesktopPane extends JDesktopPane{

    protected JLabel backgroundLabel;

    public IDesktopPane(String backgroundpath){

        backgroundLabel = new JLabel(new ImageIcon(backgroundpath));
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setOpaque(true);
        backgroundLabel.setBackground(Color.WHITE);
        backgroundLabel.add(this);
    }

    public JLabel getBackgroundLabel(){

        return backgroundLabel;
    }
}
