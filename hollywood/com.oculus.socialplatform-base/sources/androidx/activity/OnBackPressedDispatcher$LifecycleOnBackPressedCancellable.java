package androidx.activity;

import X.AbstractC05230uw;
import X.AnonymousClass01M;
import X.AnonymousClass01P;
import X.AnonymousClass01Q;
import X.AnonymousClass0AO;
import X.AnonymousClass0AQ;
import X.AnonymousClass0AS;
import X.AnonymousClass0wp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements AnonymousClass01M, AbstractC05230uw {
    @Nullable
    public AnonymousClass01M A00;
    public final AnonymousClass01P A01;
    public final AnonymousClass0AQ A02;
    public final /* synthetic */ AnonymousClass01Q A03;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(@NonNull AnonymousClass01Q r1, @NonNull AnonymousClass0AQ r2, AnonymousClass01P r3) {
        this.A03 = r1;
        this.A02 = r2;
        this.A01 = r3;
        r2.A06(this);
    }

    @Override // X.AbstractC05230uw
    public final void A87(@NonNull AnonymousClass0AS r5, @NonNull AnonymousClass0AO r6) {
        if (r6 == AnonymousClass0AO.ON_START) {
            AnonymousClass01Q r3 = this.A03;
            AnonymousClass01P r2 = this.A01;
            r3.A00.add(r2);
            AnonymousClass0wp r1 = new AnonymousClass0wp(r3, r2);
            r2.A00.add(r1);
            this.A00 = r1;
        } else if (r6 == AnonymousClass0AO.ON_STOP) {
            AnonymousClass01M r0 = this.A00;
            if (r0 != null) {
                r0.cancel();
            }
        } else if (r6 == AnonymousClass0AO.ON_DESTROY) {
            cancel();
        }
    }

    @Override // X.AnonymousClass01M
    public final void cancel() {
        this.A02.A07(this);
        this.A01.A00.remove(this);
        AnonymousClass01M r0 = this.A00;
        if (r0 != null) {
            r0.cancel();
            this.A00 = null;
        }
    }
}
