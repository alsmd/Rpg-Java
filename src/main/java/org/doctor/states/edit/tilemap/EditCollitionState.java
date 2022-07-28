package org.doctor.states.edit.tilemap;

import org.doctor.Game;
import org.doctor.config.TileMapConfig;
import org.doctor.gui.GuiCheckbox;
import org.doctor.gui.GuiForm;
import org.doctor.states.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EditCollitionState extends State {
    GuiForm collitionForm =  new GuiForm(50, 30, Game.WIDTH / 2, Game.HEIGHT / 4, 5);
    ArrayList<TileMapConfig.Tile> tiles;
    TileMapConfig tileMapConfig;
    GuiCheckbox hasCollition = new GuiCheckbox("Has Collition", 60, Game.HEIGHT / 2, 30, 30);
    public EditCollitionState(Game game, ArrayList<TileMapConfig.Tile> tiles, TileMapConfig tileMapConfig) {
        super(game);
        this.tileMapConfig = tileMapConfig;
        this.tiles = tiles;

        setupCollitionForm();
        hasCollition.isActive = tiles.get(0).collition;
        panel.add(hasCollition);
    }

    public void setupCollitionForm(){
        collitionForm = new GuiForm(75, 30, Game.WIDTH / 2 - 75 / 2, Game.HEIGHT / 3, 5);
        collitionForm.addInput("x", "x", Integer.toString(tiles.get(0).hitbox.x));
        collitionForm.addInput("y", "y", Integer.toString(tiles.get(0).hitbox.y));
        collitionForm.addInput("Width", "width", Integer.toString(tiles.get(0).hitbox.width));
        collitionForm.addInput("Height", "height", Integer.toString(tiles.get(0).hitbox.heigth));
        collitionForm.setMode(GuiForm.MODE.HORIZONTAL);
        panel.add(collitionForm);
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setColor(new Color(5, 5, 5, 202));
        var b = new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT);
        g2.fill(b);
        g2.draw(b);
        super.draw(g2);
        TileMapConfig.Tile tileObj = tiles.get(0);
        BufferedImage cuurentTileImag;
        if (tileObj.location.x < 0 ||  tileObj.location.y < 0){
            cuurentTileImag = tileMapConfig.getDefaultBlock();
        }else{
            cuurentTileImag=  tileMapConfig.spriteSheet.getSubimage(
                    tileObj.location.x * tileMapConfig.tileSize,
                    tileObj.location.y * tileMapConfig.tileSize,
                    tileMapConfig.tileSize, tileMapConfig.tileSize );
        }
        g2.drawImage(cuurentTileImag, Game.WIDTH / 2, Game.HEIGHT / 2, tileMapConfig.tileSizeScaled, tileMapConfig.tileSizeScaled, null);
        // HITBOX
        var data = collitionForm.getData();
        var hitbox = new Rectangle(Game.WIDTH / 2 +  tiles.get(0).hitbox.x, Game.HEIGHT / 2 +  tiles.get(0).hitbox.y,  tiles.get(0).hitbox.width,  tiles.get(0).hitbox.heigth);
        for (TileMapConfig.Tile tile : tiles){
            tile.hitbox.x = data.get("x").asInt();
            tile.hitbox.y = data.get("y").asInt();
            tile.hitbox.width = data.get("width").asInt();
            tile.hitbox.heigth = data.get("height").asInt();
        }
        g2.setColor(new Color(234, 3, 3, 148));
        g2.fill(hitbox);
        g2.draw(hitbox);
    }
    @Override
    public void update(){
        super.update();
        for (var tile : tiles)
            tile.collition = hasCollition.isActive;
    }

    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            game.popState();
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
}
