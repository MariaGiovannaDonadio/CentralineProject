import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        completeTask();
        System.out.println("Timer task finished at:"+new Date());
    }

    private void completeTask() {
        URL url;
        try {
   
            Date date = new Date();      
            Calendar cal = Calendar.getInstance();
            cal.setTime(date); 
            int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
            url = new URL("https://swh.fi.ibimet.cnr.it:8442/swhrest2/rest/download/j_get_mobile_data/SMART56/2021/"+dayOfYear+"/1/IT");
   
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
    
    public static void main(String args[]){
        TimerTask timerTask = new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        /*try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel(); questo serve per fermare il programma dopo tot millisecondi */
        
    }

}