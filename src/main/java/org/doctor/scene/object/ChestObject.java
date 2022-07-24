package org.doctor.scene.object;


import org.doctor.map.WorldMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;

public class ChestObject extends  SuperObject{

    // Construcors
    public ChestObject(ObjectConfig config, WorldMap map){
        super(config, map);
        loadResources();
        addAnimations();
    }


    // SETUP

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
            animationComponent.addAnimation(a.name, spriteSheet, a.beginAnimation, a.endAnimation, a.tileSize, worldMap.mapConfig.scale, a.animationFps);
        }
        animationComponent.setActiveAnimation("iddle");
    }


    // LOOP

    @Override
    public void draw(Graphics2D g2){
        animationComponent.draw(g2);
        g2.setColor(Color.RED);
        g2.draw(collitionComponent.getScreenHitbox());
    }
    @Override
    public void update(){
        if (worldMap.keyH.open){
            if (this.collitionComponent.checkSurround(worldMap.player.collitionComponent))
                action();
        }
        animationComponent.update();
    }

    // ACTIONS
    @Override
    public void action() {
        animationComponent.setActiveAnimation("open");
    }

}
