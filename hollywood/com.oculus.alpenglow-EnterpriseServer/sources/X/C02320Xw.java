package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;

@GwtCompatible(emulated = true, serializable = true)
/* renamed from: X.0Xw  reason: invalid class name and case insensitive filesystem */
public final class C02320Xw<K> extends AnonymousClass0tI<K> {
    @VisibleForTesting
    public transient long[] A00;
    public transient int A01;
    public transient int A02;

    private void A00(int i, int i2) {
        if (i == -2) {
            this.A01 = i2;
        } else {
            long[] jArr = this.A00;
            jArr[i] = (jArr[i] & -4294967296L) | (((long) i2) & 4294967295L);
        }
        if (i2 == -2) {
            this.A02 = i;
            return;
        }
        long[] jArr2 = this.A00;
        jArr2[i2] = (jArr2[i2] & 4294967295L) | (((long) i) << 32);
    }

    @Override // X.AnonymousClass0tI
    public final int A02() {
        int i = this.A01;
        if (i == -2) {
            return -1;
        }
        return i;
    }

    @Override // X.AnonymousClass0tI
    public final int A03(int i) {
        int i2 = (int) this.A00[i];
        if (i2 == -2) {
            return -1;
        }
        return i2;
    }

    @Override // X.AnonymousClass0tI
    public final int A04(int i, int i2) {
        if (i == super.A02) {
            return i2;
        }
        return i;
    }

    @Override // X.AnonymousClass0tI
    public final void A08(int i) {
        int i2 = super.A02 - 1;
        long[] jArr = this.A00;
        long j = jArr[i];
        A00((int) (j >>> 32), (int) j);
        if (i < i2) {
            A00((int) (jArr[i2] >>> 32), i);
            A00(i, (int) jArr[i2]);
        }
        super.A08(i);
    }

    @Override // X.AnonymousClass0tI
    public final void A07() {
        super.A07();
        this.A01 = -2;
        this.A02 = -2;
    }

    @Override // X.AnonymousClass0tI
    public final void A09(int i) {
        super.A09(i);
        long[] jArr = this.A00;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        this.A00 = copyOf;
        Arrays.fill(copyOf, length, i, -1L);
    }

    @Override // X.AnonymousClass0tI
    public final void A0A(int i, float f) {
        super.A0A(i, f);
        this.A01 = -2;
        this.A02 = -2;
        long[] jArr = new long[i];
        this.A00 = jArr;
        Arrays.fill(jArr, -1L);
    }

    @Override // X.AnonymousClass0tI
    public final void A0B(int i, K k, int i2, int i3) {
        super.A0B(i, k, i2, i3);
        A00(this.A02, i);
        A00(i, -2);
    }

    public C02320Xw() {
        super(3);
    }

    public C02320Xw(int i) {
        super(i);
    }
}
