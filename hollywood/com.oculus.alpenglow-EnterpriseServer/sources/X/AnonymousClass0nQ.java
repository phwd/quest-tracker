package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0nQ  reason: invalid class name */
public final class AnonymousClass0nQ {
    public final List<AbstractC01680Ku> A00;

    public final void A00(AbstractC02570aK r8, Object obj, C01570Jk r10) throws IOException, C05910ld {
        List<AbstractC01680Ku> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass0C8 r0 = new AnonymousClass0C8(r10.A01, r10.A00);
            r0.A0a();
            list.get(i).A08(r0, r8, obj);
        }
    }

    public AnonymousClass0nQ() {
        this.A00 = new ArrayList();
    }

    public AnonymousClass0nQ(List<AbstractC01680Ku> list) {
        this.A00 = list;
    }
}
