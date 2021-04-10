package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.23M  reason: invalid class name */
public final class AnonymousClass23M implements AbstractC144823m {
    public int A00 = 0;
    public final int A01;
    public final int A02;
    public final int A03;

    @Override // X.AbstractC144823m
    public final boolean A5R(boolean z) {
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

    @Override // X.AbstractC144823m
    public final AnonymousClass23S A4y() {
        return AnonymousClass23S.BACK_TO_BACK;
    }

    public final String toString() {
        return String.format(null, "BackToBackRetryStrategy: attempt:%d/%d/%d, delay:%d seconds", Integer.valueOf(this.A00), Integer.valueOf(this.A01), Integer.valueOf(this.A02), Integer.valueOf(this.A03));
    }

    public AnonymousClass23M(int i, int i2, int i3) {
        this.A01 = i;
        this.A02 = i2;
        this.A03 = i3;
    }

    @Override // X.AbstractC144823m
    public final int A6W(boolean z) {
        if (!A5R(z)) {
            return -1;
        }
        this.A00++;
        return this.A03;
    }
}
