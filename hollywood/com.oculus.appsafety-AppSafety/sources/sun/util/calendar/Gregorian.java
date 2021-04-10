package sun.util.calendar;

import java.util.TimeZone;
import sun.util.calendar.BaseCalendar;

public class Gregorian extends BaseCalendar {

    /* access modifiers changed from: package-private */
    public static class Date extends BaseCalendar.Date {
        protected Date() {
        }

        protected Date(TimeZone zone) {
            super(zone);
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public int getNormalizedYear() {
            return getYear();
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public void setNormalizedYear(int normalizedYear) {
            setYear(normalizedYear);
        }
    }

    Gregorian() {
    }

    @Override // sun.util.calendar.CalendarSystem
    public String getName() {
        return "gregorian";
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate() {
        return getCalendarDate(System.currentTimeMillis(), (CalendarDate) newCalendarDate());
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate(long millis) {
        return getCalendarDate(millis, (CalendarDate) newCalendarDate());
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate(long millis, CalendarDate date) {
        return (Date) super.getCalendarDate(millis, date);
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate(long millis, TimeZone zone) {
        return getCalendarDate(millis, (CalendarDate) newCalendarDate(zone));
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate() {
        return new Date();
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate(TimeZone zone) {
        return new Date(zone);
    }
}
