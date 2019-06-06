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

    JLabel raqueta;
    JLabel pelota;
    JLabel start;
    Timer timer;
    JLabel[] brickArray = new JLabel[25];
    int angulox = 1, anguloy = 1; //ángulo de la pelota

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
                Color color = new Color((int) Math.round(Math.random() * 100 + 100), (int) Math.round(Math.random() * 100 + 100), (int) Math.round(Math.random() * 100 + 100));
                brick.setOpaque(true);
                brick.setBackground(color);
                //brick.setIcon(new ImageIcon("bricks.jpg"));
                brick.setToolTipText("1");
                add(brick);
                brickArray[j + i * 5] = brick;
            }
        }

        //TODO darle fondo
        raqueta = new JLabel();
        raqueta.setSize(160, 20);
        raqueta.setLocation(250, 410);
        raqueta.setOpaque(true);
        raqueta.setBackground(Color.black);
        add(raqueta);

        //TODO añadirle skins personalizables - pelota cuadrada
        pelota = new JLabel();
        pelota.setSize(30, 30);
        pelota.setLocation(315, 350);
        pelota.setOpaque(true);
        pelota.setBackground(Color.black);
        add(pelota);

        start = new JLabel("[Pulsa espacio para jugar]");
        start.setFont(new Font("Arial", Font.PLAIN, 22));
        start.setSize(start.getPreferredSize());
        start.setLocation(195, 280);
        add(start);

        //TODO muchas cosas
        prueba1 = new JMenuItem("prueba1");
        prueba2 = new JMenuItem("prueba2");

        cosa = new JMenu("pruebaaa");
        cosa.add(prueba1);
        cosa.add(prueba2);

        menu = new JMenuBar();
        menu.add(cosa);
        menu.setBackground(new Color((int) Math.round(Math.random() * 55 + 200), (int) Math.round(Math.random() * 55 + 200), (int) Math.round(Math.random() * 55 + 200)));
        setJMenuBar(menu);

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //TODO arreglar bug donde la pelota queda en medio de la raqueta atrapada
                //TODO ángulo de la pelota al tocar la raqueta
                if ((pelota.getY() + 20 > raqueta.getY() - 10
                        && pelota.getX() - 30 > raqueta.getX()
                        && pelota.getX() < raqueta.getX() + 160)
                        || pelota.getY() + 30 < 25) {
                    anguloy *= -1;
                }
                if (pelota.getX() + 30 > 685 || pelota.getX() < 0) {
                    angulox *= -1;
                }
                //TODO pruebas de esta parte
                for (int i = 0; i < brickArray.length; i++) {
                    switch (i) {
                        case 4:
                        case 14:
                        case 24:
                        case 5:
                        case 15:
                            if (pelota.getY() < brickArray[i].getY() + 35
                                    && pelota.getY() + 30 > brickArray[i].getY()
                                    && pelota.getX() + 25 > brickArray[i].getX()
                                    && pelota.getX() < brickArray[i].getX() + 60
                                    && "1".equals(brickArray[i].getToolTipText())) {
                                anguloy *= -1;
                                brickArray[i].setVisible(false);
                                brickArray[i].setToolTipText("0");
                            }
                            if (pelota.getY() < brickArray[i].getY() + 30
                                    && pelota.getY() + 25 > brickArray[i].getY()
                                    && pelota.getX() + 30 > brickArray[i].getX()
                                    && pelota.getX() < brickArray[i].getX() + 65
                                    && "1".equals(brickArray[i].getToolTipText())) {
                                angulox *= -1;
                                brickArray[i].setVisible(false);
                                brickArray[i].setToolTipText("0");
                            }
                            break;

                        default:
                            if (pelota.getY() < brickArray[i].getY() + 35
                                    && pelota.getY() + 30 > brickArray[i].getY()
                                    && pelota.getX() + 25 > brickArray[i].getX()
                                    && pelota.getX() < brickArray[i].getX() + 135
                                    && "1".equals(brickArray[i].getToolTipText())) {
                                anguloy *= -1;
                                brickArray[i].setVisible(false);
                                brickArray[i].setToolTipText("0");
                            }
                            if (pelota.getY() < brickArray[i].getY() + 30
                                    && pelota.getY() + 25 > brickArray[i].getY()
                                    && pelota.getX() + 30 > brickArray[i].getX()
                                    && pelota.getX() < brickArray[i].getX() + 140
                                    && "1".equals(brickArray[i].getToolTipText())) {
                                angulox *= -1;
                                brickArray[i].setVisible(false);
                                brickArray[i].setToolTipText("0");
                            }
                    }
                }
                pelota.setLocation(pelota.getX() + 4 * angulox, pelota.getY() + 4 * anguloy);
            }
        });

        addKeyListener(this);
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
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            start.setVisible(false);
            timer.start();
        }
        if ((ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D)
                && raqueta.getX() < 685 - 160 - 10) {
            raqueta.setLocation(raqueta.getX() + 10, raqueta.getY());
        }
        if ((ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A)
                && raqueta.getX() > 10) {
            raqueta.setLocation(raqueta.getX() - 10, raqueta.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
