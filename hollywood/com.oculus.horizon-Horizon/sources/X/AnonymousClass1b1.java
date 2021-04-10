package X;

import javax.annotation.Nullable;

/* renamed from: X.1b1  reason: invalid class name */
public final class AnonymousClass1b1 extends C09331av implements AnonymousClass0Rg {
    public final long[][] A00;

    public static long A01(long j, long[][] jArr) {
        long[] jArr2;
        if (jArr == null) {
            AnonymousClass0NO.A0A("MobileConfigContextV2WithTranslationTable", "TranslationTable passed was null");
        }
        int A002 = C01340Rx.A00(j) - 1;
        int i = (int) (j & 65535);
        if (A002 >= 0 && A002 < jArr.length && i >= 0 && (jArr2 = jArr[A002]) != null && i < jArr2.length) {
            long j2 = jArr2[i];
            if (j2 == 0 || C01340Rx.A00(j2) == C01340Rx.A00(j)) {
                return j2;
            }
            AnonymousClass0NO.A0A("MobileConfigContextV2WithTranslationTable", String.format("Translated to invalid param for config idx: %d param key: %d", Integer.valueOf((int) ((j >>> 32) & 65535)), Integer.valueOf((int) ((j >>> 16) & 65535))));
        }
        return 0;
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    public final double A03(long j, double d, boolean z) {
        long A01 = A01(j, this.A00);
        if (A01 == 0) {
            return d;
        }
        return super.A03(A01, d, z);
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    public final int A04(long j) {
        long A01 = A01(j, this.A00);
        if (A01 == 0) {
            return 0;
        }
        return super.A04(A01);
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    public final long A05(long j, long j2, boolean z) {
        long A01 = A01(j, this.A00);
        if (A01 == 0) {
            return j2;
        }
        return super.A05(A01, j2, z);
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    @Nullable
    public final String A06(long j) {
        long A01 = A01(j, this.A00);
        if (A01 == 0) {
            return null;
        }
        return super.A06(A01);
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    public final String A07(long j, String str, boolean z) {
        long A01 = A01(j, this.A00);
        if (A01 == 0) {
            return str;
        }
        return super.A07(A01, str, z);
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    public final void A08(long j, AnonymousClass0Re r8) {
        long A01 = A01(j, this.A00);
        if (A01 != 0) {
            super.A08(A01, r8);
        }
    }

    @Override // X.AnonymousClass1b2, X.C09331av
    public final boolean A09(long j, boolean z, boolean z2) {
        long A01 = A01(j, this.A00);
        if (A01 == 0) {
            return z;
        }
        return super.A09(A01, z, z2);
    }
}
