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
import org.wrs.controllers.ISearchDatePurchase;
import org.wrs.models.Purchase;
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
    private SellTableModel selltableModel;
    private ISearchDatePurchase iSearchDatePurchase;
    private DateChooser chDate;
    private boolean ckeckEnable;
    private String singleDate;
    private Student studentCurrent;

    
    public PurchaseForm() {
        initComponents();
        chDate = new DateChooser();
        selltableModel = new SellTableModel();
        registerPurchaseDialog = new RegisterPurchaseDialog(null);
        this.ckeckEnable = false;
        this.singleDate = null;
       
        init();
    }
    
    public void setISearchDatePurchase(ISearchDatePurchase isdp){
        iSearchDatePurchase = isdp;
    }
    
    public void init(){
        purchaseTable.setModel(selltableModel);
        chDate.setTextField(txtCalendar);
        //chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateChanged(Date date, DateChooserAction action) {
                
                if(!ckeckEnable){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String selectedDate = df.format(date);
                    singleDate = selectedDate;
                    iSearchDatePurchase.getSingleDate(singleDate);
                }
                
            }
            
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                
                if(ckeckEnable){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String dateFromDate = df.format(date.getFromDate());
                    String dateToDate = df.format(date.getToDate());
                    iSearchDatePurchase.getBetween(dateFromDate, dateToDate);
                }
                
            }
        });
        
    }
    
    public void  setStudentCurrent (Student student){
        this.studentCurrent = student;
    }
    
    public Student getStudentCurrent (){
        return studentCurrent;
    }
    
    public void SetModeDateChoose (){
       
        if (checkModeRange.isSelected()){
           chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
            this.ckeckEnable = true;
            System.out.println("Rango "+ckeckEnable);
        }else{
            chDate.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
            this.ckeckEnable = false;
            System.out.println("Single "+ckeckEnable);
        }
        
        txtCalendar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Establesca el rango de fechas");
        
    }
    
    public String getSingleDate (){
        return this.singleDate;
    }
    
    
    
    public void setInfoPurchaseInit(PurchaseInfo purchaseInfo){
        PurchaseInfo purchaseInfoInit = purchaseInfo;
        selltableModel.setSellList(purchaseInfo.getListPurchase());
    }
    
    public Student getStudentFromForm(){
        return registerPurchaseDialog.getStudent();
    }
    
    public Purchase getPurchaseDataFromForm(){
        return registerPurchaseDialog.getPurchaseFromForm();
    }
    
    public void setActionListener(ActionListener actionListener){
        registerPurchaseDialog.setActionListener(actionListener);
        
    }
    
    public void loadStudentInRegisterPurchaseView(Student studetn) {
        registerPurchaseDialog.setStudent(studetn);
        showRegisterPurchaseView(true);
    }
    
    
    public void loadTableSellInfo (List<Purchase> purchaseList){
        selltableModel.setSellList(purchaseList);
    }
    
    public void showRegisterPurchaseView(boolean show){
        registerPurchaseDialog.setVisible(show);
    }
    
    public void setInfoPurchaseInForm (PurchaseInfo purchaseInfo){
        Double totalPurchase = purchaseInfo.sumPurchaseValue();
        lblNumberPurchase.setText(String.valueOf(purchaseInfo.getListPurchase().size()));
        lblTotal.setText(Formatter.formatCurrency(totalPurchase));
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
        jLabel1 = new javax.swing.JLabel();
        lblNumberPurchase = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        checkModeRange = new javax.swing.JCheckBox();

        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(purchaseTable);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Total ventas:");

        lblNumberPurchase.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        lblNumberPurchase.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Valor de ventas:");

        lblTotal.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        lblTotal.setText("jLabel2");

        checkModeRange.setText("Modo de rangos");
        checkModeRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkModeRangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(txtCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(checkModeRange)
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumberPurchase))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNumberPurchase))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblTotal))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkModeRange))
                        .addGap(42, 42, 42)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkModeRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkModeRangeActionPerformed
        // TODO add your handling code here:
        SetModeDateChoose ();
    }//GEN-LAST:event_checkModeRangeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkModeRange;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNumberPurchase;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable purchaseTable;
    private javax.swing.JTextField txtCalendar;
    // End of variables declaration//GEN-END:variables
}
