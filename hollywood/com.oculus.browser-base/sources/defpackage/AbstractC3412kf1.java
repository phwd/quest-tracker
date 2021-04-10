package defpackage;

/* renamed from: kf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3412kf1 {
    public static C3506lA1 a(Exception exc) {
        C3506lA1 la1 = new C3506lA1();
        synchronized (la1.f10328a) {
            Nz1.a(!la1.c, "Task is already complete");
            la1.c = true;
            la1.e = exc;
        }
        la1.b.b(la1);
        return la1;
    }

    public static C3506lA1 b(Object obj) {
        C3506lA1 la1 = new C3506lA1();
        synchronized (la1.f10328a) {
            Nz1.a(!la1.c, "Task is already complete");
            la1.c = true;
            la1.d = obj;
        }
        la1.b.b(la1);
        return la1;
    }
}
