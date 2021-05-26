package insert;
import java.util.ArrayList;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;
import apiConnect.FetchData;
import dbConnect.MysqlCon;
import element.Centralina;
import element.Helper;
import element.Sensore;

public class Osservazioni extends TimerTask {

    private MysqlCon dbConn;

    public Osservazioni (){
        this.dbConn = new MysqlCon();
    };

    @Override
    public void run() {    
        completeTask();
    }

    private void completeTask() {
        try {
            dbConn.connect();
            JSONObject jsondata;
            ArrayList<Centralina> cList = dbConn.getCentraline(); 
            System.out.println("Centraline: " + cList);
            for (Centralina centralina:cList){
                ArrayList<Sensore> sensori = dbConn.getSensori(centralina.getId());
                System.out.println("Sensori: " + sensori);
                jsondata = FetchData.getData(centralina.getNome());              
                JSONArray dataArray = jsondata.getJSONArray("data");
                int lastIndex = dataArray.length() - 1;
                JSONObject obj = dataArray.getJSONObject(lastIndex);
                String data = obj.getString("data");
                float rh = obj.getFloat("RH");
                Sensore a = Helper.findSensore(sensori, "RH");
                if(a != null){
                    dbConn.insertOsservazione(rh, data, a.getId());
                }
                float pm10 = obj.getFloat("PM10");
                Sensore b = Helper.findSensore(sensori, "PM10");
                if(b != null){
                    dbConn.insertOsservazione(pm10, data, b.getId());
                }
                float co2 = obj.getFloat("CO2");
                Sensore c = Helper.findSensore(sensori, "CO2");
                if(c != null){
                    dbConn.insertOsservazione(co2, data, c.getId());
                }
                float pm25 = obj.getFloat("PM2.5");
                Sensore d = Helper.findSensore(sensori, "PM2.5");
                if(d != null){
                    dbConn.insertOsservazione(pm25, data, d.getId());
                }
                float o3 = obj.getFloat("O3");
                Sensore e = Helper.findSensore(sensori, "O3");
                if(e != null){
                    dbConn.insertOsservazione(o3, data, e.getId());
                }
                float voc = obj.getFloat("VOC");
                Sensore f = Helper.findSensore(sensori, "VOC");
                if(f != null){
                    dbConn.insertOsservazione(voc, data, f.getId());
                }
                float noa = obj.getFloat("NO_A");
                Sensore g = Helper.findSensore(sensori, "NO_A");
                if(g != null){
                    dbConn.insertOsservazione(noa, data, g.getId());
                }
                float t = obj.getFloat("T");
                Sensore h = Helper.findSensore(sensori, "T");
                if(h != null){
                    dbConn.insertOsservazione(t, data, h.getId());
                }
                float no2 = obj.getFloat("NO2");
                Sensore l = Helper.findSensore(sensori, "NO2");
                if(l != null){
                    dbConn.insertOsservazione(no2, data, l.getId());
                }
                float co = obj.getFloat("CO");
                Sensore m = Helper.findSensore(sensori, "CO");
                if(m != null){
                    dbConn.insertOsservazione(co, data, m.getId());
                }
                float no2a = obj.getFloat("NO2_A");
                Sensore n = Helper.findSensore(sensori, "NO2_A");
                if(n != null){
                    dbConn.insertOsservazione(no2a, data, n.getId());
                }

            }
            dbConn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}    