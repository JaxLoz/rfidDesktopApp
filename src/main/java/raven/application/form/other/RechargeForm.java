package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import org.wrs.models.Recharge;
import org.wrs.models.Student;
import org.wrs.view.model.table.RechargeStatusTableCellRenderer;
import org.wrs.view.model.table.RechargeTableModel;

/**
 *
 * @author Raven
 */
public class RechargeForm extends javax.swing.JPanel {

    private final DateChooser dateChooser;
    private final RechargeTableModel rechargeTableModel;

    public RechargeForm() {
        initComponents();
        dateChooser = new DateChooser();
        rechargeTableModel = new RechargeTableModel();
        rechargeTable.setModel(rechargeTableModel);
        init();
    }

    private void init() {
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        searchRechargeTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "buscar por nombre del estudiante");
        dateChooser.setTextField(dateFilterTxt);
        rechargeTable.getColumn("Estado").setCellRenderer(new RechargeStatusTableCellRenderer());
        dateChooser.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateChanged(Date date, DateChooserAction action) {
                System.out.println(date);
            }
            
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                System.out.println(date.getFromDate() + " " + date.getToDate());
            }
        });
    }

    public void setActionListener(ActionListener actionListener) {
        updataRechargeTableBtn.addActionListener(actionListener);
    }

    public void showRegisterRechargetDialog(Student student) {

    }

    public void setListRechargeTableModel(List<Recharge> recharges) {
        rechargeTableModel.setStudentList(recharges);
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
        searchRechargeTxt = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

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

        updataRechargeTableBtn.setText("Actualizar");
        updataRechargeTableBtn.setActionCommand("updateRechargeTableCmd");

        jButton1.setText("Cambiar Modo Seleccion de Fecha");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Filtrar por Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchRechargeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateFilterTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(updataRechargeTableBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                        .addGap(253, 253, 253))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lb)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updataRechargeTableBtn)
                    .addComponent(dateFilterTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(searchRechargeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dateFilterTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable rechargeTable;
    private javax.swing.JTextField searchRechargeTxt;
    private javax.swing.JButton updataRechargeTableBtn;
    // End of variables declaration//GEN-END:variables

}
