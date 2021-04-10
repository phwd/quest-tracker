package java.util;

import android.icu.impl.Grego;
import android.icu.impl.number.RoundingUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import libcore.icu.RelativeDateTimeFormatter;
import libcore.util.ZoneInfo;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Era;
import sun.util.calendar.Gregorian;
import sun.util.calendar.LocalGregorianCalendar;
import sun.util.locale.provider.CalendarDataUtility;

/* access modifiers changed from: package-private */
public class JapaneseImperialCalendar extends Calendar {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BEFORE_MEIJI = 0;
    private static final Era BEFORE_MEIJI_ERA = new Era("BeforeMeiji", "BM", Long.MIN_VALUE, false);
    private static final int EPOCH_OFFSET = 719163;
    private static final int EPOCH_YEAR = 1970;
    public static final int HEISEI = 4;
    static final int[] LEAST_MAX_VALUES = {0, 0, 0, 0, 4, 28, 0, 7, 4, 1, 11, 23, 59, 59, RoundingUtils.MAX_INT_FRAC_SIG, 50400000, 1200000};
    static final int[] MAX_VALUES = {0, 292278994, 11, 53, 6, 31, 366, 7, 6, 1, 11, 23, 59, 59, RoundingUtils.MAX_INT_FRAC_SIG, 50400000, 7200000};
    public static final int MEIJI = 1;
    static final int[] MIN_VALUES = {0, -292275055, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, -46800000, 0};
    private static final long ONE_DAY = 86400000;
    private static final int ONE_HOUR = 3600000;
    private static final int ONE_MINUTE = 60000;
    private static final int ONE_SECOND = 1000;
    private static final long ONE_WEEK = 604800000;
    private static final int REIWA = 5;
    public static final int SHOWA = 3;
    public static final int TAISHO = 2;
    private static final int currentEra = 5;
    private static final Era[] eras;
    private static final Gregorian gcal = CalendarSystem.getGregorianCalendar();
    private static final LocalGregorianCalendar jcal = ((LocalGregorianCalendar) CalendarSystem.forName("japanese"));
    private static final long serialVersionUID = -3364572813905467929L;
    private static final long[] sinceFixedDates;
    private transient long cachedFixedDate = Long.MIN_VALUE;
    private transient LocalGregorianCalendar.Date jdate;
    private transient int[] originalFields;
    private transient int[] zoneOffsets;

    static {
        Era[] es = jcal.getEras();
        int length = es.length + 1;
        eras = new Era[length];
        sinceFixedDates = new long[length];
        sinceFixedDates[0] = gcal.getFixedDate(BEFORE_MEIJI_ERA.getSinceDate());
        eras[0] = BEFORE_MEIJI_ERA;
        int length2 = es.length;
        int index = 0 + 1;
        int index2 = 0;
        while (index2 < length2) {
            Era e = es[index2];
            sinceFixedDates[index] = gcal.getFixedDate(e.getSinceDate());
            eras[index] = e;
            index2++;
            index++;
        }
        int[] iArr = LEAST_MAX_VALUES;
        int[] iArr2 = MAX_VALUES;
        int length3 = eras.length - 1;
        iArr2[0] = length3;
        iArr[0] = length3;
        int year = Integer.MAX_VALUE;
        int dayOfYear = Integer.MAX_VALUE;
        CalendarDate date = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        int i = 1;
        while (true) {
            Era[] eraArr = eras;
            if (i < eraArr.length) {
                long fd = sinceFixedDates[i];
                CalendarDate transitionDate = eraArr[i].getSinceDate();
                date.setDate(transitionDate.getYear(), 1, 1);
                long fdd = gcal.getFixedDate(date);
                if (fd != fdd) {
                    dayOfYear = Math.min(((int) (fd - fdd)) + 1, dayOfYear);
                }
                date.setDate(transitionDate.getYear(), 12, 31);
                long fdd2 = gcal.getFixedDate(date);
                if (fd != fdd2) {
                    dayOfYear = Math.min(((int) (fdd2 - fd)) + 1, dayOfYear);
                }
                LocalGregorianCalendar.Date lgd = getCalendarDate(fd - 1);
                int y = lgd.getYear();
                if (lgd.getMonth() != 1 || lgd.getDayOfMonth() != 1) {
                    y--;
                }
                year = Math.min(y, year);
                i++;
            } else {
                int[] iArr3 = LEAST_MAX_VALUES;
                iArr3[1] = year;
                iArr3[6] = dayOfYear;
                return;
            }
        }
    }

    JapaneseImperialCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
        this.jdate = jcal.newCalendarDate(zone);
        setTimeInMillis(System.currentTimeMillis());
    }

    JapaneseImperialCalendar(TimeZone zone, Locale aLocale, boolean flag) {
        super(zone, aLocale);
        this.jdate = jcal.newCalendarDate(zone);
    }

    @Override // java.util.Calendar
    public String getCalendarType() {
        return "japanese";
    }

    @Override // java.util.Calendar
    public boolean equals(Object obj) {
        return (obj instanceof JapaneseImperialCalendar) && super.equals(obj);
    }

    @Override // java.util.Calendar
    public int hashCode() {
        return super.hashCode() ^ this.jdate.hashCode();
    }

    @Override // java.util.Calendar
    public void add(int field, int amount) {
        if (amount != 0) {
            if (field < 0 || field >= 15) {
                throw new IllegalArgumentException();
            }
            complete();
            if (field == 1) {
                LocalGregorianCalendar.Date d = (LocalGregorianCalendar.Date) this.jdate.clone();
                d.addYear(amount);
                pinDayOfMonth(d);
                set(0, getEraIndex(d));
                set(1, d.getYear());
                set(2, d.getMonth() - 1);
                set(5, d.getDayOfMonth());
            } else if (field == 2) {
                LocalGregorianCalendar.Date d2 = (LocalGregorianCalendar.Date) this.jdate.clone();
                d2.addMonth(amount);
                pinDayOfMonth(d2);
                set(0, getEraIndex(d2));
                set(1, d2.getYear());
                set(2, d2.getMonth() - 1);
                set(5, d2.getDayOfMonth());
            } else if (field == 0) {
                int era = internalGet(0) + amount;
                if (era < 0) {
                    era = 0;
                } else {
                    Era[] eraArr = eras;
                    if (era > eraArr.length - 1) {
                        era = eraArr.length - 1;
                    }
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
                long fd = this.cachedFixedDate;
                long timeOfDay2 = ((((((timeOfDay + ((long) internalGet(11))) * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
                if (timeOfDay2 >= 86400000) {
                    fd++;
                    timeOfDay2 -= 86400000;
                } else if (timeOfDay2 < 0) {
                    fd--;
                    timeOfDay2 += 86400000;
                }
                long fd2 = fd + delta;
                int zoneOffset = internalGet(15) + internalGet(16);
                setTimeInMillis((((fd2 - 719163) * 86400000) + timeOfDay2) - ((long) zoneOffset));
                int zoneOffset2 = zoneOffset - (internalGet(15) + internalGet(16));
                if (zoneOffset2 != 0) {
                    setTimeInMillis(this.time + ((long) zoneOffset2));
                    if (this.cachedFixedDate != fd2) {
                        setTimeInMillis(this.time - ((long) zoneOffset2));
                    }
                }
            }
        }
    }

    @Override // java.util.Calendar
    public void roll(int field, boolean up) {
        roll(field, up ? 1 : -1);
    }

    /* JADX INFO: Multiple debug info for r9v36 long: [D('year' int), D('fd' long)] */
    /* JADX INFO: Multiple debug info for r7v25 long: [D('lastDays' int), D('fd' long)] */
    /* JADX INFO: Multiple debug info for r7v29 int: [D('unit' int), D('min' int)] */
    @Override // java.util.Calendar
    public void roll(int field, int amount) {
        int min;
        int max;
        int max2;
        int dom;
        int max3;
        long month1;
        int max4;
        int weekOfYear;
        if (amount != 0) {
            if (field < 0 || field >= 15) {
                throw new IllegalArgumentException();
            }
            complete();
            int min2 = getMinimum(field);
            int max5 = getMaximum(field);
            switch (field) {
                case 0:
                case 9:
                case 12:
                case 13:
                case 14:
                    min = min2;
                    max2 = max5;
                    max = max2;
                    break;
                case 1:
                    int min3 = getActualMinimum(field);
                    max = getActualMaximum(field);
                    min = min3;
                    break;
                case 2:
                    int min4 = min2;
                    int max6 = max5;
                    if (!isTransitionYear(this.jdate.getNormalizedYear())) {
                        int year = this.jdate.getYear();
                        if (year == getMaximum(1)) {
                            CalendarDate jd = jcal.getCalendarDate(this.time, getZone());
                            CalendarDate d = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                            int max7 = d.getMonth() - 1;
                            int n = getRolledValue(internalGet(field), amount, min4, max7);
                            if (n == max7) {
                                jd.addYear(-400);
                                jd.setMonth(n + 1);
                                if (jd.getDayOfMonth() > d.getDayOfMonth()) {
                                    jd.setDayOfMonth(d.getDayOfMonth());
                                    jcal.normalize(jd);
                                }
                                if (jd.getDayOfMonth() == d.getDayOfMonth() && jd.getTimeOfDay() > d.getTimeOfDay()) {
                                    jd.setMonth(n + 1);
                                    jd.setDayOfMonth(d.getDayOfMonth() - 1);
                                    jcal.normalize(jd);
                                    n = jd.getMonth() - 1;
                                }
                                set(5, jd.getDayOfMonth());
                            }
                            set(2, n);
                            return;
                        } else if (year == getMinimum(1)) {
                            CalendarDate jd2 = jcal.getCalendarDate(this.time, getZone());
                            CalendarDate d2 = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
                            int min5 = d2.getMonth() - 1;
                            int n2 = getRolledValue(internalGet(field), amount, min5, max6);
                            if (n2 == min5) {
                                jd2.addYear(400);
                                jd2.setMonth(n2 + 1);
                                if (jd2.getDayOfMonth() < d2.getDayOfMonth()) {
                                    jd2.setDayOfMonth(d2.getDayOfMonth());
                                    jcal.normalize(jd2);
                                }
                                if (jd2.getDayOfMonth() == d2.getDayOfMonth() && jd2.getTimeOfDay() < d2.getTimeOfDay()) {
                                    jd2.setMonth(n2 + 1);
                                    jd2.setDayOfMonth(d2.getDayOfMonth() + 1);
                                    jcal.normalize(jd2);
                                    n2 = jd2.getMonth() - 1;
                                }
                                set(5, jd2.getDayOfMonth());
                            }
                            set(2, n2);
                            return;
                        } else {
                            int mon = (internalGet(2) + amount) % 12;
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
                    } else {
                        int eraIndex = getEraIndex(this.jdate);
                        CalendarDate transition = null;
                        if (this.jdate.getYear() == 1) {
                            transition = eras[eraIndex].getSinceDate();
                            min4 = transition.getMonth() - 1;
                        } else {
                            Era[] eraArr = eras;
                            if (eraIndex < eraArr.length - 1) {
                                transition = eraArr[eraIndex + 1].getSinceDate();
                                if (transition.getYear() == this.jdate.getNormalizedYear()) {
                                    int max8 = transition.getMonth() - 1;
                                    max6 = transition.getDayOfMonth() == 1 ? max8 - 1 : max8;
                                }
                            }
                        }
                        if (min4 != max6) {
                            int n3 = getRolledValue(internalGet(field), amount, min4, max6);
                            set(2, n3);
                            if (n3 == min4) {
                                if (!(transition.getMonth() == 1 && transition.getDayOfMonth() == 1) && this.jdate.getDayOfMonth() < transition.getDayOfMonth()) {
                                    set(5, transition.getDayOfMonth());
                                    return;
                                }
                                return;
                            } else if (n3 == max6 && transition.getMonth() - 1 == n3 && this.jdate.getDayOfMonth() >= (dom = transition.getDayOfMonth())) {
                                set(5, dom - 1);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                case 3:
                    min = min2;
                    int y = this.jdate.getNormalizedYear();
                    int max9 = getActualMaximum(3);
                    set(7, internalGet(7));
                    int woy = internalGet(3);
                    int value = woy + amount;
                    if (!isTransitionYear(this.jdate.getNormalizedYear())) {
                        int year2 = this.jdate.getYear();
                        if (year2 == getMaximum(1)) {
                            max9 = getActualMaximum(3);
                        } else if (year2 == getMinimum(1)) {
                            min = getActualMinimum(3);
                            max9 = getActualMaximum(3);
                            if (value > min && value < max9) {
                                set(3, value);
                                return;
                            }
                        }
                        if (value <= min || value >= max9) {
                            long fd = this.cachedFixedDate;
                            long day1 = fd - ((long) ((woy - min) * 7));
                            if (year2 == getMinimum(1)) {
                                max3 = max9;
                                if (day1 < jcal.getFixedDate(jcal.getCalendarDate(Long.MIN_VALUE, getZone()))) {
                                    min++;
                                }
                            } else if (gcal.getYearFromFixedDate(day1) != y) {
                                min++;
                                max3 = max9;
                            } else {
                                max3 = max9;
                            }
                            if (gcal.getYearFromFixedDate(fd + ((long) ((max3 - internalGet(3)) * 7))) == y) {
                                max = max3;
                                break;
                            } else {
                                max = max3 - 1;
                                break;
                            }
                        } else {
                            set(3, value);
                            return;
                        }
                    } else {
                        long fd2 = this.cachedFixedDate;
                        long day12 = fd2 - ((long) ((woy - min) * 7));
                        LocalGregorianCalendar.Date d3 = getCalendarDate(day12);
                        if (!(d3.getEra() == this.jdate.getEra() && d3.getYear() == this.jdate.getYear())) {
                            min++;
                        }
                        jcal.getCalendarDateFromFixedDate(d3, fd2 + ((long) ((max9 - woy) * 7)));
                        if (!(d3.getEra() == this.jdate.getEra() && d3.getYear() == this.jdate.getYear())) {
                            max9--;
                        }
                        LocalGregorianCalendar.Date d4 = getCalendarDate(((long) ((getRolledValue(woy, amount, min, max9) - 1) * 7)) + day12);
                        set(2, d4.getMonth() - 1);
                        set(5, d4.getDayOfMonth());
                        return;
                    }
                case 4:
                    boolean isTransitionYear = isTransitionYear(this.jdate.getNormalizedYear());
                    int dow = internalGet(7) - getFirstDayOfWeek();
                    if (dow < 0) {
                        dow += 7;
                    }
                    long fd3 = this.cachedFixedDate;
                    if (isTransitionYear) {
                        month1 = getFixedDateMonth1(this.jdate, fd3);
                        max4 = actualMonthLength();
                    } else {
                        max4 = jcal.getMonthLength(this.jdate);
                        month1 = (fd3 - ((long) internalGet(5))) + 1;
                    }
                    long monthDay1st = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(month1 + 6, getFirstDayOfWeek());
                    if (((int) (monthDay1st - month1)) >= getMinimalDaysInFirstWeek()) {
                        monthDay1st -= 7;
                    }
                    long nfd = ((long) ((getRolledValue(internalGet(field), amount, 1, getActualMaximum(field)) - 1) * 7)) + monthDay1st + ((long) dow);
                    if (nfd < month1) {
                        nfd = month1;
                    } else if (nfd >= ((long) max4) + month1) {
                        nfd = (((long) max4) + month1) - 1;
                    }
                    set(5, ((int) (nfd - month1)) + 1);
                    return;
                case 5:
                    min = min2;
                    if (!isTransitionYear(this.jdate.getNormalizedYear())) {
                        max = jcal.getMonthLength(this.jdate);
                        break;
                    } else {
                        long month12 = getFixedDateMonth1(this.jdate, this.cachedFixedDate);
                        set(5, getCalendarDate(((long) getRolledValue((int) (this.cachedFixedDate - month12), amount, 0, actualMonthLength() - 1)) + month12).getDayOfMonth());
                        return;
                    }
                case 6:
                    max = getActualMaximum(field);
                    if (!isTransitionYear(this.jdate.getNormalizedYear())) {
                        min = min2;
                        break;
                    } else {
                        LocalGregorianCalendar.Date d5 = getCalendarDate(((long) getRolledValue(internalGet(6), amount, min2, max)) + (this.cachedFixedDate - ((long) internalGet(6))));
                        set(2, d5.getMonth() - 1);
                        set(5, d5.getDayOfMonth());
                        return;
                    }
                case 7:
                    int normalizedYear = this.jdate.getNormalizedYear();
                    if (!isTransitionYear(normalizedYear) && !isTransitionYear(normalizedYear - 1) && (weekOfYear = internalGet(3)) > 1 && weekOfYear < 52) {
                        set(3, internalGet(3));
                        max = 7;
                        min = min2;
                        break;
                    } else {
                        int amount2 = amount % 7;
                        if (amount2 != 0) {
                            long fd4 = this.cachedFixedDate;
                            long dowFirst = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(fd4, getFirstDayOfWeek());
                            long fd5 = fd4 + ((long) amount2);
                            if (fd5 < dowFirst) {
                                fd5 += 7;
                            } else if (fd5 >= dowFirst + 7) {
                                fd5 -= 7;
                            }
                            LocalGregorianCalendar.Date d6 = getCalendarDate(fd5);
                            set(0, getEraIndex(d6));
                            set(d6.getYear(), d6.getMonth() - 1, d6.getDayOfMonth());
                            return;
                        }
                        return;
                    }
                    break;
                case 8:
                    if (!isTransitionYear(this.jdate.getNormalizedYear())) {
                        int dom2 = internalGet(5);
                        int monthLength = jcal.getMonthLength(this.jdate);
                        max = monthLength / 7;
                        if ((dom2 - 1) % 7 < monthLength % 7) {
                            max++;
                        }
                        set(7, internalGet(7));
                        min = 1;
                        break;
                    } else {
                        long fd6 = this.cachedFixedDate;
                        long month13 = getFixedDateMonth1(this.jdate, fd6);
                        int monthLength2 = actualMonthLength();
                        int max10 = monthLength2 / 7;
                        int x = ((int) (fd6 - month13)) % 7;
                        if (x < monthLength2 % 7) {
                            max10++;
                        }
                        set(5, getCalendarDate(((long) ((getRolledValue(internalGet(field), amount, 1, max10) - 1) * 7)) + month13 + ((long) x)).getDayOfMonth());
                        return;
                    }
                case 10:
                case 11:
                    int min6 = max5 + 1;
                    int h = internalGet(field);
                    int nh = (h + amount) % min6;
                    if (nh < 0) {
                        nh += min6;
                    }
                    this.time += (long) ((nh - h) * 3600000);
                    CalendarDate d7 = jcal.getCalendarDate(this.time, getZone());
                    if (internalGet(5) != d7.getDayOfMonth()) {
                        d7.setEra(this.jdate.getEra());
                        d7.setDate(internalGet(1), internalGet(2) + 1, internalGet(5));
                        if (field == 10) {
                            d7.addHours(12);
                        }
                        this.time = jcal.getTime(d7);
                    }
                    int hourOfDay = d7.getHours();
                    internalSet(field, hourOfDay % min6);
                    if (field == 10) {
                        internalSet(11, hourOfDay);
                    } else {
                        internalSet(9, hourOfDay / 12);
                        internalSet(10, hourOfDay % 12);
                    }
                    int zoneOffset = d7.getZoneOffset();
                    int saving = d7.getDaylightSaving();
                    internalSet(15, zoneOffset - saving);
                    internalSet(16, saving);
                    return;
                default:
                    min = min2;
                    max2 = max5;
                    max = max2;
                    break;
            }
            set(field, getRolledValue(internalGet(field), amount, min, max));
        }
    }

    @Override // java.util.Calendar
    public String getDisplayName(int field, int style, Locale locale) {
        if (!checkDisplayNameParams(field, style, 1, 4, locale, 647)) {
            return null;
        }
        int fieldValue = get(field);
        if (field == 1 && (getBaseStyle(style) != 2 || fieldValue != 1 || get(0) == 0)) {
            return null;
        }
        String name = CalendarDataUtility.retrieveFieldValueName(getCalendarType(), field, fieldValue, style, locale);
        if (name != null || field != 0) {
            return name;
        }
        Era[] eraArr = eras;
        if (fieldValue >= eraArr.length) {
            return name;
        }
        Era era = eraArr[fieldValue];
        return style == 1 ? era.getAbbreviation() : era.getName();
    }

    @Override // java.util.Calendar
    public Map<String, Integer> getDisplayNames(int field, int style, Locale locale) {
        if (!checkDisplayNameParams(field, style, 0, 4, locale, 647)) {
            return null;
        }
        Map<String, Integer> names = CalendarDataUtility.retrieveFieldValueNames(getCalendarType(), field, style, locale);
        if (names != null && field == 0) {
            int size = names.size();
            if (style == 0) {
                Set<Integer> values = new HashSet<>();
                for (String key : names.keySet()) {
                    values.add(names.get(key));
                }
                size = values.size();
            }
            if (size < eras.length) {
                int baseStyle = getBaseStyle(style);
                int i = size;
                while (true) {
                    Era[] eraArr = eras;
                    if (i >= eraArr.length) {
                        break;
                    }
                    Era era = eraArr[i];
                    if (baseStyle == 0 || baseStyle == 1 || baseStyle == 4) {
                        names.put(era.getAbbreviation(), Integer.valueOf(i));
                    }
                    if (baseStyle == 0 || baseStyle == 2) {
                        names.put(era.getName(), Integer.valueOf(i));
                    }
                    i++;
                }
            }
        }
        return names;
    }

    @Override // java.util.Calendar
    public int getMinimum(int field) {
        return MIN_VALUES[field];
    }

    @Override // java.util.Calendar
    public int getMaximum(int field) {
        if (field != 1) {
            return MAX_VALUES[field];
        }
        return Math.max(LEAST_MAX_VALUES[1], jcal.getCalendarDate(Long.MAX_VALUE, getZone()).getYear());
    }

    @Override // java.util.Calendar
    public int getGreatestMinimum(int field) {
        if (field == 1) {
            return 1;
        }
        return MIN_VALUES[field];
    }

    @Override // java.util.Calendar
    public int getLeastMaximum(int field) {
        if (field != 1) {
            return LEAST_MAX_VALUES[field];
        }
        return Math.min(LEAST_MAX_VALUES[1], getMaximum(1));
    }

    @Override // java.util.Calendar
    public int getActualMinimum(int field) {
        if (!isFieldSet(14, field)) {
            return getMinimum(field);
        }
        LocalGregorianCalendar.Date jd = jcal.getCalendarDate(getNormalizedCalendar().getTimeInMillis(), getZone());
        int eraIndex = getEraIndex(jd);
        if (field != 1) {
            if (field != 2) {
                if (field != 3) {
                    return 0;
                }
                CalendarDate d = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
                d.addYear(400);
                jcal.normalize(d);
                jd.setEra(d.getEra());
                jd.setYear(d.getYear());
                jcal.normalize(jd);
                long jan1 = jcal.getFixedDate(d);
                long fd = jcal.getFixedDate(jd);
                long day1 = fd - ((long) ((getWeekNumber(jan1, fd) - 1) * 7));
                if (day1 < jan1 || (day1 == jan1 && jd.getTimeOfDay() < d.getTimeOfDay())) {
                    return 1 + 1;
                }
                return 1;
            } else if (eraIndex <= 1 || jd.getYear() != 1) {
                return 0;
            } else {
                CalendarDate d2 = jcal.getCalendarDate(eras[eraIndex].getSince(getZone()), getZone());
                int value = d2.getMonth() - 1;
                if (jd.getDayOfMonth() < d2.getDayOfMonth()) {
                    return value + 1;
                }
                return value;
            }
        } else if (eraIndex > 0) {
            CalendarDate d3 = jcal.getCalendarDate(eras[eraIndex].getSince(getZone()), getZone());
            jd.setYear(d3.getYear());
            jcal.normalize(jd);
            if (getYearOffsetInMillis(jd) < getYearOffsetInMillis(d3)) {
                return 1 + 1;
            }
            return 1;
        } else {
            int value2 = getMinimum(field);
            CalendarDate d4 = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
            int y = d4.getYear();
            if (y > 400) {
                y -= 400;
            }
            jd.setYear(y);
            jcal.normalize(jd);
            if (getYearOffsetInMillis(jd) < getYearOffsetInMillis(d4)) {
                return value2 + 1;
            }
            return value2;
        }
    }

    @Override // java.util.Calendar
    public int getActualMaximum(int field) {
        int value;
        CalendarDate d;
        int value2;
        if (((1 << field) & 130689) != 0) {
            return getMaximum(field);
        }
        JapaneseImperialCalendar jc = getNormalizedCalendar();
        LocalGregorianCalendar.Date date = jc.jdate;
        date.getNormalizedYear();
        switch (field) {
            case 1:
                CalendarDate jd = jcal.getCalendarDate(jc.getTimeInMillis(), getZone());
                int eraIndex = getEraIndex(date);
                Era[] eraArr = eras;
                if (eraIndex == eraArr.length - 1) {
                    d = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                    value = d.getYear();
                    if (value > 400) {
                        jd.setYear(value - 400);
                    }
                } else {
                    d = jcal.getCalendarDate(eraArr[eraIndex + 1].getSince(getZone()) - 1, getZone());
                    value = d.getYear();
                    jd.setYear(value);
                }
                jcal.normalize(jd);
                if (getYearOffsetInMillis(jd) > getYearOffsetInMillis(d)) {
                    return value - 1;
                }
                return value;
            case 2:
                if (isTransitionYear(date.getNormalizedYear())) {
                    int eraIndex2 = getEraIndex(date);
                    if (date.getYear() != 1) {
                        eraIndex2++;
                    }
                    long transition = sinceFixedDates[eraIndex2];
                    if (jc.cachedFixedDate >= transition) {
                        return 11;
                    }
                    LocalGregorianCalendar.Date ldate = (LocalGregorianCalendar.Date) date.clone();
                    jcal.getCalendarDateFromFixedDate(ldate, transition - 1);
                    return ldate.getMonth() - 1;
                }
                LocalGregorianCalendar.Date d2 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                if (date.getEra() == d2.getEra() && date.getYear() == d2.getYear()) {
                    return d2.getMonth() - 1;
                }
                return 11;
            case 3:
                if (!isTransitionYear(date.getNormalizedYear())) {
                    LocalGregorianCalendar.Date jd2 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                    if (date.getEra() == jd2.getEra() && date.getYear() == jd2.getYear()) {
                        long fd = jcal.getFixedDate(jd2);
                        return getWeekNumber(getFixedDateJan1(jd2, fd), fd);
                    } else if (date.getEra() == null && date.getYear() == getMinimum(1)) {
                        CalendarDate d3 = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
                        d3.addYear(400);
                        jcal.normalize(d3);
                        jd2.setEra(d3.getEra());
                        jd2.setDate(d3.getYear() + 1, 1, 1);
                        jcal.normalize(jd2);
                        long jan1 = jcal.getFixedDate(d3);
                        long nextJan1 = jcal.getFixedDate(jd2);
                        long nextJan1st = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(6 + nextJan1, getFirstDayOfWeek());
                        if (((int) (nextJan1st - nextJan1)) >= getMinimalDaysInFirstWeek()) {
                            nextJan1st -= 7;
                        }
                        return getWeekNumber(jan1, nextJan1st);
                    } else {
                        CalendarDate d4 = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
                        d4.setDate(date.getNormalizedYear(), 1, 1);
                        int dayOfWeek = gcal.getDayOfWeek(d4) - getFirstDayOfWeek();
                        if (dayOfWeek < 0) {
                            dayOfWeek += 7;
                        }
                        int magic = (getMinimalDaysInFirstWeek() + dayOfWeek) - 1;
                        if (magic == 6 || (date.isLeapYear() && (magic == 5 || magic == 12))) {
                            return 52 + 1;
                        }
                        return 52;
                    }
                } else {
                    if (jc == this) {
                        jc = (JapaneseImperialCalendar) jc.clone();
                    }
                    int max = getActualMaximum(6);
                    jc.set(6, max);
                    int value3 = jc.get(3);
                    if (value3 != 1 || max <= 7) {
                        return value3;
                    }
                    jc.add(3, -1);
                    return jc.get(3);
                }
            case 4:
                LocalGregorianCalendar.Date jd3 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                if (date.getEra() == jd3.getEra() && date.getYear() == jd3.getYear()) {
                    long fd2 = jcal.getFixedDate(jd3);
                    return getWeekNumber((fd2 - ((long) jd3.getDayOfMonth())) + 1, fd2);
                }
                CalendarDate d5 = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
                d5.setDate(date.getNormalizedYear(), date.getMonth(), 1);
                int dayOfWeek2 = gcal.getDayOfWeek(d5);
                int monthLength = gcal.getMonthLength(d5);
                int dayOfWeek3 = dayOfWeek2 - getFirstDayOfWeek();
                if (dayOfWeek3 < 0) {
                    dayOfWeek3 += 7;
                }
                int nDaysFirstWeek = 7 - dayOfWeek3;
                int value4 = 3;
                if (nDaysFirstWeek >= getMinimalDaysInFirstWeek()) {
                    value4 = 3 + 1;
                }
                int monthLength2 = monthLength - (nDaysFirstWeek + 21);
                if (monthLength2 > 0) {
                    value4++;
                    if (monthLength2 > 7) {
                        value4++;
                    }
                }
                return value4;
            case 5:
                return jcal.getMonthLength(date);
            case 6:
                if (isTransitionYear(date.getNormalizedYear())) {
                    int eraIndex3 = getEraIndex(date);
                    if (date.getYear() != 1) {
                        eraIndex3++;
                    }
                    long transition2 = sinceFixedDates[eraIndex3];
                    long fd3 = jc.cachedFixedDate;
                    CalendarDate d6 = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
                    d6.setDate(date.getNormalizedYear(), 1, 1);
                    if (fd3 < transition2) {
                        value2 = (int) (transition2 - gcal.getFixedDate(d6));
                    } else {
                        d6.addYear(1);
                        value2 = (int) (gcal.getFixedDate(d6) - transition2);
                    }
                    return value2;
                }
                LocalGregorianCalendar.Date d7 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                if (date.getEra() == d7.getEra() && date.getYear() == d7.getYear()) {
                    long fd4 = jcal.getFixedDate(d7);
                    return ((int) (fd4 - getFixedDateJan1(d7, fd4))) + 1;
                } else if (date.getYear() != getMinimum(1)) {
                    return jcal.getYearLength(date);
                } else {
                    CalendarDate d1 = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
                    long fd1 = jcal.getFixedDate(d1);
                    d1.addYear(1);
                    d1.setMonth(1).setDayOfMonth(1);
                    jcal.normalize(d1);
                    return (int) (jcal.getFixedDate(d1) - fd1);
                }
            case 7:
            default:
                throw new ArrayIndexOutOfBoundsException(field);
            case 8:
                int dow = date.getDayOfWeek();
                BaseCalendar.Date d8 = (BaseCalendar.Date) date.clone();
                int ndays = jcal.getMonthLength(d8);
                d8.setDayOfMonth(1);
                jcal.normalize(d8);
                int x = dow - d8.getDayOfWeek();
                if (x < 0) {
                    x += 7;
                }
                return ((ndays - x) + 6) / 7;
        }
    }

    private long getYearOffsetInMillis(CalendarDate date) {
        return (date.getTimeOfDay() + ((jcal.getDayOfYear(date) - 1) * 86400000)) - ((long) date.getZoneOffset());
    }

    @Override // java.util.Calendar
    public Object clone() {
        JapaneseImperialCalendar other = (JapaneseImperialCalendar) super.clone();
        other.jdate = (LocalGregorianCalendar.Date) this.jdate.clone();
        other.originalFields = null;
        other.zoneOffsets = null;
        return other;
    }

    @Override // java.util.Calendar
    public TimeZone getTimeZone() {
        TimeZone zone = super.getTimeZone();
        this.jdate.setZone(zone);
        return zone;
    }

    @Override // java.util.Calendar
    public void setTimeZone(TimeZone zone) {
        super.setTimeZone(zone);
        this.jdate.setZone(zone);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Calendar
    public void computeFields() {
        int mask;
        if (isPartiallyNormalized()) {
            mask = getSetStateFields();
            int fieldMask = (~mask) & 131071;
            if (fieldMask != 0 || this.cachedFixedDate == Long.MIN_VALUE) {
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
        TimeZone tz;
        long fixedDateJan1;
        int dayOfYear;
        long fixedDateMonth1;
        TimeZone tz2;
        int month;
        int mask;
        int i;
        long nextJan1;
        long prevJan1;
        int normalizedYear;
        int zoneOffset = 0;
        TimeZone tz3 = getZone();
        if (this.zoneOffsets == null) {
            this.zoneOffsets = new int[2];
        }
        if (tzMask != 98304) {
            if (tz3 instanceof ZoneInfo) {
                zoneOffset = ((ZoneInfo) tz3).getOffsetsByUtcTime(this.time, this.zoneOffsets);
            } else {
                zoneOffset = tz3.getOffset(this.time);
                this.zoneOffsets[0] = tz3.getRawOffset();
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
        if (fixedDate2 != this.cachedFixedDate || fixedDate2 < 0) {
            jcal.getCalendarDateFromFixedDate(this.jdate, fixedDate2);
            this.cachedFixedDate = fixedDate2;
        }
        int era = getEraIndex(this.jdate);
        int year = this.jdate.getYear();
        internalSet(0, era);
        internalSet(1, year);
        int mask2 = fieldMask | 3;
        int month2 = this.jdate.getMonth() - 1;
        int dayOfMonth = this.jdate.getDayOfMonth();
        if ((fieldMask & 164) != 0) {
            internalSet(2, month2);
            internalSet(5, dayOfMonth);
            internalSet(7, this.jdate.getDayOfWeek());
            mask2 |= 164;
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
            mask2 |= 32256;
        }
        if ((fieldMask & 98304) != 0) {
            internalSet(15, this.zoneOffsets[0]);
            internalSet(16, this.zoneOffsets[1]);
            mask2 |= 98304;
        }
        if ((fieldMask & 344) == 0) {
            return mask2;
        }
        int normalizedYear2 = this.jdate.getNormalizedYear();
        boolean transitionYear = isTransitionYear(this.jdate.getNormalizedYear());
        if (transitionYear) {
            long fixedDateJan12 = getFixedDateJan1(this.jdate, fixedDate2);
            dayOfYear = ((int) (fixedDate2 - fixedDateJan12)) + 1;
            tz = tz3;
            fixedDateJan1 = fixedDateJan12;
        } else if (normalizedYear2 == MIN_VALUES[1]) {
            tz = tz3;
            fixedDateJan1 = jcal.getFixedDate(jcal.getCalendarDate(Long.MIN_VALUE, getZone()));
            dayOfYear = ((int) (fixedDate2 - fixedDateJan1)) + 1;
        } else {
            tz = tz3;
            dayOfYear = (int) jcal.getDayOfYear(this.jdate);
            fixedDateJan1 = (fixedDate2 - ((long) dayOfYear)) + 1;
        }
        if (transitionYear) {
            fixedDateMonth1 = getFixedDateMonth1(this.jdate, fixedDate2);
            month = month2;
            tz2 = tz;
        } else {
            month = month2;
            tz2 = tz;
            fixedDateMonth1 = (fixedDate2 - ((long) dayOfMonth)) + 1;
        }
        internalSet(6, dayOfYear);
        internalSet(8, ((dayOfMonth - 1) / 7) + 1);
        int weekOfYear = getWeekNumber(fixedDateJan1, fixedDate2);
        if (weekOfYear == 0) {
            long fixedDec31 = fixedDateJan1 - 1;
            LocalGregorianCalendar.Date d = getCalendarDate(fixedDec31);
            if (!transitionYear) {
                if (!isTransitionYear(d.getNormalizedYear())) {
                    long prevJan12 = fixedDateJan1 - 365;
                    if (d.isLeapYear()) {
                        normalizedYear = era;
                        mask = mask2;
                        prevJan1 = prevJan12 - 1;
                    } else {
                        normalizedYear = era;
                        mask = mask2;
                        prevJan1 = prevJan12;
                    }
                    weekOfYear = getWeekNumber(prevJan1, fixedDec31);
                }
            }
            if (transitionYear) {
                mask = mask2;
                if (this.jdate.getYear() == 1) {
                    if (era > 5) {
                        CalendarDate pd = eras[era - 1].getSinceDate();
                        if (normalizedYear2 == pd.getYear()) {
                            d.setMonth(pd.getMonth()).setDayOfMonth(pd.getDayOfMonth());
                        }
                    } else {
                        d.setMonth(1).setDayOfMonth(1);
                    }
                    jcal.normalize(d);
                    normalizedYear = era;
                    prevJan1 = jcal.getFixedDate(d);
                } else {
                    long prevJan13 = fixedDateJan1 - 365;
                    if (d.isLeapYear()) {
                        normalizedYear = era;
                        prevJan1 = prevJan13 - 1;
                    } else {
                        normalizedYear = era;
                        prevJan1 = prevJan13;
                    }
                }
            } else {
                mask = mask2;
                CalendarDate cd = eras[getEraIndex(this.jdate)].getSinceDate();
                d.setMonth(cd.getMonth()).setDayOfMonth(cd.getDayOfMonth());
                jcal.normalize(d);
                normalizedYear = era;
                prevJan1 = jcal.getFixedDate(d);
            }
            weekOfYear = getWeekNumber(prevJan1, fixedDec31);
        } else {
            mask = mask2;
            if (transitionYear) {
                LocalGregorianCalendar.Date d2 = (LocalGregorianCalendar.Date) this.jdate.clone();
                if (this.jdate.getYear() == 1) {
                    d2.addYear(1);
                    d2.setMonth(1).setDayOfMonth(1);
                    nextJan1 = jcal.getFixedDate(d2);
                } else {
                    int nextEraIndex = getEraIndex(d2) + 1;
                    CalendarDate cd2 = eras[nextEraIndex].getSinceDate();
                    d2.setEra(eras[nextEraIndex]);
                    d2.setDate(1, cd2.getMonth(), cd2.getDayOfMonth());
                    jcal.normalize(d2);
                    nextJan1 = jcal.getFixedDate(d2);
                }
                i = weekOfYear;
                long nextJan1st = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(nextJan1 + 6, getFirstDayOfWeek());
                if (((int) (nextJan1st - nextJan1)) >= getMinimalDaysInFirstWeek() && fixedDate2 >= nextJan1st - 7) {
                    weekOfYear = 1;
                }
            } else if (weekOfYear >= 52) {
                long nextJan12 = fixedDateJan1 + 365;
                if (this.jdate.isLeapYear()) {
                    nextJan12++;
                }
                long nextJan1st2 = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(nextJan12 + 6, getFirstDayOfWeek());
                if (((int) (nextJan1st2 - nextJan12)) >= getMinimalDaysInFirstWeek() && fixedDate2 >= nextJan1st2 - 7) {
                    weekOfYear = 1;
                }
            } else {
                i = weekOfYear;
            }
            weekOfYear = i;
        }
        internalSet(3, weekOfYear);
        internalSet(4, getWeekNumber(fixedDateMonth1, fixedDate2));
        return mask | 344;
    }

    private int getWeekNumber(long fixedDay1, long fixedDate) {
        long fixedDay1st = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(6 + fixedDay1, getFirstDayOfWeek());
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
    @Override // java.util.Calendar
    public void computeTime() {
        int year;
        int era;
        long timeOfDay;
        char c;
        if (!isLenient()) {
            if (this.originalFields == null) {
                this.originalFields = new int[17];
            }
            for (int field = 0; field < 17; field++) {
                int value = internalGet(field);
                if (!isExternallySet(field) || (value >= getMinimum(field) && value <= getMaximum(field))) {
                    this.originalFields[field] = value;
                } else {
                    throw new IllegalArgumentException(getFieldName(field));
                }
            }
        }
        int fieldMask = selectFields();
        if (isSet(0)) {
            era = internalGet(0);
            year = isSet(1) ? internalGet(1) : 1;
        } else if (isSet(1)) {
            era = currentEra;
            year = internalGet(1);
        } else {
            era = 3;
            year = 45;
        }
        if (isFieldSet(fieldMask, 11)) {
            timeOfDay = 0 + ((long) internalGet(11));
        } else {
            timeOfDay = 0 + ((long) internalGet(10));
            if (isFieldSet(fieldMask, 9)) {
                timeOfDay += (long) (internalGet(9) * 12);
            }
        }
        long timeOfDay2 = (((((timeOfDay * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
        long fixedDate = timeOfDay2 / 86400000;
        long timeOfDay3 = timeOfDay2 % 86400000;
        while (timeOfDay3 < 0) {
            timeOfDay3 += 86400000;
            fixedDate--;
        }
        long millis = (((fixedDate + getFixedDate(era, year, fieldMask)) - 719163) * 86400000) + timeOfDay3;
        TimeZone zone = getZone();
        if (this.zoneOffsets == null) {
            this.zoneOffsets = new int[2];
        }
        int tzMask = fieldMask & 98304;
        if (tzMask != 98304) {
            zone.getOffsets(millis - ((long) zone.getRawOffset()), this.zoneOffsets);
        }
        if (tzMask != 0) {
            if (isFieldSet(tzMask, 15)) {
                this.zoneOffsets[0] = internalGet(15);
            }
            if (isFieldSet(tzMask, 16)) {
                c = 1;
                this.zoneOffsets[1] = internalGet(16);
            } else {
                c = 1;
            }
        } else {
            c = 1;
        }
        int[] iArr = this.zoneOffsets;
        this.time = millis - ((long) (iArr[0] + iArr[c]));
        int mask = computeFields(getSetStateFields() | fieldMask, tzMask);
        if (!isLenient()) {
            int field2 = 0;
            for (int i = 17; field2 < i; i = 17) {
                if (isExternallySet(field2) && this.originalFields[field2] != internalGet(field2)) {
                    int wrongValue = internalGet(field2);
                    System.arraycopy((Object) this.originalFields, 0, (Object) this.fields, 0, this.fields.length);
                    throw new IllegalArgumentException(getFieldName(field2) + "=" + wrongValue + ", expected " + this.originalFields[field2]);
                }
                field2++;
            }
        }
        setFieldsNormalized(mask);
    }

    private long getFixedDate(int era, int year, int fieldMask) {
        int dayOfWeek;
        long fixedDate;
        int dayOfWeek2;
        int dowim;
        long fixedDate2;
        int year2 = year;
        int month = 0;
        int firstDayOfMonth = 1;
        if (isFieldSet(fieldMask, 2)) {
            month = internalGet(2);
            if (month > 11) {
                year2 += month / 12;
                month %= 12;
            } else if (month < 0) {
                int[] rem = new int[1];
                year2 += CalendarUtils.floorDivide(month, 12, rem);
                month = rem[0];
            }
        } else if (year2 == 1 && era != 0) {
            CalendarDate d = eras[era].getSinceDate();
            month = d.getMonth() - 1;
            firstDayOfMonth = d.getDayOfMonth();
        }
        if (year2 == MIN_VALUES[1]) {
            CalendarDate dx = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
            int m = dx.getMonth() - 1;
            if (month < m) {
                month = m;
            }
            if (month == m) {
                firstDayOfMonth = dx.getDayOfMonth();
            }
        }
        LocalGregorianCalendar.Date date = jcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        date.setEra(era > 0 ? eras[era] : null);
        date.setDate(year2, month + 1, firstDayOfMonth);
        jcal.normalize(date);
        long fixedDate3 = jcal.getFixedDate(date);
        if (isFieldSet(fieldMask, 2)) {
            if (isFieldSet(fieldMask, 5)) {
                if (isSet(5)) {
                    return (fixedDate3 + ((long) internalGet(5))) - ((long) firstDayOfMonth);
                }
                return fixedDate3;
            } else if (isFieldSet(fieldMask, 4)) {
                long firstDayOfWeek = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(fixedDate3 + 6, getFirstDayOfWeek());
                if (firstDayOfWeek - fixedDate3 >= ((long) getMinimalDaysInFirstWeek())) {
                    firstDayOfWeek -= 7;
                }
                if (isFieldSet(fieldMask, 7)) {
                    firstDayOfWeek = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(6 + firstDayOfWeek, internalGet(7));
                }
                return firstDayOfWeek + ((long) ((internalGet(4) - 1) * 7));
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
                    fixedDate2 = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore((fixedDate3 + ((long) (dowim * 7))) - 1, dayOfWeek2);
                } else {
                    fixedDate2 = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore((fixedDate3 + ((long) (monthLength(month, year2) + ((dowim + 1) * 7)))) - 1, dayOfWeek2);
                }
                return fixedDate2;
            }
        } else if (isFieldSet(fieldMask, 6)) {
            if (isTransitionYear(date.getNormalizedYear())) {
                fixedDate = getFixedDateJan1(date, fixedDate3);
            } else {
                fixedDate = fixedDate3;
            }
            return (fixedDate + ((long) internalGet(6))) - 1;
        } else {
            long firstDayOfWeek2 = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(fixedDate3 + 6, getFirstDayOfWeek());
            if (firstDayOfWeek2 - fixedDate3 >= ((long) getMinimalDaysInFirstWeek())) {
                firstDayOfWeek2 -= 7;
            }
            if (isFieldSet(fieldMask, 7) && (dayOfWeek = internalGet(7)) != getFirstDayOfWeek()) {
                firstDayOfWeek2 = LocalGregorianCalendar.getDayOfWeekDateOnOrBefore(6 + firstDayOfWeek2, dayOfWeek);
            }
            return firstDayOfWeek2 + ((((long) internalGet(3)) - 1) * 7);
        }
    }

    private long getFixedDateJan1(LocalGregorianCalendar.Date date, long fixedDate) {
        date.getEra();
        if (date.getEra() != null && date.getYear() == 1) {
            for (int eraIndex = getEraIndex(date); eraIndex > 0; eraIndex--) {
                long fd = gcal.getFixedDate(eras[eraIndex].getSinceDate());
                if (fd <= fixedDate) {
                    return fd;
                }
            }
        }
        CalendarDate d = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        d.setDate(date.getNormalizedYear(), 1, 1);
        return gcal.getFixedDate(d);
    }

    private long getFixedDateMonth1(LocalGregorianCalendar.Date date, long fixedDate) {
        int eraIndex = getTransitionEraIndex(date);
        if (eraIndex != -1) {
            long transition = sinceFixedDates[eraIndex];
            if (transition <= fixedDate) {
                return transition;
            }
        }
        return (fixedDate - ((long) date.getDayOfMonth())) + 1;
    }

    private static LocalGregorianCalendar.Date getCalendarDate(long fd) {
        LocalGregorianCalendar.Date d = jcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        jcal.getCalendarDateFromFixedDate(d, fd);
        return d;
    }

    private int monthLength(int month, int gregorianYear) {
        return CalendarUtils.isGregorianLeapYear(gregorianYear) ? GregorianCalendar.LEAP_MONTH_LENGTH[month] : GregorianCalendar.MONTH_LENGTH[month];
    }

    private int monthLength(int month) {
        return this.jdate.isLeapYear() ? GregorianCalendar.LEAP_MONTH_LENGTH[month] : GregorianCalendar.MONTH_LENGTH[month];
    }

    private int actualMonthLength() {
        int length = jcal.getMonthLength(this.jdate);
        int eraIndex = getTransitionEraIndex(this.jdate);
        if (eraIndex != -1) {
            return length;
        }
        long transitionFixedDate = sinceFixedDates[eraIndex];
        CalendarDate d = eras[eraIndex].getSinceDate();
        if (transitionFixedDate <= this.cachedFixedDate) {
            return length - (d.getDayOfMonth() - 1);
        }
        return d.getDayOfMonth() - 1;
    }

    private static int getTransitionEraIndex(LocalGregorianCalendar.Date date) {
        int eraIndex = getEraIndex(date);
        CalendarDate transitionDate = eras[eraIndex].getSinceDate();
        if (transitionDate.getYear() == date.getNormalizedYear() && transitionDate.getMonth() == date.getMonth()) {
            return eraIndex;
        }
        Era[] eraArr = eras;
        if (eraIndex >= eraArr.length - 1) {
            return -1;
        }
        int eraIndex2 = eraIndex + 1;
        CalendarDate transitionDate2 = eraArr[eraIndex2].getSinceDate();
        if (transitionDate2.getYear() == date.getNormalizedYear() && transitionDate2.getMonth() == date.getMonth()) {
            return eraIndex2;
        }
        return -1;
    }

    private boolean isTransitionYear(int normalizedYear) {
        for (int i = eras.length - 1; i > 0; i--) {
            int transitionYear = eras[i].getSinceDate().getYear();
            if (normalizedYear == transitionYear) {
                return true;
            }
            if (normalizedYear > transitionYear) {
                return false;
            }
        }
        return false;
    }

    private static int getEraIndex(LocalGregorianCalendar.Date date) {
        Era era = date.getEra();
        for (int i = eras.length - 1; i > 0; i--) {
            if (eras[i] == era) {
                return i;
            }
        }
        return 0;
    }

    private JapaneseImperialCalendar getNormalizedCalendar() {
        if (isFullyNormalized()) {
            return this;
        }
        JapaneseImperialCalendar jc = (JapaneseImperialCalendar) clone();
        jc.setLenient(true);
        jc.complete();
        return jc;
    }

    private void pinDayOfMonth(LocalGregorianCalendar.Date date) {
        int year = date.getYear();
        int dom = date.getDayOfMonth();
        if (year != getMinimum(1)) {
            date.setDayOfMonth(1);
            jcal.normalize(date);
            int monthLength = jcal.getMonthLength(date);
            if (dom > monthLength) {
                date.setDayOfMonth(monthLength);
            } else {
                date.setDayOfMonth(dom);
            }
            jcal.normalize(date);
            return;
        }
        LocalGregorianCalendar.Date d = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
        LocalGregorianCalendar.Date realDate = jcal.getCalendarDate(this.time, getZone());
        long tod = realDate.getTimeOfDay();
        realDate.addYear(400);
        realDate.setMonth(date.getMonth());
        realDate.setDayOfMonth(1);
        jcal.normalize(realDate);
        int monthLength2 = jcal.getMonthLength(realDate);
        if (dom > monthLength2) {
            realDate.setDayOfMonth(monthLength2);
        } else if (dom < d.getDayOfMonth()) {
            realDate.setDayOfMonth(d.getDayOfMonth());
        } else {
            realDate.setDayOfMonth(dom);
        }
        if (realDate.getDayOfMonth() == d.getDayOfMonth() && tod < d.getTimeOfDay()) {
            realDate.setDayOfMonth(Math.min(dom + 1, monthLength2));
        }
        date.setDate(year, realDate.getMonth(), realDate.getDayOfMonth());
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
        return isSet(0) ? internalGet(0) : currentEra;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (this.jdate == null) {
            this.jdate = jcal.newCalendarDate(getZone());
            this.cachedFixedDate = Long.MIN_VALUE;
        }
    }
}
