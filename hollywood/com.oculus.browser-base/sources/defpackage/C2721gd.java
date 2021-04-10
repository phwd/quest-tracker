package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: gd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2721gd {

    /* renamed from: a  reason: collision with root package name */
    public String f10008a;
    public String b;
    public int c;
    public int d;

    public C2721gd(C2721gd gdVar) {
        a(gdVar);
    }

    public static boolean g(String str, String str2) {
        return str2.startsWith(str) && str2.length() > str.length();
    }

    public void a(C2721gd gdVar) {
        h(gdVar.f10008a, gdVar.b, gdVar.c, gdVar.d);
    }

    public String b() {
        return this.f10008a + this.b;
    }

    public boolean c() {
        return !TextUtils.isEmpty(this.b);
    }

    public boolean d(C2721gd gdVar) {
        return e() && gdVar.e() && g(this.f10008a, gdVar.f10008a);
    }

    public boolean e() {
        return this.c == this.f10008a.length() && this.d == this.f10008a.length();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C2721gd)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C2721gd gdVar = (C2721gd) obj;
        return this.f10008a.equals(gdVar.f10008a) && this.b.equals(gdVar.b) && this.c == gdVar.c && this.d == gdVar.d;
    }

    public boolean f(C2721gd gdVar) {
        return e() && gdVar.e() && g(gdVar.f10008a, this.f10008a);
    }

    public void h(String str, String str2, int i, int i2) {
        this.f10008a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
    }

    public int hashCode() {
        return (this.d * 7) + (this.c * 5) + (this.b.hashCode() * 3) + (this.f10008a.hashCode() * 2);
    }

    public String toString() {
        return String.format(Locale.US, "AutocompleteState {[%s][%s] [%d-%d]}", this.f10008a, this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
    }

    public C2721gd(String str, String str2, int i, int i2) {
        this.f10008a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
    }
}
