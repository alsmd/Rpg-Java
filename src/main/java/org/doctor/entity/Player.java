package org.doctor.entity;

import org.doctor.GamePanel;
import org.doctor.KeyHandler;
import org.doctor.map.WorldMap;
import org.doctor.object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{
    KeyHandler keyH;


    public Player(WorldMap worldMap) {
        this.keyH = worldMap.keyH;
        screenPosition.x = worldMap.screenSize.x / 2 - worldMap.mapConfig.scaledTileSize.x / 2;
        screenPosition.y = worldMap.screenSize.y / 2 - worldMap.mapConfig.scaledTileSize.y / 2;
        worldPosition.x = 2 * worldMap.mapConfig.scaledTileSize.x;
        worldPosition.y = 2 * worldMap.mapConfig.scaledTileSize.y;
        size.width = worldMap.mapConfig.scaledTileSize.x;
        size.height = worldMap.mapConfig.scaledTileSize.y;
        speed = 200;
        loadResources();
       this.initAnimations();
    }

    public void update(){
        Point copy = (Point) worldPosition.clone();
        if (keyH.upPressed == true){
            animationComponent.setActiveAnimation("up");
            worldPosition.y -= speed * (double)(1.f / 60);
        }
        else if (keyH.downPressed == true){
            animationComponent.setActiveAnimation("down");
            worldPosition.y += speed * (double)(1.f / 60);
        }
        else if (keyH.leftPressed == true){
            animationComponent.setActiveAnimation("left");
            worldPosition.x -= speed * (double)(1.f / 60);
        }
        else if (keyH.rightPressed == true){
            animationComponent.setActiveAnimation("right");
            worldPosition.x += speed * (double)(1.f / 60);
        }
        else
            this.animationComponent.setActiveAnimation("iddle");
        if (collitionComponent.checkTileCollition())
            worldPosition.setLocation(copy);
        if (collitionComponent.checkObjectsCollition())
            worldPosition.setLocation(copy);
        animationComponent.update();
    }
    public void loadResources(){
        try{
            spriteSheet = ImageIO.read(new FileInputStream("images/mage.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void initAnimations(){
        this.initAnimationComponent();
        this.animationComponent.addAnimation("down", spriteSheet, new Point(0, 0), new Point(3, 0), new Point(16, 16), 3, 10);
        this.animationComponent.addAnimation("up", spriteSheet, new Point(4, 0), new Point(7, 0), new Point(16, 16), 3, 10);
        this.animationComponent.addAnimation("right", spriteSheet, new Point(0, 1), new Point(3, 1), new Point(16, 16), 3, 10);
        this.animationComponent.addAnimation("left", spriteSheet, new Point(4, 1), new Point(7, 1), new Point(16, 16), 3, 10);
        this.animationComponent.addAnimation("iddle", spriteSheet, new Point(2, 0), new Point(3, 0), new Point(16, 16), 3, 2);
        this.animationComponent.setActiveAnimation("iddle");
    }


    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.draw(collitionComponent.getScreenHitbox());
        animationComponent.draw(g2);
    }
}
