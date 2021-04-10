package defpackage;

import java.util.Arrays;

/* renamed from: Jn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0584Jn implements AbstractC0523In {

    /* renamed from: a  reason: collision with root package name */
    public long f8311a;
    public long b;
    public float c;

    @Override // defpackage.AbstractC0523In
    public boolean a(long j, long j2) {
        if (this.f8311a == j && this.b == j2) {
            return false;
        }
        this.f8311a = j;
        this.b = j2;
        return true;
    }

    @Override // defpackage.AbstractC0523In
    public float b(long j) {
        float f = this.c;
        long j2 = this.f8311a;
        return (f * ((float) (j - j2))) / ((float) (this.b - j2));
    }

    @Override // defpackage.AbstractC0523In
    public boolean c(float f) {
        if (this.c == f) {
            return false;
        }
        this.c = f;
        return true;
    }

    @Override // defpackage.AbstractC0523In
    public long d(float f) {
        long j = this.f8311a;
        return (long) (((f * ((float) (this.b - j))) / this.c) + ((float) j));
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f8311a), Long.valueOf(this.b), Float.valueOf(this.c)});
    }
}
