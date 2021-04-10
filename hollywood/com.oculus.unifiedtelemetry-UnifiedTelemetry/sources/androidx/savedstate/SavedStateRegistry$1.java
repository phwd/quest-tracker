package androidx.savedstate;

import X.AN;
import X.AR;
import X.Bz;
import X.Zx;

public class SavedStateRegistry$1 implements Zx {
    public final /* synthetic */ Bz A00;

    public SavedStateRegistry$1(Bz bz) {
        this.A00 = bz;
    }

    @Override // X.Zx
    public final void A42(AR ar, AN an) {
        Bz bz;
        boolean z;
        if (an == AN.ON_START) {
            bz = this.A00;
            z = true;
        } else if (an == AN.ON_STOP) {
            bz = this.A00;
            z = false;
        } else {
            return;
        }
        bz.A00 = z;
    }
}
