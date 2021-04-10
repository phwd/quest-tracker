package androidx.activity;

import X.AN;
import X.AP;
import X.AR;
import X.AnonymousClass1M;
import X.AnonymousClass1P;
import X.AnonymousClass1Q;
import X.C0306ae;
import X.Zx;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements AnonymousClass1M, Zx {
    @Nullable
    public AnonymousClass1M A00;
    public final AnonymousClass1P A01;
    public final AP A02;
    public final /* synthetic */ AnonymousClass1Q A03;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(@NonNull AnonymousClass1Q r1, @NonNull AP ap, AnonymousClass1P r3) {
        this.A03 = r1;
        this.A02 = ap;
        this.A01 = r3;
        ap.A06(this);
    }

    @Override // X.Zx
    public final void A42(@NonNull AR ar, @NonNull AN an) {
        if (an == AN.ON_START) {
            AnonymousClass1Q r3 = this.A03;
            AnonymousClass1P r2 = this.A01;
            r3.A00.add(r2);
            C0306ae aeVar = new C0306ae(r3, r2);
            r2.A00.add(aeVar);
            this.A00 = aeVar;
        } else if (an == AN.ON_STOP) {
            AnonymousClass1M r0 = this.A00;
            if (r0 != null) {
                r0.cancel();
            }
        } else if (an == AN.ON_DESTROY) {
            cancel();
        }
    }

    @Override // X.AnonymousClass1M
    public final void cancel() {
        this.A02.A07(this);
        this.A01.A00.remove(this);
        AnonymousClass1M r0 = this.A00;
        if (r0 != null) {
            r0.cancel();
            this.A00 = null;
        }
    }
}
