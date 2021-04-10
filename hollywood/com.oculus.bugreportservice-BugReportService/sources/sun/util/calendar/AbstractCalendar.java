package sun.util.calendar;

import java.util.TimeZone;
import libcore.util.ZoneInfo;

public abstract class AbstractCalendar extends CalendarSystem {
    private Era[] eras;

    /* access modifiers changed from: protected */
    public abstract void getCalendarDateFromFixedDate(CalendarDate calendarDate, long j);

    /* access modifiers changed from: protected */
    public abstract long getFixedDate(CalendarDate calendarDate);

    /* access modifiers changed from: protected */
    public abstract boolean isLeapYear(CalendarDate calendarDate);

    protected AbstractCalendar() {
    }

    public Era[] getEras() {
        Era[] eraArr = this.eras;
        if (eraArr == null) {
            return null;
        }
        Era[] eraArr2 = new Era[eraArr.length];
        System.arraycopy(eraArr, 0, eraArr2, 0, eraArr.length);
        return eraArr2;
    }

    /* access modifiers changed from: protected */
    public void setEras(Era[] eraArr) {
        this.eras = eraArr;
    }

    public CalendarDate getCalendarDate() {
        return getCalendarDate(System.currentTimeMillis(), newCalendarDate());
    }

    public CalendarDate getCalendarDate(long j, TimeZone timeZone) {
        return getCalendarDate(j, newCalendarDate(timeZone));
    }

    public CalendarDate getCalendarDate(long j, CalendarDate calendarDate) {
        long j2;
        int i;
        int i2;
        TimeZone zone = calendarDate.getZone();
        int i3 = 0;
        if (zone != null) {
            int[] iArr = new int[2];
            if (zone instanceof ZoneInfo) {
                i3 = ((ZoneInfo) zone).getOffsetsByUtcTime(j, iArr);
            } else {
                int offset = zone.getOffset(j);
                iArr[0] = zone.getRawOffset();
                iArr[1] = offset - iArr[0];
                i3 = offset;
            }
            j2 = (long) (i3 / 86400000);
            i2 = i3 % 86400000;
            i = iArr[1];
        } else {
            j2 = 0;
            i2 = 0;
            i = 0;
        }
        calendarDate.setZoneOffset(i3);
        calendarDate.setDaylightSaving(i);
        long j3 = j2 + (j / 86400000);
        int i4 = i2 + ((int) (j % 86400000));
        if (i4 >= 86400000) {
            i4 -= 86400000;
            j3++;
        } else {
            while (i4 < 0) {
                i4 += 86400000;
                j3--;
            }
        }
        getCalendarDateFromFixedDate(calendarDate, j3 + 719163);
        setTimeOfDay(calendarDate, i4);
        calendarDate.setLeapYear(isLeapYear(calendarDate));
        calendarDate.setNormalized(true);
        return calendarDate;
    }

    public long getTime(CalendarDate calendarDate) {
        int i;
        long fixedDate = ((getFixedDate(calendarDate) - 719163) * 86400000) + getTimeOfDay(calendarDate);
        TimeZone zone = calendarDate.getZone();
        if (zone == null) {
            i = 0;
        } else if (calendarDate.isNormalized()) {
            return fixedDate - ((long) calendarDate.getZoneOffset());
        } else {
            i = calendarDate.isStandardTime() ? zone.getOffset(fixedDate - ((long) zone.getRawOffset())) : zone.getOffset(fixedDate - ((long) zone.getRawOffset()));
        }
        long j = fixedDate - ((long) i);
        getCalendarDate(j, calendarDate);
        return j;
    }

    /* access modifiers changed from: protected */
    public long getTimeOfDay(CalendarDate calendarDate) {
        long timeOfDay = calendarDate.getTimeOfDay();
        if (timeOfDay != Long.MIN_VALUE) {
            return timeOfDay;
        }
        long timeOfDayValue = getTimeOfDayValue(calendarDate);
        calendarDate.setTimeOfDay(timeOfDayValue);
        return timeOfDayValue;
    }

    public long getTimeOfDayValue(CalendarDate calendarDate) {
        return (((((((long) calendarDate.getHours()) * 60) + ((long) calendarDate.getMinutes())) * 60) + ((long) calendarDate.getSeconds())) * 1000) + ((long) calendarDate.getMillis());
    }

    public CalendarDate setTimeOfDay(CalendarDate calendarDate, int i) {
        if (i >= 0) {
            boolean isNormalized = calendarDate.isNormalized();
            int i2 = i / 3600000;
            int i3 = i % 3600000;
            int i4 = i3 / 60000;
            int i5 = i3 % 60000;
            calendarDate.setHours(i2);
            calendarDate.setMinutes(i4);
            calendarDate.setSeconds(i5 / 1000);
            calendarDate.setMillis(i5 % 1000);
            calendarDate.setTimeOfDay((long) i);
            if (i2 < 24 && isNormalized) {
                calendarDate.setNormalized(isNormalized);
            }
            return calendarDate;
        }
        throw new IllegalArgumentException();
    }

    public CalendarDate getNthDayOfWeek(int i, int i2, CalendarDate calendarDate) {
        long j;
        long j2;
        CalendarDate calendarDate2 = (CalendarDate) calendarDate.clone();
        normalize(calendarDate2);
        long fixedDate = getFixedDate(calendarDate2);
        if (i > 0) {
            j2 = (long) (i * 7);
            j = getDayOfWeekDateBefore(fixedDate, i2);
        } else {
            j2 = (long) (i * 7);
            j = getDayOfWeekDateAfter(fixedDate, i2);
        }
        getCalendarDateFromFixedDate(calendarDate2, j2 + j);
        return calendarDate2;
    }

    static long getDayOfWeekDateBefore(long j, int i) {
        return getDayOfWeekDateOnOrBefore(j - 1, i);
    }

    static long getDayOfWeekDateAfter(long j, int i) {
        return getDayOfWeekDateOnOrBefore(j + 7, i);
    }

    public static long getDayOfWeekDateOnOrBefore(long j, int i) {
        long mod;
        long j2 = j - ((long) (i - 1));
        if (j2 >= 0) {
            mod = j2 % 7;
        } else {
            mod = CalendarUtils.mod(j2, 7);
        }
        return j - mod;
    }

    public boolean validateTime(CalendarDate calendarDate) {
        int minutes;
        int seconds;
        int millis;
        int hours = calendarDate.getHours();
        if (hours < 0 || hours >= 24 || (minutes = calendarDate.getMinutes()) < 0 || minutes >= 60 || (seconds = calendarDate.getSeconds()) < 0 || seconds >= 60 || (millis = calendarDate.getMillis()) < 0 || millis >= 1000) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int normalizeTime(CalendarDate calendarDate) {
        long j;
        long timeOfDay = getTimeOfDay(calendarDate);
        if (timeOfDay >= 86400000) {
            j = timeOfDay / 86400000;
            timeOfDay %= 86400000;
        } else if (timeOfDay < 0) {
            j = CalendarUtils.floorDivide(timeOfDay, 86400000);
            if (j != 0) {
                timeOfDay -= 86400000 * j;
            }
        } else {
            j = 0;
        }
        if (j != 0) {
            calendarDate.setTimeOfDay(timeOfDay);
        }
        calendarDate.setMillis((int) (timeOfDay % 1000));
        long j2 = timeOfDay / 1000;
        calendarDate.setSeconds((int) (j2 % 60));
        long j3 = j2 / 60;
        calendarDate.setMinutes((int) (j3 % 60));
        calendarDate.setHours((int) (j3 / 60));
        return (int) j;
    }
}
