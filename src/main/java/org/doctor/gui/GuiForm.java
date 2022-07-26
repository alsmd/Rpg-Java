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
    int startY;
    int spacing;
    Stack<GuiInput> inputs = new Stack<>();
    StoreInfo storeInfo = new StoreInfo(data, inputs);

    public GuiForm(int inputWidth, int inputHeight, int startY, int spacing) {
        this.inputWidth = inputWidth;
        this.inputHeight = inputHeight;
        this.startY = startY;
        this.spacing = spacing;
    }

    public void addInput(String title, String name){
        int inputStartY;
        if (inputs.size() == 0)
            inputStartY = startY;
        else
            inputStartY = inputs.peek().getY() + inputs.peek().getHeight() + spacing;
        GuiInput input = new GuiInput(
                title,
                Game.WIDTH / 2 - inputWidth / 2,
                inputStartY,
                inputWidth,
                inputHeight,
                null, name
        );
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
            if (ready)
                return ;
            for (GuiInput input : inputs){
                data.put(input.name, input.getText());
            }
            ready = true;
        }
    }


}
