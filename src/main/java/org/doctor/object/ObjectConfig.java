package org.doctor.object;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectConfig {
    ArrayList<AnimationConfig> animations = new ArrayList<AnimationConfig>();
    Point worldPosition = new Point();
    String spriteSheetName;
    public ObjectConfig(Point worldPosition, String spriteSheetName){
        this.worldPosition = worldPosition;
        this.spriteSheetName = spriteSheetName;
    }
    public void addAnimation(String name, int animationFps, Point tileSize, Point beginAnimation, Point endAnimation){
        animations.add(new AnimationConfig(name, animationFps, tileSize, beginAnimation, endAnimation));
    }
    public class AnimationConfig{
        String name;
        int animationFps;
        Point tileSize;
        Point beginAnimation;
        Point endAnimation;

        public AnimationConfig(String name, int animationFps, Point tileSize, Point beginAnimation, Point endAnimation) {
            this.animationFps = animationFps;
            this.tileSize = tileSize;
            this.beginAnimation = beginAnimation;
            this.endAnimation = endAnimation;
            this.name = name;
        }
    }

}
