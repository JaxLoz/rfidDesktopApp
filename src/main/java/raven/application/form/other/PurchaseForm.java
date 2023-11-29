package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.wrs.controllers.ISearchDatePurchase;
import org.wrs.models.Purchase;
import org.wrs.models.PurchaseFilter;
import org.wrs.models.PurchaseInfo;
import org.wrs.models.Student;
import org.wrs.util.Formatter;
import org.wrs.view.dialog.RegisterPurchaseDialog;
import org.wrs.view.model.table.SellTableModel;

/**
 *
 * @author C.Mateo
 */
public class PurchaseForm extends javax.swing.JPanel {

    private final RegisterPurchaseDialog registerPurchaseDialog;
    private final SellTableModel selltableModel;
    private ISearchDatePurchase iSearchDatePurchase;
    private final DateChooser chDate;
    private boolean ckeckEnable;
    private String singleDate;
    private Student studentCurrent;
    private String dateFromDate;
    private String dateToDate;

    public PurchaseForm() {
        initComponents();
        chDate = new DateChooser();
        selltableModel = new SellTableModel();
        registerPurchaseDialog = new RegisterPurchaseDialog(null);
        this.ckeckEnable = false;
        this.singleDate = Formatter.currentDate();
        init();
    }

    private void init() {
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        searchPurchaseByStudentTxt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "buscar por nombre del estudiante");

        purchaseTable.setModel(selltableModel);
        chDate.setTextField(txtCalendar);
        //chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateChanged(Date date, DateChooserAction action) {

                if (!ckeckEnable) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String selectedDate = df.format(date);
                    singleDate = selectedDate;
                    iSearchDatePurchase.getSingleDate(singleDate);
                }

            }

            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {

                if (ckeckEnable) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    dateFromDate = df.format(date.getFromDate());
                    dateToDate = df.format(date.getToDate());
                    iSearchDatePurchase.getBetween(dateFromDate, dateToDate);
                }

            }
        });

    }

    public void setStudentCurrent(Student student) {
        this.studentCurrent = student;
    }

    public Student getStudentCurrent() {
        return studentCurrent;
    }

    public void SetModeDateChoose() {

        if (checkModeRange.isSelected()) {
            chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
            this.ckeckEnable = true;

        } else {
            chDate.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
            this.ckeckEnable = false;

        }

    }

    public String getSingleDate() {
        return this.singleDate;
    }

    public Student getStudentFromForm() {
        return registerPurchaseDialog.getStudent();
    }

    public Purchase getPurchaseDataFromForm() {
        return registerPurchaseDialog.getPurchaseFromForm();
    }

    public void setISearchDatePurchase(ISearchDatePurchase isdp) {
        iSearchDatePurchase = isdp;
    }

    public void setActionListener(ActionListener actionListener) {
        registerPurchaseDialog.setActionListener(actionListener);
        searchPurchaseByStudentBtn.addActionListener(actionListener);
    }

    public void loadStudentInRegisterPurchaseView(Student studetn) {
        registerPurchaseDialog.setStudent(studetn);
        registerPurchaseDialog.setVisible(true);
    }

    public void loadTableSellInfo(List<Purchase> purchaseList) {
        selltableModel.setSellList(purchaseList);
    }

    public void setInfoPurchaseInForm(PurchaseInfo purchaseInfo) {
        Double totalPurchase = purchaseInfo.sumPurchaseValue();
        lblNumberPurchase.setText(String.valueOf(purchaseInfo.getListPurchase().size()));
        lblTotal.setText(Formatter.formatCurrency(totalPurchase));
    }

    public void updataPurchaseCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = df.format(new Date());
        singleDate = selectedDate;
        iSearchDatePurchase.getSingleDate(singleDate);
    }

    public PurchaseFilter getDataFromFilter() {

        PurchaseFilter purchaseFilter = new PurchaseFilter();

        if (ckeckEnable) {
            purchaseFilter.setFromDate(dateFromDate);
            purchaseFilter.setToDate(dateToDate);

        } else {
            purchaseFilter.setSpecificDate(singleDate);

        }
        
        purchaseFilter.setStudentName(searchPurchaseByStudentTxt.getText());

        return purchaseFilter;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        purchaseTable = new javax.swing.JTable();
        txtCalendar = new javax.swing.JTextField();
        lblNumberPurchase = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        checkModeRange = new javax.swing.JCheckBox();
        lbTitle = new javax.swing.JLabel();
        totalTitleLb = new javax.swing.JLabel();
        totalTitleLb1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        searchPurchaseByStudentBtn = new javax.swing.JButton();
        searchPurchaseByStudentTxt = new javax.swing.JTextField();

        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(purchaseTable);

        lblNumberPurchase.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lblNumberPurchase.setText("jLabel2");

        lblTotal.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lblTotal.setText("jLabel2");

        checkModeRange.setText("Modo de rangos");
        checkModeRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkModeRangeActionPerformed(evt);
            }
        });

        lbTitle.setText("Ventas");

        totalTitleLb.setText("Total Vendido:");

        totalTitleLb1.setText("Total Ventas:");

        jButton1.setText("Actualizar Tabla Fecha Actual");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        searchPurchaseByStudentBtn.setText("Buscar");
        searchPurchaseByStudentBtn.setActionCommand("searchPurchaseByStudentBtn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(totalTitleLb1)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumberPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                                .addComponent(totalTitleLb)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchPurchaseByStudentTxt)
                        .addGap(18, 18, 18)
                        .addComponent(searchPurchaseByStudentBtn)
                        .addGap(30, 30, 30)
                        .addComponent(txtCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkModeRange)
                        .addGap(41, 41, 41)
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkModeRange)
                    .addComponent(jButton1)
                    .addComponent(searchPurchaseByStudentBtn)
                    .addComponent(searchPurchaseByStudentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblNumberPurchase)
                    .addComponent(totalTitleLb)
                    .addComponent(totalTitleLb1))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkModeRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkModeRangeActionPerformed
        // TODO add your handling code here:
        SetModeDateChoose();
    }//GEN-LAST:event_checkModeRangeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updataPurchaseCurrentDate();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkModeRange;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblNumberPurchase;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable purchaseTable;
    private javax.swing.JButton searchPurchaseByStudentBtn;
    private javax.swing.JTextField searchPurchaseByStudentTxt;
    private javax.swing.JLabel totalTitleLb;
    private javax.swing.JLabel totalTitleLb1;
    private javax.swing.JTextField txtCalendar;
    // End of variables declaration//GEN-END:variables

}
