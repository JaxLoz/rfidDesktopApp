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
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
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
        lblNumberPurchase = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        checkModeRange = new javax.swing.JCheckBox();
        lbTitle = new javax.swing.JLabel();
        totalTitleLb = new javax.swing.JLabel();
        totalTitleLb1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkModeRange))
                            .addComponent(lbTitle))
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkModeRange))
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
        SetModeDateChoose ();
    }//GEN-LAST:event_checkModeRangeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkModeRange;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblNumberPurchase;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable purchaseTable;
    private javax.swing.JLabel totalTitleLb;
    private javax.swing.JLabel totalTitleLb1;
    private javax.swing.JTextField txtCalendar;
    // End of variables declaration//GEN-END:variables
}
