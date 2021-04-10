package sun.util.calendar;

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
    public int getYear() {
        return this.date.getYear();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setYear(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate addYear(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public boolean isLeapYear() {
        return this.date.isLeapYear();
    }

    /* access modifiers changed from: package-private */
    @Override // sun.util.calendar.CalendarDate
    public void setLeapYear(boolean z) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getMonth() {
        return this.date.getMonth();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setMonth(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setDayOfMonth(int i) {
        unsupported();
        throw null;
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
    public CalendarDate setHours(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getMinutes() {
        return this.date.getMinutes();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setMinutes(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getSeconds() {
        return this.date.getSeconds();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setSeconds(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getMillis() {
        return this.date.getMillis();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setMillis(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public long getTimeOfDay() {
        return this.date.getTimeOfDay();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setDate(int i, int i2, int i3) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setTimeOfDay(int i, int i2, int i3, int i4) {
        unsupported();
        throw null;
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setTimeOfDay(long j) {
        unsupported();
        throw null;
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
    public boolean isDaylightTime() {
        return this.date.isDaylightTime();
    }

    @Override // sun.util.calendar.CalendarDate
    public TimeZone getZone() {
        return this.date.getZone();
    }

    @Override // sun.util.calendar.CalendarDate
    public CalendarDate setZone(TimeZone timeZone) {
        unsupported();
        throw null;
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
    public void setDayOfWeek(int i) {
        unsupported();
        throw null;
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setNormalized(boolean z) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.CalendarDate
    public int getZoneOffset() {
        return this.date.getZoneOffset();
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setZoneOffset(int i) {
        unsupported();
        throw null;
    }

    /* access modifiers changed from: protected */
    @Override // sun.util.calendar.CalendarDate
    public void setDaylightSaving(int i) {
        unsupported();
        throw null;
    }

    @Override // sun.util.calendar.BaseCalendar.Date
    public int getNormalizedYear() {
        return this.date.getNormalizedYear();
    }

    @Override // sun.util.calendar.BaseCalendar.Date
    public void setNormalizedYear(int i) {
        unsupported();
        throw null;
    }

    private void unsupported() {
        throw new UnsupportedOperationException();
    }
}
