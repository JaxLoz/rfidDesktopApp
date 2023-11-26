package org.wrs.util;

import javax.swing.JTextField;

/**
 *
 * @author C.Mateo
 */
public class FormValidator {

    public static boolean areFieldsNotEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            if (isEmpty(field.getText())) {
                return false;  // Al menos un campo está vacío
            }
        }
        return true;  // Todos los campos están llenos
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
