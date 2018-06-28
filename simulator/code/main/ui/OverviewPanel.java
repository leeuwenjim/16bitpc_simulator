package main.ui;

import main.Converter;

import javax.swing.*;
import java.awt.*;

public class OverviewPanel extends JPanel{

    public RegisterPanel panelOut;
    public RegisterPanel panelRegisterA;
    public RegisterPanel panelRegisterB;
    public RegisterPanel panelInstructionRegister;
    public RegisterPanel panelProgramCounter;
    public RegisterPanel panelMemoryAddressRegister;
    public AluPanel panelAlu;
    public ControllPanel panelControll;
    public FlagRegisterPanel panelFlags;
    public RamPanel panelRam;

    public JLabel dataBus;
    public JLabel addressBus;
    public JComboBox comboBox;
    public JButton btnReset;

    public OverviewPanel() {
        setLayout(null);
        addBusses();

        panelOut = new RegisterPanel("Output Register", 0);
        panelOut.setBounds(77, 29, 122, 52);
        add(panelOut);

        panelRegisterA = new RegisterPanel("Register A", 0);
        panelRegisterA.setBounds(77, 111, 122, 52);
        add(panelRegisterA);

        panelRegisterB = new RegisterPanel("Register B", 0);
        panelRegisterB.setBounds(209, 111, 122, 52);
        add(panelRegisterB);

        panelInstructionRegister = new RegisterPanel("Instruction Reg.", 0);
        panelInstructionRegister.setBounds(548, 174, 122, 52);
        add(panelInstructionRegister);

        panelProgramCounter = new RegisterPanel("Program Counter", 0);
        panelProgramCounter.setBounds(684, 174, 122, 52);
        add(panelProgramCounter);

        panelMemoryAddressRegister = new RegisterPanel("Memory Address R.", 0);
        panelMemoryAddressRegister.setBounds(209, 29, 122, 52);
        add(panelMemoryAddressRegister);

        panelAlu = new AluPanel();
        panelAlu.setBounds(96, 231, 216, 249);
        add(panelAlu);

        panelControll = new ControllPanel();
        panelControll.setBounds(332, 174, 206, 268);
        add(panelControll);

        panelFlags = new FlagRegisterPanel();
        panelFlags.setBounds(331, 453, 207, 136);
        add(panelFlags);

        panelRam = new RamPanel();
        panelRam.setBounds(548, 290, 342, 299);
        add(panelRam);

        JLabel lblData = new JLabel("Data: ");
        lblData.setBounds(360, 73, 37, 14);
        add(lblData);

        dataBus = new JLabel(Converter.getBinary16String(0));
        dataBus.setBounds(400, 73, 128, 14);
        add(dataBus);

        JLabel lblAddress = new JLabel("Address: ");
        lblAddress.setBounds(615, 257, 55, 14);
        add(lblAddress);

        addressBus = new JLabel(Converter.getBinary10String(0));
        addressBus.setBounds(668, 257, 122, 14);
        add(addressBus);

        btnReset = new JButton("Reset");
        btnReset.setBounds(801, 11, 89, 23);
        add(btnReset);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Binary", "Hex", "Decimal"}));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(684, 11, 106, 23);
        add(comboBox);

    }

    private void addBusses() {
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(40, 98, 817, 1);
        add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setForeground(Color.BLACK);
        separator_1.setBounds(40, 98, 1, 292);
        add(separator_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setOrientation(SwingConstants.VERTICAL);
        separator_2.setForeground(Color.BLACK);
        separator_2.setBounds(140, 80, 1, 31);
        add(separator_2);

        JSeparator separator_3 = new JSeparator();
        separator_3.setOrientation(SwingConstants.VERTICAL);
        separator_3.setForeground(Color.BLACK);
        separator_3.setBounds(268, 80, 1, 31);
        add(separator_3);

        JSeparator separator_4 = new JSeparator();
        separator_4.setForeground(Color.BLACK);
        separator_4.setBounds(40, 390, 55, 1);
        add(separator_4);

        JSeparator separator_5 = new JSeparator();
        separator_5.setOrientation(SwingConstants.VERTICAL);
        separator_5.setForeground(Color.BLACK);
        separator_5.setBounds(856, 98, 1, 192);
        add(separator_5);

        JSeparator separator_6 = new JSeparator();
        separator_6.setBounds(610, 98, 1, 80);
        add(separator_6);
        separator_6.setOrientation(SwingConstants.VERTICAL);
        separator_6.setForeground(Color.BLACK);

        JSeparator separator_7 = new JSeparator();
        separator_7.setOrientation(SwingConstants.VERTICAL);
        separator_7.setForeground(Color.BLACK);
        separator_7.setBounds(742, 98, 1, 80);
        add(separator_7);

        JSeparator separator_8 = new JSeparator();
        separator_8.setOrientation(SwingConstants.VERTICAL);
        separator_8.setForeground(Color.BLACK);
        separator_8.setBounds(605, 226, 1, 62);
        add(separator_8);

        JSeparator separator_9 = new JSeparator();
        separator_9.setOrientation(SwingConstants.VERTICAL);
        separator_9.setForeground(Color.BLACK);
        separator_9.setBounds(742, 226, 1, 20);
        add(separator_9);

        JSeparator separator_10 = new JSeparator();
        separator_10.setForeground(Color.BLACK);
        separator_10.setBounds(605, 245, 137, 1);
        add(separator_10);

        JSeparator separator_11 = new JSeparator();
        separator_11.setOrientation(SwingConstants.VERTICAL);
        separator_11.setForeground(Color.BLACK);
        separator_11.setBounds(431, 442, 1, 11);
        add(separator_11);

        JSeparator separator_12 = new JSeparator();
        separator_12.setOrientation(SwingConstants.VERTICAL);
        separator_12.setForeground(Color.BLACK);
        separator_12.setBounds(198, 480, 1, 50);
        add(separator_12);

        JSeparator separator_13 = new JSeparator();
        separator_13.setForeground(Color.BLACK);
        separator_13.setBounds(198, 530, 137, 1);
        add(separator_13);

        JSeparator separator_14 = new JSeparator();
        separator_14.setOrientation(SwingConstants.VERTICAL);
        separator_14.setForeground(Color.BLACK);
        separator_14.setBounds(140, 163, 1, 70);
        add(separator_14);

        JSeparator separator_15 = new JSeparator();
        separator_15.setOrientation(SwingConstants.VERTICAL);
        separator_15.setForeground(Color.BLACK);
        separator_15.setBounds(268, 163, 1, 70);
        add(separator_15);
    }

}
