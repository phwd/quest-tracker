package defpackage;

import org.chromium.base.Callback;

/* renamed from: G91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G91 {

    /* renamed from: a  reason: collision with root package name */
    public static int f8070a;
    public H91 b;
    public int c;
    public boolean d;
    public boolean e;

    public G91(H91 h91, int i, boolean z, boolean z2) {
        this.b = h91;
        this.c = i;
        this.d = z;
        this.e = z2;
    }

    public void a(Callback callback) {
        F91 f91 = new F91(callback);
        f8070a++;
        this.b.a(this.c, f91, this.d, this.e);
    }
}
