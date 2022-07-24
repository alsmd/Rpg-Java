package org.doctor.states;

import org.doctor.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MenuState extends State{

    public MenuState(GamePanel panel){
        super(panel);
        panel.setPreferredSize(new Dimension(500, 600));
    }




    @Override
    public void draw(Graphics2D g2) {
        panel.repaint();
    }

    @Override
    public void update() {
    }

}
