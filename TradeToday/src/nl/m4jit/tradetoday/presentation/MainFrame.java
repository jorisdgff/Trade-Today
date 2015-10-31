package nl.m4jit.tradetoday.presentation;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.m4jit.framework.presentation.swing.abstractdialogs.MainDialog;
import nl.m4jit.tradetoday.dataaccess.custom.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.domainlogic.*;
import nl.m4jit.tradetoday.domainlogic.members.*;
import nl.m4jit.tradetoday.presentation.bulletinboard.*;
import nl.m4jit.tradetoday.presentation.loanservice.*;
import nl.m4jit.tradetoday.presentation.members.*;
import nl.m4jit.tradetoday.presentation.members.choicelists.*;
import nl.m4jit.tradetoday.presentation.nhcjobs.*;
import nl.m4jit.tradetoday.presentation.nhcjobs.iframes.*;
import nl.m4jit.tradetoday.presentation.nhcjobs.types.*;
import nl.m4jit.tradetoday.presentation.statistics.*;
import nl.m4jit.tradetoday.presentation.transactions.*;
import nl.m4jit.tradetoday.presentation.users.*;

public class MainFrame extends MainDialog implements UIMethods {

    public MainFrame() {

        addMenus();
        addButtons();

        setUI();
    }

    private void addMenus() {

        addMenu("Bestand");
        addMenuItem("Standaard datum wijzigen", "changedefdate");
        addMenuItem("Wachtwoord wijzigen", "changepassword");
        addMenuItem("Statistieken tonen", "showstatistics");
        
        if(userCanManage()){
            
            addMenuItem("Gebruikers beheren", "manageusers");
            addMenuItem("Lidactivatie reseten", "resetmemberactivation");
            //addMenuItem("Programma updaten", "updateprogram");
        }
        
        addMenuItem("Programma afsluiten", "close");

        addMenu("Leden");
        addMenuItem("Lid<br>invoeren", "newmember");
        addMenuItem("Lid<br>tonen", "changemember");
        addMenuItem("Leden exporteren", "exportmembers");
        addMenuSeparator();
        addMenuItem("Inkomstenbronnen beheren", "manageincometypes");
        addMenuItem("Participatieniveaus beheren", "manageparticipationlevels");
        addMenuItem("Gehoord via typen beheren", "manageheardoftypes");
        addMenuItem("Verwijzende instanties beheren", "managerefferinginstances");

        addMenu("Transacties");
        addMenuItem("Inname invoeren", "newtransactionin");
        addMenuItem("Uitgifte invoeren", "newtransactionout");
        addMenuItem("Dienst<br>verlening invoeren", "newservice");
        addMenuItem("Innames exporteren", "exportclothestransactions");
        addMenuItem("Alle transacties exporteren", "exportalltransactions");

        if (userCanManage()) {

            /*addMenu("Prikbord");
            addMenuItem("Prikborditem aanmaken", "addbulletin");
            addMenuItem("Prikborditems tonen", "showbulletins");*/

            addMenu("Klussendienst");
            addMenuItem("Klus aanmaken", "addnhcjob");
            addMenuItem("Klussen tonen", "shownhcjobs");
            addMenuSeparator();
            addMenuItem("Klustypen beheren", "managejobtypes");

            addMenu("Uitleningen");
            addMenuItem("Uitlening invoeren", "newletting");
            addMenuItem("Uitleningen tonen", "showcurrentlettings");
            addMenuItem("Goederen beheren", "manageloanitems");
        }

        addMenu("Help");
        addMenuItem("Handleiding weergeven", "showmanual");
        addMenuItem("Over", "showabout");
    }

    private void addButtons() {
        
        boolean userCanManage = Application.getInstance().getAccount().getRights() == 'A';

        addMenuItemToButtonBar("newmember", NORTH);
        addButtonBarSeparator(NORTH);
        addMenuItemToButtonBar("showstatistics", NORTH);
        addButtonBarSeparator(NORTH);
        addMenuItemToButtonBar("newservice", NORTH);
        addMenuItemToButtonBar("newtransactionin", NORTH);

        addMenuItemToButtonBar("newtransactionout", EAST);
        addButtonBarSeparator(EAST);
        addMenuItemToButtonBar("addbulletin", EAST);

        addMenuItemToButtonBar("showcurrentlettings", SOUTH);
        addButtonBarSeparator(SOUTH);
        addMenuItemToButtonBar("addnhcjob", SOUTH);
        addMenuItemToButtonBar("shownhcjobs", SOUTH);
        addButtonBarSeparator(SOUTH);
        addMenuItemToButtonBar("showbulletins", SOUTH);

        addMenuItemToButtonBar("changemember", WEST);
        addButtonBarSeparator(WEST);
        addMenuItemToButtonBar("newletting", WEST);
        
        if (!userCanManage) {
        
            addMenuItemToButtonBar("close", SOUTH);
        }
    }

