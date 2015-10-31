package nl.m4jit.tradetoday.presentation.nhcjobs.iframes;

import java.util.Collection;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import nl.m4jit.framework.presentation.swing.abstractdialogs.RecordDialog;
import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobGateway;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobTypeGateway;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobTypeTable;

public abstract class AbstractNHCJobIFrame extends RecordDialog<NHCJobGateway> implements ChangeListener {

    private TableDialog nextdialog;
    
    public AbstractNHCJobIFrame(int rowcount, NHCJobGateway job, TableDialog dialog, TableDialog nextdialog) {

        super(new String[]{"25dlu", "75dlu"}, rowcount + 5, job, dialog);
        this.nextdialog = nextdialog;
        addStartSection();
    }

    private void addStartSection() {

        //Lid
        addLabel("Lid:");
        JTextField numberfield = addTextField("membernumber", 1);
        numberfield.setEditable(isNew);

        JTextField nameField = addTextField("membername", 1);
        nameField.setEditable(false);
        nameField.addFocusListener(this);

        addLabel("Type");
        JComboBox typeBox = addComboBox("type", 2);
        typeBox.setEnabled(isNew);

        //Omschrijving
        addLabel("Omschrijving:");
        JTextPane descriptionpane = addTextPane("description", 2, 100);
        descriptionpane.setEditable(isNew);

        addLabel("Akkoord voorwaarden:");
        JCheckBox agreeBox = addCheckBox("agree", 2);
        agreeBox.setEnabled(isNew);

        //Aanmaakdatum
        addLabel("Aanmaakdatum:");
        addDatePicker("creationdate", 2, isNew);
    }

    protected void doAction(boolean isNew) {
        
        save();
        
        if(tableDialog != null){
            
            tableDialog.removeRecord(record);
        }
        
        if(nextdialog != null){
            
            nextdialog.updateTable();
        }
        
        dispose();
    }

    @Override
    protected NHCJobGateway create() {
        
        doAction(true);
        return null;
    }

    @Override
    protected void update() {
        
        doAction(true);
    }
    
    protected abstract void save();
    
    @Override
    public Collection getCollection(String name) {

        return NHCJobTypeTable.getInstance().findAll();
    }
}