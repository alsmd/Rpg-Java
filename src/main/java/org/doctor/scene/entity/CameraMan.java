package org.doctor.scene.entity;

import org.doctor.Game;
import org.doctor.KeyHandler;
import org.doctor.map.MapConfig;

import java.awt.*;

public class CameraMan extends Entity{

    MapConfig mapConfig;
    KeyHandler keyH;
    public CameraMan(MapConfig mapConfig, Point worldPosition, KeyHandler keyH) {
        super(null, worldPosition);
        this.mapConfig = mapConfig;
        this.keyH = keyH;
        initDefautlValues();
    }

    // SETUP
    private void initDefautlValues(){
        screenPosition.x = Game.WIDTH / 2 - mapConfig.scaledTileSize.x / 2;
        screenPosition.y = Game.HEIGHT / 2 - mapConfig.scaledTileSize.y / 2;
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
