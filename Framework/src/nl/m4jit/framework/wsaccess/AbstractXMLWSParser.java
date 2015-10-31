package nl.m4jit.framework.wsaccess;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author joris
 */
public abstract class AbstractXMLWSParser {

    public ArrayList<Element> getElements(String urladditions, String[] args, String... path) {

        try {

            String argsstring = "";

            for (String arg : args) {

                String[] array = arg.split("=");
                String name = array[0];
                String value = array[1].replaceAll(" ", "%20").replaceAll("/", "").replaceAll("&", "%26").replaceAll("!", "").replaceAll(":", "");

                argsstring += "&" + name + "=" + value;
            }

            String urlstring = getBaseURL() + "/" + urladditions + "?" + argsstring;
            URL url = new URL(urlstring);
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = null;

            while (document == null) {

                try {

                    document = db.parse(url.openStream());
                } catch (IOException ex) {

                    /*System.out.println("IO " + args[0].split("=")[1]);
                    System.out.println("");*/

                    if (ex.toString().contains("400")) {

                        System.out.println(ex);
                        return null;
                    }
                    
                    Thread.sleep(1000);
                }
            }

            document.getDocumentElement().normalize();

            ArrayList<Element> elements = new ArrayList<Element>();

            NodeList nodelist = document.getElementsByTagName(path[0]);

            for (int i = 1; i < path.length; i++) {

                Element element = (Element) nodelist.item(0);

                if (element != null) {

                    nodelist = element.getElementsByTagName(path[i]);
                } else {

                    return null;
                }
            }

            for (int index = 0; index < nodelist.getLength(); index++) {

                Element element = (Element) nodelist.item(index);
                elements.add(element);
            }

            return elements;
        } catch (ParserConfigurationException ex) {

            System.out.print(ex);
            return null;
        } catch (SAXException ex) {

            System.out.print(ex);
            return null;
        } catch (MalformedURLException ex) {

            System.out.print(ex);
            return null;
        } /*catch (UnsupportedEncodingException ex) {

            System.out.print(ex);
            return null;
        }*/catch(InterruptedException ex){
            
            return null;
        }
    }

    public abstract String getBaseURL();
}
