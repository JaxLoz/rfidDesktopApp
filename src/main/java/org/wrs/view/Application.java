package org.wrs.view;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import org.wrs.models.User;
import raven.application.form.LoginForm;
import raven.application.form.MainForm;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class Application extends javax.swing.JFrame {

    private static Application app;
    private User user;
    private final MainForm mainForm;
    private final LoginForm loginForm;

    private Application() {
        initComponents();
        setTitle();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();
        setContentPane(loginForm);
        Notifications.getInstance().setJFrame(this);
    }

    private void setTitle() {
        if (user == null) {
            setTitle("Windsor Royal School");

        } else {
            String firstName = user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1).toLowerCase();
            String lastName = user.getLastName().substring(0, 1).toUpperCase() + user.getLastName().substring(1).toLowerCase();
            setTitle("Windsor Royal School - " + firstName + " " + lastName);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public MainForm getMainForm() {
        return mainForm;
    }

    public static Application getInstance() {
        if (app == null) {
            app = new Application();
        }
        return app;
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(getInstance().getComponentOrientation());
        getInstance().mainForm.showForm(component);
    }

    public static void login(User user) {
        getInstance().setUser(user);
        getInstance().setTitle();
        FlatAnimatedLafChange.showSnapshot();
        getInstance().setContentPane(getInstance().mainForm);
        getInstance().mainForm.applyComponentOrientation(getInstance().getComponentOrientation());
        setSelectedMenu(0, 0);
        getInstance().mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(getInstance().mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        getInstance().setUser(null);
        getInstance().setTitle();
        getInstance().setContentPane(getInstance().loginForm);
        getInstance().loginForm.applyComponentOrientation(getInstance().getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(getInstance().loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void setSelectedMenu(int index, int subIndex) {
        getInstance().mainForm.setSelectedMenu(index, subIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            //app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            app.setVisible(true);
        });
    }
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
