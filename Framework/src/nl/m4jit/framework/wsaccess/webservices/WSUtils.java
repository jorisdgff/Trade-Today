package nl.m4jit.framework.wsaccess.webservices;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author Joris
 */
public class WSUtils {

    public static JsonElement getJSONFromURL(String url, ArrayList<String> parameters) throws IOException{

            url = url.replaceAll(" ", "%20");

            String paramaterstring = "";

            if (parameters != null && parameters.size() > 0) {

                for (int i = 0; i < parameters.size(); i++) {

                    paramaterstring += i == 0 ? "?" : "&";
                    paramaterstring += parameters.get(i).replaceAll("&", "%26");
                }
            }

            paramaterstring = paramaterstring.replaceAll(" ", "%20");

            URLConnection urlConnection = new URL(url + paramaterstring).openConnection();
            urlConnection.connect();
            return getJSONFromInputStream(urlConnection.getInputStream());
        
    }

    public static JsonElement getJSONFromInputStream(InputStream is) {

        JsonReader reader = new JsonReader(new InputStreamReader(is));
        return new JsonParser().parse(reader);
    }
}
