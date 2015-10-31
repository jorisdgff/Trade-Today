package nl.m4jit.framework.presentation.swing.abstractdialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Joris
 */
public abstract class ButtonBarDialog extends ContentDialog{
    
    public static final int LEFT = 0, RIGHT = 1;
    protected JPanel buttonPanel, buttonPanelLeft, buttonPanelRight;
    
    public ButtonBarDialog(){
        
        this(false);
    }
    
    public ButtonBarDialog(boolean useInTabbedPane){
        
        super(useInTabbedPane);
        
        buttonPanel = new JPanel(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        addToSouth(buttonPanel);

        buttonPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(buttonPanelLeft, BorderLayout.WEST);

        buttonPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonPanelRight, BorderLayout.EAST);
    }

    public JButton createButton(String text, String ac){

        JButton tempButton = new JButton(text);

        tempButton.setPreferredSize(new Dimension(100, 25));
        tempButton.setActionCommand(ac);
        tempButton.addActionListener(this);

        return tempButton;
    }

    public JButton addButton(String text, String ac, int position){

        JButton tempButton = createButton(text, ac);

        if(position == LEFT){

            buttonPanelLeft.add(tempButton);
        }else if(position == RIGHT){

            buttonPanelRight.add(tempButton);
        }

        return tempButton;
    }

    public void addComponent(JComponent component, int position){

        if(position == LEFT){

            buttonPanelLeft.add(component);
        }else if(position == RIGHT){

            buttonPanelRight.add(component);
        }
    }
}
