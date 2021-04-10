package defpackage;

import com.oculus.os.Version;

/* renamed from: Hn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0463Hn0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8182a;
    public final int b;
    public final int c;
    public final String d;
    public final boolean e;
    public final String f;

    public C0463Hn0(boolean z, int i, int i2, String str, boolean z2, String str2) {
        this.f8182a = z;
        this.b = i;
        this.c = i2;
        this.d = str == null ? "" : str;
        this.e = z2;
        this.f = str2 == null ? "" : str2;
    }

    public int a() {
        if (!this.f8182a) {
            return 1;
        }
        int i = this.b;
        if (i != 0 && i != 4 && i != 5) {
            return 0;
        }
        switch (this.c) {
            case 1:
                return 7;
            case 2:
                return 8;
            case 3:
                return 9;
            case 4:
                return 5;
            case 5:
                return 10;
            case 6:
                return 11;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                return 6;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                return 14;
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                return 15;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                return 12;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                return 4;
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                return 13;
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                return 18;
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                return 16;
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                return 17;
            default:
                return 0;
        }
    }

    public int b() {
        if (!this.f8182a) {
            return 6;
        }
        return C0646Kn0.a(this.b, this.c);
    }
}
