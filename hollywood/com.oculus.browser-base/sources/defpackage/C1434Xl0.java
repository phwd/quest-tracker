package defpackage;

import android.animation.TimeInterpolator;

/* renamed from: Xl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1434Xl0 {

    /* renamed from: a  reason: collision with root package name */
    public long f9232a = 0;
    public long b = 300;
    public TimeInterpolator c = null;
    public int d = 0;
    public int e = 1;

    public C1434Xl0(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f9232a = j;
        this.b = j2;
        this.c = timeInterpolator;
    }

    public TimeInterpolator a() {
        TimeInterpolator timeInterpolator = this.c;
        return timeInterpolator != null ? timeInterpolator : P6.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1434Xl0)) {
            return false;
        }
        C1434Xl0 xl0 = (C1434Xl0) obj;
        if (this.f9232a == xl0.f9232a && this.b == xl0.b && this.d == xl0.d && this.e == xl0.e) {
            return a().getClass().equals(xl0.a().getClass());
        }
        return false;
    }

    public int hashCode() {
        long j = this.f9232a;
        long j2 = this.b;
        return ((((a().getClass().hashCode() + (((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31)) * 31) + this.d) * 31) + this.e;
    }

    public String toString() {
        return '\n' + C1434Xl0.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.f9232a + " duration: " + this.b + " interpolator: " + a().getClass() + " repeatCount: " + this.d + " repeatMode: " + this.e + "}\n";
    }
}
