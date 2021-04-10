package X;

import androidx.annotation.GuardedBy;
import com.facebook.infer.annotation.ThreadSafe;
import javax.annotation.Nullable;

@ThreadSafe
/* renamed from: X.1bP  reason: invalid class name and case insensitive filesystem */
public final class C09341bP extends AnonymousClass1b2 {
    @GuardedBy("sLock")
    public static C09341bP A00;
    public static final Object A01 = new Object();

    public C09341bP() {
        super(null);
    }

    @Override // X.AnonymousClass1b2
    public final double A03(long j, double d, boolean z) {
        return d;
    }

    @Override // X.AnonymousClass1b2
    public final int A04(long j) {
        return 0;
    }

    @Override // X.AnonymousClass1b2
    public final long A05(long j, long j2, boolean z) {
        return j2;
    }

    @Override // X.AnonymousClass1b2
    @Nullable
    public final String A06(long j) {
        return null;
    }

    @Override // X.AnonymousClass1b2
    public final String A07(long j, String str, boolean z) {
        return str;
    }

    @Override // X.AnonymousClass1b2
    public final void A08(long j, AnonymousClass0Re r3) {
    }

    @Override // X.AnonymousClass1b2
    public final boolean A09(long j, boolean z, boolean z2) {
        return z;
    }

    public static synchronized C09341bP A00() {
        C09341bP r0;
        synchronized (C09341bP.class) {
            r0 = A00;
            if (r0 == null) {
                synchronized (A01) {
                    r0 = A00;
                    if (r0 == null) {
                        r0 = new C09341bP();
                        A00 = r0;
                    }
                }
            }
        }
        return r0;
    }
}
