package sun.util.calendar;

import java.util.TimeZone;
import sun.util.calendar.BaseCalendar;

public class JulianCalendar extends BaseCalendar {
    private static final Era[] eras = {new Era("BeforeCommonEra", "B.C.E.", Long.MIN_VALUE, false), new Era("CommonEra", "C.E.", -62135709175808L, true)};

    /* access modifiers changed from: private */
    public static class Date extends BaseCalendar.Date {
        protected Date() {
            setCache(1, -1, 365);
        }

        protected Date(TimeZone timeZone) {
            super(timeZone);
            setCache(1, -1, 365);
        }

        /* access modifiers changed from: protected */
        public void setKnownEra(Era era) {
            super.setEra(era);
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public int getNormalizedYear() {
            if (getEra() == JulianCalendar.eras[0]) {
                return 1 - getYear();
            }
            return getYear();
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public void setNormalizedYear(int i) {
            if (i <= 0) {
                setYear(1 - i);
                setKnownEra(JulianCalendar.eras[0]);
                return;
            }
            setYear(i);
            setKnownEra(JulianCalendar.eras[1]);
        }

        @Override // sun.util.calendar.CalendarDate
        public String toString() {
            String abbreviation;
            String calendarDate = super.toString();
            String substring = calendarDate.substring(calendarDate.indexOf(84));
            StringBuffer stringBuffer = new StringBuffer();
            Era era = getEra();
            if (!(era == null || (abbreviation = era.getAbbreviation()) == null)) {
                stringBuffer.append(abbreviation);
                stringBuffer.append(' ');
            }
            stringBuffer.append(getYear());
            stringBuffer.append('-');
            CalendarUtils.sprintf0d(stringBuffer, getMonth(), 2);
            stringBuffer.append('-');
            CalendarUtils.sprintf0d(stringBuffer, getDayOfMonth(), 2);
            stringBuffer.append(substring);
            return stringBuffer.toString();
        }
    }

    JulianCalendar() {
        setEras(eras);
    }

    @Override // sun.util.calendar.AbstractCalendar
    public Date getCalendarDate() {
        return getCalendarDate(System.currentTimeMillis(), (CalendarDate) newCalendarDate());
    }

    @Override // sun.util.calendar.AbstractCalendar
    public Date getCalendarDate(long j, CalendarDate calendarDate) {
        super.getCalendarDate(j, calendarDate);
        return (Date) calendarDate;
    }

    @Override // sun.util.calendar.AbstractCalendar
    public Date getCalendarDate(long j, TimeZone timeZone) {
        return getCalendarDate(j, (CalendarDate) newCalendarDate(timeZone));
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate() {
        return new Date();
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate(TimeZone timeZone) {
        return new Date(timeZone);
    }

    @Override // sun.util.calendar.BaseCalendar
    public long getFixedDate(int i, int i2, int i3, BaseCalendar.Date date) {
        long j;
        long j2;
        boolean z = true;
        if (!(i2 == 1 && i3 == 1)) {
            z = false;
        }
        if (date == null || !date.hit(i)) {
            long j3 = (long) i;
            long j4 = j3 - 1;
            long j5 = ((365 * j4) - 2) + ((long) i3);
            if (j3 > 0) {
                j = j5 + (j4 / 4);
            } else {
                j = j5 + CalendarUtils.floorDivide(j4, 4);
            }
            if (i2 > 0) {
                j2 = ((((long) i2) * 367) - 362) / 12;
            } else {
                j2 = CalendarUtils.floorDivide((((long) i2) * 367) - 362, 12);
            }
            long j6 = j + j2;
            if (i2 > 2) {
                j6 -= CalendarUtils.isJulianLeapYear(i) ? 1 : 2;
            }
            if (date != null && z) {
                date.setCache(i, j6, CalendarUtils.isJulianLeapYear(i) ? 366 : 365);
            }
            return j6;
        } else if (z) {
            return date.getCachedJan1();
        } else {
            return (date.getCachedJan1() + getDayOfYear(i, i2, i3)) - 1;
        }
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.BaseCalendar
    public void getCalendarDateFromFixedDate(CalendarDate calendarDate, long j) {
        long j2;
        int i;
        Date date = (Date) calendarDate;
        long j3 = ((j - -1) * 4) + 1464;
        if (j3 >= 0) {
            j2 = j3 / 1461;
        } else {
            j2 = CalendarUtils.floorDivide(j3, 1461);
        }
        int i2 = (int) j2;
        int fixedDate = (int) (j - getFixedDate(i2, 1, 1, date));
        boolean isJulianLeapYear = CalendarUtils.isJulianLeapYear(i2);
        if (j >= getFixedDate(i2, 3, 1, date)) {
            fixedDate += isJulianLeapYear ? 1 : 2;
        }
        int i3 = (fixedDate * 12) + 373;
        if (i3 > 0) {
            i = i3 / 367;
        } else {
            i = CalendarUtils.floorDivide(i3, 367);
        }
        int dayOfWeekFromFixedDate = BaseCalendar.getDayOfWeekFromFixedDate(j);
        date.setNormalizedYear(i2);
        date.setMonth(i);
        date.setDayOfMonth(((int) (j - getFixedDate(i2, i, 1, date))) + 1);
        date.setDayOfWeek(dayOfWeekFromFixedDate);
        date.setLeapYear(isJulianLeapYear);
        date.setNormalized(true);
    }

    @Override // sun.util.calendar.BaseCalendar
    public int getDayOfWeek(CalendarDate calendarDate) {
        return BaseCalendar.getDayOfWeekFromFixedDate(getFixedDate(calendarDate));
    }

    /* access modifiers changed from: package-private */
    @Override // sun.util.calendar.BaseCalendar
    public boolean isLeapYear(int i) {
        return CalendarUtils.isJulianLeapYear(i);
    }
}
