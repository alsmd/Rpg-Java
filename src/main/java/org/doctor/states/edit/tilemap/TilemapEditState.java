package org.doctor.states.edit.tilemap;

import org.doctor.Game;
import org.doctor.config.TileMapConfig;
import org.doctor.map.Camera;
import org.doctor.scene.entity.CameraMan;
import org.doctor.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TilemapEditState extends State {

    // Gui
    TileMapEditGui gui = new TileMapEditGui();

    //
    TileMapConfig.Tile.Position selectTilePos = new TileMapConfig.Tile.Position(-1, -1);
    int  saved = 0;
    SelectTileState selectTileState;

    // MOVIMENT
    CameraMan cameraMan;
    Camera camera;
    public TileMapConfig tileMapConfig = null;
    ArrayList<TileMapConfig.Tile> tilesSelect = new ArrayList<>();
    // Constructors
    public TilemapEditState(Game game) {
        super(game);
        game.pushState(new GetConfigState(game, this));
        panel.add(gui);
    }

    // EVENTS
    @Override
    public void onClose() {

    }
    @Override
    public void onBuild(){
        game.pushState(new GetConfigState(game, this));
    }

    @Override
    public void onBack() {
        if (selectTileState != null)
            selectTilePos = selectTileState.getSelectTilePos();
        tilesSelect.clear();
    }


    /**
     *
     *  *********** DRAW ***************
     *
     */

    @Override
    public void draw(Graphics2D g2){
        clearWindow(g2);
        if (tileMapConfig == null || camera == null || cameraMan == null)
            return ;
        if (gui.getIndexVisibleLayer() == -1)
            drawAllLayers(g2);
        else
            drawLayer(g2, tileMapConfig.map[gui.getIndexVisibleLayer()]);
        // DRAW GUI COMPONENTS
        super.draw(g2);
        drawSelectedTile(g2);
        drawTilesSelected(g2);
        drawSaved(g2);
    }

    private void clearWindow(Graphics2D g2){
        g2.setBackground(Color.black);
        g2.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    private void drawAllLayers(Graphics2D g2){
        for (TileMapConfig.Tile layer[][] : tileMapConfig.map){
            camera.drawLayer(g2, layer, tileMapConfig);
        }
        if (gui.getShowCollition()){
            for (TileMapConfig.Tile layer[][] : tileMapConfig.map){
                camera.drawCollitionLayer(g2, layer, tileMapConfig);
            }
        }
    }

    private void drawLayer(Graphics2D g2, TileMapConfig.Tile layer[][]){
        camera.drawLayer(g2, layer, tileMapConfig);
        if (gui.getShowCollition())
            camera.drawCollitionLayer(g2, layer, tileMapConfig);
    }

    private void drawSelectedTile(Graphics2D g2){
        if (selectTilePos.x != -1 && selectTilePos.y != -1){
            BufferedImage selectedTile = tileMapConfig.spriteSheet.getSubimage(selectTilePos.x * tileMapConfig.tileSize, selectTilePos.y * tileMapConfig.tileSize, tileMapConfig.tileSize, tileMapConfig.tileSize);
            g2.drawImage(
                    selectedTile,
                    game.mousePos.x - tileMapConfig.tileSizeScaled / 2,
                    game.mousePos.y - tileMapConfig.tileSizeScaled / 2,
                    tileMapConfig.tileSizeScaled,
                    tileMapConfig.tileSizeScaled, null);
        }
    }

    private void drawTilesSelected(Graphics2D g2){
        for (var tile : tilesSelect){
            var rec = new Rectangle(
                    (tile.x * tileMapConfig.tileSizeScaled) - cameraMan.worldPosition.x + cameraMan.screenPosition.x,
                    (tile.y * tileMapConfig.tileSizeScaled) - cameraMan.worldPosition.y + cameraMan.screenPosition.y,
                    tileMapConfig.tileSizeScaled,
                    tileMapConfig.tileSizeScaled);
            g2.setColor(new Color(114, 225, 195, 157));
            g2.fill(rec);
            g2.draw(rec);
        }
    }

    private void drawSaved(Graphics2D g2){
        g2.setColor(Color.cyan);
        if (saved > 0){
            g2.drawString("Saved!", 30, game.HEIGHT - 30);
            saved--;
        }
    }

    /**
     *
     *  *********** UPDATE ***************
     *
     */

    @Override
    public void update(){
        super.update();
        if (cameraMan == null){
            cameraMan = new CameraMan(tileMapConfig, new Point(0, 0), game.keyH);
            camera  = new Camera(cameraMan, tileMapConfig);
        }else
            updateEdit();
    }

    public void updateEdit(){
        if (game.keyH.save && tilesSelect.size() == 0){
            if (saved == 0){
                try{
                    tileMapConfig.save();
                    saved = Game.frames;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            cameraMan.update();
            camera.update();
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        if (Game.cliked.contains(e.getPoint()))
            return ;
        if (SwingUtilities.isRightMouseButton(e)){
            selectTilePos.x = -1;
            selectTilePos.y = -1;
        }else
            updateGridMap(e.getPoint());
    }
    @Override
    public void mouseDragged(MouseEvent e){
        super.mouseDragged(e);
        if (Game.cliked.contains(e.getPoint()))
            return ;
        if (SwingUtilities.isRightMouseButton(e)){
            selectTilePos.x = -1;
            selectTilePos.y = -1;
        }else
            updateGridMap(e.getPoint());
    }
    public void updateGridMap(Point point){
        Point gridPos = camera.screenToWorld(point);
        gridPos.x /= tileMapConfig.tileSizeScaled;
        gridPos.y /= tileMapConfig.tileSizeScaled;
        if (gui.getCurrentMode() == TileMapEditGui.MODE.ADDTILES){
            updateAddTileMode(gridPos);
        }else if (gui.getCurrentMode() == TileMapEditGui.MODE.ADDCOLLITION)
            updateAddCollitionMode(gridPos);
    }

    public void updateAddTileMode(Point gridPos){
        if (isInsideGridMap(gridPos)){
            TileMapConfig.Tile tile = tileMapConfig.map[gui.getIndexActiveLayer()][gridPos.y][gridPos.x];
            tile.location = new TileMapConfig.Tile.Position(selectTilePos.x, selectTilePos.y);
        }
    }

    public void updateAddCollitionMode(Point gridPos){
        if (isInsideGridMap(gridPos)){
            TileMapConfig.Tile tile = tileMapConfig.map[gui.getIndexActiveLayer()][gridPos.y][gridPos.x];
           if (!tilesSelect.contains(tile))
               tilesSelect.add(tile);
           if (!game.keyH.control)
               game.pushState(new EditCollitionState(game, tilesSelect, tileMapConfig));
        }
    }

    public boolean isInsideGridMap(Point gridPos){
        return gridPos.x < tileMapConfig.width && gridPos.y < tileMapConfig.height && gridPos.x >=0 && gridPos.y >= 0;
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
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        if (e.getKeyCode() == KeyEvent.VK_CONTROL && tilesSelect.size() > 0)
            game.pushState(new EditCollitionState(game, tilesSelect, tileMapConfig));
    }
}
