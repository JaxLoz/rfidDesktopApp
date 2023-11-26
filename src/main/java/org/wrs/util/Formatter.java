package org.wrs.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author C.Mateo
 */
public class Formatter {
    
    private static final Locale locale = new Locale("es", "CO");

    public static String formatCurrency(double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(value);
    }
}
