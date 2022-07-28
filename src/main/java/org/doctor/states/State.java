package org.doctor.states;

import org.doctor.Game;
import org.doctor.KeyHandler;
import org.doctor.gui.GuiPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State{
    public GuiPanel panel = new GuiPanel();
    public Game game;
    public KeyHandler keyH;


    public State(Game game){
        keyH = game.keyH;
       this.game = game;
    }

    public abstract void onClose();
    public abstract void onBuild();
    public abstract void onBack();

    // LOOP
    public void draw(Graphics2D g2){
        panel.draw(g2);
    }

    public void update() {
        panel.update();
    }


    public void mousePressed(MouseEvent e){
        panel.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e){
        panel.mouseReleased(e);
    }

    public void mouseDragged(MouseEvent e){
        panel.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e){
        panel.mouseMoved(e);
    }
    public void keyTyped(KeyEvent e) {
        panel.keyTyped(e);
    }

    public void keyPressed(KeyEvent e){
        panel.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
    }

}
