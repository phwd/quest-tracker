package defpackage;

/* renamed from: EY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EY0 implements Comparable {
    public final String F;
    public final String G;
    public final Object[] H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f7968J;

    public EY0(String str, String str2, Object obj) {
        this.F = str;
        this.G = str2;
        this.H = obj == null ? null : new Object[]{obj};
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        int i = this.I;
        int i2 = ((EY0) obj).I;
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EY0)) {
            return false;
        }
        int i = this.I;
        int i2 = ((EY0) obj).I;
        if ((i < i2 ? 65535 : i == i2 ? (char) 0 : 1) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public EY0(String str, String str2, Object... objArr) {
        this.F = str;
        this.G = str2;
        this.H = objArr;
    }
}
