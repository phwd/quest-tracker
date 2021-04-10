package X;

import java.nio.BufferUnderflowException;
import javax.annotation.Nullable;

/* renamed from: X.139  reason: invalid class name */
public abstract class AnonymousClass139 implements AnonymousClass0Sp {
    @Nullable
    public volatile AnonymousClass0Sr A00;

    @Override // X.AnonymousClass0Sp
    public final boolean A36(long j) {
        return A38(j, AnonymousClass0Sq.A04);
    }

    @Override // X.AnonymousClass0Sp
    public final boolean A38(long j, AnonymousClass0Sq r9) {
        boolean z = false;
        if (((j >> 61) & 1) == 1) {
            z = true;
        }
        return A39(j, z, r9);
    }

    @Override // X.AnonymousClass0Sp
    public final boolean A39(long j, boolean z, AnonymousClass0Sq r12) {
        AnonymousClass12q r6;
        C01720Lc r2;
        byte b;
        AnonymousClass0Ss r1;
        if (this.A00 != null && this.A00.hasBoolOverrideForParam(j)) {
            return this.A00.boolOverrideForParam(j, z);
        }
        if (r12.A01) {
            if (this instanceof AnonymousClass12q) {
                if (((AnonymousClass12q) this).A03 != null) {
                    AnonymousClass12q r13 = (AnonymousClass12q) this;
                    if ((r13 == null || r13.A03 != null) && (AnonymousClass12q.A00(r13, j) & 1) == 0) {
                        r1 = AnonymousClass0Ss.SERVER;
                    } else {
                        AnonymousClass12q r22 = (AnonymousClass12q) this;
                        if ((r22 == null || r22.A03 != null) && (AnonymousClass12q.A00(r22, j) & 16) == 0) {
                            r1 = AnonymousClass0Ss.DEFAULT__SERVER_RETURNED_NULL;
                        }
                    }
                } else {
                    r1 = AnonymousClass0Ss.DEFAULT__NO_DATA_ON_DISK;
                }
                if (!(r1 == AnonymousClass0Ss.SERVER || r1 == AnonymousClass0Ss.DEFAULT__SERVER_RETURNED_NULL)) {
                    return z;
                }
            }
            r1 = AnonymousClass0Ss.DEFAULT__MISSING_SERVER_VALUE;
            return z;
        }
        boolean z2 = r12.A02;
        if (!(this instanceof AnonymousClass12q) || (r2 = (r6 = (AnonymousClass12q) this).A03) == null || AnonymousClass0T5.A00(j) != 1) {
            return z;
        }
        int i = (int) (j & 65535);
        try {
            int i2 = r2.A01;
            if (i2 != 0) {
                b = ((AnonymousClass07D) r2).A01.get(r2.A01(i2) + i);
            } else {
                b = 0;
            }
            if (!z2 && (b & 6) != 0) {
                int i3 = -239;
                try {
                    int i4 = r2.A00;
                    if (i4 != 0) {
                        i3 = ((AnonymousClass07D) r2).A01.getInt(r2.A01(i4) + (i << 2));
                    } else {
                        i3 = 0;
                    }
                } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                }
                AnonymousClass12q.A01(r6, i3, AnonymousClass0Sn.AUTO_EXPOSURE);
            }
            if ((b & 1) != 0) {
                return z;
            }
            if ((b >>> 7) != 0) {
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException | BufferUnderflowException unused2) {
            return z;
        }
    }

    public AnonymousClass139(AnonymousClass0Sr r1) {
        this.A00 = r1;
    }
}
