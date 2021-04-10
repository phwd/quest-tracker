package defpackage;

/* renamed from: vn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5312vn1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f11498a;
    public final String b;
    public final Integer c;

    public C5312vn1(String str, String str2, Integer num) {
        this.f11498a = str;
        this.b = str2;
        this.c = num;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5312vn1)) {
            return false;
        }
        C5312vn1 vn1 = (C5312vn1) obj;
        if (!this.f11498a.equals(vn1.f11498a) || !this.b.equals(vn1.b) || !this.c.equals(vn1.c)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f11498a + this.b).hashCode();
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("mLanguageCode:");
        i.append(this.f11498a);
        i.append(" - mlanguageRepresentation ");
        i.append(this.b);
        i.append(" - mLanguageUMAHashCode ");
        i.append(this.c);
        return i.toString();
    }
}
