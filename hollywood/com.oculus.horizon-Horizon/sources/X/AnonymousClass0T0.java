package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0T0  reason: invalid class name */
public final class AnonymousClass0T0 {
    public int A00;
    public int A01;
    public int[] A02 = new int[5];
    public long[] A03 = new long[5];
    public AnonymousClass0T7[] A04 = new AnonymousClass0T7[5];
    public AnonymousClass0VE<?>[] A05 = new AnonymousClass0VE[5];
    public String[] A06 = new String[5];

    public final void A00(AnonymousClass0Sz r12) {
        for (int i = 0; i < this.A01; i++) {
            r12.A9l(TimeUnit.NANOSECONDS.toMillis(this.A03[i]), this.A03[i], this.A02[i], this.A06[i], this.A04[i], this.A05[i]);
        }
    }
}
