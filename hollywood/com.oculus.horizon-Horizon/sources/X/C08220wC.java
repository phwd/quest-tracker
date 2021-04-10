package X;

import java.io.Closeable;

/* renamed from: X.0wC  reason: invalid class name and case insensitive filesystem */
public final class C08220wC implements Closeable {
    public final EnumC08350wP A00;
    public final int A01;
    public final long A02;
    public final long A03;
    public final String A04;
    public final C08430wZ A05;
    public final AnonymousClass0wX A06;
    public final C08330wN A07;
    public final C08220wC A08;
    public final C08220wC A09;
    public final C08220wC A0A;
    public final AbstractC08210wB A0B;
    public volatile C08580wo A0C;

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

    public C08220wC(C08230wD r3) {
        this.A07 = r3.A07;
        this.A00 = r3.A06;
        this.A01 = r3.A00;
        this.A04 = r3.A03;
        this.A05 = r3.A04;
        this.A06 = new AnonymousClass0wX(r3.A05);
        this.A0B = r3.A0B;
        this.A09 = r3.A09;
        this.A08 = r3.A08;
        this.A0A = r3.A0A;
        this.A03 = r3.A02;
        this.A02 = r3.A01;
    }
}
