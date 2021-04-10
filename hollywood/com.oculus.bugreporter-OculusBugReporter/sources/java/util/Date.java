package java.util;

import dalvik.system.VMRuntime;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.Instant;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;

public class Date implements Serializable, Cloneable, Comparable<Date> {
    private static int defaultCenturyStart = 0;
    private static final BaseCalendar gcal = CalendarSystem.getGregorianCalendar();
    private static BaseCalendar jcal = null;
    private static final long serialVersionUID = 7523967970034938905L;
    private static final int[] ttb = {14, 1, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, VMRuntime.SDK_VERSION_CUR_DEVELOPMENT, VMRuntime.SDK_VERSION_CUR_DEVELOPMENT, VMRuntime.SDK_VERSION_CUR_DEVELOPMENT, 10300, 10240, 10360, 10300, 10420, 10360, 10480, 10420};
    private static final String[] wtb = {"am", "pm", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december", "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt"};
    private transient BaseCalendar.Date cdate;
    private transient long fastTime;

    public Date() {
        this(System.currentTimeMillis());
    }

    public Date(long date) {
        this.fastTime = date;
    }

    @Deprecated
    public Date(int year, int month, int date) {
        this(year, month, date, 0, 0, 0);
    }

    @Deprecated
    public Date(int year, int month, int date, int hrs, int min) {
        this(year, month, date, hrs, min, 0);
    }

    @Deprecated
    public Date(int year, int month, int date, int hrs, int min, int sec) {
        int y = year + 1900;
        if (month >= 12) {
            y += month / 12;
            month %= 12;
        } else if (month < 0) {
            y += CalendarUtils.floorDivide(month, 12);
            month = CalendarUtils.mod(month, 12);
        }
        this.cdate = (BaseCalendar.Date) getCalendarSystem(y).newCalendarDate(TimeZone.getDefaultRef());
        this.cdate.setNormalizedDate(y, month + 1, date).setTimeOfDay(hrs, min, sec, 0);
        getTimeImpl();
        this.cdate = null;
    }

    @Deprecated
    public Date(String s) {
        this(parse(s));
    }

    public Object clone() {
        Date d = null;
        try {
            d = (Date) super.clone();
            if (this.cdate != null) {
                d.cdate = (BaseCalendar.Date) this.cdate.clone();
            }
        } catch (CloneNotSupportedException e) {
        }
        return d;
    }

    @Deprecated
    public static long UTC(int year, int month, int date, int hrs, int min, int sec) {
        int y = year + 1900;
        if (month >= 12) {
            y += month / 12;
            month %= 12;
        } else if (month < 0) {
            y += CalendarUtils.floorDivide(month, 12);
            month = CalendarUtils.mod(month, 12);
        }
        BaseCalendar.Date udate = (BaseCalendar.Date) getCalendarSystem(y).newCalendarDate(null);
        udate.setNormalizedDate(y, month + 1, date).setTimeOfDay(hrs, min, sec, 0);
        Date d = new Date(0);
        d.normalize(udate);
        return d.fastTime;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007a, code lost:
        if (r10 != Integer.MIN_VALUE) goto L_0x00ee;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long parse(java.lang.String r24) {
        /*
        // Method dump skipped, instructions count: 701
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Date.parse(java.lang.String):long");
    }

    @Deprecated
    public int getYear() {
        return normalize().getYear() - 1900;
    }

    @Deprecated
    public void setYear(int year) {
        getCalendarDate().setNormalizedYear(year + 1900);
    }

    @Deprecated
    public int getMonth() {
        return normalize().getMonth() - 1;
    }

    @Deprecated
    public void setMonth(int month) {
        int y = 0;
        if (month >= 12) {
            y = month / 12;
            month %= 12;
        } else if (month < 0) {
            y = CalendarUtils.floorDivide(month, 12);
            month = CalendarUtils.mod(month, 12);
        }
        BaseCalendar.Date d = getCalendarDate();
        if (y != 0) {
            d.setNormalizedYear(d.getNormalizedYear() + y);
        }
        d.setMonth(month + 1);
    }

    @Deprecated
    public int getDate() {
        return normalize().getDayOfMonth();
    }

    @Deprecated
    public void setDate(int date) {
        getCalendarDate().setDayOfMonth(date);
    }

    @Deprecated
    public int getDay() {
        return normalize().getDayOfWeek() - 1;
    }

    @Deprecated
    public int getHours() {
        return normalize().getHours();
    }

    @Deprecated
    public void setHours(int hours) {
        getCalendarDate().setHours(hours);
    }

    @Deprecated
    public int getMinutes() {
        return normalize().getMinutes();
    }

    @Deprecated
    public void setMinutes(int minutes) {
        getCalendarDate().setMinutes(minutes);
    }

    @Deprecated
    public int getSeconds() {
        return normalize().getSeconds();
    }

    @Deprecated
    public void setSeconds(int seconds) {
        getCalendarDate().setSeconds(seconds);
    }

    public long getTime() {
        return getTimeImpl();
    }

    private final long getTimeImpl() {
        BaseCalendar.Date date = this.cdate;
        if (date != null && !date.isNormalized()) {
            normalize();
        }
        return this.fastTime;
    }

    public void setTime(long time) {
        this.fastTime = time;
        this.cdate = null;
    }

    public boolean before(Date when) {
        return getMillisOf(this) < getMillisOf(when);
    }

    public boolean after(Date when) {
        return getMillisOf(this) > getMillisOf(when);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Date) && getTime() == ((Date) obj).getTime();
    }

    static final long getMillisOf(Date date) {
        BaseCalendar.Date date2 = date.cdate;
        if (date2 == null || date2.isNormalized()) {
            return date.fastTime;
        }
        return gcal.getTime((BaseCalendar.Date) date.cdate.clone());
    }

    public int compareTo(Date anotherDate) {
        long thisTime = getMillisOf(this);
        long anotherTime = getMillisOf(anotherDate);
        if (thisTime < anotherTime) {
            return -1;
        }
        return thisTime == anotherTime ? 0 : 1;
    }

    public int hashCode() {
        long ht = getTime();
        return ((int) ht) ^ ((int) (ht >> 32));
    }

    public String toString() {
        BaseCalendar.Date date = normalize();
        StringBuilder sb = new StringBuilder(28);
        int index = date.getDayOfWeek();
        if (index == 1) {
            index = 8;
        }
        convertToAbbr(sb, wtb[index]).append(' ');
        convertToAbbr(sb, wtb[(date.getMonth() - 1) + 2 + 7]).append(' ');
        CalendarUtils.sprintf0d(sb, date.getDayOfMonth(), 2).append(' ');
        CalendarUtils.sprintf0d(sb, date.getHours(), 2).append(':');
        CalendarUtils.sprintf0d(sb, date.getMinutes(), 2).append(':');
        CalendarUtils.sprintf0d(sb, date.getSeconds(), 2).append(' ');
        TimeZone zi = date.getZone();
        if (zi != null) {
            sb.append(zi.getDisplayName(date.isDaylightTime(), 0, Locale.US));
        } else {
            sb.append("GMT");
        }
        sb.append(' ');
        sb.append(date.getYear());
        return sb.toString();
    }

    private static final StringBuilder convertToAbbr(StringBuilder sb, String name) {
        sb.append(Character.toUpperCase(name.charAt(0)));
        sb.append(name.charAt(1));
        sb.append(name.charAt(2));
        return sb;
    }

    @Deprecated
    public String toLocaleString() {
        return DateFormat.getDateTimeInstance().format(this);
    }

    @Deprecated
    public String toGMTString() {
        BaseCalendar.Date date = (BaseCalendar.Date) getCalendarSystem(getTime()).getCalendarDate(getTime(), (TimeZone) null);
        StringBuilder sb = new StringBuilder(32);
        CalendarUtils.sprintf0d(sb, date.getDayOfMonth(), 1).append(' ');
        convertToAbbr(sb, wtb[(date.getMonth() - 1) + 2 + 7]).append(' ');
        sb.append(date.getYear());
        sb.append(' ');
        CalendarUtils.sprintf0d(sb, date.getHours(), 2).append(':');
        CalendarUtils.sprintf0d(sb, date.getMinutes(), 2).append(':');
        CalendarUtils.sprintf0d(sb, date.getSeconds(), 2);
        sb.append(" GMT");
        return sb.toString();
    }

    @Deprecated
    public int getTimezoneOffset() {
        int zoneOffset;
        if (this.cdate == null) {
            GregorianCalendar cal = new GregorianCalendar(this.fastTime);
            zoneOffset = cal.get(15) + cal.get(16);
        } else {
            normalize();
            zoneOffset = this.cdate.getZoneOffset();
        }
        return (-zoneOffset) / 60000;
    }

    private final BaseCalendar.Date getCalendarDate() {
        if (this.cdate == null) {
            this.cdate = (BaseCalendar.Date) getCalendarSystem(this.fastTime).getCalendarDate(this.fastTime, TimeZone.getDefaultRef());
        }
        return this.cdate;
    }

    private final BaseCalendar.Date normalize() {
        BaseCalendar.Date date = this.cdate;
        if (date == null) {
            this.cdate = (BaseCalendar.Date) getCalendarSystem(this.fastTime).getCalendarDate(this.fastTime, TimeZone.getDefaultRef());
            return this.cdate;
        }
        if (!date.isNormalized()) {
            this.cdate = normalize(this.cdate);
        }
        TimeZone tz = TimeZone.getDefaultRef();
        if (tz != this.cdate.getZone()) {
            this.cdate.setZone(tz);
            getCalendarSystem(this.cdate).getCalendarDate(this.fastTime, this.cdate);
        }
        return this.cdate;
    }

    private final BaseCalendar.Date normalize(BaseCalendar.Date date) {
        TimeZone tz;
        BaseCalendar.Date date2;
        int y = date.getNormalizedYear();
        int m = date.getMonth();
        int d = date.getDayOfMonth();
        int hh = date.getHours();
        int mm = date.getMinutes();
        int ss = date.getSeconds();
        int ms = date.getMillis();
        TimeZone tz2 = date.getZone();
        if (y == 1582 || y > 280000000 || y < -280000000) {
            if (tz2 == null) {
                tz = TimeZone.getTimeZone("GMT");
            } else {
                tz = tz2;
            }
            GregorianCalendar gc = new GregorianCalendar(tz);
            gc.clear();
            gc.set(14, ms);
            gc.set(y, m - 1, d, hh, mm, ss);
            this.fastTime = gc.getTimeInMillis();
            return (BaseCalendar.Date) getCalendarSystem(this.fastTime).getCalendarDate(this.fastTime, tz);
        }
        BaseCalendar cal = getCalendarSystem(y);
        if (cal != getCalendarSystem(date)) {
            date2 = (BaseCalendar.Date) cal.newCalendarDate(tz2);
            date2.setNormalizedDate(y, m, d).setTimeOfDay(hh, mm, ss, ms);
        } else {
            date2 = date;
        }
        this.fastTime = cal.getTime(date2);
        BaseCalendar ncal = getCalendarSystem(this.fastTime);
        if (ncal == cal) {
            return date2;
        }
        BaseCalendar.Date date3 = (BaseCalendar.Date) ncal.newCalendarDate(tz2);
        date3.setNormalizedDate(y, m, d).setTimeOfDay(hh, mm, ss, ms);
        this.fastTime = ncal.getTime(date3);
        return date3;
    }

    private static final BaseCalendar getCalendarSystem(int year) {
        if (year >= 1582) {
            return gcal;
        }
        return getJulianCalendar();
    }

    private static final BaseCalendar getCalendarSystem(long utc) {
        if (utc >= 0 || utc >= -12219292800000L - ((long) TimeZone.getDefaultRef().getOffset(utc))) {
            return gcal;
        }
        return getJulianCalendar();
    }

    private static final BaseCalendar getCalendarSystem(BaseCalendar.Date cdate2) {
        if (jcal == null) {
            return gcal;
        }
        if (cdate2.getEra() != null) {
            return jcal;
        }
        return gcal;
    }

    private static final synchronized BaseCalendar getJulianCalendar() {
        BaseCalendar baseCalendar;
        synchronized (Date.class) {
            if (jcal == null) {
                jcal = (BaseCalendar) CalendarSystem.forName("julian");
            }
            baseCalendar = jcal;
        }
        return baseCalendar;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeLong(getTimeImpl());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.fastTime = s.readLong();
    }

    public static Date from(Instant instant) {
        try {
            return new Date(instant.toEpochMilli());
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public Instant toInstant() {
        return Instant.ofEpochMilli(getTime());
    }
}
