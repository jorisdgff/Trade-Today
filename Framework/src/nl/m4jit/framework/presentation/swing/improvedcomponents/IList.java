package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.util.Collection;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class IList extends JList {

    private JScrollPane pane;

    public IList() {

        pane = new JScrollPane(this);
    }

    public IList(ListModel model) {

        super(model);
        pane = new JScrollPane(this);
    }

    public IList(Object[] items) {

        super(items);
        pane = new JScrollPane(this);
    }

    public IList(Collection items) {

        super(items.toArray());
        pane = new JScrollPane(this);
    }

    public JScrollPane getPane() {

        return pane;
    }
}
