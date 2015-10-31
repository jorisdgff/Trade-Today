package nl.m4jit.framework;

import java.io.File;
import java.math.*;
import java.security.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    public static String getInstalDir() {

        File file = new File("file.txt");
        String path = file.getAbsolutePath();
        int lastIndex = path.lastIndexOf(System.getProperty("file.separator"));
        return path.substring(0, lastIndex + 1);
    }

    public static String getUsername() {

        return System.getProperty("user.name");
    }

    public static boolean checkDev(String[] args) {

        try {

            return args[0].equals("dev");
        } catch (NullPointerException ex) {

            return false;
        }
    }

    public static boolean isInt(String string) {

        try {

            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ex) {

            return false;
        }
    }

    public static boolean isDate(String string) {

        try {

            DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            formatter.parse(string);
            return true;
        } catch (ParseException ex2) {

            return false;
        }
    }

    public static Date getDateFromString(String string) {

        try {
            
            DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            return (Date) formatter.parse(string);
        } catch (ParseException ex) {
            
            return null;
        }
    }

    public static String createMD5Hash(String password) {

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            return hash.toString(16);
        } catch (NoSuchAlgorithmException ex) {

            return "";
        }
    }
}
