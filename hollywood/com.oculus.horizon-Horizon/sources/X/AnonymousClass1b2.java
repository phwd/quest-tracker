package X;

import com.oculus.horizon.service_media.OVRMediaServiceManager;
import javax.annotation.Nullable;

/* renamed from: X.1b2  reason: invalid class name */
public abstract class AnonymousClass1b2 implements AnonymousClass0Rg {
    @Nullable
    public volatile AnonymousClass0Ri A00;

    private AnonymousClass0Rj A02(long j) {
        if (this instanceof C09331av) {
            if (((C09331av) this).A03 == null) {
                return AnonymousClass0Rj.DEFAULT__NO_DATA_ON_DISK;
            }
            C09331av r1 = (C09331av) this;
            if ((r1 == null || r1.A03 != null) && (C09331av.A00(r1, j) & 1) == 0) {
                return AnonymousClass0Rj.SERVER;
            }
            C09331av r2 = (C09331av) this;
            if ((r2 == null || r2.A03 != null) && (C09331av.A00(r2, j) & 16) == 0) {
                return AnonymousClass0Rj.DEFAULT__SERVER_RETURNED_NULL;
            }
        }
        return AnonymousClass0Rj.DEFAULT__MISSING_SERVER_VALUE;
    }

    public abstract double A03(long j, double d, boolean z);

    public abstract int A04(long j);

    public abstract long A05(long j, long j2, boolean z);

    @Nullable
    public abstract String A06(long j);

    public abstract String A07(long j, String str, boolean z);

    public abstract void A08(long j, AnonymousClass0Re v);

    public abstract boolean A09(long j, boolean z, boolean z2);

    @Override // X.AnonymousClass0Rg
    public final int A3d(long j, int i) {
        long A3m = A3m(j, (long) i, AnonymousClass0Rh.A05);
        int i2 = (int) A3m;
        if (((long) i2) == A3m) {
            return i2;
        }
        return i;
    }

    @Override // X.AnonymousClass0Rg
    public final long A3n(long j, AnonymousClass0Rh r9) {
        return A3m(j, AnonymousClass1bY.A00(j), r9);
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A36(long j) {
        return A3A(j, AnonymousClass0Rh.A05);
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A37(long j, boolean z) {
        return A3B(j, z, AnonymousClass0Rh.A05);
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A3A(long j, AnonymousClass0Rh r9) {
        boolean z = false;
        if (((j >> 61) & 1) == 1) {
            z = true;
        }
        return A3B(j, z, r9);
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A3B(long j, boolean z, AnonymousClass0Rh r6) {
        if (this.A00 == null || !this.A00.hasBoolOverrideForParam(j)) {
            if (r6.A02) {
                AnonymousClass0Rj A02 = A02(j);
                r6.A00 = A02;
                if (!(A02 == AnonymousClass0Rj.SERVER || A02 == AnonymousClass0Rj.DEFAULT__SERVER_RETURNED_NULL)) {
                    return z;
                }
            }
            return A09(j, z, r6.A03);
        }
        if (r6.A02) {
            r6.A00 = AnonymousClass0Rj.OVERRIDE;
        }
        return this.A00.boolOverrideForParam(j, z);
    }

    @Override // X.AnonymousClass0Rg
    public final double A3J(long j, double d, AnonymousClass0Rh r7) {
        if (this.A00 == null || !this.A00.hasDoubleOverrideForParam(j)) {
            if (r7.A02) {
                AnonymousClass0Rj A02 = A02(j);
                r7.A00 = A02;
                if (!(A02 == AnonymousClass0Rj.SERVER || A02 == AnonymousClass0Rj.DEFAULT__SERVER_RETURNED_NULL)) {
                    return d;
                }
            }
            return A03(j, d, r7.A03);
        }
        if (r7.A02) {
            r7.A00 = AnonymousClass0Rj.OVERRIDE;
        }
        return this.A00.doubleOverrideForParam(j, d);
    }

    @Override // X.AnonymousClass0Rg
    public final long A3k(long j) {
        return A3n(j, AnonymousClass0Rh.A05);
    }

    @Override // X.AnonymousClass0Rg
    public final long A3m(long j, long j2, AnonymousClass0Rh r7) {
        if (this.A00 == null || !this.A00.hasIntOverrideForParam(j)) {
            if (r7.A02) {
                AnonymousClass0Rj A02 = A02(j);
                r7.A00 = A02;
                if (!(A02 == AnonymousClass0Rj.SERVER || A02 == AnonymousClass0Rj.DEFAULT__SERVER_RETURNED_NULL)) {
                    return j2;
                }
            }
            return A05(j, j2, r7.A03);
        }
        if (r7.A02) {
            r7.A00 = AnonymousClass0Rj.OVERRIDE;
        }
        return this.A00.intOverrideForParam(j, j2);
    }

    @Override // X.AnonymousClass0Rg
    public final String A4T(long j, String str, AnonymousClass0Rh r6) {
        if (this.A00 == null || !this.A00.hasStringOverrideForParam(j)) {
            if (r6.A02) {
                AnonymousClass0Rj A02 = A02(j);
                r6.A00 = A02;
                if (!(A02 == AnonymousClass0Rj.SERVER || A02 == AnonymousClass0Rj.DEFAULT__SERVER_RETURNED_NULL)) {
                    return str;
                }
            }
            return A07(j, str, r6.A03);
        }
        if (r6.A02) {
            r6.A00 = AnonymousClass0Rj.OVERRIDE;
        }
        String stringOverrideForParam = this.A00.stringOverrideForParam(j, str);
        if ("__fbt_null__".equals(stringOverrideForParam)) {
            return str;
        }
        return stringOverrideForParam;
    }

    public AnonymousClass1b2(AnonymousClass0Ri r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0Rg
    public final double A3K(long j, AnonymousClass0Rh r12) {
        double d;
        int i = (int) (j & 65535);
        if (i == 0) {
            d = -142.5d;
        } else if (i == 1 || i != 2) {
            d = OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY;
        } else {
            d = 9.876543210125E9d;
        }
        return A3J(j, d, r12);
    }

    @Override // X.AnonymousClass0Rg
    public final String A4S(long j, AnonymousClass0Rh r4) {
        return A4T(j, AnonymousClass1bY.A01(j), r4);
    }
}
