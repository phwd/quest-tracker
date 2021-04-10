package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.ac  reason: case insensitive filesystem */
public final class C0304ac extends AnonymousClass2R {
    @NonNull
    public static final Executor A02 = new AnonymousClass2P();
    @NonNull
    public static final Executor A03 = new AnonymousClass2O();
    public static volatile C0304ac A04;
    @NonNull
    public AnonymousClass2R A00;
    @NonNull
    public AnonymousClass2R A01;

    @NonNull
    public static C0304ac A00() {
        if (A04 == null) {
            synchronized (C0304ac.class) {
                if (A04 == null) {
                    A04 = new C0304ac();
                }
            }
        }
        return A04;
    }

    @Override // X.AnonymousClass2R
    public final void A01(Runnable runnable) {
        this.A01.A01(runnable);
    }

    @Override // X.AnonymousClass2R
    public final void A02(Runnable runnable) {
        this.A01.A02(runnable);
    }

    @Override // X.AnonymousClass2R
    public final boolean A03() {
        return this.A01.A03();
    }

    public C0304ac() {
        C0303ab abVar = new C0303ab();
        this.A00 = abVar;
        this.A01 = abVar;
    }
}
