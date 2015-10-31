package nl.m4jit.tradetoday.domainlogic;

import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import java.util.List;

/**
 *
 * @author joris
 */
public interface UIMethods {
 
    public void showMemberResult(List<MemberGateway> members);
    
    public void showMember(MemberGateway member);
    
    public void showMessage(String message);
}