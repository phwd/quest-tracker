package defpackage;

import java.util.Calendar;
import java.util.TimeZone;

/* renamed from: ds1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2255ds1 {
    public static long a(long j) {
        Calendar e = e();
        e.setTimeInMillis(j);
        return b(e).getTimeInMillis();
    }

    public static Calendar b(Calendar calendar) {
        Calendar f = f(calendar);
        Calendar e = e();
        e.set(f.get(1), f.get(2), f.get(5));
        return e;
    }

    public static TimeZone c() {
        return TimeZone.getTimeZone("UTC");
    }

    public static Calendar d() {
        return b(Calendar.getInstance());
    }

    public static Calendar e() {
        return f(null);
    }

    public static Calendar f(Calendar calendar) {
        Calendar instance = Calendar.getInstance(c());
        if (calendar == null) {
            instance.clear();
        } else {
            instance.setTimeInMillis(calendar.getTimeInMillis());
        }
        return instance;
    }
}
