package org.wrs.view.model.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author C.Mateo
 */
public class RechargeStatusTableCellRenderer extends DefaultTableCellRenderer {

    private JLabel getStatus(String status) {
        JLabel jLabel = new JLabel(status);
        switch (status) {
            case "PENDIENTE" ->
                jLabel.setForeground(Color.BLUE);
            case "REALIZADO" ->
                jLabel.setForeground(Color.GREEN);
            case "CADUCADA" ->
                jLabel.setForeground(Color.RED);
        }
        return jLabel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel jLabel = getStatus("" + value);
        return jLabel;
    }

}
