package defpackage;

/* renamed from: zA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5890zA {

    /* renamed from: a  reason: collision with root package name */
    public final int f11730a;
    public final int b;
    public final int c;

    public C5890zA(int i, int i2) {
        this.f11730a = i;
        this.b = i2;
        this.c = (i * 31) + i2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5890zA)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C5890zA zAVar = (C5890zA) obj;
        return this.f11730a == zAVar.f11730a && this.b == zAVar.b;
    }

    public int hashCode() {
        return this.c;
    }
}
