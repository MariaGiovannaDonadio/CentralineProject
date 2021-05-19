import java.util.Timer;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {

    @Override
    public void run() {    
        completeTask();
    }

    private void completeTask() {
        try {
            for (String centraline:MysqlCon.getCentraline()){
                FetchData.getData(centraline);
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