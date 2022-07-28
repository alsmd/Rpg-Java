package org.doctor.gui;

import org.doctor.DrawUtils;
import org.doctor.Game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GuiCheckbox implements GuiElement{

    String placeholder;
    public Rectangle clickBox;
    private Font font = new Font("Arial",  Font.PLAIN, 18);


    public boolean isActive = false;

    public GuiCheckbox(int x, int y, int width, int height){
        clickBox = new Rectangle(x, y, width, height);
    }
    public GuiCheckbox(String placeholder, int x, int y, int width, int height){
        clickBox = new Rectangle(x, y, width, height);
        this.placeholder = placeholder;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (clickBox.contains(e.getPoint()) && !Game.cliked.contains(e.getPoint())){
            isActive = !isActive;
            Game.cliked.add(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(173, 177, 179));
        g2.fill(clickBox);
        g2.draw(clickBox);
        if (isActive){
            g2.setColor(Color.RED);
            g2.fillOval(clickBox.x , clickBox.y, clickBox.width, clickBox.height);
        }
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.drawString(
                placeholder,
                clickBox.x - DrawUtils.getMessageWidth(placeholder, font, g2) / 2 + clickBox.width / 2,
                clickBox.y - DrawUtils.getMessageHeight(placeholder, font, g2));
    }
}
