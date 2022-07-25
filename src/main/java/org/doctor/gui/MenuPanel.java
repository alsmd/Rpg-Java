package org.doctor.gui;

import org.doctor.Game;

import java.awt.event.ActionListener;

public class MenuPanel extends GuiPanel{
    private int buttonWidth ;
    private int buttonHeight;
    private int spacing;
    private int startY;

    public MenuPanel(int buttonWidth, int buttonHeight, int startY, int spacing) {
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.startY = startY;
        this.spacing = spacing;
    }

    public void addButton(String text, ActionListener al){
        int buttonStartY;
        if (elements.size() == 0)
            buttonStartY = startY;
        else
            buttonStartY = ((GuiButton) elements.peek()).getY() + spacing;
        GuiButton newButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, buttonStartY, buttonWidth, buttonHeight);
        newButton.setText(text);
        newButton.addActionListener(al);
        super.add(newButton);
    }

    @Override
    public void add(GuiElement element){}

}
