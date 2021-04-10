package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0wn  reason: invalid class name */
public final class AnonymousClass0wn extends AnonymousClass02Y {
    @NonNull
    public static final Executor A02 = new AnonymousClass02W();
    @NonNull
    public static final Executor A03 = new AnonymousClass02V();
    public static volatile AnonymousClass0wn A04;
    @NonNull
    public AnonymousClass02Y A00;
    @NonNull
    public AnonymousClass02Y A01;

    @NonNull
    public static AnonymousClass0wn A00() {
        if (A04 == null) {
            synchronized (AnonymousClass0wn.class) {
                if (A04 == null) {
                    A04 = new AnonymousClass0wn();
                }
            }
        }
        return A04;
    }

    @Override // X.AnonymousClass02Y
    public final void A01(Runnable runnable) {
        this.A01.A01(runnable);
    }

    @Override // X.AnonymousClass02Y
    public final void A02(Runnable runnable) {
        this.A01.A02(runnable);
    }

    @Override // X.AnonymousClass02Y
    public final boolean A03() {
        return this.A01.A03();
    }

    public AnonymousClass0wn() {
        AnonymousClass0wm r0 = new AnonymousClass0wm();
        this.A00 = r0;
        this.A01 = r0;
    }
}
