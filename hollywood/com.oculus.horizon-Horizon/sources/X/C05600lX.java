package X;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

/* renamed from: X.0lX  reason: invalid class name and case insensitive filesystem */
public final class C05600lX {
    public static final HashSet<String> A00 = new HashSet<>();

    static {
        int i = 0;
        Class[] clsArr = {Calendar.class, GregorianCalendar.class, Date.class, java.util.Date.class, Timestamp.class, TimeZone.class};
        do {
            A00.add(clsArr[i].getName());
            i++;
        } while (i < 6);
    }
}
