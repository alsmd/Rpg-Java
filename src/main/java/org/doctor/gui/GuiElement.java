package org.doctor.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface GuiElement {
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseDragged(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void keyTyped(KeyEvent e);
    public void keyPressed(KeyEvent e);

    public void update();

    public void draw(Graphics2D g2);
}
