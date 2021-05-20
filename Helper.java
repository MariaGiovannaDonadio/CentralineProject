import java.util.ArrayList;

public class Helper {
    
    public static Sensore findSensore(ArrayList<Sensore> sensori, String tipo){
        for (Sensore s : sensori) {
            if (s.getTipo().equals(tipo)) {
                return s;
            }
        }
        return null;
    }
}
