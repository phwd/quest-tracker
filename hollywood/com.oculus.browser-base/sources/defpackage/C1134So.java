package defpackage;

/* renamed from: So  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1134So {

    /* renamed from: a  reason: collision with root package name */
    public final C5653xo f8918a;
    public boolean b;
    public long c;
    public boolean d;
    public int e;

    public C1134So(C5653xo xoVar, boolean z, long j, boolean z2, int i) {
        this.f8918a = xoVar;
        this.b = z;
        this.c = j;
        this.d = z2;
        this.e = i;
    }

    public boolean a() {
        return this.e == 0 && !(this.b && ((this.c > 0 ? 1 : (this.c == 0 ? 0 : -1)) == 0 || this.d));
    }
}
