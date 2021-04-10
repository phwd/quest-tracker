package X;

import java.util.Locale;

/* renamed from: X.Wq  reason: case insensitive filesystem */
public final class C0165Wq {
    public static final WM A03 = WM.A04(":");
    public static final WM A04 = WM.A04(":status");
    public static final WM A05 = WM.A04(":authority");
    public static final WM A06 = WM.A04(":method");
    public static final WM A07 = WM.A04(":path");
    public static final WM A08 = WM.A04(":scheme");
    public final int A00;
    public final WM A01;
    public final WM A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0165Wq)) {
            return false;
        }
        C0165Wq wq = (C0165Wq) obj;
        if (!this.A01.equals(wq.A01) || !this.A02.equals(wq.A02)) {
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

    public C0165Wq(String str, String str2) {
        this(WM.A04(str), WM.A04(str2));
    }

    public C0165Wq(WM wm, String str) {
        this(wm, WM.A04(str));
    }

    public C0165Wq(WM wm, WM wm2) {
        this.A01 = wm;
        this.A02 = wm2;
        this.A00 = wm.A07() + 32 + wm2.A07();
    }
}
