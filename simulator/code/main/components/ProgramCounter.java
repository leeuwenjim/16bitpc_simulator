package main.components;

import main.Converter;

import javax.swing.*;

public class ProgramCounter extends Register {

    public ProgramCounter(JLabel label) {
        super(label);
    }

    @Override
    public void readDataFromBus() {
        data = ControllUnit.dataBus.getData();
        data &= 1023;
        Converter.setLabelText(label, data);
    }

    public void writeDataOnAddressBus() {
        ControllUnit.addressBus.putDataOnBus(data);
    }

    public void increment() {
        data++;
        data &= 1023;
        Converter.setLabelText(label, data);
    }


}
