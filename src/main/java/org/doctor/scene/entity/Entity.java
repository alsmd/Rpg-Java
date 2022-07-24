package org.doctor.scene.entity;

import org.doctor.components.AnimationComponent;
import org.doctor.components.CollitionComponent;
import org.doctor.map.WorldMap;
import org.doctor.scene.SceneElement;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity extends SceneElement {
    public Dimension size = new Dimension();
    public BufferedImage spriteSheet;
    public int speed;

    public Entity(WorldMap worldMap, Point worldPosition){
        super(worldMap, worldPosition);
    }
}
