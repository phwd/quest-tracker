package defpackage;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: ly  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3638ly implements Comparable {
    public final String F;
    public final String G;
    public final List H;
    public final List I;

    /* renamed from: J  reason: collision with root package name */
    public final List f10389J;
    public final List K;
    public boolean L;
    public Drawable M;

    public C3638ly(String str, String str2, List list, List list2, List list3) {
        this.G = str2 == null ? "" : str2;
        this.H = list == null ? new ArrayList() : list;
        this.I = list2 == null ? new ArrayList() : list2;
        this.f10389J = list3 == null ? new ArrayList() : list3;
        this.K = new ArrayList();
        this.F = str;
    }

    public final String a(String str) {
        String replaceAll = str.replaceAll("\n\n", "\n");
        while (true) {
            str = replaceAll;
            if (str.length() >= str.length()) {
                return str.replaceAll("\n", ", ");
            }
            replaceAll = str.replaceAll("\n\n", "\n");
        }
    }

    public String b(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (z) {
            int i2 = 0;
            for (C1033Qy0 qy0 : this.f10389J) {
                int i3 = i2 + 1;
                if (i2 > 0) {
                    sb.append("\n");
                }
                sb.append(a(qy0.e[0]));
                i2 = i3;
            }
            i = i2;
        }
        if (z2) {
            for (String str : this.H) {
                int i4 = i + 1;
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(str);
                i = i4;
            }
        }
        if (z3) {
            for (String str2 : this.I) {
                int i5 = i + 1;
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(str2);
                i = i5;
            }
        }
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return ((C3638ly) obj).G.compareTo(this.G);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3638ly)) {
            return false;
        }
        return this.F.equals(((C3638ly) obj).F);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }
}
