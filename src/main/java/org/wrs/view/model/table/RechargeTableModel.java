package org.wrs.view.model.table;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import org.wrs.models.Recharge;
import org.wrs.models.Student;
import org.wrs.util.Formatter;

/**
 *
 * @author C.Mateo
 */
public class RechargeTableModel extends AbstractTableModel {

    private List<Recharge> rechargeList;
    private final String COLUMN_NAME[];

    public RechargeTableModel() {
        rechargeList = new ArrayList<>();
        this.COLUMN_NAME = new String[]{"Valor", "Fecha", "Confirmado", "Tipo Pago", "Estado", "Telefono", "Estudiante"};
    }

    public void setStudentList(List<Recharge> rechargeList) {
        this.rechargeList = rechargeList;
        fireTableDataChanged();
    }

    public List<Recharge> getStudentList() {
        return rechargeList;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAME[column];
    }

    @Override
    public int getRowCount() {
        return rechargeList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recharge recharge = rechargeList.get(rowIndex);
        Student student = recharge.getStudent();
        switch (columnIndex) {
            case 0 -> {
                return Formatter.formatCurrency(recharge.getAmount());
            }
            case 1 -> {
                return Formatter.formatDate(""+recharge.getDate());
            }
            case 2 -> {
                return recharge.isConfirmed() ? "CONFIRMADA": "NO CONFIRMADA" ;
            }
            case 3 -> {
                return recharge.getPaymentType();
            }
            case 4 -> {
                return recharge.getStatus();
            }
            case 5 -> {
                return recharge.getPhone();
            }
            case 6 -> {
                return student.getFirtsName() + " " + student.getLastName();
            }
        }

        return recharge;
    }

}
