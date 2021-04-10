package androidx.savedstate;

import X.Bs;
import X.Ds;
import X.EnumC0039Bo;
import X.Td;

public class SavedStateRegistry$1 implements Td {
    public final /* synthetic */ Ds A00;

    public SavedStateRegistry$1(Ds ds) {
        this.A00 = ds;
    }

    @Override // X.Td
    public final void A2i(Bs bs, EnumC0039Bo bo) {
        Ds ds;
        boolean z;
        if (bo == EnumC0039Bo.ON_START) {
            ds = this.A00;
            z = true;
        } else if (bo == EnumC0039Bo.ON_STOP) {
            ds = this.A00;
            z = false;
        } else {
            return;
        }
        ds.A00 = z;
    }
}
