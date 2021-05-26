package task;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.Centralina;
import entity.Sensore;
import utils.Fetcher;
import utils.Helper;
import utils.DBConnection;

public class MainTask extends TimerTask {

    private DBConnection dbConn;

    public MainTask (){
        this.dbConn = new DBConnection();
    };

    @Override
    public void run() {    
        String[] tipi = {"RH","PM10","CO2","PM2.5","O3","VOC","NO_A","T","NO2","CO","NO2_A"};
        List<String> tipiSenore = Arrays.asList(tipi);
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("config.json")));
            JSONObject config = new JSONObject(jsonString);
            JSONObject dbConfig = config.getJSONObject("database");
            String apitBaseUrl = config.getString("apiBaseUrl");
            dbConn.connect(dbConfig);
            JSONObject jsondata;
            ArrayList<Centralina> cList = dbConn.getCentraline(); 
            System.out.println("Centraline: " + cList);
            for (Centralina centralina:cList){
                ArrayList<Sensore> sensori = dbConn.getSensori(centralina.getId());
                System.out.println("Sensori: " + sensori);
                jsondata = Fetcher.getCentralinaData(apitBaseUrl, centralina.getNome());              
                JSONArray dataArray = jsondata.getJSONArray("data");
                int lastIndex = dataArray.length() - 1;
                JSONObject obj = dataArray.getJSONObject(lastIndex);
                String data = obj.getString("data");
                for(String tipoSensore: tipiSenore) {
                    float valoreSensore = obj.getFloat(tipoSensore);
                    Sensore s = Helper.findSensore(sensori, tipoSensore);
                    if(s != null){
                        dbConn.insertOsservazione(valoreSensore, data, s.getId());
                    }
                }
            }
            dbConn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}    