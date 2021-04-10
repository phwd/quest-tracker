package X;

import java.io.InputStream;

public final class iF implements AbstractC0106Ak, AbstractC0114As {
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final BN A04;
    public final InputStream A05;
    public final String A06 = "disabled";
    public final String A07;
    public final String A08;
    public final String A09;
    public final String A0A;
    public final boolean A0B;

    public iF(InputStream inputStream, String str, int i, String str2, boolean z, String str3, int i2, int i3, String str4, BN bn, int i4) {
        this.A05 = inputStream;
        this.A0A = str;
        this.A01 = i;
        this.A09 = str2;
        this.A0B = z;
        this.A08 = str3;
        this.A00 = i2;
        this.A03 = i3;
        this.A07 = str4;
        this.A04 = bn;
        this.A02 = i4;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
