package main.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AluPanel extends JPanel {

    public JLabel lblCompare;
    public JLabel lblShiftLeft;
    public JLabel lblShiftRight;
    public JLabel lblOr;
    public JLabel lblAnd;
    public JLabel lblNot;
    public JLabel lblSub;
    public JLabel lblAdd;

    public AluPanel() {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setLayout(null);

        JLabel lblArithmicAndLogic = new JLabel("Arithmic and Logic Unit");
        lblArithmicAndLogic.setBounds(10, 11, 159, 14);
        lblArithmicAndLogic.setFont(Window.boldFont);
        add(lblArithmicAndLogic);

        JLabel lblAluCompare = new JLabel("Compare: ");
        lblAluCompare.setBounds(10, 36, 70, 14);
        add(lblAluCompare);

        JLabel lblAluShiftLeft = new JLabel("Shift left: ");
        lblAluShiftLeft.setBounds(10, 61, 70, 14);
        add(lblAluShiftLeft);

        JLabel lblAluShiftRight = new JLabel("Shift right:");
        lblAluShiftRight.setBounds(10, 86, 70, 14);
        add(lblAluShiftRight);

        JLabel lblLogicOr = new JLabel("Logic OR:");
        lblLogicOr.setBounds(10, 111, 70, 14);
        add(lblLogicOr);

        JLabel lblLogicAnd = new JLabel("Logic AND: ");
        lblLogicAnd.setBounds(10, 136, 70, 14);
        add(lblLogicAnd);

        JLabel lblInvert = new JLabel("Logic NOT:");
        lblInvert.setBounds(10, 161, 70, 14);
        add(lblInvert);

        JLabel lblSubtract = new JLabel("Subtract: ");
        lblSubtract.setBounds(10, 186, 70, 14);
        add(lblSubtract);

        JLabel lblAddition = new JLabel("Add: ");
        lblAddition.setBounds(10, 211, 70, 14);
        add(lblAddition);


        lblCompare = new JLabel("0000000000000000");
        lblCompare.setBounds(90, 36, 116, 14);
        add(lblCompare);

        lblShiftLeft = new JLabel("0000000000000000");
        lblShiftLeft.setBounds(90, 61, 116, 14);
        add(lblShiftLeft);

        lblShiftRight = new JLabel("0000000000000000");
        lblShiftRight.setBounds(90, 86, 116, 14);
        add(lblShiftRight);

        lblOr = new JLabel("0000000000000000");
        lblOr.setBounds(90, 111, 116, 14);
        add(lblOr);

        lblAnd = new JLabel("0000000000000000");
        lblAnd.setBounds(90, 136, 116, 14);
        add(lblAnd);

        lblNot = new JLabel("0000000000000000");
        lblNot.setBounds(90, 161, 116, 14);
        add(lblNot);

        lblSub = new JLabel("0000000000000000");
        lblSub.setBounds(90, 186, 116, 14);
        add(lblSub);

        lblAdd = new JLabel("0000000000000000");
        lblAdd.setBounds(90, 211, 116, 14);
        add(lblAdd);

    }


}
