package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/* renamed from: X.0s4  reason: invalid class name */
public class AnonymousClass0s4 implements AnonymousClass09E {
    public int A00;
    public final AnonymousClass0sD A01;
    public final boolean A02;

    public final void A00() {
        boolean z = false;
        if (this.A00 > 0) {
            z = true;
        }
        AnonymousClass0sD r4 = this.A01;
        AbstractC003209a r3 = r4.A02;
        for (Fragment fragment : r3.A0P.A01()) {
            fragment.setOnStartEnterTransitionListener(null);
            if (z && fragment.isPostponed()) {
                fragment.startPostponedEnterTransition();
            }
        }
        r3.A0U(r4, this.A02, !z, true);
    }

    @Override // X.AnonymousClass09E
    public final void A6y() {
        int i = this.A00 - 1;
        this.A00 = i;
        if (i == 0) {
            this.A01.A02.A0M();
        }
    }

    @Override // X.AnonymousClass09E
    public final void A9I() {
        this.A00++;
    }

    public AnonymousClass0s4(@NonNull AnonymousClass0sD r1, boolean z) {
        this.A02 = z;
        this.A01 = r1;
    }
}
