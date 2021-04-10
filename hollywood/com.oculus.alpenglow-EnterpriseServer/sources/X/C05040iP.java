package X;

import android.annotation.SuppressLint;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.w"})
/* renamed from: X.0iP  reason: invalid class name and case insensitive filesystem */
public final class C05040iP {
    @Nullable
    public static AnonymousClass0iT A00;
    public static final AnonymousClass0iT A01;
    public static final AnonymousClass0iT A02 = new C02790ao();
    public static final AnonymousClass0iU<Object> A03;

    static {
        C02780an r1 = new C02780an();
        A01 = r1;
        A03 = new C02760al(r1);
    }

    public static synchronized AnonymousClass0iT A00() {
        AnonymousClass0iT r0;
        synchronized (C05040iP.class) {
            r0 = A00;
            if (r0 == null) {
                throw new IllegalStateException();
            }
        }
        return r0;
    }

    public static synchronized AnonymousClass0iU<Object> A01() {
        AnonymousClass0iU<Object> r0;
        synchronized (C05040iP.class) {
            r0 = A03;
        }
        return r0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void A02(android.content.Context r16) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05040iP.A02(android.content.Context):void");
    }
}
