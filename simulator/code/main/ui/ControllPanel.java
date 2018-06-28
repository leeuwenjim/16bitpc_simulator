package main.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ControllPanel extends JPanel{

    public JLabel lblStep;
    public JLabel lblAction;
    public JLabel lblSignedModus;
    public JLabel lblHalt;

    public JButton btnRun;
    public JButton btnPulseOnce;
    public JSpinner spinner;
    public JButton btnSetSpeed;
    public JLabel lblPulseSpeed;
    public JLabel lblState;

    public ControllPanel() {
        setLayout(null);

        setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblControllUnit = new JLabel("Controll Unit");
        lblControllUnit.setFont(Window.boldFont);
        lblControllUnit.setBounds(10, 11, 137, 14);
        add(lblControllUnit);

        JLabel lblMcrStep = new JLabel("Microstep:");
        lblMcrStep.setBounds(10, 36, 78, 14);
        add(lblMcrStep);

        JLabel lblActie = new JLabel("Action: ");
        lblActie.setBounds(10, 61, 46, 14);
        add(lblActie);

        JLabel lblSignedModusTitle = new JLabel("Signed modus: ");
        lblSignedModusTitle.setBounds(10, 86, 78, 14);
        add(lblSignedModusTitle);

        JLabel lblHaltTitle = new JLabel("Halt: ");
        lblHaltTitle.setBounds(10, 111, 78, 14);
        add(lblHaltTitle);

        JLabel lblClock = new JLabel("Clock");
        lblClock.setBounds(10, 136, 46, 14);
        lblClock.setFont(Window.boldFont);
        add(lblClock);

        JLabel lblCurrentState = new JLabel("Current state: ");
        lblCurrentState.setBounds(10, 161, 78, 14);
        add(lblCurrentState);

        JLabel lblPulseSpeedT = new JLabel("Pulse speed: ");
        lblPulseSpeedT.setBounds(10, 186, 78, 14);
        add(lblPulseSpeedT);

        btnRun = new JButton("Run");
        btnRun.setBounds(109, 234, 87, 23);
        add(btnRun);

        btnPulseOnce = new JButton("Pulse once");
        btnPulseOnce.setBounds(10, 234, 89, 23);
        add(btnPulseOnce);

        lblState = new JLabel("lblState");
        lblState.setBounds(109, 161, 87, 14);
        add(lblState);

        lblPulseSpeed = new JLabel("lblPulseSpeed");
        lblPulseSpeed.setBounds(109, 186, 87, 14);
        add(lblPulseSpeed);

        JSeparator separator_16 = new JSeparator();
        separator_16.setBounds(10, 127, 186, 2);
        add(separator_16);

        spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(1000, 50, 5000, 1));
        spinner.setBounds(10, 205, 89, 23);
        add(spinner);

        btnSetSpeed = new JButton("Set speed");
        btnSetSpeed.setBounds(109, 205, 87, 23);
        add(btnSetSpeed);



        lblStep = new JLabel("0");
        lblStep.setBounds(109, 36, 87, 14);
        add(lblStep);

        lblAction = new JLabel("....");
        lblAction.setBounds(109, 61, 87, 14);
        add(lblAction);

        lblSignedModus = new JLabel("false");
        lblSignedModus.setBounds(109, 86, 87, 14);
        add(lblSignedModus);

        lblHalt = new JLabel("false");
        lblHalt.setBounds(109, 111, 87, 14);
        add(lblHalt);

    }

}
