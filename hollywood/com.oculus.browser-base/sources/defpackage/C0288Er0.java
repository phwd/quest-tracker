package defpackage;

import java.util.Objects;

/* renamed from: Er0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0288Er0 {

    /* renamed from: a  reason: collision with root package name */
    public final long f7980a;
    public final Long b;
    public final int c;

    public C0288Er0(long j, Long l, int i) {
        this.f7980a = j;
        this.b = l;
        this.c = i;
    }

    public static C0288Er0 a() {
        return new C0288Er0(0, null, 2);
    }

    public int b() {
        if (this.b.longValue() == 0) {
            return 100;
        }
        return (int) ((this.f7980a * 100) / this.b.longValue());
    }

    public boolean c() {
        return this.b == null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0288Er0)) {
            return false;
        }
        C0288Er0 er0 = (C0288Er0) obj;
        if (this.f7980a == er0.f7980a && this.c == er0.c && Objects.equals(this.b, er0.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = ((int) this.f7980a) * 31;
        Long l = this.b;
        return ((i + (l == null ? 0 : l.hashCode())) * 31) + this.c;
    }
}
