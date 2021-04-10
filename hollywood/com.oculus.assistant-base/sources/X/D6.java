package X;

public final class D6 {
    public final String A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final String A04;
    public final String A05;
    public final String A06;
    public final boolean A07;
    public final boolean A08;
    public final boolean A09;
    public final boolean A0A;
    public final boolean A0B;

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        if (r1.equals(r5.A00) == false) goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: X.D6.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int hashCode = ((this.A03.hashCode() * 31) + this.A06.hashCode()) * 31;
        String str = this.A00;
        int i5 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i6 = (((((((((((hashCode + i) * 31) + (this.A0A ? 1 : 0)) * 31) + (this.A0B ? 1 : 0)) * 31) + (this.A08 ? 1 : 0)) * 31) + (this.A09 ? 1 : 0)) * 31) + (this.A07 ? 1 : 0)) * 31;
        String str2 = this.A02;
        if (str2 != null) {
            i2 = str2.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        String str3 = this.A01;
        if (str3 != null) {
            i3 = str3.hashCode();
        } else {
            i3 = 0;
        }
        int i8 = (i7 + i3) * 31;
        String str4 = this.A05;
        if (str4 != null) {
            i4 = str4.hashCode();
        } else {
            i4 = 0;
        }
        int i9 = (i8 + i4) * 31;
        String str5 = this.A04;
        if (str5 != null) {
            i5 = str5.hashCode();
        }
        return i9 + i5;
    }

    public final String toString() {
        return AnonymousClass08.A07("{name=", this.A03, ";sqlType=", this.A06, "}");
    }

    public D6(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str4, String str5, String str6, String str7) {
        this.A03 = str;
        this.A06 = str2;
        this.A00 = str3;
        this.A0A = z;
        this.A0B = z2;
        this.A08 = z3;
        this.A09 = z4;
        this.A07 = z5;
        this.A02 = str4;
        this.A01 = str5;
        this.A05 = str6;
        this.A04 = str7;
    }
}
