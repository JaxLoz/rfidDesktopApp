package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import org.wrs.models.PurchaseInfo;
import org.wrs.service.PurchaseService;
import org.wrs.util.Formatter;
import raven.application.form.other.PurchaseForm;

public class SearchDateController implements ActionListener, ISearchDatePurchase {

    private final PurchaseForm purchaseForm;
    private final PurchaseService purchaseService;

    public SearchDateController(PurchaseForm purchaseForm, PurchaseService purchaseService) {
        this.purchaseForm = purchaseForm;
        this.purchaseService = purchaseService;
        init();
    }

    public void init() {
        refreshPurchaseTable();
        refreshDataPurchase();
    }

    public void refreshPurchaseTable() {

        PurchaseInfo purchaseCurrentDate = getPurchaseInfoCurrentDate();
        purchaseForm.loadTableSellInfo(purchaseCurrentDate.getListPurchase());
    }

    public void refreshDataPurchase() {
        PurchaseInfo purchaseCurrentDate = getPurchaseInfoCurrentDate();
        purchaseForm.setInfoPurchaseInForm(purchaseCurrentDate);
    }

    public PurchaseInfo getPurchaseInfoCurrentDate() {
        PurchaseInfo purchaseInforCurrentDate = null;
        String currentDate = Formatter.currentDate();
        System.out.println("Fecha consultada: "+currentDate);
        purchaseInforCurrentDate = purchaseService.getPurchaseInfoTo(currentDate);

        return purchaseInforCurrentDate;
    }

    @Override
    public void getSingleDate(String singleDate) {
        PurchaseInfo purchaseInforCurrentDate = null;
        purchaseInforCurrentDate = purchaseService.getPurchaseInfoTo(singleDate);
        purchaseForm.loadTableSellInfo(purchaseInforCurrentDate.getListPurchase());
        purchaseForm.setInfoPurchaseInForm(purchaseInforCurrentDate);
    }

    @Override
    public void getBetween(String since, String to) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                PurchaseInfo purchaseInforCurrentDate = null;
                purchaseInforCurrentDate = purchaseService.getPurchaseInfoRange(since, to);
                purchaseForm.loadTableSellInfo(purchaseInforCurrentDate.getListPurchase());
                purchaseForm.setInfoPurchaseInForm(purchaseInforCurrentDate);
            }
        };
        thread.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch(command){
            case "registerPurchaseBtn" -> System.out.println("clase SearchData");
            
            
            default -> {}
        }
    }

}
