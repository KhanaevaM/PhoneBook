package levelp;

import java.io.File;

/**
 * Created by hanaevamaria on 23/11/16.
 */
public class SavingThread extends Thread {
    int procentInt = 0;

    @Override
    public void run() {
        File dir = new File("ContactsJson");
        dir.mkdir();
        File[] files = dir.listFiles();
        for (File f : files) {
            f.delete();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int q = 0; q < Main.count; q++) {
                    procentInt = (int) (((double) (q) / (double) (Main.count-1)) * 100);
                    Main.writeJson(Main.contacts.get(q), q);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();
        String line = "=";
        while (procentInt <= 100) {
            System.out.print("\r" + line + " " + procentInt);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (procentInt > 0 && procentInt % 10 == 0) {
                line += "=";
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (procentInt==100){
                procentInt++;
            }
        }
    }

}

