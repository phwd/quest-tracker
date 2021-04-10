package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0dp  reason: invalid class name and case insensitive filesystem */
public final class C04020dp extends AnonymousClass05q {
    @NonNull
    public static final Executor A02 = new ExecutorC005605o();
    @NonNull
    public static final Executor A03 = new ExecutorC005505n();
    public static volatile C04020dp A04;
    @NonNull
    public AnonymousClass05q A00;
    @NonNull
    public AnonymousClass05q A01;

    @NonNull
    public static C04020dp A00() {
        if (A04 == null) {
            synchronized (C04020dp.class) {
                if (A04 == null) {
                    A04 = new C04020dp();
                }
            }
        }
        return A04;
    }

    @Override // X.AnonymousClass05q
    public final void A01(Runnable runnable) {
        this.A01.A01(runnable);
    }

    @Override // X.AnonymousClass05q
    public final void A02(Runnable runnable) {
        this.A01.A02(runnable);
    }

    @Override // X.AnonymousClass05q
    public final boolean A03() {
        return this.A01.A03();
    }

    public C04020dp() {
        C04010do r0 = new C04010do();
        this.A00 = r0;
        this.A01 = r0;
    }
}
