package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;

public class Date implements Serializable, Cloneable, Comparable {
    private static int defaultCenturyStart = 0;
    private static final BaseCalendar gcal = CalendarSystem.getGregorianCalendar();
    private static BaseCalendar jcal = null;
    private static final long serialVersionUID = 7523967970034938905L;
    private static final int[] ttb = {14, 1, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 10000, 10000, 10000, 10300, 10240, 10360, 10300, 10420, 10360, 10480, 10420};
    private static final String[] wtb = {"am", "pm", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december", "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt"};
    private transient BaseCalendar.Date cdate;
    private transient long fastTime;

    public Date() {
        this(System.currentTimeMillis());
    }

    public Date(long j) {
        this.fastTime = j;
    }

    public Date(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i + 1900;
        if (i2 >= 12) {
            i7 += i2 / 12;
            i2 %= 12;
        } else if (i2 < 0) {
            i7 += CalendarUtils.floorDivide(i2, 12);
            i2 = CalendarUtils.mod(i2, 12);
        }
        this.cdate = (BaseCalendar.Date) getCalendarSystem(i7).newCalendarDate(TimeZone.getDefaultRef());
        BaseCalendar.Date date = this.cdate;
        date.setNormalizedDate(i7, i2 + 1, i3);
        date.setTimeOfDay(i4, i5, i6, 0);
        getTimeImpl();
        this.cdate = null;
    }

    public Object clone() {
        try {
            Date date = (Date) super.clone();
            try {
                if (this.cdate == null) {
                    return date;
                }
                date.cdate = (BaseCalendar.Date) this.cdate.clone();
                return date;
            } catch (CloneNotSupportedException unused) {
                return date;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0191, code lost:
        r6 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0192, code lost:
        if (r10 < 0) goto L_0x021b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0194, code lost:
        r2 = 0;
        r0 = r20;
        r1 = r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long parse(java.lang.String r20) {
        /*
        // Method dump skipped, instructions count: 545
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Date.parse(java.lang.String):long");
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

    public void setTime(long j) {
        this.fastTime = j;
        this.cdate = null;
    }

    public boolean before(Date date) {
        return getMillisOf(this) < getMillisOf(date);
    }

    public boolean after(Date date) {
        return getMillisOf(this) > getMillisOf(date);
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

    public int compareTo(Date date) {
        int i = (getMillisOf(this) > getMillisOf(date) ? 1 : (getMillisOf(this) == getMillisOf(date) ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    public int hashCode() {
        long time = getTime();
        return ((int) time) ^ ((int) (time >> 32));
    }

    public String toString() {
        BaseCalendar.Date normalize = normalize();
        StringBuilder sb = new StringBuilder(28);
        int dayOfWeek = normalize.getDayOfWeek();
        if (dayOfWeek == 1) {
            dayOfWeek = 8;
        }
        convertToAbbr(sb, wtb[dayOfWeek]);
        sb.append(' ');
        convertToAbbr(sb, wtb[(normalize.getMonth() - 1) + 2 + 7]);
        sb.append(' ');
        CalendarUtils.sprintf0d(sb, normalize.getDayOfMonth(), 2);
        sb.append(' ');
        CalendarUtils.sprintf0d(sb, normalize.getHours(), 2);
        sb.append(':');
        CalendarUtils.sprintf0d(sb, normalize.getMinutes(), 2);
        sb.append(':');
        CalendarUtils.sprintf0d(sb, normalize.getSeconds(), 2);
        sb.append(' ');
        TimeZone zone = normalize.getZone();
        if (zone != null) {
            sb.append(zone.getDisplayName(normalize.isDaylightTime(), 0, Locale.US));
        } else {
            sb.append("GMT");
        }
        sb.append(' ');
        sb.append(normalize.getYear());
        return sb.toString();
    }

    private static final StringBuilder convertToAbbr(StringBuilder sb, String str) {
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.charAt(1));
        sb.append(str.charAt(2));
        return sb;
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
        TimeZone defaultRef = TimeZone.getDefaultRef();
        if (defaultRef != this.cdate.getZone()) {
            this.cdate.setZone(defaultRef);
            getCalendarSystem(this.cdate).getCalendarDate(this.fastTime, this.cdate);
        }
        return this.cdate;
    }

    private final BaseCalendar.Date normalize(BaseCalendar.Date date) {
        int normalizedYear = date.getNormalizedYear();
        int month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        int hours = date.getHours();
        int minutes = date.getMinutes();
        int seconds = date.getSeconds();
        int millis = date.getMillis();
        TimeZone zone = date.getZone();
        if (normalizedYear == 1582 || normalizedYear > 280000000 || normalizedYear < -280000000) {
            if (zone == null) {
                zone = TimeZone.getTimeZone("GMT");
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(zone);
            gregorianCalendar.clear();
            gregorianCalendar.set(14, millis);
            gregorianCalendar.set(normalizedYear, month - 1, dayOfMonth, hours, minutes, seconds);
            this.fastTime = gregorianCalendar.getTimeInMillis();
            return (BaseCalendar.Date) getCalendarSystem(this.fastTime).getCalendarDate(this.fastTime, zone);
        }
        BaseCalendar calendarSystem = getCalendarSystem(normalizedYear);
        if (calendarSystem != getCalendarSystem(date)) {
            date = (BaseCalendar.Date) calendarSystem.newCalendarDate(zone);
            date.setNormalizedDate(normalizedYear, month, dayOfMonth);
            date.setTimeOfDay(hours, minutes, seconds, millis);
        }
        this.fastTime = calendarSystem.getTime(date);
        BaseCalendar calendarSystem2 = getCalendarSystem(this.fastTime);
        if (calendarSystem2 == calendarSystem) {
            return date;
        }
        BaseCalendar.Date date2 = (BaseCalendar.Date) calendarSystem2.newCalendarDate(zone);
        date2.setNormalizedDate(normalizedYear, month, dayOfMonth);
        date2.setTimeOfDay(hours, minutes, seconds, millis);
        this.fastTime = calendarSystem2.getTime(date2);
        return date2;
    }

    private static final BaseCalendar getCalendarSystem(int i) {
        if (i >= 1582) {
            return gcal;
        }
        return getJulianCalendar();
    }

    private static final BaseCalendar getCalendarSystem(long j) {
        if (j >= 0 || j >= -12219292800000L - ((long) TimeZone.getDefaultRef().getOffset(j))) {
            return gcal;
        }
        return getJulianCalendar();
    }

    private static final BaseCalendar getCalendarSystem(BaseCalendar.Date date) {
        if (jcal == null) {
            return gcal;
        }
        if (date.getEra() != null) {
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

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeLong(getTimeImpl());
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readLong();
        throw null;
    }
}
