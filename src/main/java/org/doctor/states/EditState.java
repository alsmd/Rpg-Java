package org.doctor.states;

import org.doctor.Game;
import org.doctor.gui.GuiInput;
import org.doctor.map.MapConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // Constructors
    public EditState(Game game) {
        super(game);
        setUpInputs();
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
    }


}
