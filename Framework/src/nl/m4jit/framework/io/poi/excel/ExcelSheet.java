package nl.m4jit.framework.io.poi.excel;

import javax.swing.table.TableModel;

public class ExcelSheet {

    private String name;
    private TableModel tableModel;

    public ExcelSheet(String name, TableModel tableModel){

        this.name = name;
        this.tableModel = tableModel;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tableModel
     */
    public TableModel getTableModel() {
        return tableModel;
    }
}
