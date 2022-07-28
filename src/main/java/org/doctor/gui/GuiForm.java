package org.doctor.gui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.doctor.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class GuiForm extends GuiPanel{
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode data = objectMapper.createObjectNode();
    int inputWidth;
    int inputHeight;
    int x;
    int y;
    int spacing;
    Stack<GuiInput> inputs = new Stack<>();
    StoreInfo storeInfo = new StoreInfo(data, inputs);

    public enum MODE{
        VERTICAL,
        HORIZONTAL
    }
    MODE currentMode = MODE.VERTICAL;

    public GuiForm(int inputWidth, int inputHeight, int x, int y, int spacing) {
        this.inputWidth = inputWidth;
        this.inputHeight = inputHeight;
        this.x = x;
        this.y = y;
        this.spacing = spacing;
    }

    public void setReady(boolean ready){
        storeInfo.ready = ready;
    }
    public void addInput(String title, String name){
        int inputStartY;
        if (inputs.size() == 0)
            inputStartY = y;
        else
            inputStartY = inputs.peek().getY() + inputs.peek().getHeight() + spacing;
        GuiInput input = new GuiInput(
                title,
                x,
                inputStartY,
                inputWidth,
                inputHeight,
                null, name
        );
        super.add(input);
        inputs.add(input);
    }

    public void addInput(String title, String name, String defaultValue){
        int inputStartY;
        if (inputs.size() == 0)
            inputStartY = y;
        else
            inputStartY = inputs.peek().getY() + inputs.peek().getHeight() + spacing;
        GuiInput input = new GuiInput(
                title,
                x,
                inputStartY,
                inputWidth,
                inputHeight,
                null, name
        );
        input.setText(defaultValue);
        super.add(input);
        inputs.add(input);
    }
    public void build(){
        int buttonStartY = inputs.peek().getY() + inputs.peek().getHeight() + spacing;
        GuiButton button = new GuiButton(Game.WIDTH / 2 - 70 / 2, buttonStartY, 70, 40);
        button.setText("Send");
        button.addActionListener(storeInfo);
        super.add(button);
    }

    public boolean isReady(){
        return storeInfo.ready;
    }

    @Override
    public void update(){
        for (GuiInput input : inputs){
            data.put(input.name, input.getText());
        }
    }
    @Override
    public void add(GuiElement e){}

    public ObjectNode getData(){
        return data;
    }

    public void clear(){
        for (GuiInput input : inputs)
            input.clear();
        storeInfo.ready = false;
        data = objectMapper.createObjectNode();
    }

    public void setMode(MODE mode){
        currentMode = mode;
        if (currentMode == MODE.VERTICAL){
            inputs.get(0).setPos(x, y);
            for (int index = 1; index < inputs.size(); index++){
                inputs.get(0).setPos(x, inputs.get(index - 1).getY() + spacing + inputs.get(index - 1).getHeight());
            }
        }
        if (currentMode == MODE.HORIZONTAL){
            inputs.get(0).setPos(x, y);
            for (int index = 1; index < inputs.size(); index++){
                inputs.get(index).setPos(inputs.get(index - 1).getX() + inputs.get(index - 1).getWidth() +  spacing, y);
            }
        }
    }
    class StoreInfo implements ActionListener{
        ObjectNode data;
        Stack<GuiInput> inputs;
        public boolean ready = false;

        StoreInfo(ObjectNode data, Stack<GuiInput> inputs){
            this.data = data;
            this.inputs = inputs;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            for (GuiInput input : inputs){
                data.put(input.name, input.getText());
            }
            ready = true;
        }
    }


}
