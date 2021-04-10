package sun.util.calendar;

import java.util.Locale;
import java.util.TimeZone;
import sun.util.calendar.BaseCalendar;

/* access modifiers changed from: package-private */
public class ImmutableGregorianDate extends BaseCalendar.Date {
    private final BaseCalendar.Date date;

    ImmutableGregorianDate(BaseCalendar.Date date2) {
        if (date2 != null) {
            this.date = date2;
            return;
        }
        throw new NullPointerException();
    }

    @Override // sun.util.calendar.CalendarDate
    public Era getEra() {
        return this.date.getEra();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setEra(Era era) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getYear() {
        return this.date.getYear();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setYear(int year) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addYear(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean isLeapYear() {
        return this.date.isLeapYear();
    }

    /* access modifiers changed from: package-private */
    @Override // sun.util.calendar.CalendarDate
    public void setLeapYear(boolean leapYear) {
        unsupported();
    }

    @Override // sun.util.calendar.CalendarDate
    public int getMonth() {
        return this.date.getMonth();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setMonth(int month) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addMonth(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setDayOfMonth(int date2) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addDayOfMonth(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    @Override // sun.util.calendar.CalendarDate
    public int getHours() {
        return this.date.getHours();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setHours(int hours) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addHours(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getMinutes() {
        return this.date.getMinutes();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setMinutes(int minutes) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addMinutes(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getSeconds() {
        return this.date.getSeconds();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setSeconds(int seconds) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addSeconds(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getMillis() {
        return this.date.getMillis();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setMillis(int millis) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addMillis(int n) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public long getTimeOfDay() {
        return this.date.getTimeOfDay();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setDate(int year, int month, int dayOfMonth) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addDate(int year, int month, int dayOfMonth) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setTimeOfDay(int hours, int minutes, int seconds, int millis) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addTimeOfDay(int hours, int minutes, int seconds, int millis) {
        unsupported();
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setTimeOfDay(long fraction) {
        unsupported();
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean isNormalized() {
        return this.date.isNormalized();
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean isStandardTime() {
        return this.date.isStandardTime();
    }

    @Override // sun.util.calendar.CalendarDate
    public void setStandardTime(boolean standardTime) {
        unsupported();
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean isDaylightTime() {
        return this.date.isDaylightTime();
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setLocale(Locale loc) {
        unsupported();
    }

    @Override // sun.util.calendar.CalendarDate
    public TimeZone getZone() {
        return this.date.getZone();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setZone(TimeZone zoneinfo) {
        unsupported();
        return this;
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean isSameDate(CalendarDate date2) {
        return date2.isSameDate(date2);
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImmutableGregorianDate)) {
            return false;
        }
        return this.date.equals(((ImmutableGregorianDate) obj).date);
    }

    @Override // sun.util.calendar.CalendarDate
    public int hashCode() {
        return this.date.hashCode();
    }

    @Override // sun.util.calendar.CalendarDate
    public Object clone() {
        return super.clone();
    }

    @Override // sun.util.calendar.CalendarDate
    public String toString() {
        return this.date.toString();
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setDayOfWeek(int dayOfWeek) {
        unsupported();
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setNormalized(boolean normalized) {
        unsupported();
    }

    @Override // sun.util.calendar.CalendarDate
    public int getZoneOffset() {
        return this.date.getZoneOffset();
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setZoneOffset(int offset) {
        unsupported();
    }

    @Override // sun.util.calendar.CalendarDate
    public int getDaylightSaving() {
        return this.date.getDaylightSaving();
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setDaylightSaving(int daylightSaving) {
        unsupported();
    }

    @Override // sun.util.calendar.BaseCalendar.Date
    public int getNormalizedYear() {
        return this.date.getNormalizedYear();
    }

    @Override // sun.util.calendar.BaseCalendar.Date
    public void setNormalizedYear(int normalizedYear) {
        unsupported();
    }

    private void unsupported() {
        throw new UnsupportedOperationException();
    }
}
