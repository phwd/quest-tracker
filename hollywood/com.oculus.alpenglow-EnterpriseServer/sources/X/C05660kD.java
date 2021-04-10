package X;

import java.io.Closeable;

/* renamed from: X.0kD  reason: invalid class name and case insensitive filesystem */
public final class C05660kD implements Closeable {
    public final AnonymousClass0kh A00;
    public final int A01;
    public final long A02;
    public final long A03;
    public final String A04;
    public final C06040lr A05;
    public final C06020lp A06;
    public final C05700ke A07;
    public final C05660kD A08;
    public final C05660kD A09;
    public final C05660kD A0A;
    public final AbstractC05650kC A0B;
    public volatile C06770np A0C;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A0B.close();
    }

    public final String toString() {
        return "Response{protocol=" + this.A00 + ", code=" + this.A01 + ", message=" + this.A04 + ", url=" + this.A07.A03 + '}';
    }

    public C05660kD(C05680ka r3) {
        this.A07 = r3.A07;
        this.A00 = r3.A06;
        this.A01 = r3.A00;
        this.A04 = r3.A03;
        this.A05 = r3.A04;
        this.A06 = new C06020lp(r3.A05);
        this.A0B = r3.A0B;
        this.A09 = r3.A09;
        this.A08 = r3.A08;
        this.A0A = r3.A0A;
        this.A03 = r3.A02;
        this.A02 = r3.A01;
    }
}
