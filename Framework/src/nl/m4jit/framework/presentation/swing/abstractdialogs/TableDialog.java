package nl.m4jit.framework.presentation.swing.abstractdialogs;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.framework.presentation.swing.improvedcomponents.ITable;

public abstract class TableDialog<D> extends ButtonBarDialog implements ItemListener {

    protected IATableModel<D> tableModel;
    protected Box upperFilterPanel;
    protected JComboBox upperFilterBox;
    protected ITable table;

    public TableDialog() {

        this(null, false);
    }

    public TableDialog(boolean useInTabbedPane) {

        this(null, useInTabbedPane);
    }

    public TableDialog(IATableModel<D> model) {

        this(model, false);
    }

    public TableDialog(IATableModel<D> model, boolean useInTabbedPane) {

        super(useInTabbedPane);
        this.tableModel = model;

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));

        List filteritems = tableModel.getFilteritems();

        if (filteritems != null) {

            upperFilterPanel = new Box(BoxLayout.LINE_AXIS);
            centerPanel.add(upperFilterPanel, BorderLayout.NORTH);

            //TODO naam opgeven
            /*JLabel label = new JLabel(filter.getText());
            upperFilterPanel.add(label);*/
            upperFilterPanel.add(Box.createHorizontalStrut(25));

            upperFilterBox = new JComboBox(tableModel.getFilteritems().toArray());
            upperFilterBox.addItemListener(this);
            upperFilterPanel.add(upperFilterBox);
        }

        table = new ITable(tableModel);
        //table.getPane().setPreferredSize(new Dimension(width, 0));
        centerPanel.add(table.getPane(), BorderLayout.CENTER);

        addToCenter(centerPanel);
    }

    public ITable getTable() {

        return table;
    }

    public void updateTable() {

        tableModel.updateData();
        table.updateUI();
    }

    public int getSelectedRow() {

        return table.getSelectedRow();
    }

    public D getSelectedRecord() {

        return tableModel.getItem(getSelectedRow());
    }

    public void enableNew() {

        addButton("Nieuw", "new", RIGHT);
    }

    public void enableChange() {

        enableChange("Aanpassen");
    }

    public void enableChange(String text) {

        addButton(text, "change", RIGHT);
    }

    public void enablePrint() {

        addButton("Afdrukken", "print", RIGHT);
    }

    public void printTable(String header, String footer) {

        MessageFormat headerfm = new MessageFormat(header);
        MessageFormat footerfm = new MessageFormat(footer);

        try {

            table.print(JTable.PrintMode.FIT_WIDTH, headerfm, footerfm);
        } catch (PrinterException ex) {

            System.out.println("Printfout: " + ex);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

        if (ie.getStateChange() == ItemEvent.SELECTED) {

            JComboBox source = (JComboBox) ie.getSource();
            Object selectedRecord = source.getSelectedItem();
            tableModel.setFilterstate(selectedRecord);

            updateTable();
        }
    }

    @Override
    protected void processActionCommand(String ac) {

        if (ac.equals("new")) {

            newRecord();
        } else if (ac.equals("change")) {

            changeRecord(getSelectedRecord());
        } else if (ac.equals("print")) {

            printTable(getHeader(), getFooter());
        } else {

            processUnknowActionCommand(ac);
        }
    }

    protected void processUnknowActionCommand(String ac) {
    }

    protected void newRecord() {
    }

    protected void changeRecord(D record) {
    }

    protected String getHeader() {

        return "";
    }

    protected String getFooter() {

        return "";
    }

    public void addRecord(D record) {

        tableModel.addItem(record);
        updateTable();
    }

    public void removeRecord(D record) {

        tableModel.removeItem(record);
        updateTable();
    }
}
