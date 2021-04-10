package sun.util.calendar;

import android.icu.lang.UCharacter;
import java.net.HttpURLConnection;
import java.sql.Types;
import java.util.TimeZone;

public abstract class BaseCalendar extends AbstractCalendar {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int[] ACCUMULATED_DAYS_IN_MONTH = {-30, 0, 31, 59, 90, 120, 151, 181, 212, 243, UCharacter.UnicodeBlock.TANGUT_COMPONENTS_ID, HttpURLConnection.HTTP_NOT_MODIFIED, 334};
    static final int[] ACCUMULATED_DAYS_IN_MONTH_LEAP = {-30, 0, 31, 60, 91, 121, 152, 182, 213, 244, UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID, HttpURLConnection.HTTP_USE_PROXY, 335};
    public static final int APRIL = 4;
    public static final int AUGUST = 8;
    private static final int BASE_YEAR = 1970;
    static final int[] DAYS_IN_MONTH = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int DECEMBER = 12;
    public static final int FEBRUARY = 2;
    private static final int[] FIXED_DATES = {719163, 719528, 719893, 720259, 720624, 720989, 721354, 721720, 722085, 722450, 722815, 723181, 723546, 723911, 724276, 724642, 725007, 725372, 725737, 726103, 726468, 726833, 727198, 727564, 727929, 728294, 728659, 729025, 729390, 729755, 730120, 730486, 730851, 731216, 731581, 731947, 732312, 732677, 733042, 733408, 733773, 734138, 734503, 734869, 735234, 735599, 735964, 736330, 736695, 737060, 737425, 737791, 738156, 738521, 738886, 739252, 739617, 739982, 740347, 740713, 741078, 741443, 741808, 742174, 742539, 742904, 743269, 743635, 744000, 744365};
    public static final int FRIDAY = 6;
    public static final int JANUARY = 1;
    public static final int JULY = 7;
    public static final int JUNE = 6;
    public static final int MARCH = 3;
    public static final int MAY = 5;
    public static final int MONDAY = 2;
    public static final int NOVEMBER = 11;
    public static final int OCTOBER = 10;
    public static final int SATURDAY = 7;
    public static final int SEPTEMBER = 9;
    public static final int SUNDAY = 1;
    public static final int THURSDAY = 5;
    public static final int TUESDAY = 3;
    public static final int WEDNESDAY = 4;

    public static abstract class Date extends CalendarDate {
        long cachedFixedDateJan1 = 731581;
        long cachedFixedDateNextJan1 = (this.cachedFixedDateJan1 + 366);
        int cachedYear = Types.BLOB;

        public abstract int getNormalizedYear();

        public abstract void setNormalizedYear(int i);

        protected Date() {
        }

        protected Date(TimeZone zone) {
            super(zone);
        }

        public Date setNormalizedDate(int normalizedYear, int month, int dayOfMonth) {
            setNormalizedYear(normalizedYear);
            setMonth(month).setDayOfMonth(dayOfMonth);
            return this;
        }

        /* access modifiers changed from: protected */
        public final boolean hit(int year) {
            return year == this.cachedYear;
        }

        /* access modifiers changed from: protected */
        public final boolean hit(long fixedDate) {
            return fixedDate >= this.cachedFixedDateJan1 && fixedDate < this.cachedFixedDateNextJan1;
        }

        /* access modifiers changed from: protected */
        public int getCachedYear() {
            return this.cachedYear;
        }

        /* access modifiers changed from: protected */
        public long getCachedJan1() {
            return this.cachedFixedDateJan1;
        }

        /* access modifiers changed from: protected */
        public void setCache(int year, long jan1, int len) {
            this.cachedYear = year;
            this.cachedFixedDateJan1 = jan1;
            this.cachedFixedDateNextJan1 = ((long) len) + jan1;
        }
    }

    @Override // sun.util.calendar.CalendarSystem
    public boolean validate(CalendarDate date) {
        int d;
        Date bdate = (Date) date;
        if (bdate.isNormalized()) {
            return true;
        }
        int month = bdate.getMonth();
        if (month < 1 || month > 12 || (d = bdate.getDayOfMonth()) <= 0 || d > getMonthLength(bdate.getNormalizedYear(), month)) {
            return false;
        }
        int dow = bdate.getDayOfWeek();
        if ((dow != Integer.MIN_VALUE && dow != getDayOfWeek(bdate)) || !validateTime(date)) {
            return false;
        }
        bdate.setNormalized(true);
        return true;
    }

