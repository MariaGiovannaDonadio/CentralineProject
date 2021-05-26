package main;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import insert.Osservazioni;

public class Main {
    public static void main(String args[]){
        TimerTask timerTask = new Osservazioni();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 30 * 1000);
        System.out.println("TimerTask started - insert 'stop' to end the program ");
        Scanner input = new Scanner(System.in);
        String line = "";
        while (!line.equals("stop") ){
            line = input.nextLine();
            System.out.println("line: " + line);
        }
        System.out.println("TimerTask ended");
        input.close();
        timer.cancel();    
    }
}
