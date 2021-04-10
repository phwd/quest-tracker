package java.util;

import android.icu.impl.Grego;
import android.icu.impl.number.RoundingUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Locale;
import libcore.icu.RelativeDateTimeFormatter;
import libcore.util.ZoneInfo;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Era;
import sun.util.calendar.Gregorian;
import sun.util.calendar.JulianCalendar;

public class GregorianCalendar extends Calendar {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int AD = 1;
    public static final int BC = 0;
    static final int BCE = 0;
    static final int CE = 1;
    static final long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;
    private static final int EPOCH_OFFSET = 719163;
    private static final int EPOCH_YEAR = 1970;
    static final int[] LEAP_MONTH_LENGTH = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final int[] LEAST_MAX_VALUES = {1, 292269054, 11, 52, 4, 28, 365, 7, 4, 1, 11, 23, 59, 59, RoundingUtils.MAX_INT_FRAC_SIG, 50400000, 1200000};
    static final int[] MAX_VALUES = {1, 292278994, 11, 53, 6, 31, 366, 7, 6, 1, 11, 23, 59, 59, RoundingUtils.MAX_INT_FRAC_SIG, 50400000, 7200000};
    static final int[] MIN_VALUES = {0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, -46800000, 0};
    static final int[] MONTH_LENGTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final long ONE_DAY = 86400000;
    private static final int ONE_HOUR = 3600000;
    private static final int ONE_MINUTE = 60000;
    private static final int ONE_SECOND = 1000;
    private static final long ONE_WEEK = 604800000;
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

    public GregorianCalendar() {
        this(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
        setZoneShared(true);
    }

    public GregorianCalendar(TimeZone zone) {
        this(zone, Locale.getDefault(Locale.Category.FORMAT));
    }

    public GregorianCalendar(Locale aLocale) {
        this(TimeZone.getDefaultRef(), aLocale);
        setZoneShared(true);
    }

    public GregorianCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
        this.gregorianCutover = DEFAULT_GREGORIAN_CUTOVER;
        this.gregorianCutoverDate = 577736;
        this.gregorianCutoverYear = 1582;
        this.gregorianCutoverYearJulian = 1582;
        this.cachedFixedDate = Long.MIN_VALUE;
        this.gdate = gcal.newCalendarDate(zone);
        setTimeInMillis(System.currentTimeMillis());
    }

    public GregorianCalendar(int year, int month, int dayOfMonth) {
        this(year, month, dayOfMonth, 0, 0, 0, 0);
    }

