package androidx.lifecycle;

import X.AbstractC01030Da;
import X.AbstractC01080Df;
import X.AbstractC03550cd;
import X.AnonymousClass006;
import X.AnonymousClass0DW;
import X.AnonymousClass0DX;
import X.AnonymousClass0Dg;
import X.C04020dp;
import androidx.annotation.NonNull;

public class LiveData$LifecycleBoundObserver extends AnonymousClass0Dg<T>.ObserverWrapper implements AbstractC03550cd {
    @NonNull
    public final AbstractC01030Da A00;
    public final /* synthetic */ AnonymousClass0Dg A01;

    public final void A00() {
        this.A00.getLifecycle().A07(this);
    }

    public final boolean A02() {
        return this.A00.getLifecycle().A05().isAtLeast(AnonymousClass0DX.STARTED);
    }

    @Override // X.AbstractC03550cd
    public final void A6c(@NonNull AbstractC01030Da r5, @NonNull AnonymousClass0DW r6) {
        if (this.A00.getLifecycle().A05() == AnonymousClass0DX.DESTROYED) {
            AnonymousClass0Dg r3 = this.A01;
            if (C04020dp.A00().A03()) {
                AbstractC01080Df A012 = r3.A01.A01(null);
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
