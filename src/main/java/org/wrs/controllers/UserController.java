package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.dao.UserDao;
import org.wrs.models.User;
import org.wrs.util.EmailSend;
import org.wrs.util.NotificationUtil;
import org.wrs.util.PasswordUtil;
import org.wrs.util.PropertiesEmailUtil;
import raven.application.form.other.UserProfileForm;
import raven.toast.Notifications;

/**
 *
 * @author C.Mateo
 */
public class UserController implements ActionListener {

    private final UserProfileForm userProfileForm;
    private final UserDao userDao;

    public UserController(UserProfileForm userProfileForm, UserDao userDao) {
        this.userProfileForm = userProfileForm;
        this.userDao = userDao;
        init();
    }

    private void init() {
        userProfileForm.setActionListener(this);
    }

    public void updateUser() {
        try {
            User userOld = userProfileForm.getUser();
            User userUpdated = userProfileForm.getUserDataFromForm();

            if (!userOld.getEmail().equals(userUpdated.getEmail()) && userDao.emailExistsForUpdate(userUpdated.getId(), userUpdated.getEmail())) {
                throw new RuntimeException("¡Email no disponible!");
            }

            if (!userOld.getUsername().equals(userUpdated.getUsername()) && userDao.usernameExistsForUpdate(userUpdated.getId(), userUpdated.getUsername())) {
                throw new RuntimeException("¡Username no disponible!");

            }

            userDao.updateUser(userUpdated);

            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Actualizacion exitosa!");
        } catch (RuntimeException e) {
            NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
        }

    }

    private void sendPinToEmail() {
        NotificationUtil.show(Notifications.Type.INFO, "¡Enviando correo, espera un momento!");
        User user = userProfileForm.getUser();
        int pin = userProfileForm.generatePin();
        EmailSend emailSend = new EmailSend();
        emailSend.sendEmail(user.getEmail(), "Restablecer contraseña", "PIN: " + pin, PropertiesEmailUtil.emailProperties);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "updateUserCmd" ->
                updateUser();
            case "sendPinToEmailCmd" ->
                sendPinToEmail();
            case "updatePasswordCmd" ->
                resetPassword();
            default -> {
            }
        }
    }

    private void resetPassword() {
        try {
            User user = userProfileForm.getUser();
            String newPassword = PasswordUtil.hashPassword(userProfileForm.getPasswordFromForm());
            System.out.println(newPassword);
            userDao.updateUserPassword(user, newPassword);
            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Contraseña Actualizada!");
        } catch (RuntimeException e) {
            NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
        }
    }

}
