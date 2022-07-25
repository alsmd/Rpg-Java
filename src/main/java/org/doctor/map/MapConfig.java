package org.doctor.map;

import org.doctor.scene.object.ChestObject;
import org.doctor.scene.object.ObjectConfig;
import org.doctor.scene.object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MapConfig {
    public BufferedImage spriteSheet;
    public BufferedImage defaultBlock;
    public String spriteSheetPath;
    public String mapPath;
    public ArrayList<Layer> layers = new ArrayList<>();

    String mapArray[][] = {
            {"0,0 1", "1,0 1", "1,0 1", "1,0 1", "6,3 1", "1,0 1", "1,0 1", "1,0 1", "1,0 1", "4,4 1", "1,0 1", "1,0 1", "1,0 1", "1,0 1", "1,0 1", "2,0 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,3 1", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,1 1", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "1,1 0", "2,1 1"},
            {"0,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "1,2 1", "2,2 1"},
    };
    public Point size = new Point(16, 30);
    public Point tileSize = new Point(16, 16);
    public int scale = 3;
    public Point scaledTileSize = new Point(tileSize.x * scale, tileSize.y * scale);
    WorldMap worldMap;
    public MapConfig(WorldMap worldMap){
        try{
            this.worldMap = worldMap;
             spriteSheet = ImageIO.read(new FileInputStream("images/simple_block_sheet.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // GETTERS

    public Layer[] getLayers(){
        Layer layers[] = new Layer[1];
        layers[0] = new Layer(this);
        return layers;
    }

    public SuperObject[] getObjects(){
        SuperObject objects[] = new SuperObject[1];
        ObjectConfig objConfig = new ObjectConfig(new Point(1 * scaledTileSize.x,1 * scaledTileSize.y), "images/chests.png");
        objConfig.addAnimation("iddle", 2, tileSize, new Point(1, 0), new Point(1, 0));
        objConfig.addAnimation("open", 3, tileSize, new Point(0, 0), new Point(0, 0));
        objects[0] = new ChestObject(objConfig, worldMap);
        objects[0].initCollitionComponent(new Rectangle(10, 28, 30, 15));
        return objects;
    }



}
