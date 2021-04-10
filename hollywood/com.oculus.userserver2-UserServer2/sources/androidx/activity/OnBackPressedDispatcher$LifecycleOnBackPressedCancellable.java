package androidx.activity;

import X.AbstractC0041Bq;
import X.AnonymousClass1N;
import X.AnonymousClass1Q;
import X.AnonymousClass1R;
import X.Bs;
import X.EnumC0039Bo;
import X.Tc;
import X.Td;
import X.UJ;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements AnonymousClass1N, Td {
    @Nullable
    public AnonymousClass1N A00;
    public final AnonymousClass1Q A01;
    public final AbstractC0041Bq A02;
    public final /* synthetic */ AnonymousClass1R A03;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(@NonNull AnonymousClass1R r1, @NonNull AbstractC0041Bq bq, AnonymousClass1Q r3) {
        this.A03 = r1;
        this.A02 = bq;
        this.A01 = r3;
        bq.A05(this);
    }

    @Override // X.Td
    public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
        if (bo == EnumC0039Bo.ON_START) {
            AnonymousClass1R r3 = this.A03;
            AnonymousClass1Q r2 = this.A01;
            r3.A00.add(r2);
            UJ uj = new UJ(r3, r2);
            r2.A00.add(uj);
            this.A00 = uj;
        } else if (bo == EnumC0039Bo.ON_STOP) {
            AnonymousClass1N r0 = this.A00;
            if (r0 != null) {
                r0.cancel();
            }
        } else if (bo == EnumC0039Bo.ON_DESTROY) {
            cancel();
        }
    }

    @Override // X.AnonymousClass1N
    public final void cancel() {
        ((Tc) this.A02).A01.A01(this);
        this.A01.A00.remove(this);
        AnonymousClass1N r0 = this.A00;
        if (r0 != null) {
            r0.cancel();
            this.A00 = null;
        }
    }
}
