package main.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FlagRegisterPanel extends JPanel {

    public JLabel lblCarryFlag;
    public JLabel lblZeroFlag;
    public JLabel lblEqualsFlag;
    public JLabel lblGreaterFlag;

    public FlagRegisterPanel() {
        setLayout(null);

        setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblFlags = new JLabel("Flag register");
        lblFlags.setBounds(10, 11, 158, 14);
        lblFlags.setFont(Window.boldFont);
        add(lblFlags);

        JLabel lblCarryFlag = new JLabel("Carry flag: ");
        lblCarryFlag.setBounds(10, 35, 78, 14);
        add(lblCarryFlag);

        JLabel lblZeroFlag = new JLabel("Zero flag: ");
        lblZeroFlag.setBounds(10, 60, 78, 14);
        add(lblZeroFlag);

        JLabel lblEqualsFlag = new JLabel("Equals flag: ");
        lblEqualsFlag.setBounds(10, 85, 78, 14);
        add(lblEqualsFlag);

        JLabel lblGreaterFlag = new JLabel("Greater flag: ");
        lblGreaterFlag.setBounds(10, 110, 78, 14);
        add(lblGreaterFlag);

        this.lblCarryFlag = new JLabel("not set");
        this.lblCarryFlag.setBounds(98, 36, 46, 14);
        add(this.lblCarryFlag);

        this.lblZeroFlag = new JLabel("not set");
        this.lblZeroFlag.setBounds(98, 60, 46, 14);
        add(this.lblZeroFlag);

        this.lblEqualsFlag = new JLabel("not set");
        this.lblEqualsFlag.setBounds(98, 85, 46, 14);
        add(this.lblEqualsFlag);

        this.lblGreaterFlag = new JLabel("not set");
        this.lblGreaterFlag.setBounds(98, 110, 46, 14);
        add(this.lblGreaterFlag);
    }

}
