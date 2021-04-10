package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Random;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yL  reason: invalid class name and case insensitive filesystem */
public final class C09010yL implements AbstractC09640zx {
    public int A00;
    public int A01 = 0;
    public final int A02;
    public final int A03;
    public final Random A04 = new Random();

    @Override // X.AbstractC09640zx
    public final boolean A51(boolean z) {
        if (this.A01 < Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC09640zx
    public final int A5l(boolean z) {
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

    public final String toString() {
        return String.format(null, "BackoffRetryStrategy: attempt:%d/Infinite, delay:%d seconds", Integer.valueOf(this.A01), Integer.valueOf(this.A00));
    }

    public C09010yL(int i, int i2, int i3) {
        this.A02 = i2;
        this.A03 = i3;
        this.A00 = i;
    }

    @Override // X.AbstractC09640zx
    public final AnonymousClass0z4 A4Y() {
        return AnonymousClass0z4.BACK_OFF;
    }
}
