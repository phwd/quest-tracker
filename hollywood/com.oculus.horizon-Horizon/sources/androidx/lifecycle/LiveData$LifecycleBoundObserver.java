package androidx.lifecycle;

import X.AbstractC07290ro;
import X.AnonymousClass006;
import X.AnonymousClass0AN;
import X.AnonymousClass0AO;
import X.AnonymousClass0AR;
import X.AnonymousClass0AW;
import X.AnonymousClass0AX;
import X.C07550sy;
import androidx.annotation.NonNull;

public class LiveData$LifecycleBoundObserver extends AnonymousClass0AX<T>.ObserverWrapper implements AbstractC07290ro {
    @NonNull
    public final AnonymousClass0AR A00;
    public final /* synthetic */ AnonymousClass0AX A01;

    public final void A00() {
        this.A00.getLifecycle().A07(this);
    }

    public final boolean A02() {
        return this.A00.getLifecycle().A05().isAtLeast(AnonymousClass0AO.STARTED);
    }

    @Override // X.AbstractC07290ro
    public final void A70(@NonNull AnonymousClass0AR r5, @NonNull AnonymousClass0AN r6) {
        if (this.A00.getLifecycle().A05() == AnonymousClass0AO.DESTROYED) {
            AnonymousClass0AX r3 = this.A01;
            if (C07550sy.A00().A03()) {
                AnonymousClass0AW A012 = r3.A01.A01(null);
                if (A012 != null) {
                    A012.A00();
                    A012.A01(false);
                    return;
                }
                return;
            }
            throw new IllegalStateException(AnonymousClass006.A07("Cannot invoke ", "removeObserver", " on a background thread"));
        }
        A01(A02());
    }
}
