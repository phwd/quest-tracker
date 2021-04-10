package defpackage;

/* renamed from: fo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2583fo {

    /* renamed from: a  reason: collision with root package name */
    public long f9953a = 0;
    public C2583fo b;

    public void a(int i) {
        if (i >= 64) {
            C2583fo foVar = this.b;
            if (foVar != null) {
                foVar.a(i - 64);
                return;
            }
            return;
        }
        this.f9953a &= ~(1 << i);
    }

    public int b(int i) {
        C2583fo foVar = this.b;
        if (foVar == null) {
            if (i >= 64) {
                return Long.bitCount(this.f9953a);
            }
            return Long.bitCount(this.f9953a & ((1 << i) - 1));
        } else if (i < 64) {
            return Long.bitCount(this.f9953a & ((1 << i) - 1));
        } else {
            return Long.bitCount(this.f9953a) + foVar.b(i - 64);
        }
    }

    public final void c() {
        if (this.b == null) {
            this.b = new C2583fo();
        }
    }

    public boolean d(int i) {
        if (i < 64) {
            return (this.f9953a & (1 << i)) != 0;
        }
        c();
        return this.b.d(i - 64);
    }

    public void e(int i, boolean z) {
        if (i >= 64) {
            c();
            this.b.e(i - 64, z);
            return;
        }
        long j = this.f9953a;
        boolean z2 = (Long.MIN_VALUE & j) != 0;
        long j2 = (1 << i) - 1;
        this.f9953a = ((j & (~j2)) << 1) | (j & j2);
        if (z) {
            h(i);
        } else {
            a(i);
        }
        if (z2 || this.b != null) {
            c();
            this.b.e(0, z2);
        }
    }

    public boolean f(int i) {
        if (i >= 64) {
            c();
            return this.b.f(i - 64);
        }
        long j = 1 << i;
        long j2 = this.f9953a;
        boolean z = (j2 & j) != 0;
        long j3 = j2 & (~j);
        this.f9953a = j3;
        long j4 = j - 1;
        this.f9953a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
        C2583fo foVar = this.b;
        if (foVar != null) {
            if (foVar.d(0)) {
                h(63);
            }
            this.b.f(0);
        }
        return z;
    }

    public void g() {
        this.f9953a = 0;
        C2583fo foVar = this.b;
        if (foVar != null) {
            foVar.g();
        }
    }

    public void h(int i) {
        if (i >= 64) {
            c();
            this.b.h(i - 64);
            return;
        }
        this.f9953a |= 1 << i;
    }

    public String toString() {
        if (this.b == null) {
            return Long.toBinaryString(this.f9953a);
        }
        return this.b.toString() + "xx" + Long.toBinaryString(this.f9953a);
    }
}
