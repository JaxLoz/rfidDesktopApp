package org.wrs.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author C.Mateo
 */
public class Formatter {

    private static final Locale colombiaLocale = new Locale("es", "CO");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", colombiaLocale);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", colombiaLocale);


    public static String formatCurrency(double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(colombiaLocale);
        return numberFormat.format(value);
    }

    public static String formatDate(String date) {
        LocalDateTime fecha = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", colombiaLocale));
    }

    public static String currentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentDateTime);
    }

    public static String currentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(dateFormatter);
    }
}
