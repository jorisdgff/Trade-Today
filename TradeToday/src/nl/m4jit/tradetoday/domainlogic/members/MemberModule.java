package nl.m4jit.tradetoday.domainlogic.members;

import java.awt.Desktop;
import java.io.*;
import java.text.*;
import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.framework.io.poi.excel.ExcelWriter;
import nl.m4jit.tradetoday.dataaccess.custom.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.users.*;
import nl.m4jit.tradetoday.domainlogic.Application;
import nl.m4jit.tradetoday.domainlogic.UIMethods;
import nl.m4jit.tradetoday.domainlogic.transactions.*;

public class MemberModule {

    private final MemberTable table;
    private int startpoints;
    private static MemberModule instance;

    private MemberModule() {

        table = MemberTable.getInstance();
        startpoints = Integer.parseInt(PropertyTable.getInstance().retreive("startpoints").getValue());
    }

    private void validate(String sex, String initials, String lastname, String street, String housenumber, String postalcode, String place,
            Date birthdate, Date registrationdate, HeardOfTypeGateway heardOfType, RefferingInstanceGateway refferingInstance) {

        Date today = new Date();

        if (sex.equals("U")) {

            throw new ValidationException("no sex");
        } else if (initials.isEmpty()) {

            throw new ValidationException("no initials");
        } else if (lastname.isEmpty()) {

            throw new ValidationException("no lastname");
        } else if (street.isEmpty()) {

            throw new ValidationException("no street");
        } else if (housenumber.isEmpty()) {

            throw new ValidationException("no housenumber");
        } else if (postalcode.isEmpty()) {

            throw new ValidationException("no postalcode");
        } else if (place.isEmpty()) {

            throw new ValidationException("no place");
        } else if (birthdate == null) {

            throw new ValidationException("no birthdate");
        } else if (birthdate.after(today)) {

            throw new ValidationException("future birthdate");
        } else if (registrationdate.after(today)) {

            throw new ValidationException("future registrationdate");
        } else if (heardOfType != null && heardOfType.getRefferingInstanceNeeded() && refferingInstance == null) {

            throw new ValidationException("refferinginstance mandatory");
        }
    }

    public MemberGateway create(int number, String initials, String firstname, String prefix, String lastname, String sex, String street, String housenumber,
            String postalcode, String place, String telephoneHome, String telephoneMobile, String email, Date birthdate, Date registrationdate,
            IncomeTypeGateway incomeType, ParticipationLevelGateway participationLevel, HeardOfTypeGateway heardOfType,
            RefferingInstanceGateway refferingInstance, boolean idChecked, boolean newsletter, boolean agree, String extra) {

        validate(sex, initials, lastname, street, housenumber, postalcode, place, birthdate, registrationdate, heardOfType, refferingInstance);

        UseraccountGateway controller = Application.getInstance().getAccount();
        UseraccountGateway processor = Application.getInstance().getAccount();

        MemberGateway member = new MemberGateway();
        member.setInitials(initials);
        member.setFirstname(firstname);
        member.setPrefix(prefix);
        member.setLastname(lastname);
        member.setSex(sex);
        member.setStreet(street);
        member.setHousenumber(housenumber);
        member.setPostalcode(postalcode);
        member.setPlace(place);
        member.setTelephoneHome(telephoneHome);
        member.setTelephoneMobile(telephoneMobile);
        member.setEmail(email);
        member.setBirthdate(birthdate);
        member.setNumber(number);
        member.setRegistrationdate(registrationdate);
        member.setIncomeType(incomeType);
        member.setParticipationLevel(participationLevel);
        member.setHeardOfType(heardOfType);
        member.setRefferingInstance(refferingInstance);
        member.setIdChecked(idChecked);
        member.setNewsletter(newsletter);
        member.setAgree(agree);
        member.setController(controller);
        member.setProcessor(processor);
        member.setExtra(extra);
        table.insert(member);

        TransactionModule.getInstance().makeSimpleTransaction(null, member, startpoints, "O", "Startpunten", registrationdate);

        return member;
    }

    public void update(MemberGateway member, String initials, String firstname, String prefix, String lastname, String sex, String street, String housenumber,
            String postalcode, String place, String telephoneHome, String telephoneMobile, String email, Date birthdate, Date registrationdate,
            Date deregistrationdate, IncomeTypeGateway incomeType, ParticipationLevelGateway participationLevel, HeardOfTypeGateway heardOfType,
            RefferingInstanceGateway refferingInstance, boolean idChecked, boolean newsletter, boolean agree, String extra) {

        validate(sex, initials, lastname, street, housenumber, postalcode, place, birthdate, registrationdate, heardOfType, refferingInstance);

        member.setInitials(initials);
        member.setFirstname(firstname);
        member.setPrefix(prefix);
        member.setLastname(lastname);
        member.setSex(sex);
        member.setStreet(street);
        member.setHousenumber(housenumber);
        member.setPostalcode(postalcode);
        member.setPlace(place);
        member.setTelephoneHome(telephoneHome);
        member.setTelephoneMobile(telephoneMobile);
        member.setEmail(email);
        member.setBirthdate(birthdate);
        member.setRegistrationdate(registrationdate);
        member.setDeregistrationdate(deregistrationdate);
        member.setIncomeType(incomeType);
        member.setParticipationLevel(participationLevel);
        member.setHeardOfType(heardOfType);
        member.setRefferingInstance(refferingInstance);
        member.setIdChecked(idChecked);
        member.setNewsletter(newsletter);
        member.setAgree(agree);
        member.setExtra(extra);
        table.update(member);
    }

    public int getStartpoints() {

        return startpoints;
    }

    public static String getFullname(MemberGateway member) {

        String initials = member.getInitials();
        String firstname = member.getFirstname();
        String prefix = member.getPrefix();
        String lastname = member.getLastname();

        if (firstname != null && !firstname.equals("")) {

            if (prefix == null) {

                return firstname + " " + lastname;
            } else {

                return firstname + " " + prefix + " " + lastname;
            }
        } else {

            if (prefix == null) {

                return initials + " " + lastname;
            } else {

                return initials + " " + prefix + " " + lastname;
            }
        }
    }

    public static void findMember(String input, UIMethods gui) {

        if (Util.isInt(input)) {

            int number = Integer.parseInt(input);

            MemberGateway member = MemberTable.getInstance().retreive(number);
            
            if(member != null){
                
                gui.showMember(member);
            }else{
                
                gui.showMessage("Lid niet gevonden");
            }
        } else {

            List<MemberGateway> members;

            if(Util.isDate(input)){
                
                Date date = Util.getDateFromString(input);
                members = MemberTable.getInstance().findByBirthdate(date);
            }else{
                
                members = MemberTable.getInstance().findByName(input);
            }

            if (members.isEmpty()) {

                gui.showMessage("Geen leden gevonden");
            } else if (members.size() == 1) {

                gui.showMember(members.get(0));
            } else {

                gui.showMemberResult(members);
            }
        }
    }

    public static void exportMembers(File file, UIMethods gui) {

        String filename = file.toString() + ".xls";

        MemberModel model = new MemberModel();

        try {

            ExcelWriter writer = new ExcelWriter();
            writer.addTableSheet(model, "Leden");
            writer.write(filename);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(filename));
        } catch (IOException ex) {

            gui.showMessage("Bestand kon niet worden weggeschreven\n" + ex);
        }
    }

    public static MemberModule getInstance() {

        if (instance == null) {

            instance = new MemberModule();
        }

        return instance;
    }
}
