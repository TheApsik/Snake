package Example;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.beans.PropertyChangeListener;

public class TestFrame extends JFrame {
    private JPanel panel;
    public Directory direction = Directory.RIGHT;
    protected boolean changeDirectory = true;

    public enum Directory {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM;
    }

    TestFrame(final int width, final int height, final int pixels){
        setTitle("Example");

        panel = new JPanel();
        panel.setLayout(new GridLayout(pixels,pixels));
        add(panel);

        Action direct = EventHandler.create(Action.class, this, "turnOn");

        InputMap imap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(KeyStroke.getKeyStroke("W"), "up");
        imap.put(KeyStroke.getKeyStroke("S"), "down");
        imap.put(KeyStroke.getKeyStroke("A"), "left");
        imap.put(KeyStroke.getKeyStroke("D"), "right");

        ActionMap amap = panel.getActionMap();
        amap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(direction != Directory.BOTTOM&& changeDirectory) {
                    changeDirectory = false;
                    direction = Directory.TOP;
                }
            }
        });
        amap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(direction != Directory.TOP && changeDirectory) {
                    changeDirectory = false;
                    direction = Directory.BOTTOM;
                }
            }
        });
        amap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(direction != Directory.RIGHT&& changeDirectory) {
                    changeDirectory = false;
                    direction = Directory.LEFT;
                }
            }
        });
        amap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(direction != Directory.LEFT&& changeDirectory) {
                    changeDirectory = false;
                    direction = Directory.RIGHT;
                }
            }
        });

        for(int i=0; i< pixels*pixels; i++)
            panel.add(new JPanel(){{
                setPreferredSize(new Dimension(width/pixels, height/pixels));
                setBackground(new Color(255, 255, 255));
            }});

        pack();
    }


    public void setColor(int x, int y, int size, Color color){
        //System.out.println(x + ", "+ y+", " + size +"=" + x*size + y);
        panel.getComponent(x*size + y).setBackground(color);
    }
}
