package sun.util.calendar;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class CalendarSystem {
    private static final Gregorian GREGORIAN_INSTANCE = new Gregorian();
    private static final ConcurrentMap<String, CalendarSystem> calendars = new ConcurrentHashMap();
    private static final Map<String, Class<?>> names = new HashMap();

    public abstract CalendarDate getCalendarDate();

    public abstract CalendarDate getCalendarDate(long j);

    public abstract CalendarDate getCalendarDate(long j, TimeZone timeZone);

    public abstract CalendarDate getCalendarDate(long j, CalendarDate calendarDate);

    public abstract Era getEra(String str);

    public abstract Era[] getEras();

    public abstract int getMonthLength(CalendarDate calendarDate);

    public abstract String getName();

    public abstract CalendarDate getNthDayOfWeek(int i, int i2, CalendarDate calendarDate);

    public abstract long getTime(CalendarDate calendarDate);

    public abstract int getWeekLength();

    public abstract int getYearLength(CalendarDate calendarDate);

    public abstract int getYearLengthInMonths(CalendarDate calendarDate);

    public abstract CalendarDate newCalendarDate();

    public abstract CalendarDate newCalendarDate(TimeZone timeZone);

    public abstract boolean normalize(CalendarDate calendarDate);

    public abstract void setEra(CalendarDate calendarDate, String str);

    public abstract CalendarDate setTimeOfDay(CalendarDate calendarDate, int i);

    public abstract boolean validate(CalendarDate calendarDate);

    static {
        names.put("gregorian", Gregorian.class);
        names.put("japanese", LocalGregorianCalendar.class);
        names.put("julian", JulianCalendar.class);
    }

    public static Gregorian getGregorianCalendar() {
        return GREGORIAN_INSTANCE;
    }

    public static CalendarSystem forName(String calendarName) {
        CalendarSystem cal;
        if ("gregorian".equals(calendarName)) {
            return GREGORIAN_INSTANCE;
        }
        CalendarSystem cal2 = calendars.get(calendarName);
        if (cal2 != null) {
            return cal2;
        }
        Class<?> calendarClass = names.get(calendarName);
        if (calendarClass == null) {
            return null;
        }
        if (calendarClass.isAssignableFrom(LocalGregorianCalendar.class)) {
            cal = LocalGregorianCalendar.getLocalGregorianCalendar(calendarName);
        } else {
            try {
                cal = (CalendarSystem) calendarClass.newInstance();
            } catch (Exception e) {
                throw new InternalError(e);
            }
        }
        if (cal == null) {
            return null;
        }
        CalendarSystem cs = calendars.putIfAbsent(calendarName, cal);
        return cs == null ? cal : cs;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r1 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Properties getCalendarProperties() throws java.io.IOException {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.lang.String r1 = "calendars.properties"
            java.io.InputStream r1 = java.lang.ClassLoader.getSystemResourceAsStream(r1)
            r0.load(r1)     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x0013
            r1.close()
        L_0x0013:
            return r0
        L_0x0014:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r3 = move-exception
            if (r1 == 0) goto L_0x0021
            r1.close()     // Catch:{ all -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0021:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.calendar.CalendarSystem.getCalendarProperties():java.util.Properties");
    }
}
