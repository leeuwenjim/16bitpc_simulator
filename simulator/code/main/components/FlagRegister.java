package main.components;

import javax.swing.*;

public class FlagRegister {

    private boolean carry = false;
    private boolean zero = false;
    private boolean equals = false;
    private boolean greater = false;

    private JLabel lblCarry;
    private JLabel lblZero;
    private JLabel lblEquals;
    private JLabel lblGreater;

    public FlagRegister(JLabel lblCarry, JLabel lblZero, JLabel lblEquals, JLabel lblGreater) {
        this.lblCarry = lblCarry;
        this.lblZero = lblZero;
        this.lblEquals = lblEquals;
        this.lblGreater = lblGreater;
    }

    public void setFlags(boolean carry, boolean zero, boolean equals, boolean greater) {
        this.carry = carry;
        this.zero = zero;
        this.equals = equals;
        this.greater = greater;
        lblCarry.setText(carry ? "true" : "false");
        lblZero.setText(zero ? "true" : "false");
        lblEquals.setText(equals ? "true" : "false");
        lblGreater.setText(greater ? "true" : "false");
    }

    public int getFlagMask() {
        int result = 0;
        if (carry)
            result |= 8;
        if (zero)
            result |= 4;
        if (equals)
            result |= 2;
        if (greater)
            result |= 1;

        return result;
    }

}
