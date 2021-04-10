package java.text;

import android.icu.text.TimeZoneNames;
import android.icu.util.ULocale;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.icu.LocaleData;
import sun.util.calendar.CalendarUtils;

public class SimpleDateFormat extends DateFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Set<TimeZoneNames.NameType> DST_NAME_TYPES = Collections.unmodifiableSet(EnumSet.of(TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_DAYLIGHT));
    private static final String GMT = "GMT";
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final EnumSet<TimeZoneNames.NameType> NAME_TYPES = EnumSet.of(TimeZoneNames.NameType.LONG_GENERIC, TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_GENERIC, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT);
    private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {0, 1, 2, 5, 11, 11, 12, 13, 14, 7, 6, 8, 3, 4, 9, 10, 10, 15, 15, 17, 1000, 15, 2, 7, 9, 9};
    private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 1, 9, 17, 2, 9, 14, 14};
    private static final DateFormat.Field[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = {DateFormat.Field.ERA, DateFormat.Field.YEAR, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_MONTH, DateFormat.Field.HOUR_OF_DAY1, DateFormat.Field.HOUR_OF_DAY0, DateFormat.Field.MINUTE, DateFormat.Field.SECOND, DateFormat.Field.MILLISECOND, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.DAY_OF_YEAR, DateFormat.Field.DAY_OF_WEEK_IN_MONTH, DateFormat.Field.WEEK_OF_YEAR, DateFormat.Field.WEEK_OF_MONTH, DateFormat.Field.AM_PM, DateFormat.Field.HOUR1, DateFormat.Field.HOUR0, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.TIME_ZONE, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.AM_PM, DateFormat.Field.AM_PM};
    private static final int[] REST_OF_STYLES = {Calendar.SHORT_STANDALONE, 2, Calendar.LONG_STANDALONE};
    private static final int TAG_QUOTE_ASCII_CHAR = 100;
    private static final int TAG_QUOTE_CHARS = 101;
    private static final ConcurrentMap<Locale, NumberFormat> cachedNumberFormatData = new ConcurrentHashMap(3);
    static final int currentSerialVersion = 1;
    static final long serialVersionUID = 4774881970558875024L;
    private transient char[] compiledPattern;
    private Date defaultCenturyStart;
    private transient int defaultCenturyStartYear;
    private DateFormatSymbols formatData;
    private transient boolean hasFollowingMinusSign;
    private Locale locale;
    private transient char minusSign;
    private transient NumberFormat originalNumberFormat;
    private transient String originalNumberPattern;
    private String pattern;
    private int serialVersionOnStream;
    private transient TimeZoneNames timeZoneNames;
    transient boolean useDateFormatSymbols;
    private transient char zeroDigit;

    public SimpleDateFormat() {
        this(3, 3, Locale.getDefault(Locale.Category.FORMAT));
    }

    SimpleDateFormat(int timeStyle, int dateStyle, Locale locale2) {
        this(getDateTimeFormat(timeStyle, dateStyle, locale2), locale2);
    }

    private static String getDateTimeFormat(int timeStyle, int dateStyle, Locale locale2) {
        LocaleData localeData = LocaleData.get(locale2);
        if (timeStyle >= 0 && dateStyle >= 0) {
            return MessageFormat.format("{0} {1}", localeData.getDateFormat(dateStyle), localeData.getTimeFormat(timeStyle));
        } else if (timeStyle >= 0) {
            return localeData.getTimeFormat(timeStyle);
        } else {
            if (dateStyle >= 0) {
                return localeData.getDateFormat(dateStyle);
            }
            throw new IllegalArgumentException("No date or time style specified");
        }
    }

    public SimpleDateFormat(String pattern2) {
        this(pattern2, Locale.getDefault(Locale.Category.FORMAT));
    }

    public SimpleDateFormat(String pattern2, Locale locale2) {
        this.serialVersionOnStream = 1;
        this.minusSign = '-';
        this.hasFollowingMinusSign = false;
        if (pattern2 == null || locale2 == null) {
            throw new NullPointerException();
        }
        initializeCalendar(locale2);
        this.pattern = pattern2;
        this.formatData = DateFormatSymbols.getInstanceRef(locale2);
        this.locale = locale2;
        initialize(locale2);
    }

    public SimpleDateFormat(String pattern2, DateFormatSymbols formatSymbols) {
        this.serialVersionOnStream = 1;
        this.minusSign = '-';
        this.hasFollowingMinusSign = false;
        if (pattern2 == null || formatSymbols == null) {
            throw new NullPointerException();
        }
        this.pattern = pattern2;
        this.formatData = (DateFormatSymbols) formatSymbols.clone();
        this.locale = Locale.getDefault(Locale.Category.FORMAT);
        initializeCalendar(this.locale);
        initialize(this.locale);
        this.useDateFormatSymbols = true;
    }

    private void initialize(Locale loc) {
        this.compiledPattern = compile(this.pattern);
        this.numberFormat = cachedNumberFormatData.get(loc);
        if (this.numberFormat == null) {
            this.numberFormat = NumberFormat.getIntegerInstance(loc);
            this.numberFormat.setGroupingUsed(false);
            cachedNumberFormatData.putIfAbsent(loc, this.numberFormat);
        }
        this.numberFormat = (NumberFormat) this.numberFormat.clone();
        initializeDefaultCentury();
    }

    private void initializeCalendar(Locale loc) {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance(TimeZone.getDefault(), loc);
        }
    }

    private char[] compile(String pattern2) {
        char c;
        int length = pattern2.length();
        boolean inQuote = false;
        StringBuilder compiledCode = new StringBuilder(length * 2);
        StringBuilder tmpBuffer = null;
        int count = 0;
        int lastTag = -1;
        int i = 0;
        while (i < length) {
            char c2 = pattern2.charAt(i);
            if (c2 == '\'') {
                if (i + 1 < length && (c = pattern2.charAt(i + 1)) == '\'') {
                    i++;
                    if (count != 0) {
                        encode(lastTag, count, compiledCode);
                        lastTag = -1;
                        count = 0;
                    }
                    if (inQuote) {
                        tmpBuffer.append(c);
                    } else {
                        compiledCode.append((char) (c | 25600));
                    }
                } else if (!inQuote) {
                    if (count != 0) {
                        encode(lastTag, count, compiledCode);
                        lastTag = -1;
                        count = 0;
                    }
                    if (tmpBuffer == null) {
                        tmpBuffer = new StringBuilder(length);
                    } else {
                        tmpBuffer.setLength(0);
                    }
                    inQuote = true;
                } else {
                    int len = tmpBuffer.length();
                    if (len == 1) {
                        char ch = tmpBuffer.charAt(0);
                        if (ch < 128) {
                            compiledCode.append((char) (ch | 25600));
                        } else {
                            compiledCode.append((char) 25857);
                            compiledCode.append(ch);
                        }
                    } else {
                        encode(101, len, compiledCode);
                        compiledCode.append((CharSequence) tmpBuffer);
                    }
                    inQuote = false;
                }
            } else if (inQuote) {
                tmpBuffer.append(c2);
            } else if ((c2 < 'a' || c2 > 'z') && (c2 < 'A' || c2 > 'Z')) {
                if (count != 0) {
                    encode(lastTag, count, compiledCode);
                    lastTag = -1;
                    count = 0;
                }
                if (c2 < 128) {
                    compiledCode.append((char) (c2 | 25600));
                } else {
                    int j = i + 1;
                    while (j < length) {
                        char d = pattern2.charAt(j);
                        if (d == '\'' || ((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {
                            break;
                        }
                        j++;
                    }
                    compiledCode.append((char) ((j - i) | 25856));
                    while (i < j) {
                        compiledCode.append(pattern2.charAt(i));
                        i++;
                    }
                    i--;
                }
            } else {
                int tag = "GyMdkHmsSEDFwWahKzZYuXLcbB".indexOf(c2);
                if (tag == -1) {
                    throw new IllegalArgumentException("Illegal pattern character '" + c2 + "'");
                } else if (lastTag == -1 || lastTag == tag) {
                    lastTag = tag;
                    count++;
                } else {
                    encode(lastTag, count, compiledCode);
                    lastTag = tag;
                    count = 1;
                }
            }
            i++;
        }
        if (!inQuote) {
            if (count != 0) {
                encode(lastTag, count, compiledCode);
            }
            int len2 = compiledCode.length();
            char[] r = new char[len2];
            compiledCode.getChars(0, len2, r, 0);
            return r;
        }
        throw new IllegalArgumentException("Unterminated quote");
    }

    private static void encode(int tag, int length, StringBuilder buffer) {
        if (tag == 21 && length >= 4) {
            throw new IllegalArgumentException("invalid ISO 8601 format: length=" + length);
        } else if (length < 255) {
            buffer.append((char) ((tag << 8) | length));
        } else {
            buffer.append((char) (255 | (tag << 8)));
            buffer.append((char) (length >>> 16));
            buffer.append((char) (65535 & length));
        }
    }

    private void initializeDefaultCentury() {
        this.calendar.setTimeInMillis(System.currentTimeMillis());
        this.calendar.add(1, -80);
        parseAmbiguousDatesAsAfter(this.calendar.getTime());
    }

    private void parseAmbiguousDatesAsAfter(Date startDate) {
        this.defaultCenturyStart = startDate;
        this.calendar.setTime(startDate);
        this.defaultCenturyStartYear = this.calendar.get(1);
    }

    public void set2DigitYearStart(Date startDate) {
        parseAmbiguousDatesAsAfter(new Date(startDate.getTime()));
    }

    public Date get2DigitYearStart() {
        return (Date) this.defaultCenturyStart.clone();
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        pos.endIndex = 0;
        pos.beginIndex = 0;
        return format(date, toAppendTo, pos.getFieldDelegate());
    }

    private StringBuffer format(Date date, StringBuffer toAppendTo, Format.FieldDelegate delegate) {
        int i;
        int count;
        this.calendar.setTime(date);
        boolean useDateFormatSymbols2 = useDateFormatSymbols();
        int i2 = 0;
        while (true) {
            char[] cArr = this.compiledPattern;
            if (i2 >= cArr.length) {
                return toAppendTo;
            }
            int tag = cArr[i2] >>> '\b';
            int i3 = i2 + 1;
            int count2 = cArr[i2] & 255;
            if (count2 == 255) {
                int i4 = i3 + 1;
                count = (cArr[i3] << 16) | cArr[i4];
                i = i4 + 1;
            } else {
                count = count2;
                i = i3;
            }
            if (tag == 100) {
                toAppendTo.append((char) count);
            } else if (tag != 101) {
                subFormat(tag, count, delegate, toAppendTo, useDateFormatSymbols2);
            } else {
                toAppendTo.append(this.compiledPattern, i, count);
                i2 = i + count;
            }
            i2 = i;
        }
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        StringBuffer sb = new StringBuffer();
        CharacterIteratorFieldDelegate delegate = new CharacterIteratorFieldDelegate();
        if (obj instanceof Date) {
            format((Date) obj, sb, delegate);
        } else if (obj instanceof Number) {
            format(new Date(((Number) obj).longValue()), sb, delegate);
        } else if (obj == null) {
            throw new NullPointerException("formatToCharacterIterator must be passed non-null object");
        } else {
            throw new IllegalArgumentException("Cannot format given Object as a Date");
        }
        return delegate.getIterator(sb.toString());
    }

    private void subFormat(int patternCharIndex, int count, Format.FieldDelegate delegate, StringBuffer buffer, boolean useDateFormatSymbols2) {
        int field;
        int patternCharIndex2;
        int value;
        String current;
        int patternCharIndex3;
        String current2;
        int style;
        int field2;
        int i;
        String zoneString;
        TimeZoneNames.NameType nameType;
        int beginOffset = buffer.length();
        int field3 = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
        if (field3 == 17) {
            if (this.calendar.isWeekDateSupported()) {
                patternCharIndex2 = patternCharIndex;
                field = field3;
                value = this.calendar.getWeekYear();
            } else {
                int field4 = PATTERN_INDEX_TO_CALENDAR_FIELD[1];
                field = field4;
                patternCharIndex2 = 1;
                value = this.calendar.get(field4);
            }
        } else if (field3 == 1000) {
            patternCharIndex2 = patternCharIndex;
            field = field3;
            value = CalendarBuilder.toISODayOfWeek(this.calendar.get(7));
        } else {
            patternCharIndex2 = patternCharIndex;
            field = field3;
            value = this.calendar.get(field3);
        }
        int style2 = count >= 4 ? 2 : 1;
        if (useDateFormatSymbols2 || field == 1000) {
            current = null;
        } else {
            current = this.calendar.getDisplayName(field, style2, this.locale);
        }
        if (patternCharIndex2 != 0) {
            if (patternCharIndex2 == 1) {
                i = 1;
                style = style2;
                patternCharIndex3 = patternCharIndex2;
                field2 = value;
            } else if (patternCharIndex2 != 2) {
                if (patternCharIndex2 == 4) {
                    patternCharIndex3 = patternCharIndex2;
                    if (current == null) {
                        if (value == 0) {
                            zeroPaddingNumber(this.calendar.getMaximum(11) + 1, count, Integer.MAX_VALUE, buffer);
                        } else {
                            zeroPaddingNumber(value, count, Integer.MAX_VALUE, buffer);
                        }
                    }
                } else if (patternCharIndex2 != 8) {
                    int tzstyle = 0;
                    boolean includeGmt = false;
                    if (patternCharIndex2 == 9) {
                        patternCharIndex3 = patternCharIndex2;
                        if (current == null) {
                            current2 = formatWeekday(count, value, useDateFormatSymbols2, false);
                        }
                    } else if (patternCharIndex2 == 14) {
                        patternCharIndex3 = patternCharIndex2;
                        if (useDateFormatSymbols2) {
                            current2 = this.formatData.getAmPmStrings()[value];
                        }
                    } else if (patternCharIndex2 != 15) {
                        switch (patternCharIndex2) {
                            case 17:
                                patternCharIndex3 = patternCharIndex2;
                                if (current != null) {
                                    break;
                                } else {
                                    TimeZone tz = this.calendar.getTimeZone();
                                    boolean daylight = this.calendar.get(16) != 0;
                                    if (this.formatData.isZoneStringsSet) {
                                        if (count >= 4) {
                                            tzstyle = 1;
                                        }
                                        zoneString = libcore.icu.TimeZoneNames.getDisplayName(this.formatData.getZoneStringsWrapper(), tz.getID(), daylight, tzstyle);
                                    } else {
                                        if (count < 4) {
                                            if (daylight) {
                                                nameType = TimeZoneNames.NameType.SHORT_DAYLIGHT;
                                            } else {
                                                nameType = TimeZoneNames.NameType.SHORT_STANDARD;
                                            }
                                        } else if (daylight) {
                                            nameType = TimeZoneNames.NameType.LONG_DAYLIGHT;
                                        } else {
                                            nameType = TimeZoneNames.NameType.LONG_STANDARD;
                                        }
                                        zoneString = getTimeZoneNames().getDisplayName(android.icu.util.TimeZone.getCanonicalID(tz.getID()), nameType, this.calendar.getTimeInMillis());
                                    }
                                    if (zoneString != null) {
                                        buffer.append(zoneString);
                                    } else {
                                        buffer.append(TimeZone.createGmtOffsetString(true, true, this.calendar.get(15) + this.calendar.get(16)));
                                    }
                                    break;
                                }
                            case 18:
                                patternCharIndex3 = patternCharIndex2;
                                int value2 = this.calendar.get(15) + this.calendar.get(16);
                                boolean includeSeparator = count >= 4;
                                if (count == 4) {
                                    includeGmt = true;
                                }
                                buffer.append(TimeZone.createGmtOffsetString(includeGmt, includeSeparator, value2));
                                current2 = current;
                                break;
                            case 19:
                                style = style2;
                                patternCharIndex3 = patternCharIndex2;
                                i = 1;
                                field2 = value;
                                break;
                            default:
                                switch (patternCharIndex2) {
                                    case 21:
                                        patternCharIndex3 = patternCharIndex2;
                                        int value3 = this.calendar.get(15) + this.calendar.get(16);
                                        if (value3 != 0) {
                                            int value4 = value3 / 60000;
                                            if (value4 >= 0) {
                                                buffer.append('+');
                                            } else {
                                                buffer.append('-');
                                                value4 = -value4;
                                            }
                                            CalendarUtils.sprintf0d(buffer, value4 / 60, 2);
                                            if (count != 1) {
                                                if (count == 3) {
                                                    buffer.append(':');
                                                }
                                                CalendarUtils.sprintf0d(buffer, value4 % 60, 2);
                                            }
                                            current2 = current;
                                            break;
                                        } else {
                                            buffer.append('Z');
                                            current2 = current;
                                            break;
                                        }
                                    case 22:
                                        patternCharIndex3 = patternCharIndex2;
                                        current2 = formatMonth(count, value, Integer.MAX_VALUE, buffer, useDateFormatSymbols2, true, field, style2);
                                        break;
                                    case 23:
                                        if (current != null) {
                                            patternCharIndex3 = patternCharIndex2;
                                            break;
                                        } else {
                                            patternCharIndex3 = patternCharIndex2;
                                            current2 = formatWeekday(count, value, useDateFormatSymbols2, true);
                                            break;
                                        }
                                    case 24:
                                    case 25:
                                        patternCharIndex3 = patternCharIndex2;
                                        current2 = "";
                                        break;
                                    default:
                                        if (current != null) {
                                            patternCharIndex3 = patternCharIndex2;
                                            break;
                                        } else {
                                            zeroPaddingNumber(value, count, Integer.MAX_VALUE, buffer);
                                            patternCharIndex3 = patternCharIndex2;
                                            break;
                                        }
                                }
                        }
                    } else {
                        patternCharIndex3 = patternCharIndex2;
                        if (current == null) {
                            if (value == 0) {
                                zeroPaddingNumber(this.calendar.getLeastMaximum(10) + 1, count, Integer.MAX_VALUE, buffer);
                            } else {
                                zeroPaddingNumber(value, count, Integer.MAX_VALUE, buffer);
                            }
                        }
                    }
                } else {
                    patternCharIndex3 = patternCharIndex2;
                    if (current == null) {
                        zeroPaddingNumber((int) ((((double) value) / 1000.0d) * Math.pow(10.0d, (double) count)), count, count, buffer);
                        current2 = current;
                    }
                }
                current2 = current;
            } else {
                patternCharIndex3 = patternCharIndex2;
                current2 = formatMonth(count, value, Integer.MAX_VALUE, buffer, useDateFormatSymbols2, false, field, style2);
            }
            if (this.calendar instanceof GregorianCalendar) {
                if (count != 2) {
                    zeroPaddingNumber(field2, count, Integer.MAX_VALUE, buffer);
                } else {
                    zeroPaddingNumber(field2, 2, 2, buffer);
                }
            } else if (current == null) {
                if (style != 2) {
                    i = count;
                }
                zeroPaddingNumber(field2, i, Integer.MAX_VALUE, buffer);
            }
            current2 = current;
        } else {
            patternCharIndex3 = patternCharIndex2;
            if (useDateFormatSymbols2) {
                String[] eras = this.formatData.getEras();
                if (value < eras.length) {
                    current = eras[value];
                }
            }
            if (current == null) {
                current2 = "";
            } else {
                current2 = current;
            }
        }
        if (current2 != null) {
            buffer.append(current2);
        }
        int fieldID = PATTERN_INDEX_TO_DATE_FORMAT_FIELD[patternCharIndex3];
        DateFormat.Field f = PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID[patternCharIndex3];
        delegate.formatted(fieldID, f, f, beginOffset, buffer.length(), buffer);
    }

    private String formatWeekday(int count, int value, boolean useDateFormatSymbols2, boolean standalone) {
        String[] weekdays;
        if (!useDateFormatSymbols2) {
            return null;
        }
        if (count == 4) {
            DateFormatSymbols dateFormatSymbols = this.formatData;
            weekdays = standalone ? dateFormatSymbols.getStandAloneWeekdays() : dateFormatSymbols.getWeekdays();
        } else if (count == 5) {
            DateFormatSymbols dateFormatSymbols2 = this.formatData;
            weekdays = standalone ? dateFormatSymbols2.getTinyStandAloneWeekdays() : dateFormatSymbols2.getTinyWeekdays();
        } else {
            DateFormatSymbols dateFormatSymbols3 = this.formatData;
            weekdays = standalone ? dateFormatSymbols3.getShortStandAloneWeekdays() : dateFormatSymbols3.getShortWeekdays();
        }
        return weekdays[value];
    }

    private String formatMonth(int count, int value, int maxIntCount, StringBuffer buffer, boolean useDateFormatSymbols2, boolean standalone, int field, int style) {
        String[] months;
        String current = null;
        if (useDateFormatSymbols2) {
            if (count == 4) {
                DateFormatSymbols dateFormatSymbols = this.formatData;
                months = standalone ? dateFormatSymbols.getStandAloneMonths() : dateFormatSymbols.getMonths();
            } else if (count == 5) {
                DateFormatSymbols dateFormatSymbols2 = this.formatData;
                months = standalone ? dateFormatSymbols2.getTinyStandAloneMonths() : dateFormatSymbols2.getTinyMonths();
            } else if (count == 3) {
                DateFormatSymbols dateFormatSymbols3 = this.formatData;
                months = standalone ? dateFormatSymbols3.getShortStandAloneMonths() : dateFormatSymbols3.getShortMonths();
            } else {
                months = null;
            }
            if (months != null) {
                current = months[value];
            }
        } else if (count < 3) {
            current = null;
        } else {
            if (standalone) {
                style = Calendar.toStandaloneStyle(style);
            }
            current = this.calendar.getDisplayName(field, style, this.locale);
        }
        if (current == null) {
            zeroPaddingNumber(value + 1, count, maxIntCount, buffer);
        }
        return current;
    }

    private void zeroPaddingNumber(int value, int minDigits, int maxDigits, StringBuffer buffer) {
        try {
            if (this.zeroDigit == 0) {
                this.zeroDigit = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols().getZeroDigit();
            }
            if (value >= 0) {
                if (value >= 100 || minDigits < 1 || minDigits > 2) {
                    if (value >= 1000 && value < 10000) {
                        if (minDigits == 4) {
                            buffer.append((char) (this.zeroDigit + (value / 1000)));
                            int value2 = value % 1000;
                            buffer.append((char) (this.zeroDigit + (value2 / 100)));
                            int value3 = value2 % 100;
                            buffer.append((char) (this.zeroDigit + (value3 / 10)));
                            buffer.append((char) (this.zeroDigit + (value3 % 10)));
                            return;
                        } else if (minDigits == 2 && maxDigits == 2) {
                            zeroPaddingNumber(value % 100, 2, 2, buffer);
                            return;
                        }
                    }
                } else if (value < 10) {
                    if (minDigits == 2) {
                        buffer.append(this.zeroDigit);
                    }
                    buffer.append((char) (this.zeroDigit + value));
                    return;
                } else {
                    buffer.append((char) (this.zeroDigit + (value / 10)));
                    buffer.append((char) (this.zeroDigit + (value % 10)));
                    return;
                }
            }
        } catch (Exception e) {
        }
        this.numberFormat.setMinimumIntegerDigits(minDigits);
        this.numberFormat.setMaximumIntegerDigits(maxDigits);
        this.numberFormat.format((long) value, buffer, DontCareFieldPosition.INSTANCE);
    }

    @Override // java.text.DateFormat
    public Date parse(String text, ParsePosition pos) {
        TimeZone tz = getTimeZone();
        try {
            return parseInternal(text, pos);
        } finally {
            setTimeZone(tz);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00fd, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Date parseInternal(java.lang.String r26, java.text.ParsePosition r27) {
        /*
        // Method dump skipped, instructions count: 310
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.SimpleDateFormat.parseInternal(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private int matchString(String text, int start, int field, String[] data, CalendarBuilder calb) {
        int i = 0;
        int count = data.length;
        if (field == 7) {
            i = 1;
        }
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i2 = i; i2 < count; i2++) {
            int length = data[i2].length();
            if (length > bestMatchLength && text.regionMatches(true, start, data[i2], 0, length)) {
                bestMatch = i2;
                bestMatchLength = length;
            }
            if (data[i2].charAt(length - 1) == '.' && length - 1 > bestMatchLength && text.regionMatches(true, start, data[i2], 0, length - 1)) {
                bestMatch = i2;
                bestMatchLength = length - 1;
            }
        }
        if (bestMatch < 0) {
            return -start;
        }
        calb.set(field, bestMatch);
        return start + bestMatchLength;
    }

    private int matchString(String text, int start, int field, Map<String, Integer> data, CalendarBuilder calb) {
        if (data != null) {
            String bestMatch = null;
            for (String name : data.keySet()) {
                int length = name.length();
                if ((bestMatch == null || length > bestMatch.length()) && text.regionMatches(true, start, name, 0, length)) {
                    bestMatch = name;
                }
            }
            if (bestMatch != null) {
                calb.set(field, data.get(bestMatch).intValue());
                return bestMatch.length() + start;
            }
        }
        return -start;
    }

    private int matchZoneString(String text, int start, String[] zoneNames) {
        for (int i = 1; i <= 4; i++) {
            String zoneName = zoneNames[i];
            if (text.regionMatches(true, start, zoneName, 0, zoneName.length())) {
                return i;
            }
        }
        return -1;
    }

    private int subParseZoneString(String text, int start, CalendarBuilder calb) {
        if (this.formatData.isZoneStringsSet) {
            return subParseZoneStringFromSymbols(text, start, calb);
        }
        return subParseZoneStringFromICU(text, start, calb);
    }

    private TimeZoneNames getTimeZoneNames() {
        if (this.timeZoneNames == null) {
            this.timeZoneNames = TimeZoneNames.getInstance(this.locale);
        }
        return this.timeZoneNames;
    }

    private int subParseZoneStringFromICU(String text, int start, CalendarBuilder calb) {
        String currentTimeZoneID = android.icu.util.TimeZone.getCanonicalID(getTimeZone().getID());
        TimeZoneNames tzNames = getTimeZoneNames();
        TimeZoneNames.MatchInfo bestMatch = null;
        Set<String> currentTzMetaZoneIds = null;
        Iterator<TimeZoneNames.MatchInfo> it = tzNames.find(text, start, NAME_TYPES).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TimeZoneNames.MatchInfo match = it.next();
            if (bestMatch == null || bestMatch.matchLength() < match.matchLength()) {
                bestMatch = match;
            } else if (bestMatch.matchLength() != match.matchLength()) {
                continue;
            } else if (currentTimeZoneID.equals(match.tzID())) {
                bestMatch = match;
                break;
            } else if (match.mzID() == null) {
                continue;
            } else {
                if (currentTzMetaZoneIds == null) {
                    currentTzMetaZoneIds = tzNames.getAvailableMetaZoneIDs(currentTimeZoneID);
                }
                if (currentTzMetaZoneIds.contains(match.mzID())) {
                    bestMatch = match;
                    break;
                }
            }
        }
        if (bestMatch == null) {
            return -start;
        }
        String tzId = bestMatch.tzID();
        if (tzId == null) {
            if (currentTzMetaZoneIds == null) {
                currentTzMetaZoneIds = tzNames.getAvailableMetaZoneIDs(currentTimeZoneID);
            }
            if (currentTzMetaZoneIds.contains(bestMatch.mzID())) {
                tzId = currentTimeZoneID;
            } else {
                ULocale uLocale = ULocale.forLocale(this.locale);
                String region = uLocale.getCountry();
                if (region.length() == 0) {
                    region = ULocale.addLikelySubtags(uLocale).getCountry();
                }
                tzId = tzNames.getReferenceZoneID(bestMatch.mzID(), region);
            }
        }
        TimeZone newTimeZone = TimeZone.getTimeZone(tzId);
        if (!currentTimeZoneID.equals(tzId)) {
            setTimeZone(newTimeZone);
        }
        boolean isDst = DST_NAME_TYPES.contains(bestMatch.nameType());
        int dstAmount = isDst ? newTimeZone.getDSTSavings() : 0;
        if (!isDst || dstAmount != 0) {
            calb.clear(15).set(16, dstAmount);
        }
        return bestMatch.matchLength() + start;
    }

    private int subParseZoneStringFromSymbols(String text, int start, CalendarBuilder calb) {
        int zoneIndex;
        boolean useSameName = false;
        TimeZone currentTimeZone = getTimeZone();
        int zoneIndex2 = this.formatData.getZoneIndex(currentTimeZone.getID());
        TimeZone tz = null;
        String[][] zoneStrings = this.formatData.getZoneStringsWrapper();
        String[] zoneNames = null;
        int nameIndex = 0;
        int dstAmount = 0;
        if (zoneIndex2 != -1) {
            zoneNames = zoneStrings[zoneIndex2];
            int matchZoneString = matchZoneString(text, start, zoneNames);
            nameIndex = matchZoneString;
            if (matchZoneString > 0) {
                if (nameIndex <= 2) {
                    useSameName = zoneNames[nameIndex].equalsIgnoreCase(zoneNames[nameIndex + 2]);
                }
                tz = TimeZone.getTimeZone(zoneNames[0]);
            }
        }
        if (tz == null && (zoneIndex = this.formatData.getZoneIndex(TimeZone.getDefault().getID())) != -1) {
            zoneNames = zoneStrings[zoneIndex];
            int matchZoneString2 = matchZoneString(text, start, zoneNames);
            nameIndex = matchZoneString2;
            if (matchZoneString2 > 0) {
                if (nameIndex <= 2) {
                    useSameName = zoneNames[nameIndex].equalsIgnoreCase(zoneNames[nameIndex + 2]);
                }
                tz = TimeZone.getTimeZone(zoneNames[0]);
            }
        }
        if (tz == null) {
            int len = zoneStrings.length;
            int i = 0;
            while (true) {
                if (i >= len) {
                    break;
                }
                zoneNames = zoneStrings[i];
                int matchZoneString3 = matchZoneString(text, start, zoneNames);
                nameIndex = matchZoneString3;
                if (matchZoneString3 > 0) {
                    if (nameIndex <= 2) {
                        useSameName = zoneNames[nameIndex].equalsIgnoreCase(zoneNames[nameIndex + 2]);
                    }
                    tz = TimeZone.getTimeZone(zoneNames[0]);
                } else {
                    i++;
                }
            }
        }
        if (tz == null) {
            return -start;
        }
        if (!tz.equals(currentTimeZone)) {
            setTimeZone(tz);
        }
        if (nameIndex >= 3) {
            dstAmount = tz.getDSTSavings();
        }
        if (!useSameName && (nameIndex < 3 || dstAmount != 0)) {
            calb.clear(15).set(16, dstAmount);
        }
        return zoneNames[nameIndex].length() + start;
    }

    private int subParseNumericZone(String text, int start, int sign, int count, boolean colon, CalendarBuilder calb) {
        int index = start + 1;
        try {
            char c = text.charAt(start);
            if (isDigit(c)) {
                int hours = c - 48;
                int index2 = index + 1;
                try {
                    char c2 = text.charAt(index);
                    if (isDigit(c2)) {
                        hours = (hours * 10) + (c2 - 48);
                        index = index2;
                    } else {
                        index = index2 - 1;
                    }
                    if (hours <= 23) {
                        int minutes = 0;
                        if (count != 1) {
                            int index3 = index + 1;
                            try {
                                char c3 = text.charAt(index);
                                if (c3 == ':') {
                                    index = index3 + 1;
                                    c3 = text.charAt(index3);
                                } else if (colon) {
                                    index = index3;
                                } else {
                                    index = index3;
                                }
                                if (isDigit(c3)) {
                                    int minutes2 = c3 - 48;
                                    int index4 = index + 1;
                                    char c4 = text.charAt(index);
                                    if (isDigit(c4) && (minutes = (minutes2 * 10) + (c4 - 48)) <= 59) {
                                        index = index4;
                                    } else {
                                        index = index4;
                                    }
                                }
                            } catch (IndexOutOfBoundsException e) {
                                index = index3;
                            }
                        }
                        calb.set(15, 60000 * (minutes + (hours * 60)) * sign).set(16, 0);
                        return index;
                    }
                } catch (IndexOutOfBoundsException e2) {
                    index = index2;
                }
            }
        } catch (IndexOutOfBoundsException e3) {
        }
        return 1 - index;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r3v5 int: [D('c' char), D('actualStart' int)] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v45 */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0086, code lost:
        if ((r11.calendar instanceof java.util.GregorianCalendar) == false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0172, code lost:
        if (r12.charAt(r9.index - 1) == r11.minusSign) goto L_0x0174;
     */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x050d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ce  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int subParse(java.lang.String r26, int r27, int r28, int r29, boolean r30, boolean[] r31, java.text.ParsePosition r32, boolean r33, java.text.CalendarBuilder r34) {
        /*
        // Method dump skipped, instructions count: 1412
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.SimpleDateFormat.subParse(java.lang.String, int, int, int, boolean, boolean[], java.text.ParsePosition, boolean, java.text.CalendarBuilder):int");
    }

    private int parseMonth(String text, int count, int value, int start, int field, ParsePosition pos, boolean useDateFormatSymbols2, boolean standalone, CalendarBuilder out) {
        int index;
        if (count <= 2) {
            out.set(2, value - 1);
            return pos.index;
        }
        if (useDateFormatSymbols2) {
            DateFormatSymbols dateFormatSymbols = this.formatData;
            int index2 = matchString(text, start, 2, standalone ? dateFormatSymbols.getStandAloneMonths() : dateFormatSymbols.getMonths(), out);
            if (index2 > 0) {
                return index2;
            }
            DateFormatSymbols dateFormatSymbols2 = this.formatData;
            int matchString = matchString(text, start, 2, standalone ? dateFormatSymbols2.getShortStandAloneMonths() : dateFormatSymbols2.getShortMonths(), out);
            index = matchString;
            if (matchString > 0) {
                return index;
            }
        } else {
            int matchString2 = matchString(text, start, field, getDisplayNamesMap(field, this.locale), out);
            index = matchString2;
            if (matchString2 > 0) {
                return index;
            }
        }
        return index;
    }

    private int parseWeekday(String text, int start, int field, boolean useDateFormatSymbols2, boolean standalone, CalendarBuilder out) {
        if (useDateFormatSymbols2) {
            DateFormatSymbols dateFormatSymbols = this.formatData;
            int index = matchString(text, start, 7, standalone ? dateFormatSymbols.getStandAloneWeekdays() : dateFormatSymbols.getWeekdays(), out);
            if (index > 0) {
                return index;
            }
            DateFormatSymbols dateFormatSymbols2 = this.formatData;
            int index2 = matchString(text, start, 7, standalone ? dateFormatSymbols2.getShortStandAloneWeekdays() : dateFormatSymbols2.getShortWeekdays(), out);
            if (index2 > 0) {
                return index2;
            }
            return index2;
        }
        int index3 = -1;
        for (int style : new int[]{2, 1}) {
            int matchString = matchString(text, start, field, this.calendar.getDisplayNames(field, style, this.locale), out);
            index3 = matchString;
            if (matchString > 0) {
                return index3;
            }
        }
        return index3;
    }

    private boolean useDateFormatSymbols() {
        return this.useDateFormatSymbols || "java.util.GregorianCalendar".equals(this.calendar.getClass().getName()) || this.locale == null;
    }

    private String translatePattern(String pattern2, String from, String to) {
        StringBuilder result = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < pattern2.length(); i++) {
            char c = pattern2.charAt(i);
            if (inQuote) {
                if (c == '\'') {
                    inQuote = false;
                }
            } else if (c == '\'') {
                inQuote = true;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                int ci = from.indexOf(c);
                if (ci < 0) {
                    throw new IllegalArgumentException("Illegal pattern  character '" + c + "'");
                } else if (ci < to.length()) {
                    c = to.charAt(ci);
                }
            }
            result.append(c);
        }
        if (!inQuote) {
            return result.toString();
        }
        throw new IllegalArgumentException("Unfinished quote in pattern");
    }

    public String toPattern() {
        return this.pattern;
    }

    public String toLocalizedPattern() {
        return translatePattern(this.pattern, "GyMdkHmsSEDFwWahKzZYuXLcbB", this.formatData.getLocalPatternChars());
    }

    public void applyPattern(String pattern2) {
        applyPatternImpl(pattern2);
    }

    private void applyPatternImpl(String pattern2) {
        this.compiledPattern = compile(pattern2);
        this.pattern = pattern2;
    }

    public void applyLocalizedPattern(String pattern2) {
        String p = translatePattern(pattern2, this.formatData.getLocalPatternChars(), "GyMdkHmsSEDFwWahKzZYuXLcbB");
        this.compiledPattern = compile(p);
        this.pattern = p;
    }

    public DateFormatSymbols getDateFormatSymbols() {
        return (DateFormatSymbols) this.formatData.clone();
    }

    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        this.formatData = (DateFormatSymbols) newFormatSymbols.clone();
        this.useDateFormatSymbols = true;
    }

    @Override // java.text.Format, java.text.DateFormat
    public Object clone() {
        SimpleDateFormat other = (SimpleDateFormat) super.clone();
        other.formatData = (DateFormatSymbols) this.formatData.clone();
        return other;
    }

    @Override // java.text.DateFormat
    public int hashCode() {
        return this.pattern.hashCode();
    }

    @Override // java.text.DateFormat
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        SimpleDateFormat that = (SimpleDateFormat) obj;
        if (!this.pattern.equals(that.pattern) || !this.formatData.equals(that.formatData)) {
            return false;
        }
        return true;
    }

    private Map<String, Integer> getDisplayNamesMap(int field, Locale locale2) {
        Map<String, Integer> map = this.calendar.getDisplayNames(field, 1, locale2);
        for (int style : REST_OF_STYLES) {
            Map<String, Integer> m = this.calendar.getDisplayNames(field, style, locale2);
            if (m != null) {
                map.putAll(m);
            }
        }
        return map;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        String id;
        TimeZone zi;
        stream.defaultReadObject();
        try {
            this.compiledPattern = compile(this.pattern);
            if (this.serialVersionOnStream < 1) {
                initializeDefaultCentury();
            } else {
                parseAmbiguousDatesAsAfter(this.defaultCenturyStart);
            }
            this.serialVersionOnStream = 1;
            TimeZone tz = getTimeZone();
            if ((tz instanceof SimpleTimeZone) && (zi = TimeZone.getTimeZone((id = tz.getID()))) != null && zi.hasSameRules(tz) && zi.getID().equals(id)) {
                setTimeZone(zi);
            }
        } catch (Exception e) {
            throw new InvalidObjectException("invalid pattern");
        }
    }

    private void checkNegativeNumberExpression() {
        int minusIndex;
        if ((this.numberFormat instanceof DecimalFormat) && !this.numberFormat.equals(this.originalNumberFormat)) {
            String numberPattern = ((DecimalFormat) this.numberFormat).toPattern();
            if (!numberPattern.equals(this.originalNumberPattern)) {
                this.hasFollowingMinusSign = false;
                int separatorIndex = numberPattern.indexOf(59);
                if (separatorIndex > -1 && (minusIndex = numberPattern.indexOf(45, separatorIndex)) > numberPattern.lastIndexOf(48) && minusIndex > numberPattern.lastIndexOf(35)) {
                    this.hasFollowingMinusSign = true;
                    this.minusSign = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols().getMinusSign();
                }
                this.originalNumberPattern = numberPattern;
            }
            this.originalNumberFormat = this.numberFormat;
        }
    }
}
