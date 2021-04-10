package java.time.format;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.Period;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class DateTimeFormatter {
    public static final DateTimeFormatter BASIC_ISO_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(ChronoField.YEAR, 4).appendValue(ChronoField.MONTH_OF_YEAR, 2).appendValue(ChronoField.DAY_OF_MONTH, 2).optionalStart().appendOffset("+HHMMss", "Z").toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_DATE_TIME = new DateTimeFormatterBuilder().append(ISO_LOCAL_DATE_TIME).optionalStart().appendOffsetId().optionalStart().appendLiteral('[').parseCaseSensitive().appendZoneRegionId().appendLiteral(']').toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_INSTANT = new DateTimeFormatterBuilder().parseCaseInsensitive().appendInstant().toFormatter(ResolverStyle.STRICT, null);
    public static final DateTimeFormatter ISO_LOCAL_DATE = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE).appendLiteral('T').append(ISO_LOCAL_TIME).toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_LOCAL_TIME = new DateTimeFormatterBuilder().appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).optionalStart().appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter(ResolverStyle.STRICT, null);
    public static final DateTimeFormatter ISO_OFFSET_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE).appendOffsetId().toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE_TIME).appendOffsetId().toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_OFFSET_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_TIME).appendOffsetId().toFormatter(ResolverStyle.STRICT, null);
    public static final DateTimeFormatter ISO_ORDINAL_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.DAY_OF_YEAR, 3).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_TIME).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT, null);
    public static final DateTimeFormatter ISO_WEEK_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(IsoFields.WEEK_BASED_YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral("-W").appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_WEEK, 1).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_ZONED_DATE_TIME = new DateTimeFormatterBuilder().append(ISO_OFFSET_DATE_TIME).optionalStart().appendLiteral('[').parseCaseSensitive().appendZoneRegionId().appendLiteral(']').toFormatter(ResolverStyle.STRICT, IsoChronology.INSTANCE);
    private static final TemporalQuery<Period> PARSED_EXCESS_DAYS = $$Lambda$DateTimeFormatter$QqeEAMXK7Qf5gsmaSCLmrVwQ1Ns.INSTANCE;
    private static final TemporalQuery<Boolean> PARSED_LEAP_SECOND = $$Lambda$DateTimeFormatter$GhpE1dbCMFpBqvhZZgrqVYpzk8E.INSTANCE;
    public static final DateTimeFormatter RFC_1123_DATE_TIME;
    private final Chronology chrono;
    private final DecimalStyle decimalStyle;
    private final Locale locale;
    private final DateTimeFormatterBuilder.CompositePrinterParser printerParser;
    private final Set<TemporalField> resolverFields;
    private final ResolverStyle resolverStyle;
    private final ZoneId zone;

    public static DateTimeFormatter ofPattern(String pattern) {
        return new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter();
    }

    public static DateTimeFormatter ofPattern(String pattern, Locale locale2) {
        return new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter(locale2);
    }

    public static DateTimeFormatter ofLocalizedDate(FormatStyle dateStyle) {
        Objects.requireNonNull(dateStyle, "dateStyle");
        return new DateTimeFormatterBuilder().appendLocalized(dateStyle, null).toFormatter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofLocalizedTime(FormatStyle timeStyle) {
        Objects.requireNonNull(timeStyle, "timeStyle");
        return new DateTimeFormatterBuilder().appendLocalized(null, timeStyle).toFormatter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofLocalizedDateTime(FormatStyle dateTimeStyle) {
        Objects.requireNonNull(dateTimeStyle, "dateTimeStyle");
        return new DateTimeFormatterBuilder().appendLocalized(dateTimeStyle, dateTimeStyle).toFormatter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofLocalizedDateTime(FormatStyle dateStyle, FormatStyle timeStyle) {
        Objects.requireNonNull(dateStyle, "dateStyle");
        Objects.requireNonNull(timeStyle, "timeStyle");
        return new DateTimeFormatterBuilder().appendLocalized(dateStyle, timeStyle).toFormatter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    static {
        Map<Long, String> dow = new HashMap<>();
        dow.put(1L, "Mon");
        dow.put(2L, "Tue");
        dow.put(3L, "Wed");
        dow.put(4L, "Thu");
        dow.put(5L, "Fri");
        dow.put(6L, "Sat");
        dow.put(7L, "Sun");
        Map<Long, String> moy = new HashMap<>();
        moy.put(1L, "Jan");
        moy.put(2L, "Feb");
        moy.put(3L, "Mar");
        moy.put(4L, "Apr");
        moy.put(5L, "May");
        moy.put(6L, "Jun");
        moy.put(7L, "Jul");
        moy.put(8L, "Aug");
        moy.put(9L, "Sep");
        moy.put(10L, "Oct");
        moy.put(11L, "Nov");
        moy.put(12L, "Dec");
        RFC_1123_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().parseLenient().optionalStart().appendText(ChronoField.DAY_OF_WEEK, dow).appendLiteral(", ").optionalEnd().appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE).appendLiteral(' ').appendText(ChronoField.MONTH_OF_YEAR, moy).appendLiteral(' ').appendValue(ChronoField.YEAR, 4).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).optionalEnd().appendLiteral(' ').appendOffset("+HHMM", "GMT").toFormatter(ResolverStyle.SMART, IsoChronology.INSTANCE);
    }

    public static final TemporalQuery<Period> parsedExcessDays() {
        return PARSED_EXCESS_DAYS;
    }

    static /* synthetic */ Period lambda$static$0(TemporalAccessor t) {
        if (t instanceof Parsed) {
            return ((Parsed) t).excessDays;
        }
        return Period.ZERO;
    }

    public static final TemporalQuery<Boolean> parsedLeapSecond() {
        return PARSED_LEAP_SECOND;
    }

    static /* synthetic */ Boolean lambda$static$1(TemporalAccessor t) {
        if (t instanceof Parsed) {
            return Boolean.valueOf(((Parsed) t).leapSecond);
        }
        return Boolean.FALSE;
    }

    DateTimeFormatter(DateTimeFormatterBuilder.CompositePrinterParser printerParser2, Locale locale2, DecimalStyle decimalStyle2, ResolverStyle resolverStyle2, Set<TemporalField> resolverFields2, Chronology chrono2, ZoneId zone2) {
        this.printerParser = (DateTimeFormatterBuilder.CompositePrinterParser) Objects.requireNonNull(printerParser2, "printerParser");
        this.resolverFields = resolverFields2;
        this.locale = (Locale) Objects.requireNonNull(locale2, "locale");
        this.decimalStyle = (DecimalStyle) Objects.requireNonNull(decimalStyle2, "decimalStyle");
        this.resolverStyle = (ResolverStyle) Objects.requireNonNull(resolverStyle2, "resolverStyle");
        this.chrono = chrono2;
        this.zone = zone2;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public DateTimeFormatter withLocale(Locale locale2) {
        if (this.locale.equals(locale2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, locale2, this.decimalStyle, this.resolverStyle, this.resolverFields, this.chrono, this.zone);
    }

    public DecimalStyle getDecimalStyle() {
        return this.decimalStyle;
    }

    public DateTimeFormatter withDecimalStyle(DecimalStyle decimalStyle2) {
        if (this.decimalStyle.equals(decimalStyle2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, decimalStyle2, this.resolverStyle, this.resolverFields, this.chrono, this.zone);
    }

    public Chronology getChronology() {
        return this.chrono;
    }

    public DateTimeFormatter withChronology(Chronology chrono2) {
        if (Objects.equals(this.chrono, chrono2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, this.resolverFields, chrono2, this.zone);
    }

    public ZoneId getZone() {
        return this.zone;
    }

    public DateTimeFormatter withZone(ZoneId zone2) {
        if (Objects.equals(this.zone, zone2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, this.resolverFields, this.chrono, zone2);
    }

    public ResolverStyle getResolverStyle() {
        return this.resolverStyle;
    }

    public DateTimeFormatter withResolverStyle(ResolverStyle resolverStyle2) {
        Objects.requireNonNull(resolverStyle2, "resolverStyle");
        if (Objects.equals(this.resolverStyle, resolverStyle2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, resolverStyle2, this.resolverFields, this.chrono, this.zone);
    }

    public Set<TemporalField> getResolverFields() {
        return this.resolverFields;
    }

    public DateTimeFormatter withResolverFields(TemporalField... resolverFields2) {
        Set<TemporalField> fields = null;
        if (resolverFields2 != null) {
            fields = Collections.unmodifiableSet(new HashSet(Arrays.asList(resolverFields2)));
        }
        if (Objects.equals(this.resolverFields, fields)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, fields, this.chrono, this.zone);
    }

    public DateTimeFormatter withResolverFields(Set<TemporalField> resolverFields2) {
        if (Objects.equals(this.resolverFields, resolverFields2)) {
            return this;
        }
        if (resolverFields2 != null) {
            resolverFields2 = Collections.unmodifiableSet(new HashSet(resolverFields2));
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, resolverFields2, this.chrono, this.zone);
    }

    public String format(TemporalAccessor temporal) {
        StringBuilder buf = new StringBuilder(32);
        formatTo(temporal, buf);
        return buf.toString();
    }

    public void formatTo(TemporalAccessor temporal, Appendable appendable) {
        Objects.requireNonNull(temporal, "temporal");
        Objects.requireNonNull(appendable, "appendable");
        try {
            DateTimePrintContext context = new DateTimePrintContext(temporal, this);
            if (appendable instanceof StringBuilder) {
                this.printerParser.format(context, (StringBuilder) appendable);
                return;
            }
            StringBuilder buf = new StringBuilder(32);
            this.printerParser.format(context, buf);
            appendable.append(buf);
        } catch (IOException ex) {
            throw new DateTimeException(ex.getMessage(), ex);
        }
    }

    public TemporalAccessor parse(CharSequence text) {
        Objects.requireNonNull(text, "text");
        try {
            return parseResolved0(text, null);
        } catch (DateTimeParseException ex) {
            throw ex;
        } catch (RuntimeException ex2) {
            throw createError(text, ex2);
        }
    }

    public TemporalAccessor parse(CharSequence text, ParsePosition position) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(position, "position");
        try {
            return parseResolved0(text, position);
        } catch (IndexOutOfBoundsException | DateTimeParseException ex) {
            throw ex;
        } catch (RuntimeException ex2) {
            throw createError(text, ex2);
        }
    }

    public <T> T parse(CharSequence text, TemporalQuery<T> query) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(query, "query");
        try {
            return (T) parseResolved0(text, null).query(query);
        } catch (DateTimeParseException ex) {
            throw ex;
        } catch (RuntimeException ex2) {
            throw createError(text, ex2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[ExcHandler: DateTimeParseException (r0v5 'ex' java.time.format.DateTimeParseException A[CUSTOM_DECLARE]), Splitter:B:3:0x000f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.temporal.TemporalAccessor parseBest(java.lang.CharSequence r6, java.time.temporal.TemporalQuery<?>... r7) {
        /*
            r5 = this;
            java.lang.String r0 = "text"
            java.util.Objects.requireNonNull(r6, r0)
            java.lang.String r0 = "queries"
            java.util.Objects.requireNonNull(r7, r0)
            int r0 = r7.length
            r1 = 2
            if (r0 < r1) goto L_0x0034
            r0 = 0
            java.time.temporal.TemporalAccessor r0 = r5.parseResolved0(r6, r0)     // Catch:{ DateTimeParseException -> 0x0032, RuntimeException -> 0x002c }
            int r1 = r7.length     // Catch:{ DateTimeParseException -> 0x0032, RuntimeException -> 0x002c }
            r2 = 0
        L_0x0015:
            if (r2 >= r1) goto L_0x0024
            r3 = r7[r2]     // Catch:{ DateTimeParseException -> 0x0032, RuntimeException -> 0x002c }
            java.lang.Object r4 = r0.query(r3)     // Catch:{ RuntimeException -> 0x0020, DateTimeParseException -> 0x0032 }
            java.time.temporal.TemporalAccessor r4 = (java.time.temporal.TemporalAccessor) r4     // Catch:{ RuntimeException -> 0x0020, DateTimeParseException -> 0x0032 }
            return r4
        L_0x0020:
            r4 = move-exception
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0024:
            java.time.DateTimeException r1 = new java.time.DateTimeException
            java.lang.String r2 = "Unable to convert parsed text using any of the specified queries"
            r1.<init>(r2)
            throw r1
        L_0x002c:
            r0 = move-exception
            java.time.format.DateTimeParseException r1 = r5.createError(r6, r0)
            throw r1
        L_0x0032:
            r0 = move-exception
            throw r0
        L_0x0034:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "At least two queries must be specified"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.format.DateTimeFormatter.parseBest(java.lang.CharSequence, java.time.temporal.TemporalQuery[]):java.time.temporal.TemporalAccessor");
    }

    private DateTimeParseException createError(CharSequence text, RuntimeException ex) {
        String abbr;
        if (text.length() > 64) {
            abbr = text.subSequence(0, 64).toString() + "...";
        } else {
            abbr = text.toString();
        }
        return new DateTimeParseException("Text '" + abbr + "' could not be parsed: " + ex.getMessage(), text, 0, ex);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private TemporalAccessor parseResolved0(CharSequence text, ParsePosition position) {
        String abbr;
        ParsePosition pos = position != null ? position : new ParsePosition(0);
        DateTimeParseContext context = parseUnresolved0(text, pos);
        if (context != null && pos.getErrorIndex() < 0 && (position != null || pos.getIndex() >= text.length())) {
            return context.toResolved(this.resolverStyle, this.resolverFields);
        }
        if (text.length() > 64) {
            abbr = text.subSequence(0, 64).toString() + "...";
        } else {
            abbr = text.toString();
        }
        if (pos.getErrorIndex() >= 0) {
            throw new DateTimeParseException("Text '" + abbr + "' could not be parsed at index " + pos.getErrorIndex(), text, pos.getErrorIndex());
        }
        throw new DateTimeParseException("Text '" + abbr + "' could not be parsed, unparsed text found at index " + pos.getIndex(), text, pos.getIndex());
    }

    public TemporalAccessor parseUnresolved(CharSequence text, ParsePosition position) {
        DateTimeParseContext context = parseUnresolved0(text, position);
        if (context == null) {
            return null;
        }
        return context.toUnresolved();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private DateTimeParseContext parseUnresolved0(CharSequence text, ParsePosition position) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(position, "position");
        DateTimeParseContext context = new DateTimeParseContext(this);
        int pos = this.printerParser.parse(context, text, position.getIndex());
        if (pos < 0) {
            position.setErrorIndex(~pos);
            return null;
        }
        position.setIndex(pos);
        return context;
    }

    /* access modifiers changed from: package-private */
    public DateTimeFormatterBuilder.CompositePrinterParser toPrinterParser(boolean optional) {
        return this.printerParser.withOptional(optional);
    }

    public Format toFormat() {
        return new ClassicFormat(this, null);
    }

    public Format toFormat(TemporalQuery<?> parseQuery) {
        Objects.requireNonNull(parseQuery, "parseQuery");
        return new ClassicFormat(this, parseQuery);
    }

    public String toString() {
        String pattern = this.printerParser.toString();
        return pattern.startsWith("[") ? pattern : pattern.substring(1, pattern.length() - 1);
    }

    static class ClassicFormat extends Format {
        private final DateTimeFormatter formatter;
        private final TemporalQuery<?> parseType;

        public ClassicFormat(DateTimeFormatter formatter2, TemporalQuery<?> parseType2) {
            this.formatter = formatter2;
            this.parseType = parseType2;
        }

        @Override // java.text.Format
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            Objects.requireNonNull(obj, "obj");
            Objects.requireNonNull(toAppendTo, "toAppendTo");
            Objects.requireNonNull(pos, "pos");
            if (obj instanceof TemporalAccessor) {
                pos.setBeginIndex(0);
                pos.setEndIndex(0);
                try {
                    this.formatter.formatTo((TemporalAccessor) obj, toAppendTo);
                    return toAppendTo;
                } catch (RuntimeException ex) {
                    throw new IllegalArgumentException(ex.getMessage(), ex);
                }
            } else {
                throw new IllegalArgumentException("Format target must implement TemporalAccessor");
            }
        }

        @Override // java.text.Format
        public Object parseObject(String text) throws ParseException {
            Objects.requireNonNull(text, "text");
            try {
                if (this.parseType == null) {
                    return this.formatter.parseResolved0(text, null);
                }
                return this.formatter.parse(text, this.parseType);
            } catch (DateTimeParseException ex) {
                throw new ParseException(ex.getMessage(), ex.getErrorIndex());
            } catch (RuntimeException ex2) {
                throw ((ParseException) new ParseException(ex2.getMessage(), 0).initCause(ex2));
            }
        }

        @Override // java.text.Format
        public Object parseObject(String text, ParsePosition pos) {
            Objects.requireNonNull(text, "text");
            try {
                DateTimeParseContext context = this.formatter.parseUnresolved0(text, pos);
                if (context == null) {
                    if (pos.getErrorIndex() < 0) {
                        pos.setErrorIndex(0);
                    }
                    return null;
                }
                try {
                    TemporalAccessor resolved = context.toResolved(this.formatter.resolverStyle, this.formatter.resolverFields);
                    if (this.parseType == null) {
                        return resolved;
                    }
                    return resolved.query(this.parseType);
                } catch (RuntimeException e) {
                    pos.setErrorIndex(0);
                    return null;
                }
            } catch (IndexOutOfBoundsException e2) {
                if (pos.getErrorIndex() < 0) {
                    pos.setErrorIndex(0);
                }
                return null;
            }
        }
    }
}
