package X;

import java.io.Closeable;

public final class XK implements Closeable {
    public final XP A00;
    public final int A01;
    public final long A02;
    public final long A03;
    public final String A04;
    public final XY A05;
    public final XW A06;
    public final XN A07;
    public final XK A08;
    public final XK A09;
    public final XK A0A;
    public final XJ A0B;
    public volatile C0186Xl A0C;

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

    public XK(XL xl) {
        this.A07 = xl.A07;
        this.A00 = xl.A06;
        this.A01 = xl.A00;
        this.A04 = xl.A03;
        this.A05 = xl.A04;
        this.A06 = new XW(xl.A05);
        this.A0B = xl.A0B;
        this.A09 = xl.A09;
        this.A08 = xl.A08;
        this.A0A = xl.A0A;
        this.A03 = xl.A02;
        this.A02 = xl.A01;
    }
}
