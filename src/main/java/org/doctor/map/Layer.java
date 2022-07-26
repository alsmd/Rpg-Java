package org.doctor.map;

import org.doctor.config.MapConfig;

import java.awt.*;
import java.util.ArrayList;

public class Layer {
    MapConfig mapConfig;
    public ArrayList<ArrayList<Tile>>  tiles = new ArrayList<ArrayList<Tile>>();

    public Layer(MapConfig mapConfig){
        this.mapConfig = mapConfig;
        createTiles();
    }

    private void createTiles(){
        for (int y = 0; y < this.mapConfig.size.y; y++){
            this.tiles.add(new ArrayList<Tile>());
            for (int x = 0; x < this.mapConfig.size.x; x++) {
//                String pos[] = mapConfig.mapArray[y][x].split(" ")[0].split(",");
                Tile tile = new Tile(new Point(-1, -1));
//                if (mapConfig.mapArray[y][x].split(" ")[1].contentEquals("1"))
//                    tile.collition = true;

                this.tiles.get(y).add(tile);
//                tile.hitbox = new Rectangle(
//                        new Point(x * mapConfig.scaledTileSize.x, y * mapConfig.scaledTileSize.x),
//                        new Dimension(mapConfig.scaledTileSize.x, mapConfig.scaledTileSize.y)
//                );
            }
        }
    }

    static public class Tile{
        /// Informations
        public boolean collition;
        public Point location;

        ///
        public Rectangle hitbox;

        public Tile(Point location){
            this.location = location;
        }

        public Tile(){}
    }
}