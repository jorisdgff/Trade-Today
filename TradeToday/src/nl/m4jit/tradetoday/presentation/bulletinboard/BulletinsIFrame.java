package nl.m4jit.tradetoday.presentation.bulletinboard;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.bulletinboard.BulletinGateway;
import nl.m4jit.tradetoday.domainlogic.bulletinboard.BulletinsModel;

public class BulletinsIFrame extends TableDialog<BulletinGateway>{

    public BulletinsIFrame(){

        super(new BulletinsModel());
        
        enableChange();
        addButton("Toon lid", "showmember", RIGHT);
        setUI();
    }
    
    @Override
    protected void changeRecord(BulletinGateway record) {
    
        new BulletinIFrame(record);
    }

    @Override
    protected void processUnknowActionCommand(String ac) {
        
        if(ac.equals("showmember")){
            
            
        }
    }

    @Override
    public String getScreenTitle() {
        
        return "Prikbord items";
    }
}