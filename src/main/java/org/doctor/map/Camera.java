package org.doctor.map;

import org.doctor.Game;
import org.doctor.scene.entity.Entity;
import org.doctor.scene.entity.Player;
import org.doctor.scene.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera{
    Point desloc = new Point();
    Entity entity;

    public Camera(Entity entity, MapConfig mapConfig) {
        this.entity = entity;
        this.mapConfig = mapConfig;
    }

    MapConfig mapConfig;


    public void update(){
        desloc.x = entity.worldPosition.x / mapConfig.scaledTileSize.x;
        desloc.y = entity.worldPosition.y / mapConfig.scaledTileSize.y;
        desloc.x -= entity.screenPosition.x / mapConfig.scaledTileSize.x;
        desloc.y -= entity.screenPosition.y / mapConfig.scaledTileSize.y;
        if (desloc.x < 0)
            desloc.x = 0;
        if (desloc.y < 0)
            desloc.y = 0;
    }

    public void drawLayer(Graphics2D g2, Layer layer){
        int maxScreenGridY = (int) (Game.HEIGHT / mapConfig.scaledTileSize.y);
        int maxScreenGridX = (int) (Game.WIDTH / mapConfig.scaledTileSize.x);
        for (int y_desloc = desloc.y - 1, y = 0; y < maxScreenGridY + 2; y++, y_desloc++){
            if (y_desloc < 0)
                y_desloc = 0;
            if (y_desloc >= layer.tiles.size())
                break ;
            for (int x_desloc = desloc.x - 1, x = 0; x <  maxScreenGridX + 2; x++, x_desloc++){
                if (x_desloc < 0)
                    x_desloc = 0;
                if (x_desloc >= layer.tiles.get(y_desloc).size())
                    break;
                Point location = new Point(
                        layer.tiles.get(y_desloc).get(x_desloc).location.x * mapConfig.tileSize.x,
                        layer.tiles.get(y_desloc).get(x_desloc).location.y * mapConfig.tileSize.y
                );
                BufferedImage sprite;
                if (location.x < 0 ||  location.y < 0){
                    sprite = mapConfig.defaultBlock;
                }else{
                    sprite = mapConfig.spriteSheet.getSubimage(location.x, location.y,
                            mapConfig.tileSize.x, mapConfig.tileSize.y);
                }
                g2.drawImage(sprite,
                        (x_desloc * mapConfig.scaledTileSize.x) - entity.worldPosition.x + entity.screenPosition.x,
                        (y_desloc * mapConfig.scaledTileSize.y) - entity.worldPosition.y + entity.screenPosition.y,
                        mapConfig.scaledTileSize.x, mapConfig.scaledTileSize.y, null);
            }
        }
    }

    public void setObjectScreenPos(SuperObject obj){
        obj.screenPosition.x = obj.worldPosition.x - entity.worldPosition.x + entity.screenPosition.x;
        obj.screenPosition.y = obj.worldPosition.y - entity.worldPosition.y + entity.screenPosition.y;
    }
}