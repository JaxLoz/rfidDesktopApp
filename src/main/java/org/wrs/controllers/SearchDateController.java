package org.wrs.controllers;

import org.wrs.models.PurchaseInfo;
import org.wrs.service.PurchaseService;
import org.wrs.util.Formatter;
import raven.application.form.other.PurchaseForm;

public class SearchDateController implements ISearchDatePurchase {

    private final PurchaseForm purchaseForm;
    private final PurchaseService purchaseService;

    public SearchDateController(PurchaseForm purchaseForm, PurchaseService purchaseService) {
        this.purchaseForm = purchaseForm;
        this.purchaseService = purchaseService;
        init();
    }

    private void init() {
        purchaseForm.setISearchDatePurchase(this);
        getSingleDate(Formatter.currentDate());
    }

    @Override
    public void getSingleDate(String singleDate) {
        PurchaseInfo purchaseInforCurrentDate = purchaseService.getPurchaseInfoTo(singleDate);
        purchaseForm.loadTableSellInfo(purchaseInforCurrentDate.getListPurchase());
        purchaseForm.setInfoPurchaseInForm(purchaseInforCurrentDate);
    }

    @Override
    public void getBetween(String since, String to) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                PurchaseInfo purchaseInforCurrentDate = purchaseService.getPurchaseInfoRange(since, to);
                purchaseForm.loadTableSellInfo(purchaseInforCurrentDate.getListPurchase());
                purchaseForm.setInfoPurchaseInForm(purchaseInforCurrentDate);
            }
        };
        thread.start();
    }

}
