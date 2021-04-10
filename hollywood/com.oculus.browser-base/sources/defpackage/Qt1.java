package defpackage;

/* renamed from: Qt1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Qt1 {

    /* renamed from: a  reason: collision with root package name */
    public int f8791a = 0;
    public int b;
    public int c;
    public int d;
    public int e;

    public boolean a() {
        int i = this.f8791a;
        if ((i & 7) != 0 && (i & (b(this.d, this.b) << 0)) == 0) {
            return false;
        }
        int i2 = this.f8791a;
        if ((i2 & 112) != 0 && (i2 & (b(this.d, this.c) << 4)) == 0) {
            return false;
        }
        int i3 = this.f8791a;
        if ((i3 & 1792) != 0 && (i3 & (b(this.e, this.b) << 8)) == 0) {
            return false;
        }
        int i4 = this.f8791a;
        if ((i4 & 28672) == 0 || (i4 & (b(this.e, this.c) << 12)) != 0) {
            return true;
        }
        return false;
    }

    public int b(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 2 : 4;
    }
}
