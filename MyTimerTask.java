import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyTimerTask extends TimerTask {

    @Override
    public void run() {    
        completeTask();
    }

    private void completeTask() {
        try {
            JSONObject jdata;
            for (Centralina centralina:MysqlCon.getCentraline()){
                ArrayList<Sensore> sensori = MysqlCon.getSensori(centralina.getId());
                jdata = FetchData.getData(centralina.getNome());              
                 JSONArray c = jdata.getJSONArray("data");
                    for (int i = 0 ; i < c.length(); i++) {
                        JSONObject obj = c.getJSONObject(i);
                        String data = obj.getString("data");
                        float rh = obj.getFloat("RH");
                        Sensore s = Helper.findSensore(sensori, "RH");
                        if(s != null){
                            MysqlCon.insertOsservatione(rh, data, s.getId());
                        }
                        float pm10 = obj.getFloat("PM10");
                        float co2 = obj.getFloat("CO2");
                        float pm25 = obj.getFloat("PM2.5");
                        float o3 = obj.getFloat("O3");
                        float voc = obj.getFloat("VOC");
                        float noa = obj.getFloat("NO_A");
                        float t = obj.getFloat("T");
                        float no2 = obj.getFloat("NO2");
                        float co = obj.getFloat("CO");
                        float no2a = obj.getFloat("NO2_A");
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