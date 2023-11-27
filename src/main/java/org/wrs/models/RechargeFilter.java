package org.wrs.models;

/**
 *
 * @author C.Mateo
 */
public class RechargeFilter {
    
    private String studentName;
    private String paymentType;
    private String status;
    private boolean isConfirmed;
    private String specificDate;
    private String fromDate;
    private String toDate;

    public RechargeFilter() {
        studentName = null;
        paymentType = null;
        status = null;
        isConfirmed = false;
        specificDate = null;
        fromDate = null;
        toDate = null;
    }

    public RechargeFilter(String studentName, String paymentType, String status, boolean isConfirmed, String specificDate, String fromDate, String toDate) {
        this.studentName = studentName;
        this.paymentType = paymentType;
        this.status = status;
        this.isConfirmed = isConfirmed;
        this.specificDate = specificDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getSpecificDate() {
        return specificDate;
    }

    public void setSpecificDate(String specificDate) {
        this.specificDate = specificDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    
    
    
}