    @Override // sun.util.calendar.CalendarSystem
    public boolean normalize(CalendarDate date) {
        if (date.isNormalized()) {
            return true;
        }
        Date bdate = (Date) date;
        if (bdate.getZone() != null) {
            getTime(date);
            return true;
        }
        int days = normalizeTime(bdate);
        normalizeMonth(bdate);
        long d = ((long) bdate.getDayOfMonth()) + ((long) days);
        int m = bdate.getMonth();
        int y = bdate.getNormalizedYear();
        int ml = getMonthLength(y, m);
        if (d > 0 && d <= ((long) ml)) {
            bdate.setDayOfWeek(getDayOfWeek(bdate));
        } else if (d <= 0 && d > -28) {
            int m2 = m - 1;
            bdate.setDayOfMonth((int) (d + ((long) getMonthLength(y, m2))));
            if (m2 == 0) {
                m2 = 12;
                bdate.setNormalizedYear(y - 1);
            }
            bdate.setMonth(m2);
        } else if (d <= ((long) ml) || d >= ((long) (ml + 28))) {
            getCalendarDateFromFixedDate(bdate, (getFixedDate(y, m, 1, bdate) + d) - 1);
        } else {
            int m3 = m + 1;
            bdate.setDayOfMonth((int) (d - ((long) ml)));
            if (m3 > 12) {
                bdate.setNormalizedYear(y + 1);
                m3 = 1;
            }
            bdate.setMonth(m3);
        }
        date.setLeapYear(isLeapYear(bdate.getNormalizedYear()));
        date.setZoneOffset(0);
        date.setDaylightSaving(0);
        bdate.setNormalized(true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void normalizeMonth(CalendarDate date) {
        Date bdate = (Date) date;
        int year = bdate.getNormalizedYear();
        long month = (long) bdate.getMonth();
        if (month <= 0) {
            long xm = 1 - month;
            bdate.setNormalizedYear(year - ((int) ((xm / 12) + 1)));
            bdate.setMonth((int) (13 - (xm % 12)));
        } else if (month > 12) {
            bdate.setNormalizedYear(year + ((int) ((month - 1) / 12)));
            bdate.setMonth((int) (((month - 1) % 12) + 1));
        }
    }

    @Override // sun.util.calendar.CalendarSystem
    public int getYearLength(CalendarDate date) {
        return isLeapYear(((Date) date).getNormalizedYear()) ? 366 : 365;
    }

    @Override // sun.util.calendar.CalendarSystem
    public int getYearLengthInMonths(CalendarDate date) {
        return 12;
    }

    @Override // sun.util.calendar.CalendarSystem
    public int getMonthLength(CalendarDate date) {
        Date gdate = (Date) date;
        int month = gdate.getMonth();
        if (month >= 1 && month <= 12) {
            return getMonthLength(gdate.getNormalizedYear(), month);
        }
        throw new IllegalArgumentException("Illegal month value: " + month);
    }

    private int getMonthLength(int year, int month) {
        int days = DAYS_IN_MONTH[month];
        if (month != 2 || !isLeapYear(year)) {
            return days;
        }
        return days + 1;
    }

    public long getDayOfYear(CalendarDate date) {
        return getDayOfYear(((Date) date).getNormalizedYear(), date.getMonth(), date.getDayOfMonth());
    }

    /* access modifiers changed from: package-private */
    public final long getDayOfYear(int year, int month, int dayOfMonth) {
        return ((long) dayOfMonth) + ((long) (isLeapYear(year) ? ACCUMULATED_DAYS_IN_MONTH_LEAP[month] : ACCUMULATED_DAYS_IN_MONTH[month]));
    }

    @Override // sun.util.calendar.AbstractCalendar
    public long getFixedDate(CalendarDate date) {
        if (!date.isNormalized()) {
            normalizeMonth(date);
        }
        return getFixedDate(((Date) date).getNormalizedYear(), date.getMonth(), date.getDayOfMonth(), (Date) date);
    }

    public long getFixedDate(int year, int month, int dayOfMonth, Date cache) {
        long days;
        boolean isJan1 = true;
        if (!(month == 1 && dayOfMonth == 1)) {
            isJan1 = false;
        }
        if (cache == null || !cache.hit(year)) {
            int n = year - 1970;
            if (n >= 0) {
                int[] iArr = FIXED_DATES;
                if (n < iArr.length) {
                    long jan1 = (long) iArr[n];
                    if (cache != null) {
                        cache.setCache(year, jan1, isLeapYear(year) ? 366 : 365);
                    }
                    return isJan1 ? jan1 : (getDayOfYear(year, month, dayOfMonth) + jan1) - 1;
                }
            }
            long prevyear = ((long) year) - 1;
            long days2 = (long) dayOfMonth;
            if (prevyear >= 0) {
                days = days2 + (((365 * prevyear) + (prevyear / 4)) - (prevyear / 100)) + (prevyear / 400) + ((long) (((month * 367) - 362) / 12));
            } else {
                days = days2 + (((365 * prevyear) + CalendarUtils.floorDivide(prevyear, 4)) - CalendarUtils.floorDivide(prevyear, 100)) + CalendarUtils.floorDivide(prevyear, 400) + ((long) CalendarUtils.floorDivide((month * 367) - 362, 12));
            }
            if (month > 2) {
                days -= isLeapYear(year) ? 1 : 2;
            }
            if (cache != null && isJan1) {
                cache.setCache(year, days, isLeapYear(year) ? 366 : 365);
            }
            return days;
        } else if (isJan1) {
            return cache.getCachedJan1();
        } else {
            return (cache.getCachedJan1() + getDayOfYear(year, month, dayOfMonth)) - 1;
        }
    }

    @Override // sun.util.calendar.AbstractCalendar
    public void getCalendarDateFromFixedDate(CalendarDate date, long fixedDate) {
        boolean isLeap;
        long jan1;
        int year;
        int month;
        Date gdate = (Date) date;
        if (gdate.hit(fixedDate)) {
            year = gdate.getCachedYear();
            jan1 = gdate.getCachedJan1();
            isLeap = isLeapYear(year);
        } else {
            year = getGregorianYearFromFixedDate(fixedDate);
            jan1 = getFixedDate(year, 1, 1, null);
            isLeap = isLeapYear(year);
            gdate.setCache(year, jan1, isLeap ? 366 : 365);
        }
        int priorDays = (int) (fixedDate - jan1);
        long mar1 = 31 + jan1 + 28;
        if (isLeap) {
            mar1++;
        }
        if (fixedDate >= mar1) {
            priorDays += isLeap ? 1 : 2;
        }
        int month2 = (priorDays * 12) + 373;
        if (month2 > 0) {
            month = month2 / 367;
        } else {
            month = CalendarUtils.floorDivide(month2, 367);
        }
        long month1 = ((long) ACCUMULATED_DAYS_IN_MONTH[month]) + jan1;
        if (isLeap && month >= 3) {
            month1++;
        }
        int dayOfWeek = getDayOfWeekFromFixedDate(fixedDate);
        gdate.setNormalizedYear(year);
        gdate.setMonth(month);
        gdate.setDayOfMonth(((int) (fixedDate - month1)) + 1);
        gdate.setDayOfWeek(dayOfWeek);
        gdate.setLeapYear(isLeap);
        gdate.setNormalized(true);
    }

    public int getDayOfWeek(CalendarDate date) {
        return getDayOfWeekFromFixedDate(getFixedDate(date));
    }

    public static final int getDayOfWeekFromFixedDate(long fixedDate) {
        if (fixedDate >= 0) {
            return ((int) (fixedDate % 7)) + 1;
        }
        return ((int) CalendarUtils.mod(fixedDate, 7)) + 1;
    }

    public int getYearFromFixedDate(long fixedDate) {
        return getGregorianYearFromFixedDate(fixedDate);
    }

    /* access modifiers changed from: package-private */
    public final int getGregorianYearFromFixedDate(long fixedDate) {
        int n1;
        int d3;
        int n100;
        int n400;
        if (fixedDate > 0) {
            long d0 = fixedDate - 1;
            n400 = (int) (d0 / 146097);
            int d1 = (int) (d0 % 146097);
            n100 = d1 / 36524;
            int d2 = d1 % 36524;
            d3 = d2 / 1461;
            int d32 = d2 % 1461;
            n1 = d32 / 365;
            int i = (d32 % 365) + 1;
        } else {
            long d02 = fixedDate - 1;
            n400 = (int) CalendarUtils.floorDivide(d02, 146097);
            int d12 = (int) CalendarUtils.mod(d02, 146097);
            n100 = CalendarUtils.floorDivide(d12, 36524);
            int d22 = CalendarUtils.mod(d12, 36524);
            int n4 = CalendarUtils.floorDivide(d22, 1461);
            int d33 = CalendarUtils.mod(d22, 1461);
            int n12 = CalendarUtils.floorDivide(d33, 365);
            int mod = CalendarUtils.mod(d33, 365) + 1;
            d3 = n4;
            n1 = n12;
        }
        int year = (n400 * 400) + (n100 * 100) + (d3 * 4) + n1;
        if (n100 == 4 || n1 == 4) {
            return year;
        }
        return year + 1;
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.AbstractCalendar
    public boolean isLeapYear(CalendarDate date) {
        return isLeapYear(((Date) date).getNormalizedYear());
    }

    /* access modifiers changed from: package-private */
    public boolean isLeapYear(int normalizedYear) {
        return CalendarUtils.isGregorianLeapYear(normalizedYear);
    }
}
