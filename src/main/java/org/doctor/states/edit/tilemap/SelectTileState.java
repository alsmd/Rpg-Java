package org.doctor.states.edit.tilemap;

import org.doctor.Game;
import org.doctor.config.TileMapConfig;
import org.doctor.states.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SelectTileState extends State {
    TileMapConfig.Tile.Position selectTilePos = new TileMapConfig.Tile.Position();
    TileMapConfig tileMapConfig;
    public SelectTileState(Game game, TileMapConfig tileMapConfig) {
        super(game);
        this.tileMapConfig = tileMapConfig;
    }

    @Override
    public void onClose() {

    }

    @Override
    public void onBuild() {

    }

    @Override
    public void onBack() {

    }

    public TileMapConfig.Tile.Position getSelectTilePos(){
        return selectTilePos;
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        g2.drawImage(
                tileMapConfig.spriteSheet,
                0,
                0,
                tileMapConfig.spriteSheet.getWidth() * tileMapConfig.scale,
                tileMapConfig.spriteSheet.getHeight() * tileMapConfig.scale,
                null);
    }
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        Point point = e.getPoint();
        point.x /= tileMapConfig.tileSizeScaled;
        point.y /= tileMapConfig.tileSizeScaled;
        if (point.x < 0 || point.y < 0 ||
                point.x >= tileMapConfig.spriteSheet.getWidth() / tileMapConfig.tileSize
                || point.y >= tileMapConfig.spriteSheet.getHeight() / tileMapConfig.tileSize)
            selectTilePos = new TileMapConfig.Tile.Position(-1, -1);
        else {
            selectTilePos = new TileMapConfig.Tile.Position(point.x, point.y);
        }
        System.out.println(selectTilePos.x);
        System.out.println(selectTilePos.y);
    }

    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            game.popState();
        }
    }

}
