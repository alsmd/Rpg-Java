package org.doctor.scene;

import org.doctor.components.AnimationComponent;
import org.doctor.components.CollitionComponent;
import org.doctor.map.WorldMap;

import java.awt.*;

/**
 *
 * @brief   SceneElement has attributes of an element present inside a WorldMap, as:
 *          screenPosition, WorldPosition and a reference to the worldMap that it is inside
 *
 */
public abstract  class SceneElement {
    // POSITIONS
    public Point screenPosition = new Point();
    public Point worldPosition;

    public WorldMap worldMap;

    // COMPONENTS
    public CollitionComponent collitionComponent = null;
    public AnimationComponent animationComponent = null;


    //Constructors
    public SceneElement(WorldMap worldMap, Point worldPosition){
        this.worldPosition = worldPosition;
        this.worldMap = worldMap;
    }

    // INITIALIZE COMPONENTS
    public void initAnimationComponent(){
        animationComponent = new AnimationComponent(screenPosition);
    }

    public void initCollitionComponent(Rectangle hitbox){
        collitionComponent = new CollitionComponent(hitbox, this.worldMap, worldPosition, screenPosition);
    }

    // LOOP
    public abstract void update();

    public abstract  void draw(Graphics2D g2);
}
