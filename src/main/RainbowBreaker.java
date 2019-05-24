package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RainbowBreaker {

    public static void main(String args[]) {
        Breaker aplicacion = new Breaker();
        aplicacion.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit.getDefaultToolkit().getScreenSize().height / 4);
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.setSize(685, 500);
        aplicacion.setVisible(true);
    }
}

class Breaker extends JFrame implements MouseMotionListener, KeyListener, ActionListener {

    JMenuBar menu;
    JMenu cosa;
    JMenuItem prueba1;
    JMenuItem prueba2;
    
    public Breaker() {
        super("RainbowBreaker");
        setLayout(null);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel brick;
                brick = new JLabel();
                if (i % 2 == 0 && j < 4) {
                    brick.setLocation(10 + j * 150, 10 + i * 45);
                    brick.setSize(140, 35);
                } else if (i % 2 == 1 && j > 0) {
                    brick.setLocation(-65 + j * 150, 10 + i * 45);
                    brick.setSize(140, 35);
                } else if (i % 2 == 0) {
                    brick.setLocation(10 + j * 150, 10 + i * 45);
                    brick.setSize(65, 35);
                } else {
                    brick.setLocation(10 + j * 150, 10 + i * 45);
                    brick.setSize(65, 35);
                }
                Color color=new Color((int)Math.round(Math.random()*255), (int)Math.round(Math.random()*255), (int)Math.round(Math.random()*255));
                brick.setOpaque(true);
                brick.setBackground(color);
                //brick.setIcon(new ImageIcon("bricks.jpg"));
                brick.setToolTipText((j+i*5) + "");
                add(brick);
            }
        }
        
        prueba1=new JMenuItem("prueba1");
        prueba2=new JMenuItem("prueba2");
        
        cosa=new JMenu("pruebaaa");
        cosa.add(prueba1);
        cosa.add(prueba2);
        
        menu=new JMenuBar();
        menu.add(cosa);
        menu.setBackground(new Color((int)Math.round(Math.random()*55+200), (int)Math.round(Math.random()*55+200), (int)Math.round(Math.random()*55+200)));
        setJMenuBar(menu);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
