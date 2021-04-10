package X;

import java.io.Closeable;

/* renamed from: X.dg  reason: case insensitive filesystem */
public final class C0359dg implements Closeable {
    public final EnumC0364dl A00;
    public final int A01;
    public final long A02;
    public final long A03;
    public final String A04;
    public final C0371du A05;
    public final C0369ds A06;
    public final C0362dj A07;
    public final C0359dg A08;
    public final C0359dg A09;
    public final C0359dg A0A;
    public final AbstractC0358df A0B;
    public volatile e7 A0C;

    public final String A00(String str) {
        String A002 = this.A06.A00(str);
        if (A002 == null) {
            return null;
        }
        return A002;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A0B.close();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Response{protocol=");
        sb.append(this.A00);
        sb.append(", code=");
        sb.append(this.A01);
        sb.append(", message=");
        sb.append(this.A04);
        sb.append(", url=");
        sb.append(this.A07.A03);
        sb.append('}');
        return sb.toString();
    }

    public C0359dg(C0360dh dhVar) {
        this.A07 = dhVar.A07;
        this.A00 = dhVar.A06;
        this.A01 = dhVar.A00;
        this.A04 = dhVar.A03;
        this.A05 = dhVar.A04;
        this.A06 = new C0369ds(dhVar.A05);
        this.A0B = dhVar.A0B;
        this.A09 = dhVar.A09;
        this.A08 = dhVar.A08;
        this.A0A = dhVar.A0A;
        this.A03 = dhVar.A02;
        this.A02 = dhVar.A01;
    }
}
