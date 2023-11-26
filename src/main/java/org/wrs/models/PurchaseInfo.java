package org.wrs.models;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInfo {
    private List<Purchase> listPurchase;
    private Double totalPurchase;

    public PurchaseInfo(List<Purchase> listPurchase, Double totalPurchase) {
        this.listPurchase = listPurchase;
        this.totalPurchase = totalPurchase;
    }

    public PurchaseInfo(List<Purchase> listPurchase) {
        this.listPurchase = listPurchase;
        this.totalPurchase = 0.0;
    }

    public PurchaseInfo(Double totalPurchase) {
        this.totalPurchase = totalPurchase;
        this.listPurchase = new ArrayList<>();
    }

    public PurchaseInfo() {
        this.listPurchase = new ArrayList<>();
        this.totalPurchase = 0.0;
    }

    public List<Purchase> getListPurchase() {
        return listPurchase;
    }

    public void setListPurchase(List<Purchase> listPurchase) {
        this.listPurchase = listPurchase;
    }

    public Double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(Double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
    
    public Double sumPurchaseValue (){
        Double balancePurchase = 0.0;
        for(Purchase purchase: this.listPurchase){
            balancePurchase += purchase.getTotal();
        }
        
        return balancePurchase;
    }
    
}
