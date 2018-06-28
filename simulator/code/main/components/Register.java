package main.components;

import main.Converter;

import javax.swing.*;

public class Register {

    protected int data;
    JLabel label;

    public Register(JLabel label) {
        this.label = label;
    }

    public void readDataFromBus() {
        data = ControllUnit.dataBus.getData();
        Converter.setLabelText(label, data);
        ControllUnit.alu.setLabels();
    }

    public void writeDataToBus() {
        ControllUnit.dataBus.putDataOnBus(data);
    }

    public void clearRegister() {
        data = 0;
        Converter.setLabelText(label, data);
        ControllUnit.alu.setLabels();
    }

    public int getData() {
        return data;
    }

    public void changeNumberSystem(int previousType) {
        Converter.resetLabelText(label, previousType);
    }

}
