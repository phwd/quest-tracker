package java.util;

import java.io.ObjectInputStream;
import java.util.Locale;
import libcore.util.ZoneInfo;
import sun.util.calendar.AbstractCalendar;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Era;
import sun.util.calendar.Gregorian;
import sun.util.calendar.JulianCalendar;

public class GregorianCalendar extends Calendar {
    static final int[] LEAP_MONTH_LENGTH = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final int[] LEAST_MAX_VALUES = {1, 292269054, 11, 52, 4, 28, 365, 7, 4, 1, 11, 23, 59, 59, 999, 50400000, 1200000};
    static final int[] MAX_VALUES = {1, 292278994, 11, 53, 6, 31, 366, 7, 6, 1, 11, 23, 59, 59, 999, 50400000, 7200000};
    static final int[] MIN_VALUES = {0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, -46800000, 0};
    static final int[] MONTH_LENGTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final Gregorian gcal = CalendarSystem.getGregorianCalendar();
    private static JulianCalendar jcal = null;
    private static Era[] jeras = null;
    static final long serialVersionUID = -8125100834729963327L;
    private transient long cachedFixedDate;
    private transient BaseCalendar calsys;
    private transient BaseCalendar.Date cdate;
    private transient BaseCalendar.Date gdate;
    private long gregorianCutover;
    private transient long gregorianCutoverDate;
    private transient int gregorianCutoverYear;
    private transient int gregorianCutoverYearJulian;
    private transient int[] originalFields;
    private transient int[] zoneOffsets;

    @Override // java.util.Calendar
    public String getCalendarType() {
        return "gregory";
    }

    @Override // java.util.Calendar
    public final boolean isWeekDateSupported() {
        return true;
    }

