import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainGET {

    public static void main(String[] args) {

        URL url;
        try {
            url = new URL("https://swh.fi.ibimet.cnr.it:8442/swhrest2/rest/download/j_get_mobile_data/SMART55/2021/1/1/IT");
                
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                System.out.println(data_obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}