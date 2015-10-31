package nl.m4jit.framework.io.poi.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

public class ExcelWriter {

    private HSSFWorkbook workbook;
    private ArrayList<HSSFSheet> sheets;

    public ExcelWriter() {

        workbook = new HSSFWorkbook();
        sheets = new ArrayList<HSSFSheet>();
    }

    public void addTableSheet(TableModel model, String name) {

        HSSFSheet sheet = workbook.createSheet(name);

        HSSFCellStyle boldstyle = workbook.createCellStyle();
        HSSFFont boldfont = workbook.createFont();
        boldfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        boldstyle.setFont(boldfont);

        HSSFRow row = sheet.createRow(0);

        for (int colnum = 0; colnum < model.getColumnCount(); colnum++) {

            HSSFCell cell = row.createCell(colnum);
            cell.setCellValue(model.getColumnName(colnum));
            cell.setCellStyle(boldstyle);
        }

        for (int rownum = 0; rownum < model.getRowCount(); rownum++) {

            row = sheet.createRow(rownum + 1);

            for (int colnum = 0; colnum < model.getColumnCount(); colnum++) {

                HSSFCell cell = row.createCell(colnum);

                Object o = model.getValueAt(rownum, colnum);

                if (o != null) {

                    cell.setCellValue(o.toString());
                } else {

                    cell.setCellValue("");
                }
            }
        }

        sheets.add(sheet);
    }

    public void write(String filename) throws IOException {

        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();
    }
}
