package nl.m4jit.framework;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class IComboBoxModel<D> implements ComboBoxModel{

    private List<D> records;
    private D selectedItem;
    
    public IComboBoxModel(List<D> records){
    
        this.records = records;
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        
        selectedItem = (D) anItem;
    }

    @Override
    public Object getSelectedItem() {
        
        return selectedItem;
    }

    @Override
    public int getSize() {
        
        return records.size();
    }

    @Override
    public Object getElementAt(int index) {
        
        return records.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
}