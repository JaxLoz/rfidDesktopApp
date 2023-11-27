package org.wrs.view.model.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StudentValueBalanceCellRender extends DefaultTableCellRenderer {
    
    private JLabel getBalance (String balance){
        String abalance = balance;
        String DeFormatterValue = abalance.replace("$", "").replace(" ", "").replace(".", "").replace(",", ".");
        Double value = Double.valueOf(DeFormatterValue);
        
        JLabel jlabel = new JLabel(balance);
        
        if(value < 0.0){
            jlabel.setForeground(Color.red);
        }else if (value > 0.0){
            jlabel.setForeground(Color.green);
        }
        
        return jlabel;
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel jLabel = getBalance("" + value);
        return jLabel;
    }
    
}
