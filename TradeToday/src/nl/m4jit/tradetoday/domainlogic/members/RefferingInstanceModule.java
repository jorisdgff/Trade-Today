package nl.m4jit.tradetoday.domainlogic.members;

import nl.m4jit.framework.Util;
import nl.m4jit.framework.ValidationException;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class RefferingInstanceModule {

    private final RefferingInstanceTable table;
    private static RefferingInstanceModule instance;

    private RefferingInstanceModule() {

        table = RefferingInstanceTable.getInstance();
    }

    public RefferingInstanceGateway create(String name, String ordering) {

        if (!ordering.isEmpty() && !Util.isInt(ordering)) {

            throw new ValidationException("no number");
        }else if (name.isEmpty()) {

            throw new ValidationException("no name");
        } else {

            int orderingnumber = 0;
            
            if(!ordering.isEmpty()){
                
                orderingnumber = Integer.parseInt(ordering);
            }
            
            RefferingInstanceGateway refferingInstance = new RefferingInstanceGateway();
            refferingInstance.setName(name);
            refferingInstance.setOrdering(orderingnumber);
            table.insert(refferingInstance);
            
            return refferingInstance;
        }
    }

    public void update(RefferingInstanceGateway refferingInstance, String name, String ordering) {

        if (!ordering.isEmpty() && !Util.isInt(ordering)) {

            throw new ValidationException("no number");
        }else if (name.isEmpty()) {

            throw new ValidationException("no name");
        } else {

            int orderingnumber = 0;
            
            if(!ordering.isEmpty()){
                
                orderingnumber = Integer.parseInt(ordering);
            }
            
            refferingInstance.setName(name);
            refferingInstance.setOrdering(orderingnumber);
            table.update(refferingInstance);
        }
    }

    public static RefferingInstanceModule getInstance() {

        if (instance == null) {

            instance = new RefferingInstanceModule();
        }

        return instance;
    }
}
