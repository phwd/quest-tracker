package androidx.lifecycle;

import X.AbstractC00450Aa;
import X.AbstractC05230uw;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AS;
import X.AnonymousClass0AY;
import androidx.annotation.NonNull;

public class LiveData$LifecycleBoundObserver extends AnonymousClass0AY<T>.ObserverWrapper implements AbstractC05230uw {
    @NonNull
    public final AnonymousClass0AS A00;
    public final /* synthetic */ AnonymousClass0AY A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LiveData$LifecycleBoundObserver(@NonNull AnonymousClass0AY r1, AnonymousClass0AS r2, AbstractC00450Aa<? super T> r3) {
        super(r1, r3);
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void A00() {
        this.A00.getLifecycle().A07(this);
    }

    public final boolean A02() {
        return this.A00.getLifecycle().A05().isAtLeast(AnonymousClass0AP.STARTED);
    }

    public final boolean A03(AnonymousClass0AS r3) {
        if (this.A00 == r3) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC05230uw
    public final void A87(@NonNull AnonymousClass0AS r3, @NonNull AnonymousClass0AO r4) {
        if (this.A00.getLifecycle().A05() == AnonymousClass0AP.DESTROYED) {
            this.A01.A04(this.A02);
        } else {
            A01(A02());
        }
    }
}
