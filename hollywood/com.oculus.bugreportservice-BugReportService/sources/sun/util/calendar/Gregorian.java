package sun.util.calendar;

import java.util.TimeZone;
import sun.util.calendar.BaseCalendar;

public class Gregorian extends BaseCalendar {

    /* access modifiers changed from: package-private */
    public static class Date extends BaseCalendar.Date {
        protected Date() {
        }

        protected Date(TimeZone timeZone) {
            super(timeZone);
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public int getNormalizedYear() {
            return getYear();
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public void setNormalizedYear(int i) {
            setYear(i);
        }
    }

    Gregorian() {
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
}
