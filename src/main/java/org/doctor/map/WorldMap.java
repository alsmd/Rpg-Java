package org.doctor.map;

import org.doctor.Game;
import org.doctor.KeyHandler;
import org.doctor.Sound;
import org.doctor.scene.entity.Player;
import org.doctor.scene.object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class WorldMap{
    // CONFIGURATION SETUP
    public Point screenSize = new Point(Game.WIDTH, Game.HEIGHT);
    public MapConfig mapConfig = new MapConfig(this);
    public final Point maxScreenGrid = new Point(screenSize.x / mapConfig.scaledTileSize.x, screenSize.y / mapConfig.scaledTileSize.x);

    // Scene's ELEMENTS
    public Sound sound = new Sound();
    Camera camera = new Camera(this);
    public Layer layers[];
    public SuperObject objects[];
    public Player player;

    // Window's handlers
    public KeyHandler keyH;

    public WorldMap(KeyHandler keyH){
        // Scene's ELEMENTS
        this.layers = mapConfig.getLayers();
        this.objects = mapConfig.getObjects();
        this.player = new Player(this, new Point(2 * mapConfig.scaledTileSize.x, 2 * mapConfig.scaledTileSize.x));
        sound.add("background", "sounds/horror_background.wav");
        sound.loop("background");
        // Window's handlers
        this.keyH = keyH;
    }

    // LOOP
    public void update(){
        player.update();
        for (SuperObject obj : objects)
            obj.update();
        camera.update();
    }
    public void draw(Graphics2D g2){
        for (Layer layer : layers)
            camera.drawLayer(g2, layer);
        for (SuperObject obj : objects){
            camera.setObjectScreenPos(obj);
            obj.draw(g2);
        }
        player.draw(g2);
    }
}
