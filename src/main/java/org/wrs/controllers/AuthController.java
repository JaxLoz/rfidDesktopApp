package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.configArduino.ISerialComunication;
import org.wrs.models.User;
import org.wrs.service.AuthService;
import org.wrs.util.NotificationUtil;
import org.wrs.view.Application;
import raven.application.form.LoginForm;
import raven.toast.Notifications;

public class AuthController implements ActionListener, ISerialComunication{
    
    private final AuthService authService;
    private final LoginForm loginForm;

    public AuthController(AuthService authService, LoginForm loginForm) {
        this.authService = authService;
        this.loginForm = loginForm;
        init();
    }
    
    private void init(){
        loginForm.setActionListener(this);
    }

    private void login(){
        try {
            User user = loginForm.getUserDataFromForm();
            User userLogged = authService.login(user);
            loginForm.clearInputs();
            Application.login(userLogged);
        } catch (RuntimeException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, e.getMessage());
        }
    }
    
    @Override
    public void receiveDataSerialPort(String data) {
        if(Application.getInstance().getUser() == null){
            NotificationUtil.show(Notifications.Type.ERROR, "¡Ingrese al sistema para poder usar el lector!");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "loginCmd" -> login();
            default -> {}
        }
    }

    
}
