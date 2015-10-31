package nl.m4jit.tradetoday.domainlogic.loanservice;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;

public class LettingModel extends IATableModel<LettingruleGateway>{

    private ArrayList<LettingruleGateway> lettingRules;
    
    public LettingModel(ArrayList<LettingruleGateway> lettingRules){

        super(new String[]{"Gereedschap"}, new Class[]{String.class}, new boolean[]{true});
        this.lettingRules = lettingRules;
    }

    @Override
    public Object getColValue(LettingruleGateway rule, int col) {

        LoanItemGateway item = rule.getItem();

        if(item != null){

            return item;
        }else{

            return "";
        }
    }

    @Override
    protected void setColValue(Object value, LettingruleGateway rule, int col) {

        int toolnumber = Integer.parseInt("" + value);

        LoanItemGateway item = LoanItemTable.getInstance().retreive(toolnumber);
        rule.setItem(item);
    }

    @Override
    protected List<LettingruleGateway> getItems(Object filter) {
        
        return lettingRules;
    }
}