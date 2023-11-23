package org.wrs.controllers;

import org.wrs.dao.SellDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Sell;
import org.wrs.models.Student;
import org.wrs.view.InfoSellView;
import org.wrs.view.SellView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InfoSellViewController implements ActionListener {

    private StudentDao studentDao;

    private SellDao sellDao;
    private InfoSellView infoSellView;

    private SellView sellView;

    private Student studenNow;

    public InfoSellViewController(StudentDao studentDao,SellDao sellDao, InfoSellView infoSellView){
        this.studentDao = studentDao;
        this.infoSellView = infoSellView;
        this.sellView = new SellView();
        this.sellDao = sellDao;
        this.studenNow = null;
        this.initSellViewListener();
    }

    public boolean isWindowActive (){
        return this.infoSellView.isActive();
    }

    public void initSellViewListener (){
        infoSellView.setActionListener(this);
    }

    public void getStudent (String uuid){
        this.studenNow = studentDao.getStudent(uuid);
        infoSellView.showStudentInformation(this.studenNow);
    }

    public void setNewSell (){
       Sell sell = this.infoSellView.getTotalSell();
       Student student = this.studenNow;
       Student studentP = sellDao.setNewSell(student, sell);
       infoSellView.showStudentInformation(studentP);
        List<Sell> sellList = sellDao.sellList(student);
        infoSellView.setSellTable(sellList);
    }

    public void setTableSell (){
        List<Sell> sellList = new ArrayList<>();
        sellList = sellDao.sellList(this.studenNow);
        infoSellView.setSellTable(sellList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("Nueva venta")){
            this.setNewSell();
            this.setTableSell();
            System.out.println("Se esta precionando el boton de nueva compra");
        }
    }
}
