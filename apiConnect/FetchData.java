package apiConnect;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import org.json.JSONObject;

public class FetchData {
    public static JSONObject getData(String centraline) {

        URL url;
        try {
            Date date = new Date();      
            Calendar cal = Calendar.getInstance();
            cal.setTime(date); 
            int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
            Calendar year = GregorianCalendar.getInstance();
            url = new URL("https://swh.fi.ibimet.cnr.it:8442/swhrest2/rest/download/j_get_mobile_data/"+centraline+"/"+year.get(Calendar.YEAR)+"/"+dayOfYear+"/1/IT");
   
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
                JSONObject data_obj = new JSONObject(inline);
                return data_obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;   
    }
}
