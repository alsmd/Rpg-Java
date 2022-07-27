package org.doctor.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.doctor.Game;
import org.doctor.map.Layer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TileMapConfig{
    @JsonIgnore
    public BufferedImage spriteSheet;
    @JsonIgnore
    public BufferedImage defaultBlock;

    public String mapName;
    public int tileSize;
    public int width;
    public int height;
    public int scale;
    public int tileSizeScaled;
    public Tile map[][][];
    public String spriteSheetPath = "";


    public TileMapConfig(){}

    public boolean build(){
        try{
            spriteSheet = ImageIO.read(new FileInputStream(spriteSheetPath));
            defaultBlock = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2 = (Graphics2D) defaultBlock.getGraphics();
            g2.setColor(new Color(189, 163, 163, 12));
            g2.fillRect(0, 0, tileSize, tileSize);
            g2.dispose();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void save() throws Exception{
        Game.objectMapper.writeValue(new File(this.mapName + ".json"), this);
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
    public BufferedImage getDefaultBlock() {
        return defaultBlock;
    }

    public void printInfo(){
        System.out.printf("Map's name: %s\n", mapName);
        System.out.printf("spriteSheetPath: %s\n", spriteSheetPath);
        System.out.printf("tileSize: %d\n", tileSize);
        System.out.printf("width: %d\n", width);
        System.out.printf("height: %d\n", height);
        System.out.printf("scale: %d\n", scale);
        System.out.println("Map: \n");
        if (map == null)
            return ;
        for (Tile layer[][] : map){
            System.out.println("Layer: ");
            for (Tile[] y : layer){
                for (Tile x : y){
                    if (x.collition)
                        System.out.print(Game.ANSI_RED);
                    System.out.print(x.location.x + "," + x.location.y + " ");
                    System.out.print(Game.ANSI_WHITE);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    static public class Tile{
        /// Informations
        public boolean collition;
        public Position location;

        ///
        public Hitbox hitbox;

        public Tile(Position location){
            this.location = location;
        }

        public Tile(){}





        static public class Position{
            public int x;
            public int y;

            public Position(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Position(){

            }
        }

        static public class Hitbox{
            public int x;
            public int y;
            public int width;

            public Hitbox(int x, int y, int width, int heigth) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.heigth = heigth;
            }

            public int heigth;
            public Hitbox(){

            }
        }
    }
}
