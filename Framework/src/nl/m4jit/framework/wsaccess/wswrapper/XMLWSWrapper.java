package nl.m4jit.framework.wsaccess.wswrapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author joris
 */
public abstract class XMLWSWrapper {

    public static Document getDocument(String baseurl, String... paramaters) throws XMLWSWrapperException {

        String paramatersstring = "";
        
        for (String paramater : paramaters) {

            String[] array = paramater.split("=");
            String name = array[0];
            String value = array[1];
            //String value = URLEncoder.encode(array[1], "ISO-8859-1"); ??

            paramatersstring += paramatersstring.startsWith("?") ? "&" : "?";
            paramatersstring += name + "=" + value;
        }
        
        String urlstring = baseurl + paramatersstring;

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            URL url = new URL(urlstring);
            InputStream is = url.openStream();

            Document doc = db.parse(is);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException ex) {

            throw new XMLWSWrapperException(ex);
        } catch (IOException ex) {

            throw new XMLWSWrapperException(ex);
        } catch (SAXException ex) {

            throw new XMLWSWrapperException(ex);
        }
    }
}