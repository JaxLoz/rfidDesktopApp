package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.wrs.models.Recharge;
import org.wrs.models.RechargeFilter;
import org.wrs.models.Student;
import org.wrs.util.Formatter;
import org.wrs.util.NotificationUtil;
import org.wrs.view.dialog.RegisterRechargeDialog;
import org.wrs.view.model.table.RechargeStatusTableCellRenderer;
import org.wrs.view.model.table.RechargeTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class RechargeForm extends javax.swing.JPanel {

    private boolean filterByName = false;
    private boolean filterByPaymentType = false;
    private boolean filterByStatus = false;
    private boolean filterByDate = true;

    private String specificDate = Formatter.currentDate();
    private String fromDate;
    private String toDate;

    private final DateChooser dateChooser;
    private final RechargeTableModel rechargeTableModel;

    private final RegisterRechargeDialog registerRechargeDialog;

    public RechargeForm() {
        initComponents();
        dateChooser = new DateChooser();
        rechargeTableModel = new RechargeTableModel();
        rechargeTable.setModel(rechargeTableModel);
        registerRechargeDialog = new RegisterRechargeDialog(null);
        init();
    }

    private void init() {
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        searchRechargeByStudentTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "buscar por nombre del estudiante");

        dateChooser.setTextField(dateFilterTxt);
        rechargeTable.getColumn("Estado").setCellRenderer(new RechargeStatusTableCellRenderer());

        //DATE CHOOSER 
        dateChooser.addActionDateChooserListener(new DateChooserAdapter() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public void dateChanged(Date date, DateChooserAction action) {
                specificDate = df.format(date);
                fromDate = null;
                toDate = null;
            }

            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                fromDate = df.format(date.getFromDate());
                toDate = df.format(date.getToDate());
                specificDate = null;
            }
        });
    }

    public Long getRechargeDataToCancel() {
        int index = rechargeTable.getSelectedRow();
        
        if(index == -1) {
            throw new RuntimeException("¡Seleccione un recarga de la tabla!");
        }
        
        return rechargeTableModel.getStudentList().get(index).getId();
    }

    public void setActionListener(ActionListener actionListener) {
        cancelRechargeBtn.addActionListener(actionListener);
        updataRechargeTableBtn.addActionListener(actionListener);
        filterRechargeTableBtn.addActionListener(actionListener);
        registerRechargeDialog.setActionListener(actionListener);
    }

    public Recharge getRechargeFromForm() {
        return registerRechargeDialog.getRechargeFromForm();
    }

    public RechargeFilter getDataFromFilter() {

        RechargeFilter rechargeFilter = new RechargeFilter();

        if (!filterByName && !filterByPaymentType && !filterByStatus && !filterByDate && !paymentConfirmedCheckBox.isSelected()) {
            throw new RuntimeException("¡No hay filtro activo!");
        }

        if (filterByName) {
            String student = searchRechargeByStudentTxt.getText();
            if (student.isEmpty()) {
                throw new RuntimeException("¡El filtro por nombre de estudiante esta activo y vacio!");
            }
            rechargeFilter.setStudentName(student);
        }

        if (filterByPaymentType) {
            String paymentType = (String) paymentTypeComboBox.getSelectedItem();
            rechargeFilter.setPaymentType(paymentType);
        }

        if (filterByStatus) {
            String status = (String) statusComboBox.getSelectedItem();
            rechargeFilter.setStatus(status);
        }

        if (filterByDate) {
            if (specificDate != null) {
                rechargeFilter.setSpecificDate(specificDate);
            } else if (fromDate == null && toDate == null) {
                throw new RuntimeException("¡La busqueda por rango de fecha esta activa y no se a registrado!");
            } else if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                rechargeFilter.setFromDate(fromDate);
                rechargeFilter.setToDate(toDate);
            } else {
                throw new RuntimeException("¡Por favor, marque la fecha a buscar!");
            }
        }

        rechargeFilter.setIsConfirmed(paymentConfirmedCheckBox.isSelected());

        return rechargeFilter;
    }

    public void showRegisterRechargetDialog(Student student) {
        registerRechargeDialog.setStudent(student);
        registerRechargeDialog.setVisible(true);
    }

    public void setListRechargeTableModel(List<Recharge> recharges) {
        rechargeTableModel.setStudentList(recharges);
        showTotalRechargesConfirmed(recharges);
    }

    public void showTotalRechargesConfirmed(List<Recharge> recharges) {
        double totalConfirmedAmount = recharges.stream()
                .filter(Recharge::isConfirmed)
                .mapToDouble(Recharge::getAmount)
                .sum();

        totalRechargeLb.setText(Formatter.formatCurrency(totalConfirmedAmount));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rechargeTable = new javax.swing.JTable();
        updataRechargeTableBtn = new javax.swing.JButton();
        dateFilterTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        searchRechargeByStudentTxt = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        paymentTypeComboBox = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        statusComboBox = new javax.swing.JComboBox<>();
        jCheckBox4 = new javax.swing.JCheckBox();
        filterRechargeTableBtn = new javax.swing.JButton();
        paymentConfirmedCheckBox = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        totalTitleLb = new javax.swing.JLabel();
        totalRechargeLb = new javax.swing.JLabel();
        cancelRechargeBtn = new javax.swing.JButton();

        lb.setText("Recargas");

        rechargeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(rechargeTable);

        updataRechargeTableBtn.setText("Mostrar Todo");
        updataRechargeTableBtn.setActionCommand("updateRechargeTableCmd");
        updataRechargeTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updataRechargeTableBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Modo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        paymentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NEQUI", "EFECTIVO" }));

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setSelected(true);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "CADUCADA", "REALIZADO" }));

        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        filterRechargeTableBtn.setText("Filtrar");
        filterRechargeTableBtn.setActionCommand("filterRechargeTableCmd");
        filterRechargeTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterRechargeTableBtnActionPerformed(evt);
            }
        });

        paymentConfirmedCheckBox.setText("PAGO CONFIRMADO");
        paymentConfirmedCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paymentConfirmedCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        paymentConfirmedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentConfirmedCheckBoxActionPerformed(evt);
            }
        });

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("Active los checkboxes para aplicar los filtros de búsqueda.");
        jCheckBox6.setEnabled(false);

        totalTitleLb.setText("Total Recargado: ");

        totalRechargeLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cancelRechargeBtn.setText("Cancelar Recarga");
        cancelRechargeBtn.setActionCommand("cancelRechargeCmd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(668, 668, 668))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filterRechargeTableBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchRechargeByStudentTxt)
                                .addGap(10, 10, 10)
                                .addComponent(jCheckBox1)
                                .addGap(30, 30, 30)
                                .addComponent(paymentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox2)
                                .addGap(30, 30, 30)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4)
                                .addGap(30, 30, 30)
                                .addComponent(dateFilterTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cancelRechargeBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(paymentConfirmedCheckBox))
                            .addComponent(updataRechargeTableBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalRechargeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lb)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchRechargeByStudentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFilterTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentConfirmedCheckBox))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updataRechargeTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox6)
                    .addComponent(filterRechargeTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelRechargeBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalRechargeLb, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (dateChooser.getDateSelectionMode() == DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED) {
            dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        } else {
            dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        }
        dateChooser.revalidate();
        dateChooser.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filterRechargeTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterRechargeTableBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterRechargeTableBtnActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        filterByName = !filterByName;
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void updataRechargeTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updataRechargeTableBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updataRechargeTableBtnActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        filterByPaymentType = !filterByPaymentType;
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
        filterByStatus = !filterByStatus;
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        filterByDate = !filterByDate;
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void paymentConfirmedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentConfirmedCheckBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_paymentConfirmedCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelRechargeBtn;
    private javax.swing.JTextField dateFilterTxt;
    private javax.swing.JButton filterRechargeTableBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb;
    private javax.swing.JCheckBox paymentConfirmedCheckBox;
    private javax.swing.JComboBox<String> paymentTypeComboBox;
    private javax.swing.JTable rechargeTable;
    private javax.swing.JTextField searchRechargeByStudentTxt;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JLabel totalRechargeLb;
    private javax.swing.JLabel totalTitleLb;
    private javax.swing.JButton updataRechargeTableBtn;
    // End of variables declaration//GEN-END:variables

}
