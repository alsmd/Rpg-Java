package org.doctor;

import org.doctor.map.WorldMap;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    WorldMap mainMap = new WorldMap(keyH, this);
    //FPS
    int FPS = 60;
    public GamePanel(){
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/ FPS;
        double delta = 0;
        long timer = 0;
        int drawCount = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
//                System.out.println(drawCount);
                timer = 0;
                drawCount = 0;
            }
        }

    }

    public void update(){
        mainMap.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        mainMap.draw(g2);
        g2.dispose();
    }

}
