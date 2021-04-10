package defpackage;

/* renamed from: LZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LZ {

    /* renamed from: a  reason: collision with root package name */
    public final String f8424a;
    public final String b;
    public final int c;
    public final int d;
    public final int e;

    public LZ(String str, String str2, int i, int i2, int i3) {
        this.f8424a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LZ)) {
            return false;
        }
        LZ lz = (LZ) obj;
        return this.f8424a.equals(lz.f8424a) && this.b.equals(lz.b) && this.c == lz.c && this.d == lz.d && this.e == lz.e;
    }

    public int hashCode() {
        String str = this.f8424a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.c) * 31) + this.d) * 31) + this.e;
    }
}
