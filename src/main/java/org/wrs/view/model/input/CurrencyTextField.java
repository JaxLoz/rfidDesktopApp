package org.wrs.view.model.input;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * JTextField extension for currency input.
 */
public class CurrencyTextField extends JTextField {

    private double numericValue;
    private String decimal;
    private DecimalFormat format;

    /**
     * Default constructor.
     */
    public CurrencyTextField() {
    }

    /**
     * Set the decimal format for the currency field.
     *
     * @param format The decimal format.
     */
    public void setFormat(DecimalFormat format) {
        this.format = format;
        init();
    }

    private void init() {
        decimal = Character.toString(format.getDecimalFormatSymbols().getDecimalSeparator());

        setColumns(format.toPattern().length());
        setHorizontalAlignment(JTextField.TRAILING);

        setText(format.format(0.0));

        AbstractDocument doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(new ABMFilter());
    }

   

    public double getNumericValue() {
        return numericValue;
    }

    @Override
    public void setText(String text) {
        Number number = format.parse(text, new ParsePosition(0));

        if (number != null) {

            numericValue = number.doubleValue();

            super.setText(text);
        }
    }

    /**
     * DocumentFilter for currency text field.
     */
    public class ABMFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
                throws BadLocationException {
            replace(fb, offs, 0, str, a);
        }

        @Override
        public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
                throws BadLocationException {
            if (".0123456789".contains(str)) {
                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));

                int decimalOffset = sb.indexOf(decimal);

                if (decimalOffset != -1) {
                    sb.deleteCharAt(decimalOffset);
                    sb.insert(decimalOffset + 1, decimal);
                }

                sb.append(str);

                try {
                    numericValue = format.parse(sb.toString()).doubleValue();
                    String text = format.format(format.parse(sb.toString()));
                    super.replace(fb, 0, doc.getLength(), text, a);
                } catch (ParseException e) {
                    e.printStackTrace(); // Print the exception stack trace for debugging.
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));

            int decimalOffset = sb.indexOf(decimal);

            if (decimalOffset != -1) {
                sb.deleteCharAt(decimalOffset);
                sb.insert(decimalOffset - 1, decimal);
            }

            sb.deleteCharAt(sb.length() - 1);

            try {
                numericValue = format.parse(sb.toString()).doubleValue();
                String text = format.format(format.parse(sb.toString()));
                super.replace(fb, 0, doc.getLength(), text, null);
            } catch (ParseException e) {

            }
        }
    }
}
