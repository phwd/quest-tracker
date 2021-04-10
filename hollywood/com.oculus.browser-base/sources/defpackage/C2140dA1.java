package defpackage;

import java.util.Objects;

/* renamed from: dA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2140dA1 {

    /* renamed from: a  reason: collision with root package name */
    public final C3506lA1 f9754a = new C3506lA1();

    public final boolean a(Exception exc) {
        C3506lA1 la1 = this.f9754a;
        Objects.requireNonNull(la1);
        synchronized (la1.f10328a) {
            if (la1.c) {
                return false;
            }
            la1.c = true;
            la1.e = exc;
            la1.b.b(la1);
            return true;
        }
    }

    public final boolean b(Object obj) {
        C3506lA1 la1 = this.f9754a;
        synchronized (la1.f10328a) {
            if (la1.c) {
                return false;
            }
            la1.c = true;
            la1.d = obj;
            la1.b.b(la1);
            return true;
        }
    }
}
