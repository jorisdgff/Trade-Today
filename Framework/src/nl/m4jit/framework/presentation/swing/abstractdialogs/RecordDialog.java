package nl.m4jit.framework.presentation.swing.abstractdialogs;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.framework.presentation.swing.improvedcomponents.IFormattedTextField;
import nl.m4jit.framework.presentation.swing.improvedcomponents.ITable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public abstract class RecordDialog<D> extends OkCancelDialog implements FocusListener, ChangeListener{

    protected boolean isNew;
    protected JPanel centerPanel;
    protected int currentRow, currentColumn, cols;
    protected PanelBuilder formPanel;
    protected CellConstraints cc;
    protected HashMap<String, Component> components;
    protected HashMap<String, ButtonGroup> buttonGroups;
    protected JTabbedPane tabbedPane;
    protected Border border;
    
    protected D record;
    protected TableDialog<D> tableDialog;
    
    public RecordDialog(String[] colWidths, int rowCount){
        
        this(colWidths, rowCount, null, null, false);
    }

    public RecordDialog(String[] colWidths, int rowCount, boolean useAsFrame){
        
        this(colWidths, rowCount, null, null, useAsFrame);
    }
    
    public RecordDialog(String[] colWidths, int rowCount, TableDialog tableDialog) {

        this(colWidths, rowCount, null, tableDialog, false);
    }
    
    public RecordDialog(String[] colWidths, int rowCount, D record) {

        this(colWidths, rowCount, record, null, false);
    }

    public RecordDialog(String[] colWidths, int rowCount, D record, TableDialog tableDialog) {
        
        this(colWidths, rowCount, record, tableDialog, false);
    }
    
    public RecordDialog(String[] colWidths, int rowCount, D record, TableDialog tableDialog, boolean useAsFrame) {

        super(useAsFrame);
        
        this.record = record;
        this.tableDialog = tableDialog;
        this.isNew = record == null;
        
        centerPanel = new JPanel(new BorderLayout());

        currentColumn = 1;
        cols = colWidths.length;

        components = new HashMap<String, Component>();
        buttonGroups = new HashMap<String, ButtonGroup>();

        //ColSpecs worden opgesteld
        String colSpecs = "pref";

        for (String width : colWidths) {

            colSpecs += ",3dlu," + width;
        }

        //RowSpecs worden opgesteld
        currentRow = 1;
        String rowSpecs = "pref";

        for (int i = 1; i < rowCount; i++) {

            rowSpecs += ",10dlu,pref";
        }

        formPanel = new PanelBuilder(new FormLayout(colSpecs, rowSpecs));
        cc = new CellConstraints();

        border = new JTextField().getBorder();
    }

    @Override
    protected void setUI() {
        
        centerPanel.add(formPanel.getPanel(), BorderLayout.WEST);

        if (tabbedPane != null) {

            centerPanel.add(Box.createHorizontalStrut(15), BorderLayout.CENTER);
            centerPanel.add(tabbedPane, BorderLayout.EAST);

            Dimension d = tabbedPane.getPreferredSize();
            d.width = 450;
            tabbedPane.setPreferredSize(d);
        }

        addToCenter(centerPanel);
        
        super.setUI(); 
    }

    public D getRecord() {
    
        return record;
    }

    public void addLabel(String value) {

        formPanel.addLabel(value, cc.xy(1, currentRow));

        /*currentColumn = 1;
         currentRow += 2;*/
    }

    public void addSeparator(String text) {

        formPanel.addSeparator(text, cc.xyw(1, currentRow, cols * 2 + 1));
        currentRow += 2;
    }

    public void addComponent(Component component, String name, int width) {

        if (!(component instanceof JRadioButton)) {

            component.setName(name);
        }

        components.put(name, component);
        formPanel.add(component, cc.xyw(currentColumn * 2 + 1, currentRow, width * 2 - 1));
        currentColumn += width;

        if (currentColumn > cols) {

            currentColumn = 1;
            currentRow += 2;
        }
    }

    public void addFWComponent(Component component, String name) {

        component.setName(name);
        components.put(name, component);
        formPanel.add(component, cc.xyw(1, currentRow, cols * 2 + 1));

        currentColumn = 1;
        currentRow += 2;
    }

    public Component getComponent(String name) {

        return components.get(name);
    }

    public boolean setFocusOnComponent(String name) {

        return getComponent(name).requestFocusInWindow();
    }

    public Object getValue(String name) {

        return null;
    }
    
    public Collection getCollection(String name) {

        return null;
    }

    public IATableModel getTableModel(String name) {

        return null;
    }
    
    //Methoden voor textfields
    public JTextField addTextField(String name, int width) {

        String value = getValue(name) == null ? "" : "" + getValue(name);

        JTextField tempTextField = new JTextField(value);
        addComponent(tempTextField, name, width);
        return tempTextField;
    }
    
    public JPasswordField addPasswordField(String name, int width) {

        JPasswordField passwordField = new JPasswordField();
        addComponent(passwordField, name, width);
        return passwordField;
    }

    public JTextField addACTextField(String name, int width) {

        String value = getValue(name) == null ? "" : "" + getValue(name);
        JTextField tempTextField = new JTextField(value);
        addComponent(tempTextField, name, width);
        AutoCompleteDecorator.decorate(tempTextField, new ArrayList(getCollection(name)), false);
        return tempTextField;
    }

    //Methoden voor formatted textfields
    public JFormattedTextField addFormattedTextField(String name, Format format, int width) {

        IFormattedTextField tempFormattedTextField = new IFormattedTextField(format);
        tempFormattedTextField.setValue(getValue(name));
        addComponent(tempFormattedTextField, name, width);
        return tempFormattedTextField;
    }

    public Object getFormattedTextFieldValue(String name) {

        JFormattedTextField formattedTextField = (JFormattedTextField) getComponent(name);
        return formattedTextField.getValue();
    }

    //Methoden voor textpanes
    public JTextPane addTextPane(String name, int width, int height) {

        String value = getValue(name) == null ? "" : "" + getValue(name);

        JTextPane tempTextPane = new JTextPane();
        tempTextPane.setText(value);
        tempTextPane.setPreferredSize(new Dimension(0, height));
        tempTextPane.setBorder(border);
        addComponent(tempTextPane, name, width);

        return tempTextPane;
    }

    //Get methoden voor text values
    public String getTextValue(String name) {

        JTextComponent textComponent = (JTextComponent) getComponent(name);
        return textComponent.getText();
    }

    //Set methoden voor text values
    public void setTextValue(String name, String text) {

        JTextComponent textComponent = (JTextComponent) getComponent(name);
        textComponent.setText(text);
    }

    //Methoden voor checkboxes
    public JCheckBox addCheckBox(String name, int width) {

        boolean isSelected = false;
        Object o = getValue(name);
        
        if(o != null){
            
            isSelected = (Boolean) o;
        }
        
        JCheckBox tempCheckBox = new JCheckBox("", isSelected);
        addComponent(tempCheckBox, name, width);
        return tempCheckBox;
    }

    public boolean getCheckBoxValue(String name) {

        JCheckBox checkBox = (JCheckBox) getComponent(name);
        return checkBox.isSelected();
    }

    //Methoden voor comboboxen
    public JComboBox addComboBox(String name, int width) {

        JComboBox tempComboBox = new JComboBox(getCollection(name).toArray());
        tempComboBox.setSelectedItem(getValue(name));
        addComponent(tempComboBox, name, width);
        return tempComboBox;
    }

    public Object getSelectedComboBoxItem(String name) {

        JComboBox comboBox = (JComboBox) getComponent(name);
        return comboBox.getSelectedItem();
    }

    //Methoden voor radiobuttons
    public JRadioButton addRadioButton(String buttongroup, String name, String text, int width) {

        JRadioButton tempRadioButton = new JRadioButton(text);
        tempRadioButton.setName(name);
        
        Object selectedvalue = getValue(buttongroup);
        
        if(selectedvalue != null){
            
            tempRadioButton.setSelected(selectedvalue.equals(name));
        }
        
        addComponent(tempRadioButton, buttongroup + ":" + name, width);

        ButtonGroup bg = buttonGroups.get(buttongroup);

        if (bg == null) { //buttongroup bestaat nog niet

            bg = new ButtonGroup();
            buttonGroups.put(buttongroup, bg);
        }

        bg.add(tempRadioButton);
        return tempRadioButton;
    }

    public String getButtonGroupValue(String name) {

        return getButtonGroupValue(name, "");
    }

    /**
     *
     * @param name de naam van de buttongroup
     * @param noval wat moet er teruggegeven worden als er niks is geselecteerd
     * @return
     */
    public String getButtonGroupValue(String name, String noval) {

        ButtonGroup bg = buttonGroups.get(name);
        Enumeration<AbstractButton> buttons = bg.getElements();

        while (buttons.hasMoreElements()) {

            JRadioButton rb = (JRadioButton) buttons.nextElement();

            if (rb.isSelected()) {

                return rb.getName();
            }
        }

        return noval;
    }

    //Methoden voor tables
    public ITable addTable(String name) {

        ITable tempTable = new ITable(getTableModel(name));
        addFWComponent(tempTable.getPane(), name);
        return tempTable;
    }

    //Methoden voor datepickers
    public JDatePickerImpl addDatePicker(String name, int width, boolean enabled){
        
        GregorianCalendar date = new GregorianCalendar();
        Object time = getValue(name);

        if (time != null) {

            date.setTime((Date) time);
        }

        UtilCalendarModel calendarmodel = new UtilCalendarModel(date);
        calendarmodel.addChangeListener(this);

        JDatePanelImpl datepanel = new JDatePanelImpl(calendarmodel);

        JDatePickerImpl temp = new JDatePickerImpl(datepanel);
        temp.setEditable(enabled);
        
        addComponent(temp, name, width);
        
        return temp;
    }
    
    public Object getDatepickerModel(String name) {

        JDatePickerImpl datepicker = (JDatePickerImpl) getComponent(name);
        return datepicker.getModel();
    }
    
    public Date getDatepickerValue(String name){
        
        JDatePickerImpl datepicker = (JDatePickerImpl) getComponent(name);
        GregorianCalendar calendar = (GregorianCalendar) datepicker.getJFormattedTextField().getValue();
        return calendar.getTime();
    }
    
    //Methoden voor extra panels
    public ContentDialog addPanel(ContentDialog dialog) {

        if (tabbedPane == null) {

            tabbedPane = new JTabbedPane();
        }

        tabbedPane.add(dialog.getScreenTitle(), dialog.getContentPane());
        
        return dialog;
    }
    
    public void giveMessageAndSetFocus(String message, String component){
        
        JOptionPane.showMessageDialog(this, message);
        setFocusOnComponent(component);
        throw new ValidationException("");
    }

    @Override
    public String getScreenTitle() {
        
        return getScreenTitle(isNew);
    }
    
    public abstract String getScreenTitle(boolean isNew);
    
    public abstract String getNotificationMessge(boolean isnew);
    
    @Override
    public void ok() {

        if (isNew) {

            create();
            
            if(tableDialog != null){
             
                tableDialog.addRecord(record);
            }
        } else {

            update();
            
            if(tableDialog != null){
             
                tableDialog.updateTable();
            }
        }

        dispose();
        MainDialog.giveNotification(getNotificationMessge(isNew));
    }
    
    protected abstract D create();
    
    protected abstract void update();
    
    public void focusGained(FocusEvent e) {

        focusGainedEvent(e.getComponent().getName());
    }

    public void focusLost(FocusEvent e) {

        focusLostEvent(e.getComponent().getName());
    }

    public void focusGainedEvent(String name) {
    }

    public void focusLostEvent(String name) {
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        
        stateChangeEvent(ce.getSource());
    }
    
    public void stateChangeEvent(Object source){
        
    }
}