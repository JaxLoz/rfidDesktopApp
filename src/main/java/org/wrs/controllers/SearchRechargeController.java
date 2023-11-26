package org.wrs.controllers;

import java.util.List;
import org.wrs.models.Recharge;
import org.wrs.service.RechargeService;
import raven.application.form.other.RechargeForm;

/**
 *
 * @author C.Mateo
 */
public class SearchRechargeController implements ISearch {
    
    private final RechargeForm rechargeForm;
    private final RechargeService rechargeService;

    public SearchRechargeController(RechargeForm rechargeForm, RechargeService rechargeService) {
        this.rechargeForm = rechargeForm;
        this.rechargeService = rechargeService;
    }
    
    @Override
    public void search(String key) {
        List<Recharge> recharges = rechargeService.filterRecharge(null, null, key, null, null, false);
        rechargeForm.setListRechargeTableModel(recharges);
    }
}
