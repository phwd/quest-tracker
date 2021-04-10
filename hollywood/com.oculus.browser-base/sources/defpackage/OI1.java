package defpackage;

/* renamed from: OI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OI1 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8618a = new Object();
    public final C5236vI1 b = new C5236vI1();
    public boolean c;
    public Object d;
    public Exception e;

    public final OI1 a(AbstractC1143Ss0 ss0) {
        this.b.a(new RH1(AbstractC0928Pe1.f8702a, ss0));
        e();
        return this;
    }

    public final Exception b() {
        Exception exc;
        synchronized (this.f8618a) {
            exc = this.e;
        }
        return exc;
    }

    public final Object c() {
        Object obj;
        synchronized (this.f8618a) {
            SE0.k(this.c, "Task is not yet complete");
            if (this.e == null) {
                obj = this.d;
            } else {
                throw new YN0(this.e);
            }
        }
        return obj;
    }

    public final boolean d() {
        boolean z;
        synchronized (this.f8618a) {
            z = this.c && this.e == null;
        }
        return z;
    }

    public final void e() {
        synchronized (this.f8618a) {
            if (this.c) {
                this.b.b(this);
            }
        }
    }
}
