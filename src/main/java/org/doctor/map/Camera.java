package org.doctor.map;

import org.doctor.Game;
import org.doctor.config.MapConfig;
import org.doctor.config.TileMapConfig;
import org.doctor.scene.entity.Entity;
import org.doctor.scene.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera{
    Point desloc = new Point();
    Entity entity;
    TileMapConfig tileMapConfig;

    public Camera(Entity entity, TileMapConfig tileMapConfig) {
        this.entity = entity;
        this.tileMapConfig = tileMapConfig;
    }



    public void update(){
        desloc.x = entity.worldPosition.x / tileMapConfig.tileSizeScaled;
        desloc.y = entity.worldPosition.y / tileMapConfig.tileSizeScaled;
        desloc.x -= entity.screenPosition.x / tileMapConfig.tileSizeScaled;
        desloc.y -= entity.screenPosition.y / tileMapConfig.tileSizeScaled;
        if (desloc.x < 0)
            desloc.x = 0;
        if (desloc.y < 0)
            desloc.y = 0;
    }

    public Point screenToWorld(Point screeenPoint){
        Point wp = new Point(
                screeenPoint.x - entity.screenPosition.x + entity.worldPosition.x,
                screeenPoint.y - entity.screenPosition.y + entity.worldPosition.y
        );
        return wp;
    }

    public void drawLayer(Graphics2D g2, TileMapConfig.Tile layer[][], TileMapConfig config){
        int maxScreenGridY = (int) (Game.HEIGHT / tileMapConfig.tileSizeScaled);
        int maxScreenGridX = (int) (Game.WIDTH / tileMapConfig.tileSizeScaled);
        for (int y_desloc = desloc.y - 1, y = 0; y < maxScreenGridY + 2; y++, y_desloc++){
            if (y_desloc < 0)
                y_desloc = 0;
            if (y_desloc >= config.height)
                break ;
            for (int x_desloc = desloc.x - 1, x = 0; x <  maxScreenGridX + 2; x++, x_desloc++){
                if (x_desloc < 0)
                    x_desloc = 0;
                if (x_desloc >= config.width)
                    break;
                Point location = new Point(
                        layer[y_desloc][x_desloc].location.x * tileMapConfig.tileSize,
                        layer[y_desloc][x_desloc].location.y * tileMapConfig.tileSize
                );
                BufferedImage sprite;
                if (location.x < 0 ||  location.y < 0){
                    sprite = tileMapConfig.getDefaultBlock();
                }else{
                    sprite = tileMapConfig.getSpriteSheet().getSubimage(location.x, location.y,
                            tileMapConfig.tileSize, tileMapConfig.tileSize);
                }
                g2.drawImage(sprite,
                        (x_desloc * tileMapConfig.tileSizeScaled) - entity.worldPosition.x + entity.screenPosition.x,
                        (y_desloc * tileMapConfig.tileSizeScaled) - entity.worldPosition.y + entity.screenPosition.y,
                        tileMapConfig.tileSizeScaled, tileMapConfig.tileSizeScaled, null);
            }
        }
    }
    public void drawCollitionLayer(Graphics2D g2, TileMapConfig.Tile layer[][], TileMapConfig config){
        int maxScreenGridY = (int) (Game.HEIGHT / tileMapConfig.tileSizeScaled);
        int maxScreenGridX = (int) (Game.WIDTH / tileMapConfig.tileSizeScaled);
        for (int y_desloc = desloc.y - 1, y = 0; y < maxScreenGridY + 2; y++, y_desloc++){
            if (y_desloc < 0)
                y_desloc = 0;
            if (y_desloc >= config.height)
                break ;
            for (int x_desloc = desloc.x - 1, x = 0; x <  maxScreenGridX + 2; x++, x_desloc++){
                if (x_desloc < 0)
                    x_desloc = 0;
                if (x_desloc >= config.width)
                    break;
                if (layer[y_desloc][x_desloc].collition){
                    var hitbox = new Rectangle(
                            (x_desloc * tileMapConfig.tileSizeScaled) - entity.worldPosition.x + entity.screenPosition.x + layer[y_desloc][x_desloc].hitbox.x,
                            (y_desloc * tileMapConfig.tileSizeScaled) - entity.worldPosition.y + entity.screenPosition.y + layer[y_desloc][x_desloc].hitbox.y,
                            layer[y_desloc][x_desloc].hitbox.width, layer[y_desloc][x_desloc].hitbox.heigth
                    );
                    g2.setColor(new Color(234, 3, 3, 148));
                    g2.fill(hitbox);
                    g2.draw(hitbox);
                }
            }
        }
    }


    public void setObjectScreenPos(SuperObject obj){
        obj.screenPosition.x = obj.worldPosition.x - entity.worldPosition.x + entity.screenPosition.x;
        obj.screenPosition.y = obj.worldPosition.y - entity.worldPosition.y + entity.screenPosition.y;
    }
}