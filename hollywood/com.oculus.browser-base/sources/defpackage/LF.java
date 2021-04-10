package defpackage;

/* renamed from: LF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LF {

    /* renamed from: a  reason: collision with root package name */
    public String f8414a;
    public final String b;
    public final long c;
    public final long d;
    public final int e;

    public LF(String str, long j, long j2, int i) {
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = i;
    }

    public Object clone() {
        return new LF(this.f8414a, this.b, this.c, this.d, this.e);
    }

    public LF(String str, String str2, long j, long j2, int i) {
        this.b = str2;
        this.c = j;
        this.d = j2;
        this.e = i;
        this.f8414a = str;
    }
}
