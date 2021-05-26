package main;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import task.MainTask;

public class Main {
    public static void main(String args[]){
        TimerTask timerTask = new MainTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 30 * 1000);
        System.out.println("TimerTask started - insert 'stop' to end the program ");
        Scanner input = new Scanner(System.in);
        String line = "";
        while (!line.equals("stop") ){
            line = input.nextLine();
        }
        System.out.println("TimerTask ended");
        input.close();
        timer.cancel();    
    }
}
