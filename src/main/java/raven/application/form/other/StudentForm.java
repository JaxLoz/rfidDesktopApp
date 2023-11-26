package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import org.wrs.models.Student;
import org.wrs.view.dialog.RegisterStudentDialog;
import org.wrs.view.dialog.UpdateStudentDialog;
import org.wrs.view.model.table.StudentTableModel;
import raven.toast.Notifications;
import org.wrs.controllers.ISearch;

/**
 *
 * @author Raven
 */
public class StudentForm extends javax.swing.JPanel {

    private String uuid = null;
    private ISearch iSearchStudent;
    private final StudentTableModel studentTableModel;
    private final RegisterStudentDialog registerStudentDialog;
    private final UpdateStudentDialog updateStudentDialog;

    public StudentForm() {
        initComponents();
        studentTableModel = new StudentTableModel();
        registerStudentDialog = new RegisterStudentDialog(null);
        updateStudentDialog = new UpdateStudentDialog(null);
        init();
    }

    private void init() {
        studentTable.setModel(studentTableModel);

        updateBtn.putClientProperty(FlatClientProperties.STYLE, "");

        Timer timer = new Timer(500, (ActionEvent e) -> {
            String searchText = searchTxt.getText();
            iSearchStudent.search(searchText);
        });

        timer.setRepeats(false);
        searchTxt.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            // Se reinicia el temporizador cada vez que se inserta un carácter
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                timer.restart();
            }

            // Se reinicia el temporizador cada vez que se elimina un carácter
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                timer.restart();
            }

            // No relevante para JTextField
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }
        });
        searchTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar estudiante por nombre o identificación");
    }

    public void setActionListener(ActionListener actionListener) {
        updateBtn.addActionListener(actionListener);
        refreshbtn.addActionListener(actionListener);
        registerStudentDialog.setActionListener(actionListener);
        updateStudentDialog.setActinListener(actionListener);
    }
    
    

    public void setISearchStudent(ISearch iSearchStudent) {
        this.iSearchStudent = iSearchStudent;
    }

    public Student getStudentFromTable() {
        int selectedRow = studentTable.getSelectedRow();
        return studentTableModel.getStudentOfList(selectedRow);
    }

    public void setListStudentTableModel(List<Student> students) {
        studentTableModel.setStudentList(students);
    }

    public void showRegisterStudentView(String uuid) {
        registerStudentDialog.cleanField();
        registerStudentDialog.setUuid(uuid);
        registerStudentDialog.setVisible(true);
    }
    
    public void closeRegisterStudentView (){
        registerStudentDialog.dispose();
    }
    
    public Student getNewStudentRegister () throws RuntimeException{
        return registerStudentDialog.getNewStudent();
    }
    
    public void showUpdateStudenView (){
        Student studentSelect = this.getStudentFromTable();
        System.out.println("id del estudiante seleccionado : "+studentSelect.getId());
        updateStudentDialog.setInfoStudent(studentSelect);
        updateStudentDialog.setVisible(true);
    }
    
    public void setNewUuid (String uuid){
        updateStudentDialog.updateUuid(uuid);
    }
    
    public Student getUpdateStudent (){
        return updateStudentDialog.getInfoUpdateStudent();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        updateBtn = new javax.swing.JButton();
        searchTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        refreshbtn = new javax.swing.JButton();

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(studentTable);

        updateBtn.setText("Actualizar");
        updateBtn.setActionCommand("updateStudentRfidCmd");

        jButton1.setText("Nuevo");
        jButton1.setActionCommand("newButtonStudent");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        refreshbtn.setText("Refresh");
        refreshbtn.setActionCommand("RefreshTableStudent");
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(refreshbtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Antes de registrar un estudiante, por favor pase el llavero por el lector");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshbtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JTable studentTable;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
