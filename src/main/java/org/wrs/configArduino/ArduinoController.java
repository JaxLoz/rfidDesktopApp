package org.wrs.configArduino;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArduinoController implements ActionListener, IToggleRfidMode {

    private final SerialLector serialLector;

    public ArduinoController(SerialLector serialLector) {
        this.serialLector = serialLector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("toggleArduinoMode")){
            serialLector.toggleMode();
        }
    }

    @Override
    public void changeRfiMode() {
        serialLector.toggleMode();
        System.out.println("cambio el modo del arduino");
    }
}
