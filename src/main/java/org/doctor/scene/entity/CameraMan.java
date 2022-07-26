package org.doctor.scene.entity;

import org.doctor.Game;
import org.doctor.KeyHandler;
import org.doctor.config.MapConfig;
import org.doctor.config.TileMapConfig;

import java.awt.*;

public class CameraMan extends Entity{

    TileMapConfig tileMapConfig;
    KeyHandler keyH;
    public CameraMan(TileMapConfig tileMapConfig, Point worldPosition, KeyHandler keyH) {
        super(null, worldPosition);
        this.tileMapConfig = tileMapConfig;
        this.keyH = keyH;
        initDefautlValues();
    }

    // SETUP
    private void initDefautlValues(){
        screenPosition.x = Game.WIDTH / 2 - tileMapConfig.tileSizeScaled / 2;
        screenPosition.y = Game.HEIGHT / 2 - tileMapConfig.tileSizeScaled / 2;
        speed = 400;
    }
    // LOOP
    @Override
    public void draw(Graphics2D g2){
    }
    @Override
    public void update(){
        if (keyH.upPressed){
            worldPosition.y -= speed * (double)(1.f / 60);
        }
        else if (keyH.downPressed){
            worldPosition.y += speed * (double)(1.f / 60);
        }
        else if (keyH.leftPressed){
            worldPosition.x -= speed * (double)(1.f / 60);
        }
        else if (keyH.rightPressed){
            worldPosition.x += speed * (double)(1.f / 60);
        }
    }
}
