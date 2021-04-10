package X;

import java.util.Map;

/* renamed from: X.8H  reason: invalid class name */
public final class AnonymousClass8H {
    public final double A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final String A04;
    public final String A05;
    public final String A06;
    public final Map A07;
    public final byte[] A08;

    public final String toString() {
        String str = this.A03;
        if (str == null) {
            str = "__UNKNOWN__";
        }
        return AnonymousClass08.A07("[", str, ":", this.A05, "]");
    }

    public AnonymousClass8H(AnonymousClass8G r3) {
        this.A00 = r3.A00;
        this.A06 = r3.A06;
        this.A04 = r3.A04;
        this.A05 = r3.A05;
        this.A07 = r3.A07;
        this.A02 = r3.A02;
        this.A08 = r3.A08;
        this.A03 = r3.A03;
        this.A01 = r3.A01;
    }
}
