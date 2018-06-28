package main.components;

import main.Converter;
import main.ui.ControllPanel;
import main.ui.OverviewPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllUnit {

    public static Bus dataBus;
    public static Bus addressBus;
    public static boolean signedModus = false;

    public Clock clock;
    public Register registerA;
    public Register registerB;
    public Register registerOut;
    public Register registerMar;
    public InstructionRegister registerIr;
    public ProgramCounter programCounter;
    public FlagRegister flagRegister;
    public Ram ram;
    public static Alu alu;

    public JLabel lblMicroStep;
    public JLabel lblAction;
    public JLabel lblSigned;
    public JLabel lblHalt;

    private int microStep = 0;
    private boolean halt = false;

    private JComboBox<String> comboBox;

    public ControllUnit(OverviewPanel overviewPanel) {
        dataBus = new Bus(overviewPanel.dataBus);
        addressBus = new Bus(overviewPanel.addressBus);
        ControllPanel pnlCU = overviewPanel.panelControll;
        clock = new Clock(this, pnlCU.lblState, pnlCU.lblPulseSpeed, pnlCU.btnRun, pnlCU.btnPulseOnce, pnlCU.spinner, pnlCU.btnSetSpeed);
        lblMicroStep = pnlCU.lblStep;
        lblAction = pnlCU.lblAction;
        lblSigned = pnlCU.lblSignedModus;
        lblHalt = pnlCU.lblHalt;
        registerA = new Register(overviewPanel.panelRegisterA.lblData);
        registerB = new Register(overviewPanel.panelRegisterB.lblData);
        registerOut = new Register(overviewPanel.panelOut.lblData);
        registerMar = new Register(overviewPanel.panelMemoryAddressRegister.lblData);
        registerIr = new InstructionRegister(overviewPanel.panelInstructionRegister.lblData);
        programCounter = new ProgramCounter(overviewPanel.panelProgramCounter.lblData);
        flagRegister = new FlagRegister(overviewPanel.panelFlags.lblCarryFlag, overviewPanel.panelFlags.lblZeroFlag, overviewPanel.panelFlags.lblEqualsFlag, overviewPanel.panelFlags.lblGreaterFlag);
        ram = new Ram(overviewPanel.panelRam.table, overviewPanel.panelRam.btnLoad);
        alu = new Alu(registerA, registerB, flagRegister, overviewPanel.panelAlu.lblCompare, overviewPanel.panelAlu.lblShiftLeft, overviewPanel.panelAlu.lblShiftRight, overviewPanel.panelAlu.lblOr, overviewPanel.panelAlu.lblAnd, overviewPanel.panelAlu.lblNot, overviewPanel.panelAlu.lblSub, overviewPanel.panelAlu.lblAdd);

        overviewPanel.btnReset.addActionListener(e -> reset());
        comboBox = overviewPanel.comboBox;
        comboBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int previousType = Converter.displayMode;
                Converter.displayMode = comboBox.getSelectedIndex();
                changeNumberSystem(previousType);
            }
        });

        step0Falling();
    }

    private void changeNumberSystem(int previousType) {
        dataBus.changeNumberSystem(previousType);
        addressBus.changeNumberSystem(previousType);
        registerA.changeNumberSystem(previousType);
        registerB.changeNumberSystem(previousType);
        registerIr.changeNumberSystem(previousType);
        registerMar.changeNumberSystem(previousType);
        registerOut.changeNumberSystem(previousType);
        programCounter.changeNumberSystem(previousType);
        ram.updateTable();
        alu.changeNumberSystem(previousType);
    }

    private void reset() {
        dataBus.clearBus();
        addressBus.clearBus();
        clock.reset();
        registerA.clearRegister();
        registerB.clearRegister();
        registerOut.clearRegister();
        registerMar.clearRegister();
        registerIr.clearRegister();
        programCounter.clearRegister();
        flagRegister.setFlags(false, false, false, false);
        ram.reset();
        microStep = 0;
        lblMicroStep.setText("0");
        halt = false;
        lblHalt.setText("false");
        signedModus = false;
        lblSigned.setText("false");
        step0Falling();
    }

    void risingEdge() {
        if (halt) {
            lblHalt.setText("true");
            return;
        }

        switch (microStep) {
            case 0:
                step0Rising();
                break;
            case 1:
                step1Rising();
                break;
            case 2:
                step2Rising();
                break;
        }
    }

    void fallingEdge() {
        if (halt)
            return;

        microStep++;
        microStep %= 3;

        lblMicroStep.setText(Integer.toString(microStep));

        dataBus.clearBus();
        addressBus.clearBus();

        switch (microStep) {
            case 0:
                step0Falling();
                break;
            case 1:
                step1Falling();
                break;
            case 2:
                step2Falling();
                break;
        }

    }

    private void step0Falling() {
        programCounter.writeDataOnAddressBus();
        ram.readDataFromRam();
        lblAction.setText("LOAD IR");
    }

    private void step1Falling() {
        lblAction.setText("INCR PC");
    }

    private void step2Falling() {
        int instruction = registerIr.getOpcode();

        switch (instruction) {
            case 0:
                lblAction.setText("NOOP");
                break;
            case 2:
                //B to A
                registerB.writeDataToBus();
                lblAction.setText("$B -> $A");
                break;
            case 3:
                //A to B
                registerA.writeDataToBus();
                lblAction.setText("$A -> $B");
                break;
            case 4:
                // clear A
                lblAction.setText("CLR $A");
                break;
            case 5:
                //clear B
                lblAction.setText("CLR $B");
                break;
            case 6:
                //clear OUT
                lblAction.setText("CLR $O");
                break;
            case 7:
                //clear MAR
                lblAction.setText("CLR $MAR");
                break;
            case 8:
                //str A
                registerA.writeDataToBus();
                registerIr.writeDataToAddressBus();
                lblAction.setText("STR $A");
                break;
            case 9:
                //str B
                registerB.writeDataToBus();
                registerIr.writeDataToAddressBus();
                lblAction.setText("STR $B");
                break;
            case 12:
                //ld A
                lblAction.setText("LD $A");
                registerIr.writeDataToAddressBus();
                ram.readDataFromRam();
                break;
            case 13:
                //ld B
                lblAction.setText("LD $B");
                registerIr.writeDataToAddressBus();
                ram.readDataFromRam();
                break;
            case 14:
                //ld O
                lblAction.setText("LD $O");
                registerIr.writeDataToAddressBus();
                ram.readDataFromRam();
                break;
            case 16:
                //unconditional jump
                lblAction.setText("JUMP");
                registerIr.writeDataToBus();
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
                //conditional jump
                lblAction.setText("BRCH " + String.format("%4s", Integer.toBinaryString(flagRegister.getFlagMask())).replace(' ', '0'));
                if ((instruction & flagRegister.getFlagMask()) > 0) {
                    registerIr.writeDataToBus();
                }
                break;
            case 34:
                //return
                lblAction.setText("RETURN");
                registerMar.writeDataToBus();
                break;
            case 35:
                //call
                lblAction.setText("CALL");
                programCounter.writeDataToBus();
                break;
            case 36:
                //ldd A
                lblAction.setText("LDD $A");
                registerIr.writeDataToBus();
                break;
            case 37:
                //ldd B
                lblAction.setText("LDD $B");
                registerIr.writeDataToBus();
                break;
            case 38:
                //ldd O
                lblAction.setText("LDD $O");
                registerIr.writeDataToBus();
                break;
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                //ALU
                lblAction.setText("ALU: " + (instruction & 7));
                alu.calculate(instruction & 7);
                break;
            case 49:
                //toggle sign
                lblAction.setText("TGL SIGN");
                break;
            case 50:
                //out A
                lblAction.setText("$A -> $O");
                registerA.writeDataToBus();
                break;
            case 51:
                //out B
                lblAction.setText("$B -> $O");
                registerB.writeDataToBus();
                break;
            case 63:
                //halt
                lblAction.setText("HALT");
                break;

        }

    }

    private void step0Rising() {
        registerIr.readDataFromBus();
    }

    private void step1Rising() {
        programCounter.increment();
    }

    private void step2Rising() {
        int instruction = registerIr.getOpcode();

        switch (instruction) {
            case 0:
                //NO OP
                break;
            case 2:
                //B to A
                registerA.readDataFromBus();
                break;
            case 3:
                //A to B
                registerB.readDataFromBus();
                break;
            case 4:
                // clear A
                registerA.clearRegister();
                break;
            case 5:
                //clear B
                registerB.clearRegister();
                break;
            case 6:
                //clear OUT
                registerOut.clearRegister();
                break;
            case 7:
                //clear MAR
                registerMar.clearRegister();
                break;
            case 8:
                //str A
            case 9:
                //str B
                ram.writeDataToRam();
                break;
            case 12:
                //ld A
                registerA.readDataFromBus();
                break;
            case 13:
                //ld B
                registerB.readDataFromBus();
                break;
            case 14:
                //ld O
                registerOut.readDataFromBus();
                break;
            case 16:
                //unconditional jump
                programCounter.readDataFromBus();
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
                //conditional jump
                if ((instruction & flagRegister.getFlagMask()) > 0) {
                    programCounter.readDataFromBus();
                }
                break;
            case 34:
                //return
                programCounter.readDataFromBus();
                break;
            case 35:
                //call
                registerMar.readDataFromBus();
                break;
            case 36:
                //ldd A
                registerA.readDataFromBus();
                break;
            case 37:
                //ldd B
                registerB.readDataFromBus();
                break;
            case 38:
                //ldd O
                registerOut.readDataFromBus();
                break;
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                //ALU
                registerA.readDataFromBus();
                break;
            case 49:
                //toggle sign
                signedModus = !signedModus;
                break;
            case 50:
                //out A
            case 51:
                //out B
                registerOut.readDataFromBus();
                break;
            case 63:
                //halt
                halt = true;
                break;
        }
    }



}
