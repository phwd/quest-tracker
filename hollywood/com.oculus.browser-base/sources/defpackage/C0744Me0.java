package defpackage;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

/* renamed from: Me0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0744Me0 {

    /* renamed from: a  reason: collision with root package name */
    public int f8491a;
    public String b;
    public List c;
    public List d;
    public double e;

    public C0744Me0(ED1 ed1) {
        this.f8491a = 0;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0.0d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0744Me0)) {
            return false;
        }
        C0744Me0 me0 = (C0744Me0) obj;
        return this.f8491a == me0.f8491a && TextUtils.equals(this.b, me0.b) && AbstractC0895Oq0.a(this.c, me0.c) && AbstractC0895Oq0.a(this.d, me0.d) && this.e == me0.e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f8491a), this.b, this.c, this.d, Double.valueOf(this.e)});
    }

    public C0744Me0(C0744Me0 me0, ED1 ed1) {
        this.f8491a = me0.f8491a;
        this.b = me0.b;
        this.c = me0.c;
        this.d = me0.d;
        this.e = me0.e;
    }
}
