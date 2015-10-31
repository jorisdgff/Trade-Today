package nl.m4jit.framework.presentation.swing.abstractdialogs;

import java.awt.Window;


/**
 *
 * @author Joris
 */
public abstract class OkCancelDialog extends ButtonBarDialog{
 
    public OkCancelDialog(){
        
        this(false);
    }
    
    public OkCancelDialog(boolean useAsFrame){
        
        super(useAsFrame);
        addButton("Ok", "ok", RIGHT);
        addButton("Annuleren", "cancel", RIGHT);
    }
    
    @Override
    protected void processActionCommand(String ac) {

        if(ac.equals("ok")){

            ok();
        }else if(ac.equals("cancel")){

            cancel();
        }else{

            processUnknownActionCommand(ac);
        }
    }
    
    public abstract void ok();

    public void cancel(){

        dispose();
    }

    protected void processUnknownActionCommand(String ac) {

    }
}