package X;

/* renamed from: X.j3  reason: case insensitive filesystem */
public final class C0827j3 extends C4 {
    public long A00 = -1;
    public long A01 = -1;

    @Override // X.C4
    public final void A00() {
        super.A00();
        long j = this.A01;
        if (j != -1) {
            long j2 = this.A00;
            if (j2 != -1) {
                if (j < 0 || j2 < 0) {
                    throw new IllegalArgumentException("Window start and end cannot be negative.");
                } else if (j >= j2) {
                    throw new IllegalArgumentException("Window start must be shorter than window end.");
                } else {
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
    }
}
