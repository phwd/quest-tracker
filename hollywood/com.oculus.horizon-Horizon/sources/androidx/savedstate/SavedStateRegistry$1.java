package androidx.savedstate;

import X.AbstractC07290ro;
import X.AnonymousClass0AN;
import X.AnonymousClass0AR;
import X.AnonymousClass0C0;

public class SavedStateRegistry$1 implements AbstractC07290ro {
    public final /* synthetic */ AnonymousClass0C0 A00;

    public SavedStateRegistry$1(AnonymousClass0C0 r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC07290ro
    public final void A70(AnonymousClass0AR r3, AnonymousClass0AN r4) {
        AnonymousClass0C0 r1;
        boolean z;
        if (r4 == AnonymousClass0AN.ON_START) {
            r1 = this.A00;
            z = true;
        } else if (r4 == AnonymousClass0AN.ON_STOP) {
            r1 = this.A00;
            z = false;
        } else {
            return;
        }
        r1.A00 = z;
    }
}
