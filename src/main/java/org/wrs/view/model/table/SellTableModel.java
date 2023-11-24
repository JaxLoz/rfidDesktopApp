package org.wrs.view.model.table;

import org.wrs.models.Purchase;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SellTableModel extends AbstractTableModel {

    private List<Purchase> listSell;
    private final String COLUMN_NAMES[];

    public SellTableModel (){
        super();
        this.listSell = new ArrayList<>();
        this.COLUMN_NAMES = new String[]{"ID", "Fecha de compra", "Total"};
    }

    public void setSellList (List<Purchase> listSell){
        this.listSell = listSell;
        fireTableDataChanged();
    }

    public List<Purchase> getListSell (){
        return this.listSell;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getRowCount() {
        return listSell.size();
    }

    @Override
    public int getColumnCount() {
        return this.COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Purchase sell = listSell.get(rowIndex);

       switch (columnIndex){
           case 0:
               return sell.getId();
           case 1:
               return sell.getDate();
           case 2:
               return sell.getTotal();
       }

        return sell;
    }
}
