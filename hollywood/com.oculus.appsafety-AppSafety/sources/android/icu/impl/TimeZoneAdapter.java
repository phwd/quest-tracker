package android.icu.impl;

import java.util.Date;
import java.util.TimeZone;

public class TimeZoneAdapter extends TimeZone {
    static final long serialVersionUID = -2040072218820018557L;
    private android.icu.util.TimeZone zone;

    public static TimeZone wrap(android.icu.util.TimeZone tz) {
        return new TimeZoneAdapter(tz);
    }

    public android.icu.util.TimeZone unwrap() {
        return this.zone;
    }

    public TimeZoneAdapter(android.icu.util.TimeZone zone2) {
        this.zone = zone2;
        super.setID(zone2.getID());
    }

    @Override // java.util.TimeZone
    public void setID(String ID) {
        super.setID(ID);
        this.zone.setID(ID);
    }

    @Override // java.util.TimeZone
    public boolean hasSameRules(TimeZone other) {
        return (other instanceof TimeZoneAdapter) && this.zone.hasSameRules(((TimeZoneAdapter) other).zone);
    }

    @Override // java.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis) {
        return this.zone.getOffset(era, year, month, day, dayOfWeek, millis);
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.zone.getRawOffset();
    }

    @Override // java.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        this.zone.setRawOffset(offsetMillis);
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return this.zone.useDaylightTime();
    }

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return this.zone.inDaylightTime(date);
    }

    @Override // java.util.TimeZone
    public Object clone() {
        return new TimeZoneAdapter((android.icu.util.TimeZone) this.zone.clone());
    }

    public synchronized int hashCode() {
        return this.zone.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeZoneAdapter)) {
            return false;
        }
        return this.zone.equals(((TimeZoneAdapter) obj).zone);
    }

    public String toString() {
        return "TimeZoneAdapter: " + this.zone.toString();
    }
}
