package nl.m4jit.tradetoday.domainlogic.statistics;


import java.util.Vector;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joris
 */
public class ResultSetModel extends DefaultTableModel {

    private Vector columnNames, rows;

    public void setResultSet(ResultSet resultset){

        try {

            ResultSetMetaData metaData = resultset.getMetaData();

            int numberOfColumns = metaData.getColumnCount();
            columnNames = new Vector();

            for (int column = 0; column < numberOfColumns; column++) {

                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }

            rows = new Vector();

            while (resultset.next()) {

                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {

                    newRow.addElement(resultset.getObject(i));
                }

                rows.addElement(newRow);
            }

            setDataVector(rows, columnNames);
        } catch (SQLException ex) {

            //TODO opvangen
        }
    }

    public DefaultTableModel copy(){

        return new DefaultTableModel(rows, columnNames);
    }
}