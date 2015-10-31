package nl.m4jit.framework.presentation.swing.implementedcomponents;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TextRenderer extends DefaultTableCellRenderer{

    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int row, int col) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, value, bln, bln1, row, col);

        if(value instanceof Integer || value instanceof Double){

            label.setHorizontalAlignment(JLabel.RIGHT);
        }

        return label;
    }
}