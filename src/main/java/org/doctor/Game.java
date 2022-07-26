package org.doctor;

import org.doctor.states.MainMenuState;
import org.doctor.states.MainState;
import org.doctor.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    Thread gameThread;
    int FPS = 60;
    int fpsCount;
    public KeyHandler keyH = new KeyHandler();
    private Stack<State> states = new Stack<>();

    public Game(){
        initWindow();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(keyH);
        addKeyListener(this);
        states.push(new MainMenuState(this));
    }




    private void initWindow(){
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    public void start(){
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
                fpsCount = drawCount;
                timer = 0;
                drawCount = 0;
            }
        }
    }
    public void pushState(State state){
        states.push(state);
    }

    public void popState(){
        states.pop().onClose();
    }
    public void update(){
        if (states.size() > 0)
            states.peek().update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (State state : states)
            state.draw(g2);
        g2.setColor(Color.white);
        g2.drawString("fps: " + Integer.toString(fpsCount), 30, 30);
        g2.dispose();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (states.size() > 0)
            states.peek().keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (states.size() > 0)
            states.peek().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (states.size() > 0){
            states.peek().mousePressed(e);
        }
        else
            System.exit(0);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (states.size() > 0){
            states.peek().mouseReleased(e);
        }
        else
            System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (states.size() > 0){
            states.peek().mouseDragged(e);
        }
        else
            System.exit(0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (states.size() > 0){
            states.peek().mouseMoved(e);
        }
        else
            System.exit(0);
    }


}
