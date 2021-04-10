package defpackage;

/* renamed from: lA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3506lA1 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f10328a = new Object();
    public final C2652gA1 b = new C2652gA1();
    public boolean c;
    public Object d;
    public Exception e;

    public final C3506lA1 a(AbstractC0838Ns0 ns0) {
        this.b.a(new Sz1(AbstractC0745Me1.f8492a, ns0));
        b();
        return this;
    }

    public final void b() {
        synchronized (this.f10328a) {
            if (this.c) {
                this.b.b(this);
            }
        }
    }

    public final boolean c() {
        boolean z;
        synchronized (this.f10328a) {
            z = this.c && this.e == null;
        }
        return z;
    }
}
