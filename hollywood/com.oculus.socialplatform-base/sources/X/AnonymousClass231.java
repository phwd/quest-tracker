package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Random;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.231  reason: invalid class name */
public final class AnonymousClass231 implements AbstractC144823m {
    public int A00;
    public int A01 = 0;
    public final int A02;
    public final int A03;
    public final Random A04 = new Random();

    @Override // X.AbstractC144823m
    public final AnonymousClass23S A4y() {
        return AnonymousClass23S.BACK_OFF;
    }

    @Override // X.AbstractC144823m
    public final boolean A5R(boolean z) {
        if (this.A01 < Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC144823m
    public final int A6W(boolean z) {
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

    public AnonymousClass231(int i, int i2, int i3) {
        this.A02 = i2;
        this.A03 = i3;
        this.A00 = i;
    }
}
