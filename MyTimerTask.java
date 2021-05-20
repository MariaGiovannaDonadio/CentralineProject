import java.util.Timer;
import java.util.TimerTask;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MyTimerTask extends TimerTask {

    @Override
    public void run() {    
        completeTask();
    }

    private void completeTask() {
        try {
            JSONObject jdata;
            for (String centraline:MysqlCon.getCentraline()){
                jdata = FetchData.getData(centraline);
                
                 JSONArray c = jdata.getJSONArray("data");
                    for (int i = 0 ; i < c.length(); i++) {
                        JSONObject obj = c.getJSONObject(i);
                        String A = obj.getString("A");
                        String B = obj.getString("B");
                        String C = obj.getString("C");
                        System.out.println(A + " " + B + " " + C);
                }
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
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel(); //questo serve per fermare il programma dopo tot millisecondi 
        
    }

}