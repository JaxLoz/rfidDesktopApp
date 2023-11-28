package org.wrs.configArduino;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.util.NotificationUtil;
import raven.toast.Notifications;

public class ArduinoController implements ActionListener, IToggleRfidMode {

    private final SerialLector serialLector;

    public ArduinoController(SerialLector serialLector) {
        this.serialLector = serialLector;
    }
    
    private void notifyMode(){
        String mode = serialLector.getMode();
        NotificationUtil.show(Notifications.Type.INFO,"MODO " + mode + " ACTIVADO");
    }
    
    @Override
    public void changeRfiMode() {
        serialLector.toggleMode();
        notifyMode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("toggleArduinoMode")){
            serialLector.toggleMode();
            notifyMode();
        }
    }

}
