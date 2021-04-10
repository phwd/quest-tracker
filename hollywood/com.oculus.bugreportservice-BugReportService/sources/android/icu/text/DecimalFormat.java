package android.icu.text;

import android.icu.impl.number.DecimalFormatProperties;
import android.icu.impl.number.PatternStringParser;
import android.icu.impl.number.parse.NumberParserImpl;
import android.icu.impl.number.parse.ParsedNumber;
import android.icu.number.FormattedNumber;
import android.icu.number.LocalizedNumberFormatter;
import android.icu.number.NumberFormatter;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.ParsePosition;

public class DecimalFormat extends NumberFormat {
    private static final long serialVersionUID = 864413376551465018L;
    volatile transient NumberParserImpl currencyParser;
    volatile transient DecimalFormatProperties exportedProperties;
    volatile transient LocalizedNumberFormatter formatter;
    private transient int icuMathContextForm = 0;
    volatile transient NumberParserImpl parser;
    transient DecimalFormatProperties properties;
    private final int serialVersionOnStream = 5;
    volatile transient DecimalFormatSymbols symbols;

    public DecimalFormat() {
        String pattern = NumberFormat.getPattern(ULocale.getDefault(ULocale.Category.FORMAT), 0);
        this.symbols = getDefaultSymbols();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        setPropertiesFromPattern(pattern, 1);
        refreshFormatter();
    }

    public DecimalFormat(String str, DecimalFormatSymbols decimalFormatSymbols) {
        this.symbols = (DecimalFormatSymbols) decimalFormatSymbols.clone();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        setPropertiesFromPattern(str, 1);
        refreshFormatter();
    }

    DecimalFormat(String str, DecimalFormatSymbols decimalFormatSymbols, int i) {
        this.symbols = (DecimalFormatSymbols) decimalFormatSymbols.clone();
        this.properties = new DecimalFormatProperties();
        this.exportedProperties = new DecimalFormatProperties();
        if (i == 1 || i == 5 || i == 7 || i == 8 || i == 9 || i == 6) {
            setPropertiesFromPattern(str, 2);
        } else {
            setPropertiesFromPattern(str, 1);
        }
        refreshFormatter();
    }

    private static DecimalFormatSymbols getDefaultSymbols() {
        return DecimalFormatSymbols.getInstance();
    }

    public synchronized void applyPattern(String str) {
        setPropertiesFromPattern(str, 0);
        this.properties.setPositivePrefix(null);
        this.properties.setNegativePrefix(null);
        this.properties.setPositiveSuffix(null);
        this.properties.setNegativeSuffix(null);
        this.properties.setCurrencyPluralInfo(null);
        refreshFormatter();
    }