    public GregorianCalendar() {
        this(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
        setZoneShared(true);
    }

    public GregorianCalendar(TimeZone timeZone) {
        this(timeZone, Locale.getDefault(Locale.Category.FORMAT));
    }

    public GregorianCalendar(TimeZone timeZone, Locale locale) {
        super(timeZone, locale);
        this.gregorianCutover = -12219292800000L;
        this.gregorianCutoverDate = 577736;
        this.gregorianCutoverYear = 1582;
        this.gregorianCutoverYearJulian = 1582;
        this.cachedFixedDate = Long.MIN_VALUE;
        this.gdate = gcal.newCalendarDate(timeZone);
        setTimeInMillis(System.currentTimeMillis());
    }

    public boolean isLeapYear(int i) {
        if ((i & 3) != 0) {
            return false;
        }
        int i2 = this.gregorianCutoverYear;
        if (i > i2) {
            return i % 100 != 0 || i % 400 == 0;
        }
        int i3 = this.gregorianCutoverYearJulian;
        if (i < i3) {
            return true;
        }
        return !(i2 != i3 ? i == i2 : getCalendarDate(this.gregorianCutoverDate).getMonth() < 3) || i % 100 != 0 || i % 400 == 0;
    }

    @Override // java.util.Calendar
    public boolean equals(Object obj) {
        return (obj instanceof GregorianCalendar) && super.equals(obj) && this.gregorianCutover == ((GregorianCalendar) obj).gregorianCutover;
    }

    @Override // java.util.Calendar
    public int hashCode() {
        return ((int) this.gregorianCutoverDate) ^ super.hashCode();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Calendar
    public void add(int i, int i2) {
        long j;
        long j2;
        int i3;
        if (i2 != 0) {
            if (i < 0 || i >= 15) {
                throw new IllegalArgumentException();
            }
            complete();
            if (i == 1) {
                int internalGet = internalGet(1);
                if (internalGetEra() == 1) {
                    int i4 = internalGet + i2;
                    if (i4 > 0) {
                        set(1, i4);
                    } else {
                        set(1, 1 - i4);
                        set(0, 0);
                    }
                } else {
                    int i5 = internalGet - i2;
                    if (i5 > 0) {
                        set(1, i5);
                    } else {
                        set(1, 1 - i5);
                        set(0, 1);
                    }
                }
                pinDayOfMonth();
            } else if (i == 2) {
                int internalGet2 = internalGet(2) + i2;
                int internalGet3 = internalGet(1);
                if (internalGet2 >= 0) {
                    i3 = internalGet2 / 12;
                } else {
                    i3 = ((internalGet2 + 1) / 12) - 1;
                }
                if (i3 != 0) {
                    if (internalGetEra() == 1) {
                        int i6 = internalGet3 + i3;
                        if (i6 > 0) {
                            set(1, i6);
                        } else {
                            set(1, 1 - i6);
                            set(0, 0);
                        }
                    } else {
                        int i7 = internalGet3 - i3;
                        if (i7 > 0) {
                            set(1, i7);
                        } else {
                            set(1, 1 - i7);
                            set(0, 1);
                        }
                    }
                }
                if (internalGet2 >= 0) {
                    set(2, internalGet2 % 12);
                } else {
                    int i8 = internalGet2 % 12;
                    if (i8 < 0) {
                        i8 += 12;
                    }
                    set(2, i8 + 0);
                }
                pinDayOfMonth();
            } else if (i == 0) {
                int internalGet4 = internalGet(0) + i2;
                if (internalGet4 < 0) {
                    internalGet4 = 0;
                }
                if (internalGet4 > 1) {
                    internalGet4 = 1;
                }
                set(0, internalGet4);
            } else {
                long j3 = (long) i2;
                switch (i) {
                    case 3:
                    case 4:
                    case 8:
                        j2 = 7;
                        j3 *= j2;
                        j = 0;
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 14:
                    default:
                        j = 0;
                        break;
                    case 9:
                        j3 = (long) (i2 / 2);
                        j = (long) ((i2 % 2) * 12);
                        break;
                    case 10:
                    case 11:
                        j2 = 3600000;
                        j3 *= j2;
                        j = 0;
                        break;
                    case 12:
                        j2 = 60000;
                        j3 *= j2;
                        j = 0;
                        break;
                    case 13:
                        j3 *= 1000;
                        j = 0;
                        break;
                }
                if (i >= 10) {
                    setTimeInMillis(this.time + j3);
                    return;
                }
                long currentFixedDate = getCurrentFixedDate();
                long internalGet5 = ((((((j + ((long) internalGet(11))) * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
                if (internalGet5 >= 86400000) {
                    currentFixedDate++;
                    internalGet5 -= 86400000;
                } else if (internalGet5 < 0) {
                    currentFixedDate--;
                    internalGet5 += 86400000;
                }
                setTimeInMillis(adjustForZoneAndDaylightSavingsTime(0, (((currentFixedDate + j3) - 719163) * 86400000) + internalGet5, getZone()));
            }
        }
    }

    @Override // java.util.Calendar
    public int getMinimum(int i) {
        return MIN_VALUES[i];
    }

    @Override // java.util.Calendar
    public int getMaximum(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                if (this.gregorianCutoverYear <= 200) {
                    GregorianCalendar gregorianCalendar = (GregorianCalendar) clone();
                    gregorianCalendar.setLenient(true);
                    gregorianCalendar.setTimeInMillis(this.gregorianCutover);
                    int actualMaximum = gregorianCalendar.getActualMaximum(i);
                    gregorianCalendar.setTimeInMillis(this.gregorianCutover - 1);
                    return Math.max(MAX_VALUES[i], Math.max(actualMaximum, gregorianCalendar.getActualMaximum(i)));
                }
                break;
        }
        return MAX_VALUES[i];
    }

    @Override // java.util.Calendar
    public int getGreatestMinimum(int i) {
        if (i != 5) {
            return MIN_VALUES[i];
        }
        return Math.max(MIN_VALUES[i], getCalendarDate(getFixedDateMonth1(getGregorianCutoverDate(), this.gregorianCutoverDate)).getDayOfMonth());
    }

    @Override // java.util.Calendar
    public int getLeastMaximum(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                GregorianCalendar gregorianCalendar = (GregorianCalendar) clone();
                gregorianCalendar.setLenient(true);
                gregorianCalendar.setTimeInMillis(this.gregorianCutover);
                int actualMaximum = gregorianCalendar.getActualMaximum(i);
                gregorianCalendar.setTimeInMillis(this.gregorianCutover - 1);
                return Math.min(LEAST_MAX_VALUES[i], Math.min(actualMaximum, gregorianCalendar.getActualMaximum(i)));
            case 7:
            default:
                return LEAST_MAX_VALUES[i];
        }
    }

    public int getActualMinimum(int i) {
        if (i == 5) {
            GregorianCalendar normalizedCalendar = getNormalizedCalendar();
            int normalizedYear = normalizedCalendar.cdate.getNormalizedYear();
            if (normalizedYear == this.gregorianCutoverYear || normalizedYear == this.gregorianCutoverYearJulian) {
                BaseCalendar.Date date = normalizedCalendar.cdate;
                return getCalendarDate(getFixedDateMonth1(date, normalizedCalendar.calsys.getFixedDate(date))).getDayOfMonth();
            }
        }
        return getMinimum(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0244, code lost:
        if (r4 < r1) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01f5, code lost:
        if (r4 > r1.getYearOffsetInMillis()) goto L_0x0246;
     */
    @Override // java.util.Calendar
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getActualMaximum(int r14) {
        /*
        // Method dump skipped, instructions count: 608
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.GregorianCalendar.getActualMaximum(int):int");
    }

    private long getYearOffsetInMillis() {
        return (((((((((long) ((internalGet(6) - 1) * 24)) + ((long) internalGet(11))) * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14))) - ((long) (internalGet(15) + internalGet(16)));
    }

    @Override // java.util.Calendar
    public Object clone() {
        GregorianCalendar gregorianCalendar = (GregorianCalendar) super.clone();
        gregorianCalendar.gdate = (BaseCalendar.Date) this.gdate.clone();
        BaseCalendar.Date date = this.cdate;
        if (date != null) {
            if (date != this.gdate) {
                gregorianCalendar.cdate = (BaseCalendar.Date) date.clone();
            } else {
                gregorianCalendar.cdate = gregorianCalendar.gdate;
            }
        }
        gregorianCalendar.originalFields = null;
        gregorianCalendar.zoneOffsets = null;
        return gregorianCalendar;
    }

    @Override // java.util.Calendar
    public TimeZone getTimeZone() {
        TimeZone timeZone = super.getTimeZone();
        this.gdate.setZone(timeZone);
        BaseCalendar.Date date = this.cdate;
        if (!(date == null || date == this.gdate)) {
            date.setZone(timeZone);
        }
        return timeZone;
    }

    @Override // java.util.Calendar
    public void setTimeZone(TimeZone timeZone) {
        super.setTimeZone(timeZone);
        this.gdate.setZone(timeZone);
        BaseCalendar.Date date = this.cdate;
        if (date != null && date != this.gdate) {
            date.setZone(timeZone);
        }
    }

    @Override // java.util.Calendar
    public int getWeekYear() {
        int i = get(1);
        if (internalGetEra() == 0) {
            i = 1 - i;
        }
        if (i > this.gregorianCutoverYear + 1) {
            int internalGet = internalGet(3);
            return internalGet(2) == 0 ? internalGet >= 52 ? i - 1 : i : internalGet == 1 ? i + 1 : i;
        }
        int internalGet2 = internalGet(6);
        int actualMaximum = getActualMaximum(6);
        int minimalDaysInFirstWeek = getMinimalDaysInFirstWeek();
        if (internalGet2 > minimalDaysInFirstWeek && internalGet2 < actualMaximum - 6) {
            return i;
        }
        GregorianCalendar gregorianCalendar = (GregorianCalendar) clone();
        gregorianCalendar.setLenient(true);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        gregorianCalendar.set(6, 1);
        gregorianCalendar.complete();
        int firstDayOfWeek = getFirstDayOfWeek() - gregorianCalendar.get(7);
        if (firstDayOfWeek != 0) {
            if (firstDayOfWeek < 0) {
                firstDayOfWeek += 7;
            }
            gregorianCalendar.add(6, firstDayOfWeek);
        }
        int i2 = gregorianCalendar.get(6);
        if (internalGet2 >= i2) {
            int i3 = i + 1;
            gregorianCalendar.set(1, i3);
            gregorianCalendar.set(6, 1);
            gregorianCalendar.complete();
            int firstDayOfWeek2 = getFirstDayOfWeek() - gregorianCalendar.get(7);
            if (firstDayOfWeek2 != 0) {
                if (firstDayOfWeek2 < 0) {
                    firstDayOfWeek2 += 7;
                }
                gregorianCalendar.add(6, firstDayOfWeek2);
            }
            int i4 = gregorianCalendar.get(6) - 1;
            if (i4 == 0) {
                i4 = 7;
            }
            return (i4 < minimalDaysInFirstWeek || (actualMaximum - internalGet2) + 1 > 7 - i4) ? i : i3;
        } else if (i2 <= minimalDaysInFirstWeek) {
            return i - 1;
        } else {
            return i;
        }
    }

    @Override // java.util.Calendar
    public void setWeekDate(int i, int i2, int i3) {
        if (i3 < 1 || i3 > 7) {
            throw new IllegalArgumentException("invalid dayOfWeek: " + i3);
        }
        GregorianCalendar gregorianCalendar = (GregorianCalendar) clone();
        gregorianCalendar.setLenient(true);
        int i4 = gregorianCalendar.get(0);
        gregorianCalendar.clear();
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        gregorianCalendar.set(0, i4);
        gregorianCalendar.set(1, i);
        gregorianCalendar.set(3, 1);
        gregorianCalendar.set(7, getFirstDayOfWeek());
        int firstDayOfWeek = i3 - getFirstDayOfWeek();
        if (firstDayOfWeek < 0) {
            firstDayOfWeek += 7;
        }
        int i5 = firstDayOfWeek + ((i2 - 1) * 7);
        if (i5 != 0) {
            gregorianCalendar.add(6, i5);
        } else {
            gregorianCalendar.complete();
        }
        if (isLenient() || (gregorianCalendar.getWeekYear() == i && gregorianCalendar.internalGet(3) == i2 && gregorianCalendar.internalGet(7) == i3)) {
            set(0, gregorianCalendar.internalGet(0));
            set(1, gregorianCalendar.internalGet(1));
            set(2, gregorianCalendar.internalGet(2));
            set(5, gregorianCalendar.internalGet(5));
            internalSet(3, i2);
            complete();
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Calendar
    public void computeFields() {
        int i = 131071;
        if (isPartiallyNormalized()) {
            int setStateFields = getSetStateFields();
            int i2 = 131071 & (~setStateFields);
            if (i2 != 0 || this.calsys == null) {
                setStateFields |= computeFields(i2, 98304 & setStateFields);
            }
            i = setStateFields;
        } else {
            computeFields(131071, 0);
        }
        setFieldsComputed(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x027b, code lost:
        if (r10 >= (r12 - 7)) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02a8, code lost:
        if (r10 >= (r12 - 7)) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01f5, code lost:
        if (sun.util.calendar.CalendarUtils.isGregorianLeapYear(r1 - 1) != false) goto L_0x01f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0203, code lost:
        if (sun.util.calendar.CalendarUtils.isJulianLeapYear(r1 - 1) != false) goto L_0x01f7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int computeFields(int r21, int r22) {
        /*
        // Method dump skipped, instructions count: 699
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.GregorianCalendar.computeFields(int, int):int");
    }

    private int getWeekNumber(long j, long j2) {
        int floorDivide;
        long dayOfWeekDateOnOrBefore = AbstractCalendar.getDayOfWeekDateOnOrBefore(6 + j, getFirstDayOfWeek());
        if (((int) (dayOfWeekDateOnOrBefore - j)) >= getMinimalDaysInFirstWeek()) {
            dayOfWeekDateOnOrBefore -= 7;
        }
        int i = (int) (j2 - dayOfWeekDateOnOrBefore);
        if (i >= 0) {
            floorDivide = i / 7;
        } else {
            floorDivide = CalendarUtils.floorDivide(i, 7);
        }
        return floorDivide + 1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0123, code lost:
        if (r5 == r4) goto L_0x0139;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0137, code lost:
        if (r4 == null) goto L_0x0139;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0169  */
    @Override // java.util.Calendar
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void computeTime() {
        /*
        // Method dump skipped, instructions count: 470
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.GregorianCalendar.computeTime():void");
    }

    private long adjustForZoneAndDaylightSavingsTime(int i, long j, TimeZone timeZone) {
        int i2;
        int i3;
        int i4 = 0;
        if (i != 98304) {
            if (this.zoneOffsets == null) {
                this.zoneOffsets = new int[2];
            }
            if (Calendar.isFieldSet(i, 15)) {
                i3 = internalGet(15);
            } else {
                i3 = timeZone.getRawOffset();
            }
            long j2 = j - ((long) i3);
            if (timeZone instanceof ZoneInfo) {
                ((ZoneInfo) timeZone).getOffsetsByUtcTime(j2, this.zoneOffsets);
            } else {
                timeZone.getOffsets(j2, this.zoneOffsets);
            }
            int[] iArr = this.zoneOffsets;
            i4 = iArr[0];
            i2 = adjustDstOffsetForInvalidWallClock(j2, timeZone, iArr[1]);
        } else {
            i2 = 0;
        }
        if (i != 0) {
            if (Calendar.isFieldSet(i, 15)) {
                i4 = internalGet(15);
            }
            if (Calendar.isFieldSet(i, 16)) {
                i2 = internalGet(16);
            }
        }
        return (j - ((long) i4)) - ((long) i2);
    }

    private int adjustDstOffsetForInvalidWallClock(long j, TimeZone timeZone, int i) {
        if (i == 0 || timeZone.inDaylightTime(new Date(j - ((long) i)))) {
            return i;
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e5, code lost:
        if (r3 != r17.gregorianCutoverYearJulian) goto L_0x00e9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long getFixedDate(sun.util.calendar.BaseCalendar r18, int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.GregorianCalendar.getFixedDate(sun.util.calendar.BaseCalendar, int, int):long");
    }

    private GregorianCalendar getNormalizedCalendar() {
        if (isFullyNormalized()) {
            return this;
        }
        GregorianCalendar gregorianCalendar = (GregorianCalendar) clone();
        gregorianCalendar.setLenient(true);
        gregorianCalendar.complete();
        return gregorianCalendar;
    }

    private static synchronized BaseCalendar getJulianCalendarSystem() {
        JulianCalendar julianCalendar;
        synchronized (GregorianCalendar.class) {
            if (jcal == null) {
                jcal = (JulianCalendar) CalendarSystem.forName("julian");
                jeras = jcal.getEras();
            }
            julianCalendar = jcal;
        }
        return julianCalendar;
    }

    private BaseCalendar getCutoverCalendarSystem() {
        if (this.gregorianCutoverYearJulian < this.gregorianCutoverYear) {
            return gcal;
        }
        return getJulianCalendarSystem();
    }

    private boolean isCutoverYear(int i) {
        return i == (this.calsys == gcal ? this.gregorianCutoverYear : this.gregorianCutoverYearJulian);
    }

    private long getFixedDateJan1(BaseCalendar.Date date, long j) {
        if (this.gregorianCutoverYear != this.gregorianCutoverYearJulian) {
            long j2 = this.gregorianCutoverDate;
            if (j >= j2) {
                return j2;
            }
        }
        return getJulianCalendarSystem().getFixedDate(date.getNormalizedYear(), 1, 1, null);
    }

    private long getFixedDateMonth1(BaseCalendar.Date date, long j) {
        BaseCalendar.Date gregorianCutoverDate2 = getGregorianCutoverDate();
        if (gregorianCutoverDate2.getMonth() == 1 && gregorianCutoverDate2.getDayOfMonth() == 1) {
            return (j - ((long) date.getDayOfMonth())) + 1;
        }
        if (date.getMonth() != gregorianCutoverDate2.getMonth()) {
            return (j - ((long) date.getDayOfMonth())) + 1;
        }
        BaseCalendar.Date lastJulianDate = getLastJulianDate();
        if (this.gregorianCutoverYear == this.gregorianCutoverYearJulian && gregorianCutoverDate2.getMonth() == lastJulianDate.getMonth()) {
            return jcal.getFixedDate(date.getNormalizedYear(), date.getMonth(), 1, null);
        }
        return this.gregorianCutoverDate;
    }

    private BaseCalendar.Date getCalendarDate(long j) {
        BaseCalendar julianCalendarSystem = j >= this.gregorianCutoverDate ? gcal : getJulianCalendarSystem();
        BaseCalendar.Date date = (BaseCalendar.Date) julianCalendarSystem.newCalendarDate(TimeZone.NO_TIMEZONE);
        julianCalendarSystem.getCalendarDateFromFixedDate(date, j);
        return date;
    }

    private BaseCalendar.Date getGregorianCutoverDate() {
        return getCalendarDate(this.gregorianCutoverDate);
    }

    private BaseCalendar.Date getLastJulianDate() {
        return getCalendarDate(this.gregorianCutoverDate - 1);
    }

    private int monthLength(int i, int i2) {
        return isLeapYear(i2) ? LEAP_MONTH_LENGTH[i] : MONTH_LENGTH[i];
    }

    private int monthLength(int i) {
        int internalGet = internalGet(1);
        if (internalGetEra() == 0) {
            internalGet = 1 - internalGet;
        }
        return monthLength(i, internalGet);
    }

    private int actualMonthLength() {
        int normalizedYear = this.cdate.getNormalizedYear();
        if (normalizedYear != this.gregorianCutoverYear && normalizedYear != this.gregorianCutoverYearJulian) {
            return this.calsys.getMonthLength(this.cdate);
        }
        BaseCalendar.Date date = (BaseCalendar.Date) this.cdate.clone();
        long fixedDateMonth1 = getFixedDateMonth1(date, this.calsys.getFixedDate(date));
        long monthLength = ((long) this.calsys.getMonthLength(date)) + fixedDateMonth1;
        if (monthLength < this.gregorianCutoverDate) {
            return (int) (monthLength - fixedDateMonth1);
        }
        if (this.cdate != this.gdate) {
            date = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        }
        gcal.getCalendarDateFromFixedDate(date, monthLength);
        return (int) (getFixedDateMonth1(date, monthLength) - fixedDateMonth1);
    }

    private void pinDayOfMonth() {
        int i;
        int internalGet = internalGet(1);
        if (internalGet > this.gregorianCutoverYear || internalGet < this.gregorianCutoverYearJulian) {
            i = monthLength(internalGet(2));
        } else {
            i = getNormalizedCalendar().getActualMaximum(5);
        }
        if (internalGet(5) > i) {
            set(5, i);
        }
    }

    private long getCurrentFixedDate() {
        BaseCalendar baseCalendar = this.calsys;
        return baseCalendar == gcal ? this.cachedFixedDate : baseCalendar.getFixedDate(this.cdate);
    }

    private int internalGetEra() {
        if (isSet(0)) {
            return internalGet(0);
        }
        return 1;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
