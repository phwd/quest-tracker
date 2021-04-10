package X;

import androidx.annotation.GuardedBy;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public final class GD extends XZ {
    @GuardedBy("sLock")
    public static GD A00;
    public static final Object A01 = new Object();

    public GD() {
        super(null);
    }

    @Override // X.XZ
    public final long A03(long j, long j2, boolean z) {
        return j2;
    }

    @Override // X.XZ
    public final String A04(long j, String str, boolean z) {
        return str;
    }

    @Override // X.XZ
    public final void A05(long j, Ra ra) {
    }

    @Override // X.XZ
    public final boolean A06() {
        return true;
    }

    @Override // X.XZ
    public final boolean A07(long j) {
        return false;
    }

    @Override // X.XZ
    public final boolean A08(long j) {
        return false;
    }

    @Override // X.XZ
    public final boolean A09(long j, boolean z, boolean z2) {
        return z;
    }

    public static synchronized GD A00() {
        GD gd;
        synchronized (GD.class) {
            gd = A00;
            if (gd == null) {
                synchronized (A01) {
                    gd = A00;
                    if (gd == null) {
                        gd = new GD();
                        A00 = gd;
                    }
                }
            }
        }
        return gd;
    }
}
