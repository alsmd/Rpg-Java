package org.doctor.components;

import org.doctor.entity.Entity;
import org.doctor.entity.Player;
import org.doctor.map.Layer;
import org.doctor.map.WorldMap;
import org.doctor.object.SuperObject;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CollitionComponent {
    WorldMap    map;
    Point worldPosition;
    Point screenPosition;

    private Rectangle hitbox;

    public CollitionComponent(Rectangle hitbox, WorldMap map,  Point worldPosition ,Point screenPosition) {
        this.worldPosition = worldPosition;
        this.screenPosition = screenPosition;
        this.map = map;
        this.hitbox =  hitbox;
    }

    public boolean checkTileCollition(){
        Rectangle entityHitbox = getWorldHitbox();
        boolean coli = false;
        int grid_y = (entityHitbox.y) / map.mapConfig.scaledTileSize.y;
        int grid_x = (entityHitbox.x)  / map.mapConfig.scaledTileSize.x ;
        ArrayList<Layer.Tile> range = createRange(grid_x, grid_y);
        for (Layer.Tile tile : range){
            if (tile.collition && (tile.hitbox.intersects(entityHitbox) || entityHitbox.intersects(tile.hitbox)))
                 coli = true;
        }
        return coli;
    }
    private ArrayList<Layer.Tile> createRange(int x, int y){
        ArrayList<Layer.Tile> range = new ArrayList<Layer.Tile>();
        add_range(range, x, y); //center
        add_range(range, x , y - 1); //up
        add_range(range, x , y + 1); //down
        add_range(range, x - 1, y); //left
        add_range(range, x + 1, y); //right
        add_range(range, x - 1, y - 1); //topLeft
        add_range(range, x + 1, y - 1); //topRight
        add_range(range, x - 1, y + 1); //bottomLeft
        add_range(range, x + 1, y + 1); //bottomRight
        return range;
    }

    private void add_range(ArrayList<Layer.Tile> range, int x, int y){
        if (x >= 0 && y >= 0 && x < map.mapConfig.size.x && y < map.mapConfig.size.y){
            range.add(map.layers[0].tiles.get(y).get(x));
        }
    }

    public Rectangle getScreenHitbox(){
        Rectangle hitboxCpy = (Rectangle) hitbox.clone();
        hitboxCpy.x += screenPosition.x;
        hitboxCpy.y += screenPosition.y;
        return hitboxCpy;
    }

    public Rectangle getWorldHitbox(){
        Rectangle hitboxCpy = (Rectangle) hitbox.clone();
        hitboxCpy.x += worldPosition.x;
        hitboxCpy.y += worldPosition.y;
        return hitboxCpy;
    }

    public boolean checkObjectsCollition(){
        Rectangle hitboxCpy = getWorldHitbox();
        for (SuperObject obj : map.objects){
            Rectangle objHit = obj.collitionComponent.getWorldHitbox();
            if (obj.collition && hitboxCpy.intersects(objHit))
                return true;
        }
        return false;
    }

    public boolean checkSurround(CollitionComponent obj){
        Rectangle hitboxLeft = getWorldHitbox();
        hitboxLeft.x -= hitbox.width / 2;
        Rectangle hitboxRight = getWorldHitbox();
        hitboxRight.x +=  hitbox.width / 2;
        Rectangle hitboxUp = getWorldHitbox();
        hitboxUp.y -= hitbox.width / 2;
        Rectangle hitboxDown = getWorldHitbox();
        hitboxDown.y += hitbox.width / 2;
        if (obj.getWorldHitbox().intersects(hitboxDown) || obj.getWorldHitbox().intersects(hitboxUp) ||
                obj.getWorldHitbox().intersects(hitboxLeft) || obj.getWorldHitbox().intersects(hitboxRight))
            return true;
        return false;
    }
}
