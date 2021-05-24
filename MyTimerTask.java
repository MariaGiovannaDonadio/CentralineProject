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
                            MysqlCon.insertOsservazione(rh, data, a.getId());
                        }
                        float pm10 = obj.getFloat("PM10");
                        Sensore b = Helper.findSensore(sensori, "PM10");
                        if(b != null){
                            MysqlCon.insertOsservazione(pm10, data, b.getId());
                        }
                        float co2 = obj.getFloat("CO2");
                        Sensore o = Helper.findSensore(sensori, "CO2");
                        if(c != null){
                            MysqlCon.insertOsservazione(co2, data, o.getId());
                        }
                        float pm25 = obj.getFloat("PM2.5");
                        Sensore d = Helper.findSensore(sensori, "PM2.5");
                        if(d != null){
                            MysqlCon.insertOsservazione(pm25, data, d.getId());
                        }
                        float o3 = obj.getFloat("O3");
                        Sensore e = Helper.findSensore(sensori, "O3");
                        if(e != null){
                            MysqlCon.insertOsservazione(o3, data, e.getId());
                        }
                        float voc = obj.getFloat("VOC");
                        Sensore f = Helper.findSensore(sensori, "VOC");
                        if(f != null){
                            MysqlCon.insertOsservazione(voc, data, f.getId());
                        }
                        float noa = obj.getFloat("NO_A");
                        Sensore g = Helper.findSensore(sensori, "NO_A");
                        if(g != null){
                            MysqlCon.insertOsservazione(noa, data, g.getId());
                        }
                        float t = obj.getFloat("T");
                        Sensore h = Helper.findSensore(sensori, "T");
                        if(h != null){
                            MysqlCon.insertOsservazione(t, data, h.getId());
                        }
                        float no2 = obj.getFloat("NO2");
                        Sensore l = Helper.findSensore(sensori, "NO2");
                        if(l != null){
                            MysqlCon.insertOsservazione(no2, data, l.getId());
                        }
                        float co = obj.getFloat("CO");
                        Sensore m = Helper.findSensore(sensori, "CO");
                        if(m != null){
                            MysqlCon.insertOsservazione(co, data, m.getId());
                        }
                        float no2a = obj.getFloat("NO2_A");
                        Sensore n = Helper.findSensore(sensori, "NO2_A");
                        if(n != null){
                            MysqlCon.insertOsservazione(no2a, data, n.getId());
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