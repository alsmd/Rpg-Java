package org.doctor.map;

import org.doctor.KeyHandler;
import org.doctor.entity.Player;
import org.doctor.object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WorldMap{
    public final Point maxScreenGrid = new Point(12, 12);
    Camera camera = new Camera(this);
    public Layer layers[];
    public SuperObject objects[];
    public MapConfig mapConfig = new MapConfig(this);
    public Player player;
    public KeyHandler keyH;
    public Point screenSize = new Point(maxScreenGrid.x * mapConfig.scaledTileSize.x, maxScreenGrid.y * mapConfig.scaledTileSize.y);
    JPanel jPanel;
    public WorldMap(KeyHandler keyH, JPanel jPanel){
        layers = mapConfig.getLayers();
        objects = mapConfig.getObjects();
        this.keyH = keyH;
        this.jPanel = jPanel;
        player = new Player(this);
        player.initCollitionComponent(new Rectangle(10, 20, 28, 22), this);
        jPanel.setPreferredSize(new Dimension(screenSize.x, screenSize.y));
    }
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