    @Override // java.text.Format, android.icu.text.NumberFormat
    public Object clone() {
        DecimalFormat decimalFormat = (DecimalFormat) super.clone();
        decimalFormat.symbols = (DecimalFormatSymbols) this.symbols.clone();
        decimalFormat.properties = this.properties.clone();
        decimalFormat.exportedProperties = new DecimalFormatProperties();
        decimalFormat.refreshFormatter();
        return decimalFormat;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FormattedNumber format = this.formatter.format(d);
        fieldPositionHelper(format, fieldPosition, stringBuffer.length());
        format.appendTo(stringBuffer);
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FormattedNumber format = this.formatter.format(j);
        fieldPositionHelper(format, fieldPosition, stringBuffer.length());
        format.appendTo(stringBuffer);
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FormattedNumber format = this.formatter.format(bigInteger);
        fieldPositionHelper(format, fieldPosition, stringBuffer.length());
        format.appendTo(stringBuffer);
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FormattedNumber format = this.formatter.format(bigDecimal);
        fieldPositionHelper(format, fieldPosition, stringBuffer.length());
        format.appendTo(stringBuffer);
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(android.icu.math.BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FormattedNumber format = this.formatter.format(bigDecimal);
        fieldPositionHelper(format, fieldPosition, stringBuffer.length());
        format.appendTo(stringBuffer);
        return stringBuffer;
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (!(obj instanceof Number)) {
            throw new IllegalArgumentException();
        }
        this.formatter.format((Number) obj).getFieldIterator();
        throw null;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(CurrencyAmount currencyAmount, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FormattedNumber format = this.formatter.format(currencyAmount);
        fieldPositionHelper(format, fieldPosition, stringBuffer.length());
        format.appendTo(stringBuffer);
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public Number parse(String str, ParsePosition parsePosition) {
        if (str != null) {
            if (parsePosition == null) {
                parsePosition = new ParsePosition(0);
            }
            if (parsePosition.getIndex() < 0) {
                throw new IllegalArgumentException("Cannot start parsing at a negative offset");
            } else if (parsePosition.getIndex() >= str.length()) {
                return null;
            } else {
                ParsedNumber parsedNumber = new ParsedNumber();
                int index = parsePosition.getIndex();
                NumberParserImpl parser2 = getParser();
                parser2.parse(str, index, true, parsedNumber);
                if (parsedNumber.success()) {
                    parsePosition.setIndex(parsedNumber.charEnd);
                    Number number = parsedNumber.getNumber(parser2.getParseFlags());
                    return number instanceof BigDecimal ? safeConvertBigDecimal((BigDecimal) number) : number;
                }
                parsePosition.setErrorIndex(index + parsedNumber.charEnd);
                return null;
            }
        } else {
            throw new IllegalArgumentException("Text cannot be null");
        }
    }

    @Override // android.icu.text.NumberFormat
    public synchronized int getMaximumFractionDigits() {
        return this.exportedProperties.getMaximumFractionDigits();
    }

    @Override // android.icu.text.NumberFormat
    public synchronized void setMaximumFractionDigits(int i) {
        int minimumFractionDigits = this.properties.getMinimumFractionDigits();
        if (minimumFractionDigits >= 0 && minimumFractionDigits > i) {
            this.properties.setMinimumFractionDigits(i);
        }
        this.properties.setMaximumFractionDigits(i);
        refreshFormatter();
    }

    @Override // android.icu.text.NumberFormat
    public synchronized void setGroupingUsed(boolean z) {
        this.properties.setGroupingUsed(z);
        refreshFormatter();
    }

    public synchronized void setDecimalSeparatorAlwaysShown(boolean z) {
        this.properties.setDecimalSeparatorAlwaysShown(z);
        refreshFormatter();
    }

    @Override // android.icu.text.NumberFormat
    public synchronized Currency getCurrency() {
        return this.exportedProperties.getCurrency();
    }

    @Override // android.icu.text.NumberFormat
    public synchronized void setCurrency(Currency currency) {
        this.properties.setCurrency(currency);
        if (currency != null) {
            this.symbols.setCurrency(currency);
            this.symbols.setCurrencySymbol(currency.getName(this.symbols.getULocale(), 0, null));
        }
        refreshFormatter();
    }

    public synchronized void setCurrencyUsage(Currency.CurrencyUsage currencyUsage) {
        this.properties.setCurrencyUsage(currencyUsage);
        refreshFormatter();
    }

    public synchronized void setCurrencyPluralInfo(CurrencyPluralInfo currencyPluralInfo) {
        this.properties.setCurrencyPluralInfo(currencyPluralInfo);
        refreshFormatter();
    }

    @Override // android.icu.text.NumberFormat
    public synchronized void setParseIntegerOnly(boolean z) {
        this.properties.setParseIntegerOnly(z);
        refreshFormatter();
    }

    @Override // android.icu.text.NumberFormat
    public synchronized boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DecimalFormat)) {
            return false;
        }
        DecimalFormat decimalFormat = (DecimalFormat) obj;
        if (this.properties.equals(decimalFormat.properties) && this.symbols.equals(decimalFormat.symbols)) {
            z = true;
        }
        return z;
    }

    @Override // android.icu.text.NumberFormat
    public synchronized int hashCode() {
        return this.properties.hashCode() ^ this.symbols.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DecimalFormat.class.getName());
        sb.append("@");
        Integer.toHexString(hashCode());
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String toPattern() {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat.toPattern():java.lang.String");
    }

    public LocalizedNumberFormatter toNumberFormatter() {
        return this.formatter;
    }

    /* access modifiers changed from: package-private */
    public void refreshFormatter() {
        if (this.exportedProperties != null) {
            ULocale locale = getLocale(ULocale.ACTUAL_LOCALE);
            if (locale == null) {
                locale = this.symbols.getLocale(ULocale.ACTUAL_LOCALE);
            }
            if (locale == null) {
                locale = this.symbols.getULocale();
            }
            this.formatter = NumberFormatter.fromDecimalFormat(this.properties, this.symbols, this.exportedProperties).locale(locale);
            this.parser = null;
            this.currencyParser = null;
        }
    }

    /* access modifiers changed from: package-private */
    public NumberParserImpl getParser() {
        if (this.parser == null) {
            this.parser = NumberParserImpl.createParserFromProperties(this.properties, this.symbols, false);
        }
        return this.parser;
    }

    private Number safeConvertBigDecimal(BigDecimal bigDecimal) {
        try {
            return new android.icu.math.BigDecimal(bigDecimal);
        } catch (NumberFormatException unused) {
            if (bigDecimal.signum() > 0 && bigDecimal.scale() < 0) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            if (bigDecimal.scale() < 0) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            if (bigDecimal.signum() < 0) {
                return Double.valueOf(-0.0d);
            }
            return Double.valueOf(0.0d);
        }
    }

    /* access modifiers changed from: package-private */
    public void setPropertiesFromPattern(String str, int i) {
        if (str != null) {
            PatternStringParser.parseToExistingProperties(str, this.properties, i);
            return;
        }
        throw new NullPointerException();
    }

    static void fieldPositionHelper(FormattedNumber formattedNumber, FieldPosition fieldPosition, int i) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        if (formattedNumber.nextFieldPosition(fieldPosition) && i != 0) {
            fieldPosition.setBeginIndex(fieldPosition.getBeginIndex() + i);
            fieldPosition.setEndIndex(fieldPosition.getEndIndex() + i);
        }
    }
}
