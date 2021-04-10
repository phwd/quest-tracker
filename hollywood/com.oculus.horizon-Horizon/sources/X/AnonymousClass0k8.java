package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Random;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0k8  reason: invalid class name */
public final class AnonymousClass0k8 implements AbstractC02280a9 {
    public int A00;
    public int A01 = 0;
    public final int A02;
    public final int A03;
    public final Random A04 = new Random();

    public final String toString() {
        return String.format(null, "BackoffRetryStrategy: attempt:%d/Infinite, delay:%d seconds", Integer.valueOf(this.A01), Integer.valueOf(this.A00));
    }

    @Override // X.AbstractC02280a9
    public final boolean A4i(boolean z) {
        if (this.A01 < Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02280a9
    public final int A5d(boolean z) {
        int i;
        this.A01++;
        int i2 = this.A00;
        if (z || i2 >= (i = this.A02)) {
            i = i2;
        }
        int nextFloat = (int) ((((double) this.A04.nextFloat()) + 0.5d) * ((double) Math.min(i << 1, this.A03)));
        this.A00 = nextFloat;
        return nextFloat;
    }

    public AnonymousClass0k8(int i, int i2, int i3) {
        this.A02 = i2;
        this.A03 = i3;
        this.A00 = i;
    }

    @Override // X.AbstractC02280a9
    public final AnonymousClass0a8 A4Q() {
        return AnonymousClass0a8.BACK_OFF;
    }
}
