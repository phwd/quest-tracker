package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.oculus.common.build.BuildConfig;
import java.util.Arrays;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
/* renamed from: X.Ma  reason: case insensitive filesystem */
public final class C0103Ma<K> extends RB<K> {
    public transient int A00;
    @VisibleForTesting
    public transient long[] A01;
    public transient int A02;

    private void A00(int i, int i2) {
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

    @Override // X.RB
    public final void A07(int i) {
        int i2 = super.A02 - 1;
        long[] jArr = this.A01;
        long j = jArr[i];
        A00((int) (j >>> 32), (int) j);
        if (i < i2) {
            A00((int) (jArr[i2] >>> 32), i);
            A00(i, (int) jArr[i2]);
        }
        super.A07(i);
    }

    @Override // X.RB
    public final void A06() {
        super.A06();
        this.A00 = -2;
        this.A02 = -2;
    }

    @Override // X.RB
    public final void A08(int i) {
        super.A08(i);
        long[] jArr = this.A01;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        this.A01 = copyOf;
        Arrays.fill(copyOf, length, i, -1L);
    }

    @Override // X.RB
    public final void A09(int i, float f) {
        super.A09(i, f);
        this.A00 = -2;
        this.A02 = -2;
        long[] jArr = new long[i];
        this.A01 = jArr;
        Arrays.fill(jArr, -1L);
    }

    @Override // X.RB
    public final void A0A(int i, K k, int i2, int i3) {
        super.A0A(i, k, i2, i3);
        A00(this.A02, i);
        A00(i, -2);
    }

    public C0103Ma() {
        super(3);
    }

    public C0103Ma(int i) {
        super(i);
    }
}
