package defpackage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* renamed from: hD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2830hD {

    /* renamed from: a  reason: collision with root package name */
    public final long f10054a;
    public final int b;
    public final int c;
    public final int d;

    public C2830hD(long j, int i, int i2, int i3) {
        this.f10054a = j;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static C2830hD a(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        instance.clear();
        instance.set(i, i2, i3);
        return new C2830hD(instance.getTimeInMillis(), i, i2, i3);
    }

    public static C2830hD b(long j) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeInMillis(j);
        return a(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
    }
}
