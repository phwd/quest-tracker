package defpackage;

/* renamed from: fl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2574fl {

    /* renamed from: a  reason: collision with root package name */
    public final String f9946a;
    public final String b;
    public final String c;

    public AbstractC2574fl(String str, String str2, int i, String str3) {
        this.f9946a = str;
        this.b = str2;
        this.c = str3;
    }

    public abstract void a();

    public String b() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        String str2 = this.f9946a;
        String str3 = this.b;
        S40 s40 = AbstractC0533Is.e;
        return s40.b(str2 + ":" + str3);
    }
}
