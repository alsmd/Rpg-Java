package org.doctor.states;

import org.doctor.Game;
import org.doctor.gui.MenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenuState extends State {
    MenuPanel menu;
    int buttonWidth = 220;
    int buttonHeight = 60;
    int startY = 160;
    int spacing = 90;

    public PauseMenuState(Game game) {
        super(game);
        setupMenu();
        panel.add(menu);
    }

    @Override
    public void onClose() {

    }

    @Override
    public void onBuild() {

    }

    @Override
    public void onBack() {

    }


    public void setupMenu(){
        menu = new MenuPanel(buttonWidth, buttonHeight, startY, spacing);
        menu.addButton("Resume", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.popState();
            }
        });
        menu.addButton("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.popState();
                game.popState();
                game.pushState(new MainMenuState(game));
            }
        });
    }
}
