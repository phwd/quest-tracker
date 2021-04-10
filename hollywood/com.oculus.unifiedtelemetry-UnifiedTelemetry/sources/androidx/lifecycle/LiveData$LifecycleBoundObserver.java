package androidx.lifecycle;

import X.AN;
import X.AO;
import X.AR;
import X.AW;
import X.AX;
import X.AnonymousClass06;
import X.C0304ac;
import X.Zx;
import androidx.annotation.NonNull;

public class LiveData$LifecycleBoundObserver extends AX<T>.ObserverWrapper implements Zx {
    @NonNull
    public final AR A00;
    public final /* synthetic */ AX A01;

    public final void A00() {
        this.A00.getLifecycle().A07(this);
    }

    public final boolean A02() {
        return this.A00.getLifecycle().A05().isAtLeast(AO.STARTED);
    }

    @Override // X.Zx
    public final void A42(@NonNull AR ar, @NonNull AN an) {
        if (this.A00.getLifecycle().A05() == AO.DESTROYED) {
            AX ax = this.A01;
            if (C0304ac.A00().A03()) {
                AW A012 = ax.A01.A01(null);
                if (A012 != null) {
                    A012.A00();
                    A012.A01(false);
                    return;
                }
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A05("Cannot invoke ", "removeObserver", " on a background thread"));
        }
        A01(A02());
    }
}
