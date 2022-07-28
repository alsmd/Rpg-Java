package org.doctor.states.edit.tilemap;

import org.doctor.Game;
import org.doctor.gui.GuiCheckbox;
import org.doctor.gui.GuiDropbox;
import org.doctor.gui.GuiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileMapEditGui extends GuiPanel {
    // INFORMATIONS
    public enum MODE{
        ADDCOLLITION,
        ADDTILES;
    }
    int indexVisibleLayer = -1; // all layers if -1
    int indexActiveLayer = 0;
    MODE currentMode = MODE.ADDTILES;
    //Gui
    GuiDropbox viewLayerDropbox; // to draw
    GuiDropbox activeLayerDropbox; //to update
    GuiDropbox modeDropbox;
    GuiCheckbox showCollition = new GuiCheckbox("Show Collition", 60, Game.HEIGHT / 2, 30, 30);


    public TileMapEditGui(){
        setupVisibleLayerMenu();
        setupActiveLayerDropbox();
        setupModeDropbox();
        add(showCollition);
    }

    /// SETUP
    public void setupVisibleLayerMenu(){
        viewLayerDropbox = new GuiDropbox("Layer in view", 50, 25, Game.WIDTH - 100, 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().contentEquals("all")){
                    indexVisibleLayer = -1;
                }else{
                    indexVisibleLayer = Integer.parseInt(e.getActionCommand()) - 1;
                    indexActiveLayer = Integer.parseInt(e.getActionCommand()) - 1;
                    activeLayerDropbox.setDefault(e.getActionCommand());

                }
            }
        });
        viewLayerDropbox.addField("1");
        viewLayerDropbox.addField("2");
        viewLayerDropbox.addField("3");
        viewLayerDropbox.addField("4");
        viewLayerDropbox.addField("5");
        viewLayerDropbox.addField("all");
        viewLayerDropbox.setDefault("all");
        add(viewLayerDropbox);
    }

    public void setupActiveLayerDropbox(){
        activeLayerDropbox = new GuiDropbox("Edit Layer", 50, 25, 50, 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexActiveLayer = Integer.parseInt(e.getActionCommand()) - 1;
            }
        });
        activeLayerDropbox.addField("1");
        activeLayerDropbox.addField("2");
        activeLayerDropbox.addField("3");
        activeLayerDropbox.addField("4");
        activeLayerDropbox.addField("5");
        add(activeLayerDropbox);
    }

    public void setupModeDropbox(){
        modeDropbox = new GuiDropbox("Mode", 100, 35, 50, Game.HEIGHT - 100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().contentEquals("Add Tiles")){
                    currentMode = MODE.ADDTILES;
                }
                else{
                    currentMode = MODE.ADDCOLLITION;
                }
            }
        });
        modeDropbox.addField("Add Tiles");
        modeDropbox.addField("Add Collitions");
        add(modeDropbox);
    }

    // GETTERS

    public int getIndexActiveLayer() {
        return indexActiveLayer;
    }

    public int getIndexVisibleLayer() {
        return indexVisibleLayer;
    }

    public boolean getShowCollition() {
        return showCollition.isActive;
    }

    public MODE getCurrentMode() {
        return currentMode;
    }
}
