package defpackage;

import java.util.Objects;

/* renamed from: Je1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0563Je1 {

    /* renamed from: a  reason: collision with root package name */
    public final OI1 f8303a = new OI1();

    public void a(Exception exc) {
        OI1 oi1 = this.f8303a;
        Objects.requireNonNull(oi1);
        SE0.i(exc, "Exception must not be null");
        synchronized (oi1.f8618a) {
            SE0.k(!oi1.c, "Task is already complete");
            oi1.c = true;
            oi1.e = exc;
        }
        oi1.b.b(oi1);
    }

    public void b(Object obj) {
        OI1 oi1 = this.f8303a;
        synchronized (oi1.f8618a) {
            SE0.k(!oi1.c, "Task is already complete");
            oi1.c = true;
            oi1.d = obj;
        }
        oi1.b.b(oi1);
    }

    public boolean c(Exception exc) {
        OI1 oi1 = this.f8303a;
        Objects.requireNonNull(oi1);
        SE0.i(exc, "Exception must not be null");
        synchronized (oi1.f8618a) {
            if (oi1.c) {
                return false;
            }
            oi1.c = true;
            oi1.e = exc;
            oi1.b.b(oi1);
            return true;
        }
    }
}
