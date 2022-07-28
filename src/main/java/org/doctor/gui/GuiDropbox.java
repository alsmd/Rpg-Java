package org.doctor.gui;

import org.doctor.DrawUtils;
import org.doctor.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class GuiDropbox extends GuiPanel{
    private int buttonWidth ;
    private int buttonHeight;
    private int startY;
    private int startX;

    private String title = "";
    private Font font = new Font("Arial",  Font.BOLD, 22);
    Stack<GuiButton> buttons = new Stack<>();

    ActionListener action;

    private boolean droped = false;

    public GuiDropbox(int buttonWidth, int buttonHeight, int startX, int startY, ActionListener action) {
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.startX = startX;
        this.startY = startY;
        this.action = action;
    }

    public GuiDropbox(String title, int buttonWidth, int buttonHeight, int startX, int startY, ActionListener action) {
        this.title = title;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.startX = startX;
        this.startY = startY;
        this.action = action;
    }

    public void setDefault(String text){
        for (GuiButton btn : buttons){
            if (btn.getText().contentEquals(text)){
                elements.clear();
                elements.push(btn);
                break ;
            }
        }
    }

    public void addField(String text){
        int buttonStartY;
        if (buttons.size() == 0)
            buttonStartY = startY;
        else
            buttonStartY = buttons.peek().getY() + buttonHeight;
        GuiButton newButton = new GuiButton(startX, buttonStartY, buttonWidth, buttonHeight);
        newButton.setText(text);
        if (elements.size() == 0)
            super.add(newButton);
        buttons.add(newButton);
    }

    @Override
    public void update(){
        if (droped){
            if (buttons.size() == 0)
                buttons.get(0).setY(startY);
            for (int index = 1; index < buttons.size(); index++){
                buttons.get(index).setY(buttons.get(index - 1).getY() + buttonHeight);
            }
        }else{
            ((GuiButton)elements.get(0)).setY(startY);
        }

    }

    public void mousePressed(MouseEvent e){
        if (Game.cliked.contains(e.getPoint()))
            return ;
        for (GuiButton button : buttons){
            if (button.clickBox.contains(e.getPoint())){
                Game.cliked.add(e.getPoint());
                if (droped){
                    droped = false;
                    elements.clear();
                    action.actionPerformed(new ActionEvent(this, 0, button.getText()));
                    button.activeEvent();
                    elements.push(button);
                    break ;
                }else {
                    droped = true;
                    elements.clear();
                    for (GuiButton btn : buttons)
                        elements.add(btn);
                    break ;
                }
            }
        }

    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString(title, startX - DrawUtils.getMessageWidth(title, font, g2) / 2 + buttonWidth / 2, startY - DrawUtils.getMessageHeight(title, font, g2));
    }
}
