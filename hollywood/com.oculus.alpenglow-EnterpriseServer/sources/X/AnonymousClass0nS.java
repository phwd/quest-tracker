package X;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

/* renamed from: X.0nS  reason: invalid class name */
public final class AnonymousClass0nS {
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
