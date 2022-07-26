package org.doctor.gui;

import org.doctor.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GuiInput implements GuiElement{
    public String name;
    private GuiInput.State currentState = GuiInput.State.NOFOCUS;
    private Rectangle clickBox;
    private ActionListener actionListener;
    private StringBuffer  text = new StringBuffer();
    private String placeholder;
    private Color focus;
    private Color noFocus;
    private Font font = new Font("Arial",  Font.PLAIN, 18);
    private Font placeholderFont = new Font("Arial",  Font.BOLD, 25);

    private enum State{
        FOCUS,
        NOFOCUS
    }

    public GuiInput(String placeholder, int x, int y, int width, int height, ActionListener actionListener){
        clickBox = new Rectangle(x, y, width, height);
        focus = new Color(173, 177, 179);
        noFocus = new Color(150, 156, 158);
        this.actionListener =  actionListener;
        this.placeholder = placeholder;
    }

    public GuiInput(String placeholder, int x, int y, int width, int height, ActionListener actionListener, String name){
        clickBox = new Rectangle(x, y, width, height);
        focus = new Color(173, 177, 179);
        noFocus = new Color(150, 156, 158);
        this.actionListener =  actionListener;
        this.placeholder = placeholder;
        this.name = name;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (clickBox.contains(e.getPoint()))
            currentState = State.FOCUS;
        else
            currentState = State.NOFOCUS;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickBox.contains(e.getPoint()))
            currentState = State.FOCUS;
        else
            currentState = State.NOFOCUS;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentState == State.FOCUS){
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !text.isEmpty()) //backspace
                text.delete(text.length() - 1, text.length());
            else if (e.getKeyCode() == KeyEvent.VK_ENTER && actionListener != null){
                actionListener.actionPerformed(new ActionEvent(this, 0, text.toString()));
                text.delete(0, text.length());
            }
            else if (DrawUtils.isAsciiPrintable(e.getKeyChar()))
                text.append(e.getKeyChar());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        if (currentState == State.FOCUS)
            g2.setColor(focus);
        else if (currentState == State.NOFOCUS)
            g2.setColor(noFocus);
        g2.fill(clickBox);
        // PLACEHOLDER
        if (text.isEmpty()){
            g2.setFont(placeholderFont);
            g2.setColor(Color.gray);
            g2.drawString(
                placeholder,
                (int) (clickBox.x + clickBox.getWidth() / 2 - DrawUtils.getMessageWidth(placeholder, placeholderFont, g2) / 2),
                (int) (clickBox.y + clickBox.getHeight() / 2 + DrawUtils.getMessageHeight(placeholder, placeholderFont, g2) / 2)
            );
        }
        // TEXT
        g2.setColor(Color.white);
        g2.setFont(font);
        g2.drawString(
                text.toString(),
                (int) (clickBox.x + clickBox.getWidth() / 2 - DrawUtils.getMessageWidth(text.toString(), font, g2) / 2),
                (int) (clickBox.y + clickBox.getHeight() / 2 + DrawUtils.getMessageHeight(text.toString(), font, g2) / 2)
        );

    }
    public void clear(){
        text.delete(0, text.length());
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

    public String getText() {
        return text.toString();
    }
}
