package nl.m4jit.framework.presentation.swing.improvedcomponents;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.framework.presentation.swing.implementedcomponents.TextRenderer;

public class ITable extends JTable implements MouseListener {

    private JScrollPane pane;
    private TextRenderer textRenderer;
    private IATableModel model;
    private IPopupMenu popupMenu;

    public ITable(TableModel model) {

        super(model);
        pane = new JScrollPane(this);
    }

    public ITable(IATableModel model) {

        super(model);
        this.model = model;
        pane = new JScrollPane(this);
        addMouseListener(this);

        setDefaultRenderer(String.class, textRenderer);
        setDefaultRenderer(Integer.class, textRenderer);
        setDefaultRenderer(Double.class, textRenderer);

        if(model != null){
            
            setColumnWidths();
        }
    }

    public void setModel(IATableModel val) {

        super.setModel(val);
        model = val;
        setColumnWidths();
    }

    public void setPopupMenu(IPopupMenu val){
        
        popupMenu = val;
    }
    
    private void setColumnWidths() {

        if (model.getMinColWidths() != null) {

            for (int i = 0; i < columnModel.getColumnCount(); i++) {

                columnModel.getColumn(i).setMinWidth(model.getMinColWidths()[i]);
            }
        }
    }

    public JScrollPane getPane() {

        return pane;
    }

    public void setSelectedRow(int row) {

        selectionModel.setSelectionInterval(row, row);
    }

    public void mouseClicked(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e)) {

            Point p = e.getPoint();
            int rowNumber = rowAtPoint(p);

            ListSelectionModel model = getSelectionModel();
            model.setSelectionInterval(rowNumber, rowNumber);
        }
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
        
        if(popupMenu != null && me.isPopupTrigger()){
            
            Point p = me.getPoint();
            int rowNumber = rowAtPoint(p);

            ListSelectionModel model = getSelectionModel();
            model.setSelectionInterval(rowNumber, rowNumber);
            
            popupMenu.show(me.getComponent(), me.getX(), me.getY());
        }
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }
}
