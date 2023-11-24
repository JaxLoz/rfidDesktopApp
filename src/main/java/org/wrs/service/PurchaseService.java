package org.wrs.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.dao.PurchaseDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Purchase;
import org.wrs.models.Student;

/**
 *
 * @author C.Mateo
 */
public class PurchaseService {

    private final PurchaseDao purchaseDao;
    private final StudentDao studentDao;

    public PurchaseService(PurchaseDao purchaseDao, StudentDao studentDao) {
        this.purchaseDao = purchaseDao;
        this.studentDao = studentDao;
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
  
}
