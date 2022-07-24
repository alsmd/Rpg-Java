package org.doctor.entity;

import org.doctor.components.AnimationComponent;
import org.doctor.components.CollitionComponent;
import org.doctor.map.WorldMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public Point worldPosition = new Point();
    public Point screenPosition = new Point();
    public Dimension size = new Dimension();
    public BufferedImage spriteSheet;
    public int speed;
    public AnimationComponent animationComponent = null;
    public CollitionComponent collitionComponent = null;

    public void initAnimationComponent(){
        animationComponent = new AnimationComponent(screenPosition);
    }

    public void initCollitionComponent(Rectangle hitbox, WorldMap worldMap){
        collitionComponent = new CollitionComponent(hitbox, worldMap, worldPosition, screenPosition);
    }
}
