package org.doctor.components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimationComponent {
    Map<String, Animation> animation = new HashMap<String, Animation>();
    String activeAnimation = null;
    Point position = null;

    public AnimationComponent(Point position){
        this.position = position;
    }

    public void addAnimation(String name, BufferedImage spriteSheet, Point beginFrame, Point endFrame, Point size, int scale, int fps) {
        animation.put(name, new Animation(spriteSheet, beginFrame, endFrame, size, scale, fps));
    }

    public void update(){
        if (activeAnimation != null && animation.get(activeAnimation) != null)
            animation.get(activeAnimation).update();
    }

    public void draw(Graphics2D g2){
        if (activeAnimation != null && animation.get(activeAnimation) != null){
            animation.get(activeAnimation).draw(g2, position);
        }
    }

    public void setActiveAnimation(String name){
        if (activeAnimation != name && activeAnimation != null)
            animation.get(activeAnimation).reset();
        if (name != null && animation.get(name) != null){
            activeAnimation = name;
        }
    }


    private class Animation {
        private BufferedImage spriteSheet;
        private Point beginFrame;
        private Point endFrame;
        private Point currentFrame;
        private Point size;
        private Point spriteLastFrame = new Point();
        private double fps;
        long lastTime = 0;
        double delta = 0;
        int scale;
        double drawInterval;


        public Animation(BufferedImage spriteSheet, Point beginFrame, Point endFrame, Point size, int scale, double fps) {
            this.spriteSheet = spriteSheet;
            this.beginFrame = beginFrame;
            this.endFrame = endFrame;
            this.size = size;
            this.currentFrame = (Point) beginFrame.clone();
            this.fps = fps;
            this.spriteLastFrame.x = spriteSheet.getWidth() / size.x;
            this.spriteLastFrame.y = spriteSheet.getHeight() / size.y;
            this.scale = scale;
            drawInterval  = 1000000000/ fps;
        }

        public void update() {
            if (lastTime == 0)
                lastTime = System.nanoTime();
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1){
                int  lastXFrame;
                if (currentFrame.y < endFrame.y)
                    lastXFrame = spriteLastFrame.x;
                else
                    lastXFrame = endFrame.x;
                if (currentFrame.x < lastXFrame)
                    currentFrame.x += 1;
                else if (currentFrame.y < endFrame.y){
                    currentFrame.y += 1;
                    currentFrame.x = 0;
                }
                else
                    currentFrame = (Point) beginFrame.clone();
                delta--;
            }
        }

        public void draw(Graphics2D g2, Point position) {
            BufferedImage frame = spriteSheet.getSubimage(currentFrame.x * size.x, currentFrame.y * size.y, size.x, size.y);
            g2.drawImage(frame, position.x, position.y, size.x * scale, size.y * scale, null);
        }

        public void reset(){
            delta = 0;
            lastTime = 0;
            this.currentFrame = (Point) this.beginFrame.clone();
        }
    }
}
