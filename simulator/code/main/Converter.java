package main;

import javax.swing.*;

public class Converter {

    public static int displayMode = 0;

    public static void setLabelText(JLabel label, int text){
        switch (displayMode) {
            case 0:
                //convert to binary
                label.setText(getBinary16String(text));
                break;
            case 1:
                //convert to hex
                label.setText(getHexString(text));
                break;
            default:
                //convert to decimal
                label.setText(Integer.toString(text));
                break;
        }
    }

    public static String getConvertedString(int text) {
        switch (displayMode) {
            case 0:
                return getBinary16String(text);
            case 1:
                return getHexString(text);
            default:
                return Integer.toString(text);
        }
    }

    public static String getHexString(int i) {
        return String.format("%4s", Integer.toHexString(i)).replace(' ', '0');
    }

    public static String getBinary16String(int i) {
        return String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    public static String getBinary10String(int i) {
        return String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    public static void resetLabelText(JLabel label, int previousMode) {
        switch (previousMode) {
            case 0:
                //Convert from bin
                setLabelText(label, Integer.parseInt(label.getText(), 2));
                break;
            case 1:
                //Convert from hex
                setLabelText(label, Integer.parseInt(label.getText(), 16));
                break;
            case 2:
                //Convert from dec
                setLabelText(label, Integer.parseInt(label.getText(), 10));
                break;
        }
    }

}
