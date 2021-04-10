package android.icu.util;

import android.icu.impl.CalType;
import android.icu.impl.CalendarUtil;
import android.icu.impl.ICUCache;
import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SimpleCache;
import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.SoftCache;
import android.icu.impl.number.RoundingUtils;
import android.icu.lang.UCharacter;
import android.icu.text.DateFormat;
import android.icu.text.DateFormatSymbols;
import android.icu.text.SimpleDateFormat;
import android.icu.util.ULocale;
import android.support.v4.os.EnvironmentCompat;
import dalvik.system.VMRuntime;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import libcore.icu.RelativeDateTimeFormatter;

public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int AM = 0;
    public static final int AM_PM = 9;
    public static final int APRIL = 3;
    public static final int AUGUST = 7;
    @Deprecated
    protected static final int BASE_FIELD_COUNT = 23;
    public static final int DATE = 5;
    static final int[][][] DATE_PRECEDENCE = {new int[][]{new int[]{5}, new int[]{3, 7}, new int[]{4, 7}, new int[]{8, 7}, new int[]{3, 18}, new int[]{4, 18}, new int[]{8, 18}, new int[]{6}, new int[]{37, 1}, new int[]{35, 17}}, new int[][]{new int[]{3}, new int[]{4}, new int[]{8}, new int[]{40, 7}, new int[]{40, 18}}};
    public static final int DAY_OF_MONTH = 5;
    public static final int DAY_OF_WEEK = 7;
    public static final int DAY_OF_WEEK_IN_MONTH = 8;
    public static final int DAY_OF_YEAR = 6;
    public static final int DECEMBER = 11;
    private static final String[] DEFAULT_PATTERNS = {"HH:mm:ss z", "HH:mm:ss z", "HH:mm:ss", "HH:mm", "EEEE, yyyy MMMM dd", "yyyy MMMM d", "yyyy MMM d", "yy/MM/dd", "{1} {0}", "{1} {0}", "{1} {0}", "{1} {0}", "{1} {0}"};
    public static final int DOW_LOCAL = 18;
    static final int[][][] DOW_PRECEDENCE = {new int[][]{new int[]{7}, new int[]{18}}};
    public static final int DST_OFFSET = 16;
    protected static final int EPOCH_JULIAN_DAY = 2440588;
    public static final int ERA = 0;
    public static final int EXTENDED_YEAR = 19;
    public static final int FEBRUARY = 1;
    private static final int FIELD_DIFF_MAX_INT = Integer.MAX_VALUE;
    private static final String[] FIELD_NAME = {"ERA", "YEAR", "MONTH", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAY_OF_MONTH", "DAY_OF_YEAR", "DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "AM_PM", "HOUR", "HOUR_OF_DAY", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET", "DST_OFFSET", "YEAR_WOY", "DOW_LOCAL", "EXTENDED_YEAR", "JULIAN_DAY", "MILLISECONDS_IN_DAY"};
    private static final int[] FIND_ZONE_TRANSITION_TIME_UNITS = {3600000, 1800000, 60000, 1000};
    public static final int FRIDAY = 6;
    protected static final int GREATEST_MINIMUM = 1;
    private static final int[][] GREGORIAN_MONTH_COUNT = {new int[]{31, 31, 0, 0}, new int[]{28, 29, 31, 31}, new int[]{31, 31, 59, 60}, new int[]{30, 30, 90, 91}, new int[]{31, 31, 120, 121}, new int[]{30, 30, 151, 152}, new int[]{31, 31, 181, 182}, new int[]{31, 31, 212, 213}, new int[]{30, 30, 243, 244}, new int[]{31, 31, UCharacter.UnicodeBlock.TANGUT_COMPONENTS_ID, UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID}, new int[]{30, 30, HttpURLConnection.HTTP_NOT_MODIFIED, HttpURLConnection.HTTP_USE_PROXY}, new int[]{31, 31, 334, 335}};
    public static final int HOUR = 10;
    public static final int HOUR_OF_DAY = 11;
    protected static final int INTERNALLY_SET = 1;
    public static final int IS_LEAP_MONTH = 22;
    public static final int JANUARY = 0;
    protected static final int JAN_1_1_JULIAN_DAY = 1721426;
    public static final int JULIAN_DAY = 20;
    public static final int JULY = 6;
    public static final int JUNE = 5;
    protected static final int LEAST_MAXIMUM = 2;
    private static final int[][] LIMITS = {new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[]{1, 1, 7, 7}, new int[0], new int[]{0, 0, 1, 1}, new int[]{0, 0, 11, 11}, new int[]{0, 0, 23, 23}, new int[]{0, 0, 59, 59}, new int[]{0, 0, 59, 59}, new int[]{0, 0, RoundingUtils.MAX_INT_FRAC_SIG, RoundingUtils.MAX_INT_FRAC_SIG}, new int[]{-43200000, -43200000, 43200000, 43200000}, new int[]{0, 0, 3600000, 3600000}, new int[0], new int[]{1, 1, 7, 7}, new int[0], new int[]{MIN_JULIAN, MIN_JULIAN, 2130706432, 2130706432}, new int[]{0, 0, 86399999, 86399999}, new int[]{0, 0, 1, 1}};
    public static final int MARCH = 2;
    protected static final int MAXIMUM = 3;
    protected static final Date MAX_DATE = new Date(183882168921600000L);
    @Deprecated
    protected static final int MAX_FIELD_COUNT = 32;
    private static final int MAX_HOURS = 548;
    protected static final int MAX_JULIAN = 2130706432;
    protected static final long MAX_MILLIS = 183882168921600000L;
    public static final int MAY = 4;
    public static final int MILLISECOND = 14;
    public static final int MILLISECONDS_IN_DAY = 21;
    protected static final int MINIMUM = 0;
    protected static final int MINIMUM_USER_STAMP = 2;
    public static final int MINUTE = 12;
    protected static final Date MIN_DATE = new Date(-184303902528000000L);
    protected static final int MIN_JULIAN = -2130706432;
    protected static final long MIN_MILLIS = -184303902528000000L;
    public static final int MONDAY = 2;
    public static final int MONTH = 2;
    public static final int NOVEMBER = 10;
    public static final int OCTOBER = 9;
    protected static final long ONE_DAY = 86400000;
    protected static final int ONE_HOUR = 3600000;
    protected static final int ONE_MINUTE = 60000;
    protected static final int ONE_SECOND = 1000;
    protected static final long ONE_WEEK = 604800000;
    private static final ICUCache<String, PatternData> PATTERN_CACHE = new SimpleCache();
    public static final int PM = 1;
    private static final char QUOTE = '\'';
    protected static final int RESOLVE_REMAP = 32;
    public static final int SATURDAY = 7;
    public static final int SECOND = 13;
    public static final int SEPTEMBER = 8;
    private static int STAMP_MAX = VMRuntime.SDK_VERSION_CUR_DEVELOPMENT;
    public static final int SUNDAY = 1;
    public static final int THURSDAY = 5;
    public static final int TUESDAY = 3;
    public static final int UNDECIMBER = 12;
    protected static final int UNSET = 0;
    public static final int WALLTIME_FIRST = 1;
    public static final int WALLTIME_LAST = 0;
    public static final int WALLTIME_NEXT_VALID = 2;
    public static final int WEDNESDAY = 4;
    @Deprecated
    public static final int WEEKDAY = 0;
    @Deprecated
    public static final int WEEKEND = 1;
    @Deprecated
    public static final int WEEKEND_CEASE = 3;
    @Deprecated
    public static final int WEEKEND_ONSET = 2;
    private static final WeekDataCache WEEK_DATA_CACHE = new WeekDataCache();
    public static final int WEEK_OF_MONTH = 4;
    public static final int WEEK_OF_YEAR = 3;
    public static final int YEAR = 1;
    public static final int YEAR_WOY = 17;
    public static final int ZONE_OFFSET = 15;
    private static final long serialVersionUID = 6222646104888790989L;
    private ULocale actualLocale;
    private transient boolean areAllFieldsSet;
    private transient boolean areFieldsSet;
    private transient boolean areFieldsVirtuallySet;
    private transient int[] fields;
    private int firstDayOfWeek;
    private transient int gregorianDayOfMonth;
    private transient int gregorianDayOfYear;
    private transient int gregorianMonth;
    private transient int gregorianYear;
    private transient int internalSetMask;
    private transient boolean isTimeSet;
    private boolean lenient;
    private int minimalDaysInFirstWeek;
    private transient int nextStamp;
    private int repeatedWallTime;
    private int skippedWallTime;
    private transient int[] stamp;
    private long time;
    private ULocale validLocale;
    private int weekendCease;
    private int weekendCeaseMillis;
    private int weekendOnset;
    private int weekendOnsetMillis;
    private TimeZone zone;

    /* access modifiers changed from: protected */
    public abstract int handleComputeMonthStart(int i, int i2, boolean z);

    /* access modifiers changed from: protected */
    public abstract int handleGetExtendedYear();

    /* access modifiers changed from: protected */
    public abstract int handleGetLimit(int i, int i2);

    protected Calendar() {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
    }

    protected Calendar(TimeZone zone2, Locale aLocale) {
        this(zone2, ULocale.forLocale(aLocale));
    }

    protected Calendar(TimeZone zone2, ULocale locale) {
        this.lenient = true;
        this.repeatedWallTime = 0;
        this.skippedWallTime = 0;
        this.nextStamp = 2;
        this.zone = zone2;
        setWeekData(getRegionForCalendar(locale));
        setCalendarLocale(locale);
        initInternal();
    }

    private void setCalendarLocale(ULocale locale) {
        ULocale calLocale = locale;
        if (!(locale.getVariant().length() == 0 && locale.getKeywords() == null)) {
            StringBuilder buf = new StringBuilder();
            buf.append(locale.getLanguage());
            String script = locale.getScript();
            if (script.length() > 0) {
                buf.append("_");
                buf.append(script);
            }
            String region = locale.getCountry();
            if (region.length() > 0) {
                buf.append("_");
                buf.append(region);
            }
            String calType = locale.getKeywordValue("calendar");
            if (calType != null) {
                buf.append("@calendar=");
                buf.append(calType);
            }
            calLocale = new ULocale(buf.toString());
        }
        setLocale(calLocale, calLocale);
    }

    private void recalculateStamp() {
        int[] iArr;
        this.nextStamp = 1;
        for (int j = 0; j < this.stamp.length; j++) {
            int currentValue = STAMP_MAX;
            int index = -1;
            int i = 0;
            while (true) {
                iArr = this.stamp;
                if (i >= iArr.length) {
                    break;
                }
                if (iArr[i] > this.nextStamp && iArr[i] < currentValue) {
                    currentValue = iArr[i];
                    index = i;
                }
                i++;
            }
            if (index < 0) {
                break;
            }
            int i2 = this.nextStamp + 1;
            this.nextStamp = i2;
            iArr[index] = i2;
        }
        this.nextStamp++;
    }

    private void initInternal() {
        this.fields = handleCreateFields();
        int[] iArr = this.fields;
        if (iArr == null || iArr.length < 23 || iArr.length > 32) {
            throw new IllegalStateException("Invalid fields[]");
        }
        this.stamp = new int[iArr.length];
        int mask = 4718695;
        for (int i = 23; i < this.fields.length; i++) {
            mask |= 1 << i;
        }
        this.internalSetMask = mask;
    }

    public static Calendar getInstance() {
        return getInstanceInternal(null, null);
    }

    public static Calendar getInstance(TimeZone zone2) {
        return getInstanceInternal(zone2, null);
    }

    public static Calendar getInstance(Locale aLocale) {
        return getInstanceInternal(null, ULocale.forLocale(aLocale));
    }

    public static Calendar getInstance(ULocale locale) {
        return getInstanceInternal(null, locale);
    }

    public static Calendar getInstance(TimeZone zone2, Locale aLocale) {
        return getInstanceInternal(zone2, ULocale.forLocale(aLocale));
    }

    public static Calendar getInstance(TimeZone zone2, ULocale locale) {
        return getInstanceInternal(zone2, locale);
    }

    private static Calendar getInstanceInternal(TimeZone tz, ULocale locale) {
        if (locale == null) {
            locale = ULocale.getDefault(ULocale.Category.FORMAT);
        }
        if (tz == null) {
            tz = TimeZone.getDefault();
        }
        Calendar cal = createInstance(locale);
        cal.setTimeZone(tz);
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal;
    }

    private static String getRegionForCalendar(ULocale loc) {
        String region = ULocale.getRegionForSupplementalData(loc, true);
        if (region.length() == 0) {
            return "001";
        }
        return region;
    }

    private static CalType getCalendarTypeForLocale(ULocale l) {
        String s = CalendarUtil.getCalendarType(l);
        if (s != null) {
            String s2 = s.toLowerCase(Locale.ENGLISH);
            CalType[] values = CalType.values();
            for (CalType type : values) {
                if (s2.equals(type.getId())) {
                    return type;
                }
            }
        }
        return CalType.UNKNOWN;
    }

    private static Calendar createInstance(ULocale locale) {
        TimeZone zone2 = TimeZone.getDefault();
        CalType calType = getCalendarTypeForLocale(locale);
        if (calType == CalType.UNKNOWN) {
            calType = CalType.GREGORIAN;
        }
        switch (calType) {
            case GREGORIAN:
                return new GregorianCalendar(zone2, locale);
            case ISO8601:
                Calendar cal = new GregorianCalendar(zone2, locale);
                cal.setFirstDayOfWeek(2);
                cal.setMinimalDaysInFirstWeek(4);
                return cal;
            case BUDDHIST:
                return new BuddhistCalendar(zone2, locale);
            case CHINESE:
                return new ChineseCalendar(zone2, locale);
            case COPTIC:
                return new CopticCalendar(zone2, locale);
            case DANGI:
                return new DangiCalendar(zone2, locale);
            case ETHIOPIC:
                return new EthiopicCalendar(zone2, locale);
            case ETHIOPIC_AMETE_ALEM:
                Calendar cal2 = new EthiopicCalendar(zone2, locale);
                ((EthiopicCalendar) cal2).setAmeteAlemEra(true);
                return cal2;
            case HEBREW:
                return new HebrewCalendar(zone2, locale);
            case INDIAN:
                return new IndianCalendar(zone2, locale);
            case ISLAMIC_CIVIL:
            case ISLAMIC_UMALQURA:
            case ISLAMIC_TBLA:
            case ISLAMIC_RGSA:
            case ISLAMIC:
                return new IslamicCalendar(zone2, locale);
            case JAPANESE:
                return new JapaneseCalendar(zone2, locale);
            case PERSIAN:
                return new PersianCalendar(zone2, locale);
            case ROC:
                return new TaiwanCalendar(zone2, locale);
            default:
                throw new IllegalArgumentException("Unknown calendar type");
        }
    }

    public static Locale[] getAvailableLocales() {
        return ICUResourceBundle.getAvailableLocales();
    }

    public static ULocale[] getAvailableULocales() {
        return ICUResourceBundle.getAvailableULocales();
    }

    public static final String[] getKeywordValuesForLocale(String key, ULocale locale, boolean commonlyUsed) {
        UResourceBundle order;
        String prefRegion = ULocale.getRegionForSupplementalData(locale, true);
        ArrayList<String> values = new ArrayList<>();
        UResourceBundle calPref = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("calendarPreferenceData");
        try {
            order = calPref.get(prefRegion);
        } catch (MissingResourceException e) {
            order = calPref.get("001");
        }
        String[] caltypes = order.getStringArray();
        if (commonlyUsed) {
            return caltypes;
        }
        for (String str : caltypes) {
            values.add(str);
        }
        CalType[] values2 = CalType.values();
        for (CalType t : values2) {
            if (!values.contains(t.getId())) {
                values.add(t.getId());
            }
        }
        return (String[]) values.toArray(new String[values.size()]);
    }

    public final Date getTime() {
        return new Date(getTimeInMillis());
    }

    public final void setTime(Date date) {
        setTimeInMillis(date.getTime());
    }

    public long getTimeInMillis() {
        if (!this.isTimeSet) {
            updateTime();
        }
        return this.time;
    }

    public void setTimeInMillis(long millis) {
        if (millis > 183882168921600000L) {
            if (isLenient()) {
                millis = 183882168921600000L;
            } else {
                throw new IllegalArgumentException("millis value greater than upper bounds for a Calendar : " + millis);
            }
        } else if (millis < -184303902528000000L) {
            if (isLenient()) {
                millis = -184303902528000000L;
            } else {
                throw new IllegalArgumentException("millis value less than lower bounds for a Calendar : " + millis);
            }
        }
        this.time = millis;
        this.areAllFieldsSet = false;
        this.areFieldsSet = false;
        this.areFieldsVirtuallySet = true;
        this.isTimeSet = true;
        int i = 0;
        while (true) {
            int[] iArr = this.fields;
            if (i < iArr.length) {
                this.stamp[i] = 0;
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    public final int get(int field) {
        complete();
        return this.fields[field];
    }

    /* access modifiers changed from: protected */
    public final int internalGet(int field) {
        return this.fields[field];
    }

    /* access modifiers changed from: protected */
    public final int internalGet(int field, int defaultValue) {
        return this.stamp[field] > 0 ? this.fields[field] : defaultValue;
    }

    public final void set(int field, int value) {
        if (this.areFieldsVirtuallySet) {
            computeFields();
        }
        this.fields[field] = value;
        if (this.nextStamp == STAMP_MAX) {
            recalculateStamp();
        }
        int[] iArr = this.stamp;
        int i = this.nextStamp;
        this.nextStamp = i + 1;
        iArr[field] = i;
        this.areFieldsVirtuallySet = false;
        this.areFieldsSet = false;
        this.isTimeSet = false;
    }

    public final void set(int year, int month, int date) {
        set(1, year);
        set(2, month);
        set(5, date);
    }

    public final void set(int year, int month, int date, int hour, int minute) {
        set(1, year);
        set(2, month);
        set(5, date);
        set(11, hour);
        set(12, minute);
    }

    public final void set(int year, int month, int date, int hour, int minute, int second) {
        set(1, year);
        set(2, month);
        set(5, date);
        set(11, hour);
        set(12, minute);
        set(13, second);
    }

    private static int gregoYearFromIslamicStart(int year) {
        int shift;
        int i = 0;
        if (year >= 1397) {
            int i2 = ((year - 1397) / 67) * 2;
            if ((year - 1397) % 67 >= 33) {
                i = 1;
            }
            shift = i2 + i;
        } else {
            int i3 = (((year - 1396) / 67) - 1) * 2;
            if ((-(year - 1396)) % 67 <= 33) {
                i = 1;
            }
            shift = i3 + i;
        }
        return (year + 579) - shift;
    }

    @Deprecated
    public final int getRelatedYear() {
        int year = get(19);
        CalType type = CalType.GREGORIAN;
        String typeString = getType();
        CalType[] values = CalType.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            CalType testType = values[i];
            if (typeString.equals(testType.getId())) {
                type = testType;
                break;
            }
            i++;
        }
        switch (type) {
            case CHINESE:
                return year - 2637;
            case COPTIC:
                return year + UCharacter.UnicodeBlock.GUNJALA_GONDI_ID;
            case DANGI:
                return year - 2333;
            case ETHIOPIC:
                return year + 8;
            case ETHIOPIC_AMETE_ALEM:
                return year - 5492;
            case HEBREW:
                return year - 3760;
            case INDIAN:
                return year + 79;
            case ISLAMIC_CIVIL:
            case ISLAMIC_UMALQURA:
            case ISLAMIC_TBLA:
            case ISLAMIC_RGSA:
            case ISLAMIC:
                return gregoYearFromIslamicStart(year);
            case JAPANESE:
            default:
                return year;
            case PERSIAN:
                return year + 622;
        }
    }

    private static int firstIslamicStartYearFromGrego(int year) {
        int shift;
        int i = 0;
        if (year >= 1977) {
            int i2 = ((year - 1977) / 65) * 2;
            if ((year - 1977) % 65 >= 32) {
                i = 1;
            }
            shift = i2 + i;
        } else {
            int i3 = (((year - 1976) / 65) - 1) * 2;
            if ((-(year - 1976)) % 65 <= 32) {
                i = 1;
            }
            shift = i3 + i;
        }
        return (year - 579) + shift;
    }

    @Deprecated
    public final void setRelatedYear(int year) {
        CalType type = CalType.GREGORIAN;
        String typeString = getType();
        CalType[] values = CalType.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            CalType testType = values[i];
            if (typeString.equals(testType.getId())) {
                type = testType;
                break;
            }
            i++;
        }
        switch (type) {
            case CHINESE:
                year += 2637;
                break;
            case COPTIC:
                year -= 284;
                break;
            case DANGI:
                year += 2333;
                break;
            case ETHIOPIC:
                year -= 8;
                break;
            case ETHIOPIC_AMETE_ALEM:
                year += 5492;
                break;
            case HEBREW:
                year += 3760;
                break;
            case INDIAN:
                year -= 79;
                break;
            case ISLAMIC_CIVIL:
            case ISLAMIC_UMALQURA:
            case ISLAMIC_TBLA:
            case ISLAMIC_RGSA:
            case ISLAMIC:
                year = firstIslamicStartYearFromGrego(year);
                break;
            case PERSIAN:
                year -= 622;
                break;
        }
        set(19, year);
    }

    public final void clear() {
        int i = 0;
        while (true) {
            int[] iArr = this.fields;
            if (i < iArr.length) {
                this.stamp[i] = 0;
                iArr[i] = 0;
                i++;
            } else {
                this.areFieldsVirtuallySet = false;
                this.areAllFieldsSet = false;
                this.areFieldsSet = false;
                this.isTimeSet = false;
                return;
            }
        }
    }

    public final void clear(int field) {
        if (this.areFieldsVirtuallySet) {
            computeFields();
        }
        this.fields[field] = 0;
        this.stamp[field] = 0;
        this.areFieldsVirtuallySet = false;
        this.areAllFieldsSet = false;
        this.areFieldsSet = false;
        this.isTimeSet = false;
    }

    public final boolean isSet(int field) {
        return this.areFieldsVirtuallySet || this.stamp[field] != 0;
    }

    /* access modifiers changed from: protected */
    public void complete() {
        if (!this.isTimeSet) {
            updateTime();
        }
        if (!this.areFieldsSet) {
            computeFields();
            this.areFieldsSet = true;
            this.areAllFieldsSet = true;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Calendar that = (Calendar) obj;
        if (!isEquivalentTo(that) || getTimeInMillis() != that.getTime().getTime()) {
            return false;
        }
        return true;
    }

    public boolean isEquivalentTo(Calendar other) {
        return getClass() == other.getClass() && isLenient() == other.isLenient() && getFirstDayOfWeek() == other.getFirstDayOfWeek() && getMinimalDaysInFirstWeek() == other.getMinimalDaysInFirstWeek() && getTimeZone().equals(other.getTimeZone()) && getRepeatedWallTimeOption() == other.getRepeatedWallTimeOption() && getSkippedWallTimeOption() == other.getSkippedWallTimeOption();
    }

    public int hashCode() {
        return (this.lenient ? 1 : 0) | (this.firstDayOfWeek << 1) | (this.minimalDaysInFirstWeek << 4) | (this.repeatedWallTime << 7) | (this.skippedWallTime << 9) | (this.zone.hashCode() << 11);
    }

    private long compare(Object that) {
        long thatMs;
        if (that instanceof Calendar) {
            thatMs = ((Calendar) that).getTimeInMillis();
        } else if (that instanceof Date) {
            thatMs = ((Date) that).getTime();
        } else {
            throw new IllegalArgumentException(that + "is not a Calendar or Date");
        }
        return getTimeInMillis() - thatMs;
    }

    public boolean before(Object when) {
        return compare(when) < 0;
    }

    public boolean after(Object when) {
        return compare(when) > 0;
    }

    /* JADX INFO: Multiple debug info for r0v5 int: [D('cal' android.icu.util.Calendar), D('result' int)] */
    /* JADX INFO: Multiple debug info for r0v9 int: [D('cal' android.icu.util.Calendar), D('result' int)] */
    public int getActualMaximum(int field) {
        if (!(field == 0 || field == 18)) {
            if (field == 5) {
                Calendar cal = (Calendar) clone();
                cal.setLenient(true);
                cal.prepareGetActual(field, false);
                return handleGetMonthLength(cal.get(19), cal.get(2));
            } else if (field == 6) {
                Calendar cal2 = (Calendar) clone();
                cal2.setLenient(true);
                cal2.prepareGetActual(field, false);
                return handleGetYearLength(cal2.get(19));
            } else if (!(field == 7 || field == 20 || field == 21)) {
                switch (field) {
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        break;
                    default:
                        return getActualHelper(field, getLeastMaximum(field), getMaximum(field));
                }
            }
        }
        return getMaximum(field);
    }

    public int getActualMinimum(int field) {
        switch (field) {
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 21:
                return getMinimum(field);
            case 8:
            case 17:
            case 19:
            default:
                return getActualHelper(field, getGreatestMinimum(field), getMinimum(field));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r6 != 19) goto L_0x0055;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepareGetActual(int r6, boolean r7) {
        /*
            r5 = this;
            r0 = 21
            r1 = 0
            r5.set(r0, r1)
            r0 = 1
            if (r6 == r0) goto L_0x004c
            r1 = 2
            r2 = 5
            if (r6 == r1) goto L_0x0044
            r1 = 3
            r3 = 7
            if (r6 == r1) goto L_0x0034
            r4 = 4
            if (r6 == r4) goto L_0x0034
            r4 = 8
            if (r6 == r4) goto L_0x0029
            r0 = 17
            if (r6 == r0) goto L_0x0021
            r0 = 19
            if (r6 == r0) goto L_0x004c
            goto L_0x0055
        L_0x0021:
            int r0 = r5.getGreatestMinimum(r1)
            r5.set(r1, r0)
            goto L_0x0055
        L_0x0029:
            r5.set(r2, r0)
            int r0 = r5.get(r3)
            r5.set(r3, r0)
            goto L_0x0055
        L_0x0034:
            int r1 = r5.firstDayOfWeek
            if (r7 == 0) goto L_0x0040
            int r2 = r1 + 6
            int r1 = r2 % 7
            if (r1 >= r0) goto L_0x0040
            int r1 = r1 + 7
        L_0x0040:
            r5.set(r3, r1)
            goto L_0x0055
        L_0x0044:
            int r0 = r5.getGreatestMinimum(r2)
            r5.set(r2, r0)
            goto L_0x0055
        L_0x004c:
            r0 = 6
            int r1 = r5.getGreatestMinimum(r0)
            r5.set(r0, r1)
        L_0x0055:
            int r0 = r5.getGreatestMinimum(r6)
            r5.set(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.Calendar.prepareGetActual(int, boolean):void");
    }

    private int getActualHelper(int field, int startValue, int endValue) {
        if (startValue == endValue) {
            return startValue;
        }
        boolean z = true;
        int delta = endValue > startValue ? 1 : -1;
        Calendar work = (Calendar) clone();
        work.complete();
        work.setLenient(true);
        if (delta >= 0) {
            z = false;
        }
        work.prepareGetActual(field, z);
        work.set(field, startValue);
        if (work.get(field) != startValue && field != 4 && delta > 0) {
            return startValue;
        }
        int result = startValue;
        do {
            startValue += delta;
            work.add(field, delta);
            if (work.get(field) != startValue) {
                break;
            }
            result = startValue;
        } while (startValue != endValue);
        return result;
    }

    public final void roll(int field, boolean up) {
        roll(field, up ? 1 : -1);
    }

    public void roll(int field, int amount) {
        int start;
        int start2;
        int amount2 = amount;
        if (amount2 != 0) {
            complete();
            int i = 1;
            switch (field) {
                case 0:
                case 5:
                case 9:
                case 12:
                case 13:
                case 14:
                case 21:
                    int min = getActualMinimum(field);
                    int gap = (getActualMaximum(field) - min) + 1;
                    int value = ((internalGet(field) + amount2) - min) % gap;
                    if (value < 0) {
                        value += gap;
                    }
                    set(field, value + min);
                    return;
                case 1:
                case 17:
                    boolean era0WithYearsThatGoBackwards = false;
                    int era = get(0);
                    if (era == 0) {
                        String calType = getType();
                        if (calType.equals("gregorian") || calType.equals("roc") || calType.equals("coptic")) {
                            amount2 = -amount2;
                            era0WithYearsThatGoBackwards = true;
                        }
                    }
                    int newYear = internalGet(field) + amount2;
                    if (era > 0 || newYear >= 1) {
                        int maxYear = getActualMaximum(field);
                        if (maxYear < 32768) {
                            if (newYear < 1) {
                                newYear = maxYear - ((-newYear) % maxYear);
                            } else if (newYear > maxYear) {
                                newYear = ((newYear - 1) % maxYear) + 1;
                            }
                        } else if (newYear < 1) {
                            newYear = 1;
                        }
                    } else if (era0WithYearsThatGoBackwards) {
                        newYear = 1;
                    }
                    set(field, newYear);
                    pinField(2);
                    pinField(5);
                    return;
                case 2:
                    int max = getActualMaximum(2);
                    int mon = (internalGet(2) + amount2) % (max + 1);
                    if (mon < 0) {
                        mon += max + 1;
                    }
                    set(2, mon);
                    pinField(5);
                    return;
                case 3:
                    int dow = internalGet(7) - getFirstDayOfWeek();
                    if (dow < 0) {
                        dow += 7;
                    }
                    int fdy = ((dow - internalGet(6)) + 1) % 7;
                    if (fdy < 0) {
                        fdy += 7;
                    }
                    if (7 - fdy < getMinimalDaysInFirstWeek()) {
                        start = 8 - fdy;
                    } else {
                        start = 1 - fdy;
                    }
                    int yearLen = getActualMaximum(6);
                    int gap2 = ((yearLen + 7) - (((yearLen - internalGet(6)) + dow) % 7)) - start;
                    int day_of_year = ((internalGet(6) + (amount2 * 7)) - start) % gap2;
                    if (day_of_year < 0) {
                        day_of_year += gap2;
                    }
                    int day_of_year2 = day_of_year + start;
                    if (day_of_year2 < 1) {
                        day_of_year2 = 1;
                    }
                    if (day_of_year2 > yearLen) {
                        day_of_year2 = yearLen;
                    }
                    set(6, day_of_year2);
                    clear(2);
                    return;
                case 4:
                    int dow2 = internalGet(7) - getFirstDayOfWeek();
                    if (dow2 < 0) {
                        dow2 += 7;
                    }
                    int fdm = ((dow2 - internalGet(5)) + 1) % 7;
                    if (fdm < 0) {
                        fdm += 7;
                    }
                    if (7 - fdm < getMinimalDaysInFirstWeek()) {
                        start2 = 8 - fdm;
                    } else {
                        start2 = 1 - fdm;
                    }
                    int monthLen = getActualMaximum(5);
                    int gap3 = ((monthLen + 7) - (((monthLen - internalGet(5)) + dow2) % 7)) - start2;
                    int day_of_month = ((internalGet(5) + (amount2 * 7)) - start2) % gap3;
                    if (day_of_month < 0) {
                        day_of_month += gap3;
                    }
                    int day_of_month2 = day_of_month + start2;
                    if (day_of_month2 < 1) {
                        day_of_month2 = 1;
                    }
                    if (day_of_month2 > monthLen) {
                        day_of_month2 = monthLen;
                    }
                    set(5, day_of_month2);
                    return;
                case 6:
                    long min2 = this.time - (((long) (internalGet(6) - 1)) * 86400000);
                    int yearLength = getActualMaximum(6);
                    this.time = ((this.time + (((long) amount2) * 86400000)) - min2) % (((long) yearLength) * 86400000);
                    long j = this.time;
                    if (j < 0) {
                        this.time = j + (((long) yearLength) * 86400000);
                    }
                    setTimeInMillis(this.time + min2);
                    return;
                case 7:
                case 18:
                    long delta = ((long) amount2) * 86400000;
                    int leadDays = internalGet(field);
                    if (field == 7) {
                        i = getFirstDayOfWeek();
                    }
                    int leadDays2 = leadDays - i;
                    if (leadDays2 < 0) {
                        leadDays2 += 7;
                    }
                    long j2 = this.time;
                    long min22 = j2 - (((long) leadDays2) * 86400000);
                    this.time = ((j2 + delta) - min22) % 604800000;
                    long j3 = this.time;
                    if (j3 < 0) {
                        this.time = j3 + 604800000;
                    }
                    setTimeInMillis(this.time + min22);
                    return;
                case 8:
                    int preWeeks = (internalGet(5) - 1) / 7;
                    long j4 = this.time;
                    long min23 = j4 - (((long) preWeeks) * 604800000);
                    long gap22 = ((long) (preWeeks + ((getActualMaximum(5) - internalGet(5)) / 7) + 1)) * 604800000;
                    this.time = ((j4 + (((long) amount2) * 604800000)) - min23) % gap22;
                    long j5 = this.time;
                    if (j5 < 0) {
                        this.time = j5 + gap22;
                    }
                    setTimeInMillis(this.time + min23);
                    return;
                case 10:
                case 11:
                    long start3 = getTimeInMillis();
                    int oldHour = internalGet(field);
                    int max2 = getMaximum(field);
                    int newHour = (oldHour + amount2) % (max2 + 1);
                    if (newHour < 0) {
                        newHour += max2 + 1;
                    }
                    setTimeInMillis(((((long) newHour) - ((long) oldHour)) * RelativeDateTimeFormatter.HOUR_IN_MILLIS) + start3);
                    return;
                case 15:
                case 16:
                default:
                    throw new IllegalArgumentException("Calendar.roll(" + fieldName(field) + ") not supported");
                case 19:
                    set(field, internalGet(field) + amount2);
                    pinField(2);
                    pinField(5);
                    return;
                case 20:
                    set(field, internalGet(field) + amount2);
                    return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void add(int r21, int r22) {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.Calendar.add(int, int):void");
    }

    public String getDisplayName(Locale loc) {
        return getClass().getName();
    }

    public String getDisplayName(ULocale loc) {
        return getClass().getName();
    }

    public int compareTo(Calendar that) {
        long v = getTimeInMillis() - that.getTimeInMillis();
        if (v < 0) {
            return -1;
        }
        return v > 0 ? 1 : 0;
    }

    public DateFormat getDateTimeFormat(int dateStyle, int timeStyle, Locale loc) {
        return formatHelper(this, ULocale.forLocale(loc), dateStyle, timeStyle);
    }

    public DateFormat getDateTimeFormat(int dateStyle, int timeStyle, ULocale loc) {
        return formatHelper(this, loc, dateStyle, timeStyle);
    }

    /* access modifiers changed from: protected */
    public DateFormat handleGetDateFormat(String pattern, Locale locale) {
        return handleGetDateFormat(pattern, (String) null, ULocale.forLocale(locale));
    }

    /* access modifiers changed from: protected */
    public DateFormat handleGetDateFormat(String pattern, String override, Locale locale) {
        return handleGetDateFormat(pattern, override, ULocale.forLocale(locale));
    }

    /* access modifiers changed from: protected */
    public DateFormat handleGetDateFormat(String pattern, ULocale locale) {
        return handleGetDateFormat(pattern, (String) null, locale);
    }

    /* access modifiers changed from: protected */
    public DateFormat handleGetDateFormat(String pattern, String override, ULocale locale) {
        FormatConfiguration fmtConfig = new FormatConfiguration();
        fmtConfig.pattern = pattern;
        fmtConfig.override = override;
        fmtConfig.formatData = new DateFormatSymbols(this, locale);
        fmtConfig.loc = locale;
        fmtConfig.cal = this;
        return SimpleDateFormat.getInstance(fmtConfig);
    }

    private static DateFormat formatHelper(Calendar cal, ULocale loc, int dateStyle, int timeStyle) {
        String pattern;
        if (timeStyle < -1 || timeStyle > 3) {
            throw new IllegalArgumentException("Illegal time style " + timeStyle);
        } else if (dateStyle < -1 || dateStyle > 3) {
            throw new IllegalArgumentException("Illegal date style " + dateStyle);
        } else {
            PatternData patternData = PatternData.make(cal, loc);
            String override = null;
            if (timeStyle >= 0 && dateStyle >= 0) {
                pattern = SimpleFormatterImpl.formatRawPattern(patternData.getDateTimePattern(dateStyle), 2, 2, patternData.patterns[timeStyle], patternData.patterns[dateStyle + 4]);
                if (patternData.overrides != null) {
                    override = mergeOverrideStrings(patternData.patterns[dateStyle + 4], patternData.patterns[timeStyle], patternData.overrides[dateStyle + 4], patternData.overrides[timeStyle]);
                }
            } else if (timeStyle >= 0) {
                pattern = patternData.patterns[timeStyle];
                if (patternData.overrides != null) {
                    override = patternData.overrides[timeStyle];
                }
            } else if (dateStyle >= 0) {
                pattern = patternData.patterns[dateStyle + 4];
                if (patternData.overrides != null) {
                    override = patternData.overrides[dateStyle + 4];
                }
            } else {
                throw new IllegalArgumentException("No date or time style specified");
            }
            DateFormat result = cal.handleGetDateFormat(pattern, override, loc);
            result.setCalendar(cal);
            return result;
        }
    }

    public static String getDateTimeFormatString(ULocale loc, String calType, int dateStyle, int timeStyle) {
        if (timeStyle < -1 || timeStyle > 3) {
            throw new IllegalArgumentException("Illegal time style " + timeStyle);
        } else if (dateStyle < -1 || dateStyle > 3) {
            throw new IllegalArgumentException("Illegal date style " + dateStyle);
        } else {
            PatternData patternData = PatternData.make(loc, calType);
            if (timeStyle >= 0 && dateStyle >= 0) {
                return SimpleFormatterImpl.formatRawPattern(patternData.getDateTimePattern(dateStyle), 2, 2, patternData.patterns[timeStyle], patternData.patterns[dateStyle + 4]);
            } else if (timeStyle >= 0) {
                return patternData.patterns[timeStyle];
            } else {
                if (dateStyle >= 0) {
                    return patternData.patterns[dateStyle + 4];
                }
                throw new IllegalArgumentException("No date or time style specified");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class PatternData {
        private String[] overrides;
        private String[] patterns;

        public PatternData(String[] patterns2, String[] overrides2) {
            this.patterns = patterns2;
            this.overrides = overrides2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String getDateTimePattern(int dateStyle) {
            int glueIndex = 8;
            if (this.patterns.length >= 13) {
                glueIndex = 8 + dateStyle + 1;
            }
            return this.patterns[glueIndex];
        }

        /* access modifiers changed from: private */
        public static PatternData make(Calendar cal, ULocale loc) {
            return make(loc, cal.getType());
        }

        /* access modifiers changed from: private */
        public static PatternData make(ULocale loc, String calType) {
            String key = loc.getBaseName() + "+" + calType;
            PatternData patternData = (PatternData) Calendar.PATTERN_CACHE.get(key);
            if (patternData == null) {
                try {
                    patternData = Calendar.getPatternData(loc, calType);
                } catch (MissingResourceException e) {
                    patternData = new PatternData(Calendar.DEFAULT_PATTERNS, null);
                }
                Calendar.PATTERN_CACHE.put(key, patternData);
            }
            return patternData;
        }
    }

    /* access modifiers changed from: private */
    public static PatternData getPatternData(ULocale locale, String calType) {
        ICUResourceBundle rb = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale);
        ICUResourceBundle dtPatternsRb = rb.findWithFallback("calendar/" + calType + "/DateTimePatterns");
        if (dtPatternsRb == null) {
            dtPatternsRb = rb.getWithFallback("calendar/gregorian/DateTimePatterns");
        }
        int patternsSize = dtPatternsRb.getSize();
        String[] dateTimePatterns = new String[patternsSize];
        String[] dateTimePatternsOverrides = new String[patternsSize];
        for (int i = 0; i < patternsSize; i++) {
            ICUResourceBundle concatenationPatternRb = (ICUResourceBundle) dtPatternsRb.get(i);
            int type = concatenationPatternRb.getType();
            if (type == 0) {
                dateTimePatterns[i] = concatenationPatternRb.getString();
            } else if (type == 8) {
                dateTimePatterns[i] = concatenationPatternRb.getString(0);
                dateTimePatternsOverrides[i] = concatenationPatternRb.getString(1);
            }
        }
        return new PatternData(dateTimePatterns, dateTimePatternsOverrides);
    }

    @Deprecated
    public static String getDateTimePattern(Calendar cal, ULocale uLocale, int dateStyle) {
        return PatternData.make(cal, uLocale).getDateTimePattern(dateStyle);
    }

    private static String mergeOverrideStrings(String datePattern, String timePattern, String dateOverride, String timeOverride) {
        if (dateOverride == null && timeOverride == null) {
            return null;
        }
        if (dateOverride == null) {
            return expandOverride(timePattern, timeOverride);
        }
        if (timeOverride == null) {
            return expandOverride(datePattern, dateOverride);
        }
        if (dateOverride.equals(timeOverride)) {
            return dateOverride;
        }
        return expandOverride(datePattern, dateOverride) + ";" + expandOverride(timePattern, timeOverride);
    }

    private static String expandOverride(String pattern, String override) {
        if (override.indexOf(61) >= 0) {
            return override;
        }
        boolean inQuotes = false;
        char prevChar = ' ';
        StringBuilder result = new StringBuilder();
        StringCharacterIterator it = new StringCharacterIterator(pattern);
        for (char c = it.first(); c != 65535; c = it.next()) {
            if (c == '\'') {
                inQuotes = !inQuotes;
            } else if (!inQuotes && c != prevChar) {
                if (result.length() > 0) {
                    result.append(";");
                }
                result.append(c);
                result.append("=");
                result.append(override);
            }
            prevChar = c;
        }
        return result.toString();
    }

    @Deprecated
    public static class FormatConfiguration {
        private Calendar cal;
        private DateFormatSymbols formatData;
        private ULocale loc;
        private String override;
        private String pattern;

        private FormatConfiguration() {
        }

        @Deprecated
        public String getPatternString() {
            return this.pattern;
        }

        @Deprecated
        public String getOverrideString() {
            return this.override;
        }

        @Deprecated
        public Calendar getCalendar() {
            return this.cal;
        }

        @Deprecated
        public ULocale getLocale() {
            return this.loc;
        }

        @Deprecated
        public DateFormatSymbols getDateFormatSymbols() {
            return this.formatData;
        }
    }

    /* access modifiers changed from: protected */
    public void pinField(int field) {
        int max = getActualMaximum(field);
        int min = getActualMinimum(field);
        int[] iArr = this.fields;
        if (iArr[field] > max) {
            set(field, max);
        } else if (iArr[field] < min) {
            set(field, min);
        }
    }

    /* access modifiers changed from: protected */
    public int weekNumber(int desiredDay, int dayOfPeriod, int dayOfWeek) {
        int periodStartDayOfWeek = (((dayOfWeek - getFirstDayOfWeek()) - dayOfPeriod) + 1) % 7;
        if (periodStartDayOfWeek < 0) {
            periodStartDayOfWeek += 7;
        }
        int weekNo = ((desiredDay + periodStartDayOfWeek) - 1) / 7;
        if (7 - periodStartDayOfWeek >= getMinimalDaysInFirstWeek()) {
            return weekNo + 1;
        }
        return weekNo;
    }

    /* access modifiers changed from: protected */
    public final int weekNumber(int dayOfPeriod, int dayOfWeek) {
        return weekNumber(dayOfPeriod, dayOfPeriod, dayOfWeek);
    }

    public int fieldDifference(Date when, int field) {
        int min = 0;
        long startMs = getTimeInMillis();
        long targetMs = when.getTime();
        if (startMs < targetMs) {
            int max = 1;
            while (true) {
                setTimeInMillis(startMs);
                add(field, max);
                long ms = getTimeInMillis();
                if (ms == targetMs) {
                    return max;
                }
                if (ms > targetMs) {
                    while (max - min > 1) {
                        int t = ((max - min) / 2) + min;
                        setTimeInMillis(startMs);
                        add(field, t);
                        long ms2 = getTimeInMillis();
                        if (ms2 == targetMs) {
                            return t;
                        }
                        if (ms2 > targetMs) {
                            max = t;
                        } else {
                            min = t;
                        }
                    }
                } else if (max < Integer.MAX_VALUE) {
                    min = max;
                    max <<= 1;
                    if (max < 0) {
                        max = Integer.MAX_VALUE;
                    }
                } else {
                    throw new RuntimeException();
                }
            }
        } else if (startMs > targetMs) {
            int max2 = -1;
            do {
                setTimeInMillis(startMs);
                add(field, max2);
                long ms3 = getTimeInMillis();
                if (ms3 == targetMs) {
                    return max2;
                }
                if (ms3 < targetMs) {
                    while (min - max2 > 1) {
                        int t2 = ((max2 - min) / 2) + min;
                        setTimeInMillis(startMs);
                        add(field, t2);
                        long ms4 = getTimeInMillis();
                        if (ms4 == targetMs) {
                            return t2;
                        }
                        if (ms4 < targetMs) {
                            max2 = t2;
                        } else {
                            min = t2;
                        }
                    }
                } else {
                    min = max2;
                    max2 <<= 1;
                }
            } while (max2 != 0);
            throw new RuntimeException();
        }
        setTimeInMillis(startMs);
        add(field, min);
        return min;
    }

    public void setTimeZone(TimeZone value) {
        this.zone = value;
        this.areFieldsSet = false;
    }

    public TimeZone getTimeZone() {
        return this.zone;
    }

    public void setLenient(boolean lenient2) {
        this.lenient = lenient2;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public void setRepeatedWallTimeOption(int option) {
        if (option == 0 || option == 1) {
            this.repeatedWallTime = option;
            return;
        }
        throw new IllegalArgumentException("Illegal repeated wall time option - " + option);
    }

    public int getRepeatedWallTimeOption() {
        return this.repeatedWallTime;
    }

    public void setSkippedWallTimeOption(int option) {
        if (option == 0 || option == 1 || option == 2) {
            this.skippedWallTime = option;
            return;
        }
        throw new IllegalArgumentException("Illegal skipped wall time option - " + option);
    }

    public int getSkippedWallTimeOption() {
        return this.skippedWallTime;
    }

    public void setFirstDayOfWeek(int value) {
        if (this.firstDayOfWeek == value) {
            return;
        }
        if (value < 1 || value > 7) {
            throw new IllegalArgumentException("Invalid day of week");
        }
        this.firstDayOfWeek = value;
        this.areFieldsSet = false;
    }

    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public void setMinimalDaysInFirstWeek(int value) {
        if (value < 1) {
            value = 1;
        } else if (value > 7) {
            value = 7;
        }
        if (this.minimalDaysInFirstWeek != value) {
            this.minimalDaysInFirstWeek = value;
            this.areFieldsSet = false;
        }
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDaysInFirstWeek;
    }

    /* JADX INFO: Multiple debug info for r0v2 int: [D('minDaysInFirst' int), D('limit' int)] */
    /* access modifiers changed from: protected */
    public int getLimit(int field, int limitType) {
        switch (field) {
            case 4:
                if (limitType == 0) {
                    if (getMinimalDaysInFirstWeek() == 1) {
                        return 1;
                    }
                    return 0;
                } else if (limitType == 1) {
                    return 1;
                } else {
                    int minDaysInFirst = getMinimalDaysInFirstWeek();
                    int daysInMonth = handleGetLimit(5, limitType);
                    if (limitType == 2) {
                        return ((7 - minDaysInFirst) + daysInMonth) / 7;
                    }
                    return ((daysInMonth + 6) + (7 - minDaysInFirst)) / 7;
                }
            case 5:
            case 6:
            case 8:
            case 17:
            case 19:
            default:
                return handleGetLimit(field, limitType);
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 21:
            case 22:
                return LIMITS[field][limitType];
        }
    }

    public final int getMinimum(int field) {
        return getLimit(field, 0);
    }

    public final int getMaximum(int field) {
        return getLimit(field, 3);
    }

    public final int getGreatestMinimum(int field) {
        return getLimit(field, 1);
    }

    public final int getLeastMaximum(int field) {
        return getLimit(field, 2);
    }

    @Deprecated
    public int getDayOfWeekType(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new IllegalArgumentException("Invalid day of week");
        }
        int i = this.weekendOnset;
        int i2 = this.weekendCease;
        if (i != i2) {
            if (i < i2) {
                if (dayOfWeek < i || dayOfWeek > i2) {
                    return 0;
                }
            } else if (dayOfWeek > i2 && dayOfWeek < i) {
                return 0;
            }
            if (dayOfWeek == this.weekendOnset) {
                if (this.weekendOnsetMillis == 0) {
                    return 1;
                }
                return 2;
            } else if (dayOfWeek != this.weekendCease || this.weekendCeaseMillis >= 86400000) {
                return 1;
            } else {
                return 3;
            }
        } else if (dayOfWeek != i) {
            return 0;
        } else {
            if (this.weekendOnsetMillis == 0) {
                return 1;
            }
            return 2;
        }
    }

    @Deprecated
    public int getWeekendTransition(int dayOfWeek) {
        if (dayOfWeek == this.weekendOnset) {
            return this.weekendOnsetMillis;
        }
        if (dayOfWeek == this.weekendCease) {
            return this.weekendCeaseMillis;
        }
        throw new IllegalArgumentException("Not weekend transition day");
    }

    public boolean isWeekend(Date date) {
        setTime(date);
        return isWeekend();
    }

    public boolean isWeekend() {
        int dow = get(7);
        int dowt = getDayOfWeekType(dow);
        if (dowt == 0) {
            return false;
        }
        if (dowt == 1) {
            return true;
        }
        int millisInDay = internalGet(14) + ((internalGet(13) + ((internalGet(12) + (internalGet(11) * 60)) * 60)) * 1000);
        int transition = getWeekendTransition(dow);
        if (dowt == 2) {
            if (millisInDay >= transition) {
                return true;
            }
            return false;
        } else if (millisInDay < transition) {
            return true;
        } else {
            return false;
        }
    }

    public Object clone() {
        try {
            Calendar other = (Calendar) super.clone();
            other.fields = new int[this.fields.length];
            other.stamp = new int[this.fields.length];
            System.arraycopy((Object) this.fields, 0, (Object) other.fields, 0, this.fields.length);
            System.arraycopy((Object) this.stamp, 0, (Object) other.stamp, 0, this.fields.length);
            other.zone = (TimeZone) this.zone.clone();
            return other;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public String toString() {
        String str;
        StringBuilder buffer = new StringBuilder();
        buffer.append(getClass().getName());
        buffer.append("[time=");
        buffer.append(this.isTimeSet ? String.valueOf(this.time) : "?");
        buffer.append(",areFieldsSet=");
        buffer.append(this.areFieldsSet);
        buffer.append(",areAllFieldsSet=");
        buffer.append(this.areAllFieldsSet);
        buffer.append(",lenient=");
        buffer.append(this.lenient);
        buffer.append(",zone=");
        buffer.append((Object) this.zone);
        buffer.append(",firstDayOfWeek=");
        buffer.append(this.firstDayOfWeek);
        buffer.append(",minimalDaysInFirstWeek=");
        buffer.append(this.minimalDaysInFirstWeek);
        buffer.append(",repeatedWallTime=");
        buffer.append(this.repeatedWallTime);
        buffer.append(",skippedWallTime=");
        buffer.append(this.skippedWallTime);
        for (int i = 0; i < this.fields.length; i++) {
            buffer.append(',');
            buffer.append(fieldName(i));
            buffer.append('=');
            if (isSet(i)) {
                str = String.valueOf(this.fields[i]);
            } else {
                str = "?";
            }
            buffer.append(str);
        }
        buffer.append(']');
        return buffer.toString();
    }

    public static final class WeekData {
        public final int firstDayOfWeek;
        public final int minimalDaysInFirstWeek;
        public final int weekendCease;
        public final int weekendCeaseMillis;
        public final int weekendOnset;
        public final int weekendOnsetMillis;

        public WeekData(int fdow, int mdifw, int weekendOnset2, int weekendOnsetMillis2, int weekendCease2, int weekendCeaseMillis2) {
            this.firstDayOfWeek = fdow;
            this.minimalDaysInFirstWeek = mdifw;
            this.weekendOnset = weekendOnset2;
            this.weekendOnsetMillis = weekendOnsetMillis2;
            this.weekendCease = weekendCease2;
            this.weekendCeaseMillis = weekendCeaseMillis2;
        }

        public int hashCode() {
            return (((((((((this.firstDayOfWeek * 37) + this.minimalDaysInFirstWeek) * 37) + this.weekendOnset) * 37) + this.weekendOnsetMillis) * 37) + this.weekendCease) * 37) + this.weekendCeaseMillis;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WeekData)) {
                return false;
            }
            WeekData that = (WeekData) other;
            if (this.firstDayOfWeek == that.firstDayOfWeek && this.minimalDaysInFirstWeek == that.minimalDaysInFirstWeek && this.weekendOnset == that.weekendOnset && this.weekendOnsetMillis == that.weekendOnsetMillis && this.weekendCease == that.weekendCease && this.weekendCeaseMillis == that.weekendCeaseMillis) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "{" + this.firstDayOfWeek + ", " + this.minimalDaysInFirstWeek + ", " + this.weekendOnset + ", " + this.weekendOnsetMillis + ", " + this.weekendCease + ", " + this.weekendCeaseMillis + "}";
        }
    }

    public static WeekData getWeekDataForRegion(String region) {
        return WEEK_DATA_CACHE.createInstance(region, region);
    }

    public WeekData getWeekData() {
        return new WeekData(this.firstDayOfWeek, this.minimalDaysInFirstWeek, this.weekendOnset, this.weekendOnsetMillis, this.weekendCease, this.weekendCeaseMillis);
    }

    public Calendar setWeekData(WeekData wdata) {
        setFirstDayOfWeek(wdata.firstDayOfWeek);
        setMinimalDaysInFirstWeek(wdata.minimalDaysInFirstWeek);
        this.weekendOnset = wdata.weekendOnset;
        this.weekendOnsetMillis = wdata.weekendOnsetMillis;
        this.weekendCease = wdata.weekendCease;
        this.weekendCeaseMillis = wdata.weekendCeaseMillis;
        return this;
    }

    /* access modifiers changed from: private */
    public static WeekData getWeekDataForRegionInternal(String region) {
        UResourceBundle weekDataBundle;
        if (region == null) {
            region = "001";
        }
        UResourceBundle weekDataInfo = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("weekData");
        try {
            weekDataBundle = weekDataInfo.get(region);
        } catch (MissingResourceException mre) {
            if (!region.equals("001")) {
                weekDataBundle = weekDataInfo.get("001");
            } else {
                throw mre;
            }
        }
        int[] wdi = weekDataBundle.getIntVector();
        return new WeekData(wdi[0], wdi[1], wdi[2], wdi[3], wdi[4], wdi[5]);
    }

    /* access modifiers changed from: private */
    public static class WeekDataCache extends SoftCache<String, WeekData, String> {
        private WeekDataCache() {
        }

        /* access modifiers changed from: protected */
        public WeekData createInstance(String key, String data) {
            return Calendar.getWeekDataForRegionInternal(key);
        }
    }

    private void setWeekData(String region) {
        if (region == null) {
            region = "001";
        }
        setWeekData((WeekData) WEEK_DATA_CACHE.getInstance(region, region));
    }

    private void updateTime() {
        computeTime();
        if (isLenient() || !this.areAllFieldsSet) {
            this.areFieldsSet = false;
        }
        this.isTimeSet = true;
        this.areFieldsVirtuallySet = false;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        if (!this.isTimeSet) {
            try {
                updateTime();
            } catch (IllegalArgumentException e) {
            }
        }
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        initInternal();
        this.isTimeSet = true;
        this.areAllFieldsSet = false;
        this.areFieldsSet = false;
        this.areFieldsVirtuallySet = true;
        this.nextStamp = 2;
    }

    /* access modifiers changed from: protected */
    public void computeFields() {
        int[] offsets = new int[2];
        getTimeZone().getOffset(this.time, false, offsets);
        long localMillis = this.time + ((long) offsets[0]) + ((long) offsets[1]);
        int mask = this.internalSetMask;
        for (int i = 0; i < this.fields.length; i++) {
            if ((mask & 1) == 0) {
                this.stamp[i] = 1;
            } else {
                this.stamp[i] = 0;
            }
            mask >>= 1;
        }
        long days = floorDivide(localMillis, 86400000);
        int[] iArr = this.fields;
        iArr[20] = ((int) days) + EPOCH_JULIAN_DAY;
        computeGregorianAndDOWFields(iArr[20]);
        handleComputeFields(this.fields[20]);
        computeWeekFields();
        int millisInDay = (int) (localMillis - (86400000 * days));
        int[] iArr2 = this.fields;
        iArr2[21] = millisInDay;
        iArr2[14] = millisInDay % 1000;
        int millisInDay2 = millisInDay / 1000;
        iArr2[13] = millisInDay2 % 60;
        int millisInDay3 = millisInDay2 / 60;
        iArr2[12] = millisInDay3 % 60;
        int millisInDay4 = millisInDay3 / 60;
        iArr2[11] = millisInDay4;
        iArr2[9] = millisInDay4 / 12;
        iArr2[10] = millisInDay4 % 12;
        iArr2[15] = offsets[0];
        iArr2[16] = offsets[1];
    }

    private final void computeGregorianAndDOWFields(int julianDay) {
        computeGregorianFields(julianDay);
        int[] iArr = this.fields;
        int dow = julianDayToDayOfWeek(julianDay);
        iArr[7] = dow;
        int dowLocal = (dow - getFirstDayOfWeek()) + 1;
        if (dowLocal < 1) {
            dowLocal += 7;
        }
        this.fields[18] = dowLocal;
    }

    /* access modifiers changed from: protected */
    public final void computeGregorianFields(int julianDay) {
        int[] rem = new int[1];
        int n400 = floorDivide((long) (julianDay - JAN_1_1_JULIAN_DAY), 146097, rem);
        boolean isLeap = false;
        int n100 = floorDivide(rem[0], 36524, rem);
        int n4 = floorDivide(rem[0], 1461, rem);
        int n1 = floorDivide(rem[0], 365, rem);
        int year = (n400 * 400) + (n100 * 100) + (n4 * 4) + n1;
        int dayOfYear = rem[0];
        if (n100 == 4 || n1 == 4) {
            dayOfYear = 365;
        } else {
            year++;
        }
        if ((year & 3) == 0 && (year % 100 != 0 || year % 400 == 0)) {
            isLeap = true;
        }
        int correction = 0;
        char c = 2;
        if (dayOfYear >= (isLeap ? 60 : 59)) {
            correction = isLeap ? 1 : 2;
        }
        int month = (((dayOfYear + correction) * 12) + 6) / 367;
        int[] iArr = GREGORIAN_MONTH_COUNT[month];
        if (isLeap) {
            c = 3;
        }
        this.gregorianYear = year;
        this.gregorianMonth = month;
        this.gregorianDayOfMonth = (dayOfYear - iArr[c]) + 1;
        this.gregorianDayOfYear = dayOfYear + 1;
    }

    private final void computeWeekFields() {
        int[] iArr = this.fields;
        int eyear = iArr[19];
        int dayOfWeek = iArr[7];
        int dayOfYear = iArr[6];
        int yearOfWeekOfYear = eyear;
        int relDow = ((dayOfWeek + 7) - getFirstDayOfWeek()) % 7;
        int relDowJan1 = (((dayOfWeek - dayOfYear) + 7001) - getFirstDayOfWeek()) % 7;
        int woy = ((dayOfYear - 1) + relDowJan1) / 7;
        if (7 - relDowJan1 >= getMinimalDaysInFirstWeek()) {
            woy++;
        }
        if (woy == 0) {
            woy = weekNumber(handleGetYearLength(eyear - 1) + dayOfYear, dayOfWeek);
            yearOfWeekOfYear--;
        } else {
            int lastDoy = handleGetYearLength(eyear);
            if (dayOfYear >= lastDoy - 5) {
                int lastRelDow = ((relDow + lastDoy) - dayOfYear) % 7;
                if (lastRelDow < 0) {
                    lastRelDow += 7;
                }
                if (6 - lastRelDow >= getMinimalDaysInFirstWeek() && (dayOfYear + 7) - relDow > lastDoy) {
                    woy = 1;
                    yearOfWeekOfYear++;
                }
            }
        }
        int[] iArr2 = this.fields;
        iArr2[3] = woy;
        iArr2[17] = yearOfWeekOfYear;
        int dayOfMonth = iArr2[5];
        iArr2[4] = weekNumber(dayOfMonth, dayOfWeek);
        this.fields[8] = ((dayOfMonth - 1) / 7) + 1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        if (r9[4] < r9[r8]) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int resolveFields(int[][][] r13) {
        /*
            r12 = this;
            r0 = -1
            r1 = 0
        L_0x0002:
            int r2 = r13.length
            r3 = 32
            if (r1 >= r2) goto L_0x004e
            if (r0 >= 0) goto L_0x004e
            r2 = r13[r1]
            r4 = 0
            r5 = 0
        L_0x000d:
            int r6 = r2.length
            if (r5 >= r6) goto L_0x004b
            r6 = r2[r5]
            r7 = 0
            r8 = 0
            r9 = r6[r8]
            if (r9 < r3) goto L_0x001a
            r9 = 1
            goto L_0x001b
        L_0x001a:
            r9 = r8
        L_0x001b:
            int r10 = r6.length
            if (r9 >= r10) goto L_0x002e
            int[] r10 = r12.stamp
            r11 = r6[r9]
            r10 = r10[r11]
            if (r10 != 0) goto L_0x0027
            goto L_0x0048
        L_0x0027:
            int r7 = java.lang.Math.max(r7, r10)
            int r9 = r9 + 1
            goto L_0x001b
        L_0x002e:
            if (r7 <= r4) goto L_0x0048
            r8 = r6[r8]
            if (r8 < r3) goto L_0x0044
            r8 = r8 & 31
            r9 = 5
            if (r8 != r9) goto L_0x0042
            int[] r9 = r12.stamp
            r10 = 4
            r10 = r9[r10]
            r9 = r9[r8]
            if (r10 >= r9) goto L_0x0045
        L_0x0042:
            r0 = r8
            goto L_0x0045
        L_0x0044:
            r0 = r8
        L_0x0045:
            if (r0 != r8) goto L_0x0048
            r4 = r7
        L_0x0048:
            int r5 = r5 + 1
            goto L_0x000d
        L_0x004b:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x004e:
            if (r0 < r3) goto L_0x0053
            r1 = r0 & 31
            goto L_0x0054
        L_0x0053:
            r1 = r0
        L_0x0054:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.Calendar.resolveFields(int[][][]):int");
    }

    /* access modifiers changed from: protected */
    public int newestStamp(int first, int last, int bestStampSoFar) {
        int bestStamp = bestStampSoFar;
        for (int i = first; i <= last; i++) {
            int[] iArr = this.stamp;
            if (iArr[i] > bestStamp) {
                bestStamp = iArr[i];
            }
        }
        return bestStamp;
    }

    /* access modifiers changed from: protected */
    public final int getStamp(int field) {
        return this.stamp[field];
    }

    /* access modifiers changed from: protected */
    public int newerField(int defaultField, int alternateField) {
        int[] iArr = this.stamp;
        if (iArr[alternateField] > iArr[defaultField]) {
            return alternateField;
        }
        return defaultField;
    }

    /* access modifiers changed from: protected */
    public void validateFields() {
        for (int field = 0; field < this.fields.length; field++) {
            if (this.stamp[field] >= 2) {
                validateField(field);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void validateField(int field) {
        if (field == 5) {
            validateField(field, 1, handleGetMonthLength(handleGetExtendedYear(), internalGet(2)));
        } else if (field == 6) {
            validateField(field, 1, handleGetYearLength(handleGetExtendedYear()));
        } else if (field != 8) {
            validateField(field, getMinimum(field), getMaximum(field));
        } else if (internalGet(field) != 0) {
            validateField(field, getMinimum(field), getMaximum(field));
        } else {
            throw new IllegalArgumentException("DAY_OF_WEEK_IN_MONTH cannot be zero");
        }
    }

    /* access modifiers changed from: protected */
    public final void validateField(int field, int min, int max) {
        int value = this.fields[field];
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName(field) + '=' + value + ", valid range=" + min + ".." + max);
        }
    }

    /* access modifiers changed from: protected */
    public void computeTime() {
        long millisInDay;
        if (!isLenient()) {
            validateFields();
        }
        long millis = julianDayToMillis(computeJulianDay());
        if (this.stamp[21] >= 2 && newestStamp(9, 14, 0) <= this.stamp[21]) {
            millisInDay = (long) internalGet(21);
        } else if (Math.max(Math.abs(internalGet(11)), Math.abs(internalGet(10))) > MAX_HOURS) {
            millisInDay = computeMillisInDayLong();
        } else {
            millisInDay = (long) computeMillisInDay();
        }
        int[] iArr = this.stamp;
        if (iArr[15] >= 2 || iArr[16] >= 2) {
            this.time = (millis + millisInDay) - ((long) (internalGet(15) + internalGet(16)));
        } else if (!this.lenient || this.skippedWallTime == 2) {
            int zoneOffset = computeZoneOffset(millis, millisInDay);
            long tmpTime = (millis + millisInDay) - ((long) zoneOffset);
            if (zoneOffset == this.zone.getOffset(tmpTime)) {
                this.time = tmpTime;
            } else if (this.lenient) {
                Long immediatePrevTransition = getImmediatePreviousZoneTransition(tmpTime);
                if (immediatePrevTransition != null) {
                    this.time = immediatePrevTransition.longValue();
                    return;
                }
                throw new RuntimeException("Could not locate a time zone transition before " + tmpTime);
            } else {
                throw new IllegalArgumentException("The specified wall time does not exist due to time zone offset transition.");
            }
        } else {
            this.time = (millis + millisInDay) - ((long) computeZoneOffset(millis, millisInDay));
        }
    }

    private Long getImmediatePreviousZoneTransition(long base) {
        TimeZone timeZone = this.zone;
        if (timeZone instanceof BasicTimeZone) {
            TimeZoneTransition transition = ((BasicTimeZone) timeZone).getPreviousTransition(base, true);
            if (transition != null) {
                return Long.valueOf(transition.getTime());
            }
            return null;
        }
        Long transitionTime = getPreviousZoneTransitionTime(timeZone, base, 7200000);
        if (transitionTime == null) {
            return getPreviousZoneTransitionTime(this.zone, base, 108000000);
        }
        return transitionTime;
    }

    private static Long getPreviousZoneTransitionTime(TimeZone tz, long base, long duration) {
        long lower = (base - duration) - 1;
        int offsetU = tz.getOffset(base);
        if (offsetU == tz.getOffset(lower)) {
            return null;
        }
        return findPreviousZoneTransitionTime(tz, offsetU, base, lower);
    }

    private static Long findPreviousZoneTransitionTime(TimeZone tz, int upperOffset, long upper, long lower) {
        boolean onUnitTime;
        long mid;
        long mid2;
        long upper2;
        long upper3;
        long mid3 = 0;
        int[] iArr = FIND_ZONE_TRANSITION_TIME_UNITS;
        int length = iArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                onUnitTime = false;
                break;
            }
            int unit = iArr[i];
            long lunits = lower / ((long) unit);
            long uunits = upper / ((long) unit);
            if (uunits > lunits) {
                mid3 = (((lunits + uunits) + 1) >>> 1) * ((long) unit);
                onUnitTime = true;
                break;
            }
            i++;
        }
        if (!onUnitTime) {
            mid = (upper + lower) >>> 1;
        } else {
            mid = mid3;
        }
        if (onUnitTime) {
            if (mid == upper) {
                upper3 = upper;
            } else if (tz.getOffset(mid) != upperOffset) {
                return findPreviousZoneTransitionTime(tz, upperOffset, upper, mid);
            } else {
                upper3 = mid;
            }
            mid2 = mid - 1;
            upper2 = upper3;
        } else {
            upper2 = upper;
            mid2 = (upper + lower) >>> 1;
        }
        if (mid2 == lower) {
            return Long.valueOf(upper2);
        }
        if (tz.getOffset(mid2) == upperOffset) {
            return findPreviousZoneTransitionTime(tz, upperOffset, mid2, lower);
        }
        if (onUnitTime) {
            return Long.valueOf(upper2);
        }
        return findPreviousZoneTransitionTime(tz, upperOffset, upper2, mid2);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public int computeMillisInDay() {
        int millisInDay = 0;
        int[] iArr = this.stamp;
        int hourOfDayStamp = iArr[11];
        int hourStamp = Math.max(iArr[10], iArr[9]);
        int bestStamp = hourStamp > hourOfDayStamp ? hourStamp : hourOfDayStamp;
        if (bestStamp != 0) {
            if (bestStamp == hourOfDayStamp) {
                millisInDay = 0 + internalGet(11);
            } else {
                millisInDay = 0 + internalGet(10) + (internalGet(9) * 12);
            }
        }
        return (((((millisInDay * 60) + internalGet(12)) * 60) + internalGet(13)) * 1000) + internalGet(14);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public long computeMillisInDayLong() {
        long millisInDay = 0;
        int[] iArr = this.stamp;
        int hourOfDayStamp = iArr[11];
        int hourStamp = Math.max(iArr[10], iArr[9]);
        int bestStamp = hourStamp > hourOfDayStamp ? hourStamp : hourOfDayStamp;
        if (bestStamp != 0) {
            if (bestStamp == hourOfDayStamp) {
                millisInDay = 0 + ((long) internalGet(11));
            } else {
                millisInDay = 0 + ((long) internalGet(10)) + ((long) (internalGet(9) * 12));
            }
        }
        return (((((millisInDay * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public int computeZoneOffset(long millis, int millisInDay) {
        int offsetDelta;
        int[] offsets = new int[2];
        long wall = millis + ((long) millisInDay);
        TimeZone timeZone = this.zone;
        if (timeZone instanceof BasicTimeZone) {
            ((BasicTimeZone) this.zone).getOffsetFromLocal(wall, this.skippedWallTime == 1 ? 12 : 4, this.repeatedWallTime == 1 ? 4 : 12, offsets);
        } else {
            timeZone.getOffset(wall, true, offsets);
            boolean sawRecentNegativeShift = false;
            if (this.repeatedWallTime == 1 && (offsetDelta = (offsets[0] + offsets[1]) - this.zone.getOffset((wall - ((long) (offsets[0] + offsets[1]))) - 21600000)) < 0) {
                sawRecentNegativeShift = true;
                this.zone.getOffset(((long) offsetDelta) + wall, true, offsets);
            }
            if (!sawRecentNegativeShift && this.skippedWallTime == 1) {
                this.zone.getOffset(wall - ((long) (offsets[0] + offsets[1])), false, offsets);
            }
        }
        return offsets[0] + offsets[1];
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public int computeZoneOffset(long millis, long millisInDay) {
        int offsetDelta;
        int[] offsets = new int[2];
        long wall = millis + millisInDay;
        TimeZone timeZone = this.zone;
        if (timeZone instanceof BasicTimeZone) {
            ((BasicTimeZone) this.zone).getOffsetFromLocal(wall, this.skippedWallTime == 1 ? 12 : 4, this.repeatedWallTime == 1 ? 4 : 12, offsets);
        } else {
            timeZone.getOffset(wall, true, offsets);
            boolean sawRecentNegativeShift = false;
            if (this.repeatedWallTime == 1 && (offsetDelta = (offsets[0] + offsets[1]) - this.zone.getOffset((wall - ((long) (offsets[0] + offsets[1]))) - 21600000)) < 0) {
                sawRecentNegativeShift = true;
                this.zone.getOffset(((long) offsetDelta) + wall, true, offsets);
            }
            if (!sawRecentNegativeShift && this.skippedWallTime == 1) {
                this.zone.getOffset(wall - ((long) (offsets[0] + offsets[1])), false, offsets);
            }
        }
        return offsets[0] + offsets[1];
    }

    /* access modifiers changed from: protected */
    public int computeJulianDay() {
        if (this.stamp[20] >= 2 && newestStamp(17, 19, newestStamp(0, 8, 0)) <= this.stamp[20]) {
            return internalGet(20);
        }
        int bestField = resolveFields(getFieldResolutionTable());
        if (bestField < 0) {
            bestField = 5;
        }
        return handleComputeJulianDay(bestField);
    }

    /* access modifiers changed from: protected */
    public int[][][] getFieldResolutionTable() {
        return DATE_PRECEDENCE;
    }

    /* access modifiers changed from: protected */
    public int handleGetMonthLength(int extendedYear, int month) {
        return handleComputeMonthStart(extendedYear, month + 1, true) - handleComputeMonthStart(extendedYear, month, true);
    }

    /* access modifiers changed from: protected */
    public int handleGetYearLength(int eyear) {
        return handleComputeMonthStart(eyear + 1, 0, false) - handleComputeMonthStart(eyear, 0, false);
    }

    /* access modifiers changed from: protected */
    public int[] handleCreateFields() {
        return new int[23];
    }

    /* access modifiers changed from: protected */
    public int getDefaultMonthInYear(int extendedYear) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getDefaultDayInMonth(int extendedYear, int month) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public int handleComputeJulianDay(int bestField) {
        int year;
        int date;
        boolean useMonth = bestField == 5 || bestField == 4 || bestField == 8;
        if (bestField == 3 && newerField(17, 1) == 17) {
            year = internalGet(17);
        } else {
            year = handleGetExtendedYear();
        }
        internalSet(19, year);
        int month = useMonth ? internalGet(2, getDefaultMonthInYear(year)) : 0;
        int julianDay = handleComputeMonthStart(year, month, useMonth);
        if (bestField == 5) {
            if (isSet(5)) {
                return internalGet(5, getDefaultDayInMonth(year, month)) + julianDay;
            }
            return getDefaultDayInMonth(year, month) + julianDay;
        } else if (bestField == 6) {
            return internalGet(6) + julianDay;
        } else {
            int firstDOW = getFirstDayOfWeek();
            int first = julianDayToDayOfWeek(julianDay + 1) - firstDOW;
            if (first < 0) {
                first += 7;
            }
            int dowLocal = 0;
            int resolveFields = resolveFields(DOW_PRECEDENCE);
            if (resolveFields == 7) {
                dowLocal = internalGet(7) - firstDOW;
            } else if (resolveFields == 18) {
                dowLocal = internalGet(18) - 1;
            }
            int dowLocal2 = dowLocal % 7;
            if (dowLocal2 < 0) {
                dowLocal2 += 7;
            }
            int date2 = (1 - first) + dowLocal2;
            if (bestField == 8) {
                if (date2 < 1) {
                    date2 += 7;
                }
                int dim = internalGet(8, 1);
                if (dim >= 0) {
                    date = date2 + ((dim - 1) * 7);
                } else {
                    date = date2 + ((((handleGetMonthLength(year, internalGet(2, 0)) - date2) / 7) + dim + 1) * 7);
                }
            } else {
                if (7 - first < getMinimalDaysInFirstWeek()) {
                    date2 += 7;
                }
                date = date2 + ((internalGet(bestField) - 1) * 7);
            }
            return julianDay + date;
        }
    }

    /* access modifiers changed from: protected */
    public int computeGregorianMonthStart(int year, int month) {
        boolean isLeap = false;
        if (month < 0 || month > 11) {
            int[] rem = new int[1];
            year += floorDivide(month, 12, rem);
            month = rem[0];
        }
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            isLeap = true;
        }
        int y = year - 1;
        int julianDay = (((((y * 365) + floorDivide(y, 4)) - floorDivide(y, 100)) + floorDivide(y, 400)) + JAN_1_1_JULIAN_DAY) - 1;
        if (month == 0) {
            return julianDay;
        }
        return julianDay + GREGORIAN_MONTH_COUNT[month][isLeap ? (char) 3 : 2];
    }

    /* access modifiers changed from: protected */
    public void handleComputeFields(int julianDay) {
        internalSet(2, getGregorianMonth());
        internalSet(5, getGregorianDayOfMonth());
        internalSet(6, getGregorianDayOfYear());
        int eyear = getGregorianYear();
        internalSet(19, eyear);
        int era = 1;
        if (eyear < 1) {
            era = 0;
            eyear = 1 - eyear;
        }
        internalSet(0, era);
        internalSet(1, eyear);
    }

    /* access modifiers changed from: protected */
    public final int getGregorianYear() {
        return this.gregorianYear;
    }

    /* access modifiers changed from: protected */
    public final int getGregorianMonth() {
        return this.gregorianMonth;
    }

    /* access modifiers changed from: protected */
    public final int getGregorianDayOfYear() {
        return this.gregorianDayOfYear;
    }

    /* access modifiers changed from: protected */
    public final int getGregorianDayOfMonth() {
        return this.gregorianDayOfMonth;
    }

    public final int getFieldCount() {
        return this.fields.length;
    }

    /* access modifiers changed from: protected */
    public final void internalSet(int field, int value) {
        if (((1 << field) & this.internalSetMask) != 0) {
            this.fields[field] = value;
            this.stamp[field] = 1;
            return;
        }
        throw new IllegalStateException("Subclass cannot set " + fieldName(field));
    }

    protected static final boolean isGregorianLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    protected static final int gregorianMonthLength(int y, int m) {
        return GREGORIAN_MONTH_COUNT[m][isGregorianLeapYear(y) ? 1 : 0];
    }

    protected static final int gregorianPreviousMonthLength(int y, int m) {
        if (m > 0) {
            return gregorianMonthLength(y, m - 1);
        }
        return 31;
    }

    protected static final long floorDivide(long numerator, long denominator) {
        if (numerator >= 0) {
            return numerator / denominator;
        }
        return ((numerator + 1) / denominator) - 1;
    }

    protected static final int floorDivide(int numerator, int denominator) {
        if (numerator >= 0) {
            return numerator / denominator;
        }
        return ((numerator + 1) / denominator) - 1;
    }

    protected static final int floorDivide(int numerator, int denominator, int[] remainder) {
        if (numerator >= 0) {
            remainder[0] = numerator % denominator;
            return numerator / denominator;
        }
        int quotient = ((numerator + 1) / denominator) - 1;
        remainder[0] = numerator - (quotient * denominator);
        return quotient;
    }

    protected static final int floorDivide(long numerator, int denominator, int[] remainder) {
        if (numerator >= 0) {
            remainder[0] = (int) (numerator % ((long) denominator));
            return (int) (numerator / ((long) denominator));
        }
        int quotient = (int) (((numerator + 1) / ((long) denominator)) - 1);
        remainder[0] = (int) (numerator - (((long) quotient) * ((long) denominator)));
        return quotient;
    }

    /* access modifiers changed from: protected */
    public String fieldName(int field) {
        try {
            return FIELD_NAME[field];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Field " + field;
        }
    }

    protected static final int millisToJulianDay(long millis) {
        return (int) (floorDivide(millis, 86400000) + 2440588);
    }

    protected static final long julianDayToMillis(int julian) {
        return ((long) (julian - EPOCH_JULIAN_DAY)) * 86400000;
    }

    protected static final int julianDayToDayOfWeek(int julian) {
        int dayOfWeek = (julian + 2) % 7;
        if (dayOfWeek < 1) {
            return dayOfWeek + 7;
        }
        return dayOfWeek;
    }

    /* access modifiers changed from: protected */
    public final long internalGetTimeInMillis() {
        return this.time;
    }

    public String getType() {
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    @Deprecated
    public boolean haveDefaultCentury() {
        return true;
    }

    public final ULocale getLocale(ULocale.Type type) {
        return type == ULocale.ACTUAL_LOCALE ? this.actualLocale : this.validLocale;
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale valid, ULocale actual) {
        boolean z = true;
        boolean z2 = valid == null;
        if (actual != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = valid;
            this.actualLocale = actual;
            return;
        }
        throw new IllegalArgumentException();
    }
}
