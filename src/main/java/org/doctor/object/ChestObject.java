package org.doctor.object;


import org.doctor.map.WorldMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class ChestObject extends  SuperObject{
    WorldMap map;
    boolean openning = false;
    public ChestObject(ObjectConfig config, WorldMap map){
        this.config = config;
        this.map = map;
        this.worldPosition = config.worldPosition;
        loadResources();
        addAnimations();
    }

    @Override
    public void action() {
        animationComponent.setActiveAnimation("open");
    }

    private void loadResources(){
        try{
            spriteSheet = ImageIO.read(new FileInputStream(config.spriteSheetName));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void addAnimations(){
        initAnimationComponent();
        for (ObjectConfig.AnimationConfig a : config.animations){
            animationComponent.addAnimation(a.name, spriteSheet, a.beginAnimation, a.endAnimation, a.tileSize, map.mapConfig.scale, a.animationFps);
        }
        animationComponent.setActiveAnimation("iddle");
    }

    public void draw(Graphics2D g2){
        Rectangle objHit = collitionComponent.getScreenHitbox();
        animationComponent.draw(g2);
        g2.setColor(Color.RED);
        g2.draw(objHit);
    }

    public void update(){
        if (map.keyH.open == true){
            if (map.player.collitionComponent.checkSurround(this.collitionComponent)){
                action();
            }
        }
        animationComponent.update();
    }

}
