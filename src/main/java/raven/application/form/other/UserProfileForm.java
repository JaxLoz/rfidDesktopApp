package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionListener;
import org.wrs.models.User;
import org.wrs.util.FormValidator;
import org.wrs.util.PasswordUtil;
import org.wrs.view.dialog.ConfirmUserPasswordDialog;

/**
 *
 * @author C.Mateo
 */
public class UserProfileForm extends javax.swing.JPanel {

    private User user;
    private final ConfirmUserPasswordDialog confirmUserPasswordDialog;

    /**
     * Creates new form UserProfileForm
     */
    public UserProfileForm() {
        initComponents();
        confirmUserPasswordDialog = new ConfirmUserPasswordDialog(null);
        init();
    }
    
    public void setActionListener(ActionListener actionListener) {
        confirmUserPasswordDialog.setActionListener(actionListener);
    }


    private void init() {

        this.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;"
                + "border:30,40,50,30");

        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        resetPasswordLb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");

        userInfoLb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");

        pinTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,
                "ingrese el pin");

        newPasswordTxt.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        newPasswordTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ingrese la nueva contraseña");

        confirmNewPasswordTxt.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");

        confirmNewPasswordTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "confirme la nueva contraseña");

        updateUserBtn.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");

        sendPinBtn.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");

        confirmBtn.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");

        resetValuesBtn.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
        loadInfo();
    }

    private void loadInfo() {
        usernameTxt.setText(user.getUsername());
        firstNameTxt.setText(user.getFirstName());
        lastNameTxt.setText(user.getLastName());
        emailTxt.setText(user.getEmail());
    }

    public User getUserDataFromForm() {
        
        confirmUserPasswordDialog.setVisible(false);
        
        String password = confirmUserPasswordDialog.getPassword();
        
        System.out.println(password);
        
        if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
            throw new RuntimeException("¡Credenciales Incorrectas!");
        }

        if (!FormValidator.areFieldsNotEmpty(usernameTxt, firstNameTxt, lastNameTxt, emailTxt)) {
            throw new RuntimeException("¡Hay campos vacios!");
        }

        String username = usernameTxt.getText();
        String firstName = firstNameTxt.getText();
        String lastName = lastNameTxt.getText();
        String email = emailTxt.getText();
        
        User updateUser = new User(user.getId(), firstName, lastName, username, user.getPassword(), email);

        if (user.equals(updateUser)) {
            throw new RuntimeException("¡No hay cambios por actualizar!");
        }

        return updateUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        resetPasswordLb = new javax.swing.JLabel();
        sendPinBtn = new javax.swing.JButton();
        pinTxt = new javax.swing.JTextField();
        newPasswordTxt = new javax.swing.JPasswordField();
        confirmNewPasswordTxt = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        confirmBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userInfoLb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        updateUserBtn = new javax.swing.JButton();
        resetValuesBtn = new javax.swing.JButton();
        emailTxt = new javax.swing.JTextField();
        firstNameTxt = new javax.swing.JTextField();
        lastNameTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        usernameTxt = new javax.swing.JTextField();

        lbTitle.setText("Perfil de Usuario");

        resetPasswordLb.setText("Restablecer Contraseña");

        sendPinBtn.setText("Enviar pin al correo");

        jLabel4.setText("Nueva Contraseña");

        jLabel5.setText("Confirme la Nueva Contraseña");

        jLabel6.setText("Ingrese el pin: ");

        confirmBtn.setText("Confirmar");

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellidos:");

        userInfoLb.setText("Datos del Usuario");

        jLabel3.setText("Correo:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        updateUserBtn.setText("Actualizar datos del usuario");
        updateUserBtn.setActionCommand("updateUserCmd");
        updateUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserBtnActionPerformed(evt);
            }
        });

        resetValuesBtn.setText("Restablecer valores");
        resetValuesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetValuesBtnActionPerformed(evt);
            }
        });

        emailTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        firstNameTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lastNameTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText("Username:");

        usernameTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(resetValuesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(117, 117, 117)
                                        .addComponent(updateUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(userInfoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(newPasswordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(confirmNewPasswordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(pinTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(sendPinBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                            .addComponent(confirmBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetPasswordLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(resetPasswordLb)
                            .addComponent(userInfoLb))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(sendPinBtn)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(pinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)
                                .addComponent(newPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(confirmNewPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(confirmBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(updateUserBtn)
                                    .addComponent(resetValuesBtn))))
                        .addContainerGap(162, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(6, 6, 6))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserBtnActionPerformed
        // TODO add your handling code here:
        confirmUserPasswordDialog.setVisible(true);
    }//GEN-LAST:event_updateUserBtnActionPerformed

    private void resetValuesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetValuesBtnActionPerformed
        // TODO add your handling code here:
        loadInfo();
    }//GEN-LAST:event_resetValuesBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmBtn;
    private javax.swing.JPasswordField confirmNewPasswordTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField firstNameTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastNameTxt;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField newPasswordTxt;
    private javax.swing.JTextField pinTxt;
    private javax.swing.JLabel resetPasswordLb;
    private javax.swing.JButton resetValuesBtn;
    private javax.swing.JButton sendPinBtn;
    private javax.swing.JButton updateUserBtn;
    private javax.swing.JLabel userInfoLb;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables

}
