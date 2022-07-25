package org.doctor.states;

import org.doctor.Game;
import org.doctor.KeyHandler;
import org.doctor.map.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MainState extends State{

    WorldMap map;


    public MainState(Game game){
        super(game);
        map = new WorldMap(keyH);
    }

    @Override
    public void onClose() {
        map.sound.stop("background");
    }

    @Override
    public void draw(Graphics2D g2) {
        map.draw(g2);
        super.draw(g2);
    }

    @Override
    public void update() {
        map.update();
        if (keyH.pause)
            game.pushState(new PauseMenuState(game));
    }
}
