package nl.m4jit.framework.presentation.swing.implementedcomponents;

import java.awt.Component;
import java.util.EventObject;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import nl.m4jit.framework.IComboBoxModel;

public class TableComboBox extends JComboBox implements TableCellEditor{

    public TableComboBox(List records){
        
        super(new IComboBoxModel(records));
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        
        return "U2";
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        
        return false;
    }

    @Override
    public void cancelCellEditing() {
        
        
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        
    }
}