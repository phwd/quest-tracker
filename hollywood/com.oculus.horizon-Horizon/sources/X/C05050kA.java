package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0kA  reason: invalid class name and case insensitive filesystem */
public final class C05050kA implements AbstractC02280a9 {
    public int A00 = 0;
    public final int A01;
    public final int A02;
    public final int A03;

    @Override // X.AbstractC02280a9
    public final boolean A4i(boolean z) {
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

    public C05050kA(int i, int i2, int i3) {
        this.A01 = i;
        this.A02 = i2;
        this.A03 = i3;
    }

    @Override // X.AbstractC02280a9
    public final AnonymousClass0a8 A4Q() {
        return AnonymousClass0a8.BACK_TO_BACK;
    }

    @Override // X.AbstractC02280a9
    public final int A5d(boolean z) {
        if (!A4i(z)) {
            return -1;
        }
        this.A00++;
        return this.A03;
    }
}
