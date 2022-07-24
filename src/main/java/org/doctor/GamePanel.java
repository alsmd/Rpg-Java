package org.doctor;

import org.doctor.map.WorldMap;
import org.doctor.states.MainState;
import org.doctor.states.MenuState;
import org.doctor.states.State;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class GamePanel extends JPanel implements Runnable{
    Stack<State> states = new Stack<>();
    Thread gameThread;
    public KeyHandler keyH = new KeyHandler();
    //FPS
    int FPS = 60;
    public GamePanel(){
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        states.push(new MainState(this));
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
        if (states.size() > 0)
            states.peek().update();
        else
            System.exit(0);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (states.size() > 0)
            states.peek().draw(g2);
        else
            System.exit(0);
        g2.dispose();
    }

}
