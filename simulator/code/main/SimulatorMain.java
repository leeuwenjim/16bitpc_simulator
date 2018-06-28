package main;

import main.components.ControllUnit;
import main.ui.ControllPanel;
import main.ui.Window;

public class SimulatorMain {

    public SimulatorMain() {
        Window w = new Window();
        new ControllUnit(w.overView);
    }

    public static void main(String[] args) {
        new SimulatorMain();
    }

}
