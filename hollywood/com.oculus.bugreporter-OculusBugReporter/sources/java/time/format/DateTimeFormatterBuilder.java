package java.time.format;

import android.icu.impl.PatternTokenizer;
import android.icu.text.LocaleDisplayNames;
import android.icu.text.TimeZoneNames;
import android.icu.util.Calendar;
import android.icu.util.ULocale;
import dalvik.system.VMRuntime;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Types;
import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeTextProvider;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.time.zone.ZoneRulesProvider;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

public final class DateTimeFormatterBuilder {
    private static final Map<Character, TemporalField> FIELD_MAP = new HashMap();
    static final Comparator<String> LENGTH_SORT = new Comparator<String>() {
        /* class java.time.format.DateTimeFormatterBuilder.AnonymousClass2 */

        public int compare(String str1, String str2) {
            return str1.length() == str2.length() ? str1.compareTo(str2) : str1.length() - str2.length();
        }
    };
    private static final TemporalQuery<ZoneId> QUERY_REGION_ONLY = $$Lambda$DateTimeFormatterBuilder$MGACNxm6552EiylPRPw4dyNXKo.INSTANCE;
    private DateTimeFormatterBuilder active;
    private final boolean optional;
    private char padNextChar;
    private int padNextWidth;
    private final DateTimeFormatterBuilder parent;
    private final List<DateTimePrinterParser> printerParsers;
    private int valueParserIndex;

    /* access modifiers changed from: package-private */
    public interface DateTimePrinterParser {
        boolean format(DateTimePrintContext dateTimePrintContext, StringBuilder sb);

