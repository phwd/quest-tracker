package androidx.savedstate;

import X.AbstractC01030Da;
import X.AbstractC03550cd;
import X.AnonymousClass0DW;
import X.AnonymousClass0GJ;

public class SavedStateRegistry$1 implements AbstractC03550cd {
    public final /* synthetic */ AnonymousClass0GJ A00;

    public SavedStateRegistry$1(AnonymousClass0GJ r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC03550cd
    public final void A6c(AbstractC01030Da r3, AnonymousClass0DW r4) {
        AnonymousClass0GJ r1;
        boolean z;
        if (r4 == AnonymousClass0DW.ON_START) {
            r1 = this.A00;
            z = true;
        } else if (r4 == AnonymousClass0DW.ON_STOP) {
            r1 = this.A00;
            z = false;
        } else {
            return;
        }
        r1.A00 = z;
    }
}
