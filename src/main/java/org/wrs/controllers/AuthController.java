package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.configArduino.ISerialComunication;
import org.wrs.models.User;
import org.wrs.service.AuthService;
import org.wrs.util.NotificationUtil;
import org.wrs.view.Application;
import raven.application.form.IAuthData;
import raven.toast.Notifications;

public class AuthController implements IAuth, ISerialComunication{
    
    private IAuthData IAuthData;
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @Override
    public void receiveDataSerialPort(String data) {
        if(Application.getInstance().getUser() == null){
            NotificationUtil.show(Notifications.Type.ERROR, "¡Ingrese al sistema para poder usar el lector!");
        }
    }

    public void setIAuthData(IAuthData IAuthData) {
        this.IAuthData = IAuthData;
    }
    
    @Override
    public void logout() {
        authService.logout();
        Application.getInstance().setUser(null);
        
    }

    @Override
    public void login() {
         try {
            User user = IAuthData.getDataFromForm();
            User userLogged = authService.login(user);
            Application.login(userLogged);
        } catch (RuntimeException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, e.getMessage());
        }
    }

    
}
