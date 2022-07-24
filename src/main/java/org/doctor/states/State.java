package org.doctor.states;

import org.doctor.GamePanel;
import org.doctor.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public abstract class State {
    GamePanel panel;


    public State(GamePanel panel){
       this.panel = panel;
    }


    // LOOP
    public abstract void draw(Graphics2D g2);

    public abstract void update();

}
