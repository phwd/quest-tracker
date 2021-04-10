package androidx.lifecycle;

import X.AbstractC0045Bx;
import X.AbstractC0046By;
import X.AnonymousClass06;
import X.Bs;
import X.EnumC0039Bo;
import X.EnumC0040Bp;
import X.Tc;
import X.Td;
import X.UH;
import androidx.annotation.NonNull;

public class LiveData$LifecycleBoundObserver extends AbstractC0046By<T>.ObserverWrapper implements Td {
    @NonNull
    public final Bs A00;
    public final /* synthetic */ AbstractC0046By A01;

    @Override // X.Td
    public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
        if (((Tc) this.A00.getLifecycle()).A02 == EnumC0040Bp.DESTROYED) {
            AbstractC0046By by = this.A01;
            if (UH.A00().A03()) {
                AbstractC0045Bx A012 = by.A01.A01(null);
                if (A012 != null) {
                    if (A012 instanceof LiveData$LifecycleBoundObserver) {
                        LiveData$LifecycleBoundObserver liveData$LifecycleBoundObserver = (LiveData$LifecycleBoundObserver) A012;
                        ((Tc) liveData$LifecycleBoundObserver.A00.getLifecycle()).A01.A01(liveData$LifecycleBoundObserver);
                    }
                    A012.A00(false);
                    return;
                }
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A04("Cannot invoke ", "removeObserver", " on a background thread"));
        }
        A00(((Tc) this.A00.getLifecycle()).A02.isAtLeast(EnumC0040Bp.STARTED));
    }
}
