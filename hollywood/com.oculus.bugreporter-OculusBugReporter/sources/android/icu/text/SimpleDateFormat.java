package android.icu.text;

import android.icu.impl.DateNumberFormat;
import android.icu.impl.DayPeriodRules;
import android.icu.impl.ICUCache;
import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.PatternProps;
import android.icu.impl.PatternTokenizer;
import android.icu.impl.SimpleCache;
import android.icu.impl.SimpleFormatterImpl;
import android.icu.lang.UCharacter;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.text.TimeZoneFormat;
import android.icu.util.Calendar;
import android.icu.util.Output;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.UUID;

public class SimpleDateFormat extends DateFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int[] CALENDAR_FIELD_TO_LEVEL = {0, 10, 20, 20, 30, 30, 20, 30, 30, 40, 50, 50, 60, 70, 80, 0, 0, 10, 30, 10, 0, 40, 0, 0};
    static final UnicodeSet DATE_PATTERN_TYPE = new UnicodeSet("[GyYuUQqMLlwWd]").freeze();
    private static final int DECIMAL_BUF_SIZE = 10;
    static boolean DelayedHebrewMonthCheck = false;
    private static final String FALLBACKPATTERN = "yy/MM/dd HH:mm";
    private static final int HEBREW_CAL_CUR_MILLENIUM_END_YEAR = 6000;
    private static final int HEBREW_CAL_CUR_MILLENIUM_START_YEAR = 5000;
    private static final int ISOSpecialEra = -32000;
    private static final String NUMERIC_FORMAT_CHARS = "ADdFgHhKkmrSsuWwYy";
    private static final String NUMERIC_FORMAT_CHARS2 = "ceLMQq";
    private static ICUCache<String, Object[]> PARSED_PATTERN_CACHE = new SimpleCache();
    private static final boolean[] PATTERN_CHAR_IS_SYNTAX = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false};
    private static final int[] PATTERN_CHAR_TO_INDEX = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, 36, -1, 10, 9, 11, 0, 5, -1, -1, 16, 26, 2, -1, 31, -1, 27, -1, 8, -1, 30, 29, 13, 32, 18, 23, -1, -1, -1, -1, -1, -1, 14, 35, 25, 3, 19, -1, 21, 15, -1, -1, 4, -1, 6, -1, -1, -1, 28, 34, 7, -1, 20, 24, 12, 33, 1, 17, -1, -1, -1, -1, -1};
    private static final int[] PATTERN_CHAR_TO_LEVEL = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, 20, 30, 30, 0, 50, -1, -1, 50, 20, 20, -1, 0, -1, 20, -1, 80, -1, 10, 0, 30, 0, 10, 0, -1, -1, -1, -1, -1, -1, 40, -1, 30, 30, 30, -1, 0, 50, -1, -1, 50, -1, 60, -1, -1, -1, 20, 10, 70, -1, 10, 0, 20, 0, 10, 0, -1, -1, -1, -1, -1};
    private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = {0, 1, 2, 5, 11, 11, 12, 13, 14, 7, 6, 8, 3, 4, 9, 10, 10, 15, 17, 18, 19, 20, 21, 15, 15, 18, 2, 2, 2, 15, 1, 15, 15, 15, 19, -1, -2};
    private static final DateFormat.Field[] PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE = {DateFormat.Field.ERA, DateFormat.Field.YEAR, DateFormat.Field.MONTH, DateFormat.Field.DAY_OF_MONTH, DateFormat.Field.HOUR_OF_DAY1, DateFormat.Field.HOUR_OF_DAY0, DateFormat.Field.MINUTE, DateFormat.Field.SECOND, DateFormat.Field.MILLISECOND, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.DAY_OF_YEAR, DateFormat.Field.DAY_OF_WEEK_IN_MONTH, DateFormat.Field.WEEK_OF_YEAR, DateFormat.Field.WEEK_OF_MONTH, DateFormat.Field.AM_PM, DateFormat.Field.HOUR1, DateFormat.Field.HOUR0, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR_WOY, DateFormat.Field.DOW_LOCAL, DateFormat.Field.EXTENDED_YEAR, DateFormat.Field.JULIAN_DAY, DateFormat.Field.MILLISECONDS_IN_DAY, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.DAY_OF_WEEK, DateFormat.Field.MONTH, DateFormat.Field.QUARTER, DateFormat.Field.QUARTER, DateFormat.Field.TIME_ZONE, DateFormat.Field.YEAR, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.TIME_ZONE, DateFormat.Field.RELATED_YEAR, DateFormat.Field.AM_PM_MIDNIGHT_NOON, DateFormat.Field.FLEXIBLE_DAY_PERIOD, DateFormat.Field.TIME_SEPARATOR};
    private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
    private static final String SUPPRESS_NEGATIVE_PREFIX = "꬀";
    private static ULocale cachedDefaultLocale = null;
    private static String cachedDefaultPattern = null;
    static final int currentSerialVersion = 2;
    private static final int millisPerHour = 3600000;
    private static final long serialVersionUID = 4774881970558875024L;
    private transient BreakIterator capitalizationBrkIter;
    private transient char[] decDigits;
    private transient char[] decimalBuf;
    private transient long defaultCenturyBase;
    private Date defaultCenturyStart;
    private transient int defaultCenturyStartYear;
    private DateFormatSymbols formatData;
    private transient boolean hasMinute;
    private transient boolean hasSecond;
    private transient ULocale locale;
    private HashMap<String, NumberFormat> numberFormatters;
    private String override;
    private HashMap<Character, String> overrideMap;
    private String pattern;
    private transient Object[] patternItems;
    private int serialVersionOnStream;
    private volatile TimeZoneFormat tzFormat;
    private transient boolean useFastFormat;
    private transient boolean useLocalZeroPaddingNumberFormat;

    private enum ContextValue {
        UNKNOWN,
        CAPITALIZATION_FOR_MIDDLE_OF_SENTENCE,
        CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE,
        CAPITALIZATION_FOR_UI_LIST_OR_MENU,
        CAPITALIZATION_FOR_STANDALONE
    }

    private static int getLevelFromChar(char ch) {
        int[] iArr = PATTERN_CHAR_TO_LEVEL;
        if (ch < iArr.length) {
            return iArr[ch & 255];
        }
        return -1;
    }

    private static boolean isSyntaxChar(char ch) {
        boolean[] zArr = PATTERN_CHAR_IS_SYNTAX;
        if (ch < zArr.length) {
            return zArr[ch & 255];
        }
        return false;
    }

    public SimpleDateFormat() {
        this(getDefaultPattern(), null, null, null, null, true, null);
    }

    public SimpleDateFormat(String pattern2) {
        this(pattern2, null, null, null, null, true, null);
    }

    public SimpleDateFormat(String pattern2, Locale loc) {
        this(pattern2, null, null, null, ULocale.forLocale(loc), true, null);
    }

    public SimpleDateFormat(String pattern2, ULocale loc) {
        this(pattern2, null, null, null, loc, true, null);
    }

    public SimpleDateFormat(String pattern2, String override2, ULocale loc) {
        this(pattern2, null, null, null, loc, false, override2);
    }

    public SimpleDateFormat(String pattern2, DateFormatSymbols formatData2) {
        this(pattern2, (DateFormatSymbols) formatData2.clone(), null, null, null, true, null);
    }

    @Deprecated
    public SimpleDateFormat(String pattern2, DateFormatSymbols formatData2, ULocale loc) {
        this(pattern2, (DateFormatSymbols) formatData2.clone(), null, null, loc, true, null);
    }

    SimpleDateFormat(String pattern2, DateFormatSymbols formatData2, Calendar calendar, ULocale locale2, boolean useFastFormat2, String override2) {
        this(pattern2, (DateFormatSymbols) formatData2.clone(), (Calendar) calendar.clone(), null, locale2, useFastFormat2, override2);
    }

    private SimpleDateFormat(String pattern2, DateFormatSymbols formatData2, Calendar calendar, NumberFormat numberFormat, ULocale locale2, boolean useFastFormat2, String override2) {
        this.serialVersionOnStream = 2;
        this.capitalizationBrkIter = null;
        this.pattern = pattern2;
        this.formatData = formatData2;
        this.calendar = calendar;
        this.numberFormat = numberFormat;
        this.locale = locale2;
        this.useFastFormat = useFastFormat2;
        this.override = override2;
        initialize();
    }

    @Deprecated
    public static SimpleDateFormat getInstance(Calendar.FormatConfiguration formatConfig) {
        String ostr = formatConfig.getOverrideString();
        return new SimpleDateFormat(formatConfig.getPatternString(), formatConfig.getDateFormatSymbols(), formatConfig.getCalendar(), null, formatConfig.getLocale(), ostr != null && ostr.length() > 0, formatConfig.getOverrideString());
    }

    private void initialize() {
        if (this.locale == null) {
            this.locale = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        if (this.formatData == null) {
            this.formatData = new DateFormatSymbols(this.locale);
        }
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance(this.locale);
        }
        if (this.numberFormat == null) {
            NumberingSystem ns = NumberingSystem.getInstance(this.locale);
            String digitString = ns.getDescription();
            if (ns.isAlgorithmic() || digitString.length() != 10) {
                this.numberFormat = NumberFormat.getInstance(this.locale);
            } else {
                this.numberFormat = new DateNumberFormat(this.locale, digitString, ns.getName());
            }
        }
        if (this.numberFormat instanceof DecimalFormat) {
            fixNumberFormatForDates(this.numberFormat);
        }
        this.defaultCenturyBase = System.currentTimeMillis();
        setLocale(this.calendar.getLocale(ULocale.VALID_LOCALE), this.calendar.getLocale(ULocale.ACTUAL_LOCALE));
        initLocalZeroPaddingNumberFormat();
        if (this.override != null) {
            initNumberFormatters(this.locale);
        }
        parsePattern();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        if (r7.tzFormat == null) goto L_0x0007;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void initializeTimeZoneFormat(boolean r8) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.SimpleDateFormat.initializeTimeZoneFormat(boolean):void");
    }

    private TimeZoneFormat tzFormat() {
        if (this.tzFormat == null) {
            initializeTimeZoneFormat(false);
        }
        return this.tzFormat;
    }

    private static synchronized String getDefaultPattern() {
        String str;
        synchronized (SimpleDateFormat.class) {
            ULocale defaultLocale = ULocale.getDefault(ULocale.Category.FORMAT);
            if (!defaultLocale.equals(cachedDefaultLocale)) {
                cachedDefaultLocale = defaultLocale;
                Calendar cal = Calendar.getInstance(cachedDefaultLocale);
                try {
                    ICUResourceBundle rb = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, cachedDefaultLocale);
                    ICUResourceBundle patternsRb = rb.findWithFallback("calendar/" + cal.getType() + "/DateTimePatterns");
                    if (patternsRb == null) {
                        patternsRb = rb.findWithFallback("calendar/gregorian/DateTimePatterns");
                    }
                    if (patternsRb != null) {
                        if (patternsRb.getSize() >= 9) {
                            int defaultIndex = 8;
                            if (patternsRb.getSize() >= 13) {
                                defaultIndex = 8 + 4;
                            }
                            cachedDefaultPattern = SimpleFormatterImpl.formatRawPattern(patternsRb.getString(defaultIndex), 2, 2, patternsRb.getString(3), patternsRb.getString(7));
                        }
                    }
                    cachedDefaultPattern = FALLBACKPATTERN;
                } catch (MissingResourceException e) {
                    cachedDefaultPattern = FALLBACKPATTERN;
                }
            }
            str = cachedDefaultPattern;
        }
        return str;
    }

    private void parseAmbiguousDatesAsAfter(Date startDate) {
        this.defaultCenturyStart = startDate;
        this.calendar.setTime(startDate);
        this.defaultCenturyStartYear = this.calendar.get(1);
    }

    private void initializeDefaultCenturyStart(long baseTime) {
        this.defaultCenturyBase = baseTime;
        Calendar tmpCal = (Calendar) this.calendar.clone();
        tmpCal.setTimeInMillis(baseTime);
        tmpCal.add(1, -80);
        this.defaultCenturyStart = tmpCal.getTime();
        this.defaultCenturyStartYear = tmpCal.get(1);
    }

    private Date getDefaultCenturyStart() {
        if (this.defaultCenturyStart == null) {
            initializeDefaultCenturyStart(this.defaultCenturyBase);
        }
        return this.defaultCenturyStart;
    }

    private int getDefaultCenturyStartYear() {
        if (this.defaultCenturyStart == null) {
            initializeDefaultCenturyStart(this.defaultCenturyBase);
        }
        return this.defaultCenturyStartYear;
    }

    public void set2DigitYearStart(Date startDate) {
        parseAmbiguousDatesAsAfter(startDate);
    }

    public Date get2DigitYearStart() {
        return getDefaultCenturyStart();
    }

    @Override // android.icu.text.DateFormat
    public void setContext(DisplayContext context) {
        super.setContext(context);
        if (this.capitalizationBrkIter != null) {
            return;
        }
        if (context == DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE || context == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU || context == DisplayContext.CAPITALIZATION_FOR_STANDALONE) {
            this.capitalizationBrkIter = BreakIterator.getSentenceInstance(this.locale);
        }
    }

    @Override // android.icu.text.DateFormat
    public StringBuffer format(Calendar cal, StringBuffer toAppendTo, FieldPosition pos) {
        TimeZone backupTZ = null;
        if (cal != this.calendar && !cal.getType().equals(this.calendar.getType())) {
            this.calendar.setTimeInMillis(cal.getTimeInMillis());
            backupTZ = this.calendar.getTimeZone();
            this.calendar.setTimeZone(cal.getTimeZone());
            cal = this.calendar;
        }
        StringBuffer result = format(cal, getContext(DisplayContext.Type.CAPITALIZATION), toAppendTo, pos, null);
        if (backupTZ != null) {
            this.calendar.setTimeZone(backupTZ);
        }
        return result;
    }

    private StringBuffer format(Calendar cal, DisplayContext capitalizationContext, StringBuffer toAppendTo, FieldPosition pos, List<FieldPosition> attributes) {
        int start;
        pos.setBeginIndex(0);
        pos.setEndIndex(0);
        Object[] items = getPatternItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof String) {
                toAppendTo.append((String) items[i]);
            } else {
                PatternItem item = (PatternItem) items[i];
                int start2 = attributes != null ? toAppendTo.length() : 0;
                if (this.useFastFormat) {
                    start = start2;
                    subFormat(toAppendTo, item.type, item.length, toAppendTo.length(), i, capitalizationContext, pos, cal);
                } else {
                    start = start2;
                    toAppendTo.append(subFormat(item.type, item.length, toAppendTo.length(), i, capitalizationContext, pos, cal));
                }
                if (attributes != null) {
                    int end = toAppendTo.length();
                    if (end - start > 0) {
                        FieldPosition fp = new FieldPosition(patternCharToDateFormatField(item.type));
                        fp.setBeginIndex(start);
                        fp.setEndIndex(end);
                        attributes.add(fp);
                    }
                }
            }
        }
        return toAppendTo;
    }

    private static int getIndexFromChar(char ch) {
        int[] iArr = PATTERN_CHAR_TO_INDEX;
        if (ch < iArr.length) {
            return iArr[ch & 255];
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public DateFormat.Field patternCharToDateFormatField(char ch) {
        int patternCharIndex = getIndexFromChar(ch);
        if (patternCharIndex != -1) {
            return PATTERN_INDEX_TO_DATE_FORMAT_ATTRIBUTE[patternCharIndex];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String subFormat(char ch, int count, int beginOffset, FieldPosition pos, DateFormatSymbols fmtData, Calendar cal) throws IllegalArgumentException {
        return subFormat(ch, count, beginOffset, 0, DisplayContext.CAPITALIZATION_NONE, pos, cal);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public String subFormat(char ch, int count, int beginOffset, int fieldNum, DisplayContext capitalizationContext, FieldPosition pos, Calendar cal) {
        StringBuffer buf = new StringBuffer();
        subFormat(buf, ch, count, beginOffset, fieldNum, capitalizationContext, pos, cal);
        return buf.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x0615  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x0625  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x083b  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0849  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subFormat(java.lang.StringBuffer r34, char r35, int r36, int r37, int r38, android.icu.text.DisplayContext r39, java.text.FieldPosition r40, android.icu.util.Calendar r41) {
        /*
        // Method dump skipped, instructions count: 2480
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.SimpleDateFormat.subFormat(java.lang.StringBuffer, char, int, int, int, android.icu.text.DisplayContext, java.text.FieldPosition, android.icu.util.Calendar):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.text.SimpleDateFormat$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$DisplayContext = new int[DisplayContext.values().length];

        static {
            try {
                $SwitchMap$android$icu$text$DisplayContext[DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$text$DisplayContext[DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$text$DisplayContext[DisplayContext.CAPITALIZATION_FOR_STANDALONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static void safeAppend(String[] array, int value, StringBuffer appendTo) {
        if (array != null && value >= 0 && value < array.length) {
            appendTo.append(array[value]);
        }
    }

    private static void safeAppendWithMonthPattern(String[] array, int value, StringBuffer appendTo, String monthPattern) {
        if (array != null && value >= 0 && value < array.length) {
            if (monthPattern == null) {
                appendTo.append(array[value]);
                return;
            }
            appendTo.append(SimpleFormatterImpl.formatRawPattern(monthPattern, 1, 1, array[value]));
        }
    }

    /* access modifiers changed from: private */
    public static class PatternItem {
        final boolean isNumeric;
        final int length;
        final char type;

        PatternItem(char type2, int length2) {
            this.type = type2;
            this.length = length2;
            this.isNumeric = SimpleDateFormat.isNumeric(type2, length2);
        }
    }

    private Object[] getPatternItems() {
        Object[] objArr = this.patternItems;
        if (objArr != null) {
            return objArr;
        }
        this.patternItems = PARSED_PATTERN_CACHE.get(this.pattern);
        Object[] objArr2 = this.patternItems;
        if (objArr2 != null) {
            return objArr2;
        }
        boolean isPrevQuote = false;
        boolean inQuote = false;
        StringBuilder text = new StringBuilder();
        char itemType = 0;
        int itemLength = 1;
        List<Object> items = new ArrayList<>();
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= this.pattern.length()) {
                break;
            }
            char ch = this.pattern.charAt(i);
            if (ch == '\'') {
                if (isPrevQuote) {
                    text.append(PatternTokenizer.SINGLE_QUOTE);
                    isPrevQuote = false;
                } else {
                    isPrevQuote = true;
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                        itemType = 0;
                    }
                }
                if (!inQuote) {
                    z = true;
                }
                inQuote = z;
            } else {
                isPrevQuote = false;
                if (inQuote) {
                    text.append(ch);
                } else if (!isSyntaxChar(ch)) {
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                        itemType = 0;
                    }
                    text.append(ch);
                } else if (ch == itemType) {
                    itemLength++;
                } else {
                    if (itemType != 0) {
                        items.add(new PatternItem(itemType, itemLength));
                    } else if (text.length() > 0) {
                        items.add(text.toString());
                        text.setLength(0);
                    }
                    itemType = ch;
                    itemLength = 1;
                }
            }
            i++;
        }
        if (itemType != 0) {
            items.add(new PatternItem(itemType, itemLength));
        } else if (text.length() > 0) {
            items.add(text.toString());
            text.setLength(0);
        }
        this.patternItems = items.toArray(new Object[items.size()]);
        PARSED_PATTERN_CACHE.put(this.pattern, this.patternItems);
        return this.patternItems;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void zeroPaddingNumber(NumberFormat nf, StringBuffer buf, int value, int minDigits, int maxDigits) {
        if (!this.useLocalZeroPaddingNumberFormat || value < 0) {
            nf.setMinimumIntegerDigits(minDigits);
            nf.setMaximumIntegerDigits(maxDigits);
            nf.format((long) value, buf, new FieldPosition(-1));
            return;
        }
        fastZeroPaddingNumber(buf, value, minDigits, maxDigits);
    }

    @Override // android.icu.text.DateFormat
    public void setNumberFormat(NumberFormat newNumberFormat) {
        super.setNumberFormat(newNumberFormat);
        initLocalZeroPaddingNumberFormat();
        initializeTimeZoneFormat(true);
        if (this.numberFormatters != null) {
            this.numberFormatters = null;
        }
        if (this.overrideMap != null) {
            this.overrideMap = null;
        }
    }

    private void initLocalZeroPaddingNumberFormat() {
        if (this.numberFormat instanceof DecimalFormat) {
            String[] tmpDigits = ((DecimalFormat) this.numberFormat).getDecimalFormatSymbols().getDigitStringsLocal();
            this.useLocalZeroPaddingNumberFormat = true;
            this.decDigits = new char[10];
            int i = 0;
            while (true) {
                if (i >= 10) {
                    break;
                } else if (tmpDigits[i].length() > 1) {
                    this.useLocalZeroPaddingNumberFormat = false;
                    break;
                } else {
                    this.decDigits[i] = tmpDigits[i].charAt(0);
                    i++;
                }
            }
        } else if (this.numberFormat instanceof DateNumberFormat) {
            this.decDigits = ((DateNumberFormat) this.numberFormat).getDigits();
            this.useLocalZeroPaddingNumberFormat = true;
        } else {
            this.useLocalZeroPaddingNumberFormat = false;
        }
        if (this.useLocalZeroPaddingNumberFormat) {
            this.decimalBuf = new char[10];
        }
    }

    private void fastZeroPaddingNumber(StringBuffer buf, int value, int minDigits, int maxDigits) {
        char[] cArr = this.decimalBuf;
        int limit = cArr.length < maxDigits ? cArr.length : maxDigits;
        int index = limit - 1;
        while (true) {
            this.decimalBuf[index] = this.decDigits[value % 10];
            value /= 10;
            if (index == 0 || value == 0) {
                int padding = minDigits - (limit - index);
            } else {
                index--;
            }
        }
        int padding2 = minDigits - (limit - index);
        while (padding2 > 0 && index > 0) {
            index--;
            this.decimalBuf[index] = this.decDigits[0];
            padding2--;
        }
        while (padding2 > 0) {
            buf.append(this.decDigits[0]);
            padding2--;
        }
        buf.append(this.decimalBuf, index, limit - index);
    }

    /* access modifiers changed from: protected */
    public String zeroPaddingNumber(long value, int minDigits, int maxDigits) {
        this.numberFormat.setMinimumIntegerDigits(minDigits);
        this.numberFormat.setMaximumIntegerDigits(maxDigits);
        return this.numberFormat.format(value);
    }

    /* access modifiers changed from: private */
    public static final boolean isNumeric(char formatChar, int count) {
        return NUMERIC_FORMAT_CHARS.indexOf(formatChar) >= 0 || (count <= 2 && NUMERIC_FORMAT_CHARS2.indexOf(formatChar) >= 0);
    }

    /* JADX INFO: Multiple debug info for r1v10 'localMillis'  long: [D('numericFieldStart' int), D('localMillis' long)] */
    /* JADX INFO: Multiple debug info for r8v14 'start'  int: [D('start' int), D('dayPeriod' android.icu.util.Output<android.icu.impl.DayPeriodRules$DayPeriod>)] */
    /* JADX INFO: Multiple debug info for r7v22 'items'  java.lang.Object[]: [D('tzTimeType' android.icu.util.Output<android.icu.text.TimeZoneFormat$TimeType>), D('items' java.lang.Object[])] */
    /* JADX INFO: Multiple debug info for r15v6 'backupTZ'  android.icu.util.TimeZone: [D('cal' android.icu.util.Calendar), D('backupTZ' android.icu.util.TimeZone)] */
    /* JADX INFO: Multiple debug info for r15v9 'backupTZ'  android.icu.util.TimeZone: [D('cal' android.icu.util.Calendar), D('backupTZ' android.icu.util.TimeZone)] */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03ec, code lost:
        if (r12[1] == 0) goto L_0x03ee;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0327 A[SYNTHETIC, Splitter:B:138:0x0327] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x036a A[SYNTHETIC, Splitter:B:151:0x036a] */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x04e0  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x04ee  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x04fe  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0506  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x051f  */
    /* JADX WARNING: Removed duplicated region for block: B:265:? A[RETURN, SYNTHETIC] */
    @Override // android.icu.text.DateFormat
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parse(java.lang.String r41, android.icu.util.Calendar r42, java.text.ParsePosition r43) {
        /*
        // Method dump skipped, instructions count: 1317
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.SimpleDateFormat.parse(java.lang.String, android.icu.util.Calendar, java.text.ParsePosition):void");
    }

    private int matchLiteral(String text, int pos, Object[] items, int itemIndex, boolean[] complete) {
        String patternLiteral = (String) items[itemIndex];
        int plen = patternLiteral.length();
        int tlen = text.length();
        int idx = 0;
        int pos2 = pos;
        while (idx < plen && pos2 < tlen) {
            char pch = patternLiteral.charAt(idx);
            char ich = text.charAt(pos2);
            if (!PatternProps.isWhiteSpace(pch) || !PatternProps.isWhiteSpace(ich)) {
                if (pch != ich) {
                    if (ich != '.' || pos2 != pos || itemIndex <= 0 || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE)) {
                        if ((pch != ' ' && pch != '.') || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE)) {
                            if (pos2 == pos || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH)) {
                                break;
                            }
                            idx++;
                        } else {
                            idx++;
                        }
                    } else {
                        Object before = items[itemIndex - 1];
                        if (!(before instanceof PatternItem) || ((PatternItem) before).isNumeric) {
                            break;
                        }
                        pos2++;
                    }
                }
            } else {
                while (idx + 1 < plen && PatternProps.isWhiteSpace(patternLiteral.charAt(idx + 1))) {
                    idx++;
                }
                while (pos2 + 1 < tlen && PatternProps.isWhiteSpace(text.charAt(pos2 + 1))) {
                    pos2++;
                }
            }
            idx++;
            pos2++;
        }
        complete[0] = idx == plen;
        if (complete[0] || !getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_ALLOW_WHITESPACE) || itemIndex <= 0 || itemIndex >= items.length - 1 || pos >= tlen) {
            return pos2;
        }
        Object before2 = items[itemIndex - 1];
        Object after = items[itemIndex + 1];
        if (!(before2 instanceof PatternItem) || !(after instanceof PatternItem)) {
            return pos2;
        }
        if (DATE_PATTERN_TYPE.contains(((PatternItem) before2).type) == DATE_PATTERN_TYPE.contains(((PatternItem) after).type)) {
            return pos2;
        }
        int newPos = pos;
        while (newPos < tlen && PatternProps.isWhiteSpace(text.charAt(newPos))) {
            newPos++;
        }
        complete[0] = newPos > pos;
        return newPos;
    }

    /* access modifiers changed from: protected */
    public int matchString(String text, int start, int field, String[] data, Calendar cal) {
        return matchString(text, start, field, data, null, cal);
    }

    @Deprecated
    private int matchString(String text, int start, int field, String[] data, String monthPattern, Calendar cal) {
        String leapMonthName;
        int length;
        int matchLength;
        int matchLength2;
        int i = 0;
        int count = data.length;
        if (field == 7) {
            i = 1;
        }
        int bestMatchLength = 0;
        int bestMatch = -1;
        int isLeapMonth = 0;
        while (i < count) {
            int length2 = data[i].length();
            if (length2 > bestMatchLength && (matchLength2 = regionMatchesWithOptionalDot(text, start, data[i], length2)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength2;
                isLeapMonth = 0;
            }
            if (monthPattern != null && (length = (leapMonthName = SimpleFormatterImpl.formatRawPattern(monthPattern, 1, 1, data[i])).length()) > bestMatchLength && (matchLength = regionMatchesWithOptionalDot(text, start, leapMonthName, length)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength;
                isLeapMonth = 1;
            }
            i++;
        }
        if (bestMatch < 0) {
            return ~start;
        }
        if (field >= 0) {
            if (field == 1) {
                bestMatch++;
            }
            cal.set(field, bestMatch);
            if (monthPattern != null) {
                cal.set(22, isLeapMonth);
            }
        }
        return start + bestMatchLength;
    }

    private int regionMatchesWithOptionalDot(String text, int start, String data, int length) {
        if (text.regionMatches(true, start, data, 0, length)) {
            return length;
        }
        if (data.length() <= 0 || data.charAt(data.length() - 1) != '.' || !text.regionMatches(true, start, data, 0, length - 1)) {
            return -1;
        }
        return length - 1;
    }

    /* access modifiers changed from: protected */
    public int matchQuarterString(String text, int start, int field, String[] data, Calendar cal) {
        int matchLength;
        int count = data.length;
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i = 0; i < count; i++) {
            int length = data[i].length();
            if (length > bestMatchLength && (matchLength = regionMatchesWithOptionalDot(text, start, data[i], length)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength;
            }
        }
        if (bestMatch < 0) {
            return -start;
        }
        cal.set(field, bestMatch * 3);
        return start + bestMatchLength;
    }

    private int matchDayPeriodString(String text, int start, String[] data, int dataLength, Output<DayPeriodRules.DayPeriod> dayPeriod) {
        int length;
        int matchLength;
        int bestMatchLength = 0;
        int bestMatch = -1;
        for (int i = 0; i < dataLength; i++) {
            if (data[i] != null && (length = data[i].length()) > bestMatchLength && (matchLength = regionMatchesWithOptionalDot(text, start, data[i], length)) >= 0) {
                bestMatch = i;
                bestMatchLength = matchLength;
            }
        }
        if (bestMatch < 0) {
            return -start;
        }
        dayPeriod.value = (T) DayPeriodRules.DayPeriod.VALUES[bestMatch];
        return start + bestMatchLength;
    }

    /* access modifiers changed from: protected */
    public int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal) {
        return subParse(text, start, ch, count, obeyCount, allowNegative, ambiguousYear, cal, null, null);
    }

    private int subParse(String text, int start, char ch, int count, boolean obeyCount, boolean allowNegative, boolean[] ambiguousYear, Calendar cal, MessageFormat numericLeapMonthFormatter, Output<TimeZoneFormat.TimeType> output) {
        return subParse(text, start, ch, count, obeyCount, allowNegative, ambiguousYear, cal, null, null, null);
    }

    /* JADX INFO: Multiple debug info for r7v8 int: [D('start' int), D('value' int)] */
    /* JADX INFO: Multiple debug info for r5v61 'currentNumberFormat'  android.icu.text.NumberFormat: [D('patternCharIndex' int), D('currentNumberFormat' android.icu.text.NumberFormat)] */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x039f, code lost:
        if (r5 > r33.formatData.shortYearNames.length) goto L_0x03a4;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int subParse(java.lang.String r34, int r35, char r36, int r37, boolean r38, boolean r39, boolean[] r40, android.icu.util.Calendar r41, android.icu.text.MessageFormat r42, android.icu.util.Output<android.icu.text.TimeZoneFormat.TimeType> r43, android.icu.util.Output<android.icu.impl.DayPeriodRules.DayPeriod> r44) {
        /*
        // Method dump skipped, instructions count: 2528
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.SimpleDateFormat.subParse(java.lang.String, int, char, int, boolean, boolean, boolean[], android.icu.util.Calendar, android.icu.text.MessageFormat, android.icu.util.Output, android.icu.util.Output):int");
    }

    private boolean allowNumericFallback(int patternCharIndex) {
        if (patternCharIndex == 26 || patternCharIndex == 19 || patternCharIndex == 25 || patternCharIndex == 30 || patternCharIndex == 27 || patternCharIndex == 28) {
            return true;
        }
        return false;
    }

    private Number parseInt(String text, ParsePosition pos, boolean allowNegative, NumberFormat fmt) {
        return parseInt(text, -1, pos, allowNegative, fmt);
    }

    private Number parseInt(String text, int maxDigits, ParsePosition pos, boolean allowNegative, NumberFormat fmt) {
        Number number;
        int nDigits;
        int oldPos = pos.getIndex();
        if (allowNegative) {
            number = fmt.parse(text, pos);
        } else if (fmt instanceof DecimalFormat) {
            String oldPrefix = ((DecimalFormat) fmt).getNegativePrefix();
            ((DecimalFormat) fmt).setNegativePrefix(SUPPRESS_NEGATIVE_PREFIX);
            number = fmt.parse(text, pos);
            ((DecimalFormat) fmt).setNegativePrefix(oldPrefix);
        } else {
            boolean dateNumberFormat = fmt instanceof DateNumberFormat;
            if (dateNumberFormat) {
                ((DateNumberFormat) fmt).setParsePositiveOnly(true);
            }
            number = fmt.parse(text, pos);
            if (dateNumberFormat) {
                ((DateNumberFormat) fmt).setParsePositiveOnly(false);
            }
        }
        if (maxDigits <= 0 || (nDigits = pos.getIndex() - oldPos) <= maxDigits) {
            return number;
        }
        double val = number.doubleValue();
        for (int nDigits2 = nDigits - maxDigits; nDigits2 > 0; nDigits2--) {
            val /= 10.0d;
        }
        pos.setIndex(oldPos + maxDigits);
        return Integer.valueOf((int) val);
    }

    private static int countDigits(String text, int start, int end) {
        int numDigits = 0;
        int idx = start;
        while (idx < end) {
            int cp = text.codePointAt(idx);
            if (UCharacter.isDigit(cp)) {
                numDigits++;
            }
            idx += UCharacter.charCount(cp);
        }
        return numDigits;
    }

    private String translatePattern(String pat, String from, String to) {
        int ci;
        StringBuilder result = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < pat.length(); i++) {
            char c = pat.charAt(i);
            if (inQuote) {
                if (c == '\'') {
                    inQuote = false;
                }
            } else if (c == '\'') {
                inQuote = true;
            } else if (isSyntaxChar(c) && (ci = from.indexOf(c)) != -1) {
                c = to.charAt(ci);
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
        return translatePattern(this.pattern, "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB", this.formatData.localPatternChars);
    }

    public void applyPattern(String pat) {
        this.pattern = pat;
        parsePattern();
        setLocale(null, null);
        this.patternItems = null;
    }

    public void applyLocalizedPattern(String pat) {
        this.pattern = translatePattern(pat, this.formatData.localPatternChars, "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB");
        setLocale(null, null);
    }

    public DateFormatSymbols getDateFormatSymbols() {
        return (DateFormatSymbols) this.formatData.clone();
    }

    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        this.formatData = (DateFormatSymbols) newFormatSymbols.clone();
    }

    /* access modifiers changed from: protected */
    public DateFormatSymbols getSymbols() {
        return this.formatData;
    }

    public TimeZoneFormat getTimeZoneFormat() {
        return tzFormat().freeze();
    }

    public void setTimeZoneFormat(TimeZoneFormat tzfmt) {
        if (tzfmt.isFrozen()) {
            this.tzFormat = tzfmt;
        } else {
            this.tzFormat = tzfmt.cloneAsThawed().freeze();
        }
    }

    @Override // java.text.Format, android.icu.text.DateFormat
    public Object clone() {
        SimpleDateFormat other = (SimpleDateFormat) super.clone();
        other.formatData = (DateFormatSymbols) this.formatData.clone();
        if (this.decimalBuf != null) {
            other.decimalBuf = new char[10];
        }
        return other;
    }

    @Override // android.icu.text.DateFormat
    public int hashCode() {
        return this.pattern.hashCode();
    }

    @Override // android.icu.text.DateFormat
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

    private void writeObject(ObjectOutputStream stream) throws IOException {
        if (this.defaultCenturyStart == null) {
            initializeDefaultCenturyStart(this.defaultCenturyBase);
        }
        initializeTimeZoneFormat(false);
        stream.defaultWriteObject();
        stream.writeInt(getContext(DisplayContext.Type.CAPITALIZATION).value());
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int capitalizationSettingValue = this.serialVersionOnStream > 1 ? stream.readInt() : -1;
        if (this.serialVersionOnStream < 1) {
            this.defaultCenturyBase = System.currentTimeMillis();
        } else {
            parseAmbiguousDatesAsAfter(this.defaultCenturyStart);
        }
        this.serialVersionOnStream = 2;
        this.locale = getLocale(ULocale.VALID_LOCALE);
        if (this.locale == null) {
            this.locale = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        initLocalZeroPaddingNumberFormat();
        setContext(DisplayContext.CAPITALIZATION_NONE);
        if (capitalizationSettingValue >= 0) {
            DisplayContext[] values = DisplayContext.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                DisplayContext context = values[i];
                if (context.value() == capitalizationSettingValue) {
                    setContext(context);
                    break;
                }
                i++;
            }
        }
        if (!getBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_MATCH)) {
            setBooleanAttribute(DateFormat.BooleanAttribute.PARSE_PARTIAL_LITERAL_MATCH, false);
        }
        parsePattern();
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        Calendar cal = this.calendar;
        if (obj instanceof Calendar) {
            cal = (Calendar) obj;
        } else if (obj instanceof Date) {
            this.calendar.setTime((Date) obj);
        } else if (obj instanceof Number) {
            this.calendar.setTimeInMillis(((Number) obj).longValue());
        } else {
            throw new IllegalArgumentException("Cannot format given Object as a Date");
        }
        StringBuffer toAppendTo = new StringBuffer();
        FieldPosition pos = new FieldPosition(0);
        List<FieldPosition> attributes = new ArrayList<>();
        format(cal, getContext(DisplayContext.Type.CAPITALIZATION), toAppendTo, pos, attributes);
        AttributedString as = new AttributedString(toAppendTo.toString());
        for (int i = 0; i < attributes.size(); i++) {
            FieldPosition fp = attributes.get(i);
            Format.Field attribute = fp.getFieldAttribute();
            as.addAttribute(attribute, attribute, fp.getBeginIndex(), fp.getEndIndex());
        }
        return as.getIterator();
    }

    /* access modifiers changed from: package-private */
    public ULocale getLocale() {
        return this.locale;
    }

    /* access modifiers changed from: package-private */
    public boolean isFieldUnitIgnored(int field) {
        return isFieldUnitIgnored(this.pattern, field);
    }

    static boolean isFieldUnitIgnored(String pattern2, int field) {
        int fieldLevel = CALENDAR_FIELD_TO_LEVEL[field];
        boolean inQuote = false;
        char prevCh = 0;
        int count = 0;
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= pattern2.length()) {
                return count <= 0 || fieldLevel > getLevelFromChar(prevCh);
            }
            char ch = pattern2.charAt(i);
            if (ch != prevCh && count > 0) {
                if (fieldLevel <= getLevelFromChar(prevCh)) {
                    return false;
                }
                count = 0;
            }
            if (ch == '\'') {
                if (i + 1 >= pattern2.length() || pattern2.charAt(i + 1) != '\'') {
                    if (!inQuote) {
                        z = true;
                    }
                    inQuote = z;
                } else {
                    i++;
                }
            } else if (!inQuote && isSyntaxChar(ch)) {
                prevCh = ch;
                count++;
            }
            i++;
        }
    }

    @Deprecated
    public final StringBuffer intervalFormatByAlgorithm(Calendar fromCalendar, Calendar toCalendar, StringBuffer appendTo, FieldPosition pos) throws IllegalArgumentException {
        int diffBegin;
        IllegalArgumentException e;
        int diffEnd;
        int highestLevel;
        int diffEnd2;
        int i;
        String str;
        if (fromCalendar.isEquivalentTo(toCalendar)) {
            Object[] items = getPatternItems();
            int diffBegin2 = -1;
            int diffEnd3 = -1;
            int i2 = 0;
            while (true) {
                try {
                    if (i2 >= items.length) {
                        break;
                    } else if (diffCalFieldValue(fromCalendar, toCalendar, items, i2)) {
                        diffBegin2 = i2;
                        break;
                    } else {
                        i2++;
                    }
                } catch (IllegalArgumentException e2) {
                    throw new IllegalArgumentException(e2.toString());
                }
            }
            if (diffBegin2 == -1) {
                return format(fromCalendar, appendTo, pos);
            }
            int i3 = items.length - 1;
            while (true) {
                if (i3 < diffBegin2) {
                    break;
                } else if (diffCalFieldValue(fromCalendar, toCalendar, items, i3)) {
                    diffEnd3 = i3;
                    break;
                } else {
                    i3--;
                }
            }
            String str2 = " – ";
            if (diffBegin2 == 0 && diffEnd3 == items.length - 1) {
                format(fromCalendar, appendTo, pos);
                appendTo.append(str2);
                format(toCalendar, appendTo, pos);
                return appendTo;
            }
            int highestLevel2 = 1000;
            for (int i4 = diffBegin2; i4 <= diffEnd3; i4++) {
                if (!(items[i4] instanceof String)) {
                    char ch = ((PatternItem) items[i4]).type;
                    int patternCharIndex = getIndexFromChar(ch);
                    if (patternCharIndex == -1) {
                        throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + '\"');
                    } else if (patternCharIndex < highestLevel2) {
                        highestLevel2 = patternCharIndex;
                    }
                }
            }
            int i5 = 0;
            while (true) {
                if (i5 >= diffBegin2) {
                    diffBegin = diffBegin2;
                    break;
                }
                try {
                    if (lowerLevel(items, i5, highestLevel2)) {
                        diffBegin = i5;
                        break;
                    }
                    i5++;
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    throw new IllegalArgumentException(e.toString());
                }
            }
            try {
                int i6 = items.length - 1;
                while (true) {
                    if (i6 <= diffEnd3) {
                        diffEnd = diffEnd3;
                        break;
                    }
                    try {
                        if (lowerLevel(items, i6, highestLevel2)) {
                            diffEnd = i6;
                            break;
                        }
                        i6--;
                    } catch (IllegalArgumentException e4) {
                        e = e4;
                        throw new IllegalArgumentException(e.toString());
                    }
                }
                if (diffBegin == 0 && diffEnd == items.length - 1) {
                    format(fromCalendar, appendTo, pos);
                    appendTo.append(str2);
                    format(toCalendar, appendTo, pos);
                    return appendTo;
                }
                pos.setBeginIndex(0);
                pos.setEndIndex(0);
                DisplayContext capSetting = getContext(DisplayContext.Type.CAPITALIZATION);
                int i7 = 0;
                while (i7 <= diffEnd) {
                    if (items[i7] instanceof String) {
                        appendTo.append((String) items[i7]);
                        i = i7;
                        diffEnd2 = diffEnd;
                        highestLevel = highestLevel2;
                        str = str2;
                    } else {
                        PatternItem item = (PatternItem) items[i7];
                        if (this.useFastFormat) {
                            i = i7;
                            diffEnd2 = diffEnd;
                            highestLevel = highestLevel2;
                            str = str2;
                            subFormat(appendTo, item.type, item.length, appendTo.length(), i7, capSetting, pos, fromCalendar);
                        } else {
                            i = i7;
                            diffEnd2 = diffEnd;
                            highestLevel = highestLevel2;
                            str = str2;
                            appendTo.append(subFormat(item.type, item.length, appendTo.length(), i, capSetting, pos, fromCalendar));
                        }
                    }
                    i7 = i + 1;
                    str2 = str;
                    diffEnd = diffEnd2;
                    highestLevel2 = highestLevel;
                }
                appendTo.append(str2);
                for (int i8 = diffBegin; i8 < items.length; i8++) {
                    if (items[i8] instanceof String) {
                        appendTo.append((String) items[i8]);
                    } else {
                        PatternItem item2 = (PatternItem) items[i8];
                        if (this.useFastFormat) {
                            subFormat(appendTo, item2.type, item2.length, appendTo.length(), i8, capSetting, pos, toCalendar);
                        } else {
                            appendTo.append(subFormat(item2.type, item2.length, appendTo.length(), i8, capSetting, pos, toCalendar));
                        }
                    }
                }
                return appendTo;
            } catch (IllegalArgumentException e5) {
                e = e5;
                throw new IllegalArgumentException(e.toString());
            }
        } else {
            throw new IllegalArgumentException("can not format on two different calendars");
        }
    }

    private boolean diffCalFieldValue(Calendar fromCalendar, Calendar toCalendar, Object[] items, int i) throws IllegalArgumentException {
        if (items[i] instanceof String) {
            return false;
        }
        char ch = ((PatternItem) items[i]).type;
        int patternCharIndex = getIndexFromChar(ch);
        if (patternCharIndex != -1) {
            int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
            if (field < 0 || fromCalendar.get(field) == toCalendar.get(field)) {
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + '\"');
    }

    private boolean lowerLevel(Object[] items, int i, int level) throws IllegalArgumentException {
        if (items[i] instanceof String) {
            return false;
        }
        char ch = ((PatternItem) items[i]).type;
        int patternCharIndex = getLevelFromChar(ch);
        if (patternCharIndex == -1) {
            throw new IllegalArgumentException("Illegal pattern character '" + ch + "' in \"" + this.pattern + '\"');
        } else if (patternCharIndex >= level) {
            return true;
        } else {
            return false;
        }
    }

    public void setNumberFormat(String fields, NumberFormat overrideNF) {
        overrideNF.setGroupingUsed(false);
        String nsName = "$" + UUID.randomUUID().toString();
        if (this.numberFormatters == null) {
            this.numberFormatters = new HashMap<>();
        }
        if (this.overrideMap == null) {
            this.overrideMap = new HashMap<>();
        }
        for (int i = 0; i < fields.length(); i++) {
            char field = fields.charAt(i);
            if ("GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB".indexOf(field) != -1) {
                this.overrideMap.put(Character.valueOf(field), nsName);
                this.numberFormatters.put(nsName, overrideNF);
            } else {
                throw new IllegalArgumentException("Illegal field character '" + field + "' in setNumberFormat.");
            }
        }
        this.useLocalZeroPaddingNumberFormat = false;
    }

    public NumberFormat getNumberFormat(char field) {
        Character ovrField = Character.valueOf(field);
        HashMap<Character, String> hashMap = this.overrideMap;
        if (hashMap == null || !hashMap.containsKey(ovrField)) {
            return this.numberFormat;
        }
        return this.numberFormatters.get(this.overrideMap.get(ovrField).toString());
    }

    private void initNumberFormatters(ULocale loc) {
        this.numberFormatters = new HashMap<>();
        this.overrideMap = new HashMap<>();
        processOverrideString(loc, this.override);
    }

    private void processOverrideString(ULocale loc, String str) {
        int end;
        boolean fullOverride;
        String nsName;
        if (str != null && str.length() != 0) {
            int start = 0;
            boolean moreToProcess = true;
            while (moreToProcess) {
                int delimiterPosition = str.indexOf(";", start);
                if (delimiterPosition == -1) {
                    moreToProcess = false;
                    end = str.length();
                } else {
                    end = delimiterPosition;
                }
                String currentString = str.substring(start, end);
                int equalSignPosition = currentString.indexOf("=");
                if (equalSignPosition == -1) {
                    nsName = currentString;
                    fullOverride = true;
                } else {
                    nsName = currentString.substring(equalSignPosition + 1);
                    this.overrideMap.put(Character.valueOf(currentString.charAt(0)), nsName);
                    fullOverride = false;
                }
                NumberFormat nf = NumberFormat.createInstance(new ULocale(loc.getBaseName() + "@numbers=" + nsName), 0);
                nf.setGroupingUsed(false);
                if (fullOverride) {
                    setNumberFormat(nf);
                } else {
                    this.useLocalZeroPaddingNumberFormat = false;
                }
                if (!fullOverride && !this.numberFormatters.containsKey(nsName)) {
                    this.numberFormatters.put(nsName, nf);
                }
                start = delimiterPosition + 1;
            }
        }
    }

    private void parsePattern() {
        this.hasMinute = false;
        this.hasSecond = false;
        boolean inQuote = false;
        for (int i = 0; i < this.pattern.length(); i++) {
            char ch = this.pattern.charAt(i);
            if (ch == '\'') {
                inQuote = !inQuote;
            }
            if (!inQuote) {
                if (ch == 'm') {
                    this.hasMinute = true;
                }
                if (ch == 's') {
                    this.hasSecond = true;
                }
            }
        }
    }
}
