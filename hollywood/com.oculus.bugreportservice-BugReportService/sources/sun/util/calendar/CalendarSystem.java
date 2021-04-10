package sun.util.calendar;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class CalendarSystem {
    private static final Gregorian GREGORIAN_INSTANCE = new Gregorian();
    private static final ConcurrentMap calendars = new ConcurrentHashMap();
    private static final Map names = new HashMap();

    public abstract CalendarDate newCalendarDate();

    public abstract CalendarDate newCalendarDate(TimeZone timeZone);

    public abstract boolean normalize(CalendarDate calendarDate);

    static {
        names.put("gregorian", Gregorian.class);
        names.put("japanese", LocalGregorianCalendar.class);
        names.put("julian", JulianCalendar.class);
    }

    public static Gregorian getGregorianCalendar() {
        return GREGORIAN_INSTANCE;
    }

    public static CalendarSystem forName(String str) {
        CalendarSystem calendarSystem;
        if ("gregorian".equals(str)) {
            return GREGORIAN_INSTANCE;
        }
        CalendarSystem calendarSystem2 = (CalendarSystem) calendars.get(str);
        if (calendarSystem2 != null) {
            return calendarSystem2;
        }
        Class cls = (Class) names.get(str);
        if (cls == null) {
            return null;
        }
        if (cls.isAssignableFrom(LocalGregorianCalendar.class)) {
            calendarSystem = LocalGregorianCalendar.getLocalGregorianCalendar(str);
        } else {
            try {
                calendarSystem = (CalendarSystem) cls.newInstance();
            } catch (Exception e) {
                throw new InternalError(e);
            }
        }
        if (calendarSystem == null) {
            return null;
        }
        CalendarSystem calendarSystem3 = (CalendarSystem) calendars.putIfAbsent(str, calendarSystem);
        return calendarSystem3 == null ? calendarSystem : calendarSystem3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r1 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Properties getCalendarProperties() {
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
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r2 = move-exception
            if (r1 == 0) goto L_0x0021
            r1.close()     // Catch:{ all -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x0021:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.calendar.CalendarSystem.getCalendarProperties():java.util.Properties");
    }
}
