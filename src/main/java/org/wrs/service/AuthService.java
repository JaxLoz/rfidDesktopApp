package org.wrs.service;

import org.wrs.configArduino.SerialLector;
import org.wrs.dao.UserDao;
import org.wrs.models.User;
import org.wrs.util.PasswordUtil;

/**
 *
 * @author C.Mateo
 */
public class AuthService {

    private final UserDao userDao;
    private final SerialLector serialLector;

    public AuthService(UserDao userDao, SerialLector serialLector) {
        this.userDao = userDao;
        this.serialLector = serialLector;
    }

    public User login(User user) {
        User userLogged = userDao.getUserByUsername(user.getUsername());
        boolean isLoginSuccessful = PasswordUtil.verifyPassword(user.getPassword(), userLogged.getPassword());
        if (!isLoginSuccessful) {
            throw new RuntimeException("¡credenciales incorrectas!");
        }
        serialLector.authenticated(true);
        return userLogged;
    }

}
