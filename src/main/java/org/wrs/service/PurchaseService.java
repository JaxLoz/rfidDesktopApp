package org.wrs.service;


import java.util.List;
import org.wrs.dao.PurchaseDao;
import org.wrs.dao.PurchaseInfoDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Purchase;
import org.wrs.models.PurchaseFilter;
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
        purchaseDao.createPurchase(student, purchase);
    }
    
    public List<Purchase> filterPurchase(PurchaseFilter purchaseFilter){
        return purchaseInfoDao.filterPurchases(purchaseFilter);
    }
    
    public PurchaseInfo getPurchaseInfoTo (String to){
        PurchaseInfo purchaseInfoTo = purchaseInfoDao.getInfoPurchaseTo(to);
        return purchaseInfoTo;
    }
    
    public PurchaseInfo getPurchaseInfoRange (String since, String to){
        PurchaseInfo purchaseInfoRange = purchaseInfoDao.getInfoPurchaseRange(since, to);
        return purchaseInfoRange;
    }
  
}
