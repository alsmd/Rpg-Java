package org.doctor.states;

import org.doctor.Game;
import org.doctor.gui.MenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuState extends State{
    MenuPanel menu;
    int buttonWidth = 220;
    int buttonHeight = 60;
    int startY = 160;
    int spacing = 90;

    public MainMenuState(Game game){
        super(game);
        setupMenu();
        panel.add(menu);
    }

    @Override
    public void onClose() {

    }

    public void setupMenu(){
        menu = new MenuPanel(buttonWidth, buttonHeight, startY, spacing);
        menu.addButton("Play", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.popState();
                game.pushState(new MainState(game));
            }
        });
        menu.addButton("Edit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.popState();
                game.pushState(new EditState(game));
            }
        });
        menu.addButton("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
