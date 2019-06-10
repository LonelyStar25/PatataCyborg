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

class Breaker extends JFrame implements KeyListener, ActionListener {

    JLabel raqueta;
    JLabel pelota;
    JLabel infoLabel;
    Timer timer;
    JLabel[] brickArray = new JLabel[25];
    int nivel = 1, velocidad = 8;
    boolean gameOver = false, gameStarted = false;
    double dirx = Math.cos(Math.PI / 4), diry = Math.cos(Math.PI / 4); //ángulo de la pelota

    JMenuBar menu;
    JMenu personaliz;
    JMenu info;
    JMenuItem instrucciones;
    JMenuItem controles;
    JMenuItem créditos;

    public Breaker() {
        super("RainbowBreaker");
        setLayout(null);

        //S E P A R A D O R  de código || aquí elementos básicos
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
                brick.setToolTipText("1");
                add(brick);
                brickArray[j + i * 5] = brick;
            }
        }

        raqueta = new JLabel();
        raqueta.setSize(160, 20);
        raqueta.setLocation(250, 410);
        raqueta.setOpaque(true);
        raqueta.setBackground(new Color((int) Math.round(Math.random() * 100 + 50), (int) Math.round(Math.random() * 100 + 50), (int) Math.round(Math.random() * 100 + 50)));
        add(raqueta);

        pelota = new JLabel();
        pelota.setSize(30, 30);
        pelota.setLocation(315, 350);
        pelota.setOpaque(true);
        ImageIcon imageIcon = new ImageIcon("gd1.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        pelota.setIcon(imageIcon);
        add(pelota);

        infoLabel = new JLabel("[Pulsa espacio para jugar]");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        infoLabel.setSize(infoLabel.getPreferredSize());
        infoLabel.setLocation(190, 280);
        add(infoLabel);

        //S E P A R A D O R  de código || aquí menús
        personaliz = new JMenu("Icono de la pelota");
        JMenuItem iconMenu = null;
        for (int i = 0; i < 7; i++) {
            iconMenu = new JMenuItem("Icono " + (i + 1));
            iconMenu.addActionListener(this);
            personaliz.add(iconMenu);
        }

        instrucciones = new JMenuItem("Instrucciones");
        instrucciones.addActionListener(this);

        controles = new JMenuItem("Controles");
        controles.addActionListener(this);

        créditos = new JMenuItem("Créditos");
        créditos.addActionListener(this);

        info = new JMenu("Info");
        info.add(instrucciones);
        info.add(controles);
        info.add(créditos);

        menu = new JMenuBar();
        menu.add(personaliz);
        menu.add(info);
        menu.setBackground(new Color((int) Math.round(Math.random() * 55 + 200), (int) Math.round(Math.random() * 55 + 200), (int) Math.round(Math.random() * 55 + 200)));
        setJMenuBar(menu);

        //S E P A R A D O R  de código || aquí timer
        timer = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (pelota.getY() > 480) {
                    gameOver = true;
                }

                if (!gameOver) {
                    if (pelota.getY() + 20 > raqueta.getY() - 10
                            && pelota.getX() > raqueta.getX()
                            && pelota.getX() < raqueta.getX() + 160) {
                        dirx = -Math.sin(((pelota.getX() - raqueta.getX() + 80) * Math.PI / 2) / 80);
                        if (dirx > 0.7) {
                            dirx = 0.7;
                        } else if (dirx < -0.7) {
                            dirx = -0.7;
                        }
                        diry = (1 - Math.abs(dirx)) * -1;
                    }
                    if (pelota.getY() + 30 < 25) {
                        diry *= -1;
                    }
                    if (pelota.getX() + 30 > 685 || pelota.getX() < 0) {
                        dirx *= -1;
                    }

                    boolean stageClear = true;
                    for (int i = 0; i < brickArray.length; i++) {
                        if ("1".equals(brickArray[i].getToolTipText())) {
                            stageClear = false;
                        }

                        switch (i) {
                            case 4:
                            case 14:
                            case 24:
                            case 5:
                            case 15:
                                if (pelota.getY() < brickArray[i].getY() + 35
                                        && pelota.getY() + 30 > brickArray[i].getY()
                                        && pelota.getX() + 20 > brickArray[i].getX()
                                        && pelota.getX() < brickArray[i].getX() + 55
                                        && "1".equals(brickArray[i].getToolTipText())) {
                                    diry *= -1;
                                    brickArray[i].setVisible(false);
                                    brickArray[i].setToolTipText("0");
                                }
                                if (pelota.getY() < brickArray[i].getY() + 25
                                        && pelota.getY() + 20 > brickArray[i].getY()
                                        && pelota.getX() + 30 > brickArray[i].getX()
                                        && pelota.getX() < brickArray[i].getX() + 65
                                        && "1".equals(brickArray[i].getToolTipText())) {
                                    dirx *= -1;
                                    brickArray[i].setVisible(false);
                                    brickArray[i].setToolTipText("0");
                                }
                                break;

                            default:
                                if (pelota.getY() < brickArray[i].getY() + 35
                                        && pelota.getY() + 30 > brickArray[i].getY()
                                        && pelota.getX() + 20 > brickArray[i].getX()
                                        && pelota.getX() < brickArray[i].getX() + 130
                                        && "1".equals(brickArray[i].getToolTipText())) {
                                    diry *= -1;
                                    brickArray[i].setVisible(false);
                                    brickArray[i].setToolTipText("0");
                                }
                                if (pelota.getY() < brickArray[i].getY() + 25
                                        && pelota.getY() + 20 > brickArray[i].getY()
                                        && pelota.getX() + 30 > brickArray[i].getX()
                                        && pelota.getX() < brickArray[i].getX() + 140
                                        && "1".equals(brickArray[i].getToolTipText())) {
                                    dirx *= -1;
                                    brickArray[i].setVisible(false);
                                    brickArray[i].setToolTipText("0");
                                }
                        }
                    }
                    pelota.setLocation((int) (pelota.getX() + velocidad * dirx), (int) (pelota.getY() + velocidad * diry));

                    if (stageClear) {
                        nivel++;
                        timer.stop();
                        pelota.setLocation(315, 350);
                        raqueta.setLocation(250, 410);
                        for (JLabel brick : brickArray) {
                            brick.setVisible(true);
                            brick.setToolTipText("1");
                        }
                        velocidad += 4;
                        infoLabel.setText("Nivel " + nivel + ": pulsa espacio para comenzar");
                        infoLabel.setSize(infoLabel.getPreferredSize());
                        infoLabel.setLocation(135, 280);
                        infoLabel.setVisible(true);
                    }
                } else {
                    timer.stop();
                    infoLabel.setText("G A M E  O V E R");
                    infoLabel.setSize(infoLabel.getPreferredSize());
                    infoLabel.setLocation(240, 280);
                    infoLabel.setVisible(true);
                }
            }
        });

        addKeyListener(this);
    }

    //S E P A R A D O R  de código || aquí eventos
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (!gameOver) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!gameStarted) {
                    infoLabel.setVisible(false);
                    timer.start();
                    gameStarted=true;
                } else {
                    infoLabel.setVisible(true);
                    infoLabel.setText("P A U S A");
                    infoLabel.setSize(infoLabel.getPreferredSize());
                    infoLabel.setLocation(280, 280);
                    timer.stop();
                    gameStarted=false;
                }
            }
            if (gameStarted) {
                if ((ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D)
                        && raqueta.getX() < 685 - 160 - 10) {
                    raqueta.setLocation(raqueta.getX() + 10, raqueta.getY());
                }
                if ((ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A)
                        && raqueta.getX() > 10) {
                    raqueta.setLocation(raqueta.getX() - 10, raqueta.getY());
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JMenuItem src = (JMenuItem) ae.getSource();

        if (null != src.getText()) {
            switch (src.getText()) {
                case "Controles":
                    JOptionPane.showMessageDialog(null, "Teclas de flecha o A, D: mover la raqueta\nEspacio: inicio y pausa", "Controles", 1);
                    break;
                case "Créditos":
                    JOptionPane.showMessageDialog(null, "Hecho por: Carmen Lonely Star\nEso es todo\n¿Qué te esperabas? :p", "Créditos", 1);
                    break;
                case "Instrucciones":
                    JOptionPane.showMessageDialog(null, "Rompe todos los bricks, pasa al siguiente nivel, no dejes que la pelota caiga\nGarantizamos la simpleza de un botijo", "Instrucciones", 1);
                    break;
                default:
                    ImageIcon imageIcon = new ImageIcon("gd" + src.getText().charAt(6) + ".png");
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newimg);
                    pelota.setIcon(imageIcon);
            }
        }
    }
}
