package androidx.savedstate;

import X.AbstractC05230uw;
import X.AnonymousClass0AO;
import X.AnonymousClass0AS;
import X.AnonymousClass0C4;

public class SavedStateRegistry$1 implements AbstractC05230uw {
    public final /* synthetic */ AnonymousClass0C4 A00;

    public SavedStateRegistry$1(AnonymousClass0C4 r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC05230uw
    public final void A87(AnonymousClass0AS r3, AnonymousClass0AO r4) {
        AnonymousClass0C4 r1;
        boolean z;
        if (r4 == AnonymousClass0AO.ON_START) {
            r1 = this.A00;
            z = true;
        } else if (r4 == AnonymousClass0AO.ON_STOP) {
            r1 = this.A00;
            z = false;
        } else {
            return;
        }
        r1.A00 = z;
    }
}
