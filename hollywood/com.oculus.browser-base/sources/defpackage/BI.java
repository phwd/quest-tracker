package defpackage;

import android.text.TextUtils;
import java.util.UUID;

/* renamed from: BI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BI {

    /* renamed from: a  reason: collision with root package name */
    public static final BI f7727a = new BI(new C0788My(), -1, false, false, "", false, false);
    public final int b;
    public final boolean c;
    public final boolean d;
    public final String e;
    public final boolean f;
    public final C0788My g;
    public final boolean h;

    public BI(C0788My my, int i, boolean z, boolean z2, String str, boolean z3, boolean z4) {
        this.b = i;
        this.c = z;
        this.d = z2;
        this.e = str;
        this.f = z3;
        this.g = my == null ? new C0788My() : my;
        this.h = z4;
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        try {
            return str.equalsIgnoreCase(UUID.fromString(str).toString());
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static BI b(String str) {
        boolean z = false;
        try {
            switch (Integer.parseInt(str.substring(0, str.indexOf(",")))) {
                case 1:
                    String[] split = str.split(",", 6);
                    if (split.length != 6) {
                        return f7727a;
                    }
                    String str2 = split[0];
                    String str3 = split[1];
                    String str4 = split[2];
                    String str5 = split[3];
                    String str6 = split[4];
                    String str7 = split[5];
                    boolean equals = "1".equals(str4);
                    boolean equals2 = "1".equals(str5);
                    try {
                        int parseInt = Integer.parseInt(str2);
                        int parseInt2 = Integer.parseInt(str3);
                        if (parseInt != 1) {
                            return f7727a;
                        }
                        if (!a(str6)) {
                            return f7727a;
                        }
                        return new BI(U70.a(false, str6), parseInt2, !equals, equals2, str7, true, false);
                    } catch (NumberFormatException unused) {
                        return f7727a;
                    }
                case 2:
                    String[] split2 = str.split(",", 6);
                    if (split2.length != 6) {
                        return f7727a;
                    }
                    String str8 = split2[0];
                    String str9 = split2[1];
                    String str10 = split2[2];
                    String str11 = split2[3];
                    String str12 = split2[4];
                    String str13 = split2[5];
                    boolean equals3 = "1".equals(str10);
                    boolean equals4 = "1".equals(str11);
                    try {
                        int parseInt3 = Integer.parseInt(str8);
                        int parseInt4 = Integer.parseInt(str9);
                        if (parseInt3 != 2) {
                            return f7727a;
                        }
                        if (!a(str12)) {
                            return f7727a;
                        }
                        return new BI(U70.a(false, str12), parseInt4, equals3, equals4, str13, true, false);
                    } catch (NumberFormatException unused2) {
                        return f7727a;
                    }
                case 3:
                    String[] split3 = str.split(",", 7);
                    if (split3.length != 7) {
                        return f7727a;
                    }
                    String str14 = split3[0];
                    String str15 = split3[1];
                    String str16 = split3[2];
                    String str17 = split3[3];
                    String str18 = split3[4];
                    String str19 = split3[5];
                    String str20 = split3[6];
                    boolean equals5 = "1".equals(str17);
                    boolean equals6 = "1".equals(str18);
                    try {
                        int parseInt5 = Integer.parseInt(str14);
                        int parseInt6 = Integer.parseInt(str15);
                        int parseInt7 = Integer.parseInt(str16);
                        if (parseInt5 != 3) {
                            return f7727a;
                        }
                        if (!a(str19)) {
                            return f7727a;
                        }
                        if (parseInt7 != 1 && parseInt7 != 2) {
                            return f7727a;
                        }
                        if (parseInt7 == 2) {
                            z = true;
                        }
                        return new BI(U70.a(z, str19), parseInt6, equals5, equals6, str20, true, false);
                    } catch (NumberFormatException unused3) {
                        return f7727a;
                    }
                case 4:
                    String[] split4 = str.split(",", 8);
                    if (split4.length != 8) {
                        return f7727a;
                    }
                    String str21 = split4[0];
                    String str22 = split4[1];
                    String str23 = split4[2];
                    String str24 = split4[3];
                    String str25 = split4[4];
                    String str26 = split4[5];
                    String str27 = split4[6];
                    String str28 = split4[7];
                    boolean equals7 = "1".equals(str24);
                    boolean equals8 = "1".equals(str25);
                    boolean equals9 = "1".equals(str26);
                    try {
                        int parseInt8 = Integer.parseInt(str21);
                        int parseInt9 = Integer.parseInt(str22);
                        int parseInt10 = Integer.parseInt(str23);
                        if (parseInt8 != 4) {
                            return f7727a;
                        }
                        if (!a(str27)) {
                            return f7727a;
                        }
                        if (parseInt10 != 1 && parseInt10 != 2) {
                            return f7727a;
                        }
                        if (parseInt10 == 2) {
                            z = true;
                        }
                        return new BI(U70.a(z, str27), parseInt9, equals7, equals8, str28, equals9, false);
                    } catch (NumberFormatException unused4) {
                        return f7727a;
                    }
                case 5:
                    String[] split5 = str.split(",", 8);
                    if (split5.length != 8) {
                        return f7727a;
                    }
                    String str29 = split5[0];
                    String str30 = split5[1];
                    String str31 = split5[2];
                    String str32 = split5[3];
                    String str33 = split5[4];
                    String str34 = split5[5];
                    String str35 = split5[6];
                    String str36 = split5[7];
                    boolean equals10 = "1".equals(str33);
                    boolean equals11 = "1".equals(str34);
                    boolean equals12 = "1".equals(str35);
                    try {
                        int parseInt11 = Integer.parseInt(str29);
                        int parseInt12 = Integer.parseInt(str30);
                        if (parseInt11 != 5) {
                            return f7727a;
                        }
                        if (!a(str32)) {
                            return f7727a;
                        }
                        if (TextUtils.isEmpty(str31)) {
                            return f7727a;
                        }
                        return new BI(new C0788My(str31, str32), parseInt12, equals10, equals11, str36, equals12, false);
                    } catch (NumberFormatException unused5) {
                        return f7727a;
                    }
                case 6:
                    String[] split6 = str.split(",", 9);
                    if (split6.length != 9) {
                        return f7727a;
                    }
                    String str37 = split6[0];
                    String str38 = split6[1];
                    String str39 = split6[2];
                    String str40 = split6[3];
                    String str41 = split6[4];
                    String str42 = split6[5];
                    String str43 = split6[6];
                    String str44 = split6[7];
                    String str45 = split6[8];
                    boolean equals13 = "1".equals(str41);
                    boolean equals14 = "1".equals(str42);
                    boolean equals15 = "1".equals(str43);
                    boolean equals16 = "1".equals(str44);
                    try {
                        int parseInt13 = Integer.parseInt(str37);
                        int parseInt14 = Integer.parseInt(str38);
                        if (parseInt13 != 6) {
                            return f7727a;
                        }
                        if (TextUtils.isEmpty(str40)) {
                            return f7727a;
                        }
                        if (TextUtils.isEmpty(str39)) {
                            return f7727a;
                        }
                        return new BI(new C0788My(str39, str40), parseInt14, equals13, equals14, str45, equals15, equals16);
                    } catch (NumberFormatException unused6) {
                        return f7727a;
                    }
                default:
                    return f7727a;
            }
        } catch (NumberFormatException unused7) {
            AbstractC1220Ua0.f("DownloadEntry", AbstractC2531fV.f("Exception while parsing pending download:", str), new Object[0]);
            return f7727a;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BI)) {
            return false;
        }
        BI bi = (BI) obj;
        if (this.g.equals(bi.g) && TextUtils.equals(this.e, bi.e) && this.b == bi.b && this.c == bi.c && this.d == bi.d && this.f == bi.f && this.h == bi.h) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.g.hashCode();
        return ((this.e.hashCode() + ((hashCode + ((((((((1147 + (this.c ? 1 : 0)) * 37) + (this.d ? 1 : 0)) * 37) + (this.f ? 1 : 0)) * 37) + this.b) * 37)) * 37)) * 37) + (this.h ? 1 : 0);
    }
}
