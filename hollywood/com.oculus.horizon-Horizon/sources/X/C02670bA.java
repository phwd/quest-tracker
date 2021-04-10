package X;

import android.annotation.SuppressLint;
import android.content.Context;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.w"})
/* renamed from: X.0bA  reason: invalid class name and case insensitive filesystem */
public final class C02670bA {
    @Nullable
    public static AbstractC02690bC A00;
    public static final AbstractC02690bC A01;
    public static final AbstractC02690bC A02 = new AnonymousClass0i6();
    public static final AbstractC02700bD<Object> A03;

    static {
        C04570i0 r1 = new C04570i0();
        A01 = r1;
        A03 = new C04550hy(r1);
    }

    public static synchronized AbstractC02690bC A00() {
        AbstractC02690bC r0;
        synchronized (C02670bA.class) {
            r0 = A00;
            if (r0 == null) {
                throw new IllegalStateException();
            }
        }
        return r0;
    }

    public static synchronized AbstractC02700bD<Object> A01() {
        AbstractC02700bD<Object> r0;
        synchronized (C02670bA.class) {
            r0 = A03;
        }
        return r0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(android.content.Context r15) {
        /*
        // Method dump skipped, instructions count: 215
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02670bA.A02(android.content.Context):void");
    }

    public static synchronized void A03(Context context) {
        synchronized (C02670bA.class) {
            if (A00 == null) {
                A02(context);
                if (A00 == null) {
                    A00 = A02;
                }
            }
        }
    }
}
