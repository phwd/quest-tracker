package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Random;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0aA  reason: invalid class name and case insensitive filesystem */
public final class C02290aA {
    public int A00;
    public int A01 = -2;
    public final int A02;
    public final int A03;
    public final Random A04 = new Random();

    public final String toString() {
        return String.format(null, "ParameterizedRetryState (%d,%d): multiplier:%d, interval:%d", Integer.valueOf(this.A02), Integer.valueOf(this.A03), Integer.valueOf(this.A01), Integer.valueOf(this.A00));
    }

    public C02290aA(int i, int i2) {
        this.A02 = i;
        this.A03 = i2;
        this.A00 = i;
    }
}
