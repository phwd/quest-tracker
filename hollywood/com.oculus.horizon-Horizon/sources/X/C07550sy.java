package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0sy  reason: invalid class name and case insensitive filesystem */
public final class C07550sy extends AnonymousClass02R {
    @NonNull
    public static final Executor A02 = new AnonymousClass02P();
    @NonNull
    public static final Executor A03 = new AnonymousClass02O();
    public static volatile C07550sy A04;
    @NonNull
    public AnonymousClass02R A00;
    @NonNull
    public AnonymousClass02R A01;

    @NonNull
    public static C07550sy A00() {
        if (A04 == null) {
            synchronized (C07550sy.class) {
                if (A04 == null) {
                    A04 = new C07550sy();
                }
            }
        }
        return A04;
    }

    @Override // X.AnonymousClass02R
    public final void A01(Runnable runnable) {
        this.A01.A01(runnable);
    }

    @Override // X.AnonymousClass02R
    public final void A02(Runnable runnable) {
        this.A01.A02(runnable);
    }

    @Override // X.AnonymousClass02R
    public final boolean A03() {
        return this.A01.A03();
    }

    public C07550sy() {
        C07540sx r0 = new C07540sx();
        this.A00 = r0;
        this.A01 = r0;
    }
}
