package main.components;

import javax.swing.*;

public class InstructionRegister extends Register {

    public InstructionRegister(JLabel label) {
        super(label);
    }

    @Override
    public void writeDataToBus() {
        ControllUnit.dataBus.putDataOnBus(data & 1023);
    }

    public void writeDataToAddressBus() {
        ControllUnit.addressBus.putDataOnBus(data & 1023);
    }

    public int getOpcode() {
        return (data >>> 10) & 63;
    }

}
