package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import org.wrs.controllers.ISearchStudent;
import org.wrs.models.Student;
import org.wrs.view.dialog.RegisterStudentDialog;
import org.wrs.view.model.table.StudentTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class StudentForm extends javax.swing.JPanel {

    private String uuid = null;
    private ISearchStudent iSearchStudent;
    private final StudentTableModel studentTableModel;
    private final RegisterStudentDialog registerStudentDialog;

    public StudentForm() {
        initComponents();
        studentTableModel = new StudentTableModel();
        registerStudentDialog = new RegisterStudentDialog(null);
        init();
    }

    private void init() {
        studentTable.setModel(studentTableModel);

        updateBtn.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");

        Timer timer = new Timer(500, (ActionEvent e) -> {
            String searchText = searchTxt.getText();
            iSearchStudent.searchStudent(searchText);
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
        registerStudentDialog.setActionListener(actionListener);
    }

    public void setISearchStudent(ISearchStudent iSearchStudent) {
        this.iSearchStudent = iSearchStudent;
    }

    public Student getStudentFromTable() {
        int selectRow = studentTable.getSelectedRow();
        return studentTableModel.getStudentOfList(selectRow);
    }

    public void setListStudentTableModel(List<Student> students) {
        studentTableModel.setStudentList(students);
    }

    public void showRegisterStudentView(String uuid) {
        registerStudentDialog.setUuid(uuid);
        registerStudentDialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        updateBtn = new javax.swing.JButton();
        searchTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

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
        updateBtn.setActionCommand("refreshStudentTableCmd");

        jButton1.setText("Nuevo");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
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
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Antes de registrar un estudiante, por favor pase el llavero por el lector");
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JTable studentTable;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
