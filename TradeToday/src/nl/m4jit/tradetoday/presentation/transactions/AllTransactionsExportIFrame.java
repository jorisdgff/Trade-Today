package nl.m4jit.tradetoday.presentation.transactions;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.m4jit.framework.io.poi.excel.ExcelWriter;
import nl.m4jit.framework.presentation.swing.abstractdialogs.OkCancelDialog;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleTable;
import nl.m4jit.tradetoday.domainlogic.transactions.AllTransactionsExportModel;
import org.jdesktop.swingx.JXMonthView;

/**
 *
 * @author joris
 */
public class AllTransactionsExportIFrame extends OkCancelDialog{

    private JXMonthView startDateMV, endDateMV;
    
    public AllTransactionsExportIFrame(){
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        
        JLabel startDateLabel = new JLabel("Selecteer startdatum");
        centerPanel.add(startDateLabel);
        
        startDateMV = new JXMonthView();
        startDateMV.setSelectionDate(new Date());
        startDateMV.setTraversable(true);
        startDateMV.setShowingLeadingDays(true);
        startDateMV.setShowingTrailingDays(true);
        startDateMV.setOpaque(false);
        centerPanel.add(startDateMV);
        
        JLabel endDateLabel = new JLabel("Selecteer eindatum");
        centerPanel.add(endDateLabel);
        
        endDateMV = new JXMonthView();
        endDateMV.setSelectionDate(new Date());
        endDateMV.setTraversable(true);
        endDateMV.setShowingLeadingDays(true);
        endDateMV.setShowingTrailingDays(true);
        endDateMV.setOpaque(false);
        centerPanel.add(endDateMV);
        
        addToCenter(centerPanel);
        setUI();
    }
    
    @Override
    public void ok() {
        
        Date startDate = startDateMV.getSelectionDate();
        Date endDate = endDateMV.getSelectionDate();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel bestand", "xls"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        int returnval = fileChooser.showSaveDialog(this);

        if (returnval == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            String filename = file.toString() + ".xls";

            try {

                ExcelWriter writer = new ExcelWriter();

                List<TransactionruleGateway> rules = TransactionruleTable.getInstance().findByTimePeriod(startDate, endDate);
                writer.addTableSheet(new AllTransactionsExportModel(rules), "Alle transacties");
                writer.write(filename);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File(filename));
            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this, "Bestand kon niet worden weggeschreven\n" + ex);
            } 
        }
        
        dispose();
    }

    @Override
    public String getScreenTitle() {
        
        return "Alle transacties exporteren";
    }
}
