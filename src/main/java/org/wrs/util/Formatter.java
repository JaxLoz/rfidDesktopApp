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


    public static String formatCurrency(double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(colombiaLocale);
        return numberFormat.format(value);
    }

    public static String formatDate(String date) {
        LocalDateTime fecha = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", colombiaLocale));
    }

    public static String formatDateV2(String data) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", colombiaLocale);

        LocalDateTime dateTime = LocalDateTime.parse(data, inputFormatter);
        String formattedDateTime = dateTime.format(outputFormatter);

        return formattedDateTime;
    }

    public static String currentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(dateFormatter);
    }
}
