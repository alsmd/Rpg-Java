package org.doctor.scene.object;

import org.doctor.components.AnimationComponent;
import org.doctor.components.CollitionComponent;
import org.doctor.map.WorldMap;
import org.doctor.scene.SceneElement;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject extends SceneElement {
    // CONFIGURATION
    protected ObjectConfig config;
    protected BufferedImage spriteSheet;

    // CONSTRUCTORS

    public SuperObject(ObjectConfig config, WorldMap map){
        super(map, config.worldPosition);
        this.config = config;
    }

   // ABSTRACT
    public abstract  void action();

}
