package X;

/* renamed from: X.1xt  reason: invalid class name and case insensitive filesystem */
public final class C12621xt implements Comparable<C12621xt> {
    public final int A00;
    public final long A01;
    public final Runnable A02;
    public volatile boolean A03;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(C12621xt r6) {
        C12621xt r62 = r6;
        long j = this.A01;
        long j2 = r62.A01;
        if (j < j2) {
            return -1;
        }
        if (j <= j2) {
            int i = this.A00;
            int i2 = r62.A00;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            return 0;
        }
        return 1;
    }

    public C12621xt(Runnable runnable, Long l, int i) {
        this.A02 = runnable;
        this.A01 = l.longValue();
        this.A00 = i;
    }
}
