package CentralineProject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
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
            //Getting the response code
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                // //Get the required object from the above created object
                // JSONObject obj = (JSONObject) data_obj.get("Global");

                // //Get the required data using its key
                System.out.println(data_obj);

                // JSONArray arr = (JSONArray) data_obj.get("Countries");

                // for (int i = 0; i < arr.size(); i++) {

                //     JSONObject new_obj = (JSONObject) arr.get(i);

                //     if (new_obj.get("Slug").equals("albania")) {
                //         System.out.println("Total Recovered: " + new_obj.get("TotalRecovered"));
                //         break;
                //     }
                // }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}