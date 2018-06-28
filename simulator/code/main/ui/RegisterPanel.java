package main.ui;

import main.Converter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RegisterPanel extends JPanel{

    public JLabel lblData;

    public RegisterPanel(String title, int data) {
        setLayout(null);
        setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setBounds(10, 11, 110, 14);
        lblTitle.setFont(Window.boldFont);
        add(lblTitle);

        lblData = new JLabel("");
        lblData.setBounds(10, 27, 127, 14);
        add(lblData);
        Converter.setLabelText(lblData, 0);
    }

}

