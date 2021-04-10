package android.icu.util;

import android.icu.impl.CalendarAstronomer;
import android.icu.impl.CalendarCache;
import android.icu.text.DateFormat;
import android.icu.util.ULocale;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.Locale;

public class ChineseCalendar extends Calendar {
    private static final TimeZone CHINA_ZONE = new SimpleTimeZone(28800000, "CHINA_ZONE").freeze();
    static final int[][][] CHINESE_DATE_PRECEDENCE = {new int[][]{new int[]{5}, new int[]{3, 7}, new int[]{4, 7}, new int[]{8, 7}, new int[]{3, 18}, new int[]{4, 18}, new int[]{8, 18}, new int[]{6}, new int[]{37, 22}}, new int[][]{new int[]{3}, new int[]{4}, new int[]{8}, new int[]{40, 7}, new int[]{40, 18}}};
    private static final int CHINESE_EPOCH_YEAR = -2636;
    private static final int[][] LIMITS = {new int[]{1, 1, 83333, 83333}, new int[]{1, 1, 60, 60}, new int[]{0, 0, 11, 11}, new int[]{1, 1, 50, 55}, new int[0], new int[]{1, 1, 29, 30}, new int[]{1, 1, 353, 385}, new int[0], new int[]{-1, -1, 5, 5}, new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[0], new int[]{-5000000, -5000000, 5000000, 5000000}, new int[0], new int[]{-5000000, -5000000, 5000000, 5000000}, new int[0], new int[0], new int[]{0, 0, 1, 1}};
    private static final int SYNODIC_GAP = 25;
    private static final long serialVersionUID = 7312110751940929420L;
    private transient CalendarAstronomer astro;
    private int epochYear;
    private transient boolean isLeapYear;
    private transient CalendarCache newYearCache;
    private transient CalendarCache winterSolsticeCache;
    private TimeZone zoneAstro;

    public ChineseCalendar() {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
    }

    public ChineseCalendar(Date date) {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
        setTime(date);
    }

    public ChineseCalendar(int year, int month, int isLeapMonth, int date) {
        this(year, month, isLeapMonth, date, 0, 0, 0);
    }

    public ChineseCalendar(int year, int month, int isLeapMonth, int date, int hour, int minute, int second) {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
        set(14, 0);
        set(1, year);
        set(2, month);
        set(22, isLeapMonth);
        set(5, date);
        set(11, hour);
        set(12, minute);
        set(13, second);
    }

    public ChineseCalendar(int era, int year, int month, int isLeapMonth, int date) {
        this(era, year, month, isLeapMonth, date, 0, 0, 0);
    }

    public ChineseCalendar(int era, int year, int month, int isLeapMonth, int date, int hour, int minute, int second) {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
        set(14, 0);
        set(0, era);
        set(1, year);
        set(2, month);
        set(22, isLeapMonth);
        set(5, date);
        set(11, hour);
        set(12, minute);
        set(13, second);
    }

