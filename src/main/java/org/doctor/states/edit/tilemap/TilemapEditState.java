package org.doctor.states.edit.tilemap;

import org.doctor.Game;
import org.doctor.KeyHandler;
import org.doctor.config.TileMapConfig;
import org.doctor.gui.GuiDropbox;
import org.doctor.map.Camera;
import org.doctor.scene.entity.CameraMan;
import org.doctor.states.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TilemapEditState extends State {
    // Select
    int indexVisibleLayer = -1; // all layers if -1
    int indexActiveLayer = 0;
    SelectTileState selectTileState;
    TileMapConfig.Tile.Position selectTilePos = new TileMapConfig.Tile.Position(-1, -1);

    //

    int  saved = 0;
    // MOVIMENT
    CameraMan cameraMan;
    Camera camera;
    public TileMapConfig tileMapConfig = null;
    GuiDropbox viewLayerDropbox; // to draw
    GuiDropbox activeLayerDropbox; //to update
    // Constructors
    public TilemapEditState(Game game) {
        super(game);
        game.pushState(new getConfigState(game, this));
        setupVisibleLayerMenu();
        activeLayerDropbox();
    }
    //Setup

    public void setupVisibleLayerMenu(){
        viewLayerDropbox = new GuiDropbox("Layer in view", 50, 25, Game.WIDTH - 100, 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().contentEquals("all")){
                   indexVisibleLayer = -1;
                }else{
                    indexVisibleLayer = Integer.parseInt(e.getActionCommand()) - 1;
                }
            }
        });
        viewLayerDropbox.addField("1");
        viewLayerDropbox.addField("2");
        viewLayerDropbox.addField("3");
        viewLayerDropbox.addField("4");
        viewLayerDropbox.addField("5");
        viewLayerDropbox.addField("all");
        viewLayerDropbox.setDefault("all");
        panel.add(viewLayerDropbox);
    }

    public void activeLayerDropbox(){
        activeLayerDropbox = new GuiDropbox("Edit Layer", 50, 25, 50, 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexActiveLayer = Integer.parseInt(e.getActionCommand()) - 1;
            }
        });
        activeLayerDropbox.addField("1");
        activeLayerDropbox.addField("2");
        activeLayerDropbox.addField("3");
        activeLayerDropbox.addField("4");
        activeLayerDropbox.addField("5");
        panel.add(activeLayerDropbox);
    }


    // EVENTS
    @Override
    public void onClose() {

    }
    @Override
    public void onBuild(){
        game.pushState(new getConfigState(game, this));
    }

    @Override
    public void onBack() {
        if (selectTileState != null)
            selectTilePos = selectTileState.getSelectTilePos();
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
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (selectTileState == null)
                selectTileState = new SelectTileState(game, tileMapConfig);
            game.pushState(selectTileState);
        }
    }


    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        updateTile(e.getPoint());
     }
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        updateTile(e.getPoint());
    }

    public void updateTile(Point point){
        Point wp = camera.screenToWorld(point);
        wp.x /= tileMapConfig.tileSizeScaled;
        wp.y /= tileMapConfig.tileSizeScaled;
        if (wp.x < tileMapConfig.width && wp.y < tileMapConfig.height && wp.x >=0 && wp.y >= 0){
            tileMapConfig.map[indexActiveLayer][wp.y][wp.x].location = new TileMapConfig.Tile.Position(selectTilePos.x, selectTilePos.y);
        }
    }

    public void updateEdit(){
        if (game.keyH.save){
            if (saved == 0){
                try{
                    tileMapConfig.save();
                    saved = Game.frames;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else
            cameraMan.update();
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setBackground(Color.black);
        g2.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        if (tileMapConfig == null || camera == null || cameraMan == null)
            return ;
        if (indexVisibleLayer == -1){
            for (TileMapConfig.Tile layer[][] : tileMapConfig.map){
                camera.drawLayer(g2, layer, tileMapConfig);
            }
        }else
            camera.drawLayer(g2, tileMapConfig.map[indexVisibleLayer], tileMapConfig);
        super.draw(g2);
        if (selectTilePos.x != -1 && selectTilePos.y != -1){
            BufferedImage selectedTile = tileMapConfig.spriteSheet.getSubimage(selectTilePos.x * tileMapConfig.tileSize, selectTilePos.y * tileMapConfig.tileSize, tileMapConfig.tileSize, tileMapConfig.tileSize);
            g2.drawImage(
                    selectedTile,
                    game.mousePos.x - tileMapConfig.tileSizeScaled / 2,
                    game.mousePos.y - tileMapConfig.tileSizeScaled / 2,
                    tileMapConfig.tileSizeScaled,
                    tileMapConfig.tileSizeScaled, null);
        }
        g2.setColor(Color.cyan);
        if (saved > 0){
            g2.drawString("Saved!", 30, game.HEIGHT - 30);
            saved--;
        }
    }
}
