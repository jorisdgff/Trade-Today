package nl.m4jit.tradetoday.presentation.members;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import nl.m4jit.framework.presentation.swing.abstractdialogs.ContentDialog;
import nl.m4jit.framework.presentation.swing.improvedcomponents.IList;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.domainlogic.UIMethods;

public class MembersDialog extends ContentDialog implements MouseListener{

    private IList membersList;
    private UIMethods gui;
    
    public MembersDialog(Collection<MemberGateway> members, UIMethods gui){

        this.gui = gui;

        membersList = new IList(members);
        membersList.addMouseListener(this);
        addToCenter(membersList.getPane());

        setUI();
    }

    public Object getSelectedItem(){

        return membersList.getSelectedValue();
    }
    
    @Override
    public String getScreenTitle() {

        return "Selecteer lid";
    }

    public void mouseClicked(MouseEvent ev) {

        if (ev.getClickCount() == 2) {

            MemberGateway member = (MemberGateway) getSelectedItem();
            gui.showMember(member);
            dispose();
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