    public ChineseCalendar(Locale aLocale) {
        this(TimeZone.getDefault(), ULocale.forLocale(aLocale), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
    }

    public ChineseCalendar(TimeZone zone) {
        this(zone, ULocale.getDefault(ULocale.Category.FORMAT), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
    }

    public ChineseCalendar(TimeZone zone, Locale aLocale) {
        this(zone, ULocale.forLocale(aLocale), (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
    }

    public ChineseCalendar(ULocale locale) {
        this(TimeZone.getDefault(), locale, (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
    }

    public ChineseCalendar(TimeZone zone, ULocale locale) {
        this(zone, locale, (int) CHINESE_EPOCH_YEAR, CHINA_ZONE);
    }

    @Deprecated
    protected ChineseCalendar(TimeZone zone, ULocale locale, int epochYear2, TimeZone zoneAstroCalc) {
        super(zone, locale);
        this.astro = new CalendarAstronomer();
        this.winterSolsticeCache = new CalendarCache();
        this.newYearCache = new CalendarCache();
        this.epochYear = epochYear2;
        this.zoneAstro = zoneAstroCalc;
        setTimeInMillis(System.currentTimeMillis());
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int handleGetLimit(int field, int limitType) {
        return LIMITS[field][limitType];
    }

    /* JADX INFO: Multiple debug info for r0v3 int: [D('cycle' int), D('year' int)] */
    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int handleGetExtendedYear() {
        if (newestStamp(0, 1, 0) <= getStamp(19)) {
            return internalGet(19, 1);
        }
        return (((internalGet(0, 1) - 1) * 60) + internalGet(1, 1)) - (this.epochYear + 2636);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int handleGetMonthLength(int extendedYear, int month) {
        int thisStart = (handleComputeMonthStart(extendedYear, month, true) - 2440588) + 1;
        return newMoonNear(thisStart + 25, true) - thisStart;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public DateFormat handleGetDateFormat(String pattern, String override, ULocale locale) {
        return super.handleGetDateFormat(pattern, override, locale);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int[][][] getFieldResolutionTable() {
        return CHINESE_DATE_PRECEDENCE;
    }

    private void offsetMonth(int newMoon, int dom, int delta) {
        int jd = ((2440588 + newMoonNear(newMoon + ((int) ((((double) delta) - 0.5d) * 29.530588853d)), true)) - 1) + dom;
        if (dom > 29) {
            set(20, jd - 1);
            complete();
            if (getActualMaximum(5) >= dom) {
                set(20, jd);
                return;
            }
            return;
        }
        set(20, jd);
    }

    @Override // android.icu.util.Calendar
    public void add(int field, int amount) {
        if (field != 2) {
            super.add(field, amount);
        } else if (amount != 0) {
            int dom = get(5);
            offsetMonth(((get(20) - 2440588) - dom) + 1, dom, amount);
        }
    }

    @Override // android.icu.util.Calendar
    public void roll(int field, int amount) {
        if (field != 2) {
            super.roll(field, amount);
        } else if (amount != 0) {
            int dom = get(5);
            int moon = ((get(20) - 2440588) - dom) + 1;
            int m = get(2);
            if (this.isLeapYear) {
                if (get(22) == 1) {
                    m++;
                } else if (isLeapMonthBetween(newMoonNear(moon - ((int) ((((double) m) - 0.5d) * 29.530588853d)), true), moon)) {
                    m++;
                }
            }
            int n = this.isLeapYear ? 13 : 12;
            int newM = (m + amount) % n;
            if (newM < 0) {
                newM += n;
            }
            if (newM != m) {
                offsetMonth(moon, dom, newM - m);
            }
        }
    }

    private final long daysToMillis(int days) {
        long millis = ((long) days) * 86400000;
        return millis - ((long) this.zoneAstro.getOffset(millis));
    }

    private final int millisToDays(long millis) {
        return (int) floorDivide(((long) this.zoneAstro.getOffset(millis)) + millis, 86400000);
    }

    private int winterSolstice(int gyear) {
        long cacheValue = this.winterSolsticeCache.get((long) gyear);
        if (cacheValue == CalendarCache.EMPTY) {
            this.astro.setTime(daysToMillis((computeGregorianMonthStart(gyear, 11) + 1) - 2440588));
            cacheValue = (long) millisToDays(this.astro.getSunTime(CalendarAstronomer.WINTER_SOLSTICE, true));
            this.winterSolsticeCache.put((long) gyear, cacheValue);
        }
        return (int) cacheValue;
    }

    private int newMoonNear(int days, boolean after) {
        this.astro.setTime(daysToMillis(days));
        return millisToDays(this.astro.getMoonTime(CalendarAstronomer.NEW_MOON, after));
    }

    private int synodicMonthsBetween(int day1, int day2) {
        return (int) Math.round(((double) (day2 - day1)) / 29.530588853d);
    }

    private int majorSolarTerm(int days) {
        this.astro.setTime(daysToMillis(days));
        int term = (((int) Math.floor((this.astro.getSunLongitude() * 6.0d) / 3.141592653589793d)) + 2) % 12;
        if (term < 1) {
            return term + 12;
        }
        return term;
    }

    private boolean hasNoMajorSolarTerm(int newMoon) {
        if (majorSolarTerm(newMoon) == majorSolarTerm(newMoonNear(newMoon + 25, true))) {
            return true;
        }
        return false;
    }

    private boolean isLeapMonthBetween(int newMoon1, int newMoon2) {
        if (synodicMonthsBetween(newMoon1, newMoon2) >= 50) {
            throw new IllegalArgumentException("isLeapMonthBetween(" + newMoon1 + ", " + newMoon2 + "): Invalid parameters");
        } else if (newMoon2 < newMoon1) {
            return false;
        } else {
            if (isLeapMonthBetween(newMoon1, newMoonNear(newMoon2 - 25, false)) || hasNoMajorSolarTerm(newMoon2)) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public void handleComputeFields(int julianDay) {
        computeChineseFields(julianDay - 2440588, getGregorianYear(), getGregorianMonth(), true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0087, code lost:
        if (r22 >= 6) goto L_0x008c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void computeChineseFields(int r20, int r21, int r22, boolean r23) {
        /*
        // Method dump skipped, instructions count: 212
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.ChineseCalendar.computeChineseFields(int, int, int, boolean):void");
    }

    private int newYear(int gyear) {
        long cacheValue = this.newYearCache.get((long) gyear);
        if (cacheValue == CalendarCache.EMPTY) {
            int solsticeBefore = winterSolstice(gyear - 1);
            int solsticeAfter = winterSolstice(gyear);
            int newMoon1 = newMoonNear(solsticeBefore + 1, true);
            int newMoon2 = newMoonNear(newMoon1 + 25, true);
            if (synodicMonthsBetween(newMoon1, newMoonNear(solsticeAfter + 1, false)) != 12 || (!hasNoMajorSolarTerm(newMoon1) && !hasNoMajorSolarTerm(newMoon2))) {
                cacheValue = (long) newMoon2;
            } else {
                cacheValue = (long) newMoonNear(newMoon2 + 25, true);
            }
            this.newYearCache.put((long) gyear, cacheValue);
        }
        return (int) cacheValue;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int handleComputeMonthStart(int eyear, int month, boolean useMonth) {
        int eyear2;
        int month2 = month;
        if (month2 < 0 || month2 > 11) {
            int[] rem = new int[1];
            eyear2 = eyear + floorDivide(month2, 12, rem);
            month2 = rem[0];
        } else {
            eyear2 = eyear;
        }
        int newMoon = newMoonNear((month2 * 29) + newYear((this.epochYear + eyear2) - 1), true);
        int julianDay = newMoon + 2440588;
        int saveMonth = internalGet(2);
        int saveIsLeapMonth = internalGet(22);
        int isLeapMonth = useMonth ? saveIsLeapMonth : 0;
        computeGregorianFields(julianDay);
        computeChineseFields(newMoon, getGregorianYear(), getGregorianMonth(), false);
        if (!(month2 == internalGet(2) && isLeapMonth == internalGet(22))) {
            julianDay = newMoonNear(newMoon + 25, true) + 2440588;
        }
        internalSet(2, saveMonth);
        internalSet(22, saveIsLeapMonth);
        return julianDay - 1;
    }

    @Override // android.icu.util.Calendar
    public String getType() {
        return "chinese";
    }

    @Override // android.icu.util.Calendar
    @Deprecated
    public boolean haveDefaultCentury() {
        return false;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        this.epochYear = CHINESE_EPOCH_YEAR;
        this.zoneAstro = CHINA_ZONE;
        stream.defaultReadObject();
        this.astro = new CalendarAstronomer();
        this.winterSolsticeCache = new CalendarCache();
        this.newYearCache = new CalendarCache();
    }
}
