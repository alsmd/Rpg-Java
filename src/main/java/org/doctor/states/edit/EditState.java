package org.doctor.states.edit;

import org.doctor.Game;
import org.doctor.gui.MenuPanel;
import org.doctor.states.MainMenuState;
import org.doctor.states.State;
import org.doctor.states.edit.tilemap.TilemapEditState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditState extends State {
    MenuPanel menu;
    int buttonWidth = 220;
    int buttonHeight = 60;
    int startY = 160;
    int spacing = 90;

    public EditState(Game game) {
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
        menu.addButton("Edit Tilemap", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pushState(new TilemapEditState(game));
            }
        });
        menu.addButton("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.popState();
                game.pushState(new MainMenuState(game));
            }
        });
    }

}
