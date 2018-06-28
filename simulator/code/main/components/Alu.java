package main.components;

import main.Converter;

import javax.swing.*;

public class Alu {

    private Register registerA;
    private Register registerB;
    private FlagRegister flags;
    private JLabel lblCompare;
    private JLabel lblShiftLeft;
    private JLabel lblShiftRight;
    private JLabel lblOr;
    private JLabel lblAnd;
    private JLabel lblInv;
    private JLabel lblSub;
    private JLabel lblAdd;

    public Alu(Register registerA, Register registerB, FlagRegister flags, JLabel lblCompare, JLabel lblShiftLeft, JLabel lblShiftRight, JLabel lblOr, JLabel lblAnd, JLabel lblInv, JLabel lblSub, JLabel lblAdd) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.flags = flags;
        this.lblCompare = lblCompare;
        this.lblShiftLeft = lblShiftLeft;
        this.lblShiftRight = lblShiftRight;
        this.lblOr = lblOr;
        this.lblAnd = lblAnd;
        this.lblInv = lblInv;
        this.lblSub = lblSub;
        this.lblAdd = lblAdd;
        setLabels();
    }

    public void changeNumberSystem(int previousType) {
        Converter.resetLabelText(lblCompare, previousType);
        Converter.resetLabelText(lblShiftLeft, previousType);
        Converter.resetLabelText(lblShiftRight, previousType);
        Converter.resetLabelText(lblOr, previousType);
        Converter.resetLabelText(lblAnd, previousType);
        Converter.resetLabelText(lblInv, previousType);
        Converter.resetLabelText(lblSub, previousType);
        Converter.resetLabelText(lblAdd, previousType);
    }

    public void setLabels() {
        Converter.setLabelText(lblCompare, compare(false));
        Converter.setLabelText(lblShiftLeft, shiftLeft(false));
        Converter.setLabelText(lblShiftRight, shiftRight(false));
        Converter.setLabelText(lblOr, orAB(false));
        Converter.setLabelText(lblAnd, andAB(false));
        Converter.setLabelText(lblInv, notA(false));
        Converter.setLabelText(lblSub, sub(false));
        Converter.setLabelText(lblAdd, add(false));
    }

    public void calculate(int function) {
        switch (function) {
            case 0:
                ControllUnit.dataBus.putDataOnBus(compare(true));
                break;
            case 1:
                ControllUnit.dataBus.putDataOnBus(shiftLeft(true));
                break;
            case 2:
                ControllUnit.dataBus.putDataOnBus(shiftRight(true));
                break;
            case 3:
                ControllUnit.dataBus.putDataOnBus(orAB(true));
                break;
            case 4:
                ControllUnit.dataBus.putDataOnBus(andAB(true));
                break;
            case 5:
                ControllUnit.dataBus.putDataOnBus(notA(true));
                break;
            case 6:
                ControllUnit.dataBus.putDataOnBus(sub(true));
                break;
            case 7:
                ControllUnit.dataBus.putDataOnBus(add(true));
                break;
        }
    }

    private int compare(boolean setFlags) {
        if (setFlags) {
            flags.setFlags(false, registerA.getData() == 0, registerA.getData() == registerB.getData(), registerA.getData() > registerB.getData());
        }
        return registerA.getData();
    }

    private int shiftLeft(boolean setFlags) {
        int result = registerA.getData() << 1;
        int shiftOut = result >>> 16;
        result &= 65535;

        if (setFlags) {
            flags.setFlags(shiftOut>0, result==0, result == registerB.getData(), result > registerB.getData());
        }

        return result;
    }

    private int shiftRight(boolean setFlags) {
        int result = registerA.getData();
        int shiftOut = result & 1;
        int sign = result & 32768; // 1000 0000 0000 0000 in binary
        result = result >>> 1;
        if (ControllUnit.signedModus) {
            result |= sign;
        }

        if (setFlags) {
            flags.setFlags(shiftOut > 1, result == 1, result == registerB.getData(), result > registerB.getData());
        }

        return result;
    }

    private int orAB(boolean setFlags) {
        int result = registerA.getData() | registerB.getData();

        if (setFlags) {
            flags.setFlags(false, result == 0, result == registerB.getData(), result > registerB.getData());
        }

        return result;
    }

    private int andAB(boolean setFlags) {
        int result = registerA.getData() & registerB.getData();

        if (setFlags) {
            flags.setFlags(false, result == 0, result == registerB.getData(), result > registerB.getData());
        }

        return result;
    }

    private int notA(boolean setFlags) {
        int result = ~registerA.getData();

        result &= 65535;

        if (setFlags) {
            flags.setFlags(false, result == 0, result == registerB.getData(), result > registerB.getData());
        }

        return result;
    }

    private int add(boolean setFlags) {
        int result = registerA.getData() + registerB.getData();
        int carry = result >>> 16;
        result &= 65535;

        if (setFlags) {
            flags.setFlags(carry > 0, result == 0, result == registerB.getData(), result > registerB.getData());
        }

        return result;
    }

    private int sub(boolean setFlags) {
        int tempB = registerB.getData() ^ 65535;
        int result = registerA.getData() + tempB + 1;
        int carry = result >>> 16;

        if (setFlags) {
            flags.setFlags(carry > 0, result == 0, result == registerB.getData(), result > registerB.getData());
        }
        result &= 65535;
        return result;
    }

}
