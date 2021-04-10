package androidx.activity;

import X.AbstractC01030Da;
import X.AbstractC03550cd;
import X.AnonymousClass01N;
import X.AnonymousClass01Q;
import X.AnonymousClass01R;
import X.AnonymousClass0DW;
import X.AnonymousClass0DY;
import X.AnonymousClass0f5;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements AnonymousClass01N, AbstractC03550cd {
    @Nullable
    public AnonymousClass01N A00;
    public final AnonymousClass01Q A01;
    public final AnonymousClass0DY A02;
    public final /* synthetic */ AnonymousClass01R A03;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(@NonNull AnonymousClass01R r1, @NonNull AnonymousClass0DY r2, AnonymousClass01Q r3) {
        this.A03 = r1;
        this.A02 = r2;
        this.A01 = r3;
        r2.A06(this);
    }

    @Override // X.AbstractC03550cd
    public final void A6c(@NonNull AbstractC01030Da r5, @NonNull AnonymousClass0DW r6) {
        if (r6 == AnonymousClass0DW.ON_START) {
            AnonymousClass01R r3 = this.A03;
            AnonymousClass01Q r2 = this.A01;
            r3.A00.add(r2);
            AnonymousClass0f5 r1 = new AnonymousClass0f5(r3, r2);
            r2.A00.add(r1);
            this.A00 = r1;
        } else if (r6 == AnonymousClass0DW.ON_STOP) {
            AnonymousClass01N r0 = this.A00;
            if (r0 != null) {
                r0.cancel();
            }
        } else if (r6 == AnonymousClass0DW.ON_DESTROY) {
            cancel();
        }
    }

    @Override // X.AnonymousClass01N
    public final void cancel() {
        this.A02.A07(this);
        this.A01.A00.remove(this);
        AnonymousClass01N r0 = this.A00;
        if (r0 != null) {
            r0.cancel();
            this.A00 = null;
        }
    }
}
