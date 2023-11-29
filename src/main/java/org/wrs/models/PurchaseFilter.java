package org.wrs.models;


public class PurchaseFilter {

    private String fromDate;
    private String toDate;
    private String specificDate;
    private String studentName;
    
    public PurchaseFilter(){
        fromDate = null;
        toDate = null;
        specificDate = null;
        studentName = null;
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

    public String getSpecificDate() {
        return specificDate;
    }

    public void setSpecificDate(String specificDate) {
        this.specificDate = specificDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    
    
}
