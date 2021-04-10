package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0lV  reason: invalid class name and case insensitive filesystem */
public final class C05580lV {
    public final List<AnonymousClass0HD> A00;

    public final void A00(AbstractC04020gg r8, Object obj, AnonymousClass0Fv r10) throws IOException, AnonymousClass0jg {
        List<AnonymousClass0HD> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass070 r0 = new AnonymousClass070(r10.A01, r10.A00);
            r0.A0b();
            list.get(i).A08(r0, r8, obj);
        }
    }

    public C05580lV() {
        this.A00 = new ArrayList();
    }

    public C05580lV(List<AnonymousClass0HD> list) {
        this.A00 = list;
    }
}
