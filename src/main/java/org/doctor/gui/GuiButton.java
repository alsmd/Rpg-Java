package org.doctor.gui;

import org.doctor.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GuiButton  implements  GuiElement{

    private State currentState = State.RELEASED;
    private Rectangle clickBox;
    private ArrayList<ActionListener> actionListeners;
    private String text = "";

    private Color released;
    private Color hover;
    private Color pressed;
    private Font font = new Font("Arial",  Font.PLAIN, 18);

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
        if (clickBox.contains(e.getPoint()))
            currentState = State.PRESSED;
    }

    public void mouseReleased(MouseEvent e){
        if (clickBox.contains(e.getPoint())){
            for (ActionListener al : actionListeners)
                al.actionPerformed(null);
        }
        currentState = State.RELEASED;
    }

    public void mouseDragged(MouseEvent e){
        if (clickBox.contains(e.getPoint())){
            currentState = State.PRESSED;
        }else{
            currentState = State.RELEASED;
        }
    }

    public void mouseMoved(MouseEvent e){
        if (clickBox.contains(e.getPoint())){
            currentState = State.HOVER;
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
}
