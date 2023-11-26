package org.wrs.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.wrs.dao.PurchaseDao;
import org.wrs.dao.PurchaseInfoDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Purchase;
import org.wrs.models.PurchaseInfo;
import org.wrs.models.Student;

/**
 *
 * @author C.Mateo
 */
public class PurchaseService {

    private final PurchaseDao purchaseDao;
    private final StudentDao studentDao;
    private final PurchaseInfoDao purchaseInfoDao;

    public PurchaseService(PurchaseDao purchaseDao, StudentDao studentDao, PurchaseInfoDao purchaseInfoDao) {
        this.purchaseDao = purchaseDao;
        this.studentDao = studentDao;
        this.purchaseInfoDao = purchaseInfoDao;
    }
    
    public boolean studentExists(String uuid){
        return studentDao.thereIsStudent(uuid);
    }
    
    public Student getStudent(String uuid){
        return studentDao.getStudent(uuid);
    }

    public void registerPurchase(Student student, Purchase purchase){
        //body method to register purchase
        purchaseDao.createPurchase(student, purchase);
    }
    
    public List<Purchase> getPurchaseList (Student student){
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList = purchaseDao.sellList(student);
        return purchaseList;
    }
    
    public PurchaseInfo getPurchaseInfoTo (String to){
        PurchaseInfo purchaseInfoTo = purchaseInfoDao.getInfoPurchaseTo(to);
        return purchaseInfoTo;
    }
    
    public PurchaseInfo getPurchaseInfoSince (String since){
        PurchaseInfo purchaseInfoSince = purchaseInfoDao.getInfoPurchaseSince(since);
        return purchaseInfoSince;
    }
    
    public PurchaseInfo getPurchaseInfoRange (String since, String to){
        PurchaseInfo purchaseInfoRange = purchaseInfoDao.getInfoPurchaseRange(since, to);
        return purchaseInfoRange;
    }
  
}
