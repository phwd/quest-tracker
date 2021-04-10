package android.icu.text;

import android.icu.impl.CacheBase;
import android.icu.impl.CalendarUtil;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SoftCache;
import android.icu.impl.UResource$Key;
import android.icu.impl.UResource$Sink;
import android.icu.impl.UResource$Table;
import android.icu.impl.UResource$Value;
import android.icu.impl.Utility;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.ICUException;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceBundleIterator;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;

public class DateFormatSymbols implements Serializable, Cloneable {
    private static final String[][] CALENDAR_CLASSES = {new String[]{"GregorianCalendar", "gregorian"}, new String[]{"JapaneseCalendar", "japanese"}, new String[]{"BuddhistCalendar", "buddhist"}, new String[]{"TaiwanCalendar", "roc"}, new String[]{"PersianCalendar", "persian"}, new String[]{"IslamicCalendar", "islamic"}, new String[]{"HebrewCalendar", "hebrew"}, new String[]{"ChineseCalendar", "chinese"}, new String[]{"IndianCalendar", "indian"}, new String[]{"CopticCalendar", "coptic"}, new String[]{"EthiopicCalendar", "ethiopic"}};
    private static final String[] DAY_PERIOD_KEYS = {"midnight", "noon", "morning1", "afternoon1", "evening1", "night1", "morning2", "afternoon2", "evening2", "night2"};
    private static CacheBase DFSCACHE = new SoftCache() {
        /* class android.icu.text.DateFormatSymbols.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public DateFormatSymbols createInstance(String str, ULocale uLocale) {
            int indexOf = str.indexOf(43) + 1;
            int indexOf2 = str.indexOf(43, indexOf);
            if (indexOf2 < 0) {
                indexOf2 = str.length();
            }
            return new DateFormatSymbols(uLocale, null, str.substring(indexOf, indexOf2));
        }
    };
    private static final String[] LEAP_MONTH_PATTERNS_PATHS = new String[7];
    private static final Map contextUsageTypeMap = new HashMap();
    private static final long serialVersionUID = -5987973545549424702L;
    String[] abbreviatedDayPeriods;
    private ULocale actualLocale;
    String[] ampms;
    String[] ampmsNarrow;
    Map capitalization;
    String[] eraNames;
    String[] eras;
    String[] leapMonthPatterns;
    String localPatternChars;
    String[] months;
    String[] narrowDayPeriods;
    String[] narrowEras;
    String[] narrowMonths;
    String[] narrowWeekdays;
    String[] quarters;
    private ULocale requestedLocale;
    String[] shortMonths;
    String[] shortQuarters;
    String[] shortWeekdays;
    String[] shortYearNames;
    String[] shortZodiacNames;
    String[] shorterWeekdays;
    String[] standaloneAbbreviatedDayPeriods;
    String[] standaloneMonths;
    String[] standaloneNarrowDayPeriods;
    String[] standaloneNarrowMonths;
    String[] standaloneNarrowWeekdays;
    String[] standaloneQuarters;
    String[] standaloneShortMonths;
    String[] standaloneShortQuarters;
    String[] standaloneShortWeekdays;
    String[] standaloneShorterWeekdays;
    String[] standaloneWeekdays;
    String[] standaloneWideDayPeriods;
    private String timeSeparator;
    private ULocale validLocale;
    String[] weekdays;
    String[] wideDayPeriods;
    private String[][] zoneStrings;

    /* access modifiers changed from: package-private */
    public enum CapitalizationContextUsage {
        OTHER,
        MONTH_FORMAT,
        MONTH_STANDALONE,
        MONTH_NARROW,
        DAY_FORMAT,
        DAY_STANDALONE,
        DAY_NARROW,
        ERA_WIDE,
        ERA_ABBREV,
        ERA_NARROW,
        ZONE_LONG,
        ZONE_SHORT,
        METAZONE_LONG,
        METAZONE_SHORT
    }

    public DateFormatSymbols() {
        this(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public DateFormatSymbols(ULocale uLocale) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(uLocale, CalendarUtil.getCalendarType(uLocale));
    }

    static {
        contextUsageTypeMap.put("month-format-except-narrow", CapitalizationContextUsage.MONTH_FORMAT);
        contextUsageTypeMap.put("month-standalone-except-narrow", CapitalizationContextUsage.MONTH_STANDALONE);
        contextUsageTypeMap.put("month-narrow", CapitalizationContextUsage.MONTH_NARROW);
        contextUsageTypeMap.put("day-format-except-narrow", CapitalizationContextUsage.DAY_FORMAT);
        contextUsageTypeMap.put("day-standalone-except-narrow", CapitalizationContextUsage.DAY_STANDALONE);
        contextUsageTypeMap.put("day-narrow", CapitalizationContextUsage.DAY_NARROW);
        contextUsageTypeMap.put("era-name", CapitalizationContextUsage.ERA_WIDE);
        contextUsageTypeMap.put("era-abbr", CapitalizationContextUsage.ERA_ABBREV);
        contextUsageTypeMap.put("era-narrow", CapitalizationContextUsage.ERA_NARROW);
        contextUsageTypeMap.put("zone-long", CapitalizationContextUsage.ZONE_LONG);
        contextUsageTypeMap.put("zone-short", CapitalizationContextUsage.ZONE_SHORT);
        contextUsageTypeMap.put("metazone-long", CapitalizationContextUsage.METAZONE_LONG);
        contextUsageTypeMap.put("metazone-short", CapitalizationContextUsage.METAZONE_SHORT);
        String[] strArr = LEAP_MONTH_PATTERNS_PATHS;
        strArr[0] = "monthPatterns/format/wide";
        strArr[1] = "monthPatterns/format/abbreviated";
        strArr[2] = "monthPatterns/format/narrow";
        strArr[3] = "monthPatterns/stand-alone/wide";
        strArr[4] = "monthPatterns/stand-alone/abbreviated";
        strArr[5] = "monthPatterns/stand-alone/narrow";
        strArr[6] = "monthPatterns/numeric/all";
    }

    public String[] getEras() {
        return duplicate(this.eras);
    }

    public String[] getEraNames() {
        return duplicate(this.eraNames);
    }

    public String[] getNarrowEras() {
        return duplicate(this.narrowEras);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        if (r6 != 3) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r6 != 3) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] getMonths(int r5, int r6) {
        /*
            r4 = this;
            r0 = 3
            r1 = 2
            r2 = 1
            r3 = 0
            if (r5 == 0) goto L_0x001b
            if (r5 == r2) goto L_0x0009
            goto L_0x002c
        L_0x0009:
            if (r6 == 0) goto L_0x0018
            if (r6 == r2) goto L_0x0015
            if (r6 == r1) goto L_0x0012
            if (r6 == r0) goto L_0x0018
            goto L_0x002c
        L_0x0012:
            java.lang.String[] r3 = r4.standaloneNarrowMonths
            goto L_0x002c
        L_0x0015:
            java.lang.String[] r3 = r4.standaloneMonths
            goto L_0x002c
        L_0x0018:
            java.lang.String[] r3 = r4.standaloneShortMonths
            goto L_0x002c
        L_0x001b:
            if (r6 == 0) goto L_0x002a
            if (r6 == r2) goto L_0x0027
            if (r6 == r1) goto L_0x0024
            if (r6 == r0) goto L_0x002a
            goto L_0x002c
        L_0x0024:
            java.lang.String[] r3 = r4.narrowMonths
            goto L_0x002c
        L_0x0027:
            java.lang.String[] r3 = r4.months
            goto L_0x002c
        L_0x002a:
            java.lang.String[] r3 = r4.shortMonths
        L_0x002c:
            if (r3 == 0) goto L_0x0033
            java.lang.String[] r4 = r4.duplicate(r3)
            return r4
        L_0x0033:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Bad context or width argument"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DateFormatSymbols.getMonths(int, int):java.lang.String[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] getWeekdays(int r5, int r6) {
        /*
            r4 = this;
            r0 = 3
            r1 = 2
            r2 = 1
            r3 = 0
            if (r5 == 0) goto L_0x0024
            if (r5 == r2) goto L_0x0009
            goto L_0x003d
        L_0x0009:
            if (r6 == 0) goto L_0x0021
            if (r6 == r2) goto L_0x001e
            if (r6 == r1) goto L_0x001b
            if (r6 == r0) goto L_0x0012
            goto L_0x003d
        L_0x0012:
            java.lang.String[] r5 = r4.standaloneShorterWeekdays
            if (r5 == 0) goto L_0x0017
        L_0x0016:
            goto L_0x0019
        L_0x0017:
            java.lang.String[] r5 = r4.standaloneShortWeekdays
        L_0x0019:
            r3 = r5
            goto L_0x003d
        L_0x001b:
            java.lang.String[] r3 = r4.standaloneNarrowWeekdays
            goto L_0x003d
        L_0x001e:
            java.lang.String[] r3 = r4.standaloneWeekdays
            goto L_0x003d
        L_0x0021:
            java.lang.String[] r3 = r4.standaloneShortWeekdays
            goto L_0x003d
        L_0x0024:
            if (r6 == 0) goto L_0x003b
            if (r6 == r2) goto L_0x0038
            if (r6 == r1) goto L_0x0035
            if (r6 == r0) goto L_0x002d
            goto L_0x003d
        L_0x002d:
            java.lang.String[] r5 = r4.shorterWeekdays
            if (r5 == 0) goto L_0x0032
            goto L_0x0016
        L_0x0032:
            java.lang.String[] r5 = r4.shortWeekdays
            goto L_0x0019
        L_0x0035:
            java.lang.String[] r3 = r4.narrowWeekdays
            goto L_0x003d
        L_0x0038:
            java.lang.String[] r3 = r4.weekdays
            goto L_0x003d
        L_0x003b:
            java.lang.String[] r3 = r4.shortWeekdays
        L_0x003d:
            if (r3 == 0) goto L_0x0044
            java.lang.String[] r4 = r4.duplicate(r3)
            return r4
        L_0x0044:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Bad context or width argument"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DateFormatSymbols.getWeekdays(int, int):java.lang.String[]");
    }

    public String[] getAmPmStrings() {
        return duplicate(this.ampms);
    }

    public void setTimeSeparatorString(String str) {
        this.timeSeparator = str;
    }

    public Object clone() {
        try {
            return (DateFormatSymbols) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public int hashCode() {
        return this.requestedLocale.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DateFormatSymbols.class != obj.getClass()) {
            return false;
        }
        DateFormatSymbols dateFormatSymbols = (DateFormatSymbols) obj;
        return Utility.arrayEquals(this.eras, dateFormatSymbols.eras) && Utility.arrayEquals(this.eraNames, dateFormatSymbols.eraNames) && Utility.arrayEquals(this.months, dateFormatSymbols.months) && Utility.arrayEquals(this.shortMonths, dateFormatSymbols.shortMonths) && Utility.arrayEquals(this.narrowMonths, dateFormatSymbols.narrowMonths) && Utility.arrayEquals(this.standaloneMonths, dateFormatSymbols.standaloneMonths) && Utility.arrayEquals(this.standaloneShortMonths, dateFormatSymbols.standaloneShortMonths) && Utility.arrayEquals(this.standaloneNarrowMonths, dateFormatSymbols.standaloneNarrowMonths) && Utility.arrayEquals(this.weekdays, dateFormatSymbols.weekdays) && Utility.arrayEquals(this.shortWeekdays, dateFormatSymbols.shortWeekdays) && Utility.arrayEquals(this.shorterWeekdays, dateFormatSymbols.shorterWeekdays) && Utility.arrayEquals(this.narrowWeekdays, dateFormatSymbols.narrowWeekdays) && Utility.arrayEquals(this.standaloneWeekdays, dateFormatSymbols.standaloneWeekdays) && Utility.arrayEquals(this.standaloneShortWeekdays, dateFormatSymbols.standaloneShortWeekdays) && Utility.arrayEquals(this.standaloneShorterWeekdays, dateFormatSymbols.standaloneShorterWeekdays) && Utility.arrayEquals(this.standaloneNarrowWeekdays, dateFormatSymbols.standaloneNarrowWeekdays) && Utility.arrayEquals(this.ampms, dateFormatSymbols.ampms) && Utility.arrayEquals(this.ampmsNarrow, dateFormatSymbols.ampmsNarrow) && Utility.arrayEquals(this.abbreviatedDayPeriods, dateFormatSymbols.abbreviatedDayPeriods) && Utility.arrayEquals(this.wideDayPeriods, dateFormatSymbols.wideDayPeriods) && Utility.arrayEquals(this.narrowDayPeriods, dateFormatSymbols.narrowDayPeriods) && Utility.arrayEquals(this.standaloneAbbreviatedDayPeriods, dateFormatSymbols.standaloneAbbreviatedDayPeriods) && Utility.arrayEquals(this.standaloneWideDayPeriods, dateFormatSymbols.standaloneWideDayPeriods) && Utility.arrayEquals(this.standaloneNarrowDayPeriods, dateFormatSymbols.standaloneNarrowDayPeriods) && Utility.arrayEquals(this.timeSeparator, dateFormatSymbols.timeSeparator) && arrayOfArrayEquals(this.zoneStrings, dateFormatSymbols.zoneStrings) && this.requestedLocale.getDisplayName().equals(dateFormatSymbols.requestedLocale.getDisplayName()) && Utility.arrayEquals(this.localPatternChars, dateFormatSymbols.localPatternChars);
    }

    /* access modifiers changed from: protected */
    public void initializeData(ULocale uLocale, String str) {
        String str2 = uLocale.getBaseName() + '+' + str;
        String keywordValue = uLocale.getKeywordValue("numbers");
        if (keywordValue != null && keywordValue.length() > 0) {
            str2 = str2 + '+' + keywordValue;
        }
        initializeData((DateFormatSymbols) DFSCACHE.getInstance(str2, uLocale));
    }

    /* access modifiers changed from: package-private */
    public void initializeData(DateFormatSymbols dateFormatSymbols) {
        this.eras = dateFormatSymbols.eras;
        this.eraNames = dateFormatSymbols.eraNames;
        this.narrowEras = dateFormatSymbols.narrowEras;
        this.months = dateFormatSymbols.months;
        this.shortMonths = dateFormatSymbols.shortMonths;
        this.narrowMonths = dateFormatSymbols.narrowMonths;
        this.standaloneMonths = dateFormatSymbols.standaloneMonths;
        this.standaloneShortMonths = dateFormatSymbols.standaloneShortMonths;
        this.standaloneNarrowMonths = dateFormatSymbols.standaloneNarrowMonths;
        this.weekdays = dateFormatSymbols.weekdays;
        this.shortWeekdays = dateFormatSymbols.shortWeekdays;
        this.shorterWeekdays = dateFormatSymbols.shorterWeekdays;
        this.narrowWeekdays = dateFormatSymbols.narrowWeekdays;
        this.standaloneWeekdays = dateFormatSymbols.standaloneWeekdays;
        this.standaloneShortWeekdays = dateFormatSymbols.standaloneShortWeekdays;
        this.standaloneShorterWeekdays = dateFormatSymbols.standaloneShorterWeekdays;
        this.standaloneNarrowWeekdays = dateFormatSymbols.standaloneNarrowWeekdays;
        this.ampms = dateFormatSymbols.ampms;
        this.ampmsNarrow = dateFormatSymbols.ampmsNarrow;
        this.timeSeparator = dateFormatSymbols.timeSeparator;
        this.shortQuarters = dateFormatSymbols.shortQuarters;
        this.quarters = dateFormatSymbols.quarters;
        this.standaloneShortQuarters = dateFormatSymbols.standaloneShortQuarters;
        this.standaloneQuarters = dateFormatSymbols.standaloneQuarters;
        this.leapMonthPatterns = dateFormatSymbols.leapMonthPatterns;
        this.shortYearNames = dateFormatSymbols.shortYearNames;
        this.shortZodiacNames = dateFormatSymbols.shortZodiacNames;
        this.abbreviatedDayPeriods = dateFormatSymbols.abbreviatedDayPeriods;
        this.wideDayPeriods = dateFormatSymbols.wideDayPeriods;
        this.narrowDayPeriods = dateFormatSymbols.narrowDayPeriods;
        this.standaloneAbbreviatedDayPeriods = dateFormatSymbols.standaloneAbbreviatedDayPeriods;
        this.standaloneWideDayPeriods = dateFormatSymbols.standaloneWideDayPeriods;
        this.standaloneNarrowDayPeriods = dateFormatSymbols.standaloneNarrowDayPeriods;
        this.zoneStrings = dateFormatSymbols.zoneStrings;
        this.localPatternChars = dateFormatSymbols.localPatternChars;
        this.capitalization = dateFormatSymbols.capitalization;
        this.actualLocale = dateFormatSymbols.actualLocale;
        this.validLocale = dateFormatSymbols.validLocale;
        this.requestedLocale = dateFormatSymbols.requestedLocale;
    }

    /* access modifiers changed from: private */
    public static final class CalendarDataSink extends UResource$Sink {
        List aliasPathPairs = new ArrayList();
        private String aliasRelativePath;
        Map arrays = new TreeMap();
        String currentCalendarType = null;
        Map maps = new TreeMap();
        String nextCalendarType = null;
        private Set resourcesToVisit;

        /* access modifiers changed from: private */
        public enum AliasType {
            SAME_CALENDAR,
            DIFFERENT_CALENDAR,
            GREGORIAN,
            NONE
        }

        CalendarDataSink() {
        }

        /* access modifiers changed from: package-private */
        public void visitAllResources() {
            this.resourcesToVisit = null;
        }

        /* access modifiers changed from: package-private */
        public void preEnumerate(String str) {
            this.currentCalendarType = str;
            this.nextCalendarType = null;
            this.aliasPathPairs.clear();
        }

        /* JADX WARNING: Removed duplicated region for block: B:60:0x0123  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x0131  */
        @Override // android.icu.impl.UResource$Sink
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void put(android.icu.impl.UResource$Key r7, android.icu.impl.UResource$Value r8, boolean r9) {
            /*
            // Method dump skipped, instructions count: 323
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DateFormatSymbols.CalendarDataSink.put(android.icu.impl.UResource$Key, android.icu.impl.UResource$Value, boolean):void");
        }

        /* access modifiers changed from: protected */
        public void processResource(String str, UResource$Key uResource$Key, UResource$Value uResource$Value) {
            UResource$Table table = uResource$Value.getTable();
            HashMap hashMap = null;
            for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                if (!uResource$Key.endsWith("%variant")) {
                    String uResource$Key2 = uResource$Key.toString();
                    if (uResource$Value.getType() == 0) {
                        if (i == 0) {
                            hashMap = new HashMap();
                            this.maps.put(str, hashMap);
                        }
                        hashMap.put(uResource$Key2, uResource$Value.getString());
                    } else {
                        String str2 = str + "/" + uResource$Key2;
                        if ((!str2.startsWith("cyclicNameSets") || "cyclicNameSets/years/format/abbreviated".startsWith(str2) || "cyclicNameSets/zodiacs/format/abbreviated".startsWith(str2) || "cyclicNameSets/dayParts/format/abbreviated".startsWith(str2)) && !this.arrays.containsKey(str2) && !this.maps.containsKey(str2)) {
                            if (processAliasFromValue(str2, uResource$Value) == AliasType.SAME_CALENDAR) {
                                this.aliasPathPairs.add(this.aliasRelativePath);
                                this.aliasPathPairs.add(str2);
                            } else if (uResource$Value.getType() == 8) {
                                this.arrays.put(str2, uResource$Value.getStringArray());
                            } else if (uResource$Value.getType() == 2) {
                                processResource(str2, uResource$Key, uResource$Value);
                            }
                        }
                    }
                }
            }
        }

        private AliasType processAliasFromValue(String str, UResource$Value uResource$Value) {
            int indexOf;
            if (uResource$Value.getType() != 3) {
                return AliasType.NONE;
            }
            String aliasString = uResource$Value.getAliasString();
            if (aliasString.startsWith("/LOCALE/calendar/") && aliasString.length() > 17 && (indexOf = aliasString.indexOf(47, 17)) > 17) {
                String substring = aliasString.substring(17, indexOf);
                this.aliasRelativePath = aliasString.substring(indexOf + 1);
                if (this.currentCalendarType.equals(substring) && !str.equals(this.aliasRelativePath)) {
                    return AliasType.SAME_CALENDAR;
                }
                if (!this.currentCalendarType.equals(substring) && str.equals(this.aliasRelativePath)) {
                    if (substring.equals("gregorian")) {
                        return AliasType.GREGORIAN;
                    }
                    String str2 = this.nextCalendarType;
                    if (str2 == null || str2.equals(substring)) {
                        this.nextCalendarType = substring;
                        return AliasType.DIFFERENT_CALENDAR;
                    }
                }
            }
            throw new ICUException("Malformed 'calendar' alias. Path: " + aliasString);
        }
    }

    private DateFormatSymbols(ULocale uLocale, ICUResourceBundle iCUResourceBundle, String str) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(uLocale, iCUResourceBundle, str);
    }

    /* access modifiers changed from: protected */
    public void initializeData(ULocale uLocale, ICUResourceBundle iCUResourceBundle, String str) {
        ICUResourceBundle iCUResourceBundle2;
        String str2;
        Map map;
        String str3;
        CalendarDataSink calendarDataSink = new CalendarDataSink();
        if (iCUResourceBundle == null) {
            iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", uLocale);
        }
        while (str != null) {
            ICUResourceBundle findWithFallback = iCUResourceBundle.findWithFallback("calendar/" + str);
            if (findWithFallback != null) {
                calendarDataSink.preEnumerate(str);
                findWithFallback.getAllItemsWithFallback("", calendarDataSink);
                if (str.equals("gregorian")) {
                    break;
                }
                str = calendarDataSink.nextCalendarType;
                if (str == null) {
                    calendarDataSink.visitAllResources();
                }
            } else if (!"gregorian".equals(str)) {
                calendarDataSink.visitAllResources();
            } else {
                throw new MissingResourceException("The 'gregorian' calendar type wasn't found for the locale: " + uLocale.getBaseName(), DateFormatSymbols.class.getName(), "gregorian");
            }
            str = "gregorian";
        }
        Map map2 = calendarDataSink.arrays;
        Map map3 = calendarDataSink.maps;
        this.eras = (String[]) map2.get("eras/abbreviated");
        this.eraNames = (String[]) map2.get("eras/wide");
        this.narrowEras = (String[]) map2.get("eras/narrow");
        this.months = (String[]) map2.get("monthNames/format/wide");
        this.shortMonths = (String[]) map2.get("monthNames/format/abbreviated");
        this.narrowMonths = (String[]) map2.get("monthNames/format/narrow");
        this.standaloneMonths = (String[]) map2.get("monthNames/stand-alone/wide");
        this.standaloneShortMonths = (String[]) map2.get("monthNames/stand-alone/abbreviated");
        this.standaloneNarrowMonths = (String[]) map2.get("monthNames/stand-alone/narrow");
        String[] strArr = (String[]) map2.get("dayNames/format/wide");
        this.weekdays = new String[8];
        String[] strArr2 = this.weekdays;
        strArr2[0] = "";
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        String[] strArr3 = (String[]) map2.get("dayNames/format/abbreviated");
        this.shortWeekdays = new String[8];
        String[] strArr4 = this.shortWeekdays;
        strArr4[0] = "";
        System.arraycopy(strArr3, 0, strArr4, 1, strArr3.length);
        String[] strArr5 = (String[]) map2.get("dayNames/format/short");
        this.shorterWeekdays = new String[8];
        String[] strArr6 = this.shorterWeekdays;
        strArr6[0] = "";
        System.arraycopy(strArr5, 0, strArr6, 1, strArr5.length);
        String[] strArr7 = (String[]) map2.get("dayNames/format/narrow");
        if (strArr7 == null && (strArr7 = (String[]) map2.get("dayNames/stand-alone/narrow")) == null && (strArr7 = (String[]) map2.get("dayNames/format/abbreviated")) == null) {
            throw new MissingResourceException("Resource not found", DateFormatSymbols.class.getName(), "dayNames/format/abbreviated");
        }
        this.narrowWeekdays = new String[8];
        String[] strArr8 = this.narrowWeekdays;
        strArr8[0] = "";
        System.arraycopy(strArr7, 0, strArr8, 1, strArr7.length);
        String[] strArr9 = (String[]) map2.get("dayNames/stand-alone/wide");
        this.standaloneWeekdays = new String[8];
        String[] strArr10 = this.standaloneWeekdays;
        strArr10[0] = "";
        System.arraycopy(strArr9, 0, strArr10, 1, strArr9.length);
        String[] strArr11 = (String[]) map2.get("dayNames/stand-alone/abbreviated");
        this.standaloneShortWeekdays = new String[8];
        String[] strArr12 = this.standaloneShortWeekdays;
        strArr12[0] = "";
        System.arraycopy(strArr11, 0, strArr12, 1, strArr11.length);
        String[] strArr13 = (String[]) map2.get("dayNames/stand-alone/short");
        this.standaloneShorterWeekdays = new String[8];
        String[] strArr14 = this.standaloneShorterWeekdays;
        strArr14[0] = "";
        System.arraycopy(strArr13, 0, strArr14, 1, strArr13.length);
        String[] strArr15 = (String[]) map2.get("dayNames/stand-alone/narrow");
        this.standaloneNarrowWeekdays = new String[8];
        String[] strArr16 = this.standaloneNarrowWeekdays;
        strArr16[0] = "";
        System.arraycopy(strArr15, 0, strArr16, 1, strArr15.length);
        this.ampms = (String[]) map2.get("AmPmMarkers");
        this.ampmsNarrow = (String[]) map2.get("AmPmMarkersNarrow");
        this.quarters = (String[]) map2.get("quarters/format/wide");
        this.shortQuarters = (String[]) map2.get("quarters/format/abbreviated");
        this.standaloneQuarters = (String[]) map2.get("quarters/stand-alone/wide");
        this.standaloneShortQuarters = (String[]) map2.get("quarters/stand-alone/abbreviated");
        this.abbreviatedDayPeriods = loadDayPeriodStrings((Map) map3.get("dayPeriod/format/abbreviated"));
        this.wideDayPeriods = loadDayPeriodStrings((Map) map3.get("dayPeriod/format/wide"));
        this.narrowDayPeriods = loadDayPeriodStrings((Map) map3.get("dayPeriod/format/narrow"));
        this.standaloneAbbreviatedDayPeriods = loadDayPeriodStrings((Map) map3.get("dayPeriod/stand-alone/abbreviated"));
        this.standaloneWideDayPeriods = loadDayPeriodStrings((Map) map3.get("dayPeriod/stand-alone/wide"));
        this.standaloneNarrowDayPeriods = loadDayPeriodStrings((Map) map3.get("dayPeriod/stand-alone/narrow"));
        for (int i = 0; i < 7; i++) {
            String str4 = LEAP_MONTH_PATTERNS_PATHS[i];
            if (!(str4 == null || (map = (Map) map3.get(str4)) == null || (str3 = (String) map.get("leap")) == null)) {
                if (this.leapMonthPatterns == null) {
                    this.leapMonthPatterns = new String[7];
                }
                this.leapMonthPatterns[i] = str3;
            }
        }
        this.shortYearNames = (String[]) map2.get("cyclicNameSets/years/format/abbreviated");
        this.shortZodiacNames = (String[]) map2.get("cyclicNameSets/zodiacs/format/abbreviated");
        this.requestedLocale = uLocale;
        ICUResourceBundle iCUResourceBundle3 = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", uLocale);
        this.localPatternChars = "GyMdkHmsSEDFwWahKzYeugAZvcLQqVUOXxrbB";
        ULocale uLocale2 = iCUResourceBundle3.getULocale();
        setLocale(uLocale2, uLocale2);
        this.capitalization = new HashMap();
        boolean[] zArr = {false, false};
        for (CapitalizationContextUsage capitalizationContextUsage : CapitalizationContextUsage.values()) {
            this.capitalization.put(capitalizationContextUsage, zArr);
        }
        try {
            iCUResourceBundle2 = iCUResourceBundle3.getWithFallback("contextTransforms");
        } catch (MissingResourceException unused) {
            iCUResourceBundle2 = null;
        }
        if (iCUResourceBundle2 != null) {
            UResourceBundleIterator iterator = iCUResourceBundle2.getIterator();
            while (iterator.hasNext()) {
                UResourceBundle next = iterator.next();
                int[] intVector = next.getIntVector();
                if (intVector.length >= 2) {
                    CapitalizationContextUsage capitalizationContextUsage2 = (CapitalizationContextUsage) contextUsageTypeMap.get(next.getKey());
                    if (capitalizationContextUsage2 != null) {
                        boolean[] zArr2 = new boolean[2];
                        zArr2[0] = intVector[0] != 0;
                        zArr2[1] = intVector[1] != 0;
                        this.capitalization.put(capitalizationContextUsage2, zArr2);
                    }
                }
            }
        }
        NumberingSystem instance = NumberingSystem.getInstance(uLocale);
        if (instance == null) {
            str2 = "latn";
        } else {
            str2 = instance.getName();
        }
        try {
            setTimeSeparatorString(iCUResourceBundle3.getStringWithFallback("NumberElements/" + str2 + "/symbols/timeSeparator"));
        } catch (MissingResourceException unused2) {
            setTimeSeparatorString(":");
        }
    }

    private static final boolean arrayOfArrayEquals(Object[][] objArr, Object[][] objArr2) {
        boolean z = true;
        if (objArr == objArr2) {
            return true;
        }
        int i = 0;
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        while (i < objArr.length && (z = Utility.arrayEquals(objArr[i], (Object) objArr2[i]))) {
            i++;
        }
        return z;
    }

    private String[] loadDayPeriodStrings(Map map) {
        String[] strArr = new String[DAY_PERIOD_KEYS.length];
        if (map != null) {
            int i = 0;
            while (true) {
                String[] strArr2 = DAY_PERIOD_KEYS;
                if (i >= strArr2.length) {
                    break;
                }
                strArr[i] = (String) map.get(strArr2[i]);
                i++;
            }
        }
        return strArr;
    }

    private final String[] duplicate(String[] strArr) {
        return (String[]) strArr.clone();
    }

    public DateFormatSymbols(ULocale uLocale, String str) {
        this.eras = null;
        this.eraNames = null;
        this.narrowEras = null;
        this.months = null;
        this.shortMonths = null;
        this.narrowMonths = null;
        this.standaloneMonths = null;
        this.standaloneShortMonths = null;
        this.standaloneNarrowMonths = null;
        this.weekdays = null;
        this.shortWeekdays = null;
        this.shorterWeekdays = null;
        this.narrowWeekdays = null;
        this.standaloneWeekdays = null;
        this.standaloneShortWeekdays = null;
        this.standaloneShorterWeekdays = null;
        this.standaloneNarrowWeekdays = null;
        this.ampms = null;
        this.ampmsNarrow = null;
        this.timeSeparator = null;
        this.shortQuarters = null;
        this.quarters = null;
        this.standaloneShortQuarters = null;
        this.standaloneQuarters = null;
        this.leapMonthPatterns = null;
        this.shortYearNames = null;
        this.shortZodiacNames = null;
        this.zoneStrings = null;
        this.localPatternChars = null;
        this.abbreviatedDayPeriods = null;
        this.wideDayPeriods = null;
        this.narrowDayPeriods = null;
        this.standaloneAbbreviatedDayPeriods = null;
        this.standaloneWideDayPeriods = null;
        this.standaloneNarrowDayPeriods = null;
        this.capitalization = null;
        initializeData(uLocale, str);
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale uLocale, ULocale uLocale2) {
        boolean z = true;
        boolean z2 = uLocale == null;
        if (uLocale2 != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = uLocale;
            this.actualLocale = uLocale2;
            return;
        }
        throw new IllegalArgumentException();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
