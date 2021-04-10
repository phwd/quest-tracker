package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.Vf  reason: case insensitive filesystem */
public final class C0199Vf {
    public final List<AbstractC0073Cr> A00;

    public final void A00(AbstractC0226Wn wn, Object obj, Br br) throws IOException, q0 {
        List<AbstractC0073Cr> list = this.A00;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass72 r0 = new AnonymousClass72(br.A01, br.A00);
            r0.A0a();
            list.get(i).A08(r0, wn, obj);
        }
    }

    public C0199Vf() {
        this.A00 = new ArrayList();
    }

    public C0199Vf(List<AbstractC0073Cr> list) {
        this.A00 = list;
    }
}
