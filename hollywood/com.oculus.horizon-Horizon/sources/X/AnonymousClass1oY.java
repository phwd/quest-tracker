package X;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* renamed from: X.1oY  reason: invalid class name */
public class AnonymousClass1oY {
    public long A00 = -1;
    public long A01 = -1;
    public boolean A02 = false;

    public final synchronized long A00() {
        return this.A01;
    }

    public final synchronized void A01(long j, long j2) {
        if (this.A02) {
            this.A01 += j;
            this.A00 += j2;
        }
    }
}
