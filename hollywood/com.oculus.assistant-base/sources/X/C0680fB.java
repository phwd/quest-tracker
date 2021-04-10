package X;

/* renamed from: X.fB  reason: case insensitive filesystem */
public final class C0680fB extends VG {
    public int A00;
    public NT A01 = null;
    public NY A02;
    public C1016qk A03;
    public QK A04;
    public boolean A05;
    public transient C0259Nx A06;

    public C0680fB(QK qk, NY ny) {
        super(0);
        this.A04 = qk;
        this.A00 = -1;
        this.A02 = ny;
        this.A03 = new C1016qk(null, 0, -1, -1);
    }

    @Override // X.AbstractC1014qi, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.A05) {
            this.A05 = true;
        }
    }
}
