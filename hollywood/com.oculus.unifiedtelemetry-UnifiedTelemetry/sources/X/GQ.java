package X;

import javax.annotation.Nullable;

public class GQ {
    @Nullable
    public static GQ A02;
    public static final Object A03 = new Object();
    public int A00;
    @Nullable
    public GQ A01;

    public final void A00() {
        this.A01 = null;
        this.A00 = 0;
        synchronized (A03) {
            GQ gq = A02;
            if (gq == null || 15 > gq.A00) {
                this.A01 = gq;
                int i = 1;
                if (gq != null) {
                    i = 1 + gq.A00;
                }
                this.A00 = i;
                A02 = this;
            }
        }
    }
}
