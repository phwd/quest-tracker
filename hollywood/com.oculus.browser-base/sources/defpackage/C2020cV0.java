package defpackage;

/* renamed from: cV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2020cV0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9609a;
    public final String b;

    public C2020cV0(String str, String str2) {
        this.f9609a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C2020cV0)) {
            return false;
        }
        C2020cV0 cv0 = (C2020cV0) obj;
        if (!this.f9609a.equals(cv0.f9609a) || !this.b.equals(cv0.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.f9609a;
        int i = 0;
        int hashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
