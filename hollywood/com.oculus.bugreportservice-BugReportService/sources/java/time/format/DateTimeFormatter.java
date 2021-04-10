package java.time.format;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Period;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public final class DateTimeFormatter {
    public static final DateTimeFormatter BASIC_ISO_DATE;
    public static final DateTimeFormatter ISO_DATE;
    public static final DateTimeFormatter ISO_DATE_TIME;
    public static final DateTimeFormatter ISO_INSTANT;
    public static final DateTimeFormatter ISO_LOCAL_DATE;
    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter ISO_LOCAL_TIME;
    public static final DateTimeFormatter ISO_OFFSET_DATE;
    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME;
    public static final DateTimeFormatter ISO_OFFSET_TIME;
    public static final DateTimeFormatter ISO_ORDINAL_DATE;
    public static final DateTimeFormatter ISO_TIME;
    public static final DateTimeFormatter ISO_WEEK_DATE;
    public static final DateTimeFormatter ISO_ZONED_DATE_TIME;
    private static final TemporalQuery PARSED_EXCESS_DAYS = $$Lambda$DateTimeFormatter$QqeEAMXK7Qf5gsmaSCLmrVwQ1Ns.INSTANCE;
    private static final TemporalQuery PARSED_LEAP_SECOND = $$Lambda$DateTimeFormatter$GhpE1dbCMFpBqvhZZgrqVYpzk8E.INSTANCE;
    public static final DateTimeFormatter RFC_1123_DATE_TIME;
    private final Chronology chrono;
    private final DecimalStyle decimalStyle;
    private final Locale locale;
    private final DateTimeFormatterBuilder.CompositePrinterParser printerParser;
    private final Set resolverFields;
    private final ResolverStyle resolverStyle;
    private final ZoneId zone;

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD);
        dateTimeFormatterBuilder.appendLiteral('-');
        dateTimeFormatterBuilder.appendValue(ChronoField.MONTH_OF_YEAR, 2);
        dateTimeFormatterBuilder.appendLiteral('-');
        dateTimeFormatterBuilder.appendValue(ChronoField.DAY_OF_MONTH, 2);
        ISO_LOCAL_DATE = dateTimeFormatterBuilder.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder2 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder2.parseCaseInsensitive();
        dateTimeFormatterBuilder2.append(ISO_LOCAL_DATE);
        dateTimeFormatterBuilder2.appendOffsetId();
        ISO_OFFSET_DATE = dateTimeFormatterBuilder2.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder3.parseCaseInsensitive();
        dateTimeFormatterBuilder3.append(ISO_LOCAL_DATE);
        dateTimeFormatterBuilder3.optionalStart();
        dateTimeFormatterBuilder3.appendOffsetId();
        ISO_DATE = dateTimeFormatterBuilder3.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder4 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder4.appendValue(ChronoField.HOUR_OF_DAY, 2);
        dateTimeFormatterBuilder4.appendLiteral(':');
        dateTimeFormatterBuilder4.appendValue(ChronoField.MINUTE_OF_HOUR, 2);
        dateTimeFormatterBuilder4.optionalStart();
        dateTimeFormatterBuilder4.appendLiteral(':');
        dateTimeFormatterBuilder4.appendValue(ChronoField.SECOND_OF_MINUTE, 2);
        dateTimeFormatterBuilder4.optionalStart();
        dateTimeFormatterBuilder4.appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true);
        ISO_LOCAL_TIME = dateTimeFormatterBuilder4.toFormatter(ResolverStyle.STRICT, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder5 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder5.parseCaseInsensitive();
        dateTimeFormatterBuilder5.append(ISO_LOCAL_TIME);
        dateTimeFormatterBuilder5.appendOffsetId();
        ISO_OFFSET_TIME = dateTimeFormatterBuilder5.toFormatter(ResolverStyle.STRICT, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder6 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder6.parseCaseInsensitive();
        dateTimeFormatterBuilder6.append(ISO_LOCAL_TIME);
        dateTimeFormatterBuilder6.optionalStart();
        dateTimeFormatterBuilder6.appendOffsetId();
        ISO_TIME = dateTimeFormatterBuilder6.toFormatter(ResolverStyle.STRICT, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder7 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder7.parseCaseInsensitive();
        dateTimeFormatterBuilder7.append(ISO_LOCAL_DATE);
        dateTimeFormatterBuilder7.appendLiteral('T');
        dateTimeFormatterBuilder7.append(ISO_LOCAL_TIME);
        ISO_LOCAL_DATE_TIME = dateTimeFormatterBuilder7.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder8 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder8.parseCaseInsensitive();
        dateTimeFormatterBuilder8.append(ISO_LOCAL_DATE_TIME);
        dateTimeFormatterBuilder8.appendOffsetId();
        ISO_OFFSET_DATE_TIME = dateTimeFormatterBuilder8.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder9 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder9.append(ISO_OFFSET_DATE_TIME);
        dateTimeFormatterBuilder9.optionalStart();
        dateTimeFormatterBuilder9.appendLiteral('[');
        dateTimeFormatterBuilder9.parseCaseSensitive();
        dateTimeFormatterBuilder9.appendZoneRegionId();
        dateTimeFormatterBuilder9.appendLiteral(']');
        ISO_ZONED_DATE_TIME = dateTimeFormatterBuilder9.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder10 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder10.append(ISO_LOCAL_DATE_TIME);
        dateTimeFormatterBuilder10.optionalStart();
        dateTimeFormatterBuilder10.appendOffsetId();
        dateTimeFormatterBuilder10.optionalStart();
        dateTimeFormatterBuilder10.appendLiteral('[');
        dateTimeFormatterBuilder10.parseCaseSensitive();
        dateTimeFormatterBuilder10.appendZoneRegionId();
        dateTimeFormatterBuilder10.appendLiteral(']');
        ISO_DATE_TIME = dateTimeFormatterBuilder10.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder11 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder11.parseCaseInsensitive();
        dateTimeFormatterBuilder11.appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD);
        dateTimeFormatterBuilder11.appendLiteral('-');
        dateTimeFormatterBuilder11.appendValue(ChronoField.DAY_OF_YEAR, 3);
        dateTimeFormatterBuilder11.optionalStart();
        dateTimeFormatterBuilder11.appendOffsetId();
        ISO_ORDINAL_DATE = dateTimeFormatterBuilder11.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder12 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder12.parseCaseInsensitive();
        dateTimeFormatterBuilder12.appendValue(IsoFields.WEEK_BASED_YEAR, 4, 10, SignStyle.EXCEEDS_PAD);
        dateTimeFormatterBuilder12.appendLiteral("-W");
        dateTimeFormatterBuilder12.appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2);
        dateTimeFormatterBuilder12.appendLiteral('-');
        dateTimeFormatterBuilder12.appendValue(ChronoField.DAY_OF_WEEK, 1);
        dateTimeFormatterBuilder12.optionalStart();
        dateTimeFormatterBuilder12.appendOffsetId();
        ISO_WEEK_DATE = dateTimeFormatterBuilder12.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        DateTimeFormatterBuilder dateTimeFormatterBuilder13 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder13.parseCaseInsensitive();
        dateTimeFormatterBuilder13.appendInstant();
        ISO_INSTANT = dateTimeFormatterBuilder13.toFormatter(ResolverStyle.STRICT, null);
        DateTimeFormatterBuilder dateTimeFormatterBuilder14 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder14.parseCaseInsensitive();
        dateTimeFormatterBuilder14.appendValue(ChronoField.YEAR, 4);
        dateTimeFormatterBuilder14.appendValue(ChronoField.MONTH_OF_YEAR, 2);
        dateTimeFormatterBuilder14.appendValue(ChronoField.DAY_OF_MONTH, 2);
        dateTimeFormatterBuilder14.optionalStart();
        dateTimeFormatterBuilder14.appendOffset("+HHMMss", "Z");
        BASIC_ISO_DATE = dateTimeFormatterBuilder14.toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
        HashMap hashMap = new HashMap();
        hashMap.put(1L, "Mon");
        hashMap.put(2L, "Tue");
        hashMap.put(3L, "Wed");
        hashMap.put(4L, "Thu");
        hashMap.put(5L, "Fri");
        hashMap.put(6L, "Sat");
        hashMap.put(7L, "Sun");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1L, "Jan");
        hashMap2.put(2L, "Feb");
        hashMap2.put(3L, "Mar");
        hashMap2.put(4L, "Apr");
        hashMap2.put(5L, "May");
        hashMap2.put(6L, "Jun");
        hashMap2.put(7L, "Jul");
        hashMap2.put(8L, "Aug");
        hashMap2.put(9L, "Sep");
        hashMap2.put(10L, "Oct");
        hashMap2.put(11L, "Nov");
        hashMap2.put(12L, "Dec");
        DateTimeFormatterBuilder dateTimeFormatterBuilder15 = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder15.parseCaseInsensitive();
        dateTimeFormatterBuilder15.parseLenient();
        dateTimeFormatterBuilder15.optionalStart();
        dateTimeFormatterBuilder15.appendText(ChronoField.DAY_OF_WEEK, hashMap);
        dateTimeFormatterBuilder15.appendLiteral(", ");
        dateTimeFormatterBuilder15.optionalEnd();
        dateTimeFormatterBuilder15.appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE);
        dateTimeFormatterBuilder15.appendLiteral(' ');
        dateTimeFormatterBuilder15.appendText(ChronoField.MONTH_OF_YEAR, hashMap2);
        dateTimeFormatterBuilder15.appendLiteral(' ');
        dateTimeFormatterBuilder15.appendValue(ChronoField.YEAR, 4);
        dateTimeFormatterBuilder15.appendLiteral(' ');
        dateTimeFormatterBuilder15.appendValue(ChronoField.HOUR_OF_DAY, 2);
        dateTimeFormatterBuilder15.appendLiteral(':');
        dateTimeFormatterBuilder15.appendValue(ChronoField.MINUTE_OF_HOUR, 2);
        dateTimeFormatterBuilder15.optionalStart();
        dateTimeFormatterBuilder15.appendLiteral(':');
        dateTimeFormatterBuilder15.appendValue(ChronoField.SECOND_OF_MINUTE, 2);
        dateTimeFormatterBuilder15.optionalEnd();
        dateTimeFormatterBuilder15.appendLiteral(' ');
        dateTimeFormatterBuilder15.appendOffset("+HHMM", "GMT");
        RFC_1123_DATE_TIME = dateTimeFormatterBuilder15.toFormatter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    static /* synthetic */ Period lambda$static$0(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Parsed) {
            return ((Parsed) temporalAccessor).excessDays;
        }
        return Period.ZERO;
    }

    static /* synthetic */ Boolean lambda$static$1(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Parsed) {
            return Boolean.valueOf(((Parsed) temporalAccessor).leapSecond);
        }
        return Boolean.FALSE;
    }

    DateTimeFormatter(DateTimeFormatterBuilder.CompositePrinterParser compositePrinterParser, Locale locale2, DecimalStyle decimalStyle2, ResolverStyle resolverStyle2, Set set, Chronology chronology, ZoneId zoneId) {
        Objects.requireNonNull(compositePrinterParser, "printerParser");
        this.printerParser = compositePrinterParser;
        this.resolverFields = set;
        Objects.requireNonNull(locale2, "locale");
        this.locale = locale2;
        Objects.requireNonNull(decimalStyle2, "decimalStyle");
        this.decimalStyle = decimalStyle2;
        Objects.requireNonNull(resolverStyle2, "resolverStyle");
        this.resolverStyle = resolverStyle2;
        this.chrono = chronology;
        this.zone = zoneId;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public DecimalStyle getDecimalStyle() {
        return this.decimalStyle;
    }

    public Chronology getChronology() {
        return this.chrono;
    }

    public ZoneId getZone() {
        return this.zone;
    }

    public String format(TemporalAccessor temporalAccessor) {
        StringBuilder sb = new StringBuilder(32);
        formatTo(temporalAccessor, sb);
        return sb.toString();
    }

    public void formatTo(TemporalAccessor temporalAccessor, Appendable appendable) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        Objects.requireNonNull(appendable, "appendable");
        try {
            DateTimePrintContext dateTimePrintContext = new DateTimePrintContext(temporalAccessor, this);
            if (appendable instanceof StringBuilder) {
                this.printerParser.format(dateTimePrintContext, (StringBuilder) appendable);
                return;
            }
            StringBuilder sb = new StringBuilder(32);
            this.printerParser.format(dateTimePrintContext, sb);
            appendable.append(sb);
        } catch (IOException e) {
            throw new DateTimeException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public DateTimeFormatterBuilder.CompositePrinterParser toPrinterParser(boolean z) {
        return this.printerParser.withOptional(z);
    }

    public String toString() {
        String compositePrinterParser = this.printerParser.toString();
        return compositePrinterParser.startsWith("[") ? compositePrinterParser : compositePrinterParser.substring(1, compositePrinterParser.length() - 1);
    }
}
