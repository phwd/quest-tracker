package defpackage;

import J.N;
import android.net.Uri;
import java.io.Serializable;

/* renamed from: ly1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3640ly1 implements Comparable, Serializable {
    public final String F;
    public final String G;
    public final String H;
    public final boolean I;

    public C3640ly1(String str, String str2, String str3, String str4, boolean z) {
        this.F = str2;
        this.G = str3;
        this.H = str4;
        this.I = z;
    }

    public static C3640ly1 b(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.startsWith("[*.]")) {
            return new C3640ly1(str, null, null, str.substring(4), true);
        }
        if (str.indexOf("://") == -1) {
            return new C3640ly1(str, null, null, str, true);
        }
        Uri parse = Uri.parse(str);
        return new C3640ly1(str, str.endsWith("/") ? str.substring(0, str.length() - 1) : str, parse.getScheme(), parse.getHost(), "http".equals(parse.getScheme()) && (parse.getPort() == -1 || parse.getPort() == 80));
    }

    /* renamed from: a */
    public int compareTo(C3640ly1 ly1) {
        int compareTo;
        boolean z = false;
        if (this == ly1) {
            return 0;
        }
        int compareTo2 = c().compareTo(ly1.c());
        if (compareTo2 != 0) {
            return compareTo2;
        }
        String str = this.G;
        boolean z2 = str == null;
        String str2 = ly1.G;
        if (str2 == null) {
            z = true;
        }
        if (z2 != z) {
            return str == null ? -1 : 1;
        }
        if (str != null && (compareTo = str.compareTo(str2)) != 0) {
            return compareTo;
        }
        String[] e = e();
        String[] e2 = ly1.e();
        int length = e.length - 1;
        int length2 = e2.length - 1;
        while (length >= 0 && length2 >= 0) {
            int i = length - 1;
            int i2 = length2 - 1;
            int compareTo3 = e[length].compareTo(e2[length2]);
            if (compareTo3 != 0) {
                return compareTo3;
            }
            length = i;
            length2 = i2;
        }
        return length - length2;
    }

    public final String c() {
        String str = this.F;
        if (str != null) {
            return AbstractC5154ur1.b(str, false);
        }
        StringBuilder i = AbstractC2531fV.i("http://");
        i.append(this.H);
        return AbstractC5154ur1.b(i.toString(), false);
    }

    public String d() {
        if (this.H == null || !this.I) {
            return this.F;
        }
        StringBuilder i = AbstractC2531fV.i("http://");
        i.append(this.H);
        return i.toString();
    }

    public final String[] e() {
        String str;
        int i;
        String str2 = this.F;
        if (str2 != null) {
            int indexOf = str2.indexOf("://");
            if (indexOf == -1) {
                return new String[0];
            }
            i = indexOf + 3;
            str = this.F;
        } else {
            str = this.H;
            i = 0;
        }
        int indexOf2 = str.indexOf(c()) - 1;
        return indexOf2 > i ? str.substring(i, indexOf2).split("\\.") : new String[0];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C3640ly1) || compareTo((C3640ly1) obj) != 0) {
            return false;
        }
        return true;
    }

    public String f() {
        String str = this.F;
        if (str == null) {
            return this.H;
        }
        return N.MR6Af3ZS(str, this.I ? 1 : 0);
    }

    public int hashCode() {
        String str = this.F;
        int i = 0;
        int hashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.G;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.H;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }
}
