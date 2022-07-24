package org.doctor.states;

import org.doctor.GamePanel;
import org.doctor.KeyHandler;
import org.doctor.map.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MainState extends State{

    WorldMap map;


    public MainState(GamePanel panel){
        super(panel);
        map = new WorldMap(panel.keyH, panel);
    }
    @Override
    public void draw(Graphics2D g2) {
        map.draw(g2);
    }

    @Override
    public void update() {
        map.update();
    }
}
