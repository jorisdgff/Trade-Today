package nl.m4jit.tradetoday.domainlogic.members;

import java.text.*;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.members.*;



public class MemberModel extends IATableModel<MemberGateway> {

    private HashMap<Integer, Long> purchasenumbers;

    public MemberModel() {

        super(new String[]{
                    "Nummer", //0
                    "Saldo", //1
                    "Geslacht", //2
                    "Voorletters", //3
                    "Voornaam", //4
                    "Tussenvoegsel", //5
                    "Achternaam", //6
                    "Straat", //7
                    "Huisnummer", //8
                    "Postcode", //9
                    "Plaats", //10
                    "Geboortedatum", //11
                    "Telefoon", //12
                    "Mobiel", //13
                    "E-mail", //14
                    "Inkomsten", //15
                    "Participatiniveau", //16
                    "Gehoord via", //17
                    "Doorverwezen door", //18
                    "ID getoond", //19
                    "Inschrijven nieuwsbrief", //20
                    "Akkoord lidmaatschapsvoorwaarden", //21
                    "Datum inschrijving", //22
                    "Datum uitschrijving", //23
                    "Extra", //24
                    //"Aantal aankopen" //25
                }, null);

        
        
        /*purchasenumbers = new HashMap<Integer, Long>();
        
        String sql = "select NumberMember, count(*) from tblTransaction where Type = 'O' group by NumberMember";
        
        pm.prepareNativeQuery(sql);
        List result = pm.runQuery();
        
        for (Object o : result) {

            Object[] os = (Object[]) o;
            int membernumber = (Integer) os[0];
            long purchasenumber = (Long) os[1];
            purchasenumbers.put(membernumber, purchasenumber);
        }*/
    }

    public String getColValue(MemberGateway member, int col) {

        switch (col) {

            case 0:
                return member.getNumber() + "";

            case 1:
                return member.getBalance() + "";

            case 2:
                return getSex(member);

            case 3:
                return member.getInitials();

            case 4:
                return member.getFirstname();

            case 5:
                return member.getPrefix();

            case 6:
                return member.getLastname();

            case 7:
                return member.getStreet();

            case 8:
                return member.getHousenumber();

            case 9:
                return member.getPostalcode();

            case 10:
                return member.getPlace();

            case 11:
                
                if(member.getBirthdate() != null){
                    
                    return DateFormat.getDateInstance().format(member.getBirthdate());
                }else{
                    
                    return "";
                }
                
            case 12:
                return member.getTelephoneHome();

            case 13:
                return member.getTelephoneMobile();

            case 14:
                return member.getEmail();

            case 15:
                return getIncomeType(member);

            case 16:
                return getParticipationLevel(member);

            case 17:
                return getHeardOfType(member);

            case 18:
                return getRefferingInstanceType(member);

            case 19:
                return getYesNo(member.getIdChecked());

            case 20:
                return getYesNo(member.getNewsletter());

            case 21:
                return getYesNo(member.getAgree());

            case 22:
                
                if(member.getRegistrationdate() != null){
                    
                    return DateFormat.getDateInstance().format(member.getRegistrationdate());
                }else{
                    
                    return "";
                }
                
            case 23:
                
                if(member.getDeregistrationdate() != null){
                    
                    return DateFormat.getDateInstance().format(member.getDeregistrationdate());
                }else{
                    
                    return "";
                }
                
            case 24:
                return member.getExtra();

            case 25:
                return getPurchaseCount(member) + "";
                
            default:
                return null;
        }
    }

    private long getPurchaseCount(MemberGateway member){
     
        if(purchasenumbers.containsKey(member.getNumber())){
            
            return purchasenumbers.get(member.getNumber());
        }else{

            return 0;
        }
    }
    
    private String getSex(MemberGateway member) {

        if (member.getSex().equals("M")) {

            return "Man";
        } else if (member.getSex().equals("F")) {

            return "Vrouw";
        } else {

            return "Onbekend";
        }
    }

    private String getIncomeType(MemberGateway member) {

        if (member.getIncomeType() != null) {

            return member.getIncomeType().getName();
        } else {

            return "Onbekend";
        }
    }

    private String getParticipationLevel(MemberGateway member) {

        if (member.getParticipationLevel() != null) {

            return member.getParticipationLevel().getName();
        } else {

            return "Onbekend";
        }
    }

    private String getHeardOfType(MemberGateway member) {

        if (member.getHeardOfType() != null) {

            return member.getHeardOfType().getName();
        } else {

            return "Onbekend";
        }
    }

    private String getRefferingInstanceType(MemberGateway member) {

        if (member.getRefferingInstance() != null) {

            return member.getRefferingInstance().getName();
        } else {

            return "Onbekend";
        }
    }

    private String getYesNo(boolean yn) {

        if (yn) {

            return "Ja";
        } else {

            return "Nee";
        }
    }

    @Override
    protected List<MemberGateway> getItems(Object filter) {
        
        return MemberTable.getInstance().findAll();
    }
}
