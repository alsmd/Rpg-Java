package org.doctor.gui;

import org.doctor.DrawUtils;
import org.doctor.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GuiButton  implements  GuiElement{

    private State currentState = State.RELEASED;
    public Rectangle clickBox;
    private ArrayList<ActionListener> actionListeners;
    private String text = "";
    private String value = "";

    private Color released;
    private Color hover;
    private Color pressed;
    private Font font = new Font("Arial",  Font.PLAIN, 18);
    private boolean is_pressed = false;

    public GuiButton(int x, int y, int width, int height){
        clickBox = new Rectangle(x, y, width, height);
        actionListeners = new ArrayList<ActionListener>();
        released = new Color(173, 177, 179);
        hover = new Color(150, 156, 158);
        pressed = new Color(111, 116, 117);
    }
    private enum State{
        RELEASED,
        HOVER,
        PRESSED
    }


    public void update(){
    }

    public void draw(Graphics2D g2){
        if (currentState == State.RELEASED)
            g2.setColor(released);
        else if (currentState == State.HOVER)
            g2.setColor(hover);
        else if (currentState == State.PRESSED)
            g2.setColor(pressed);
        g2.fill(clickBox);
        g2.setColor(Color.white);
        g2.setFont(font);
        g2.drawString(
                text,
                (int) (clickBox.x + clickBox.getWidth() / 2 -   DrawUtils.getMessageWidth(text, font, g2) / 2),
                (int) (clickBox.y + clickBox.getHeight() / 2 + DrawUtils.getMessageHeight(text, font, g2) / 2)
        );
    }

    public void addActionListener(ActionListener listener){
        actionListeners.add(listener);
    }
    public void mousePressed(MouseEvent e){
        if (clickBox.contains(e.getPoint()) && !Game.cliked.contains(e.getPoint())){
            currentState = State.PRESSED;
            is_pressed = true;
            Game.cliked.add(e.getPoint());
        }
    }

    public void mouseReleased(MouseEvent e){
        if (clickBox.contains(e.getPoint()) && is_pressed && !Game.cliked.contains(e.getPoint())){
            for (ActionListener al : actionListeners)
                al.actionPerformed(null);
            Game.cliked.add(e.getPoint());
        }
        is_pressed = false;
        currentState = State.RELEASED;
    }

    public void mouseDragged(MouseEvent e){
        if (clickBox.contains(e.getPoint()) && !Game.cliked.contains(e.getPoint())){
            currentState = State.PRESSED;
            Game.cliked.add(e.getPoint());
        }else{
            currentState = State.RELEASED;
        }
    }

    public void mouseMoved(MouseEvent e){
        if (clickBox.contains(e.getPoint()) && !Game.cliked.contains(e.getPoint())){
            currentState = State.HOVER;
            Game.cliked.add(e.getPoint());
        }else{
            currentState = State.RELEASED;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    public void setY(int y){
        clickBox.y = y;
    }
    public int getX(){
        return clickBox.x;
    }

    public int getY(){
        return clickBox.y;
    }

    public int getWidth(){
        return clickBox.width;
    }

    public int getHeight(){
        return clickBox.height;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public void activeEvent(){
        for (ActionListener al : actionListeners)
            al.actionPerformed(null);
    }
}
