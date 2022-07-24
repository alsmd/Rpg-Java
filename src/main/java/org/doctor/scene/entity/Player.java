package org.doctor.scene.entity;

import org.doctor.KeyHandler;
import org.doctor.map.WorldMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{
    public Player(WorldMap worldMap, Point worldPosition) {
        super(worldMap, worldPosition);
        initDefautlValues();
        loadResources();
        this.initCollitionComponent(new Rectangle(10, 20, 28, 22));
        this.initAnimations();
    }

    // SETUP
    private void initDefautlValues(){
        screenPosition.x = worldMap.screenSize.x / 2 - worldMap.mapConfig.scaledTileSize.x / 2;
        screenPosition.y = worldMap.screenSize.y / 2 - worldMap.mapConfig.scaledTileSize.y / 2;
        size.width = worldMap.mapConfig.scaledTileSize.x;
        size.height = worldMap.mapConfig.scaledTileSize.y;
        speed = 200;
    }

    private void loadResources(){
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

    // LOOP
    @Override
    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.draw(collitionComponent.getScreenHitbox());
        animationComponent.draw(g2);
    }
    @Override
    public void update(){
        Point copy = (Point) worldPosition.clone();
        if (worldMap.keyH.upPressed){
            animationComponent.setActiveAnimation("up");
            worldPosition.y -= speed * (double)(1.f / 60);
        }
        else if (worldMap.keyH.downPressed){
            animationComponent.setActiveAnimation("down");
            worldPosition.y += speed * (double)(1.f / 60);
        }
        else if (worldMap.keyH.leftPressed){
            animationComponent.setActiveAnimation("left");
            worldPosition.x -= speed * (double)(1.f / 60);
        }
        else if (worldMap.keyH.rightPressed){
            animationComponent.setActiveAnimation("right");
            worldPosition.x += speed * (double)(1.f / 60);
        }
        else
            this.animationComponent.setActiveAnimation("iddle");
        if (collitionComponent.checkTileCollition() || collitionComponent.checkObjectsCollition())
            worldPosition.setLocation(copy);
        animationComponent.update();
    }
}
