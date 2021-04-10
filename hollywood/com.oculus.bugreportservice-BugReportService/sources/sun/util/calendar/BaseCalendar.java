package sun.util.calendar;

import java.util.TimeZone;

public abstract class BaseCalendar extends AbstractCalendar {
    static final int[] ACCUMULATED_DAYS_IN_MONTH = {-30, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    static final int[] ACCUMULATED_DAYS_IN_MONTH_LEAP = {-30, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    static final int[] DAYS_IN_MONTH = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] FIXED_DATES = {719163, 719528, 719893, 720259, 720624, 720989, 721354, 721720, 722085, 722450, 722815, 723181, 723546, 723911, 724276, 724642, 725007, 725372, 725737, 726103, 726468, 726833, 727198, 727564, 727929, 728294, 728659, 729025, 729390, 729755, 730120, 730486, 730851, 731216, 731581, 731947, 732312, 732677, 733042, 733408, 733773, 734138, 734503, 734869, 735234, 735599, 735964, 736330, 736695, 737060, 737425, 737791, 738156, 738521, 738886, 739252, 739617, 739982, 740347, 740713, 741078, 741443, 741808, 742174, 742539, 742904, 743269, 743635, 744000, 744365};

    public static abstract class Date extends CalendarDate {
        long cachedFixedDateJan1 = 731581;
        long cachedFixedDateNextJan1 = (this.cachedFixedDateJan1 + 366);
        int cachedYear = 2004;

        public abstract int getNormalizedYear();

        public abstract void setNormalizedYear(int i);

        protected Date() {
        }

        protected Date(TimeZone timeZone) {
            super(timeZone);
        }

        public Date setNormalizedDate(int i, int i2, int i3) {
            setNormalizedYear(i);
            setMonth(i2).setDayOfMonth(i3);
            return this;
        }

        /* access modifiers changed from: protected */
        public final boolean hit(int i) {
            return i == this.cachedYear;
        }

        /* access modifiers changed from: protected */
        public final boolean hit(long j) {
            return j >= this.cachedFixedDateJan1 && j < this.cachedFixedDateNextJan1;
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
        public void setCache(int i, long j, int i2) {
            this.cachedYear = i;
            this.cachedFixedDateJan1 = j;
            this.cachedFixedDateNextJan1 = j + ((long) i2);
        }
    }

    public boolean validate(CalendarDate calendarDate) {
        int dayOfMonth;
        Date date = (Date) calendarDate;
        if (date.isNormalized()) {
            return true;
        }
        int month = date.getMonth();
        if (month < 1 || month > 12 || (dayOfMonth = date.getDayOfMonth()) <= 0 || dayOfMonth > getMonthLength(date.getNormalizedYear(), month)) {
            return false;
        }
        int dayOfWeek = date.getDayOfWeek();
        if ((dayOfWeek != Integer.MIN_VALUE && dayOfWeek != getDayOfWeek(date)) || !validateTime(calendarDate)) {
            return false;
        }
        date.setNormalized(true);
        return true;
    }

    @Override // sun.util.calendar.CalendarSystem
    public boolean normalize(CalendarDate calendarDate) {
        if (calendarDate.isNormalized()) {
            return true;
        }
        Date date = (Date) calendarDate;
        if (date.getZone() != null) {
            getTime(calendarDate);
            return true;
        }
        int normalizeTime = normalizeTime(date);
        normalizeMonth(date);
        long dayOfMonth = ((long) date.getDayOfMonth()) + ((long) normalizeTime);
        int month = date.getMonth();
        int normalizedYear = date.getNormalizedYear();
        int monthLength = getMonthLength(normalizedYear, month);
        int i = (dayOfMonth > 0 ? 1 : (dayOfMonth == 0 ? 0 : -1));
        if (i > 0 && dayOfMonth <= ((long) monthLength)) {
            date.setDayOfWeek(getDayOfWeek(date));
        } else if (i > 0 || dayOfMonth <= -28) {
            long j = (long) monthLength;
            if (dayOfMonth <= j || dayOfMonth >= ((long) (monthLength + 28))) {
                getCalendarDateFromFixedDate(date, (dayOfMonth + getFixedDate(normalizedYear, month, 1, date)) - 1);
            } else {
                int i2 = month + 1;
                date.setDayOfMonth((int) (dayOfMonth - j));
                if (i2 > 12) {
                    date.setNormalizedYear(normalizedYear + 1);
                    i2 = 1;
                }
                date.setMonth(i2);
            }
        } else {
            int i3 = month - 1;
            date.setDayOfMonth((int) (dayOfMonth + ((long) getMonthLength(normalizedYear, i3))));
            if (i3 == 0) {
                date.setNormalizedYear(normalizedYear - 1);
                i3 = 12;
            }
            date.setMonth(i3);
        }
        calendarDate.setLeapYear(isLeapYear(date.getNormalizedYear()));
        calendarDate.setZoneOffset(0);
        calendarDate.setDaylightSaving(0);
        date.setNormalized(true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void normalizeMonth(CalendarDate calendarDate) {
        Date date = (Date) calendarDate;
        int normalizedYear = date.getNormalizedYear();
        long month = (long) date.getMonth();
        if (month <= 0) {
            long j = 1 - month;
            date.setNormalizedYear(normalizedYear - ((int) ((j / 12) + 1)));
            date.setMonth((int) (13 - (j % 12)));
        } else if (month > 12) {
            long j2 = month - 1;
            date.setNormalizedYear(normalizedYear + ((int) (j2 / 12)));
            date.setMonth((int) ((j2 % 12) + 1));
        }
    }

    public int getYearLength(CalendarDate calendarDate) {
        return isLeapYear(((Date) calendarDate).getNormalizedYear()) ? 366 : 365;
    }

    public int getMonthLength(CalendarDate calendarDate) {
        Date date = (Date) calendarDate;
        int month = date.getMonth();
        if (month >= 1 && month <= 12) {
            return getMonthLength(date.getNormalizedYear(), month);
        }
        throw new IllegalArgumentException("Illegal month value: " + month);
    }

    private int getMonthLength(int i, int i2) {
        int i3 = DAYS_IN_MONTH[i2];
        return (i2 != 2 || !isLeapYear(i)) ? i3 : i3 + 1;
    }

    public long getDayOfYear(CalendarDate calendarDate) {
        return getDayOfYear(((Date) calendarDate).getNormalizedYear(), calendarDate.getMonth(), calendarDate.getDayOfMonth());
    }

    /* access modifiers changed from: package-private */
    public final long getDayOfYear(int i, int i2, int i3) {
        return ((long) i3) + ((long) (isLeapYear(i) ? ACCUMULATED_DAYS_IN_MONTH_LEAP[i2] : ACCUMULATED_DAYS_IN_MONTH[i2]));
    }

    @Override // sun.util.calendar.AbstractCalendar
    public long getFixedDate(CalendarDate calendarDate) {
        if (!calendarDate.isNormalized()) {
            normalizeMonth(calendarDate);
        }
        Date date = (Date) calendarDate;
        return getFixedDate(date.getNormalizedYear(), calendarDate.getMonth(), calendarDate.getDayOfMonth(), date);
    }

    public long getFixedDate(int i, int i2, int i3, Date date) {
        long j;
        int i4;
        boolean z = true;
        if (!(i2 == 1 && i3 == 1)) {
            z = false;
        }
        if (date == null || !date.hit(i)) {
            int i5 = i - 1970;
            if (i5 >= 0) {
                int[] iArr = FIXED_DATES;
                if (i5 < iArr.length) {
                    long j2 = (long) iArr[i5];
                    if (date != null) {
                        date.setCache(i, j2, isLeapYear(i) ? 366 : 365);
                    }
                    return z ? j2 : (j2 + getDayOfYear(i, i2, i3)) - 1;
                }
            }
            long j3 = ((long) i) - 1;
            long j4 = (long) i3;
            if (j3 >= 0) {
                j = (((365 * j3) + (j3 / 4)) - (j3 / 100)) + (j3 / 400);
                i4 = ((i2 * 367) - 362) / 12;
            } else {
                j = (((365 * j3) + CalendarUtils.floorDivide(j3, 4)) - CalendarUtils.floorDivide(j3, 100)) + CalendarUtils.floorDivide(j3, 400);
                i4 = CalendarUtils.floorDivide((i2 * 367) - 362, 12);
            }
            long j5 = j4 + j + ((long) i4);
            if (i2 > 2) {
                j5 -= isLeapYear(i) ? 1 : 2;
            }
            if (date != null && z) {
                date.setCache(i, j5, isLeapYear(i) ? 366 : 365);
            }
            return j5;
        } else if (z) {
            return date.getCachedJan1();
        } else {
            return (date.getCachedJan1() + getDayOfYear(i, i2, i3)) - 1;
        }
    }

    @Override // sun.util.calendar.AbstractCalendar
    public void getCalendarDateFromFixedDate(CalendarDate calendarDate, long j) {
        boolean z;
        long j2;
        int i;
        int i2;
        Date date = (Date) calendarDate;
        if (date.hit(j)) {
            i = date.getCachedYear();
            j2 = date.getCachedJan1();
            z = isLeapYear(i);
        } else {
            i = getGregorianYearFromFixedDate(j);
            j2 = getFixedDate(i, 1, 1, null);
            z = isLeapYear(i);
            date.setCache(i, j2, z ? 366 : 365);
        }
        int i3 = (int) (j - j2);
        long j3 = 31 + j2 + 28;
        if (z) {
            j3++;
        }
        if (j >= j3) {
            i3 += z ? 1 : 2;
        }
        int i4 = (i3 * 12) + 373;
        if (i4 > 0) {
            i2 = i4 / 367;
        } else {
            i2 = CalendarUtils.floorDivide(i4, 367);
        }
        long j4 = j2 + ((long) ACCUMULATED_DAYS_IN_MONTH[i2]);
        if (z && i2 >= 3) {
            j4++;
        }
        int dayOfWeekFromFixedDate = getDayOfWeekFromFixedDate(j);
        date.setNormalizedYear(i);
        date.setMonth(i2);
        date.setDayOfMonth(((int) (j - j4)) + 1);
        date.setDayOfWeek(dayOfWeekFromFixedDate);
        date.setLeapYear(z);
        date.setNormalized(true);
    }

    public int getDayOfWeek(CalendarDate calendarDate) {
        return getDayOfWeekFromFixedDate(getFixedDate(calendarDate));
    }

    public static final int getDayOfWeekFromFixedDate(long j) {
        long mod;
        if (j >= 0) {
            mod = j % 7;
        } else {
            mod = CalendarUtils.mod(j, 7);
        }
        return ((int) mod) + 1;
    }

    /* access modifiers changed from: package-private */
    public final int getGregorianYearFromFixedDate(long j) {
        int i;
        int i2;
        int i3;
        int i4;
        if (j > 0) {
            long j2 = j - 1;
            i2 = (int) (j2 / 146097);
            int i5 = (int) (j2 % 146097);
            i = i5 / 36524;
            int i6 = i5 % 36524;
            i4 = i6 / 1461;
            int i7 = i6 % 1461;
            i3 = i7 / 365;
            int i8 = i7 % 365;
        } else {
            long j3 = j - 1;
            i2 = (int) CalendarUtils.floorDivide(j3, 146097);
            int mod = (int) CalendarUtils.mod(j3, 146097);
            i = CalendarUtils.floorDivide(mod, 36524);
            int mod2 = CalendarUtils.mod(mod, 36524);
            i4 = CalendarUtils.floorDivide(mod2, 1461);
            int mod3 = CalendarUtils.mod(mod2, 1461);
            i3 = CalendarUtils.floorDivide(mod3, 365);
            CalendarUtils.mod(mod3, 365);
        }
        int i9 = (i2 * 400) + (i * 100) + (i4 * 4) + i3;
        return (i == 4 || i3 == 4) ? i9 : i9 + 1;
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.AbstractCalendar
    public boolean isLeapYear(CalendarDate calendarDate) {
        return isLeapYear(((Date) calendarDate).getNormalizedYear());
    }

    /* access modifiers changed from: package-private */
    public boolean isLeapYear(int i) {
        return CalendarUtils.isGregorianLeapYear(i);
    }
}
