package X;

import java.util.Locale;

/* renamed from: X.0vi  reason: invalid class name and case insensitive filesystem */
public final class C07990vi {
    public static final C07700vD A03 = C07700vD.A04(":");
    public static final C07700vD A04 = C07700vD.A04(":status");
    public static final C07700vD A05 = C07700vD.A04(":authority");
    public static final C07700vD A06 = C07700vD.A04(":method");
    public static final C07700vD A07 = C07700vD.A04(":path");
    public static final C07700vD A08 = C07700vD.A04(":scheme");
    public final int A00;
    public final C07700vD A01;
    public final C07700vD A02;

    public final String toString() {
        return String.format(Locale.US, "%s: %s", this.A01.A0A(), this.A02.A0A());
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C07990vi)) {
            return false;
        }
        C07990vi r4 = (C07990vi) obj;
        if (!this.A01.equals(r4.A01) || !this.A02.equals(r4.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((527 + this.A01.hashCode()) * 31) + this.A02.hashCode();
    }

    public C07990vi(String str, String str2) {
        this(C07700vD.A04(str), C07700vD.A04(str2));
    }

    public C07990vi(C07700vD r2, String str) {
        this(r2, C07700vD.A04(str));
    }

    public C07990vi(C07700vD r3, C07700vD r4) {
        this.A01 = r3;
        this.A02 = r4;
        this.A00 = r3.A07() + 32 + r4.A07();
    }
}
