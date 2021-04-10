package android.icu.text;

import android.icu.impl.DontCareFieldPosition;
import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SimpleCache;
import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.number.LongNameHandler;
import android.icu.number.FormattedNumber;
import android.icu.number.LocalizedNumberFormatter;
import android.icu.number.NumberFormatter;
import android.icu.number.Precision;
import android.icu.text.DateFormat;
import android.icu.text.ListFormatter;
import android.icu.util.Currency;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.concurrent.ConcurrentHashMap;

public class MeasureFormat extends UFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CURRENCY_FORMAT = 2;
    private static final int MEASURE_FORMAT = 0;
    static final int NUMBER_FORMATTER_CURRENCY = 2;
    static final int NUMBER_FORMATTER_INTEGER = 3;
    static final int NUMBER_FORMATTER_STANDARD = 1;
    private static final int TIME_UNIT_FORMAT = 1;
    private static final Map<MeasureUnit, Integer> hmsTo012 = new HashMap();
    private static final Map<ULocale, String> localeIdToRangeFormat = new ConcurrentHashMap();
    private static final SimpleCache<ULocale, NumericFormatters> localeToNumericDurationFormatters = new SimpleCache<>();
    static final long serialVersionUID = -7182021401701778240L;
    private final transient FormatWidth formatWidth;
    private transient NumberFormatterCacheEntry formatter1;
    private transient NumberFormatterCacheEntry formatter2;
    private transient NumberFormatterCacheEntry formatter3;
    private final transient NumberFormat numberFormat;
    private final transient LocalizedNumberFormatter numberFormatter;
    private final transient NumericFormatters numericFormatters;
    private final transient PluralRules rules;

    static {
        hmsTo012.put(MeasureUnit.HOUR, 0);
        hmsTo012.put(MeasureUnit.MINUTE, 1);
        hmsTo012.put(MeasureUnit.SECOND, 2);
    }

    public enum FormatWidth {
        WIDE(ListFormatter.Style.DURATION, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.FULL_NAME),
        SHORT(ListFormatter.Style.DURATION_SHORT, NumberFormatter.UnitWidth.SHORT, NumberFormatter.UnitWidth.ISO_CODE),
        NARROW(ListFormatter.Style.DURATION_NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
        NUMERIC(ListFormatter.Style.DURATION_NARROW, NumberFormatter.UnitWidth.NARROW, NumberFormatter.UnitWidth.SHORT),
        DEFAULT_CURRENCY(ListFormatter.Style.DURATION, NumberFormatter.UnitWidth.FULL_NAME, NumberFormatter.UnitWidth.SHORT);
        
        final NumberFormatter.UnitWidth currencyWidth;
        private final ListFormatter.Style listFormatterStyle;
        final NumberFormatter.UnitWidth unitWidth;

        private FormatWidth(ListFormatter.Style style, NumberFormatter.UnitWidth unitWidth2, NumberFormatter.UnitWidth currencyWidth2) {
            this.listFormatterStyle = style;
            this.unitWidth = unitWidth2;
            this.currencyWidth = currencyWidth2;
        }

        /* access modifiers changed from: package-private */
        public ListFormatter.Style getListFormatterStyle() {
            return this.listFormatterStyle;
        }
    }

    public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth2) {
        return getInstance(locale, formatWidth2, NumberFormat.getInstance(locale));
    }

    public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth2) {
        return getInstance(ULocale.forLocale(locale), formatWidth2);
    }

    public static MeasureFormat getInstance(ULocale locale, FormatWidth formatWidth2, NumberFormat format) {
        return new MeasureFormat(locale, formatWidth2, format, null, null);
    }

    public static MeasureFormat getInstance(Locale locale, FormatWidth formatWidth2, NumberFormat format) {
        return getInstance(ULocale.forLocale(locale), formatWidth2, format);
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition fpos) {
        int prevLength = toAppendTo.length();
        fpos.setBeginIndex(0);
        fpos.setEndIndex(0);
        if (obj instanceof Collection) {
            Collection<?> coll = (Collection) obj;
            Measure[] measures = new Measure[coll.size()];
            int idx = 0;
            for (Object o : coll) {
                if (o instanceof Measure) {
                    measures[idx] = (Measure) o;
                    idx++;
                } else {
                    throw new IllegalArgumentException(obj.toString());
                }
            }
            formatMeasuresInternal(toAppendTo, fpos, measures);
        } else if (obj instanceof Measure[]) {
            formatMeasuresInternal(toAppendTo, fpos, (Measure[]) obj);
        } else if (obj instanceof Measure) {
            FormattedNumber result = formatMeasure((Measure) obj);
            result.populateFieldPosition(fpos);
            result.appendTo(toAppendTo);
        } else {
            throw new IllegalArgumentException(obj.toString());
        }
        if (prevLength > 0 && fpos.getEndIndex() != 0) {
            fpos.setBeginIndex(fpos.getBeginIndex() + prevLength);
            fpos.setEndIndex(fpos.getEndIndex() + prevLength);
        }
        return toAppendTo;
    }

    @Override // java.text.Format
    public Measure parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    public final String formatMeasures(Measure... measures) {
        return formatMeasures(new StringBuilder(), DontCareFieldPosition.INSTANCE, measures).toString();
    }

    public StringBuilder formatMeasurePerUnit(Measure measure, MeasureUnit perUnit, StringBuilder appendTo, FieldPosition pos) {
        FormattedNumber result = getUnitFormatterFromCache(1, measure.getUnit(), perUnit).format(measure.getNumber());
        DecimalFormat.fieldPositionHelper(result, pos, appendTo.length());
        result.appendTo(appendTo);
        return appendTo;
    }

    public StringBuilder formatMeasures(StringBuilder appendTo, FieldPosition fpos, Measure... measures) {
        int prevLength = appendTo.length();
        formatMeasuresInternal(appendTo, fpos, measures);
        if (prevLength > 0 && fpos.getEndIndex() > 0) {
            fpos.setBeginIndex(fpos.getBeginIndex() + prevLength);
            fpos.setEndIndex(fpos.getEndIndex() + prevLength);
        }
        return appendTo;
    }

    private void formatMeasuresInternal(Appendable appendTo, FieldPosition fieldPosition, Measure... measures) {
        Number[] hms;
        if (measures.length != 0) {
            if (measures.length == 1) {
                FormattedNumber result = formatMeasure(measures[0]);
                result.populateFieldPosition(fieldPosition);
                result.appendTo(appendTo);
            } else if (this.formatWidth != FormatWidth.NUMERIC || (hms = toHMS(measures)) == null) {
                ListFormatter listFormatter = ListFormatter.getInstance(getLocale(), this.formatWidth.getListFormatterStyle());
                if (fieldPosition != DontCareFieldPosition.INSTANCE) {
                    formatMeasuresSlowTrack(listFormatter, appendTo, fieldPosition, measures);
                    return;
                }
                String[] results = new String[measures.length];
                for (int i = 0; i < measures.length; i++) {
                    if (i == measures.length - 1) {
                        results[i] = formatMeasure(measures[i]).toString();
                    } else {
                        results[i] = formatMeasureInteger(measures[i]).toString();
                    }
                }
                listFormatter.format(Arrays.asList(results), -1).appendTo(appendTo);
            } else {
                formatNumeric(hms, appendTo);
            }
        }
    }

    public String getUnitDisplayName(MeasureUnit unit) {
        return LongNameHandler.getUnitDisplayName(getLocale(), unit, this.formatWidth.unitWidth);
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MeasureFormat)) {
            return false;
        }
        MeasureFormat rhs = (MeasureFormat) other;
        if (getWidth() != rhs.getWidth() || !getLocale().equals(rhs.getLocale()) || !getNumberFormatInternal().equals(rhs.getNumberFormatInternal())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((getLocale().hashCode() * 31) + getNumberFormatInternal().hashCode()) * 31) + getWidth().hashCode();
    }

    public FormatWidth getWidth() {
        if (this.formatWidth == FormatWidth.DEFAULT_CURRENCY) {
            return FormatWidth.WIDE;
        }
        return this.formatWidth;
    }

    public final ULocale getLocale() {
        return getLocale(ULocale.VALID_LOCALE);
    }

    public NumberFormat getNumberFormat() {
        return (NumberFormat) this.numberFormat.clone();
    }

    /* access modifiers changed from: package-private */
    public NumberFormat getNumberFormatInternal() {
        return this.numberFormat;
    }

    public static MeasureFormat getCurrencyFormat(ULocale locale) {
        return new CurrencyFormat(locale);
    }

    public static MeasureFormat getCurrencyFormat(Locale locale) {
        return getCurrencyFormat(ULocale.forLocale(locale));
    }

    public static MeasureFormat getCurrencyFormat() {
        return getCurrencyFormat(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    /* access modifiers changed from: package-private */
    public MeasureFormat withLocale(ULocale locale) {
        return getInstance(locale, getWidth());
    }

    /* access modifiers changed from: package-private */
    public MeasureFormat withNumberFormat(NumberFormat format) {
        return new MeasureFormat(getLocale(), this.formatWidth, format, this.rules, this.numericFormatters);
    }

    MeasureFormat(ULocale locale, FormatWidth formatWidth2) {
        this(locale, formatWidth2, null, null, null);
    }

    private MeasureFormat(ULocale locale, FormatWidth formatWidth2, NumberFormat numberFormat2, PluralRules rules2, NumericFormatters formatters) {
        NumberFormat numberFormat3;
        this.formatter1 = null;
        this.formatter2 = null;
        this.formatter3 = null;
        setLocale(locale, locale);
        this.formatWidth = formatWidth2;
        this.rules = rules2 == null ? PluralRules.forLocale(locale) : rules2;
        if (numberFormat2 == null) {
            numberFormat3 = NumberFormat.getInstance(locale);
        } else {
            numberFormat3 = (NumberFormat) numberFormat2.clone();
        }
        this.numberFormat = numberFormat3;
        if (formatters == null && formatWidth2 == FormatWidth.NUMERIC && (formatters = localeToNumericDurationFormatters.get(locale)) == null) {
            formatters = loadNumericFormatters(locale);
            localeToNumericDurationFormatters.put(locale, formatters);
        }
        this.numericFormatters = formatters;
        if (numberFormat3 instanceof DecimalFormat) {
            this.numberFormatter = (LocalizedNumberFormatter) ((DecimalFormat) numberFormat3).toNumberFormatter().unitWidth(formatWidth2.unitWidth);
            return;
        }
        throw new IllegalArgumentException();
    }

    MeasureFormat(ULocale locale, FormatWidth formatWidth2, NumberFormat numberFormat2, PluralRules rules2) {
        this(locale, formatWidth2, numberFormat2, rules2, null);
        if (formatWidth2 == FormatWidth.NUMERIC) {
            throw new IllegalArgumentException("The format width 'numeric' is not allowed by this constructor");
        }
    }

    /* access modifiers changed from: package-private */
    public static class NumericFormatters {
        private DateFormat hourMinute;
        private DateFormat hourMinuteSecond;
        private DateFormat minuteSecond;

        public NumericFormatters(DateFormat hourMinute2, DateFormat minuteSecond2, DateFormat hourMinuteSecond2) {
            this.hourMinute = hourMinute2;
            this.minuteSecond = minuteSecond2;
            this.hourMinuteSecond = hourMinuteSecond2;
        }

        public DateFormat getHourMinute() {
            return this.hourMinute;
        }

        public DateFormat getMinuteSecond() {
            return this.minuteSecond;
        }

        public DateFormat getHourMinuteSecond() {
            return this.hourMinuteSecond;
        }
    }

    private static NumericFormatters loadNumericFormatters(ULocale locale) {
        ICUResourceBundle r = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_UNIT_BASE_NAME, locale);
        return new NumericFormatters(loadNumericDurationFormat(r, "hm"), loadNumericDurationFormat(r, DateFormat.MINUTE_SECOND), loadNumericDurationFormat(r, "hms"));
    }

    /* access modifiers changed from: package-private */
    public static class NumberFormatterCacheEntry {
        LocalizedNumberFormatter formatter;
        MeasureUnit perUnit;
        int type;
        MeasureUnit unit;

        NumberFormatterCacheEntry() {
        }
    }

    private synchronized LocalizedNumberFormatter getUnitFormatterFromCache(int type, MeasureUnit unit, MeasureUnit perUnit) {
        LocalizedNumberFormatter formatter;
        if (this.formatter1 != null) {
            if (this.formatter1.type == type && this.formatter1.unit == unit && this.formatter1.perUnit == perUnit) {
                return this.formatter1.formatter;
            } else if (this.formatter2 != null) {
                if (this.formatter2.type == type && this.formatter2.unit == unit && this.formatter2.perUnit == perUnit) {
                    return this.formatter2.formatter;
                } else if (this.formatter3 != null && this.formatter3.type == type && this.formatter3.unit == unit && this.formatter3.perUnit == perUnit) {
                    return this.formatter3.formatter;
                }
            }
        }
        if (type == 1) {
            formatter = (LocalizedNumberFormatter) ((LocalizedNumberFormatter) ((LocalizedNumberFormatter) getNumberFormatter().unit(unit)).perUnit(perUnit)).unitWidth(this.formatWidth.unitWidth);
        } else if (type == 2) {
            formatter = (LocalizedNumberFormatter) ((LocalizedNumberFormatter) ((LocalizedNumberFormatter) NumberFormatter.withLocale(getLocale()).unit(unit)).perUnit(perUnit)).unitWidth(this.formatWidth.currencyWidth);
        } else {
            formatter = (LocalizedNumberFormatter) ((LocalizedNumberFormatter) ((LocalizedNumberFormatter) ((LocalizedNumberFormatter) getNumberFormatter().unit(unit)).perUnit(perUnit)).unitWidth(this.formatWidth.unitWidth)).rounding(Precision.integer().withMode(RoundingMode.DOWN));
        }
        this.formatter3 = this.formatter2;
        this.formatter2 = this.formatter1;
        this.formatter1 = new NumberFormatterCacheEntry();
        this.formatter1.type = type;
        this.formatter1.unit = unit;
        this.formatter1.perUnit = perUnit;
        this.formatter1.formatter = formatter;
        return formatter;
    }

    /* access modifiers changed from: package-private */
    public synchronized void clearCache() {
        this.formatter1 = null;
        this.formatter2 = null;
        this.formatter3 = null;
    }

    /* access modifiers changed from: package-private */
    public LocalizedNumberFormatter getNumberFormatter() {
        return this.numberFormatter;
    }

    private FormattedNumber formatMeasure(Measure measure) {
        MeasureUnit unit = measure.getUnit();
        if (unit instanceof Currency) {
            return getUnitFormatterFromCache(2, unit, null).format(measure.getNumber());
        }
        return getUnitFormatterFromCache(1, unit, null).format(measure.getNumber());
    }

    private FormattedNumber formatMeasureInteger(Measure measure) {
        return getUnitFormatterFromCache(3, measure.getUnit(), null).format(measure.getNumber());
    }

    private void formatMeasuresSlowTrack(ListFormatter listFormatter, Appendable appendTo, FieldPosition fieldPosition, Measure... measures) {
        FormattedNumber result;
        String[] results = new String[measures.length];
        FieldPosition fpos = new FieldPosition(fieldPosition.getFieldAttribute(), fieldPosition.getField());
        int fieldPositionFoundIndex = -1;
        for (int i = 0; i < measures.length; i++) {
            if (i == measures.length - 1) {
                result = formatMeasure(measures[i]);
            } else {
                result = formatMeasureInteger(measures[i]);
            }
            if (fieldPositionFoundIndex == -1) {
                result.populateFieldPosition(fpos);
                if (fpos.getEndIndex() != 0) {
                    fieldPositionFoundIndex = i;
                }
            }
            results[i] = result.toString();
        }
        ListFormatter.FormattedListBuilder builder = listFormatter.format(Arrays.asList(results), fieldPositionFoundIndex);
        if (builder.getOffset() != -1) {
            fieldPosition.setBeginIndex(fpos.getBeginIndex() + builder.getOffset());
            fieldPosition.setEndIndex(fpos.getEndIndex() + builder.getOffset());
        }
        builder.appendTo(appendTo);
    }

    private static DateFormat loadNumericDurationFormat(ICUResourceBundle r, String type) {
        DateFormat result = new SimpleDateFormat(r.getWithFallback(String.format("durationUnits/%s", type)).getString().replace("h", DateFormat.HOUR24));
        result.setTimeZone(TimeZone.GMT_ZONE);
        return result;
    }

    private static Number[] toHMS(Measure[] measures) {
        Integer idxObj;
        int idx;
        Number[] result = new Number[3];
        int lastIdx = -1;
        for (Measure m : measures) {
            if (m.getNumber().doubleValue() < 0.0d || (idxObj = hmsTo012.get(m.getUnit())) == null || (idx = idxObj.intValue()) <= lastIdx) {
                return null;
            }
            lastIdx = idx;
            result[idx] = m.getNumber();
        }
        return result;
    }

    private void formatNumeric(Number[] hms, Appendable appendable) {
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < hms.length; i++) {
            if (hms[i] == null) {
                hms[i] = 0;
            } else if (startIndex == -1) {
                endIndex = i;
                startIndex = i;
            } else {
                endIndex = i;
            }
        }
        Date d = new Date((long) (((((Math.floor(hms[0].doubleValue()) * 60.0d) + Math.floor(hms[1].doubleValue())) * 60.0d) + Math.floor(hms[2].doubleValue())) * 1000.0d));
        if (startIndex == 0 && endIndex == 2) {
            formatNumeric(d, this.numericFormatters.getHourMinuteSecond(), DateFormat.Field.SECOND, hms[endIndex], appendable);
        } else if (startIndex == 1 && endIndex == 2) {
            formatNumeric(d, this.numericFormatters.getMinuteSecond(), DateFormat.Field.SECOND, hms[endIndex], appendable);
        } else if (startIndex == 0 && endIndex == 1) {
            formatNumeric(d, this.numericFormatters.getHourMinute(), DateFormat.Field.MINUTE, hms[endIndex], appendable);
        } else {
            throw new IllegalStateException();
        }
    }

    private void formatNumeric(Date duration, DateFormat formatter, DateFormat.Field smallestField, Number smallestAmount, Appendable appendTo) {
        String draft;
        FieldPosition intFieldPosition = new FieldPosition(0);
        FormattedNumber result = getNumberFormatter().format(smallestAmount);
        result.populateFieldPosition(intFieldPosition);
        String smallestAmountFormatted = result.toString();
        if (intFieldPosition.getBeginIndex() == 0 && intFieldPosition.getEndIndex() == 0) {
            throw new IllegalStateException();
        }
        FieldPosition smallestFieldPosition = new FieldPosition(smallestField);
        synchronized (formatter) {
            draft = formatter.format(duration, new StringBuffer(), smallestFieldPosition).toString();
        }
        try {
            if (smallestFieldPosition.getBeginIndex() == 0) {
                if (smallestFieldPosition.getEndIndex() == 0) {
                    appendTo.append(draft);
                    return;
                }
            }
            appendTo.append(draft, 0, smallestFieldPosition.getBeginIndex());
            appendTo.append(smallestAmountFormatted, 0, intFieldPosition.getBeginIndex());
            appendTo.append(draft, smallestFieldPosition.getBeginIndex(), smallestFieldPosition.getEndIndex());
            appendTo.append(smallestAmountFormatted, intFieldPosition.getEndIndex(), smallestAmountFormatted.length());
            appendTo.append(draft, smallestFieldPosition.getEndIndex(), draft.length());
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public Object toTimeUnitProxy() {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 1);
    }

    /* access modifiers changed from: package-private */
    public Object toCurrencyProxy() {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 2);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new MeasureProxy(getLocale(), this.formatWidth, getNumberFormatInternal(), 0);
    }

    static class MeasureProxy implements Externalizable {
        private static final long serialVersionUID = -6033308329886716770L;
        private FormatWidth formatWidth;
        private HashMap<Object, Object> keyValues;
        private ULocale locale;
        private NumberFormat numberFormat;
        private int subClass;

        public MeasureProxy(ULocale locale2, FormatWidth width, NumberFormat numberFormat2, int subClass2) {
            this.locale = locale2;
            this.formatWidth = width;
            this.numberFormat = numberFormat2;
            this.subClass = subClass2;
            this.keyValues = new HashMap<>();
        }

        public MeasureProxy() {
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(0);
            out.writeUTF(this.locale.toLanguageTag());
            out.writeByte(this.formatWidth.ordinal());
            out.writeObject(this.numberFormat);
            out.writeByte(this.subClass);
            out.writeObject(this.keyValues);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            in.readByte();
            this.locale = ULocale.forLanguageTag(in.readUTF());
            this.formatWidth = MeasureFormat.fromFormatWidthOrdinal(in.readByte() & 255);
            this.numberFormat = (NumberFormat) in.readObject();
            if (this.numberFormat != null) {
                this.subClass = in.readByte() & 255;
                this.keyValues = (HashMap) in.readObject();
                if (this.keyValues == null) {
                    throw new InvalidObjectException("Missing optional values map.");
                }
                return;
            }
            throw new InvalidObjectException("Missing number format.");
        }

        private TimeUnitFormat createTimeUnitFormat() throws InvalidObjectException {
            int style;
            if (this.formatWidth == FormatWidth.WIDE) {
                style = 0;
            } else if (this.formatWidth == FormatWidth.SHORT) {
                style = 1;
            } else {
                throw new InvalidObjectException("Bad width: " + ((Object) this.formatWidth));
            }
            TimeUnitFormat result = new TimeUnitFormat(this.locale, style);
            result.setNumberFormat(this.numberFormat);
            return result;
        }

        private Object readResolve() throws ObjectStreamException {
            int i = this.subClass;
            if (i == 0) {
                return MeasureFormat.getInstance(this.locale, this.formatWidth, this.numberFormat);
            }
            if (i == 1) {
                return createTimeUnitFormat();
            }
            if (i == 2) {
                return MeasureFormat.getCurrencyFormat(this.locale);
            }
            throw new InvalidObjectException("Unknown subclass: " + this.subClass);
        }
    }

    /* access modifiers changed from: private */
    public static FormatWidth fromFormatWidthOrdinal(int ordinal) {
        FormatWidth[] values = FormatWidth.values();
        if (ordinal < 0 || ordinal >= values.length) {
            return FormatWidth.SHORT;
        }
        return values[ordinal];
    }

    @Deprecated
    public static String getRangeFormat(ULocale forLocale, FormatWidth width) {
        String resultString;
        String result;
        if (forLocale.getLanguage().equals("fr")) {
            return getRangeFormat(ULocale.ROOT, width);
        }
        String result2 = localeIdToRangeFormat.get(forLocale);
        if (result2 == null) {
            ICUResourceBundle rb = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, forLocale);
            ULocale realLocale = rb.getULocale();
            if (forLocale.equals(realLocale) || (result = localeIdToRangeFormat.get(forLocale)) == null) {
                NumberingSystem ns = NumberingSystem.getInstance(forLocale);
                try {
                    resultString = rb.getStringWithFallback("NumberElements/" + ns.getName() + "/miscPatterns/range");
                } catch (MissingResourceException e) {
                    resultString = rb.getStringWithFallback("NumberElements/latn/patterns/range");
                }
                result2 = SimpleFormatterImpl.compileToStringMinMaxArguments(resultString, new StringBuilder(), 2, 2);
                localeIdToRangeFormat.put(forLocale, result2);
                if (!forLocale.equals(realLocale)) {
                    localeIdToRangeFormat.put(realLocale, result2);
                }
            } else {
                localeIdToRangeFormat.put(forLocale, result);
                return result;
            }
        }
        return result2;
    }
}
