package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.util.HashMap;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author joris
 */
public class ITaskPaneContainer extends JXTaskPaneContainer {

    protected HashMap<String, JXTaskPane> taskPanes;
    protected JXTaskPane currentTaskPane;
    protected ActionListener listener;

    public ITaskPaneContainer(ActionListener listener) {

        setOpaque(false);

        this.listener = listener;
        taskPanes = new HashMap<String, JXTaskPane>();
    }

    public void addTaskPane(String name, String text) {

        JXTaskPane taskPane = new JXTaskPane();
        taskPane.setTitle(text);
        
        add(taskPane);

        currentTaskPane = taskPane;
        taskPanes.put(name, taskPane);
    }

    public void addTaskPaneItem(String text, String actionCommand) {

        JButton button = (JButton) currentTaskPane.add((Action) null);
        button.setText(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
    }
}
