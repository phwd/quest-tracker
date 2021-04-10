package java.text;

import android.icu.text.TimeZoneNames;
import android.icu.util.ULocale;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.icu.LocaleData;

public class SimpleDateFormat extends DateFormat {
    private static final Set DST_NAME_TYPES = Collections.unmodifiableSet(EnumSet.of(TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_DAYLIGHT));
    private static final EnumSet NAME_TYPES = EnumSet.of(TimeZoneNames.NameType.LONG_GENERIC, TimeZoneNames.NameType.LONG_STANDARD, TimeZoneNames.NameType.LONG_DAYLIGHT, TimeZoneNames.NameType.SHORT_GENERIC, TimeZoneNames.NameType.SHORT_STANDARD, TimeZoneNames.NameType.SHORT_DAYLIGHT);
    private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {0, 1, 2, 5, 11, 11, 12, 13, 14, 7, 6, 8, 3, 4, 9, 10, 10, 15, 15, 17, 1000, 15, 2, 7, 9, 9};
    private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 1, 9, 17, 2, 9, 14, 14};
    private static final DateFormat.Field[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID;
    private static final int[] REST_OF_STYLES = {32769, 2, 32770};
    private static final ConcurrentMap cachedNumberFormatData = new ConcurrentHashMap(3);
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

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static {
        DateFormat.Field field = DateFormat.Field.TIME_ZONE;
        DateFormat.Field field2 = DateFormat.Field.AM_PM;
        PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = new DateFormat.Field[]{DateFormat.Field.ERA, DateFormat.Field.YEAR, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_MONTH, DateFormat.Field.HOUR_OF_DAY1, DateFormat.Field.HOUR_OF_DAY0, DateFormat.Field.MINUTE, DateFormat.Field.SECOND, DateFormat.Field.MILLISECOND, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.DAY_OF_YEAR, DateFormat.Field.DAY_OF_WEEK_IN_MONTH, DateFormat.Field.WEEK_OF_YEAR, DateFormat.Field.WEEK_OF_MONTH, DateFormat.Field.AM_PM, DateFormat.Field.HOUR1, DateFormat.Field.HOUR0, field, field, DateFormat.Field.YEAR, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.TIME_ZONE, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_WEEK, field2, field2};
    }

    public SimpleDateFormat() {
        this(3, 3, Locale.getDefault(Locale.Category.FORMAT));
    }

    SimpleDateFormat(int i, int i2, Locale locale2) {
        this(getDateTimeFormat(i, i2, locale2), locale2);
    }

    private static String getDateTimeFormat(int i, int i2, Locale locale2) {
        LocaleData localeData = LocaleData.get(locale2);
        if (i >= 0 && i2 >= 0) {
            return MessageFormat.format("{0} {1}", localeData.getDateFormat(i2), localeData.getTimeFormat(i));
        } else if (i >= 0) {
            return localeData.getTimeFormat(i);
        } else {
            if (i2 >= 0) {
                return localeData.getDateFormat(i2);
            }
            throw new IllegalArgumentException("No date or time style specified");
        }
    }

    public SimpleDateFormat(String str) {
        this(str, Locale.getDefault(Locale.Category.FORMAT));
    }

    public SimpleDateFormat(String str, Locale locale2) {
        this.serialVersionOnStream = 1;
        this.minusSign = '-';
        this.hasFollowingMinusSign = false;
        if (str == null || locale2 == null) {
            throw new NullPointerException();
        }
        initializeCalendar(locale2);
        this.pattern = str;
        this.formatData = DateFormatSymbols.getInstanceRef(locale2);
        this.locale = locale2;
        initialize(locale2);
    }

    private void initialize(Locale locale2) {
        this.compiledPattern = compile(this.pattern);
        this.numberFormat = (NumberFormat) cachedNumberFormatData.get(locale2);
        if (this.numberFormat == null) {
            this.numberFormat = NumberFormat.getIntegerInstance(locale2);
            this.numberFormat.setGroupingUsed(false);
            cachedNumberFormatData.putIfAbsent(locale2, this.numberFormat);
        }
        this.numberFormat = (NumberFormat) this.numberFormat.clone();
        initializeDefaultCentury();
    }

    private void initializeCalendar(Locale locale2) {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance(TimeZone.getDefault(), locale2);
        }
    }

    private char[] compile(String str) {
        int i;
        char charAt;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length * 2);
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        StringBuilder sb2 = null;
        int i6 = 0;
        while (i6 < length) {
            char charAt2 = str.charAt(i6);
            if (charAt2 == '\'') {
                int i7 = i6 + 1;
                if (i7 < length && (charAt = str.charAt(i7)) == '\'') {
                    if (i5 != 0) {
                        encode(i3, i5, sb);
                        i3 = -1;
                        i5 = i2;
                    }
                    if (i4 != 0) {
                        sb2.append(charAt);
                    } else {
                        sb.append((char) (charAt | 25600));
                    }
                    i6 = i7;
                } else if (i4 == 0) {
                    if (i5 != 0) {
                        encode(i3, i5, sb);
                        i3 = -1;
                        i5 = i2;
                    }
                    if (sb2 == null) {
                        sb2 = new StringBuilder(length);
                    } else {
                        sb2.setLength(i2);
                    }
                    i = 1;
                    i4 = 1;
                    i6 += i;
                    i2 = 0;
                } else {
                    int length2 = sb2.length();
                    if (length2 == 1) {
                        char charAt3 = sb2.charAt(i2);
                        if (charAt3 < 128) {
                            sb.append((char) (charAt3 | 25600));
                        } else {
                            sb.append((char) 25857);
                            sb.append(charAt3);
                        }
                    } else {
                        encode(101, length2, sb);
                        sb.append((CharSequence) sb2);
                    }
                    i4 = i2;
                }
            } else if (i4 != 0) {
                sb2.append(charAt2);
            } else {
                if ((charAt2 < 'a' || charAt2 > 'z') && (charAt2 < 'A' || charAt2 > 'Z')) {
                    if (i5 != 0) {
                        encode(i3, i5, sb);
                        i3 = -1;
                        i5 = 0;
                    }
                    if (charAt2 < 128) {
                        sb.append((char) (charAt2 | 25600));
                    } else {
                        int i8 = i6 + 1;
                        while (i8 < length) {
                            char charAt4 = str.charAt(i8);
                            if (charAt4 == '\'' || ((charAt4 >= 'a' && charAt4 <= 'z') || (charAt4 >= 'A' && charAt4 <= 'Z'))) {
                                break;
                            }
                            i8++;
                        }
                        sb.append((char) ((i8 - i6) | 25856));
                        while (i6 < i8) {
                            sb.append(str.charAt(i6));
                            i6++;
                        }
                        i6--;
                    }
                } else {
                    int indexOf = "GyMdkHmsSEDFwWahKzZYuXLcbB".indexOf(charAt2);
                    if (indexOf == -1) {
                        throw new IllegalArgumentException("Illegal pattern character '" + charAt2 + "'");
                    } else if (i3 == -1 || i3 == indexOf) {
                        i5++;
                        i3 = indexOf;
                    } else {
                        encode(i3, i5, sb);
                        i3 = indexOf;
                        i = 1;
                        i5 = 1;
                        i6 += i;
                        i2 = 0;
                    }
                }
                i = 1;
                i6 += i;
                i2 = 0;
            }
            i = 1;
            i6 += i;
            i2 = 0;
        }
        if (i4 == 0) {
            if (i5 != 0) {
                encode(i3, i5, sb);
            }
            int length3 = sb.length();
            char[] cArr = new char[length3];
            sb.getChars(0, length3, cArr, 0);
            return cArr;
        }
        throw new IllegalArgumentException("Unterminated quote");
    }

    private static void encode(int i, int i2, StringBuilder sb) {
        if (i == 21 && i2 >= 4) {
            throw new IllegalArgumentException("invalid ISO 8601 format: length=" + i2);
        } else if (i2 < 255) {
            sb.append((char) ((i << 8) | i2));
        } else {
            sb.append((char) ((i << 8) | 255));
            sb.append((char) (i2 >>> 16));
            sb.append((char) (65535 & i2));
        }
    }

    private void initializeDefaultCentury() {
        this.calendar.setTimeInMillis(System.currentTimeMillis());
        this.calendar.add(1, -80);
        parseAmbiguousDatesAsAfter(this.calendar.getTime());
    }

    private void parseAmbiguousDatesAsAfter(Date date) {
        this.defaultCenturyStart = date;
        this.calendar.setTime(date);
        this.defaultCenturyStartYear = this.calendar.get(1);
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.endIndex = 0;
        fieldPosition.beginIndex = 0;
        format(date, stringBuffer, fieldPosition.getFieldDelegate());
        return stringBuffer;
    }

    private StringBuffer format(Date date, StringBuffer stringBuffer, Format.FieldDelegate fieldDelegate) {
        int i;
        this.calendar.setTime(date);
        boolean useDateFormatSymbols2 = useDateFormatSymbols();
        int i2 = 0;
        while (true) {
            char[] cArr = this.compiledPattern;
            if (i2 >= cArr.length) {
                return stringBuffer;
            }
            int i3 = cArr[i2] >>> '\b';
            int i4 = i2 + 1;
            int i5 = cArr[i2] & 255;
            if (i5 == 255) {
                int i6 = i4 + 1;
                int i7 = i6 + 1;
                i = cArr[i6] | (cArr[i4] << 16);
                i2 = i7;
            } else {
                i = i5;
                i2 = i4;
            }
            if (i3 == 100) {
                stringBuffer.append((char) i);
            } else if (i3 != 101) {
                subFormat(i3, i, fieldDelegate, stringBuffer, useDateFormatSymbols2);
            } else {
                stringBuffer.append(this.compiledPattern, i2, i);
                i2 += i;
            }
        }
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        CharacterIteratorFieldDelegate characterIteratorFieldDelegate = new CharacterIteratorFieldDelegate();
        if (obj instanceof Date) {
            format((Date) obj, stringBuffer, characterIteratorFieldDelegate);
        } else if (obj instanceof Number) {
            format(new Date(((Number) obj).longValue()), stringBuffer, characterIteratorFieldDelegate);
        } else if (obj == null) {
            throw new NullPointerException("formatToCharacterIterator must be passed non-null object");
        } else {
            throw new IllegalArgumentException("Cannot format given Object as a Date");
        }
        return characterIteratorFieldDelegate.getIterator(stringBuffer.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x020c, code lost:
        if (r3 == null) goto L_0x008c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void subFormat(int r17, int r18, java.text.Format.FieldDelegate r19, java.lang.StringBuffer r20, boolean r21) {
        /*
        // Method dump skipped, instructions count: 580
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.SimpleDateFormat.subFormat(int, int, java.text.Format$FieldDelegate, java.lang.StringBuffer, boolean):void");
    }

    private String formatWeekday(int i, int i2, boolean z, boolean z2) {
        String[] strArr;
        if (!z) {
            return null;
        }
        if (i == 4) {
            DateFormatSymbols dateFormatSymbols = this.formatData;
            strArr = z2 ? dateFormatSymbols.getStandAloneWeekdays() : dateFormatSymbols.getWeekdays();
        } else if (i == 5) {
            DateFormatSymbols dateFormatSymbols2 = this.formatData;
            strArr = z2 ? dateFormatSymbols2.getTinyStandAloneWeekdays() : dateFormatSymbols2.getTinyWeekdays();
        } else {
            DateFormatSymbols dateFormatSymbols3 = this.formatData;
            strArr = z2 ? dateFormatSymbols3.getShortStandAloneWeekdays() : dateFormatSymbols3.getShortWeekdays();
        }
        return strArr[i2];
    }

    private String formatMonth(int i, int i2, int i3, StringBuffer stringBuffer, boolean z, boolean z2, int i4, int i5) {
        String[] strArr;
        String str = null;
        if (z) {
            if (i == 4) {
                DateFormatSymbols dateFormatSymbols = this.formatData;
                strArr = z2 ? dateFormatSymbols.getStandAloneMonths() : dateFormatSymbols.getMonths();
            } else if (i == 5) {
                DateFormatSymbols dateFormatSymbols2 = this.formatData;
                strArr = z2 ? dateFormatSymbols2.getTinyStandAloneMonths() : dateFormatSymbols2.getTinyMonths();
            } else if (i == 3) {
                DateFormatSymbols dateFormatSymbols3 = this.formatData;
                strArr = z2 ? dateFormatSymbols3.getShortStandAloneMonths() : dateFormatSymbols3.getShortMonths();
            } else {
                strArr = null;
            }
            if (strArr != null) {
                str = strArr[i2];
            }
        } else if (i >= 3) {
            if (z2) {
                i5 = Calendar.toStandaloneStyle(i5);
            }
            str = this.calendar.getDisplayName(i4, i5, this.locale);
        }
        if (str == null) {
            zeroPaddingNumber(i2 + 1, i, i3, stringBuffer);
        }
        return str;
    }

    private void zeroPaddingNumber(int i, int i2, int i3, StringBuffer stringBuffer) {
        try {
            if (this.zeroDigit == 0) {
                this.zeroDigit = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols().getZeroDigit();
            }
            if (i >= 0) {
                if (i >= 100 || i2 < 1 || i2 > 2) {
                    if (i >= 1000 && i < 10000) {
                        if (i2 == 4) {
                            stringBuffer.append((char) (this.zeroDigit + (i / 1000)));
                            int i4 = i % 1000;
                            stringBuffer.append((char) (this.zeroDigit + (i4 / 100)));
                            int i5 = i4 % 100;
                            stringBuffer.append((char) (this.zeroDigit + (i5 / 10)));
                            stringBuffer.append((char) (this.zeroDigit + (i5 % 10)));
                            return;
                        } else if (i2 == 2 && i3 == 2) {
                            zeroPaddingNumber(i % 100, 2, 2, stringBuffer);
                            return;
                        }
                    }
                } else if (i < 10) {
                    if (i2 == 2) {
                        stringBuffer.append(this.zeroDigit);
                    }
                    stringBuffer.append((char) (this.zeroDigit + i));
                    return;
                } else {
                    stringBuffer.append((char) (this.zeroDigit + (i / 10)));
                    stringBuffer.append((char) (this.zeroDigit + (i % 10)));
                    return;
                }
            }
        } catch (Exception unused) {
        }
        this.numberFormat.setMinimumIntegerDigits(i2);
        this.numberFormat.setMaximumIntegerDigits(i3);
        this.numberFormat.format((long) i, stringBuffer, DontCareFieldPosition.INSTANCE);
    }

    @Override // java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        TimeZone timeZone = getTimeZone();
        try {
            return parseInternal(str, parsePosition);
        } finally {
            setTimeZone(timeZone);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dd, code lost:
        return null;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Date parseInternal(java.lang.String r21, java.text.ParsePosition r22) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.SimpleDateFormat.parseInternal(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private int matchString(String str, int i, int i2, String[] strArr, CalendarBuilder calendarBuilder) {
        int length = strArr.length;
        int i3 = 0;
        int i4 = -1;
        for (int i5 = i2 == 7 ? 1 : 0; i5 < length; i5++) {
            int length2 = strArr[i5].length();
            if (length2 > i3 && str.regionMatches(true, i, strArr[i5], 0, length2)) {
                i4 = i5;
                i3 = length2;
            }
            int i6 = length2 - 1;
            if (strArr[i5].charAt(i6) == '.' && i6 > i3 && str.regionMatches(true, i, strArr[i5], 0, i6)) {
                i4 = i5;
                i3 = i6;
            }
        }
        if (i4 < 0) {
            return -i;
        }
        calendarBuilder.set(i2, i4);
        return i + i3;
    }

    private int matchString(String str, int i, int i2, Map map, CalendarBuilder calendarBuilder) {
        if (map != null) {
            String str2 = null;
            for (String str3 : map.keySet()) {
                int length = str3.length();
                if ((str2 == null || length > str2.length()) && str.regionMatches(true, i, str3, 0, length)) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                calendarBuilder.set(i2, ((Integer) map.get(str2)).intValue());
                return i + str2.length();
            }
        }
        return -i;
    }

    private int matchZoneString(String str, int i, String[] strArr) {
        for (int i2 = 1; i2 <= 4; i2++) {
            String str2 = strArr[i2];
            if (str.regionMatches(true, i, str2, 0, str2.length())) {
                return i2;
            }
        }
        return -1;
    }

    private int subParseZoneString(String str, int i, CalendarBuilder calendarBuilder) {
        if (this.formatData.isZoneStringsSet) {
            return subParseZoneStringFromSymbols(str, i, calendarBuilder);
        }
        return subParseZoneStringFromICU(str, i, calendarBuilder);
    }

    private TimeZoneNames getTimeZoneNames() {
        if (this.timeZoneNames == null) {
            this.timeZoneNames = TimeZoneNames.getInstance(this.locale);
        }
        return this.timeZoneNames;
    }

    private int subParseZoneStringFromICU(String str, int i, CalendarBuilder calendarBuilder) {
        TimeZoneNames.MatchInfo matchInfo;
        String canonicalID = android.icu.util.TimeZone.getCanonicalID(getTimeZone().getID());
        TimeZoneNames timeZoneNames2 = getTimeZoneNames();
        Iterator it = timeZoneNames2.find(str, i, NAME_TYPES).iterator();
        TimeZoneNames.MatchInfo matchInfo2 = null;
        Set set = null;
        while (true) {
            if (!it.hasNext()) {
                matchInfo = matchInfo2;
                break;
            }
            matchInfo = (TimeZoneNames.MatchInfo) it.next();
            if (matchInfo2 != null && matchInfo2.matchLength() >= matchInfo.matchLength()) {
                if (matchInfo2.matchLength() == matchInfo.matchLength()) {
                    if (canonicalID.equals(matchInfo.tzID())) {
                        break;
                    } else if (matchInfo.mzID() == null) {
                        continue;
                    } else {
                        if (set == null) {
                            set = timeZoneNames2.getAvailableMetaZoneIDs(canonicalID);
                        }
                        if (set.contains(matchInfo.mzID())) {
                            break;
                        }
                    }
                } else {
                    continue;
                }
            } else {
                matchInfo2 = matchInfo;
            }
        }
        if (matchInfo == null) {
            return -i;
        }
        String tzID = matchInfo.tzID();
        if (tzID == null) {
            if (set == null) {
                set = timeZoneNames2.getAvailableMetaZoneIDs(canonicalID);
            }
            if (set.contains(matchInfo.mzID())) {
                tzID = canonicalID;
            } else {
                ULocale forLocale = ULocale.forLocale(this.locale);
                String country = forLocale.getCountry();
                if (country.length() == 0) {
                    country = ULocale.addLikelySubtags(forLocale).getCountry();
                }
                tzID = timeZoneNames2.getReferenceZoneID(matchInfo.mzID(), country);
            }
        }
        TimeZone timeZone = TimeZone.getTimeZone(tzID);
        if (!canonicalID.equals(tzID)) {
            setTimeZone(timeZone);
        }
        boolean contains = DST_NAME_TYPES.contains(matchInfo.nameType());
        int dSTSavings = contains ? timeZone.getDSTSavings() : 0;
        if (!contains || dSTSavings != 0) {
            calendarBuilder.clear(15);
            calendarBuilder.set(16, dSTSavings);
        }
        return matchInfo.matchLength() + i;
    }

    private int subParseZoneStringFromSymbols(String str, int i, CalendarBuilder calendarBuilder) {
        String[] strArr;
        int i2;
        boolean z;
        String[] strArr2;
        int i3;
        int zoneIndex;
        TimeZone timeZone = getTimeZone();
        int zoneIndex2 = this.formatData.getZoneIndex(timeZone.getID());
        String[][] zoneStringsWrapper = this.formatData.getZoneStringsWrapper();
        TimeZone timeZone2 = null;
        int i4 = 0;
        if (zoneIndex2 != -1) {
            String[] strArr3 = zoneStringsWrapper[zoneIndex2];
            i2 = matchZoneString(str, i, strArr3);
            if (i2 > 0) {
                boolean equalsIgnoreCase = i2 <= 2 ? strArr3[i2].equalsIgnoreCase(strArr3[i2 + 2]) : false;
                TimeZone timeZone3 = TimeZone.getTimeZone(strArr3[0]);
                strArr = strArr3;
                z = equalsIgnoreCase;
                timeZone2 = timeZone3;
            } else {
                strArr = strArr3;
                z = false;
            }
        } else {
            strArr = null;
            z = false;
            i2 = 0;
        }
        if (timeZone2 == null && (zoneIndex = this.formatData.getZoneIndex(TimeZone.getDefault().getID())) != -1 && (i2 = matchZoneString(str, i, (strArr = zoneStringsWrapper[zoneIndex]))) > 0) {
            if (i2 <= 2) {
                z = strArr[i2].equalsIgnoreCase(strArr[i2 + 2]);
            }
            timeZone2 = TimeZone.getTimeZone(strArr[0]);
        }
        if (timeZone2 == null) {
            int length = zoneStringsWrapper.length;
            strArr2 = strArr;
            i3 = i2;
            int i5 = 0;
            while (true) {
                if (i5 >= length) {
                    break;
                }
                strArr2 = zoneStringsWrapper[i5];
                i3 = matchZoneString(str, i, strArr2);
                if (i3 > 0) {
                    if (i3 <= 2) {
                        z = strArr2[i3].equalsIgnoreCase(strArr2[i3 + 2]);
                    }
                    timeZone2 = TimeZone.getTimeZone(strArr2[0]);
                } else {
                    i5++;
                }
            }
        } else {
            strArr2 = strArr;
            i3 = i2;
        }
        if (timeZone2 == null) {
            return -i;
        }
        if (!timeZone2.equals(timeZone)) {
            setTimeZone(timeZone2);
        }
        if (i3 >= 3) {
            i4 = timeZone2.getDSTSavings();
        }
        if (!z && (i3 < 3 || i4 != 0)) {
            calendarBuilder.clear(15);
            calendarBuilder.set(16, i4);
        }
        return i + strArr2[i3].length();
    }

    private int subParseNumericZone(String str, int i, int i2, int i3, boolean z, CalendarBuilder calendarBuilder) {
        int i4;
        char c;
        int i5 = i + 1;
        try {
            char charAt = str.charAt(i);
            if (isDigit(charAt)) {
                int i6 = charAt - 48;
                int i7 = i5 + 1;
                try {
                    char charAt2 = str.charAt(i5);
                    if (isDigit(charAt2)) {
                        i6 = (i6 * 10) + (charAt2 - 48);
                    } else {
                        i7--;
                    }
                    i5 = i7;
                    if (i6 <= 23) {
                        if (i3 != 1) {
                            int i8 = i5 + 1;
                            try {
                                char charAt3 = str.charAt(i5);
                                if (charAt3 == ':') {
                                    i5 = i8 + 1;
                                    c = str.charAt(i8);
                                } else {
                                    if (!z) {
                                        i5 = i8;
                                        c = charAt3;
                                    }
                                    i5 = i8;
                                }
                                if (isDigit(c)) {
                                    int i9 = c - 48;
                                    int i10 = i5 + 1;
                                    try {
                                        char charAt4 = str.charAt(i5);
                                        if (isDigit(charAt4) && (i4 = (i9 * 10) + (charAt4 - 48)) <= 59) {
                                            i5 = i10;
                                        }
                                    } catch (IndexOutOfBoundsException unused) {
                                    }
                                    i5 = i10;
                                }
                            } catch (IndexOutOfBoundsException unused2) {
                            }
                        } else {
                            i4 = 0;
                        }
                        calendarBuilder.set(15, (i4 + (i6 * 60)) * 60000 * i2);
                        calendarBuilder.set(16, 0);
                        return i5;
                    }
                } catch (IndexOutOfBoundsException unused3) {
                    i5 = i7;
                }
            }
        } catch (IndexOutOfBoundsException unused4) {
        }
        return 1 - i5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:150:0x027c, code lost:
        if (r12 <= 12) goto L_0x0281;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02e0, code lost:
        if (r12 <= 24) goto L_0x02e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0079, code lost:
        if ((r18.calendar instanceof java.util.GregorianCalendar) == false) goto L_0x00b1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0220 A[Catch:{ IndexOutOfBoundsException -> 0x03a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x022e A[Catch:{ IndexOutOfBoundsException -> 0x03a6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int subParse(java.lang.String r19, int r20, int r21, int r22, boolean r23, boolean[] r24, java.text.ParsePosition r25, boolean r26, java.text.CalendarBuilder r27) {
        /*
        // Method dump skipped, instructions count: 972
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.SimpleDateFormat.subParse(java.lang.String, int, int, int, boolean, boolean[], java.text.ParsePosition, boolean, java.text.CalendarBuilder):int");
    }

    private int parseMonth(String str, int i, int i2, int i3, int i4, ParsePosition parsePosition, boolean z, boolean z2, CalendarBuilder calendarBuilder) {
        int i5;
        if (i <= 2) {
            calendarBuilder.set(2, i2 - 1);
            return parsePosition.index;
        }
        if (z) {
            DateFormatSymbols dateFormatSymbols = this.formatData;
            int matchString = matchString(str, i3, 2, z2 ? dateFormatSymbols.getStandAloneMonths() : dateFormatSymbols.getMonths(), calendarBuilder);
            if (matchString > 0) {
                return matchString;
            }
            DateFormatSymbols dateFormatSymbols2 = this.formatData;
            i5 = matchString(str, i3, 2, z2 ? dateFormatSymbols2.getShortStandAloneMonths() : dateFormatSymbols2.getShortMonths(), calendarBuilder);
            if (i5 > 0) {
                return i5;
            }
        } else {
            i5 = matchString(str, i3, i4, getDisplayNamesMap(i4, this.locale), calendarBuilder);
            if (i5 > 0) {
            }
        }
        return i5;
    }

    private int parseWeekday(String str, int i, int i2, boolean z, boolean z2, CalendarBuilder calendarBuilder) {
        int i3;
        if (z) {
            DateFormatSymbols dateFormatSymbols = this.formatData;
            int matchString = matchString(str, i, 7, z2 ? dateFormatSymbols.getStandAloneWeekdays() : dateFormatSymbols.getWeekdays(), calendarBuilder);
            if (matchString > 0) {
                return matchString;
            }
            DateFormatSymbols dateFormatSymbols2 = this.formatData;
            i3 = matchString(str, i, 7, z2 ? dateFormatSymbols2.getShortStandAloneWeekdays() : dateFormatSymbols2.getShortWeekdays(), calendarBuilder);
            if (i3 > 0) {
                return i3;
            }
        } else {
            i3 = -1;
            for (int i4 : new int[]{2, 1}) {
                i3 = matchString(str, i, i2, this.calendar.getDisplayNames(i2, i4, this.locale), calendarBuilder);
                if (i3 > 0) {
                    return i3;
                }
            }
        }
        return i3;
    }

    private boolean useDateFormatSymbols() {
        return this.useDateFormatSymbols || "java.util.GregorianCalendar".equals(this.calendar.getClass().getName()) || this.locale == null;
    }

    @Override // java.text.Format, java.text.DateFormat
    public Object clone() {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) super.clone();
        simpleDateFormat.formatData = (DateFormatSymbols) this.formatData.clone();
        return simpleDateFormat;
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
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) obj;
        if (!this.pattern.equals(simpleDateFormat.pattern) || !this.formatData.equals(simpleDateFormat.formatData)) {
            return false;
        }
        return true;
    }

    private Map getDisplayNamesMap(int i, Locale locale2) {
        Map displayNames = this.calendar.getDisplayNames(i, 1, locale2);
        for (int i2 : REST_OF_STYLES) {
            Map displayNames2 = this.calendar.getDisplayNames(i, i2, locale2);
            if (displayNames2 != null) {
                displayNames.putAll(displayNames2);
            }
        }
        return displayNames;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void checkNegativeNumberExpression() {
        int indexOf;
        NumberFormat numberFormat = this.numberFormat;
        if ((numberFormat instanceof DecimalFormat) && !numberFormat.equals(this.originalNumberFormat)) {
            String pattern2 = ((DecimalFormat) this.numberFormat).toPattern();
            if (!pattern2.equals(this.originalNumberPattern)) {
                this.hasFollowingMinusSign = false;
                int indexOf2 = pattern2.indexOf(59);
                if (indexOf2 > -1 && (indexOf = pattern2.indexOf(45, indexOf2)) > pattern2.lastIndexOf(48) && indexOf > pattern2.lastIndexOf(35)) {
                    this.hasFollowingMinusSign = true;
                    this.minusSign = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols().getMinusSign();
                }
                this.originalNumberPattern = pattern2;
            }
            this.originalNumberFormat = this.numberFormat;
        }
    }
}