    public GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this(year, month, dayOfMonth, hourOfDay, minute, 0, 0);
    }

    public GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        this(year, month, dayOfMonth, hourOfDay, minute, second, 0);
    }

    GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millis) {
        this.gregorianCutover = DEFAULT_GREGORIAN_CUTOVER;
        this.gregorianCutoverDate = 577736;
        this.gregorianCutoverYear = 1582;
        this.gregorianCutoverYearJulian = 1582;
        this.cachedFixedDate = Long.MIN_VALUE;
        this.gdate = gcal.newCalendarDate(getZone());
        set(1, year);
        set(2, month);
        set(5, dayOfMonth);
        if (hourOfDay < 12 || hourOfDay > 23) {
            internalSet(10, hourOfDay);
        } else {
            internalSet(9, 1);
            internalSet(10, hourOfDay - 12);
        }
        setFieldsComputed(1536);
        set(11, hourOfDay);
        set(12, minute);
        set(13, second);
        internalSet(14, millis);
    }

    GregorianCalendar(TimeZone zone, Locale locale, boolean flag) {
        super(zone, locale);
        this.gregorianCutover = DEFAULT_GREGORIAN_CUTOVER;
        this.gregorianCutoverDate = 577736;
        this.gregorianCutoverYear = 1582;
        this.gregorianCutoverYearJulian = 1582;
        this.cachedFixedDate = Long.MIN_VALUE;
        this.gdate = gcal.newCalendarDate(getZone());
    }

    GregorianCalendar(long milliseconds) {
        this();
        setTimeInMillis(milliseconds);
    }

    public void setGregorianChange(Date date) {
        long cutoverTime = date.getTime();
        if (cutoverTime != this.gregorianCutover) {
            complete();
            setGregorianChange(cutoverTime);
        }
    }

    private void setGregorianChange(long cutoverTime) {
        this.gregorianCutover = cutoverTime;
        this.gregorianCutoverDate = CalendarUtils.floorDivide(cutoverTime, 86400000) + 719163;
        if (cutoverTime == Long.MAX_VALUE) {
            this.gregorianCutoverDate++;
        }
        this.gregorianCutoverYear = getGregorianCutoverDate().getYear();
        BaseCalendar julianCal = getJulianCalendarSystem();
        BaseCalendar.Date d = (BaseCalendar.Date) julianCal.newCalendarDate(TimeZone.NO_TIMEZONE);
        julianCal.getCalendarDateFromFixedDate(d, this.gregorianCutoverDate - 1);
        this.gregorianCutoverYearJulian = d.getNormalizedYear();
        if (this.time < this.gregorianCutover) {
            setUnnormalized();
        }
    }

    public final Date getGregorianChange() {
        return new Date(this.gregorianCutover);
    }

    public boolean isLeapYear(int year) {
        BaseCalendar.Date d;
        if ((year & 3) != 0) {
            return false;
        }
        int i = this.gregorianCutoverYear;
        if (year <= i) {
            int i2 = this.gregorianCutoverYearJulian;
            if (year < i2) {
                return true;
            }
            if (i == i2) {
                d = getCalendarDate(this.gregorianCutoverDate).getMonth() < 3 ? 1 : null;
            } else {
                d = year == i ? 1 : null;
            }
            if (d == null || year % 100 != 0 || year % 400 == 0) {
                return true;
            }
            return false;
        } else if (year % 100 != 0 || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override // java.util.Calendar
    public String getCalendarType() {
        return "gregory";
    }

    @Override // java.util.Calendar
    public boolean equals(Object obj) {
        return (obj instanceof GregorianCalendar) && super.equals(obj) && this.gregorianCutover == ((GregorianCalendar) obj).gregorianCutover;
    }

    @Override // java.util.Calendar
    public int hashCode() {
        return super.hashCode() ^ ((int) this.gregorianCutoverDate);
    }

    @Override // java.util.Calendar
    public void add(int field, int amount) {
        int y_amount;
        if (amount != 0) {
            if (field < 0 || field >= 15) {
                throw new IllegalArgumentException();
            }
            complete();
            if (field == 1) {
                int year = internalGet(1);
                if (internalGetEra() == 1) {
                    int year2 = year + amount;
                    if (year2 > 0) {
                        set(1, year2);
                    } else {
                        set(1, 1 - year2);
                        set(0, 0);
                    }
                } else {
                    int year3 = year - amount;
                    if (year3 > 0) {
                        set(1, year3);
                    } else {
                        set(1, 1 - year3);
                        set(0, 1);
                    }
                }
                pinDayOfMonth();
            } else if (field == 2) {
                int month = internalGet(2) + amount;
                int year4 = internalGet(1);
                if (month >= 0) {
                    y_amount = month / 12;
                } else {
                    y_amount = ((month + 1) / 12) - 1;
                }
                if (y_amount != 0) {
                    if (internalGetEra() == 1) {
                        int year5 = year4 + y_amount;
                        if (year5 > 0) {
                            set(1, year5);
                        } else {
                            set(1, 1 - year5);
                            set(0, 0);
                        }
                    } else {
                        int year6 = year4 - y_amount;
                        if (year6 > 0) {
                            set(1, year6);
                        } else {
                            set(1, 1 - year6);
                            set(0, 1);
                        }
                    }
                }
                if (month >= 0) {
                    set(2, month % 12);
                } else {
                    int month2 = month % 12;
                    if (month2 < 0) {
                        month2 += 12;
                    }
                    set(2, month2 + 0);
                }
                pinDayOfMonth();
            } else if (field == 0) {
                int era = internalGet(0) + amount;
                if (era < 0) {
                    era = 0;
                }
                if (era > 1) {
                    era = 1;
                }
                set(0, era);
            } else {
                long delta = (long) amount;
                long timeOfDay = 0;
                switch (field) {
                    case 3:
                    case 4:
                    case 8:
                        delta *= 7;
                        break;
                    case 9:
                        delta = (long) (amount / 2);
                        timeOfDay = (long) ((amount % 2) * 12);
                        break;
                    case 10:
                    case 11:
                        delta *= RelativeDateTimeFormatter.HOUR_IN_MILLIS;
                        break;
                    case 12:
                        delta *= RelativeDateTimeFormatter.MINUTE_IN_MILLIS;
                        break;
                    case 13:
                        delta *= 1000;
                        break;
                }
                if (field >= 10) {
                    setTimeInMillis(this.time + delta);
                    return;
                }
                long fd = getCurrentFixedDate();
                long timeOfDay2 = ((((((timeOfDay + ((long) internalGet(11))) * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
                if (timeOfDay2 >= 86400000) {
                    fd++;
                    timeOfDay2 -= 86400000;
                } else if (timeOfDay2 < 0) {
                    fd--;
                    timeOfDay2 += 86400000;
                }
                setTimeInMillis(adjustForZoneAndDaylightSavingsTime(0, (((fd + delta) - 719163) * 86400000) + timeOfDay2, getZone()));
            }
        }
    }

    @Override // java.util.Calendar
    public void roll(int field, boolean up) {
        roll(field, up ? 1 : -1);
    }

    @Override // java.util.Calendar
    public void roll(int field, int amount) {
        int min;
        int min2;
        BaseCalendar cal;
        int min3;
        long month1;
        int monthLength;
        int dayOfMonth;
        int weekOfYear;
        int amount2 = amount;
        if (amount2 != 0) {
            if (field < 0 || field >= 15) {
                throw new IllegalArgumentException();
            }
            complete();
            int min4 = getMinimum(field);
            int max = getMaximum(field);
            switch (field) {
                case 0:
                case 1:
                case 9:
                case 12:
                case 13:
                case 14:
                    min2 = min4;
                    min = min2;
                    break;
                case 2:
                    if (!isCutoverYear(this.cdate.getNormalizedYear())) {
                        int mon = (internalGet(2) + amount2) % 12;
                        if (mon < 0) {
                            mon += 12;
                        }
                        set(2, mon);
                        int monthLen = monthLength(mon);
                        if (internalGet(5) > monthLen) {
                            set(5, monthLen);
                            return;
                        }
                        return;
                    }
                    int yearLength = getActualMaximum(2) + 1;
                    int mon2 = (internalGet(2) + amount2) % yearLength;
                    if (mon2 < 0) {
                        mon2 += yearLength;
                    }
                    set(2, mon2);
                    int monthLen2 = getActualMaximum(5);
                    if (internalGet(5) > monthLen2) {
                        set(5, monthLen2);
                        return;
                    }
                    return;
                case 3:
                    int y = this.cdate.getNormalizedYear();
                    int max2 = getActualMaximum(3);
                    set(7, internalGet(7));
                    int woy = internalGet(3);
                    int value = woy + amount2;
                    if (!isCutoverYear(y)) {
                        int weekYear = getWeekYear();
                        if (weekYear == y) {
                            min3 = min4;
                            if (value <= min3 || value >= max2) {
                                long fd = getCurrentFixedDate();
                                if (this.calsys.getYearFromFixedDate(fd - ((long) ((woy - min3) * 7))) != y) {
                                    min3++;
                                }
                                if (this.calsys.getYearFromFixedDate(fd + ((long) ((max2 - internalGet(3)) * 7))) != y) {
                                    max2--;
                                }
                            } else {
                                set(3, value);
                                return;
                            }
                        } else {
                            min3 = min4;
                            if (weekYear > y) {
                                if (amount2 < 0) {
                                    amount2++;
                                }
                                woy = max2;
                            } else {
                                if (amount2 > 0) {
                                    amount2 -= woy - max2;
                                }
                                woy = min3;
                            }
                        }
                        set(field, getRolledValue(woy, amount2, min3, max2));
                        return;
                    }
                    int min5 = min4;
                    long fd2 = getCurrentFixedDate();
                    int i = this.gregorianCutoverYear;
                    if (i == this.gregorianCutoverYearJulian) {
                        cal = getCutoverCalendarSystem();
                    } else if (y == i) {
                        cal = gcal;
                    } else {
                        cal = getJulianCalendarSystem();
                    }
                    long day1 = fd2 - ((long) ((woy - min5) * 7));
                    if (cal.getYearFromFixedDate(day1) != y) {
                        min5++;
                    }
                    long fd3 = fd2 + ((long) ((max2 - woy) * 7));
                    if ((fd3 >= this.gregorianCutoverDate ? gcal : getJulianCalendarSystem()).getYearFromFixedDate(fd3) != y) {
                        max2--;
                    }
                    BaseCalendar.Date d = getCalendarDate(((long) ((getRolledValue(woy, amount2, min5, max2) - 1) * 7)) + day1);
                    set(2, d.getMonth() - 1);
                    set(5, d.getDayOfMonth());
                    return;
                case 4:
                    boolean isCutoverYear = isCutoverYear(this.cdate.getNormalizedYear());
                    int dow = internalGet(7) - getFirstDayOfWeek();
                    if (dow < 0) {
                        dow += 7;
                    }
                    long fd4 = getCurrentFixedDate();
                    if (isCutoverYear) {
                        month1 = getFixedDateMonth1(this.cdate, fd4);
                        monthLength = actualMonthLength();
                    } else {
                        month1 = (fd4 - ((long) internalGet(5))) + 1;
                        monthLength = this.calsys.getMonthLength(this.cdate);
                    }
                    long monthDay1st = BaseCalendar.getDayOfWeekDateOnOrBefore(6 + month1, getFirstDayOfWeek());
                    if (((int) (monthDay1st - month1)) >= getMinimalDaysInFirstWeek()) {
                        monthDay1st -= 7;
                    }
                    long nfd = ((long) ((getRolledValue(internalGet(field), amount2, 1, getActualMaximum(field)) - 1) * 7)) + monthDay1st + ((long) dow);
                    if (nfd < month1) {
                        nfd = month1;
                    } else if (nfd >= ((long) monthLength) + month1) {
                        nfd = (((long) monthLength) + month1) - 1;
                    }
                    if (isCutoverYear) {
                        dayOfMonth = getCalendarDate(nfd).getDayOfMonth();
                    } else {
                        dayOfMonth = ((int) (nfd - month1)) + 1;
                    }
                    set(5, dayOfMonth);
                    return;
                case 5:
                    if (!isCutoverYear(this.cdate.getNormalizedYear())) {
                        max = this.calsys.getMonthLength(this.cdate);
                        min = min4;
                        break;
                    } else {
                        long fd5 = getCurrentFixedDate();
                        long month12 = getFixedDateMonth1(this.cdate, fd5);
                        set(5, getCalendarDate(((long) getRolledValue((int) (fd5 - month12), amount2, 0, actualMonthLength() - 1)) + month12).getDayOfMonth());
                        return;
                    }
                case 6:
                    max = getActualMaximum(field);
                    if (!isCutoverYear(this.cdate.getNormalizedYear())) {
                        min = min4;
                        break;
                    } else {
                        long fd6 = getCurrentFixedDate();
                        long jan1 = (fd6 - ((long) internalGet(6))) + 1;
                        BaseCalendar.Date d2 = getCalendarDate((((long) getRolledValue(((int) (fd6 - jan1)) + 1, amount2, min4, max)) + jan1) - 1);
                        set(2, d2.getMonth() - 1);
                        set(5, d2.getDayOfMonth());
                        return;
                    }
                case 7:
                    if (!isCutoverYear(this.cdate.getNormalizedYear()) && (weekOfYear = internalGet(3)) > 1 && weekOfYear < 52) {
                        set(3, weekOfYear);
                        max = 7;
                        min = min4;
                        break;
                    } else {
                        int amount3 = amount2 % 7;
                        if (amount3 != 0) {
                            long fd7 = getCurrentFixedDate();
                            long dowFirst = BaseCalendar.getDayOfWeekDateOnOrBefore(fd7, getFirstDayOfWeek());
                            long fd8 = fd7 + ((long) amount3);
                            if (fd8 < dowFirst) {
                                fd8 += 7;
                            } else if (fd8 >= dowFirst + 7) {
                                fd8 -= 7;
                            }
                            BaseCalendar.Date d3 = getCalendarDate(fd8);
                            set(0, d3.getNormalizedYear() <= 0 ? 0 : 1);
                            set(d3.getYear(), d3.getMonth() - 1, d3.getDayOfMonth());
                            return;
                        }
                        return;
                    }
                case 8:
                    min = 1;
                    if (!isCutoverYear(this.cdate.getNormalizedYear())) {
                        int dom = internalGet(5);
                        int monthLength2 = this.calsys.getMonthLength(this.cdate);
                        max = monthLength2 / 7;
                        if ((dom - 1) % 7 < monthLength2 % 7) {
                            max++;
                        }
                        set(7, internalGet(7));
                        break;
                    } else {
                        long fd9 = getCurrentFixedDate();
                        long month13 = getFixedDateMonth1(this.cdate, fd9);
                        int monthLength3 = actualMonthLength();
                        int max3 = monthLength3 / 7;
                        int x = ((int) (fd9 - month13)) % 7;
                        if (x < monthLength3 % 7) {
                            max3++;
                        }
                        long fd10 = ((long) ((getRolledValue(internalGet(field), amount2, 1, max3) - 1) * 7)) + month13 + ((long) x);
                        BaseCalendar cal2 = fd10 >= this.gregorianCutoverDate ? gcal : getJulianCalendarSystem();
                        BaseCalendar.Date d4 = (BaseCalendar.Date) cal2.newCalendarDate(TimeZone.NO_TIMEZONE);
                        cal2.getCalendarDateFromFixedDate(d4, fd10);
                        set(5, d4.getDayOfMonth());
                        return;
                    }
                case 10:
                case 11:
                    int unit = max + 1;
                    int h = internalGet(field);
                    int nh = (h + amount2) % unit;
                    if (nh < 0) {
                        nh += unit;
                    }
                    this.time += (long) ((nh - h) * 3600000);
                    CalendarDate d5 = this.calsys.getCalendarDate(this.time, getZone());
                    if (internalGet(5) != d5.getDayOfMonth()) {
                        d5.setDate(internalGet(1), internalGet(2) + 1, internalGet(5));
                        if (field == 10) {
                            d5.addHours(12);
                        }
                        this.time = this.calsys.getTime(d5);
                    }
                    int hourOfDay = d5.getHours();
                    internalSet(field, hourOfDay % unit);
                    if (field == 10) {
                        internalSet(11, hourOfDay);
                    } else {
                        internalSet(9, hourOfDay / 12);
                        internalSet(10, hourOfDay % 12);
                    }
                    int zoneOffset = d5.getZoneOffset();
                    int saving = d5.getDaylightSaving();
                    internalSet(15, zoneOffset - saving);
                    internalSet(16, saving);
                    return;
                default:
                    min2 = min4;
                    min = min2;
                    break;
            }
            set(field, getRolledValue(internalGet(field), amount2, min, max));
        }
    }

    @Override // java.util.Calendar
    public int getMinimum(int field) {
        return MIN_VALUES[field];
    }

    @Override // java.util.Calendar
    public int getMaximum(int field) {
        switch (field) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                if (this.gregorianCutoverYear <= 200) {
                    GregorianCalendar gc = (GregorianCalendar) clone();
                    gc.setLenient(true);
                    gc.setTimeInMillis(this.gregorianCutover);
                    int v1 = gc.getActualMaximum(field);
                    gc.setTimeInMillis(this.gregorianCutover - 1);
                    return Math.max(MAX_VALUES[field], Math.max(v1, gc.getActualMaximum(field)));
                }
                break;
        }
        return MAX_VALUES[field];
    }

    @Override // java.util.Calendar
    public int getGreatestMinimum(int field) {
        if (field != 5) {
            return MIN_VALUES[field];
        }
        return Math.max(MIN_VALUES[field], getCalendarDate(getFixedDateMonth1(getGregorianCutoverDate(), this.gregorianCutoverDate)).getDayOfMonth());
    }

    @Override // java.util.Calendar
    public int getLeastMaximum(int field) {
        switch (field) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                GregorianCalendar gc = (GregorianCalendar) clone();
                gc.setLenient(true);
                gc.setTimeInMillis(this.gregorianCutover);
                int v1 = gc.getActualMaximum(field);
                gc.setTimeInMillis(this.gregorianCutover - 1);
                return Math.min(LEAST_MAX_VALUES[field], Math.min(v1, gc.getActualMaximum(field)));
            case 7:
            default:
                return LEAST_MAX_VALUES[field];
        }
    }

    @Override // java.util.Calendar
    public int getActualMinimum(int field) {
        if (field == 5) {
            GregorianCalendar gc = getNormalizedCalendar();
            int year = gc.cdate.getNormalizedYear();
            if (year == this.gregorianCutoverYear || year == this.gregorianCutoverYearJulian) {
                BaseCalendar.Date date = gc.cdate;
                return getCalendarDate(getFixedDateMonth1(date, gc.calsys.getFixedDate(date))).getDayOfMonth();
            }
        }
        return getMinimum(field);
    }

    @Override // java.util.Calendar
    public int getActualMaximum(int field) {
        GregorianCalendar gc;
        int value;
        int value2;
        long jan1;
        int ndays;
        int dow1;
        int i = 1;
        if (((1 << field) & 130689) != 0) {
            return getMaximum(field);
        }
        GregorianCalendar gc2 = getNormalizedCalendar();
        BaseCalendar.Date date = gc2.cdate;
        BaseCalendar cal = gc2.calsys;
        int normalizedYear = date.getNormalizedYear();
        switch (field) {
            case 1:
                if (gc2 == this) {
                    gc = (GregorianCalendar) clone();
                } else {
                    gc = gc2;
                }
                long current = gc.getYearOffsetInMillis();
                if (gc.internalGetEra() == 1) {
                    gc.setTimeInMillis(Long.MAX_VALUE);
                    int value3 = gc.get(1);
                    if (current > gc.getYearOffsetInMillis()) {
                        value3--;
                    }
                    return value3;
                }
                CalendarDate d = (gc.getTimeInMillis() >= this.gregorianCutover ? gcal : getJulianCalendarSystem()).getCalendarDate(Long.MIN_VALUE, getZone());
                long maxEnd = ((((((((cal.getDayOfYear(d) - 1) * 24) + ((long) d.getHours())) * 60) + ((long) d.getMinutes())) * 60) + ((long) d.getSeconds())) * 1000) + ((long) d.getMillis());
                int value4 = d.getYear();
                if (value4 <= 0) {
                    value4 = 1 - value4;
                }
                if (current < maxEnd) {
                    return value4 - 1;
                }
                return value4;
            case 2:
                if (!gc2.isCutoverYear(normalizedYear)) {
                    return 11;
                }
                while (true) {
                    normalizedYear += i;
                    long nextJan1 = gcal.getFixedDate(normalizedYear, i, i, null);
                    if (nextJan1 >= this.gregorianCutoverDate) {
                        BaseCalendar.Date d2 = (BaseCalendar.Date) date.clone();
                        cal.getCalendarDateFromFixedDate(d2, nextJan1 - 1);
                        return d2.getMonth() - 1;
                    }
                    gc2 = gc2;
                    i = 1;
                }
            case 3:
                if (!gc2.isCutoverYear(normalizedYear)) {
                    CalendarDate d3 = cal.newCalendarDate(TimeZone.NO_TIMEZONE);
                    d3.setDate(date.getYear(), 1, 1);
                    int dayOfWeek = cal.getDayOfWeek(d3) - getFirstDayOfWeek();
                    if (dayOfWeek < 0) {
                        dayOfWeek += 7;
                    }
                    int magic = (getMinimalDaysInFirstWeek() + dayOfWeek) - 1;
                    if (magic == 6 || (date.isLeapYear() && (magic == 5 || magic == 12))) {
                        return 52 + 1;
                    }
                    return 52;
                }
                if (gc2 == this) {
                    gc2 = (GregorianCalendar) gc2.clone();
                }
                int maxDayOfYear = getActualMaximum(6);
                gc2.set(6, maxDayOfYear);
                int value5 = gc2.get(3);
                if (internalGet(1) != gc2.getWeekYear()) {
                    gc2.set(6, maxDayOfYear - 7);
                    value = gc2.get(3);
                } else {
                    value = value5;
                }
                return value;
            case 4:
                if (!gc2.isCutoverYear(normalizedYear)) {
                    CalendarDate d4 = cal.newCalendarDate(null);
                    d4.setDate(date.getYear(), date.getMonth(), 1);
                    int dayOfWeek2 = cal.getDayOfWeek(d4);
                    int monthLength = cal.getMonthLength(d4);
                    int dayOfWeek3 = dayOfWeek2 - getFirstDayOfWeek();
                    if (dayOfWeek3 < 0) {
                        dayOfWeek3 += 7;
                    }
                    int nDaysFirstWeek = 7 - dayOfWeek3;
                    if (nDaysFirstWeek >= getMinimalDaysInFirstWeek()) {
                        value2 = 3 + 1;
                    } else {
                        value2 = 3;
                    }
                    int monthLength2 = monthLength - (nDaysFirstWeek + 21);
                    if (monthLength2 <= 0) {
                        return value2;
                    }
                    int value6 = value2 + 1;
                    if (monthLength2 > 7) {
                        return value6 + 1;
                    }
                    return value6;
                }
                if (gc2 == this) {
                    gc2 = (GregorianCalendar) gc2.clone();
                }
                int y = gc2.internalGet(1);
                int m = gc2.internalGet(2);
                do {
                    int value7 = gc2.get(4);
                    gc2.add(4, 1);
                    if (gc2.get(1) == y) {
                    }
                    return value7;
                } while (gc2.get(2) == m);
                return value7;
            case 5:
                int value8 = cal.getMonthLength(date);
                if (gc2.isCutoverYear(normalizedYear) && date.getDayOfMonth() != value8) {
                    long fd = gc2.getCurrentFixedDate();
                    if (fd < this.gregorianCutoverDate) {
                        return gc2.getCalendarDate((gc2.getFixedDateMonth1(gc2.cdate, fd) + ((long) gc2.actualMonthLength())) - 1).getDayOfMonth();
                    }
                }
                return value8;
            case 6:
                if (!gc2.isCutoverYear(normalizedYear)) {
                    return cal.getYearLength(date);
                }
                int i2 = this.gregorianCutoverYear;
                int i3 = this.gregorianCutoverYearJulian;
                if (i2 == i3) {
                    jan1 = gc2.getCutoverCalendarSystem().getFixedDate(normalizedYear, 1, 1, null);
                } else if (normalizedYear == i3) {
                    jan1 = cal.getFixedDate(normalizedYear, 1, 1, null);
                } else {
                    jan1 = this.gregorianCutoverDate;
                }
                long nextJan12 = gcal.getFixedDate(normalizedYear + 1, 1, 1, null);
                if (nextJan12 < this.gregorianCutoverDate) {
                    nextJan12 = this.gregorianCutoverDate;
                }
                return (int) (nextJan12 - jan1);
            case 7:
            default:
                throw new ArrayIndexOutOfBoundsException(field);
            case 8:
                int dow = date.getDayOfWeek();
                if (!gc2.isCutoverYear(normalizedYear)) {
                    BaseCalendar.Date d5 = (BaseCalendar.Date) date.clone();
                    ndays = cal.getMonthLength(d5);
                    d5.setDayOfMonth(1);
                    cal.normalize(d5);
                    dow1 = d5.getDayOfWeek();
                } else {
                    if (gc2 == this) {
                        gc2 = (GregorianCalendar) clone();
                    }
                    ndays = gc2.actualMonthLength();
                    gc2.set(5, gc2.getActualMinimum(5));
                    dow1 = gc2.get(7);
                }
                int x = dow - dow1;
                if (x < 0) {
                    x += 7;
                }
                return ((ndays - x) + 6) / 7;
        }
    }

    private long getYearOffsetInMillis() {
        return (((long) internalGet(14)) + ((((((((long) ((internalGet(6) - 1) * 24)) + ((long) internalGet(11))) * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000)) - ((long) (internalGet(15) + internalGet(16)));
    }

    @Override // java.util.Calendar
    public Object clone() {
        GregorianCalendar other = (GregorianCalendar) super.clone();
        other.gdate = (BaseCalendar.Date) this.gdate.clone();
        BaseCalendar.Date date = this.cdate;
        if (date != null) {
            if (date != this.gdate) {
                other.cdate = (BaseCalendar.Date) date.clone();
            } else {
                other.cdate = other.gdate;
            }
        }
        other.originalFields = null;
        other.zoneOffsets = null;
        return other;
    }

    @Override // java.util.Calendar
    public TimeZone getTimeZone() {
        TimeZone zone = super.getTimeZone();
        this.gdate.setZone(zone);
        BaseCalendar.Date date = this.cdate;
        if (!(date == null || date == this.gdate)) {
            date.setZone(zone);
        }
        return zone;
    }

    @Override // java.util.Calendar
    public void setTimeZone(TimeZone zone) {
        super.setTimeZone(zone);
        this.gdate.setZone(zone);
        BaseCalendar.Date date = this.cdate;
        if (date != null && date != this.gdate) {
            date.setZone(zone);
        }
    }

    @Override // java.util.Calendar
    public final boolean isWeekDateSupported() {
        return true;
    }

    @Override // java.util.Calendar
    public int getWeekYear() {
        int minDayOfYear;
        int year = get(1);
        if (internalGetEra() == 0) {
            year = 1 - year;
        }
        if (year > this.gregorianCutoverYear + 1) {
            int weekOfYear = internalGet(3);
            if (internalGet(2) == 0) {
                if (weekOfYear >= 52) {
                    return year - 1;
                }
                return year;
            } else if (weekOfYear == 1) {
                return year + 1;
            } else {
                return year;
            }
        } else {
            int dayOfYear = internalGet(6);
            int maxDayOfYear = getActualMaximum(6);
            int minimalDays = getMinimalDaysInFirstWeek();
            if (dayOfYear > minimalDays && dayOfYear < maxDayOfYear - 6) {
                return year;
            }
            GregorianCalendar cal = (GregorianCalendar) clone();
            cal.setLenient(true);
            cal.setTimeZone(TimeZone.getTimeZone("GMT"));
            cal.set(6, 1);
            cal.complete();
            int delta = getFirstDayOfWeek() - cal.get(7);
            if (delta != 0) {
                if (delta < 0) {
                    delta += 7;
                }
                cal.add(6, delta);
            }
            int minDayOfYear2 = cal.get(6);
            if (dayOfYear >= minDayOfYear2) {
                cal.set(1, year + 1);
                cal.set(6, 1);
                cal.complete();
                int del = getFirstDayOfWeek() - cal.get(7);
                if (del != 0) {
                    if (del < 0) {
                        del += 7;
                    }
                    cal.add(6, del);
                }
                int minDayOfYear3 = cal.get(6) - 1;
                if (minDayOfYear3 == 0) {
                    minDayOfYear = 7;
                } else {
                    minDayOfYear = minDayOfYear3;
                }
                if (minDayOfYear < minimalDays || (maxDayOfYear - dayOfYear) + 1 > 7 - minDayOfYear) {
                    return year;
                }
                return year + 1;
            } else if (minDayOfYear2 <= minimalDays) {
                return year - 1;
            } else {
                return year;
            }
        }
    }

    @Override // java.util.Calendar
    public void setWeekDate(int weekYear, int weekOfYear, int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new IllegalArgumentException("invalid dayOfWeek: " + dayOfWeek);
        }
        GregorianCalendar gc = (GregorianCalendar) clone();
        gc.setLenient(true);
        int era = gc.get(0);
        gc.clear();
        gc.setTimeZone(TimeZone.getTimeZone("GMT"));
        gc.set(0, era);
        gc.set(1, weekYear);
        gc.set(3, 1);
        gc.set(7, getFirstDayOfWeek());
        int days = dayOfWeek - getFirstDayOfWeek();
        if (days < 0) {
            days += 7;
        }
        int days2 = days + ((weekOfYear - 1) * 7);
        if (days2 != 0) {
            gc.add(6, days2);
        } else {
            gc.complete();
        }
        if (isLenient() || (gc.getWeekYear() == weekYear && gc.internalGet(3) == weekOfYear && gc.internalGet(7) == dayOfWeek)) {
            set(0, gc.internalGet(0));
            set(1, gc.internalGet(1));
            set(2, gc.internalGet(2));
            set(5, gc.internalGet(5));
            internalSet(3, weekOfYear);
            complete();
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.Calendar
    public int getWeeksInWeekYear() {
        GregorianCalendar gc = getNormalizedCalendar();
        int weekYear = gc.getWeekYear();
        if (weekYear == gc.internalGet(1)) {
            return gc.getActualMaximum(3);
        }
        if (gc == this) {
            gc = (GregorianCalendar) gc.clone();
        }
        gc.setWeekDate(weekYear, 2, internalGet(7));
        return gc.getActualMaximum(3);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Calendar
    public void computeFields() {
        int mask;
        if (isPartiallyNormalized()) {
            mask = getSetStateFields();
            int fieldMask = (~mask) & 131071;
            if (fieldMask != 0 || this.calsys == null) {
                mask |= computeFields(fieldMask, 98304 & mask);
            }
        } else {
            mask = 131071;
            computeFields(131071, 0);
        }
        setFieldsComputed(mask);
    }

    private int computeFields(int fieldMask, int tzMask) {
        int timeOfDay;
        int year;
        int year2;
        int relativeDayOfMonth;
        long fixedDateJan1;
        long nextJan1;
        int i;
        long prevJan1;
        int zoneOffset = 0;
        TimeZone tz = getZone();
        if (this.zoneOffsets == null) {
            this.zoneOffsets = new int[2];
        }
        if (tzMask != 98304) {
            if (tz instanceof ZoneInfo) {
                zoneOffset = ((ZoneInfo) tz).getOffsetsByUtcTime(this.time, this.zoneOffsets);
            } else {
                zoneOffset = tz.getOffset(this.time);
                this.zoneOffsets[0] = tz.getRawOffset();
                int[] iArr = this.zoneOffsets;
                iArr[1] = zoneOffset - iArr[0];
            }
        }
        if (tzMask != 0) {
            if (isFieldSet(tzMask, 15)) {
                this.zoneOffsets[0] = internalGet(15);
            }
            if (isFieldSet(tzMask, 16)) {
                this.zoneOffsets[1] = internalGet(16);
            }
            int[] iArr2 = this.zoneOffsets;
            zoneOffset = iArr2[0] + iArr2[1];
        }
        int timeOfDay2 = zoneOffset % Grego.MILLIS_PER_DAY;
        long fixedDate = (((long) zoneOffset) / 86400000) + (this.time / 86400000);
        int timeOfDay3 = timeOfDay2 + ((int) (this.time % 86400000));
        if (((long) timeOfDay3) >= 86400000) {
            timeOfDay = (int) (((long) timeOfDay3) - 86400000);
            fixedDate++;
        } else {
            timeOfDay = timeOfDay3;
            while (timeOfDay < 0) {
                timeOfDay = (int) (((long) timeOfDay) + 86400000);
                fixedDate--;
            }
        }
        long fixedDate2 = fixedDate + 719163;
        int era = 1;
        if (fixedDate2 >= this.gregorianCutoverDate) {
            if (fixedDate2 != this.cachedFixedDate) {
                gcal.getCalendarDateFromFixedDate(this.gdate, fixedDate2);
                this.cachedFixedDate = fixedDate2;
            }
            year = this.gdate.getYear();
            if (year <= 0) {
                year = 1 - year;
                era = 0;
            }
            this.calsys = gcal;
            this.cdate = this.gdate;
        } else {
            this.calsys = getJulianCalendarSystem();
            this.cdate = jcal.newCalendarDate(getZone());
            jcal.getCalendarDateFromFixedDate(this.cdate, fixedDate2);
            if (this.cdate.getEra() == jeras[0]) {
                era = 0;
            }
            year = this.cdate.getYear();
        }
        internalSet(0, era);
        internalSet(1, year);
        int mask = fieldMask | 3;
        int month = this.cdate.getMonth() - 1;
        int dayOfMonth = this.cdate.getDayOfMonth();
        if ((fieldMask & 164) != 0) {
            internalSet(2, month);
            internalSet(5, dayOfMonth);
            internalSet(7, this.cdate.getDayOfWeek());
            mask |= 164;
        }
        if ((fieldMask & 32256) != 0) {
            if (timeOfDay != 0) {
                int hours = timeOfDay / 3600000;
                internalSet(11, hours);
                internalSet(9, hours / 12);
                internalSet(10, hours % 12);
                int r = timeOfDay % 3600000;
                internalSet(12, r / 60000);
                int r2 = r % 60000;
                internalSet(13, r2 / 1000);
                internalSet(14, r2 % 1000);
            } else {
                internalSet(11, 0);
                internalSet(9, 0);
                internalSet(10, 0);
                internalSet(12, 0);
                internalSet(13, 0);
                internalSet(14, 0);
            }
            mask |= 32256;
        }
        if ((fieldMask & 98304) != 0) {
            internalSet(15, this.zoneOffsets[0]);
            internalSet(16, this.zoneOffsets[1]);
            mask |= 98304;
        }
        if ((fieldMask & 344) == 0) {
            return mask;
        }
        int normalizedYear = this.cdate.getNormalizedYear();
        long fixedDateJan12 = this.calsys.getFixedDate(normalizedYear, 1, 1, this.cdate);
        int dayOfYear = ((int) (fixedDate2 - fixedDateJan12)) + 1;
        long fixedDateMonth1 = (fixedDate2 - ((long) dayOfMonth)) + 1;
        int cutoverYear = this.calsys == gcal ? this.gregorianCutoverYear : this.gregorianCutoverYearJulian;
        int relativeDayOfMonth2 = dayOfMonth - 1;
        if (normalizedYear == cutoverYear) {
            if (this.gregorianCutoverYearJulian <= this.gregorianCutoverYear) {
                long fixedDateJan13 = getFixedDateJan1(this.cdate, fixedDate2);
                if (fixedDate2 >= this.gregorianCutoverDate) {
                    fixedDateJan1 = getFixedDateMonth1(this.cdate, fixedDate2);
                    fixedDateJan12 = fixedDateJan13;
                } else {
                    fixedDateJan12 = fixedDateJan13;
                    fixedDateJan1 = fixedDateMonth1;
                }
            } else {
                fixedDateJan1 = fixedDateMonth1;
            }
            int realDayOfYear = ((int) (fixedDate2 - fixedDateJan12)) + 1;
            int cutoverGap = dayOfYear - realDayOfYear;
            dayOfYear = realDayOfYear;
            year2 = year;
            relativeDayOfMonth = (int) (fixedDate2 - fixedDateJan1);
            fixedDateJan12 = fixedDateJan12;
        } else {
            year2 = year;
            fixedDateJan1 = fixedDateMonth1;
            relativeDayOfMonth = relativeDayOfMonth2;
        }
        internalSet(6, dayOfYear);
        internalSet(8, (relativeDayOfMonth / 7) + 1);
        int weekOfYear = getWeekNumber(fixedDateJan12, fixedDate2);
        if (weekOfYear == 0) {
            long fixedDec31 = fixedDateJan12 - 1;
            long prevJan12 = fixedDateJan12 - 365;
            if (normalizedYear > cutoverYear + 1) {
                if (CalendarUtils.isGregorianLeapYear(normalizedYear - 1)) {
                    prevJan1 = prevJan12 - 1;
                    weekOfYear = getWeekNumber(prevJan1, fixedDec31);
                }
            } else if (normalizedYear > this.gregorianCutoverYearJulian) {
                BaseCalendar baseCalendar = this.calsys;
                int prevYear = getCalendarDate(fixedDec31).getNormalizedYear();
                if (prevYear == this.gregorianCutoverYear) {
                    BaseCalendar calForJan1 = getCutoverCalendarSystem();
                    if (calForJan1 == jcal) {
                        prevJan1 = calForJan1.getFixedDate(prevYear, 1, 1, null);
                    } else {
                        prevJan1 = this.gregorianCutoverDate;
                        BaseCalendar calForJan12 = gcal;
                    }
                } else if (prevYear <= this.gregorianCutoverYearJulian) {
                    prevJan1 = getJulianCalendarSystem().getFixedDate(prevYear, 1, 1, null);
                }
                weekOfYear = getWeekNumber(prevJan1, fixedDec31);
            } else if (CalendarUtils.isJulianLeapYear(normalizedYear - 1)) {
                prevJan1 = prevJan12 - 1;
                weekOfYear = getWeekNumber(prevJan1, fixedDec31);
            }
            prevJan1 = prevJan12;
            weekOfYear = getWeekNumber(prevJan1, fixedDec31);
        } else {
            int cutoverYear2 = this.gregorianCutoverYear;
            if (normalizedYear <= cutoverYear2) {
                int i2 = this.gregorianCutoverYearJulian;
                if (normalizedYear >= i2 - 1) {
                    BaseCalendar calForJan13 = this.calsys;
                    int nextYear = normalizedYear + 1;
                    if (nextYear == i2 + 1 && nextYear < cutoverYear2) {
                        nextYear = this.gregorianCutoverYear;
                    }
                    if (nextYear == this.gregorianCutoverYear) {
                        calForJan13 = getCutoverCalendarSystem();
                    }
                    int i3 = this.gregorianCutoverYear;
                    if (nextYear > i3 || (i = this.gregorianCutoverYearJulian) == i3 || nextYear == i) {
                        nextJan1 = calForJan13.getFixedDate(nextYear, 1, 1, null);
                    } else {
                        nextJan1 = this.gregorianCutoverDate;
                        calForJan13 = gcal;
                    }
                    long nextJan1st = BaseCalendar.getDayOfWeekDateOnOrBefore(nextJan1 + 6, getFirstDayOfWeek());
                    if (((int) (nextJan1st - nextJan1)) >= getMinimalDaysInFirstWeek() && fixedDate2 >= nextJan1st - 7) {
                        weekOfYear = 1;
                    }
                }
            }
            if (weekOfYear >= 52) {
                long nextJan12 = 365 + fixedDateJan12;
                if (this.cdate.isLeapYear()) {
                    nextJan12++;
                }
                long nextJan1st2 = BaseCalendar.getDayOfWeekDateOnOrBefore(6 + nextJan12, getFirstDayOfWeek());
                if (((int) (nextJan1st2 - nextJan12)) >= getMinimalDaysInFirstWeek() && fixedDate2 >= nextJan1st2 - 7) {
                    weekOfYear = 1;
                }
            }
        }
        internalSet(3, weekOfYear);
        internalSet(4, getWeekNumber(fixedDateJan1, fixedDate2));
        return mask | 344;
    }

    private int getWeekNumber(long fixedDay1, long fixedDate) {
        long fixedDay1st = Gregorian.getDayOfWeekDateOnOrBefore(6 + fixedDay1, getFirstDayOfWeek());
        if (((int) (fixedDay1st - fixedDay1)) >= getMinimalDaysInFirstWeek()) {
            fixedDay1st -= 7;
        }
        int normalizedDayOfPeriod = (int) (fixedDate - fixedDay1st);
        if (normalizedDayOfPeriod >= 0) {
            return (normalizedDayOfPeriod / 7) + 1;
        }
        return CalendarUtils.floorDivide(normalizedDayOfPeriod, 7) + 1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01e0  */
    @Override // java.util.Calendar
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void computeTime() {
        /*
        // Method dump skipped, instructions count: 506
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.GregorianCalendar.computeTime():void");
    }

    private long adjustForZoneAndDaylightSavingsTime(int tzMask, long utcTimeInMillis, TimeZone zone) {
        int zoneOffset = 0;
        int dstOffset = 0;
        if (tzMask != 98304) {
            if (this.zoneOffsets == null) {
                this.zoneOffsets = new int[2];
            }
            long standardTimeInZone = utcTimeInMillis - ((long) (isFieldSet(tzMask, 15) ? internalGet(15) : zone.getRawOffset()));
            if (zone instanceof ZoneInfo) {
                ((ZoneInfo) zone).getOffsetsByUtcTime(standardTimeInZone, this.zoneOffsets);
            } else {
                zone.getOffsets(standardTimeInZone, this.zoneOffsets);
            }
            int[] iArr = this.zoneOffsets;
            zoneOffset = iArr[0];
            dstOffset = adjustDstOffsetForInvalidWallClock(standardTimeInZone, zone, iArr[1]);
        }
        if (tzMask != 0) {
            if (isFieldSet(tzMask, 15)) {
                zoneOffset = internalGet(15);
            }
            if (isFieldSet(tzMask, 16)) {
                dstOffset = internalGet(16);
            }
        }
        return (utcTimeInMillis - ((long) zoneOffset)) - ((long) dstOffset);
    }

    private int adjustDstOffsetForInvalidWallClock(long standardTimeInZone, TimeZone zone, int dstOffset) {
        if (dstOffset == 0 || zone.inDaylightTime(new Date(standardTimeInZone - ((long) dstOffset)))) {
            return dstOffset;
        }
        return 0;
    }

    private long getFixedDate(BaseCalendar cal, int year, int fieldMask) {
        int year2;
        int dayOfWeek;
        int dayOfWeek2;
        int dowim;
        int month = 0;
        if (isFieldSet(fieldMask, 2)) {
            month = internalGet(2);
            if (month > 11) {
                year2 = year + (month / 12);
                month %= 12;
            } else if (month < 0) {
                int[] rem = new int[1];
                month = rem[0];
                year2 = year + CalendarUtils.floorDivide(month, 12, rem);
            } else {
                year2 = year;
            }
        } else {
            year2 = year;
        }
        long fixedDate = cal.getFixedDate(year2, month + 1, 1, cal == gcal ? this.gdate : null);
        if (!isFieldSet(fieldMask, 2)) {
            int i = this.gregorianCutoverYear;
            if (year2 == i && cal == gcal && fixedDate < this.gregorianCutoverDate && i != this.gregorianCutoverYearJulian) {
                fixedDate = this.gregorianCutoverDate;
            }
            if (isFieldSet(fieldMask, 6)) {
                return (fixedDate + ((long) internalGet(6))) - 1;
            }
            long firstDayOfWeek = BaseCalendar.getDayOfWeekDateOnOrBefore(fixedDate + 6, getFirstDayOfWeek());
            if (firstDayOfWeek - fixedDate >= ((long) getMinimalDaysInFirstWeek())) {
                firstDayOfWeek -= 7;
            }
            if (isFieldSet(fieldMask, 7) && (dayOfWeek = internalGet(7)) != getFirstDayOfWeek()) {
                firstDayOfWeek = BaseCalendar.getDayOfWeekDateOnOrBefore(6 + firstDayOfWeek, dayOfWeek);
            }
            return firstDayOfWeek + ((((long) internalGet(3)) - 1) * 7);
        } else if (isFieldSet(fieldMask, 5)) {
            if (isSet(5)) {
                return (fixedDate + ((long) internalGet(5))) - 1;
            }
            return fixedDate;
        } else if (isFieldSet(fieldMask, 4)) {
            long firstDayOfWeek2 = BaseCalendar.getDayOfWeekDateOnOrBefore(fixedDate + 6, getFirstDayOfWeek());
            if (firstDayOfWeek2 - fixedDate >= ((long) getMinimalDaysInFirstWeek())) {
                firstDayOfWeek2 -= 7;
            }
            if (isFieldSet(fieldMask, 7)) {
                firstDayOfWeek2 = BaseCalendar.getDayOfWeekDateOnOrBefore(6 + firstDayOfWeek2, internalGet(7));
            }
            return firstDayOfWeek2 + ((long) ((internalGet(4) - 1) * 7));
        } else {
            if (isFieldSet(fieldMask, 7)) {
                dayOfWeek2 = internalGet(7);
            } else {
                dayOfWeek2 = getFirstDayOfWeek();
            }
            if (isFieldSet(fieldMask, 8)) {
                dowim = internalGet(8);
            } else {
                dowim = 1;
            }
            if (dowim >= 0) {
                return BaseCalendar.getDayOfWeekDateOnOrBefore((((long) (dowim * 7)) + fixedDate) - 1, dayOfWeek2);
            }
            return BaseCalendar.getDayOfWeekDateOnOrBefore((((long) (monthLength(month, year2) + ((dowim + 1) * 7))) + fixedDate) - 1, dayOfWeek2);
        }
    }

    private GregorianCalendar getNormalizedCalendar() {
        if (isFullyNormalized()) {
            return this;
        }
        GregorianCalendar gc = (GregorianCalendar) clone();
        gc.setLenient(true);
        gc.complete();
        return gc;
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

    private boolean isCutoverYear(int normalizedYear) {
        return normalizedYear == (this.calsys == gcal ? this.gregorianCutoverYear : this.gregorianCutoverYearJulian);
    }

    private long getFixedDateJan1(BaseCalendar.Date date, long fixedDate) {
        if (this.gregorianCutoverYear != this.gregorianCutoverYearJulian) {
            long j = this.gregorianCutoverDate;
            if (fixedDate >= j) {
                return j;
            }
        }
        return getJulianCalendarSystem().getFixedDate(date.getNormalizedYear(), 1, 1, null);
    }

    private long getFixedDateMonth1(BaseCalendar.Date date, long fixedDate) {
        BaseCalendar.Date gCutover = getGregorianCutoverDate();
        if (gCutover.getMonth() == 1 && gCutover.getDayOfMonth() == 1) {
            return (fixedDate - ((long) date.getDayOfMonth())) + 1;
        }
        if (date.getMonth() != gCutover.getMonth()) {
            return 1 + (fixedDate - ((long) date.getDayOfMonth()));
        }
        BaseCalendar.Date jLastDate = getLastJulianDate();
        if (this.gregorianCutoverYear == this.gregorianCutoverYearJulian && gCutover.getMonth() == jLastDate.getMonth()) {
            return jcal.getFixedDate(date.getNormalizedYear(), date.getMonth(), 1, null);
        }
        return this.gregorianCutoverDate;
    }

    private BaseCalendar.Date getCalendarDate(long fd) {
        BaseCalendar cal = fd >= this.gregorianCutoverDate ? gcal : getJulianCalendarSystem();
        BaseCalendar.Date d = (BaseCalendar.Date) cal.newCalendarDate(TimeZone.NO_TIMEZONE);
        cal.getCalendarDateFromFixedDate(d, fd);
        return d;
    }

    private BaseCalendar.Date getGregorianCutoverDate() {
        return getCalendarDate(this.gregorianCutoverDate);
    }

    private BaseCalendar.Date getLastJulianDate() {
        return getCalendarDate(this.gregorianCutoverDate - 1);
    }

    private int monthLength(int month, int year) {
        return isLeapYear(year) ? LEAP_MONTH_LENGTH[month] : MONTH_LENGTH[month];
    }

    private int monthLength(int month) {
        int year = internalGet(1);
        if (internalGetEra() == 0) {
            year = 1 - year;
        }
        return monthLength(month, year);
    }

    private int actualMonthLength() {
        int year = this.cdate.getNormalizedYear();
        if (year != this.gregorianCutoverYear && year != this.gregorianCutoverYearJulian) {
            return this.calsys.getMonthLength(this.cdate);
        }
        BaseCalendar.Date date = (BaseCalendar.Date) this.cdate.clone();
        long month1 = getFixedDateMonth1(date, this.calsys.getFixedDate(date));
        long next1 = ((long) this.calsys.getMonthLength(date)) + month1;
        if (next1 < this.gregorianCutoverDate) {
            return (int) (next1 - month1);
        }
        if (this.cdate != this.gdate) {
            date = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        }
        gcal.getCalendarDateFromFixedDate(date, next1);
        return (int) (getFixedDateMonth1(date, next1) - month1);
    }

    private int yearLength(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    private int yearLength() {
        int year = internalGet(1);
        if (internalGetEra() == 0) {
            year = 1 - year;
        }
        return yearLength(year);
    }

    private void pinDayOfMonth() {
        int monthLen;
        int year = internalGet(1);
        if (year > this.gregorianCutoverYear || year < this.gregorianCutoverYearJulian) {
            monthLen = monthLength(internalGet(2));
        } else {
            monthLen = getNormalizedCalendar().getActualMaximum(5);
        }
        if (internalGet(5) > monthLen) {
            set(5, monthLen);
        }
    }

    private long getCurrentFixedDate() {
        BaseCalendar baseCalendar = this.calsys;
        return baseCalendar == gcal ? this.cachedFixedDate : baseCalendar.getFixedDate(this.cdate);
    }

    private static int getRolledValue(int value, int amount, int min, int max) {
        int range = (max - min) + 1;
        int n = value + (amount % range);
        if (n > max) {
            return n - range;
        }
        if (n < min) {
            return n + range;
        }
        return n;
    }

    private int internalGetEra() {
        if (isSet(0)) {
            return internalGet(0);
        }
        return 1;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (this.gdate == null) {
            this.gdate = gcal.newCalendarDate(getZone());
            this.cachedFixedDate = Long.MIN_VALUE;
        }
        setGregorianChange(this.gregorianCutover);
    }

    public ZonedDateTime toZonedDateTime() {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(getTimeInMillis()), getTimeZone().toZoneId());
    }

    public static GregorianCalendar from(ZonedDateTime zdt) {
        GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone(zdt.getZone()));
        cal.setGregorianChange(new Date(Long.MIN_VALUE));
        cal.setFirstDayOfWeek(2);
        cal.setMinimalDaysInFirstWeek(4);
        try {
            cal.setTimeInMillis(Math.addExact(Math.multiplyExact(zdt.toEpochSecond(), 1000), (long) zdt.get(ChronoField.MILLI_OF_SECOND)));
            return cal;
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
