package org.wrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.wrs.models.Purchase;
import org.wrs.models.PurchaseInfo;

public class PurchaseInfoDao {
    
    private DataSource dataSource;
    
    public PurchaseInfoDao (DataSource datasource){
        this.dataSource = datasource;
    }
    
    public PurchaseInfo getInfoPurchaseRange (String since, String to){
        PurchaseInfo purchaseInfoRange = new PurchaseInfo();
        List<Purchase> listPurchase = new ArrayList<>();
        
        try(Connection con = dataSource.getConnection()){
            PreparedStatement prestatementPurchase = con.prepareStatement("select id, TO_CHAR(date_time, 'DD-MM-YYYY HH24:MI:SS'), total from purchase where date_time  between CAST(? AS TIMESTAMP) and CAST(? AS TIMESTAMP) order by date_time  DESC");
            prestatementPurchase.setString(1, since);
            prestatementPurchase.setString(2, to);
            prestatementPurchase.executeQuery();
            ResultSet resultset = prestatementPurchase.getResultSet();
            
            try (resultset){
                while(resultset.next()){
                    Purchase purchase = new Purchase(resultset.getInt(1),
                                                    resultset.getString(2),
                                                   resultset.getDouble(3));
                    
                    listPurchase.add(purchase);
                    purchaseInfoRange = new PurchaseInfo(listPurchase);
                }
            }
            
            
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        
        return purchaseInfoRange;
    }
    
    
    
    public PurchaseInfo getInfoPurchaseTo (String to){
        PurchaseInfo purchaseInfoTo = new PurchaseInfo();
        List<Purchase> listPurchase = new ArrayList<>();
        
        try(Connection con = dataSource.getConnection()){
            PreparedStatement prestatementPurchase = con.prepareStatement("select id, TO_CHAR(date_time, 'DD-MM-YYYY HH24:MI:SS'), total from purchase where DATE(date_time) = CAST(? AS TIMESTAMP) order by date_time  DESC");
            prestatementPurchase.setString(1, to);
            prestatementPurchase.executeQuery();
            ResultSet resultset = prestatementPurchase.getResultSet();
            
            try (resultset){
                while(resultset.next()){
                    Purchase purchase = new Purchase(resultset.getInt(1),
                                                    resultset.getString(2),
                                                   resultset.getDouble(3));
                    
                    listPurchase.add(purchase);
                    purchaseInfoTo = new PurchaseInfo(listPurchase);
                }
            }
            
            
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        
        return purchaseInfoTo;
    }
    
}
