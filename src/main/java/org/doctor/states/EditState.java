package org.doctor.states;

import org.doctor.Game;
import org.doctor.gui.GuiInput;
import org.doctor.map.Camera;
import org.doctor.map.Layer;
import org.doctor.map.MapConfig;
import org.doctor.scene.entity.CameraMan;
import org.doctor.scene.entity.Player;

import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EditState extends State{
    // Info
    MapConfig mapConfig = new MapConfig(null);
    ArrayList<GuiInput> infoInputs = new ArrayList<>();

    // Helpers
    int intputX = Game.WIDTH / 2 - 150;
    int intputY = 20;
    int inputWidth = 300;
    int inputHeight = 75;
    boolean next = true;
    GuiInput current = null;

    // MOVIMENT
    CameraMan cameraMan = new CameraMan(mapConfig, new Point(0, 0), game.keyH);
    Camera camera = new Camera(cameraMan, mapConfig);

    // Constructors
    public EditState(Game game) {
        super(game);
//        setUpInputs();

        //DEBUG
        setMapName("file");
        setPathSpriteSheet("images/a.png");
        setTileSize("16");
        setScale("3");
        setWidth("10");
        setHeight("10");
        initMapConfig();
    }

    // SETUP

    public void setUpInputs(){
        infoInputs.add(new GuiInput("Map's Name", intputX, intputY, inputWidth, inputHeight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMapName(e.getActionCommand());
                next = true;
            }
        }));
        infoInputs.add(new GuiInput("SpriteSheet's file name", intputX, intputY, inputWidth, inputHeight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPathSpriteSheet(e.getActionCommand());
                next = true;
            }
        }));
        infoInputs.add(new GuiInput("Tile's size", intputX, intputY, inputWidth, inputHeight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTileSize(e.getActionCommand());
                next = true;
            }
        }));
        infoInputs.add(new GuiInput("Tile's scale", intputX, intputY, inputWidth, inputHeight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setScale(e.getActionCommand());
                next = true;
            }
        }));
        infoInputs.add(new GuiInput("Map's Width (Block qnt)", intputX, intputY, inputWidth, inputHeight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWidth(e.getActionCommand());
                next = true;
            }
        }));
        infoInputs.add(new GuiInput("Map's Height (Block qnt)", intputX, intputY, inputWidth, inputHeight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHeight(e.getActionCommand());
                initMapConfig();
                next = true;
            }
        }));
    }

    public void setMapName(String name){
        mapConfig.mapPath = name + ".config";
    }

    public void setPathSpriteSheet(String path){
        mapConfig.spriteSheetPath = path;

    }

    public void setTileSize(String size){
        mapConfig.tileSize.x = Integer.parseInt(size);
        mapConfig.tileSize.y = Integer.parseInt(size);
    }

    public void setScale(String scale){
        mapConfig.scale = Integer.parseInt(scale);
    }

    public void setWidth(String width){
        mapConfig.size.x = Integer.parseInt(width);
    }
    public void setHeight(String height){
        mapConfig.size.x = Integer.parseInt(height);
    }

    public void initMapConfig(){
        mapConfig.defaultBlock = new BufferedImage(mapConfig.tileSize.x, mapConfig.tileSize.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = (Graphics2D) mapConfig.defaultBlock.getGraphics();
        g2.setColor(Color.gray);
        g2.fillRect(0, 0, mapConfig.tileSize.x, mapConfig.tileSize.y);
        g2.dispose();
        mapConfig.scaledTileSize = new Point(mapConfig.tileSize.x * mapConfig.scale, mapConfig.tileSize.y * mapConfig.scale);
        mapConfig.layers.add(new Layer(mapConfig));
    }
    // EVENTS
    @Override
    public void onClose() {

    }

    @Override
    public void update(){
        super.update();
        if (keyH.pause)
            game.pushState(new PauseMenuState(game));
        if (next){
            if (current != null)
                panel.remove(current);
            if (!infoInputs.isEmpty()){
                current = infoInputs.get(0);
                panel.add(current);
                infoInputs.remove(0);
            }
            next = false;
        }
        if (infoInputs.isEmpty()){
            cameraMan.update();
        }
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        if (infoInputs.isEmpty()){
            for (Layer layer : mapConfig.layers){
                camera.drawLayer(g2, layer);
            }
        }
    }


}
