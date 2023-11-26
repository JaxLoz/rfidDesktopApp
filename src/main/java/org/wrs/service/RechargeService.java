package org.wrs.service;

import java.util.List;
import org.wrs.dao.RechargeDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Recharge;
import org.wrs.models.Student;

public class RechargeService {

    private final StudentDao studentDao;
    private final RechargeDao rechargeDao;

    public RechargeService(StudentDao studentDao, RechargeDao rechargeDao) {
        this.studentDao = studentDao;
        this.rechargeDao = rechargeDao;
    }

    public List<Recharge> getAll() {
        return rechargeDao.all();
    }

    public boolean studentExists(String uuid) {
        return studentDao.thereIsStudent(uuid);
    }

    public Student getStudent(String uuid) {
        return studentDao.getStudent(uuid);
    }

    public void registerPurchase(Recharge recharge) {
        rechargeDao.save(recharge);
    }

}
