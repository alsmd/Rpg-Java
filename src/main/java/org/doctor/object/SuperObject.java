package org.doctor.object;

import org.doctor.components.AnimationComponent;
import org.doctor.components.CollitionComponent;
import org.doctor.map.WorldMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public abstract class SuperObject {
    BufferedImage spriteSheet;
    ObjectConfig config;
    public Point screenPosition = new Point();
    public Point worldPosition = new Point();
    public CollitionComponent collitionComponent;
    public AnimationComponent animationComponent = null;
    public boolean collition = true;

    public void initCollitionComponent(Rectangle hitbox, WorldMap worldMap){
        collitionComponent = new CollitionComponent(hitbox, worldMap, worldPosition, screenPosition);
    }

    public abstract  void action();

    public abstract  void draw(Graphics2D g2);
    public abstract void update();

    public void initAnimationComponent(){
        animationComponent = new AnimationComponent(screenPosition);
    }

}
