package main.components;

import javax.swing.*;

public class Clock implements Runnable {

    private int pulseSpeed = 1000;

    private long lastUpdateTime = 0L;
    private boolean status = false;
    public boolean autoPulse = false;
    private ControllUnit controllUnit;

    private JLabel lblState;
    private JLabel lblPulseSpeed;
    private JButton runButton;
    private JSpinner spinner;

    Clock(ControllUnit controllUnit, JLabel lblState, JLabel lblPulseSpeed, JButton runButton, JButton pulseButton, JSpinner spinner, JButton setSpeed) {

        this.controllUnit = controllUnit;
        this.lastUpdateTime = System.currentTimeMillis();
        this.lblPulseSpeed = lblPulseSpeed;
        this.lblState = lblState;
        this.runButton = runButton;
        pulseButton.addActionListener(e -> manualPulse());
        runButton.addActionListener(e -> changeMode());
        this.spinner = spinner;
        setSpeed.addActionListener(e -> changePulseSpeed());
        lblState.setText(status ? "HIGH" : "LOW");
        lblPulseSpeed.setText(Integer.toString(pulseSpeed));
    }

    public void reset() {
        status = false;
        autoPulse = false;
        lblState.setText("LOW");
        runButton.setText("Run");
    }

    void update() {
            long currentTime = System.currentTimeMillis();
            if (lastUpdateTime + pulseSpeed < currentTime) {
                lastUpdateTime = currentTime;
                toggle();
            }
    }

    private void changePulseSpeed() {
        try {
            spinner.commitEdit();
        } catch (Exception e) {}
        pulseSpeed = (Integer) spinner.getValue();
        lblPulseSpeed.setText(Integer.toString(pulseSpeed));
    }

    void changeMode() {
        if (autoPulse) {
            setInManualMode();
        } else {
            setInAutoMode();
        }
    }

    public void run() {
        while(autoPulse) {
            update();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
        }
    }

    public void setInAutoMode() {
        if (!autoPulse) {
            autoPulse = true;
            runButton.setText("Stop");
            lastUpdateTime = System.currentTimeMillis();
            Thread autoRunner = new Thread(this);
            autoRunner.start();
        }
    }

    public void manualPulse() {
        if (!autoPulse) {
            toggle();
        }
    }

    private void toggle() {
        if (status) {
            controllUnit.fallingEdge();
            lblState.setText("LOW");
            status = false;
        } else {
            controllUnit.risingEdge();
            lblState.setText("HIGH");
            status = true;
        }
    }

    public void setInManualMode() {
        if (autoPulse) {
            autoPulse = false;
            runButton.setText("Run");
        }
    }

}
