package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yw  reason: invalid class name and case insensitive filesystem */
public final class C09250yw implements AbstractC09640zx {
    public int A00 = 0;
    public final int A01;
    public final int A02;
    public final int A03;

    @Override // X.AbstractC09640zx
    public final boolean A51(boolean z) {
        int i;
        int i2;
        if (z) {
            i = this.A00;
            i2 = this.A01;
        } else {
            i = this.A00;
            i2 = this.A02;
        }
        return i < i2;
    }

    public final String toString() {
        return String.format(null, "BackToBackRetryStrategy: attempt:%d/%d/%d, delay:%d seconds", Integer.valueOf(this.A00), Integer.valueOf(this.A01), Integer.valueOf(this.A02), Integer.valueOf(this.A03));
    }

    public C09250yw(int i, int i2, int i3) {
        this.A01 = i;
        this.A02 = i2;
        this.A03 = i3;
    }

    @Override // X.AbstractC09640zx
    public final int A5l(boolean z) {
        if (!A51(z)) {
            return -1;
        }
        this.A00++;
        return this.A03;
    }

    @Override // X.AbstractC09640zx
    public final AnonymousClass0z4 A4Y() {
        return AnonymousClass0z4.BACK_TO_BACK;
    }
}
