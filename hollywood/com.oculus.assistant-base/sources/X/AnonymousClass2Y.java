package X;

/* renamed from: X.2Y  reason: invalid class name */
public final class AnonymousClass2Y extends C0516bD {
    public final long[][] A00;

    public static long A00(long j, long[][] jArr) {
        long[] jArr2;
        if (jArr == null) {
            C0139Dd.A0E("MobileConfigContextV2WithTranslationTable", "TranslationTable passed was null");
        }
        int A002 = GJ.A00(j) - 1;
        int i = (int) (j & 65535);
        if (A002 >= 0 && A002 < jArr.length && i >= 0 && (jArr2 = jArr[A002]) != null && i < jArr2.length) {
            long j2 = jArr2[i];
            if (j2 == 0 || GJ.A00(j2) == GJ.A00(j)) {
                return j2;
            }
            C0139Dd.A0E("MobileConfigContextV2WithTranslationTable", String.format("Translated to invalid param for config idx: %d param key: %d", Integer.valueOf((int) ((j >>> 32) & 65535)), Integer.valueOf((int) ((j >>> 16) & 65535))));
        }
        return 0;
    }

    @Override // X.C0516bD, X.AbstractC0890oC
    public final long A04(long j, long j2, boolean z) {
        long A002 = A00(j, this.A00);
        if (A002 == 0) {
            return j2;
        }
        return super.A04(A002, j2, z);
    }

    @Override // X.C0516bD, X.AbstractC0890oC
    public final String A05(long j, String str, boolean z) {
        long A002 = A00(j, this.A00);
        if (A002 == 0) {
            return str;
        }
        return super.A05(A002, str, z);
    }

    @Override // X.C0516bD, X.AbstractC0890oC
    public final void A06(long j, EnumC0165Fq fq) {
        long A002 = A00(j, this.A00);
        if (A002 != 0) {
            super.A06(A002, fq);
        }
    }

    @Override // X.C0516bD, X.AbstractC0890oC
    public final boolean A08(long j, boolean z, boolean z2) {
        long A002 = A00(j, this.A00);
        if (A002 == 0) {
            return z;
        }
        return super.A08(A002, z, z2);
    }
}
