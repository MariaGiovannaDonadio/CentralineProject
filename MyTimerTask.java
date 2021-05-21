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
                        Sensore a = Helper.findSensore(sensori, "RH");
                        if(a != null){
                            MysqlCon.insertOsservatione(rh, data, a.getId());
                        }
                        float pm10 = obj.getFloat("PM10");
                        Sensore b = Helper.findSensore(sensori, "RH");
                        if(b != null){
                            MysqlCon.insertOsservatione(rh, data, b.getId());
                        }
                        float co2 = obj.getFloat("CO2");
                        Sensore o = Helper.findSensore(sensori, "RH");
                        if(c != null){
                            MysqlCon.insertOsservatione(rh, data, o.getId());
                        }
                        float pm25 = obj.getFloat("PM2.5");
                        Sensore d = Helper.findSensore(sensori, "RH");
                        if(d != null){
                            MysqlCon.insertOsservatione(rh, data, d.getId());
                        }
                        float o3 = obj.getFloat("O3");
                        Sensore e = Helper.findSensore(sensori, "RH");
                        if(e != null){
                            MysqlCon.insertOsservatione(rh, data, e.getId());
                        }
                        float voc = obj.getFloat("VOC");
                        Sensore f = Helper.findSensore(sensori, "RH");
                        if(f != null){
                            MysqlCon.insertOsservatione(rh, data, f.getId());
                        }
                        float noa = obj.getFloat("NO_A");
                        Sensore g = Helper.findSensore(sensori, "RH");
                        if(g != null){
                            MysqlCon.insertOsservatione(rh, data, g.getId());
                        }
                        float t = obj.getFloat("T");
                        Sensore h = Helper.findSensore(sensori, "RH");
                        if(h != null){
                            MysqlCon.insertOsservatione(rh, data, h.getId());
                        }
                        float no2 = obj.getFloat("NO2");
                        Sensore l = Helper.findSensore(sensori, "RH");
                        if(l != null){
                            MysqlCon.insertOsservatione(rh, data, l.getId());
                        }
                        float co = obj.getFloat("CO");
                        Sensore m = Helper.findSensore(sensori, "RH");
                        if(m != null){
                            MysqlCon.insertOsservatione(rh, data, m.getId());
                        }
                        float no2a = obj.getFloat("NO2_A");
                        Sensore n = Helper.findSensore(sensori, "RH");
                        if(n != null){
                            MysqlCon.insertOsservatione(rh, data, n.getId());
                        }
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