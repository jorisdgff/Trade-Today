package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeModel;

public class ITree extends JTree implements MouseListener {

    private JScrollPane pane;
    private HashMap<Class, JPopupMenu> popupMenus;

    public ITree(TreeModel model) {

        super(model);
        pane = new JScrollPane(this);
        addMouseListener(this);

        popupMenus = new HashMap<Class, JPopupMenu>();
    }

    public JScrollPane getPane() {

        return pane;
    }

    public void addPopupMenu(Class c, JPopupMenu popupMenu){

        popupMenus.put(c, popupMenu);
    }

    public void mouseClicked(MouseEvent me) {

        if (SwingUtilities.isRightMouseButton(me)) {

            int row = getClosestRowForLocation(me.getX(), me.getY());
            setSelectionRow(row);

            JPopupMenu popupMenu = popupMenus.get(getSelectionPath().getLastPathComponent().getClass());

            if(popupMenu != null){

                popupMenu.show(me.getComponent(), me.getX(), me.getY());
            }
        }
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }
}
