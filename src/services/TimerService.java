package services;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jrabidea on 8/28/15.
 */
public class TimerService {

    public static int seconds = 0;
    public static int minutes = 0;

    public static void timer(){

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            public void run(){
                seconds++;
                if(seconds == 60){
                    minutes++;
                    seconds = 0;
                }
            }
        }, 1* 1000, 1* 1000);
    }

}
