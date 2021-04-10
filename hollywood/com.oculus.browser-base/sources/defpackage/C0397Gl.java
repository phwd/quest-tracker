package defpackage;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.Month;

/* renamed from: Gl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0397Gl {

    /* renamed from: a  reason: collision with root package name */
    public static final long f8106a = AbstractC2255ds1.a(Month.c(1900, 0).L);
    public static final long b = AbstractC2255ds1.a(Month.c(2100, 11).L);
    public long c = f8106a;
    public long d = b;
    public Long e;
    public CalendarConstraints.DateValidator f = new DateValidatorPointForward(Long.MIN_VALUE);

    public C0397Gl(CalendarConstraints calendarConstraints) {
        this.c = calendarConstraints.F.L;
        this.d = calendarConstraints.G.L;
        this.e = Long.valueOf(calendarConstraints.H.L);
        this.f = calendarConstraints.I;
    }
}
