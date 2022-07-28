package org.doctor.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class GuiPanel implements GuiElement{
    public Stack<GuiElement> elements = new Stack<>();

    public GuiPanel(){
    }

    public void update(){
        for (GuiElement element : elements)
            element.update();
    }

    public void draw(Graphics2D g2){
        for (GuiElement element : elements){
            element.draw(g2);
        }
    }

    public void add(GuiElement element){
        elements.add(element);
    }


    public void remove(GuiElement element){
        elements.remove(element);
    }

    public void mousePressed(MouseEvent e){
        if (elements.size() == 0) {
            return ;
        }
        for (int index = elements.size() - 1; index >= 0; index--)
            elements.get(index).mousePressed(e);
    }

    public void mouseReleased(MouseEvent e){
        for (GuiElement element : elements)
            element.mouseReleased(e);
    }

    public void mouseDragged(MouseEvent e){
        for (GuiElement element : elements)
            element.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e){
        for (GuiElement element : elements)
            element.mouseMoved(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (GuiElement element : elements)
            element.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (GuiElement element : elements)
            element.keyPressed(e);
    }
}
