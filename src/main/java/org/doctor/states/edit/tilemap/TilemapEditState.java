package org.doctor.states.edit.tilemap;

import org.doctor.Game;
import org.doctor.config.TileMapConfig;
import org.doctor.gui.GuiDropbox;
import org.doctor.map.Camera;
import org.doctor.scene.entity.CameraMan;
import org.doctor.states.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class TilemapEditState extends State {
    // MOVIMENT
    CameraMan cameraMan;
    Camera camera;
    public TileMapConfig tileMapConfig = null;
    GuiDropbox modeDropbox = new GuiDropbox("Active Layer", 50, 25, Game.WIDTH - 100, 50);
    // Constructors
    public TilemapEditState(Game game) {
        super(game);
        game.pushState(new getConfigState(game, this));
        setupDropbox();
    }
    //Setup

    public void setupDropbox(){
        modeDropbox.addButton("1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1 Ativo");
            }
        });
        modeDropbox.addButton("2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("2 Ativo");
            }
        });
        modeDropbox.addButton("3", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("3 Ativo");
            }
        });
        modeDropbox.addButton("4", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("4 Ativo");
            }
        });
        modeDropbox.addButton("5", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("5 Ativo");
            }
        });
        modeDropbox.addButton("all", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("all Ativo");
            }
        });
        panel.add(modeDropbox);
    }

    // EVENTS
    @Override
    public void onClose() {

    }
    @Override
    public void onBuild(){
        game.pushState(new getConfigState(game, this));
    }


    // LOOP
    @Override
    public void update(){
        super.update();
        if (cameraMan == null){
            cameraMan = new CameraMan(tileMapConfig, new Point(0, 0), game.keyH);
            camera  = new Camera(cameraMan, tileMapConfig);
        }else
            updateEdit();
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        Point wp = camera.screenToWorld(e.getPoint());
        wp.x /= tileMapConfig.tileSizeScaled;
        wp.y /= tileMapConfig.tileSizeScaled;
//        if (wp.x < tileMapConfig.width && wp.y < tileMapConfig.height && wp.x >=0 && wp.y >= 0){
//            tileMapConfig.map[4][wp.y][wp.x].location = new TileMapConfig.Tile.Position(0, 0);
//        }
    }
    public void updateEdit(){
        cameraMan.update();
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setBackground(Color.black);
        g2.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        if (tileMapConfig == null || camera == null || cameraMan == null)
            return ;
        for (TileMapConfig.Tile layer[][] : tileMapConfig.map){
            camera.drawLayer(g2, layer, tileMapConfig);
        }
        super.draw(g2);
    }
}
