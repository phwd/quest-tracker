package defpackage;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

/* renamed from: Ne0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0805Ne0 {

    /* renamed from: a  reason: collision with root package name */
    public String f8561a;
    public String b;
    public int c;
    public String d;
    public C0744Me0 e;
    public int f;
    public List g;
    public int h;
    public long i;

    public C0805Ne0(ID1 id1) {
        a();
    }

    public final void a() {
        this.f8561a = null;
        this.b = null;
        this.c = 0;
        this.d = null;
        this.f = 0;
        this.g = null;
        this.h = 0;
        this.i = -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0805Ne0)) {
            return false;
        }
        C0805Ne0 ne0 = (C0805Ne0) obj;
        return TextUtils.equals(this.f8561a, ne0.f8561a) && TextUtils.equals(this.b, ne0.b) && this.c == ne0.c && TextUtils.equals(this.d, ne0.d) && AbstractC0895Oq0.a(this.e, ne0.e) && this.f == ne0.f && AbstractC0895Oq0.a(this.g, ne0.g) && this.h == ne0.h && this.i == ne0.i;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8561a, this.b, Integer.valueOf(this.c), this.d, this.e, Integer.valueOf(this.f), this.g, Integer.valueOf(this.h), Long.valueOf(this.i)});
    }

    public C0805Ne0(C0805Ne0 ne0, ID1 id1) {
        this.f8561a = ne0.f8561a;
        this.b = ne0.b;
        this.c = ne0.c;
        this.d = ne0.d;
        this.e = ne0.e;
        this.f = ne0.f;
        this.g = ne0.g;
        this.h = ne0.h;
        this.i = ne0.i;
    }
}
