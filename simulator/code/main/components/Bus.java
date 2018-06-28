package main.components;

import main.Converter;

import javax.swing.*;

public class Bus {

    private int data = 0;
    private JLabel label;

    public Bus(JLabel label) {
        this.label = label;
    }

    public void putDataOnBus(int dataToBreadcast) {
        data |= dataToBreadcast;
        data &= 65535;
        Converter.setLabelText(label, data);
    }

    public int getData() {
        return data;
    }

    public void clearBus() {
        data = 0;
        Converter.setLabelText(label, data);
    }

    public void changeNumberSystem(int previousType) {
        Converter.resetLabelText(label, previousType);
    }

}
