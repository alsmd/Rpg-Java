package org.doctor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, open, pause, tab, save, control, s;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE)
            open = true;
        if (code == KeyEvent.VK_ESCAPE)
            pause = true;
        if (code == KeyEvent.VK_TAB)
            tab = true;
        if (code == KeyEvent.VK_S)
            s = true;
        if (code == KeyEvent.VK_CONTROL)
            control = true;
        if (control && s)
            save = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE)
            open = false;
        if (code == KeyEvent.VK_ESCAPE)
            pause = false;
        if (code == KeyEvent.VK_TAB)
            tab = false;
        if (code == KeyEvent.VK_S)
            s = false;
        if (code == KeyEvent.VK_CONTROL)
            control = false;
        if (!control || !s)
            save = false;
    }
}
