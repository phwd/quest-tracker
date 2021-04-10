package defpackage;

/* renamed from: HC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HC {

    /* renamed from: a  reason: collision with root package name */
    public String f8142a;
    public long b;
    public long c;

    public HC(String str, long j, long j2) {
        this.f8142a = str;
        this.b = j;
        this.c = j2;
    }

    public long a() {
        long j = this.b;
        long j2 = this.c;
        if (j > j2) {
            return 0;
        }
        return j2 - j;
    }
}
