package Example;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    public static void main(String... args)throws InterruptedException{
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Snake tFrame = new Snake(30);
                tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tFrame.setVisible(true);
                tFrame.setResizable(false);
            }
        });
    }
}