        int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i);
    }

    static {
        FIELD_MAP.put('G', ChronoField.ERA);
        FIELD_MAP.put('y', ChronoField.YEAR_OF_ERA);
        FIELD_MAP.put('u', ChronoField.YEAR);
        FIELD_MAP.put('Q', IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put('q', IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put('M', ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put('L', ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put('D', ChronoField.DAY_OF_YEAR);
        FIELD_MAP.put('d', ChronoField.DAY_OF_MONTH);
        FIELD_MAP.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        FIELD_MAP.put('E', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('c', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('e', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('a', ChronoField.AMPM_OF_DAY);
        FIELD_MAP.put('H', ChronoField.HOUR_OF_DAY);
        FIELD_MAP.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        FIELD_MAP.put('K', ChronoField.HOUR_OF_AMPM);
        FIELD_MAP.put('h', ChronoField.CLOCK_HOUR_OF_AMPM);
        FIELD_MAP.put('m', ChronoField.MINUTE_OF_HOUR);
        FIELD_MAP.put('s', ChronoField.SECOND_OF_MINUTE);
        FIELD_MAP.put('S', ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put('A', ChronoField.MILLI_OF_DAY);
        FIELD_MAP.put('n', ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put('N', ChronoField.NANO_OF_DAY);
    }

    static /* synthetic */ ZoneId lambda$static$0(TemporalAccessor temporal) {
        ZoneId zone = (ZoneId) temporal.query(TemporalQueries.zoneId());
        if (zone == null || (zone instanceof ZoneOffset)) {
            return null;
        }
        return zone;
    }

    public static String getLocalizedDateTimePattern(FormatStyle dateStyle, FormatStyle timeStyle, Chronology chrono, Locale locale) {
        Objects.requireNonNull(locale, "locale");
        Objects.requireNonNull(chrono, "chrono");
        if (dateStyle != null || timeStyle != null) {
            return Calendar.getDateTimeFormatString(ULocale.forLocale(locale), chrono.getCalendarType(), convertStyle(dateStyle), convertStyle(timeStyle));
        }
        throw new IllegalArgumentException("Either dateStyle or timeStyle must be non-null");
    }

    private static int convertStyle(FormatStyle style) {
        if (style == null) {
            return -1;
        }
        return style.ordinal();
    }

    public DateTimeFormatterBuilder() {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = null;
        this.optional = false;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder parent2, boolean optional2) {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = parent2;
        this.optional = optional2;
    }

    public DateTimeFormatterBuilder parseCaseSensitive() {
        appendInternal(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseCaseInsensitive() {
        appendInternal(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseStrict() {
        appendInternal(SettingsParser.STRICT);
        return this;
    }

    public DateTimeFormatterBuilder parseLenient() {
        appendInternal(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatterBuilder parseDefaulting(TemporalField field, long value) {
        Objects.requireNonNull(field, "field");
        appendInternal(new DefaultValueParser(field, value));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField field) {
        Objects.requireNonNull(field, "field");
        appendValue(new NumberPrinterParser(field, 1, 19, SignStyle.NORMAL));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField field, int width) {
        Objects.requireNonNull(field, "field");
        if (width < 1 || width > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + width);
        }
        appendValue(new NumberPrinterParser(field, width, width, SignStyle.NOT_NEGATIVE));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField field, int minWidth, int maxWidth, SignStyle signStyle) {
        if (minWidth == maxWidth && signStyle == SignStyle.NOT_NEGATIVE) {
            return appendValue(field, maxWidth);
        }
        Objects.requireNonNull(field, "field");
        Objects.requireNonNull(signStyle, "signStyle");
        if (minWidth < 1 || minWidth > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + minWidth);
        } else if (maxWidth < 1 || maxWidth > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + maxWidth);
        } else if (maxWidth >= minWidth) {
            appendValue(new NumberPrinterParser(field, minWidth, maxWidth, signStyle));
            return this;
        } else {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + maxWidth + " < " + minWidth);
        }
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField field, int width, int maxWidth, int baseValue) {
        Objects.requireNonNull(field, "field");
        appendValue(new ReducedPrinterParser(field, width, maxWidth, baseValue, null));
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField field, int width, int maxWidth, ChronoLocalDate baseDate) {
        Objects.requireNonNull(field, "field");
        Objects.requireNonNull(baseDate, "baseDate");
        appendValue(new ReducedPrinterParser(field, width, maxWidth, 0, baseDate));
        return this;
    }

    private DateTimeFormatterBuilder appendValue(NumberPrinterParser pp) {
        NumberPrinterParser basePP;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.valueParserIndex >= 0) {
            int activeValueParser = dateTimeFormatterBuilder.valueParserIndex;
            NumberPrinterParser basePP2 = (NumberPrinterParser) dateTimeFormatterBuilder.printerParsers.get(activeValueParser);
            if (pp.minWidth == pp.maxWidth && pp.signStyle == SignStyle.NOT_NEGATIVE) {
                basePP = basePP2.withSubsequentWidth(pp.maxWidth);
                appendInternal(pp.withFixedWidth());
                this.active.valueParserIndex = activeValueParser;
            } else {
                basePP = basePP2.withFixedWidth();
                this.active.valueParserIndex = appendInternal(pp);
            }
            this.active.printerParsers.set(activeValueParser, basePP);
        } else {
            dateTimeFormatterBuilder.valueParserIndex = appendInternal(pp);
        }
        return this;
    }

    public DateTimeFormatterBuilder appendFraction(TemporalField field, int minWidth, int maxWidth, boolean decimalPoint) {
        appendInternal(new FractionPrinterParser(field, minWidth, maxWidth, decimalPoint));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField field) {
        return appendText(field, TextStyle.FULL);
    }

    public DateTimeFormatterBuilder appendText(TemporalField field, TextStyle textStyle) {
        Objects.requireNonNull(field, "field");
        Objects.requireNonNull(textStyle, "textStyle");
        appendInternal(new TextPrinterParser(field, textStyle, DateTimeTextProvider.getInstance()));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField field, Map<Long, String> textLookup) {
        Objects.requireNonNull(field, "field");
        Objects.requireNonNull(textLookup, "textLookup");
        final DateTimeTextProvider.LocaleStore store = new DateTimeTextProvider.LocaleStore(Collections.singletonMap(TextStyle.FULL, new LinkedHashMap<>(textLookup)));
        appendInternal(new TextPrinterParser(field, TextStyle.FULL, new DateTimeTextProvider() {
            /* class java.time.format.DateTimeFormatterBuilder.AnonymousClass1 */

            @Override // java.time.format.DateTimeTextProvider
            public String getText(TemporalField field, long value, TextStyle style, Locale locale) {
                return store.getText(value, style);
            }

            @Override // java.time.format.DateTimeTextProvider
            public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField field, TextStyle style, Locale locale) {
                return store.getTextIterator(style);
            }
        }));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant() {
        appendInternal(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant(int fractionalDigits) {
        if (fractionalDigits < -1 || fractionalDigits > 9) {
            throw new IllegalArgumentException("The fractional digits must be from -1 to 9 inclusive but was " + fractionalDigits);
        }
        appendInternal(new InstantPrinterParser(fractionalDigits));
        return this;
    }

    public DateTimeFormatterBuilder appendOffsetId() {
        appendInternal(OffsetIdPrinterParser.INSTANCE_ID_Z);
        return this;
    }

    public DateTimeFormatterBuilder appendOffset(String pattern, String noOffsetText) {
        appendInternal(new OffsetIdPrinterParser(pattern, noOffsetText));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalizedOffset(TextStyle style) {
        Objects.requireNonNull(style, "style");
        if (style == TextStyle.FULL || style == TextStyle.SHORT) {
            appendInternal(new LocalizedOffsetIdPrinterParser(style));
            return this;
        }
        throw new IllegalArgumentException("Style must be either full or short");
    }

    public DateTimeFormatterBuilder appendZoneId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zoneId(), "ZoneId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneRegionId() {
        appendInternal(new ZoneIdPrinterParser(QUERY_REGION_ONLY, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneOrOffsetId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zone(), "ZoneOrOffsetId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        appendInternal(new ZoneTextPrinterParser(textStyle, null));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle, Set<ZoneId> preferredZones) {
        Objects.requireNonNull(preferredZones, "preferredZones");
        appendInternal(new ZoneTextPrinterParser(textStyle, preferredZones));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyId() {
        appendInternal(new ChronoPrinterParser(null));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyText(TextStyle textStyle) {
        Objects.requireNonNull(textStyle, "textStyle");
        appendInternal(new ChronoPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalized(FormatStyle dateStyle, FormatStyle timeStyle) {
        if (dateStyle == null && timeStyle == null) {
            throw new IllegalArgumentException("Either the date or time style must be non-null");
        }
        appendInternal(new LocalizedPrinterParser(dateStyle, timeStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char literal) {
        appendInternal(new CharLiteralPrinterParser(literal));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(String literal) {
        Objects.requireNonNull(literal, "literal");
        if (literal.length() > 0) {
            if (literal.length() == 1) {
                appendInternal(new CharLiteralPrinterParser(literal.charAt(0)));
            } else {
                appendInternal(new StringLiteralPrinterParser(literal));
            }
        }
        return this;
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        appendInternal(formatter.toPrinterParser(false));
        return this;
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        appendInternal(formatter.toPrinterParser(true));
        return this;
    }

    public DateTimeFormatterBuilder appendPattern(String pattern) {
        Objects.requireNonNull(pattern, "pattern");
        parsePattern(pattern);
        return this;
    }

    /* JADX INFO: Multiple debug info for r0v1 'pos'  int: [D('start' int), D('pos' int)] */
    private void parsePattern(String pattern) {
        int start = 0;
        while (start < pattern.length()) {
            char cur = pattern.charAt(start);
            if ((cur >= 'A' && cur <= 'Z') || (cur >= 'a' && cur <= 'z')) {
                int pos = start + 1;
                while (pos < pattern.length() && pattern.charAt(pos) == cur) {
                    pos++;
                }
                int count = pos - start;
                if (cur == 'p') {
                    int pad = 0;
                    if (pos < pattern.length() && (((cur = pattern.charAt(pos)) >= 'A' && cur <= 'Z') || (cur >= 'a' && cur <= 'z'))) {
                        pad = count;
                        int pos2 = pos + 1;
                        while (pos2 < pattern.length() && pattern.charAt(pos2) == cur) {
                            pos2++;
                        }
                        int i = pos2 - pos;
                        pos = pos2;
                        count = i;
                    }
                    if (pad != 0) {
                        padNext(pad);
                    } else {
                        throw new IllegalArgumentException("Pad letter 'p' must be followed by valid pad pattern: " + pattern);
                    }
                }
                TemporalField field = FIELD_MAP.get(Character.valueOf(cur));
                if (field != null) {
                    parseField(cur, count, field);
                } else if (cur == 'z') {
                    if (count > 4) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                    } else if (count == 4) {
                        appendZoneText(TextStyle.FULL);
                    } else {
                        appendZoneText(TextStyle.SHORT);
                    }
                } else if (cur != 'V') {
                    String zero = "+0000";
                    if (cur == 'Z') {
                        if (count < 4) {
                            appendOffset("+HHMM", zero);
                        } else if (count == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else if (count == 5) {
                            appendOffset("+HH:MM:ss", "Z");
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + cur);
                        }
                    } else if (cur != 'O') {
                        int i2 = 0;
                        if (cur == 'X') {
                            if (count <= 5) {
                                String[] strArr = OffsetIdPrinterParser.PATTERNS;
                                if (count != 1) {
                                    i2 = 1;
                                }
                                appendOffset(strArr[i2 + count], "Z");
                            } else {
                                throw new IllegalArgumentException("Too many pattern letters: " + cur);
                            }
                        } else if (cur == 'x') {
                            if (count <= 5) {
                                if (count == 1) {
                                    zero = "+00";
                                } else if (count % 2 != 0) {
                                    zero = "+00:00";
                                }
                                String[] strArr2 = OffsetIdPrinterParser.PATTERNS;
                                if (count != 1) {
                                    i2 = 1;
                                }
                                appendOffset(strArr2[i2 + count], zero);
                            } else {
                                throw new IllegalArgumentException("Too many pattern letters: " + cur);
                            }
                        } else if (cur == 'W') {
                            if (count <= 1) {
                                appendInternal(new WeekBasedFieldPrinterParser(cur, count));
                            } else {
                                throw new IllegalArgumentException("Too many pattern letters: " + cur);
                            }
                        } else if (cur == 'w') {
                            if (count <= 2) {
                                appendInternal(new WeekBasedFieldPrinterParser(cur, count));
                            } else {
                                throw new IllegalArgumentException("Too many pattern letters: " + cur);
                            }
                        } else if (cur == 'Y') {
                            appendInternal(new WeekBasedFieldPrinterParser(cur, count));
                        } else {
                            throw new IllegalArgumentException("Unknown pattern letter: " + cur);
                        }
                    } else if (count == 1) {
                        appendLocalizedOffset(TextStyle.SHORT);
                    } else if (count == 4) {
                        appendLocalizedOffset(TextStyle.FULL);
                    } else {
                        throw new IllegalArgumentException("Pattern letter count must be 1 or 4: " + cur);
                    }
                } else if (count == 2) {
                    appendZoneId();
                } else {
                    throw new IllegalArgumentException("Pattern letter count must be 2: " + cur);
                }
                start = pos - 1;
            } else if (cur == '\'') {
                int pos3 = start + 1;
                while (pos3 < pattern.length()) {
                    if (pattern.charAt(pos3) == '\'') {
                        if (pos3 + 1 >= pattern.length() || pattern.charAt(pos3 + 1) != '\'') {
                            break;
                        }
                        pos3++;
                    }
                    pos3++;
                }
                if (pos3 < pattern.length()) {
                    String str = pattern.substring(start + 1, pos3);
                    if (str.length() == 0) {
                        appendLiteral(PatternTokenizer.SINGLE_QUOTE);
                    } else {
                        appendLiteral(str.replace("''", "'"));
                    }
                    start = pos3;
                } else {
                    throw new IllegalArgumentException("Pattern ends with an incomplete string literal: " + pattern);
                }
            } else if (cur == '[') {
                optionalStart();
            } else if (cur == ']') {
                if (this.active.parent != null) {
                    optionalEnd();
                } else {
                    throw new IllegalArgumentException("Pattern invalid as it contains ] without previous [");
                }
            } else if (cur == '{' || cur == '}' || cur == '#') {
                throw new IllegalArgumentException("Pattern includes reserved character: '" + cur + "'");
            } else {
                appendLiteral(cur);
            }
            start++;
        }
    }

    private void parseField(char cur, int count, TemporalField field) {
        boolean standalone = false;
        if (cur != 'Q') {
            if (cur == 'S') {
                appendFraction(ChronoField.NANO_OF_SECOND, count, count, false);
                return;
            } else if (cur != 'a') {
                if (!(cur == 'h' || cur == 'k' || cur == 'm')) {
                    if (cur != 'q') {
                        if (cur != 's') {
                            if (cur != 'u' && cur != 'y') {
                                switch (cur) {
                                    case 'D':
                                        if (count == 1) {
                                            appendValue(field);
                                            return;
                                        } else if (count <= 3) {
                                            appendValue(field, count);
                                            return;
                                        } else {
                                            throw new IllegalArgumentException("Too many pattern letters: " + cur);
                                        }
                                    case 'E':
                                        break;
                                    case 'F':
                                        if (count == 1) {
                                            appendValue(field);
                                            return;
                                        }
                                        throw new IllegalArgumentException("Too many pattern letters: " + cur);
                                    case 'G':
                                        if (count == 1 || count == 2 || count == 3) {
                                            appendText(field, TextStyle.SHORT);
                                            return;
                                        } else if (count == 4) {
                                            appendText(field, TextStyle.FULL);
                                            return;
                                        } else if (count == 5) {
                                            appendText(field, TextStyle.NARROW);
                                            return;
                                        } else {
                                            throw new IllegalArgumentException("Too many pattern letters: " + cur);
                                        }
                                    case 'H':
                                        break;
                                    default:
                                        switch (cur) {
                                            case 'K':
                                                break;
                                            case 'L':
                                                break;
                                            case 'M':
                                                break;
                                            default:
                                                switch (cur) {
                                                    case 'c':
                                                        if (count == 2) {
                                                            throw new IllegalArgumentException("Invalid pattern \"cc\"");
                                                        }
                                                        break;
                                                    case 'd':
                                                        break;
                                                    case 'e':
                                                        break;
                                                    default:
                                                        if (count == 1) {
                                                            appendValue(field);
                                                            return;
                                                        } else {
                                                            appendValue(field, count);
                                                            return;
                                                        }
                                                }
                                        }
                                }
                            } else if (count == 2) {
                                appendValueReduced(field, 2, 2, ReducedPrinterParser.BASE_DATE);
                                return;
                            } else if (count < 4) {
                                appendValue(field, count, 19, SignStyle.NORMAL);
                                return;
                            } else {
                                appendValue(field, count, 19, SignStyle.EXCEEDS_PAD);
                                return;
                            }
                        }
                    }
                    standalone = true;
                }
                if (count == 1) {
                    appendValue(field);
                    return;
                } else if (count == 2) {
                    appendValue(field, count);
                    return;
                } else {
                    throw new IllegalArgumentException("Too many pattern letters: " + cur);
                }
            } else if (count == 1) {
                appendText(field, TextStyle.SHORT);
                return;
            } else {
                throw new IllegalArgumentException("Too many pattern letters: " + cur);
            }
        }
        if (count == 1 || count == 2) {
            if (cur == 'c' || cur == 'e') {
                appendInternal(new WeekBasedFieldPrinterParser(cur, count));
            } else if (cur == 'E') {
                appendText(field, TextStyle.SHORT);
            } else if (count == 1) {
                appendValue(field);
            } else {
                appendValue(field, 2);
            }
        } else if (count == 3) {
            appendText(field, standalone ? TextStyle.SHORT_STANDALONE : TextStyle.SHORT);
        } else if (count == 4) {
            appendText(field, standalone ? TextStyle.FULL_STANDALONE : TextStyle.FULL);
        } else if (count == 5) {
            appendText(field, standalone ? TextStyle.NARROW_STANDALONE : TextStyle.NARROW);
        } else {
            throw new IllegalArgumentException("Too many pattern letters: " + cur);
        }
    }

    public DateTimeFormatterBuilder padNext(int padWidth) {
        return padNext(padWidth, ' ');
    }

    public DateTimeFormatterBuilder padNext(int padWidth, char padChar) {
        if (padWidth >= 1) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
            dateTimeFormatterBuilder.padNextWidth = padWidth;
            dateTimeFormatterBuilder.padNextChar = padChar;
            dateTimeFormatterBuilder.valueParserIndex = -1;
            return this;
        }
        throw new IllegalArgumentException("The pad width must be at least one but was " + padWidth);
    }

    public DateTimeFormatterBuilder optionalStart() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(dateTimeFormatterBuilder, true);
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.parent != null) {
            if (dateTimeFormatterBuilder.printerParsers.size() > 0) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
                CompositePrinterParser cpp = new CompositePrinterParser(dateTimeFormatterBuilder2.printerParsers, dateTimeFormatterBuilder2.optional);
                this.active = this.active.parent;
                appendInternal(cpp);
            } else {
                this.active = this.active.parent;
            }
            return this;
        }
        throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
    }

    private int appendInternal(DateTimePrinterParser pp) {
        Objects.requireNonNull(pp, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.padNextWidth;
        if (i > 0) {
            if (pp != null) {
                pp = new PadPrinterParserDecorator(pp, i, dateTimeFormatterBuilder.padNextChar);
            }
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            dateTimeFormatterBuilder2.padNextWidth = 0;
            dateTimeFormatterBuilder2.padNextChar = 0;
        }
        this.active.printerParsers.add(pp);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = this.active;
        dateTimeFormatterBuilder3.valueParserIndex = -1;
        return dateTimeFormatterBuilder3.printerParsers.size() - 1;
    }

    public DateTimeFormatter toFormatter() {
        return toFormatter(Locale.getDefault(Locale.Category.FORMAT));
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        return toFormatter(locale, ResolverStyle.SMART, null);
    }

    /* access modifiers changed from: package-private */
    public DateTimeFormatter toFormatter(ResolverStyle resolverStyle, Chronology chrono) {
        return toFormatter(Locale.getDefault(Locale.Category.FORMAT), resolverStyle, chrono);
    }

    private DateTimeFormatter toFormatter(Locale locale, ResolverStyle resolverStyle, Chronology chrono) {
        Objects.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            optionalEnd();
        }
        return new DateTimeFormatter(new CompositePrinterParser(this.printerParsers, false), locale, DecimalStyle.STANDARD, resolverStyle, null, chrono, null);
    }

    /* access modifiers changed from: package-private */
    public static final class CompositePrinterParser implements DateTimePrinterParser {
        private final boolean optional;
        private final DateTimePrinterParser[] printerParsers;

        CompositePrinterParser(List<DateTimePrinterParser> printerParsers2, boolean optional2) {
            this((DateTimePrinterParser[]) printerParsers2.toArray(new DateTimePrinterParser[printerParsers2.size()]), optional2);
        }

        CompositePrinterParser(DateTimePrinterParser[] printerParsers2, boolean optional2) {
            this.printerParsers = printerParsers2;
            this.optional = optional2;
        }

        public CompositePrinterParser withOptional(boolean optional2) {
            if (optional2 == this.optional) {
                return this;
            }
            return new CompositePrinterParser(this.printerParsers, optional2);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            int length = buf.length();
            if (this.optional) {
                context.startOptional();
            }
            try {
                for (DateTimePrinterParser pp : this.printerParsers) {
                    if (!pp.format(context, buf)) {
                        buf.setLength(length);
                        return true;
                    }
                }
                if (this.optional) {
                    context.endOptional();
                }
                return true;
            } finally {
                if (this.optional) {
                    context.endOptional();
                }
            }
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            if (this.optional) {
                context.startOptional();
                int pos = position;
                for (DateTimePrinterParser pp : this.printerParsers) {
                    pos = pp.parse(context, text, pos);
                    if (pos < 0) {
                        context.endOptional(false);
                        return position;
                    }
                }
                context.endOptional(true);
                return pos;
            }
            for (DateTimePrinterParser pp2 : this.printerParsers) {
                position = pp2.parse(context, text, position);
                if (position < 0) {
                    break;
                }
            }
            return position;
        }

        public String toString() {
            StringBuilder buf = new StringBuilder();
            if (this.printerParsers != null) {
                buf.append(this.optional ? "[" : "(");
                for (DateTimePrinterParser pp : this.printerParsers) {
                    buf.append((Object) pp);
                }
                buf.append(this.optional ? "]" : ")");
            }
            return buf.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class PadPrinterParserDecorator implements DateTimePrinterParser {
        private final char padChar;
        private final int padWidth;
        private final DateTimePrinterParser printerParser;

        PadPrinterParserDecorator(DateTimePrinterParser printerParser2, int padWidth2, char padChar2) {
            this.printerParser = printerParser2;
            this.padWidth = padWidth2;
            this.padChar = padChar2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            int preLen = buf.length();
            if (!this.printerParser.format(context, buf)) {
                return false;
            }
            int len = buf.length() - preLen;
            if (len <= this.padWidth) {
                for (int i = 0; i < this.padWidth - len; i++) {
                    buf.insert(preLen, this.padChar);
                }
                return true;
            }
            throw new DateTimeException("Cannot print as output of " + len + " characters exceeds pad width of " + this.padWidth);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            boolean strict = context.isStrict();
            if (position > text.length()) {
                throw new IndexOutOfBoundsException();
            } else if (position == text.length()) {
                return ~position;
            } else {
                int endPos = this.padWidth + position;
                if (endPos > text.length()) {
                    if (strict) {
                        return ~position;
                    }
                    endPos = text.length();
                }
                int pos = position;
                while (pos < endPos && context.charEquals(text.charAt(pos), this.padChar)) {
                    pos++;
                }
                int resultPos = this.printerParser.parse(context, text.subSequence(0, endPos), pos);
                if (resultPos == endPos || !strict) {
                    return resultPos;
                }
                return ~(position + pos);
            }
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("Pad(");
            sb.append((Object) this.printerParser);
            sb.append(",");
            sb.append(this.padWidth);
            if (this.padChar == ' ') {
                str = ")";
            } else {
                str = ",'" + this.padChar + "')";
            }
            sb.append(str);
            return sb.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public enum SettingsParser implements DateTimePrinterParser {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            return true;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int ordinal = ordinal();
            if (ordinal == 0) {
                context.setCaseSensitive(true);
            } else if (ordinal == 1) {
                context.setCaseSensitive(false);
            } else if (ordinal == 2) {
                context.setStrict(true);
            } else if (ordinal == 3) {
                context.setStrict(false);
            }
            return position;
        }

        @Override // java.lang.Enum
        public String toString() {
            int ordinal = ordinal();
            if (ordinal == 0) {
                return "ParseCaseSensitive(true)";
            }
            if (ordinal == 1) {
                return "ParseCaseSensitive(false)";
            }
            if (ordinal == 2) {
                return "ParseStrict(true)";
            }
            if (ordinal == 3) {
                return "ParseStrict(false)";
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    static class DefaultValueParser implements DateTimePrinterParser {
        private final TemporalField field;
        private final long value;

        DefaultValueParser(TemporalField field2, long value2) {
            this.field = field2;
            this.value = value2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            return true;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            if (context.getParsed(this.field) == null) {
                context.setParsedField(this.field, this.value, position, position);
            }
            return position;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CharLiteralPrinterParser implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char literal2) {
            this.literal = literal2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            buf.append(this.literal);
            return true;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            if (position == text.length()) {
                return ~position;
            }
            char ch = text.charAt(position);
            if (ch == this.literal || (!context.isCaseSensitive() && (Character.toUpperCase(ch) == Character.toUpperCase(this.literal) || Character.toLowerCase(ch) == Character.toLowerCase(this.literal)))) {
                return position + 1;
            }
            return ~position;
        }

        public String toString() {
            if (this.literal == '\'') {
                return "''";
            }
            return "'" + this.literal + "'";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StringLiteralPrinterParser implements DateTimePrinterParser {
        private final String literal;

        StringLiteralPrinterParser(String literal2) {
            this.literal = literal2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            buf.append(this.literal);
            return true;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            if (position > text.length() || position < 0) {
                throw new IndexOutOfBoundsException();
            }
            String str = this.literal;
            if (!context.subSequenceEquals(text, position, str, 0, str.length())) {
                return ~position;
            }
            return this.literal.length() + position;
        }

        public String toString() {
            String converted = this.literal.replace("'", "''");
            return "'" + converted + "'";
        }
    }

    /* access modifiers changed from: package-private */
    public static class NumberPrinterParser implements DateTimePrinterParser {
        static final long[] EXCEED_POINTS = {0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L};
        final TemporalField field;
        final int maxWidth;
        final int minWidth;
        private final SignStyle signStyle;
        final int subsequentWidth;

        NumberPrinterParser(TemporalField field2, int minWidth2, int maxWidth2, SignStyle signStyle2) {
            this.field = field2;
            this.minWidth = minWidth2;
            this.maxWidth = maxWidth2;
            this.signStyle = signStyle2;
            this.subsequentWidth = 0;
        }

        protected NumberPrinterParser(TemporalField field2, int minWidth2, int maxWidth2, SignStyle signStyle2, int subsequentWidth2) {
            this.field = field2;
            this.minWidth = minWidth2;
            this.maxWidth = maxWidth2;
            this.signStyle = signStyle2;
            this.subsequentWidth = subsequentWidth2;
        }

        /* access modifiers changed from: package-private */
        public NumberPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        /* access modifiers changed from: package-private */
        public NumberPrinterParser withSubsequentWidth(int subsequentWidth2) {
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, this.subsequentWidth + subsequentWidth2);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            Long valueLong = context.getValue(this.field);
            if (valueLong == null) {
                return false;
            }
            long value = getValue(context, valueLong.longValue());
            DecimalStyle decimalStyle = context.getDecimalStyle();
            String str = value == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(value));
            if (str.length() <= this.maxWidth) {
                String str2 = decimalStyle.convertNumberToI18N(str);
                if (value >= 0) {
                    int i = AnonymousClass3.$SwitchMap$java$time$format$SignStyle[this.signStyle.ordinal()];
                    if (i == 1) {
                        int i2 = this.minWidth;
                        if (i2 < 19 && value >= EXCEED_POINTS[i2]) {
                            buf.append(decimalStyle.getPositiveSign());
                        }
                    } else if (i == 2) {
                        buf.append(decimalStyle.getPositiveSign());
                    }
                } else {
                    int i3 = AnonymousClass3.$SwitchMap$java$time$format$SignStyle[this.signStyle.ordinal()];
                    if (i3 == 1 || i3 == 2 || i3 == 3) {
                        buf.append(decimalStyle.getNegativeSign());
                    } else if (i3 == 4) {
                        throw new DateTimeException("Field " + ((Object) this.field) + " cannot be printed as the value " + value + " cannot be negative according to the SignStyle");
                    }
                }
                for (int i4 = 0; i4 < this.minWidth - str2.length(); i4++) {
                    buf.append(decimalStyle.getZeroDigit());
                }
                buf.append(str2);
                return true;
            }
            throw new DateTimeException("Field " + ((Object) this.field) + " cannot be printed as the value " + value + " exceeds the maximum print width of " + this.maxWidth);
        }

        /* access modifiers changed from: package-private */
        public long getValue(DateTimePrintContext context, long value) {
            return value;
        }

        /* access modifiers changed from: package-private */
        public boolean isFixedWidth(DateTimeParseContext context) {
            int i = this.subsequentWidth;
            return i == -1 || (i > 0 && this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            boolean positive;
            boolean negative;
            int position2;
            int pos;
            long total;
            BigInteger totalBig;
            long total2;
            int length;
            char sign;
            char sign2;
            int length2 = text.length();
            if (position == length2) {
                return ~position;
            }
            char sign3 = text.charAt(position);
            int effMinWidth = 1;
            if (sign3 == context.getDecimalStyle().getPositiveSign()) {
                if (!this.signStyle.parse(true, context.isStrict(), this.minWidth == this.maxWidth)) {
                    return ~position;
                }
                position2 = position + 1;
                negative = false;
                positive = true;
            } else if (sign3 == context.getDecimalStyle().getNegativeSign()) {
                if (!this.signStyle.parse(false, context.isStrict(), this.minWidth == this.maxWidth)) {
                    return ~position;
                }
                position2 = position + 1;
                negative = true;
                positive = false;
            } else if (this.signStyle == SignStyle.ALWAYS && context.isStrict()) {
                return ~position;
            } else {
                position2 = position;
                negative = false;
                positive = false;
            }
            if (context.isStrict() || isFixedWidth(context)) {
                effMinWidth = this.minWidth;
            }
            int minEndPos = position2 + effMinWidth;
            if (minEndPos > length2) {
                return ~position2;
            }
            long total3 = 0;
            BigInteger totalBig2 = null;
            int pos2 = position2;
            int pass = 0;
            int effMaxWidth = ((context.isStrict() || isFixedWidth(context)) ? this.maxWidth : 9) + Math.max(this.subsequentWidth, 0);
            while (true) {
                if (pass >= 2) {
                    pos = pos2;
                    break;
                }
                int digit = Math.min(pos2 + effMaxWidth, length2);
                while (true) {
                    if (pos2 >= digit) {
                        total2 = total3;
                        length = length2;
                        sign = sign3;
                        pos = pos2;
                        break;
                    }
                    int pos3 = pos2 + 1;
                    length = length2;
                    int digit2 = context.getDecimalStyle().convertToDigit(text.charAt(pos2));
                    if (digit2 < 0) {
                        pos = pos3 - 1;
                        if (pos < minEndPos) {
                            return ~position2;
                        }
                        total2 = total3;
                        sign = sign3;
                    } else {
                        if (pos3 - position2 > 18) {
                            if (totalBig2 == null) {
                                totalBig2 = BigInteger.valueOf(total3);
                            }
                            sign2 = sign3;
                            totalBig2 = totalBig2.multiply(BigInteger.TEN).add(BigInteger.valueOf((long) digit2));
                        } else {
                            sign2 = sign3;
                            total3 = ((long) digit2) + (10 * total3);
                        }
                        digit = digit;
                        pos2 = pos3;
                        length2 = length;
                        sign3 = sign2;
                    }
                }
                int i = this.subsequentWidth;
                if (i <= 0 || pass != 0) {
                    total3 = total2;
                } else {
                    effMaxWidth = Math.max(effMinWidth, (pos - position2) - i);
                    pos2 = position2;
                    totalBig2 = null;
                    pass++;
                    total3 = 0;
                    length2 = length;
                    sign3 = sign;
                }
            }
            if (!negative) {
                if (this.signStyle == SignStyle.EXCEEDS_PAD && context.isStrict()) {
                    int parseLen = pos - position2;
                    if (positive) {
                        if (parseLen <= this.minWidth) {
                            return ~(position2 - 1);
                        }
                    } else if (parseLen > this.minWidth) {
                        return ~position2;
                    }
                }
                total = total3;
                totalBig = totalBig2;
            } else if (totalBig2 != null) {
                if (totalBig2.equals(BigInteger.ZERO) && context.isStrict()) {
                    return ~(position2 - 1);
                }
                total = total3;
                totalBig = totalBig2.negate();
            } else if (total3 == 0 && context.isStrict()) {
                return ~(position2 - 1);
            } else {
                total = -total3;
                totalBig = totalBig2;
            }
            if (totalBig == null) {
                return setValue(context, total, position2, pos);
            }
            if (totalBig.bitLength() > 63) {
                totalBig = totalBig.divide(BigInteger.TEN);
                pos--;
            }
            return setValue(context, totalBig.longValue(), position2, pos);
        }

        /* access modifiers changed from: package-private */
        public int setValue(DateTimeParseContext context, long value, int errorPos, int successPos) {
            return context.setParsedField(this.field, value, errorPos, successPos);
        }

        public String toString() {
            if (this.minWidth == 1 && this.maxWidth == 19 && this.signStyle == SignStyle.NORMAL) {
                return "Value(" + ((Object) this.field) + ")";
            } else if (this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE) {
                return "Value(" + ((Object) this.field) + "," + this.minWidth + ")";
            } else {
                return "Value(" + ((Object) this.field) + "," + this.minWidth + "," + this.maxWidth + "," + ((Object) this.signStyle) + ")";
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.format.DateTimeFormatterBuilder$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$java$time$format$SignStyle = new int[SignStyle.values().length];

        static {
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.EXCEEDS_PAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$time$format$SignStyle[SignStyle.NOT_NEGATIVE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ReducedPrinterParser extends NumberPrinterParser {
        static final LocalDate BASE_DATE = LocalDate.of((int) Types.JAVA_OBJECT, 1, 1);
        private final ChronoLocalDate baseDate;
        private final int baseValue;

        ReducedPrinterParser(TemporalField field, int minWidth, int maxWidth, int baseValue2, ChronoLocalDate baseDate2) {
            this(field, minWidth, maxWidth, baseValue2, baseDate2, 0);
            if (minWidth < 1 || minWidth > 10) {
                throw new IllegalArgumentException("The minWidth must be from 1 to 10 inclusive but was " + minWidth);
            } else if (maxWidth < 1 || maxWidth > 10) {
                throw new IllegalArgumentException("The maxWidth must be from 1 to 10 inclusive but was " + minWidth);
            } else if (maxWidth < minWidth) {
                throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + maxWidth + " < " + minWidth);
            } else if (baseDate2 != null) {
            } else {
                if (!field.range().isValidValue((long) baseValue2)) {
                    throw new IllegalArgumentException("The base value must be within the range of the field");
                } else if (((long) baseValue2) + EXCEED_POINTS[maxWidth] > 2147483647L) {
                    throw new DateTimeException("Unable to add printer-parser as the range exceeds the capacity of an int");
                }
            }
        }

        private ReducedPrinterParser(TemporalField field, int minWidth, int maxWidth, int baseValue2, ChronoLocalDate baseDate2, int subsequentWidth) {
            super(field, minWidth, maxWidth, SignStyle.NOT_NEGATIVE, subsequentWidth);
            this.baseValue = baseValue2;
            this.baseDate = baseDate2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public long getValue(DateTimePrintContext context, long value) {
            long absValue = Math.abs(value);
            int baseValue2 = this.baseValue;
            if (this.baseDate != null) {
                baseValue2 = Chronology.from(context.getTemporal()).date(this.baseDate).get(this.field);
            }
            if (value < ((long) baseValue2) || value >= ((long) baseValue2) + EXCEED_POINTS[this.minWidth]) {
                return absValue % EXCEED_POINTS[this.maxWidth];
            }
            return absValue % EXCEED_POINTS[this.minWidth];
        }

        /* access modifiers changed from: package-private */
        @Override // java.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public int setValue(DateTimeParseContext context, long value, int errorPos, int successPos) {
            long range;
            long value2;
            int baseValue2 = this.baseValue;
            if (this.baseDate != null) {
                int baseValue3 = context.getEffectiveChronology().date(this.baseDate).get(this.field);
                context.addChronoChangedListener(new Consumer(context, value, errorPos, successPos) {
                    /* class java.time.format.$$Lambda$DateTimeFormatterBuilder$ReducedPrinterParser$O7fxxUm4cHduGbldToNj0T92oIo */
                    private final /* synthetic */ DateTimeParseContext f$1;
                    private final /* synthetic */ long f$2;
                    private final /* synthetic */ int f$3;
                    private final /* synthetic */ int f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r5;
                        this.f$4 = r6;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DateTimeFormatterBuilder.ReducedPrinterParser.this.lambda$setValue$0$DateTimeFormatterBuilder$ReducedPrinterParser(this.f$1, this.f$2, this.f$3, this.f$4, (Chronology) obj);
                    }
                });
                baseValue2 = baseValue3;
            }
            if (successPos - errorPos != this.minWidth || value < 0) {
                range = value;
            } else {
                long range2 = EXCEED_POINTS[this.minWidth];
                long basePart = ((long) baseValue2) - (((long) baseValue2) % range2);
                if (baseValue2 > 0) {
                    value2 = basePart + value;
                } else {
                    value2 = basePart - value;
                }
                if (value2 < ((long) baseValue2)) {
                    range = value2 + range2;
                } else {
                    range = value2;
                }
            }
            return context.setParsedField(this.field, range, errorPos, successPos);
        }

        public /* synthetic */ void lambda$setValue$0$DateTimeFormatterBuilder$ReducedPrinterParser(DateTimeParseContext context, long initialValue, int errorPos, int successPos, Chronology _unused) {
            setValue(context, initialValue, errorPos, successPos);
        }

        /* access modifiers changed from: package-private */
        @Override // java.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public ReducedPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, -1);
        }

        /* access modifiers changed from: package-private */
        @Override // java.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public ReducedPrinterParser withSubsequentWidth(int subsequentWidth) {
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, this.subsequentWidth + subsequentWidth);
        }

        /* access modifiers changed from: package-private */
        @Override // java.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public boolean isFixedWidth(DateTimeParseContext context) {
            if (!context.isStrict()) {
                return false;
            }
            return super.isFixedWidth(context);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.NumberPrinterParser
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReducedValue(");
            sb.append((Object) this.field);
            sb.append(",");
            sb.append(this.minWidth);
            sb.append(",");
            sb.append(this.maxWidth);
            sb.append(",");
            Object obj = this.baseDate;
            if (obj == null) {
                obj = Integer.valueOf(this.baseValue);
            }
            sb.append(obj);
            sb.append(")");
            return sb.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class FractionPrinterParser implements DateTimePrinterParser {
        private final boolean decimalPoint;
        private final TemporalField field;
        private final int maxWidth;
        private final int minWidth;

        FractionPrinterParser(TemporalField field2, int minWidth2, int maxWidth2, boolean decimalPoint2) {
            Objects.requireNonNull(field2, "field");
            if (!field2.range().isFixed()) {
                throw new IllegalArgumentException("Field must have a fixed set of values: " + ((Object) field2));
            } else if (minWidth2 < 0 || minWidth2 > 9) {
                throw new IllegalArgumentException("Minimum width must be from 0 to 9 inclusive but was " + minWidth2);
            } else if (maxWidth2 < 1 || maxWidth2 > 9) {
                throw new IllegalArgumentException("Maximum width must be from 1 to 9 inclusive but was " + maxWidth2);
            } else if (maxWidth2 >= minWidth2) {
                this.field = field2;
                this.minWidth = minWidth2;
                this.maxWidth = maxWidth2;
                this.decimalPoint = decimalPoint2;
            } else {
                throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + maxWidth2 + " < " + minWidth2);
            }
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            Long value = context.getValue(this.field);
            if (value == null) {
                return false;
            }
            DecimalStyle decimalStyle = context.getDecimalStyle();
            BigDecimal fraction = convertToFraction(value.longValue());
            if (fraction.scale() != 0) {
                String str = decimalStyle.convertNumberToI18N(fraction.setScale(Math.min(Math.max(fraction.scale(), this.minWidth), this.maxWidth), RoundingMode.FLOOR).toPlainString().substring(2));
                if (this.decimalPoint) {
                    buf.append(decimalStyle.getDecimalSeparator());
                }
                buf.append(str);
                return true;
            } else if (this.minWidth <= 0) {
                return true;
            } else {
                if (this.decimalPoint) {
                    buf.append(decimalStyle.getDecimalSeparator());
                }
                for (int i = 0; i < this.minWidth; i++) {
                    buf.append(decimalStyle.getZeroDigit());
                }
                return true;
            }
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int pos;
            int position2 = position;
            int effectiveMin = context.isStrict() ? this.minWidth : 0;
            int effectiveMax = context.isStrict() ? this.maxWidth : 9;
            int length = text.length();
            if (position2 == length) {
                return effectiveMin > 0 ? ~position2 : position2;
            }
            if (this.decimalPoint) {
                if (text.charAt(position) != context.getDecimalStyle().getDecimalSeparator()) {
                    return effectiveMin > 0 ? ~position2 : position2;
                }
                position2++;
            }
            int minEndPos = position2 + effectiveMin;
            if (minEndPos > length) {
                return ~position2;
            }
            int maxEndPos = Math.min(position2 + effectiveMax, length);
            int pos2 = position2;
            int total = 0;
            while (true) {
                if (pos2 >= maxEndPos) {
                    pos = pos2;
                    break;
                }
                int pos3 = pos2 + 1;
                int digit = context.getDecimalStyle().convertToDigit(text.charAt(pos2));
                if (digit >= 0) {
                    total = (total * 10) + digit;
                    pos2 = pos3;
                } else if (pos3 < minEndPos) {
                    return ~position2;
                } else {
                    pos = pos3 - 1;
                }
            }
            return context.setParsedField(this.field, convertFromFraction(new BigDecimal(total).movePointLeft(pos - position2)), position2, pos);
        }

        private BigDecimal convertToFraction(long value) {
            ValueRange range = this.field.range();
            range.checkValidValue(value, this.field);
            BigDecimal minBD = BigDecimal.valueOf(range.getMinimum());
            BigDecimal fraction = BigDecimal.valueOf(value).subtract(minBD).divide(BigDecimal.valueOf(range.getMaximum()).subtract(minBD).add(BigDecimal.ONE), 9, RoundingMode.FLOOR);
            return fraction.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : fraction.stripTrailingZeros();
        }

        private long convertFromFraction(BigDecimal fraction) {
            ValueRange range = this.field.range();
            BigDecimal minBD = BigDecimal.valueOf(range.getMinimum());
            return fraction.multiply(BigDecimal.valueOf(range.getMaximum()).subtract(minBD).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(minBD).longValueExact();
        }

        public String toString() {
            String decimal = this.decimalPoint ? ",DecimalPoint" : "";
            return "Fraction(" + ((Object) this.field) + "," + this.minWidth + "," + this.maxWidth + decimal + ")";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TextPrinterParser implements DateTimePrinterParser {
        private final TemporalField field;
        private volatile NumberPrinterParser numberPrinterParser;
        private final DateTimeTextProvider provider;
        private final TextStyle textStyle;

        TextPrinterParser(TemporalField field2, TextStyle textStyle2, DateTimeTextProvider provider2) {
            this.field = field2;
            this.textStyle = textStyle2;
            this.provider = provider2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            String text;
            Long value = context.getValue(this.field);
            if (value == null) {
                return false;
            }
            Chronology chrono = (Chronology) context.getTemporal().query(TemporalQueries.chronology());
            if (chrono == null || chrono == IsoChronology.INSTANCE) {
                text = this.provider.getText(this.field, value.longValue(), this.textStyle, context.getLocale());
            } else {
                text = this.provider.getText(chrono, this.field, value.longValue(), this.textStyle, context.getLocale());
            }
            if (text == null) {
                return numberPrinterParser().format(context, buf);
            }
            buf.append(text);
            return true;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence parseText, int position) {
            Iterator<Map.Entry<String, Long>> it;
            int length = parseText.length();
            if (position < 0 || position > length) {
                throw new IndexOutOfBoundsException();
            }
            TextStyle style = context.isStrict() ? this.textStyle : null;
            Chronology chrono = context.getEffectiveChronology();
            if (chrono == null || chrono == IsoChronology.INSTANCE) {
                it = this.provider.getTextIterator(this.field, style, context.getLocale());
            } else {
                it = this.provider.getTextIterator(chrono, this.field, style, context.getLocale());
            }
            if (it != null) {
                while (it.hasNext()) {
                    Map.Entry<String, Long> entry = it.next();
                    String itText = entry.getKey();
                    if (context.subSequenceEquals(itText, 0, parseText, position, itText.length())) {
                        return context.setParsedField(this.field, entry.getValue().longValue(), position, position + itText.length());
                    }
                }
                if (context.isStrict()) {
                    return ~position;
                }
            }
            return numberPrinterParser().parse(context, parseText, position);
        }

        private NumberPrinterParser numberPrinterParser() {
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser;
        }

        public String toString() {
            if (this.textStyle == TextStyle.FULL) {
                return "Text(" + ((Object) this.field) + ")";
            }
            return "Text(" + ((Object) this.field) + "," + ((Object) this.textStyle) + ")";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InstantPrinterParser implements DateTimePrinterParser {
        private static final long SECONDS_0000_TO_1970 = 62167219200L;
        private static final long SECONDS_PER_10000_YEARS = 315569520000L;
        private final int fractionalDigits;

        InstantPrinterParser(int fractionalDigits2) {
            this.fractionalDigits = fractionalDigits2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            Long inSecs = context.getValue(ChronoField.INSTANT_SECONDS);
            Long inNanos = null;
            if (context.getTemporal().isSupported(ChronoField.NANO_OF_SECOND)) {
                inNanos = Long.valueOf(context.getTemporal().getLong(ChronoField.NANO_OF_SECOND));
            }
            if (inSecs == null) {
                return false;
            }
            long inSec = inSecs.longValue();
            int inNano = ChronoField.NANO_OF_SECOND.checkValidIntValue(inNanos != null ? inNanos.longValue() : 0);
            if (inSec >= -62167219200L) {
                long zeroSecs = (inSec - SECONDS_PER_10000_YEARS) + SECONDS_0000_TO_1970;
                long hi = Math.floorDiv(zeroSecs, (long) SECONDS_PER_10000_YEARS) + 1;
                LocalDateTime ldt = LocalDateTime.ofEpochSecond(Math.floorMod(zeroSecs, (long) SECONDS_PER_10000_YEARS) - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                if (hi > 0) {
                    buf.append('+');
                    buf.append(hi);
                }
                buf.append((Object) ldt);
                if (ldt.getSecond() == 0) {
                    buf.append(":00");
                }
            } else {
                long zeroSecs2 = inSec + SECONDS_0000_TO_1970;
                long hi2 = zeroSecs2 / SECONDS_PER_10000_YEARS;
                long lo = zeroSecs2 % SECONDS_PER_10000_YEARS;
                LocalDateTime ldt2 = LocalDateTime.ofEpochSecond(lo - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                int pos = buf.length();
                buf.append((Object) ldt2);
                if (ldt2.getSecond() == 0) {
                    buf.append(":00");
                }
                if (hi2 < 0) {
                    if (ldt2.getYear() == -10000) {
                        buf.replace(pos, pos + 2, Long.toString(hi2 - 1));
                    } else if (lo == 0) {
                        buf.insert(pos, hi2);
                    } else {
                        buf.insert(pos + 1, Math.abs(hi2));
                    }
                }
            }
            if ((this.fractionalDigits < 0 && inNano > 0) || this.fractionalDigits > 0) {
                buf.append('.');
                int div = 100000000;
                int i = 0;
                while (true) {
                    if ((this.fractionalDigits != -1 || inNano <= 0) && ((this.fractionalDigits != -2 || (inNano <= 0 && i % 3 == 0)) && i >= this.fractionalDigits)) {
                        break;
                    }
                    int digit = inNano / div;
                    buf.append((char) (digit + 48));
                    inNano -= digit * div;
                    div /= 10;
                    i++;
                }
            }
            buf.append('Z');
            return true;
        }

        /* JADX INFO: Multiple debug info for r0v31 long: [D('ldt' java.time.LocalDateTime), D('instantSecs' long)] */
        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int sec;
            int hour;
            int sec2;
            int minDigits = this.fractionalDigits;
            int nano = 0;
            if (minDigits < 0) {
                minDigits = 0;
            }
            int maxDigits = this.fractionalDigits;
            if (maxDigits < 0) {
                maxDigits = 9;
            }
            CompositePrinterParser parser = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral('T').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendFraction(ChronoField.NANO_OF_SECOND, minDigits, maxDigits, true).appendLiteral('Z').toFormatter().toPrinterParser(false);
            DateTimeParseContext newContext = context.copy();
            int pos = parser.parse(newContext, text, position);
            if (pos < 0) {
                return pos;
            }
            long yearParsed = newContext.getParsed(ChronoField.YEAR).longValue();
            int month = newContext.getParsed(ChronoField.MONTH_OF_YEAR).intValue();
            int day = newContext.getParsed(ChronoField.DAY_OF_MONTH).intValue();
            int hour2 = newContext.getParsed(ChronoField.HOUR_OF_DAY).intValue();
            int min = newContext.getParsed(ChronoField.MINUTE_OF_HOUR).intValue();
            Long secVal = newContext.getParsed(ChronoField.SECOND_OF_MINUTE);
            Long nanoVal = newContext.getParsed(ChronoField.NANO_OF_SECOND);
            int sec3 = secVal != null ? secVal.intValue() : 0;
            if (nanoVal != null) {
                nano = nanoVal.intValue();
            }
            if (hour2 == 24 && min == 0 && sec3 == 0 && nano == 0) {
                hour = 0;
                sec = sec3;
                sec2 = 1;
            } else if (hour2 == 23 && min == 59 && sec3 == 60) {
                context.setParsedLeapSecond();
                hour = hour2;
                sec = 59;
                sec2 = 0;
            } else {
                hour = hour2;
                sec = sec3;
                sec2 = 0;
            }
            try {
                try {
                    try {
                        try {
                            return context.setParsedField(ChronoField.NANO_OF_SECOND, (long) nano, position, context.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(((int) yearParsed) % VMRuntime.SDK_VERSION_CUR_DEVELOPMENT, month, day, hour, min, sec, 0).plusDays((long) sec2).toEpochSecond(ZoneOffset.UTC) + Math.multiplyExact(yearParsed / 10000, (long) SECONDS_PER_10000_YEARS), position, pos));
                        } catch (RuntimeException e) {
                            return ~position;
                        }
                    } catch (RuntimeException e2) {
                        return ~position;
                    }
                } catch (RuntimeException e3) {
                    return ~position;
                }
            } catch (RuntimeException e4) {
                return ~position;
            }
        }

        public String toString() {
            return "Instant()";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class OffsetIdPrinterParser implements DateTimePrinterParser {
        static final OffsetIdPrinterParser INSTANCE_ID_Z = new OffsetIdPrinterParser("+HH:MM:ss", "Z");
        static final OffsetIdPrinterParser INSTANCE_ID_ZERO = new OffsetIdPrinterParser("+HH:MM:ss", AndroidHardcodedSystemProperties.JAVA_VERSION);
        static final String[] PATTERNS = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS"};
        private final String noOffsetText;
        private final int type;

        OffsetIdPrinterParser(String pattern, String noOffsetText2) {
            Objects.requireNonNull(pattern, "pattern");
            Objects.requireNonNull(noOffsetText2, "noOffsetText");
            this.type = checkPattern(pattern);
            this.noOffsetText = noOffsetText2;
        }

        private int checkPattern(String pattern) {
            int i = 0;
            while (true) {
                String[] strArr = PATTERNS;
                if (i >= strArr.length) {
                    throw new IllegalArgumentException("Invalid zone offset pattern: " + pattern);
                } else if (strArr[i].equals(pattern)) {
                    return i;
                } else {
                    i++;
                }
            }
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            Long offsetSecs = context.getValue(ChronoField.OFFSET_SECONDS);
            if (offsetSecs == null) {
                return false;
            }
            int totalSecs = Math.toIntExact(offsetSecs.longValue());
            if (totalSecs == 0) {
                buf.append(this.noOffsetText);
            } else {
                int absHours = Math.abs((totalSecs / 3600) % 100);
                int absMinutes = Math.abs((totalSecs / 60) % 60);
                int absSeconds = Math.abs(totalSecs % 60);
                int bufPos = buf.length();
                int output = absHours;
                buf.append(totalSecs < 0 ? "-" : "+");
                buf.append((char) ((absHours / 10) + 48));
                buf.append((char) ((absHours % 10) + 48));
                int i = this.type;
                if (i >= 3 || (i >= 1 && absMinutes > 0)) {
                    String str = ":";
                    buf.append(this.type % 2 == 0 ? str : "");
                    buf.append((char) ((absMinutes / 10) + 48));
                    buf.append((char) ((absMinutes % 10) + 48));
                    output += absMinutes;
                    int i2 = this.type;
                    if (i2 >= 7 || (i2 >= 5 && absSeconds > 0)) {
                        if (this.type % 2 != 0) {
                            str = "";
                        }
                        buf.append(str);
                        buf.append((char) ((absSeconds / 10) + 48));
                        buf.append((char) ((absSeconds % 10) + 48));
                        output += absSeconds;
                    }
                }
                if (output == 0) {
                    buf.setLength(bufPos);
                    buf.append(this.noOffsetText);
                }
            }
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parse(java.time.format.DateTimeParseContext r19, java.lang.CharSequence r20, int r21) {
            /*
            // Method dump skipped, instructions count: 185
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.format.DateTimeFormatterBuilder.OffsetIdPrinterParser.parse(java.time.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        private boolean parseNumber(int[] array, int arrayIndex, CharSequence parseText, boolean required) {
            int value;
            int i = this.type;
            if ((i + 3) / 2 < arrayIndex) {
                return false;
            }
            int pos = array[0];
            if (i % 2 == 0 && arrayIndex > 1) {
                if (pos + 1 > parseText.length() || parseText.charAt(pos) != ':') {
                    return required;
                }
                pos++;
            }
            if (pos + 2 > parseText.length()) {
                return required;
            }
            int pos2 = pos + 1;
            char ch1 = parseText.charAt(pos);
            int pos3 = pos2 + 1;
            char ch2 = parseText.charAt(pos2);
            if (ch1 < '0' || ch1 > '9' || ch2 < '0' || ch2 > '9' || (value = ((ch1 - '0') * 10) + (ch2 - '0')) < 0 || value > 59) {
                return required;
            }
            array[arrayIndex] = value;
            array[0] = pos3;
            return false;
        }

        public String toString() {
            String converted = this.noOffsetText.replace("'", "''");
            return "Offset(" + PATTERNS[this.type] + ",'" + converted + "')";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class LocalizedOffsetIdPrinterParser implements DateTimePrinterParser {
        private final TextStyle style;

        LocalizedOffsetIdPrinterParser(TextStyle style2) {
            this.style = style2;
        }

        private static StringBuilder appendHMS(StringBuilder buf, int t) {
            buf.append((char) ((t / 10) + 48));
            buf.append((char) ((t % 10) + 48));
            return buf;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            Long offsetSecs = context.getValue(ChronoField.OFFSET_SECONDS);
            if (offsetSecs == null) {
                return false;
            }
            buf.append("GMT");
            int totalSecs = Math.toIntExact(offsetSecs.longValue());
            if (totalSecs == 0) {
                return true;
            }
            int absHours = Math.abs((totalSecs / 3600) % 100);
            int absMinutes = Math.abs((totalSecs / 60) % 60);
            int absSeconds = Math.abs(totalSecs % 60);
            buf.append(totalSecs < 0 ? "-" : "+");
            if (this.style == TextStyle.FULL) {
                appendHMS(buf, absHours);
                buf.append(':');
                appendHMS(buf, absMinutes);
                if (absSeconds == 0) {
                    return true;
                }
                buf.append(':');
                appendHMS(buf, absSeconds);
                return true;
            }
            if (absHours >= 10) {
                buf.append((char) ((absHours / 10) + 48));
            }
            buf.append((char) ((absHours % 10) + 48));
            if (absMinutes == 0 && absSeconds == 0) {
                return true;
            }
            buf.append(':');
            appendHMS(buf, absMinutes);
            if (absSeconds == 0) {
                return true;
            }
            buf.append(':');
            appendHMS(buf, absSeconds);
            return true;
        }

        /* access modifiers changed from: package-private */
        public int getDigit(CharSequence text, int position) {
            char c = text.charAt(position);
            if (c < '0' || c > '9') {
                return -1;
            }
            return c - '0';
        }

        /* JADX INFO: Multiple debug info for r4v2 int: [D('pos' int), D('h2' int)] */
        /* JADX INFO: Multiple debug info for r1v11 int: [D('pos' int), D('h' int)] */
        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int negative;
            int pos;
            int pos2;
            int m1;
            int h;
            int end = position + text.length();
            if (!context.subSequenceEquals(text, position, "GMT", 0, "GMT".length())) {
                return ~position;
            }
            int pos3 = position + "GMT".length();
            if (pos3 == end) {
                return context.setParsedField(ChronoField.OFFSET_SECONDS, 0, position, pos3);
            }
            char sign = text.charAt(pos3);
            if (sign == '+') {
                negative = 1;
            } else if (sign != '-') {
                return context.setParsedField(ChronoField.OFFSET_SECONDS, 0, position, pos3);
            } else {
                negative = -1;
            }
            int pos4 = pos3 + 1;
            int m = 0;
            int s = 0;
            if (this.style == TextStyle.FULL) {
                int pos5 = pos4 + 1;
                int h1 = getDigit(text, pos4);
                int pos6 = pos5 + 1;
                int h2 = getDigit(text, pos5);
                if (h1 >= 0 && h2 >= 0) {
                    int pos7 = pos6 + 1;
                    if (text.charAt(pos6) == 58) {
                        h = (h1 * 10) + h2;
                        int h3 = pos7 + 1;
                        int m12 = getDigit(text, pos7);
                        int pos8 = h3 + 1;
                        int m2 = getDigit(text, h3);
                        if (m12 >= 0) {
                            if (m2 >= 0) {
                                int m3 = (m12 * 10) + m2;
                                if (pos8 + 2 < end) {
                                    if (text.charAt(pos8) == ':') {
                                        int s1 = getDigit(text, pos8 + 1);
                                        int s2 = getDigit(text, pos8 + 2);
                                        if (s1 >= 0 && s2 >= 0) {
                                            s = (s1 * 10) + s2;
                                            pos8 += 3;
                                        }
                                    }
                                }
                                m1 = m3;
                                pos = pos8;
                                pos2 = s;
                            }
                        }
                        return ~position;
                    }
                }
                return ~position;
            }
            int pos9 = pos4 + 1;
            h = getDigit(text, pos4);
            if (h < 0) {
                return ~position;
            }
            if (pos9 < end) {
                int h22 = getDigit(text, pos9);
                if (h22 >= 0) {
                    pos9++;
                    h = (h * 10) + h22;
                }
                if (pos9 + 2 < end && text.charAt(pos9) == ':' && pos9 + 2 < end && text.charAt(pos9) == ':') {
                    int m13 = getDigit(text, pos9 + 1);
                    int m22 = getDigit(text, pos9 + 2);
                    if (m13 >= 0 && m22 >= 0) {
                        m = (m13 * 10) + m22;
                        pos9 += 3;
                        if (pos9 + 2 < end && text.charAt(pos9) == ':') {
                            int s12 = getDigit(text, pos9 + 1);
                            int s22 = getDigit(text, pos9 + 2);
                            if (s12 >= 0 && s22 >= 0) {
                                m1 = m;
                                pos2 = (s12 * 10) + s22;
                                pos = pos9 + 3;
                            }
                        }
                    }
                }
                m1 = m;
                pos2 = 0;
                pos = pos9;
            } else {
                m1 = 0;
                pos2 = 0;
                pos = pos9;
            }
            return context.setParsedField(ChronoField.OFFSET_SECONDS, ((long) negative) * ((((long) h) * 3600) + (((long) m1) * 60) + ((long) pos2)), position, pos);
        }

        public String toString() {
            return "LocalizedOffset(" + ((Object) this.style) + ")";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ZoneTextPrinterParser extends ZoneIdPrinterParser {
        private static final int DST = 1;
        private static final TimeZoneNames.NameType[] FULL_TYPES = {TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.LONG_GENERIC};
        private static final int GENERIC = 2;
        private static final TimeZoneNames.NameType[] SHORT_TYPES = {TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT, TimeZoneNames.NameType.SHORT_GENERIC};
        private static final int STD = 0;
        private static final TimeZoneNames.NameType[] TYPES = {TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_DAYLIGHT, TimeZoneNames.NameType.LONG_GENERIC, TimeZoneNames.NameType.SHORT_GENERIC};
        private static final Map<String, SoftReference<Map<Locale, String[]>>> cache = new ConcurrentHashMap();
        private final Map<Locale, Map.Entry<Integer, SoftReference<PrefixTree>>> cachedTree = new HashMap();
        private final Map<Locale, Map.Entry<Integer, SoftReference<PrefixTree>>> cachedTreeCI = new HashMap();
        private Set<String> preferredZones;
        private final TextStyle textStyle;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ZoneTextPrinterParser(java.time.format.TextStyle r5, java.util.Set<java.time.ZoneId> r6) {
            /*
                r4 = this;
                java.time.temporal.TemporalQuery r0 = java.time.temporal.TemporalQueries.zone()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "ZoneText("
                r1.append(r2)
                r1.append(r5)
                java.lang.String r2 = ")"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r4.<init>(r0, r1)
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r4.cachedTree = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r4.cachedTreeCI = r0
                java.lang.String r0 = "textStyle"
                java.lang.Object r0 = java.util.Objects.requireNonNull(r5, r0)
                java.time.format.TextStyle r0 = (java.time.format.TextStyle) r0
                r4.textStyle = r0
                if (r6 == 0) goto L_0x005e
                int r0 = r6.size()
                if (r0 == 0) goto L_0x005e
                java.util.HashSet r0 = new java.util.HashSet
                r0.<init>()
                r4.preferredZones = r0
                java.util.Iterator r0 = r6.iterator()
            L_0x0048:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x005e
                java.lang.Object r1 = r0.next()
                java.time.ZoneId r1 = (java.time.ZoneId) r1
                java.util.Set<java.lang.String> r2 = r4.preferredZones
                java.lang.String r3 = r1.getId()
                r2.add(r3)
                goto L_0x0048
            L_0x005e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.format.DateTimeFormatterBuilder.ZoneTextPrinterParser.<init>(java.time.format.TextStyle, java.util.Set):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:45:0x00d7  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00ee  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String getDisplayName(java.lang.String r21, int r22, java.util.Locale r23) {
            /*
            // Method dump skipped, instructions count: 248
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.format.DateTimeFormatterBuilder.ZoneTextPrinterParser.getDisplayName(java.lang.String, int, java.util.Locale):java.lang.String");
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser, java.time.format.DateTimeFormatterBuilder.ZoneIdPrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            ZoneId zone = (ZoneId) context.getValue(TemporalQueries.zoneId());
            int i = 0;
            if (zone == null) {
                return false;
            }
            String zname = zone.getId();
            if (!(zone instanceof ZoneOffset)) {
                TemporalAccessor dt = context.getTemporal();
                if (!dt.isSupported(ChronoField.INSTANT_SECONDS)) {
                    i = 2;
                } else if (zone.getRules().isDaylightSavings(Instant.from(dt))) {
                    i = 1;
                }
                String name = getDisplayName(zname, i, context.getLocale());
                if (name != null) {
                    zname = name;
                }
            }
            buf.append(zname);
            return true;
        }

        /* access modifiers changed from: protected */
        @Override // java.time.format.DateTimeFormatterBuilder.ZoneIdPrinterParser
        public PrefixTree getTree(DateTimeParseContext context) {
            PrefixTree tree;
            String zid;
            ZoneTextPrinterParser zoneTextPrinterParser = this;
            if (zoneTextPrinterParser.textStyle == TextStyle.NARROW) {
                return super.getTree(context);
            }
            Locale locale = context.getLocale();
            boolean isCaseSensitive = context.isCaseSensitive();
            Set<String> regionIds = ZoneRulesProvider.getAvailableZoneIds();
            int regionIdsSize = regionIds.size();
            Map<Locale, Map.Entry<Integer, SoftReference<PrefixTree>>> cached = isCaseSensitive ? zoneTextPrinterParser.cachedTree : zoneTextPrinterParser.cachedTreeCI;
            Map.Entry<Integer, SoftReference<PrefixTree>> entry = cached.get(locale);
            if (entry != null && entry.getKey().intValue() == regionIdsSize) {
                PrefixTree prefixTree = entry.getValue().get();
                tree = prefixTree;
                if (prefixTree != null) {
                    return tree;
                }
            }
            tree = PrefixTree.newTree(context);
            TimeZoneNames timeZoneNames = TimeZoneNames.getInstance(locale);
            long now = System.currentTimeMillis();
            TimeZoneNames.NameType[] types = zoneTextPrinterParser.textStyle == TextStyle.FULL ? FULL_TYPES : SHORT_TYPES;
            String[] names = new String[types.length];
            for (String zid2 : regionIds) {
                tree.add(zid2, zid2);
                String zid3 = ZoneName.toZid(zid2, locale);
                String zid4 = zid3;
                String[] names2 = names;
                timeZoneNames.getDisplayNames(zid3, types, now, names, 0);
                int i = 0;
                while (i < names2.length) {
                    if (names2[i] != null) {
                        zid = zid4;
                        tree.add(names2[i], zid);
                    } else {
                        zid = zid4;
                    }
                    i++;
                    zid4 = zid;
                    names2 = names2;
                }
                names = names2;
                types = types;
            }
            String[] names3 = names;
            if (zoneTextPrinterParser.preferredZones != null) {
                for (String zid5 : regionIds) {
                    if (zoneTextPrinterParser.preferredZones.contains(zid5)) {
                        timeZoneNames.getDisplayNames(ZoneName.toZid(zid5, locale), types, now, names3, 0);
                        for (int i2 = 0; i2 < names3.length; i2++) {
                            if (names3[i2] != null) {
                                tree.add(names3[i2], zid5);
                            }
                        }
                        zoneTextPrinterParser = this;
                        names3 = names3;
                        isCaseSensitive = isCaseSensitive;
                    }
                }
            }
            cached.put(locale, new AbstractMap.SimpleImmutableEntry(Integer.valueOf(regionIdsSize), new SoftReference(tree)));
            return tree;
        }
    }

    /* access modifiers changed from: package-private */
    public static class ZoneIdPrinterParser implements DateTimePrinterParser {
        private static volatile Map.Entry<Integer, PrefixTree> cachedPrefixTree;
        private static volatile Map.Entry<Integer, PrefixTree> cachedPrefixTreeCI;
        private final String description;
        private final TemporalQuery<ZoneId> query;

        ZoneIdPrinterParser(TemporalQuery<ZoneId> query2, String description2) {
            this.query = query2;
            this.description = description2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            ZoneId zone = (ZoneId) context.getValue(this.query);
            if (zone == null) {
                return false;
            }
            buf.append(zone.getId());
            return true;
        }

        /* access modifiers changed from: protected */
        public PrefixTree getTree(DateTimeParseContext context) {
            Set<String> regionIds = ZoneRulesProvider.getAvailableZoneIds();
            int regionIdsSize = regionIds.size();
            Map.Entry<Integer, PrefixTree> cached = context.isCaseSensitive() ? cachedPrefixTree : cachedPrefixTreeCI;
            if (cached == null || cached.getKey().intValue() != regionIdsSize) {
                synchronized (this) {
                    cached = context.isCaseSensitive() ? cachedPrefixTree : cachedPrefixTreeCI;
                    if (cached == null || cached.getKey().intValue() != regionIdsSize) {
                        cached = new AbstractMap.SimpleImmutableEntry<>(Integer.valueOf(regionIdsSize), PrefixTree.newTree(regionIds, context));
                        if (context.isCaseSensitive()) {
                            cachedPrefixTree = cached;
                        } else {
                            cachedPrefixTreeCI = cached;
                        }
                    }
                }
            }
            return cached.getValue();
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            int length = text.length();
            if (position > length) {
                throw new IndexOutOfBoundsException();
            } else if (position == length) {
                return ~position;
            } else {
                char nextChar = text.charAt(position);
                if (nextChar == '+' || nextChar == '-') {
                    return parseOffsetBased(context, text, position, position, OffsetIdPrinterParser.INSTANCE_ID_Z);
                }
                if (length >= position + 2) {
                    char nextNextChar = text.charAt(position + 1);
                    if (!context.charEquals(nextChar, 'U') || !context.charEquals(nextNextChar, 'T')) {
                        if (context.charEquals(nextChar, 'G') && length >= position + 3 && context.charEquals(nextNextChar, 'M') && context.charEquals(text.charAt(position + 2), 'T')) {
                            return parseOffsetBased(context, text, position, position + 3, OffsetIdPrinterParser.INSTANCE_ID_ZERO);
                        }
                    } else if (length < position + 3 || !context.charEquals(text.charAt(position + 2), 'C')) {
                        return parseOffsetBased(context, text, position, position + 2, OffsetIdPrinterParser.INSTANCE_ID_ZERO);
                    } else {
                        return parseOffsetBased(context, text, position, position + 3, OffsetIdPrinterParser.INSTANCE_ID_ZERO);
                    }
                }
                PrefixTree tree = getTree(context);
                ParsePosition ppos = new ParsePosition(position);
                String parsedZoneId = tree.match(text, ppos);
                if (parsedZoneId != null) {
                    context.setParsed(ZoneId.of(parsedZoneId));
                    return ppos.getIndex();
                } else if (!context.charEquals(nextChar, 'Z')) {
                    return ~position;
                } else {
                    context.setParsed(ZoneOffset.UTC);
                    return position + 1;
                }
            }
        }

        private int parseOffsetBased(DateTimeParseContext context, CharSequence text, int prefixPos, int position, OffsetIdPrinterParser parser) {
            String prefix = text.toString().substring(prefixPos, position).toUpperCase();
            if (position >= text.length()) {
                context.setParsed(ZoneId.of(prefix));
                return position;
            } else if (text.charAt(position) == '0' && prefix.equals("GMT")) {
                context.setParsed(ZoneId.of("GMT0"));
                return position + 1;
            } else if (text.charAt(position) == '0' || context.charEquals(text.charAt(position), 'Z')) {
                context.setParsed(ZoneId.of(prefix));
                return position;
            } else {
                DateTimeParseContext newContext = context.copy();
                int endPos = parser.parse(newContext, text, position);
                if (endPos < 0) {
                    try {
                        if (parser == OffsetIdPrinterParser.INSTANCE_ID_Z) {
                            return ~prefixPos;
                        }
                        context.setParsed(ZoneId.of(prefix));
                        return position;
                    } catch (DateTimeException e) {
                        return ~prefixPos;
                    }
                } else {
                    context.setParsed(ZoneId.ofOffset(prefix, ZoneOffset.ofTotalSeconds((int) newContext.getParsed(ChronoField.OFFSET_SECONDS).longValue())));
                    return endPos;
                }
            }
        }

        public String toString() {
            return this.description;
        }
    }

    /* access modifiers changed from: package-private */
    public static class PrefixTree {
        protected char c0;
        protected PrefixTree child;
        protected String key;
        protected PrefixTree sibling;
        protected String value;

        private PrefixTree(String k, String v, PrefixTree child2) {
            this.key = k;
            this.value = v;
            this.child = child2;
            if (k.length() == 0) {
                this.c0 = 65535;
            } else {
                this.c0 = this.key.charAt(0);
            }
        }

        public static PrefixTree newTree(DateTimeParseContext context) {
            if (context.isCaseSensitive()) {
                return new PrefixTree("", null, null);
            }
            return new CI("", null, null);
        }

        public static PrefixTree newTree(Set<String> keys, DateTimeParseContext context) {
            PrefixTree tree = newTree(context);
            for (String k : keys) {
                tree.add0(k, k);
            }
            return tree;
        }

        public PrefixTree copyTree() {
            PrefixTree copy = new PrefixTree(this.key, this.value, null);
            PrefixTree prefixTree = this.child;
            if (prefixTree != null) {
                copy.child = prefixTree.copyTree();
            }
            PrefixTree prefixTree2 = this.sibling;
            if (prefixTree2 != null) {
                copy.sibling = prefixTree2.copyTree();
            }
            return copy;
        }

        public boolean add(String k, String v) {
            return add0(k, v);
        }

        private boolean add0(String k, String v) {
            String k2 = toKey(k);
            int prefixLen = prefixLength(k2);
            if (prefixLen != this.key.length()) {
                PrefixTree n1 = newNode(this.key.substring(prefixLen), this.value, this.child);
                this.key = k2.substring(0, prefixLen);
                this.child = n1;
                if (prefixLen < k2.length()) {
                    this.child.sibling = newNode(k2.substring(prefixLen), v, null);
                    this.value = null;
                } else {
                    this.value = v;
                }
                return true;
            } else if (prefixLen < k2.length()) {
                String subKey = k2.substring(prefixLen);
                for (PrefixTree c = this.child; c != null; c = c.sibling) {
                    if (isEqual(c.c0, subKey.charAt(0))) {
                        return c.add0(subKey, v);
                    }
                }
                PrefixTree c2 = newNode(subKey, v, null);
                c2.sibling = this.child;
                this.child = c2;
                return true;
            } else {
                this.value = v;
                return true;
            }
        }

        public String match(CharSequence text, int off, int end) {
            int off2;
            if (!prefixOf(text, off, end)) {
                return null;
            }
            if (this.child == null || (off2 = this.key.length() + off) == end) {
                return this.value;
            }
            PrefixTree c = this.child;
            while (!isEqual(c.c0, text.charAt(off2))) {
                c = c.sibling;
                if (c == null) {
                    return this.value;
                }
            }
            String found = c.match(text, off2, end);
            if (found != null) {
                return found;
            }
            return this.value;
        }

        public String match(CharSequence text, ParsePosition pos) {
            int off = pos.getIndex();
            int end = text.length();
            if (!prefixOf(text, off, end)) {
                return null;
            }
            int off2 = off + this.key.length();
            if (this.child != null && off2 != end) {
                PrefixTree c = this.child;
                while (true) {
                    if (!isEqual(c.c0, text.charAt(off2))) {
                        c = c.sibling;
                        if (c == null) {
                            break;
                        }
                    } else {
                        pos.setIndex(off2);
                        String found = c.match(text, pos);
                        if (found != null) {
                            return found;
                        }
                    }
                }
            }
            pos.setIndex(off2);
            return this.value;
        }

        /* access modifiers changed from: protected */
        public String toKey(String k) {
            return k;
        }

        /* access modifiers changed from: protected */
        public PrefixTree newNode(String k, String v, PrefixTree child2) {
            return new PrefixTree(k, v, child2);
        }

        /* access modifiers changed from: protected */
        public boolean isEqual(char c1, char c2) {
            return c1 == c2;
        }

        /* access modifiers changed from: protected */
        public boolean prefixOf(CharSequence text, int off, int end) {
            if (text instanceof String) {
                return ((String) text).startsWith(this.key, off);
            }
            int len = this.key.length();
            if (len > end - off) {
                return false;
            }
            int off2 = 0;
            while (true) {
                int len2 = len - 1;
                if (len <= 0) {
                    return true;
                }
                int off0 = off2 + 1;
                char charAt = this.key.charAt(off2);
                int off3 = off + 1;
                if (!isEqual(charAt, text.charAt(off))) {
                    return false;
                }
                off = off3;
                len = len2;
                off2 = off0;
            }
        }

        private int prefixLength(String k) {
            int off = 0;
            while (off < k.length() && off < this.key.length() && isEqual(k.charAt(off), this.key.charAt(off))) {
                off++;
            }
            return off;
        }

        /* access modifiers changed from: private */
        public static class CI extends PrefixTree {
            private CI(String k, String v, PrefixTree child) {
                super(k, v, child);
            }

            /* access modifiers changed from: protected */
            @Override // java.time.format.DateTimeFormatterBuilder.PrefixTree
            public CI newNode(String k, String v, PrefixTree child) {
                return new CI(k, v, child);
            }

            /* access modifiers changed from: protected */
            @Override // java.time.format.DateTimeFormatterBuilder.PrefixTree
            public boolean isEqual(char c1, char c2) {
                return DateTimeParseContext.charEqualsIgnoreCase(c1, c2);
            }

            /* access modifiers changed from: protected */
            @Override // java.time.format.DateTimeFormatterBuilder.PrefixTree
            public boolean prefixOf(CharSequence text, int off, int end) {
                int len = this.key.length();
                if (len > end - off) {
                    return false;
                }
                int off2 = 0;
                while (true) {
                    int len2 = len - 1;
                    if (len <= 0) {
                        return true;
                    }
                    int off0 = off2 + 1;
                    char charAt = this.key.charAt(off2);
                    int off3 = off + 1;
                    if (!isEqual(charAt, text.charAt(off))) {
                        return false;
                    }
                    off = off3;
                    len = len2;
                    off2 = off0;
                }
            }
        }

        private static class LENIENT extends CI {
            private LENIENT(String k, String v, PrefixTree child) {
                super(k, v, child);
            }

            /* access modifiers changed from: protected */
            @Override // java.time.format.DateTimeFormatterBuilder.PrefixTree.CI, java.time.format.DateTimeFormatterBuilder.PrefixTree.CI, java.time.format.DateTimeFormatterBuilder.PrefixTree
            public CI newNode(String k, String v, PrefixTree child) {
                return new LENIENT(k, v, child);
            }

            private boolean isLenientChar(char c) {
                return c == ' ' || c == '_' || c == '/';
            }

            /* access modifiers changed from: protected */
            @Override // java.time.format.DateTimeFormatterBuilder.PrefixTree
            public String toKey(String k) {
                int i = 0;
                while (i < k.length()) {
                    if (isLenientChar(k.charAt(i))) {
                        StringBuilder sb = new StringBuilder(k.length());
                        sb.append((CharSequence) k, 0, i);
                        while (true) {
                            i++;
                            if (i >= k.length()) {
                                return sb.toString();
                            }
                            if (!isLenientChar(k.charAt(i))) {
                                sb.append(k.charAt(i));
                            }
                        }
                    } else {
                        i++;
                    }
                }
                return k;
            }

            @Override // java.time.format.DateTimeFormatterBuilder.PrefixTree
            public String match(CharSequence text, ParsePosition pos) {
                int off = pos.getIndex();
                int end = text.length();
                int len = this.key.length();
                int koff = 0;
                while (koff < len && off < end) {
                    if (isLenientChar(text.charAt(off))) {
                        off++;
                    } else {
                        int koff2 = koff + 1;
                        int off2 = off + 1;
                        if (!isEqual(this.key.charAt(koff), text.charAt(off))) {
                            return null;
                        }
                        off = off2;
                        koff = koff2;
                    }
                }
                if (koff != len) {
                    return null;
                }
                if (this.child != null && off != end) {
                    int off0 = off;
                    while (off0 < end && isLenientChar(text.charAt(off0))) {
                        off0++;
                    }
                    if (off0 < end) {
                        PrefixTree c = this.child;
                        while (true) {
                            if (!isEqual(c.c0, text.charAt(off0))) {
                                c = c.sibling;
                                if (c == null) {
                                    break;
                                }
                            } else {
                                pos.setIndex(off0);
                                String found = c.match(text, pos);
                                if (found != null) {
                                    return found;
                                }
                            }
                        }
                    }
                }
                pos.setIndex(off);
                return this.value;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ChronoPrinterParser implements DateTimePrinterParser {
        private final TextStyle textStyle;

        ChronoPrinterParser(TextStyle textStyle2) {
            this.textStyle = textStyle2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            Chronology chrono = (Chronology) context.getValue(TemporalQueries.chronology());
            if (chrono == null) {
                return false;
            }
            if (this.textStyle == null) {
                buf.append(chrono.getId());
                return true;
            }
            buf.append(getChronologyName(chrono, context.getLocale()));
            return true;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            String name;
            if (position < 0 || position > text.length()) {
                throw new IndexOutOfBoundsException();
            }
            Chronology bestMatch = null;
            int matchLen = -1;
            for (Chronology chrono : Chronology.getAvailableChronologies()) {
                if (this.textStyle == null) {
                    name = chrono.getId();
                } else {
                    name = getChronologyName(chrono, context.getLocale());
                }
                int nameLen = name.length();
                if (nameLen > matchLen && context.subSequenceEquals(text, position, name, 0, nameLen)) {
                    bestMatch = chrono;
                    matchLen = nameLen;
                }
            }
            if (bestMatch == null) {
                return ~position;
            }
            context.setParsed(bestMatch);
            return position + matchLen;
        }

        private String getChronologyName(Chronology chrono, Locale locale) {
            String name = LocaleDisplayNames.getInstance(ULocale.forLocale(locale)).keyValueDisplayName("calendar", chrono.getCalendarType());
            return name != null ? name : chrono.getId();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class LocalizedPrinterParser implements DateTimePrinterParser {
        private static final ConcurrentMap<String, DateTimeFormatter> FORMATTER_CACHE = new ConcurrentHashMap(16, 0.75f, 2);
        private final FormatStyle dateStyle;
        private final FormatStyle timeStyle;

        LocalizedPrinterParser(FormatStyle dateStyle2, FormatStyle timeStyle2) {
            this.dateStyle = dateStyle2;
            this.timeStyle = timeStyle2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            return formatter(context.getLocale(), Chronology.from(context.getTemporal())).toPrinterParser(false).format(context, buf);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            return formatter(context.getLocale(), context.getEffectiveChronology()).toPrinterParser(false).parse(context, text, position);
        }

        private DateTimeFormatter formatter(Locale locale, Chronology chrono) {
            String key = chrono.getId() + '|' + locale.toString() + '|' + ((Object) this.dateStyle) + ((Object) this.timeStyle);
            DateTimeFormatter formatter = FORMATTER_CACHE.get(key);
            if (formatter != null) {
                return formatter;
            }
            DateTimeFormatter formatter2 = new DateTimeFormatterBuilder().appendPattern(DateTimeFormatterBuilder.getLocalizedDateTimePattern(this.dateStyle, this.timeStyle, chrono, locale)).toFormatter(locale);
            DateTimeFormatter old = FORMATTER_CACHE.putIfAbsent(key, formatter2);
            return old != null ? old : formatter2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Localized(");
            FormatStyle formatStyle = this.dateStyle;
            if (formatStyle == null) {
                formatStyle = "";
            }
            sb.append((Object) formatStyle);
            sb.append(",");
            FormatStyle formatStyle2 = this.timeStyle;
            if (formatStyle2 == null) {
                formatStyle2 = "";
            }
            sb.append((Object) formatStyle2);
            sb.append(")");
            return sb.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WeekBasedFieldPrinterParser implements DateTimePrinterParser {
        private char chr;
        private int count;

        WeekBasedFieldPrinterParser(char chr2, int count2) {
            this.chr = chr2;
            this.count = count2;
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean format(DateTimePrintContext context, StringBuilder buf) {
            return printerParser(context.getLocale()).format(context, buf);
        }

        @Override // java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext context, CharSequence text, int position) {
            return printerParser(context.getLocale()).parse(context, text, position);
        }

        private DateTimePrinterParser printerParser(Locale locale) {
            TemporalField field;
            WeekFields weekDef = WeekFields.of(locale);
            char c = this.chr;
            if (c == 'W') {
                field = weekDef.weekOfMonth();
            } else if (c == 'Y') {
                TemporalField field2 = weekDef.weekBasedYear();
                int i = this.count;
                if (i == 2) {
                    return new ReducedPrinterParser(field2, 2, 2, 0, ReducedPrinterParser.BASE_DATE, 0);
                }
                return new NumberPrinterParser(field2, i, 19, i < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD, -1);
            } else if (c == 'c' || c == 'e') {
                field = weekDef.dayOfWeek();
            } else if (c == 'w') {
                field = weekDef.weekOfWeekBasedYear();
            } else {
                throw new IllegalStateException("unreachable");
            }
            return new NumberPrinterParser(field, this.count == 2 ? 2 : 1, 2, SignStyle.NOT_NEGATIVE);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Localized(");
            char c = this.chr;
            if (c == 'Y') {
                int i = this.count;
                if (i == 1) {
                    sb.append("WeekBasedYear");
                } else if (i == 2) {
                    sb.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
                } else {
                    sb.append("WeekBasedYear,");
                    sb.append(this.count);
                    sb.append(",");
                    sb.append(19);
                    sb.append(",");
                    sb.append((Object) (this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD));
                }
            } else {
                if (c == 'W') {
                    sb.append("WeekOfMonth");
                } else if (c == 'c' || c == 'e') {
                    sb.append("DayOfWeek");
                } else if (c == 'w') {
                    sb.append("WeekOfWeekBasedYear");
                }
                sb.append(",");
                sb.append(this.count);
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
