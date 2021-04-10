package defpackage;

import java.util.Date;
import org.chromium.url.GURL;

/* renamed from: SX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SX0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8898a;
    public final GURL b;
    public final String c;
    public final int d;
    public final int e;
    public final int f;
    public final Date g;

    public SX0(String str, GURL gurl, String str2, int i, int i2, int i3, Date date) {
        this.f8898a = str;
        this.b = gurl;
        this.c = str2;
        this.e = i2;
        this.d = i;
        this.f = i3;
        this.g = (Date) date.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SX0.class != obj.getClass()) {
            return false;
        }
        SX0 sx0 = (SX0) obj;
        if (this.e == sx0.e && this.d == sx0.d && this.f == sx0.f && this.f8898a.equals(sx0.f8898a) && this.b.equals(sx0.b)) {
            return this.c.equals(sx0.c);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        return ((((((this.c.hashCode() + ((hashCode + (this.f8898a.hashCode() * 31)) * 31)) * 31) + this.e) * 31) + this.f) * 31) + this.d;
    }
}
