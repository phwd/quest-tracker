package X;

import java.util.Locale;

/* renamed from: X.0hr  reason: invalid class name and case insensitive filesystem */
public final class C04870hr {
    public static final C04610h7 A03 = C04610h7.A04(":");
    public static final C04610h7 A04 = C04610h7.A04(":status");
    public static final C04610h7 A05 = C04610h7.A04(":authority");
    public static final C04610h7 A06 = C04610h7.A04(":method");
    public static final C04610h7 A07 = C04610h7.A04(":path");
    public static final C04610h7 A08 = C04610h7.A04(":scheme");
    public final int A00;
    public final C04610h7 A01;
    public final C04610h7 A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C04870hr)) {
            return false;
        }
        C04870hr r4 = (C04870hr) obj;
        if (!this.A01.equals(r4.A01) || !this.A02.equals(r4.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((527 + this.A01.hashCode()) * 31) + this.A02.hashCode();
    }

    public final String toString() {
        return String.format(Locale.US, "%s: %s", this.A01.A0A(), this.A02.A0A());
    }

    public C04870hr(String str, String str2) {
        this(C04610h7.A04(str), C04610h7.A04(str2));
    }

    public C04870hr(C04610h7 r2, String str) {
        this(r2, C04610h7.A04(str));
    }

    public C04870hr(C04610h7 r3, C04610h7 r4) {
        this.A01 = r3;
        this.A02 = r4;
        this.A00 = r3.A07() + 32 + r4.A07();
    }
}
