package nl.m4jit.framework;

import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Joris
 */
public abstract class IATableModel<T> implements TableModel {

    protected List<T> items;
    protected List filteritems;
    protected Object filterstate;
    protected String[] header;
    protected Class[] classes;
    protected boolean[] editable;
    protected int[] minColWidths;
    

    public IATableModel(String[] header, Class[] classes){
     
        this(header, classes, null, null, true);
    }
    
    public IATableModel(String[] header, Class[] classes, boolean ddr){
     
        this(header, classes, null, null, ddr);
    }
    
    public IATableModel(String[] header, Class[] classes, boolean[] editable){
     
        this(header, classes, editable, null, true);
    }
    
    public IATableModel(String[] header, Class[] classes, boolean[] editable, boolean ddr){
     
        this(header, classes, editable, null, ddr);
    }
    
    public IATableModel(String[] header, Class[] classes, int[] minColWidths){
     
        this(header, classes, null, minColWidths, true);
    }
    
    public IATableModel(String[] header, Class[] classes, int[] minColWidths, boolean ddr){
     
        this(header, classes, null, minColWidths, ddr);
    }
    
    public IATableModel(String[] header, Class[] classes, boolean[] editable, int[] minColWidths, boolean ddr){
        
        this.header = header;
        this.classes = classes;
        this.editable = editable;
        this.minColWidths = minColWidths;
        
        if(ddr){
            
            retreiveData();
        }
    }
    
    protected void retreiveData(){
        
        this.filteritems = getFilterItems();
        
        if(filteritems == null){//Geen filter
            
            this.items = getItems(null);
        }else if(filteritems.isEmpty()){ //Lege filter
            
            this.items = new ArrayList<T>();
        }else{ //Filter
            
            filterstate = filteritems.get(0);
            this.items = getItems(filterstate);
        }
    }
    
    public void updateData(){
        
        this.items = getItems(filterstate);
    }
    
    protected abstract List<T> getItems(Object filter);
    
    protected List getFilterItems(){
        
        return null;
    }
    
    public T getItem(int index) {
        
        return items.get(index);
    }
    
    public void addItem(T item){
     
        if(item != null){
            
            items.add(item);
        }
    }
    
    public void removeItem(T item){
        
        items.remove(item);
    }

    public List getFilteritems() {
    
        return filteritems;
    }

    public Object getFilterstate() {
    
        return filterstate;
    }

    public void setFilterstate(Object filterstate) {
    
        this.filterstate = filterstate;
        this.items = getItems(filterstate);
    }
    
    public int[] getMinColWidths() {

        return minColWidths;
    }
    
    //TableModel implementatie
    
    @Override
    public int getRowCount() {

        return items.size();
    }

    @Override
    public int getColumnCount() {

        return header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return header[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        return classes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        if (editable == null) {

            return false;
        } else {

            return editable[columnIndex];
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return getColValue(items.get(rowIndex), columnIndex);
    }

    public abstract Object getColValue(T item, int columnIndex);

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        T item = items.get(rowIndex);
        setColValue(aValue, item, columnIndex);
    }

    protected void setColValue(Object aValue, T item, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
        
    }

    
}