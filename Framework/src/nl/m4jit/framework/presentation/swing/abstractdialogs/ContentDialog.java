package nl.m4jit.framework.presentation.swing.abstractdialogs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSeparator;

/**
 *
 * @author Joris
 */
public abstract class ContentDialog extends JFrame implements ActionListener, WindowListener {

    protected boolean useInTabbedPane;
    protected JFrame frame;
    
    public ContentDialog() {

        this(false);
    }

    public ContentDialog(boolean useInTabbedPane) {

        this.useInTabbedPane = useInTabbedPane;

        setLayout(new BorderLayout());
        setBorders();

        if (!useInTabbedPane) {

            addWindowListener(this);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }
    }

    
    
    private void setBorders(){
        
        Box northBox = new Box(BoxLayout.PAGE_AXIS);
        northBox.add(Box.createVerticalStrut(5));
        northBox.add(new JSeparator());
        northBox.add(Box.createVerticalStrut(5));
        
        add(northBox, BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
    }
    
    protected void setUI() {

        if (!useInTabbedPane) {

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }
        
        setTitle(getScreenTitle());
    }

    public abstract String getScreenTitle();

    public void addToCenter(Container val) {

        add(val, BorderLayout.CENTER);
    }

    public void addToSouth(Container val) {

        add(val, BorderLayout.SOUTH);
    }

    public void addToWest(Container val) {

        add(val, BorderLayout.WEST);
    }

    public void addToEast(Container val) {

        add(val, BorderLayout.EAST);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String ac = e.getActionCommand();
        processActionCommand(ac);
    }

    protected void processActionCommand(String ac) {

    }

    public JFrame createFrame(boolean fullscreen) {

        frame = new JFrame(getScreenTitle());
        frame.setContentPane(getContentPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if(getJMenuBar() != null){
            
            frame.setJMenuBar(getJMenuBar());
        }
        
        if (fullscreen) {

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        return frame;
    }

    @Override
    public void windowOpened(WindowEvent we) {
        
    }

    @Override
    public void windowClosing(WindowEvent we) {
        
    }

    @Override
    public void windowClosed(WindowEvent we) {
        
        closed();
    }

    protected void closed(){
        
    }
    
    @Override
    public void windowIconified(WindowEvent we) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        
    }

    @Override
    public void windowActivated(WindowEvent we) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        
    }
}
