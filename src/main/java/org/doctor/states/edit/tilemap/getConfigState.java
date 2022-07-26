package org.doctor.states.edit.tilemap;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.doctor.Game;
import org.doctor.config.TileMapConfig;
import org.doctor.gui.GuiForm;
import org.doctor.gui.MenuPanel;
import org.doctor.states.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class getConfigState extends State {
    //GUI
    MenuPanel menu;
    GuiForm newForm;
    GuiForm loadForm;
    // Helpers
    int inputWidth = 300;
    int inputHeight = 45;
    int spacing = 10;

    //Informations
    TilemapEditState editState;
    ObjectNode data;

    getConfigState(Game game, TilemapEditState editState){
        super(game);
        this.editState = editState;
        initMenu();
    }

    // SETUP
    public void initMenu(){
        menu = new MenuPanel(220, 60, 160, 90);
        menu.addButton("New", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                initNewForm();
            }
        });
        menu.addButton("Load", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                initLoadForm();
            }
        });
        panel.add(menu);
    }

    public void initNewForm(){
        newForm = new GuiForm(inputWidth, inputHeight, 20, spacing);
        newForm.addInput("Map's name", "mapName");
        newForm.addInput("SpriteSheet's file name", "spriteSheetPath");
        newForm.addInput("Tile's size", "tileSize");
        newForm.addInput("Tile's scale", "scale");
        newForm.addInput("Map's Width (Block qnt)", "width");
        newForm.addInput("Map's Height (Block qnt)", "height");
        newForm.build();
        panel.add(newForm);
    }
    public void initLoadForm(){
        loadForm = new GuiForm(inputWidth, inputHeight, 20, spacing);
        loadForm.addInput("Map's name", "mapName");
        loadForm.build();
        panel.add(loadForm);
    }



    @Override
    public void onClose() {

    }

    @Override
    public void onBuild() {

    }

    @Override
    public void update(){
        super.update();
        if (newForm != null){
            updateNewForm();
        }else if (loadForm != null)
            updateLoadForm();
    }

    public void updateNewForm(){
        if (newForm.isReady()){
            data = newForm.getData();
            newForm.clear();
            try{
                editState.tileMapConfig = Game.objectMapper.treeToValue(data, TileMapConfig.class);
                editState.tileMapConfig.map = new TileMapConfig.Tile[Game.LAYERS][editState.tileMapConfig.height][editState.tileMapConfig.width];
                editState.tileMapConfig.tileSizeScaled = editState.tileMapConfig.tileSize * editState.tileMapConfig.scale;
                for (TileMapConfig.Tile[][] layer : editState.tileMapConfig.map){
                    for (TileMapConfig.Tile[] y : layer){
                        for (int x = 0; x < editState.tileMapConfig.width; x++){
                            y[x] = new TileMapConfig.Tile();
                            y[x].collition = false;
                            y[x].location = new TileMapConfig.Tile.Position(-1, -1);
                            y[x].hitbox = new TileMapConfig.Tile.Hitbox(0, 0, editState.tileMapConfig.tileSizeScaled, editState.tileMapConfig.tileSizeScaled);
                        }
                    }
                }
                editState.tileMapConfig.save();
                editState.tileMapConfig.build();
                panel.remove(newForm);
                newForm = null;
                game.popState();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void updateLoadForm(){
        if (loadForm.isReady()){
            data = loadForm.getData();
            System.out.println(data.get("mapName"));
            loadForm.clear();
            try{
                editState.tileMapConfig = Game.objectMapper.readValue(new File(data.get("mapName").asText() + ".json"), TileMapConfig.class);
                editState.tileMapConfig.build();
                panel.remove(newForm);
                game.popState();
            }catch (Exception e){
                e.printStackTrace();
            }
            loadForm = null;
        }
    }


}
