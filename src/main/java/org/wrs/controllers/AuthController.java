package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.dao.UserDao;
import org.wrs.models.User;
import org.wrs.view.Application;
import raven.application.form.LoginForm;
import raven.toast.Notifications;

public class AuthController implements ActionListener{
    
    private final UserDao userDao;
    private final LoginForm loginForm;

    public AuthController(UserDao userDao, LoginForm loginForm) {
        this.userDao = userDao;
        this.loginForm = loginForm;
    }

    private void login(){
        try {
            User user = loginForm.getUserDataFromForm();
            User userLogged = userDao.getUserByUsername(user.getUsername(), user.getPassword());
            loginForm.clearInputs();
            Application.login(userLogged);
        } catch (RuntimeException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, e.getMessage());
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