    @Override
    public String getScreenTitle() {

        return "Trade Today";
    }

    @Override
    protected void processActionCommand(String ac) {

        if (ac.equals("changedefdate")) {

            new DefDateIFrame();
        } else if (ac.equals("showstatistics")) {

            new StatisticsIFrame();
        } else if (ac.equals("newmember")) {

            
            newMember();
        } else if (ac.equals("changemember")) {

            findMember();
        } else if (ac.equals("exportmembers")) {

            exportMembers();
        } else if (ac.equals("newtransactionin")) {

            new TransactionIFrame(true);
        } else if (ac.equals("newtransactionout")) {

            new TransactionIFrame(false);
        } else if (ac.equals("newservice")) {

            new ServiceTransactionIFrame();
        } else if (ac.equals("exportclothestransactions")) {

            new ClothesTransactionsExportIFrame();
        } else if (ac.equals("exportalltransactions")) {

            new AllTransactionsExportIFrame();
        } else if (ac.equals("manageloanitems")) {

            new LoanItemsManagementIFrame();
        } else if (ac.equals("newletting")) {

            new LettingIFrame();
        } else if (ac.equals("showcurrentlettings")) {

            new LettingsIFrame();
        } else if (ac.equals("manageincometypes")) {

            new IncomeTypesIFrame();
        } else if (ac.equals("manageparticipationlevels")) {

            new ParticipationLevelsIFrame();
        } else if (ac.equals("manageheardoftypes")) {

            new HeardOfTypesIFrame();
        } else if (ac.equals("managerefferinginstances")) {

            new RefferingInstancesIFrame();
        } else if (ac.equals("manageusers")) {

            new AccountsIFrame();
        } else if (ac.equals("changepassword")) {

            new ChangePasswordIFrame();
        } else if (ac.equals("updateprogram")) {

            updateProgram();
        } else if (ac.equals("addnhcjob")) {

            new AddNHCJobIFrame();
        } else if (ac.equals("shownhcjobs")) {

            new NHCJobsIFrame();
        } else if (ac.equals("managejobtypes")) {

            new NHCJobTypesIFrame();
        } else if (ac.equals("addbulletin")) {

            new BulletinIFrame();
        } else if (ac.equals("showbulletins")) {

            new BulletinsIFrame();
        }else if(ac.equals("resetmemberactivation")){
            
            PropertyGateway paddingmember = PropertyTable.getInstance().retreive("addingmember");
            paddingmember.setValue(0);
            PropertyTable.getInstance().update(paddingmember);
        }
    }

    public void newMember(){
        
        PropertyGateway paddingmember = PropertyTable.getInstance().retreive("addingmember");
        int addingmember = Integer.parseInt(paddingmember.getValue());
        
        if(addingmember == 0){
            
            paddingmember.setValue(1);
            PropertyTable.getInstance().update(paddingmember);
            new MemberIFrame();
        }else{
             
            showMessage("Er kan maar een lid tegelijk ingevoerd worden.");
        }
    }
    
    public void findMember() {

        String input = JOptionPane.showInputDialog(null, "Geef lidnummer/naam/geboortedatum op");

        if (input != null) {

            MemberModule.findMember(input, this);
        }
    }

    private void exportMembers() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel bestand", "xls"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        int returnval = fileChooser.showSaveDialog(this);

        if (returnval == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            MemberModule.exportMembers(file, this);
        }
    }

    @Override
    protected String getAboutMessage() {

        return "Trade Today is,\nin opdracht van de Ruilwinkel in Goes,\nontwikkeld door M4J - IT.";
    }

    public boolean userCanManage() {

        return Application.getInstance().getAccount().getRights() == 'A';
    }

    @Override
    public void showMemberResult(List<MemberGateway> members) {

        new MembersDialog(members, this);
    }

    @Override
    public void showMember(MemberGateway member) {

        new MemberIFrame(member, this);
    }

    @Override
    public void showMessage(String message) {

        JOptionPane.showMessageDialog(null, message);
    }
}
