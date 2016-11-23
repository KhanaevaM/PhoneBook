package levelp;

/**
 * Created by hanaevamaria on 23/11/16.
 */
public class ProgressThread extends Thread {
    public double oneProcent = (double)(Main.count)/1000;
    public double procent = oneProcent;
    @Override
    public void run() {

        while (procent<=Main.count){
           procent+=oneProcent;
            try {Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
