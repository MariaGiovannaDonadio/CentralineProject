package CentralineProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.jar.JarException;
import javax.tools.JavaFileObject;
//import org.json.JSONException;
//import org.json.JSONObject;
import netscape.javascript.JSObject;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JavaFileObject readJsonFromUrl(String url) throws IOException, JarException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSObject json = new JSObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JarException {
    JavaFileObject json = readJsonFromUrl("https://swh.fi.ibimet.cnr.it:8442/swhrest2/rest/download/j_get_mobile_data/station_name/year/doy/n_days/language");
    System.out.println(json.toString());
    System.out.println(((Object) json).get("id"));
  }
}