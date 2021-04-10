package X;

import java.util.Arrays;

/* renamed from: X.ue  reason: case insensitive filesystem */
public final class C1182ue extends UQ {
    public transient int A00;
    public transient long[] A01;
    public transient int A02;

    private void A01(int i, int i2) {
        if (i == -2) {
            this.A00 = i2;
        } else {
            long[] jArr = this.A01;
            jArr[i] = (jArr[i] & -4294967296L) | (((long) i2) & 4294967295L);
        }
        if (i2 == -2) {
            this.A02 = i;
            return;
        }
        long[] jArr2 = this.A01;
        jArr2[i2] = (jArr2[i2] & 4294967295L) | (((long) i) << 32);
    }

    @Override // X.UQ
    public final void A07(int i) {
        int i2 = super.A02 - 1;
        long[] jArr = this.A01;
        long j = jArr[i];
        A01((int) (j >>> 32), (int) j);
        if (i < i2) {
            A01((int) (jArr[i2] >>> 32), i);
            A01(i, (int) jArr[i2]);
        }
        super.A07(i);
    }

    @Override // X.UQ
    public final void A06() {
        super.A06();
        this.A00 = -2;
        this.A02 = -2;
    }

    @Override // X.UQ
    public final void A08(int i) {
        super.A08(i);
        long[] jArr = this.A01;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        this.A01 = copyOf;
        Arrays.fill(copyOf, length, i, -1L);
    }

    @Override // X.UQ
    public final void A09(int i, float f) {
        super.A09(i, f);
        this.A00 = -2;
        this.A02 = -2;
        long[] jArr = new long[i];
        this.A01 = jArr;
        Arrays.fill(jArr, -1L);
    }

    @Override // X.UQ
    public final void A0A(int i, Object obj, int i2, int i3) {
        super.A0A(i, obj, i2, i3);
        A01(this.A02, i);
        A01(i, -2);
    }

    public C1182ue() {
        super(3);
    }

    public C1182ue(int i) {
        super(i);
    }
}
