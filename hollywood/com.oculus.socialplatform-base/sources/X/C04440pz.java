package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0pz  reason: invalid class name and case insensitive filesystem */
public final class C04440pz {
    public final List<AbstractC01170Rz> A00;

    public final void A00(AbstractC02210iH r8, Object obj, AnonymousClass0OD r10) throws IOException, C03620oC {
        List<AbstractC01170Rz> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass0C6 r0 = new AnonymousClass0C6(r10.A01, r10.A00);
            r0.A0j();
            list.get(i).A08(r0, r8, obj);
        }
    }

    public C04440pz() {
        this.A00 = new ArrayList();
    }

    public C04440pz(List<AbstractC01170Rz> list) {
        this.A00 = list;
    }
}
