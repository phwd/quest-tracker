package java.util;

import java.io.ObjectInputStream;
import sun.util.calendar.AbstractCalendar;
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
    private static final Era BEFORE_MEIJI_ERA = new Era("BeforeMeiji", "BM", Long.MIN_VALUE, false);
    static final int[] LEAST_MAX_VALUES = {currentEra, currentEra, currentEra, currentEra, 4, 28, currentEra, 7, 4, 1, 11, 23, 59, 59, 999, 50400000, 1200000};
    static final int[] MAX_VALUES = {currentEra, 292278994, 11, 53, 6, 31, 366, 7, 6, 1, 11, 23, 59, 59, 999, 50400000, 7200000};
    static final int[] MIN_VALUES = {currentEra, -292275055, currentEra, 1, currentEra, 1, 1, 1, 1, currentEra, currentEra, currentEra, currentEra, currentEra, currentEra, -46800000, currentEra};
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

    @Override // java.util.Calendar
    public String getCalendarType() {
        return "japanese";
    }

    static {
        Era[] eras2 = jcal.getEras();
        int length = eras2.length + 1;
        eras = new Era[length];
        sinceFixedDates = new long[length];
        sinceFixedDates[currentEra] = gcal.getFixedDate(BEFORE_MEIJI_ERA.getSinceDate());
        eras[currentEra] = BEFORE_MEIJI_ERA;
        int length2 = eras2.length;
        int i = 1;
        int i2 = currentEra;
        while (i2 < length2) {
            Era era = eras2[i2];
            sinceFixedDates[i] = gcal.getFixedDate(era.getSinceDate());
            eras[i] = era;
            i2++;
            i++;
        }
        int[] iArr = LEAST_MAX_VALUES;
        int[] iArr2 = MAX_VALUES;
        int length3 = eras.length - 1;
        iArr2[currentEra] = length3;
        iArr[currentEra] = length3;
        Gregorian.Date newCalendarDate = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = 1;
        while (true) {
            Era[] eraArr = eras;
            if (i5 < eraArr.length) {
                long j = sinceFixedDates[i5];
                CalendarDate sinceDate = eraArr[i5].getSinceDate();
                newCalendarDate.setDate(sinceDate.getYear(), 1, 1);
                long fixedDate = gcal.getFixedDate(newCalendarDate);
                if (j != fixedDate) {
                    i4 = Math.min(((int) (j - fixedDate)) + 1, i4);
                }
                newCalendarDate.setDate(sinceDate.getYear(), 12, 31);
                long fixedDate2 = gcal.getFixedDate(newCalendarDate);
                if (j != fixedDate2) {
                    i4 = Math.min(((int) (fixedDate2 - j)) + 1, i4);
                }
                LocalGregorianCalendar.Date calendarDate = getCalendarDate(j - 1);
                int year = calendarDate.getYear();
                if (calendarDate.getMonth() != 1 || calendarDate.getDayOfMonth() != 1) {
                    year--;
                }
                i3 = Math.min(year, i3);
                i5++;
            } else {
                int[] iArr3 = LEAST_MAX_VALUES;
                iArr3[1] = i3;
                iArr3[6] = i4;
                return;
            }
        }
    }

    JapaneseImperialCalendar(TimeZone timeZone, Locale locale) {
        super(timeZone, locale);
        this.jdate = jcal.newCalendarDate(timeZone);
        setTimeInMillis(System.currentTimeMillis());
    }

    @Override // java.util.Calendar
    public boolean equals(Object obj) {
        return (obj instanceof JapaneseImperialCalendar) && super.equals(obj);
    }

    @Override // java.util.Calendar
    public int hashCode() {
        return this.jdate.hashCode() ^ super.hashCode();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Calendar
    public void add(int i, int i2) {
        long j;
        long j2;
        if (i2 != 0) {
            if (i < 0 || i >= 15) {
                throw new IllegalArgumentException();
            }
            complete();
            if (i == 1) {
                LocalGregorianCalendar.Date date = (LocalGregorianCalendar.Date) this.jdate.clone();
                date.addYear(i2);
                pinDayOfMonth(date);
                set(currentEra, getEraIndex(date));
                set(1, date.getYear());
                set(2, date.getMonth() - 1);
                set(5, date.getDayOfMonth());
            } else if (i == 2) {
                LocalGregorianCalendar.Date date2 = (LocalGregorianCalendar.Date) this.jdate.clone();
                date2.addMonth(i2);
                pinDayOfMonth(date2);
                set(currentEra, getEraIndex(date2));
                set(1, date2.getYear());
                set(2, date2.getMonth() - 1);
                set(5, date2.getDayOfMonth());
            } else if (i == 0) {
                int internalGet = internalGet(currentEra) + i2;
                if (internalGet < 0) {
                    internalGet = currentEra;
                } else {
                    Era[] eraArr = eras;
                    if (internalGet > eraArr.length - 1) {
                        internalGet = eraArr.length - 1;
                    }
                }
                set(currentEra, internalGet);
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
                long j4 = this.cachedFixedDate;
                long internalGet2 = ((((((j + ((long) internalGet(11))) * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
                if (internalGet2 >= 86400000) {
                    j4++;
                    internalGet2 -= 86400000;
                } else if (internalGet2 < 0) {
                    j4--;
                    internalGet2 += 86400000;
                }
                long j5 = j4 + j3;
                int internalGet3 = internalGet(15) + internalGet(16);
                setTimeInMillis((((j5 - 719163) * 86400000) + internalGet2) - ((long) internalGet3));
                int internalGet4 = internalGet3 - (internalGet(15) + internalGet(16));
                if (internalGet4 != 0) {
                    long j6 = (long) internalGet4;
                    setTimeInMillis(this.time + j6);
                    if (this.cachedFixedDate != j5) {
                        setTimeInMillis(this.time - j6);
                    }
                }
            }
        }
    }

    @Override // java.util.Calendar
    public String getDisplayName(int i, int i2, Locale locale) {
        if (!checkDisplayNameParams(i, i2, 1, 4, locale, 647)) {
            return null;
        }
        int i3 = get(i);
        if (i == 1 && (getBaseStyle(i2) != 2 || i3 != 1 || get(currentEra) == 0)) {
            return null;
        }
        String retrieveFieldValueName = CalendarDataUtility.retrieveFieldValueName(getCalendarType(), i, i3, i2, locale);
        if (retrieveFieldValueName != null || i != 0) {
            return retrieveFieldValueName;
        }
        Era[] eraArr = eras;
        if (i3 >= eraArr.length) {
            return retrieveFieldValueName;
        }
        Era era = eraArr[i3];
        return i2 == 1 ? era.getAbbreviation() : era.getName();
    }

    @Override // java.util.Calendar
    public Map getDisplayNames(int i, int i2, Locale locale) {
        if (!checkDisplayNameParams(i, i2, currentEra, 4, locale, 647)) {
            return null;
        }
        Map retrieveFieldValueNames = CalendarDataUtility.retrieveFieldValueNames(getCalendarType(), i, i2, locale);
        if (retrieveFieldValueNames != null && i == 0) {
            int size = retrieveFieldValueNames.size();
            if (i2 == 0) {
                HashSet hashSet = new HashSet();
                for (String str : retrieveFieldValueNames.keySet()) {
                    hashSet.add((Integer) retrieveFieldValueNames.get(str));
                }
                size = hashSet.size();
            }
            if (size < eras.length) {
                int baseStyle = getBaseStyle(i2);
                while (true) {
                    Era[] eraArr = eras;
                    if (size >= eraArr.length) {
                        break;
                    }
                    Era era = eraArr[size];
                    if (baseStyle == 0 || baseStyle == 1 || baseStyle == 4) {
                        retrieveFieldValueNames.put(era.getAbbreviation(), Integer.valueOf(size));
                    }
                    if (baseStyle == 0 || baseStyle == 2) {
                        retrieveFieldValueNames.put(era.getName(), Integer.valueOf(size));
                    }
                    size++;
                }
            }
        }
        return retrieveFieldValueNames;
    }

    @Override // java.util.Calendar
    public int getMinimum(int i) {
        return MIN_VALUES[i];
    }

    @Override // java.util.Calendar
    public int getMaximum(int i) {
        if (i != 1) {
            return MAX_VALUES[i];
        }
        return Math.max(LEAST_MAX_VALUES[1], jcal.getCalendarDate(Long.MAX_VALUE, getZone()).getYear());
    }

    @Override // java.util.Calendar
    public int getGreatestMinimum(int i) {
        if (i == 1) {
            return 1;
        }
        return MIN_VALUES[i];
    }

    @Override // java.util.Calendar
    public int getLeastMaximum(int i) {
        if (i != 1) {
            return LEAST_MAX_VALUES[i];
        }
        return Math.min(LEAST_MAX_VALUES[1], getMaximum(1));
    }

    @Override // java.util.Calendar
    public int getActualMaximum(int i) {
        int i2;
        LocalGregorianCalendar.Date date;
        int i3;
        int month;
        int i4;
        long fixedDate;
        if (((1 << i) & 130689) != 0) {
            return getMaximum(i);
        }
        JapaneseImperialCalendar normalizedCalendar = getNormalizedCalendar();
        LocalGregorianCalendar.Date date2 = normalizedCalendar.jdate;
        date2.getNormalizedYear();
        int i5 = 3;
        switch (i) {
            case 1:
                LocalGregorianCalendar.Date calendarDate = jcal.getCalendarDate(normalizedCalendar.getTimeInMillis(), getZone());
                int eraIndex = getEraIndex(date2);
                Era[] eraArr = eras;
                if (eraIndex == eraArr.length - 1) {
                    date = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                    i2 = date.getYear();
                    if (i2 > 400) {
                        calendarDate.setYear(i2 - 400);
                    }
                } else {
                    date = jcal.getCalendarDate(eraArr[eraIndex + 1].getSince(getZone()) - 1, getZone());
                    i2 = date.getYear();
                    calendarDate.setYear(i2);
                }
                jcal.normalize(calendarDate);
                return getYearOffsetInMillis(calendarDate) > getYearOffsetInMillis(date) ? i2 - 1 : i2;
            case 2:
                i3 = 11;
                if (isTransitionYear(date2.getNormalizedYear())) {
                    int eraIndex2 = getEraIndex(date2);
                    if (date2.getYear() != 1) {
                        eraIndex2++;
                    }
                    long j = sinceFixedDates[eraIndex2];
                    if (normalizedCalendar.cachedFixedDate < j) {
                        LocalGregorianCalendar.Date date3 = (LocalGregorianCalendar.Date) date2.clone();
                        jcal.getCalendarDateFromFixedDate(date3, j - 1);
                        month = date3.getMonth();
                    }
                    return i3;
                }
                LocalGregorianCalendar.Date calendarDate2 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                if (date2.getEra() == calendarDate2.getEra() && date2.getYear() == calendarDate2.getYear()) {
                    month = calendarDate2.getMonth();
                }
                return i3;
                i4 = month - 1;
                return i4;
            case 3:
                if (!isTransitionYear(date2.getNormalizedYear())) {
                    LocalGregorianCalendar.Date calendarDate3 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                    if (date2.getEra() == calendarDate3.getEra() && date2.getYear() == calendarDate3.getYear()) {
                        long fixedDate2 = jcal.getFixedDate(calendarDate3);
                        return getWeekNumber(getFixedDateJan1(calendarDate3, fixedDate2), fixedDate2);
                    } else if (date2.getEra() == null && date2.getYear() == getMinimum(1)) {
                        LocalGregorianCalendar.Date calendarDate4 = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
                        calendarDate4.addYear(400);
                        jcal.normalize(calendarDate4);
                        calendarDate3.setEra(calendarDate4.getEra());
                        calendarDate3.setDate(calendarDate4.getYear() + 1, 1, 1);
                        jcal.normalize(calendarDate3);
                        long fixedDate3 = jcal.getFixedDate(calendarDate4);
                        long fixedDate4 = jcal.getFixedDate(calendarDate3);
                        long dayOfWeekDateOnOrBefore = AbstractCalendar.getDayOfWeekDateOnOrBefore(6 + fixedDate4, getFirstDayOfWeek());
                        if (((int) (dayOfWeekDateOnOrBefore - fixedDate4)) >= getMinimalDaysInFirstWeek()) {
                            dayOfWeekDateOnOrBefore -= 7;
                        }
                        return getWeekNumber(fixedDate3, dayOfWeekDateOnOrBefore);
                    } else {
                        Gregorian.Date newCalendarDate = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
                        newCalendarDate.setDate(date2.getNormalizedYear(), 1, 1);
                        int dayOfWeek = gcal.getDayOfWeek(newCalendarDate) - getFirstDayOfWeek();
                        if (dayOfWeek < 0) {
                            dayOfWeek += 7;
                        }
                        int minimalDaysInFirstWeek = (dayOfWeek + getMinimalDaysInFirstWeek()) - 1;
                        if (minimalDaysInFirstWeek != 6) {
                            if (!date2.isLeapYear()) {
                                return 52;
                            }
                            if (!(minimalDaysInFirstWeek == 5 || minimalDaysInFirstWeek == 12)) {
                                return 52;
                            }
                        }
                        i4 = 53;
                    }
                } else {
                    if (normalizedCalendar == this) {
                        normalizedCalendar = (JapaneseImperialCalendar) normalizedCalendar.clone();
                    }
                    int actualMaximum = getActualMaximum(6);
                    normalizedCalendar.set(6, actualMaximum);
                    i3 = normalizedCalendar.get(3);
                    if (i3 == 1 && actualMaximum > 7) {
                        normalizedCalendar.add(3, -1);
                        i4 = normalizedCalendar.get(3);
                    }
                    return i3;
                }
                return i4;
            case 4:
                LocalGregorianCalendar.Date calendarDate5 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                if (date2.getEra() == calendarDate5.getEra() && date2.getYear() == calendarDate5.getYear()) {
                    long fixedDate5 = jcal.getFixedDate(calendarDate5);
                    i4 = getWeekNumber((fixedDate5 - ((long) calendarDate5.getDayOfMonth())) + 1, fixedDate5);
                    return i4;
                }
                Gregorian.Date newCalendarDate2 = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
                newCalendarDate2.setDate(date2.getNormalizedYear(), date2.getMonth(), 1);
                int dayOfWeek2 = gcal.getDayOfWeek(newCalendarDate2);
                int monthLength = gcal.getMonthLength(newCalendarDate2);
                int firstDayOfWeek = dayOfWeek2 - getFirstDayOfWeek();
                if (firstDayOfWeek < 0) {
                    firstDayOfWeek += 7;
                }
                int i6 = 7 - firstDayOfWeek;
                if (i6 >= getMinimalDaysInFirstWeek()) {
                    i5 = 4;
                }
                int i7 = monthLength - (i6 + 21);
                if (i7 > 0) {
                    i5++;
                    if (i7 > 7) {
                        i5++;
                    }
                }
                return i5;
            case 5:
                return jcal.getMonthLength(date2);
            case 6:
                if (isTransitionYear(date2.getNormalizedYear())) {
                    int eraIndex3 = getEraIndex(date2);
                    if (date2.getYear() != 1) {
                        eraIndex3++;
                    }
                    long j2 = sinceFixedDates[eraIndex3];
                    long j3 = normalizedCalendar.cachedFixedDate;
                    Gregorian.Date newCalendarDate3 = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
                    newCalendarDate3.setDate(date2.getNormalizedYear(), 1, 1);
                    if (j3 < j2) {
                        fixedDate = j2 - gcal.getFixedDate(newCalendarDate3);
                    } else {
                        newCalendarDate3.addYear(1);
                        i4 = (int) (gcal.getFixedDate(newCalendarDate3) - j2);
                        return i4;
                    }
                } else {
                    LocalGregorianCalendar.Date calendarDate6 = jcal.getCalendarDate(Long.MAX_VALUE, getZone());
                    if (date2.getEra() == calendarDate6.getEra() && date2.getYear() == calendarDate6.getYear()) {
                        long fixedDate6 = jcal.getFixedDate(calendarDate6);
                        i4 = ((int) (fixedDate6 - getFixedDateJan1(calendarDate6, fixedDate6))) + 1;
                        return i4;
                    } else if (date2.getYear() == getMinimum(1)) {
                        LocalGregorianCalendar.Date calendarDate7 = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
                        long fixedDate7 = jcal.getFixedDate(calendarDate7);
                        calendarDate7.addYear(1);
                        calendarDate7.setMonth(1);
                        calendarDate7.setDayOfMonth(1);
                        jcal.normalize(calendarDate7);
                        fixedDate = jcal.getFixedDate(calendarDate7) - fixedDate7;
                    } else {
                        i4 = jcal.getYearLength(date2);
                        return i4;
                    }
                }
                i4 = (int) fixedDate;
                return i4;
            case 7:
            default:
                throw new ArrayIndexOutOfBoundsException(i);
            case 8:
                int dayOfWeek3 = date2.getDayOfWeek();
                BaseCalendar.Date date4 = (BaseCalendar.Date) date2.clone();
                int monthLength2 = jcal.getMonthLength(date4);
                date4.setDayOfMonth(1);
                jcal.normalize(date4);
                int dayOfWeek4 = dayOfWeek3 - date4.getDayOfWeek();
                if (dayOfWeek4 < 0) {
                    dayOfWeek4 += 7;
                }
                return ((monthLength2 - dayOfWeek4) + 6) / 7;
        }
    }

    private long getYearOffsetInMillis(CalendarDate calendarDate) {
        return (((jcal.getDayOfYear(calendarDate) - 1) * 86400000) + calendarDate.getTimeOfDay()) - ((long) calendarDate.getZoneOffset());
    }

    @Override // java.util.Calendar
    public Object clone() {
        JapaneseImperialCalendar japaneseImperialCalendar = (JapaneseImperialCalendar) super.clone();
        japaneseImperialCalendar.jdate = (LocalGregorianCalendar.Date) this.jdate.clone();
        japaneseImperialCalendar.originalFields = null;
        japaneseImperialCalendar.zoneOffsets = null;
        return japaneseImperialCalendar;
    }

    @Override // java.util.Calendar
    public TimeZone getTimeZone() {
        TimeZone timeZone = super.getTimeZone();
        this.jdate.setZone(timeZone);
        return timeZone;
    }

    @Override // java.util.Calendar
    public void setTimeZone(TimeZone timeZone) {
        super.setTimeZone(timeZone);
        this.jdate.setZone(timeZone);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Calendar
    public void computeFields() {
        int i = 131071;
        if (isPartiallyNormalized()) {
            int setStateFields = getSetStateFields();
            int i2 = 131071 & (~setStateFields);
            if (i2 != 0 || this.cachedFixedDate == Long.MIN_VALUE) {
                setStateFields |= computeFields(i2, 98304 & setStateFields);
            }
            i = setStateFields;
        } else {
            computeFields(131071, currentEra);
        }
        setFieldsComputed(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01ee, code lost:
        if (r4.isLeapYear() != false) goto L_0x01f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x023a, code lost:
        if (r4.isLeapYear() != false) goto L_0x01f0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int computeFields(int r23, int r24) {
        /*
        // Method dump skipped, instructions count: 786
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.JapaneseImperialCalendar.computeFields(int, int):int");
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
    @Override // java.util.Calendar
    public void computeTime() {
        int i;
        int i2;
        long j;
        if (!isLenient()) {
            if (this.originalFields == null) {
                this.originalFields = new int[17];
            }
            for (int i3 = currentEra; i3 < 17; i3++) {
                int internalGet = internalGet(i3);
                if (!isExternallySet(i3) || (internalGet >= getMinimum(i3) && internalGet <= getMaximum(i3))) {
                    this.originalFields[i3] = internalGet;
                } else {
                    throw new IllegalArgumentException(Calendar.getFieldName(i3));
                }
            }
        }
        int selectFields = selectFields();
        if (isSet(currentEra)) {
            i2 = internalGet(currentEra);
            i = isSet(1) ? internalGet(1) : 1;
        } else if (isSet(1)) {
            i2 = currentEra;
            i = internalGet(1);
        } else {
            i2 = 3;
            i = 45;
        }
        if (Calendar.isFieldSet(selectFields, 11)) {
            j = ((long) internalGet(11)) + 0;
        } else {
            j = ((long) internalGet(10)) + 0;
            if (Calendar.isFieldSet(selectFields, 9)) {
                j += (long) (internalGet(9) * 12);
            }
        }
        long internalGet2 = (((((j * 60) + ((long) internalGet(12))) * 60) + ((long) internalGet(13))) * 1000) + ((long) internalGet(14));
        long j2 = internalGet2 / 86400000;
        long j3 = internalGet2 % 86400000;
        while (j3 < 0) {
            j3 += 86400000;
            j2--;
        }
        long fixedDate = (((j2 + getFixedDate(i2, i, selectFields)) - 719163) * 86400000) + j3;
        TimeZone zone = getZone();
        if (this.zoneOffsets == null) {
            this.zoneOffsets = new int[2];
        }
        int i4 = selectFields & 98304;
        if (i4 != 98304) {
            zone.getOffsets(fixedDate - ((long) zone.getRawOffset()), this.zoneOffsets);
        }
        if (i4 != 0) {
            if (Calendar.isFieldSet(i4, 15)) {
                this.zoneOffsets[currentEra] = internalGet(15);
            }
            if (Calendar.isFieldSet(i4, 16)) {
                this.zoneOffsets[1] = internalGet(16);
            }
        }
        int[] iArr = this.zoneOffsets;
        this.time = fixedDate - ((long) (iArr[currentEra] + iArr[1]));
        int computeFields = computeFields(selectFields | getSetStateFields(), i4);
        if (!isLenient()) {
            for (int i5 = currentEra; i5 < 17; i5++) {
                if (isExternallySet(i5) && this.originalFields[i5] != internalGet(i5)) {
                    int internalGet3 = internalGet(i5);
                    int[] iArr2 = this.originalFields;
                    int[] iArr3 = this.fields;
                    System.arraycopy(iArr2, (int) currentEra, iArr3, (int) currentEra, iArr3.length);
                    throw new IllegalArgumentException(Calendar.getFieldName(i5) + "=" + internalGet3 + ", expected " + this.originalFields[i5]);
                }
            }
        }
        setFieldsNormalized(computeFields);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long getFixedDate(int r16, int r17, int r18) {
        /*
        // Method dump skipped, instructions count: 375
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.JapaneseImperialCalendar.getFixedDate(int, int, int):long");
    }

    private long getFixedDateJan1(LocalGregorianCalendar.Date date, long j) {
        date.getEra();
        if (date.getEra() != null && date.getYear() == 1) {
            for (int eraIndex = getEraIndex(date); eraIndex > 0; eraIndex--) {
                long fixedDate = gcal.getFixedDate(eras[eraIndex].getSinceDate());
                if (fixedDate <= j) {
                    return fixedDate;
                }
            }
        }
        Gregorian.Date newCalendarDate = gcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        newCalendarDate.setDate(date.getNormalizedYear(), 1, 1);
        return gcal.getFixedDate(newCalendarDate);
    }

    private long getFixedDateMonth1(LocalGregorianCalendar.Date date, long j) {
        int transitionEraIndex = getTransitionEraIndex(date);
        if (transitionEraIndex != -1) {
            long j2 = sinceFixedDates[transitionEraIndex];
            if (j2 <= j) {
                return j2;
            }
        }
        return (j - ((long) date.getDayOfMonth())) + 1;
    }

    private static LocalGregorianCalendar.Date getCalendarDate(long j) {
        LocalGregorianCalendar.Date newCalendarDate = jcal.newCalendarDate(TimeZone.NO_TIMEZONE);
        jcal.getCalendarDateFromFixedDate(newCalendarDate, j);
        return newCalendarDate;
    }

    private int monthLength(int i, int i2) {
        return CalendarUtils.isGregorianLeapYear(i2) ? GregorianCalendar.LEAP_MONTH_LENGTH[i] : GregorianCalendar.MONTH_LENGTH[i];
    }

    private static int getTransitionEraIndex(LocalGregorianCalendar.Date date) {
        int eraIndex = getEraIndex(date);
        CalendarDate sinceDate = eras[eraIndex].getSinceDate();
        if (sinceDate.getYear() == date.getNormalizedYear() && sinceDate.getMonth() == date.getMonth()) {
            return eraIndex;
        }
        Era[] eraArr = eras;
        if (eraIndex >= eraArr.length - 1) {
            return -1;
        }
        int i = eraIndex + 1;
        CalendarDate sinceDate2 = eraArr[i].getSinceDate();
        if (sinceDate2.getYear() == date.getNormalizedYear() && sinceDate2.getMonth() == date.getMonth()) {
            return i;
        }
        return -1;
    }

    private boolean isTransitionYear(int i) {
        for (int length = eras.length - 1; length > 0; length--) {
            int year = eras[length].getSinceDate().getYear();
            if (i == year) {
                return true;
            }
            if (i > year) {
                return false;
            }
        }
        return false;
    }

    private static int getEraIndex(LocalGregorianCalendar.Date date) {
        Era era = date.getEra();
        for (int length = eras.length - 1; length > 0; length--) {
            if (eras[length] == era) {
                return length;
            }
        }
        return currentEra;
    }

    private JapaneseImperialCalendar getNormalizedCalendar() {
        if (isFullyNormalized()) {
            return this;
        }
        JapaneseImperialCalendar japaneseImperialCalendar = (JapaneseImperialCalendar) clone();
        japaneseImperialCalendar.setLenient(true);
        japaneseImperialCalendar.complete();
        return japaneseImperialCalendar;
    }

    private void pinDayOfMonth(LocalGregorianCalendar.Date date) {
        int year = date.getYear();
        int dayOfMonth = date.getDayOfMonth();
        if (year != getMinimum(1)) {
            date.setDayOfMonth(1);
            jcal.normalize(date);
            int monthLength = jcal.getMonthLength(date);
            if (dayOfMonth > monthLength) {
                date.setDayOfMonth(monthLength);
            } else {
                date.setDayOfMonth(dayOfMonth);
            }
            jcal.normalize(date);
            return;
        }
        LocalGregorianCalendar.Date calendarDate = jcal.getCalendarDate(Long.MIN_VALUE, getZone());
        LocalGregorianCalendar.Date calendarDate2 = jcal.getCalendarDate(this.time, getZone());
        long timeOfDay = calendarDate2.getTimeOfDay();
        calendarDate2.addYear(400);
        calendarDate2.setMonth(date.getMonth());
        calendarDate2.setDayOfMonth(1);
        jcal.normalize(calendarDate2);
        int monthLength2 = jcal.getMonthLength(calendarDate2);
        if (dayOfMonth > monthLength2) {
            calendarDate2.setDayOfMonth(monthLength2);
        } else if (dayOfMonth < calendarDate.getDayOfMonth()) {
            calendarDate2.setDayOfMonth(calendarDate.getDayOfMonth());
        } else {
            calendarDate2.setDayOfMonth(dayOfMonth);
        }
        if (calendarDate2.getDayOfMonth() == calendarDate.getDayOfMonth() && timeOfDay < calendarDate.getTimeOfDay()) {
            calendarDate2.setDayOfMonth(Math.min(dayOfMonth + 1, monthLength2));
        }
        date.setDate(year, calendarDate2.getMonth(), calendarDate2.getDayOfMonth());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
