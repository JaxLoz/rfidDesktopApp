package org.wrs.configArduino;

import org.wrs.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArduinoController implements ActionListener {

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
}
