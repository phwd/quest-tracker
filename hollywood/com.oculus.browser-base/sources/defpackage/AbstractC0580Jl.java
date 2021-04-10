package defpackage;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* renamed from: Jl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0580Jl {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC2032cb f8310a;

    static {
        C0519Il il = new C0519Il(null);
        Executor executor = AbstractC2032cb.f9616a;
        il.f();
        ((ExecutorC1463Ya) executor).execute(il.e);
        f8310a = il;
    }

    public static Calendar a() {
        Calendar calendar;
        try {
            calendar = (Calendar) ((Calendar) f8310a.g()).clone();
        } catch (InterruptedException | ExecutionException unused) {
            calendar = Calendar.getInstance();
        }
        calendar.clear();
        return calendar;
    }
}
