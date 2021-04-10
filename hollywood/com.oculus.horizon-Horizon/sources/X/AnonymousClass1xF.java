package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xF  reason: invalid class name */
public final class AnonymousClass1xF {
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final String A05;

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnonymousClass1xF)) {
            return false;
        }
        AnonymousClass1xF r4 = (AnonymousClass1xF) obj;
        return this.A04 == r4.A04 && this.A02 == r4.A02 && this.A00 == r4.A00 && this.A01 == r4.A01 && this.A03 == r4.A03 && this.A05.equals(r4.A05);
    }

    public final int hashCode() {
        return (((((((((((((((this.A04 * 31) + this.A02) * 31) + this.A00) * 31) + this.A01) * 31) + this.A03) * 31) + this.A05.hashCode()) * 31) + 2) * 31) + 1) * 31) + 3;
    }

    public AnonymousClass1xF(AnonymousClass1xG r7) {
        int min;
        int i = r7.A03;
        this.A04 = i;
        int i2 = r7.A01;
        this.A02 = i2;
        int i3 = r7.A00;
        this.A01 = i3;
        this.A05 = r7.A05;
        this.A03 = r7.A02;
        Integer num = r7.A04;
        if (num != null) {
            min = num.intValue();
        } else {
            min = Math.min((int) (((double) (i * i2)) * 0.07d * ((double) ((float) i3)) * ((double) 2)), 10000000);
        }
        this.A00 = min;
    }
}
