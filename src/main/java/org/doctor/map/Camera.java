package org.doctor.map;

import org.doctor.scene.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera{
    Point desloc = new Point();
    WorldMap worldMap;

    public Camera(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void update(){
        desloc.x = worldMap.player.worldPosition.x / this.worldMap.mapConfig.scaledTileSize.x;
        desloc.y = worldMap.player.worldPosition.y / this.worldMap.mapConfig.scaledTileSize.y;
        desloc.x -= worldMap.player.screenPosition.x / this.worldMap.mapConfig.scaledTileSize.x;
        desloc.y -= worldMap.player.screenPosition.y / this.worldMap.mapConfig.scaledTileSize.y;
        if (desloc.x < 0)
            desloc.x = 0;
        if (desloc.y < 0)
            desloc.y = 0;
    }

    public void drawLayer(Graphics2D g2, Layer layer){
        for (int y_desloc = desloc.y - 1, y = 0; y < worldMap.maxScreenGrid.y + 2; y++, y_desloc++){
            if (y_desloc < 0)
                y_desloc = 0;
            if (y_desloc >= layer.tiles.size())
                break ;
            for (int x_desloc = desloc.x - 1, x = 0; x <  worldMap.maxScreenGrid.x + 2; x++, x_desloc++){
                if (x_desloc < 0)
                    x_desloc = 0;
                if (x_desloc >= layer.tiles.get(y_desloc).size())
                    break;
                Point location = new Point(
                        layer.tiles.get(y_desloc).get(x_desloc).location.x * worldMap.mapConfig.tileSize.x,
                        layer.tiles.get(y_desloc).get(x_desloc).location.y * worldMap.mapConfig.tileSize.y
                );
                BufferedImage sprite = worldMap.mapConfig.spriteSheet.getSubimage(location.x, location.y,
                        worldMap.mapConfig.tileSize.x, worldMap.mapConfig.tileSize.y);
                g2.drawImage(sprite,
                        (x_desloc * worldMap.mapConfig.scaledTileSize.x) - worldMap.player.worldPosition.x + worldMap.player.screenPosition.x,
                        (y_desloc * worldMap.mapConfig.scaledTileSize.y) - worldMap.player.worldPosition.y + worldMap.player.screenPosition.y,
                        worldMap.mapConfig.scaledTileSize.x, worldMap.mapConfig.scaledTileSize.y, null);
            }
        }
    }

    public void setObjectScreenPos(SuperObject obj){
        obj.screenPosition.x = obj.worldPosition.x - worldMap.player.worldPosition.x + worldMap.player.screenPosition.x;
        obj.screenPosition.y = obj.worldPosition.y - worldMap.player.worldPosition.y + worldMap.player.screenPosition.y;
    }
}