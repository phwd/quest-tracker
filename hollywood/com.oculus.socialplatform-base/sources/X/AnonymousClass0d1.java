package X;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0d1  reason: invalid class name */
public class AnonymousClass0d1 extends AbstractC057613n {
    @Override // X.AbstractC057613n
    public final void A00(AnonymousClass14I r4) throws IOException {
        int i;
        if (r4 instanceof C01410dw) {
            C01410dw r42 = (C01410dw) r4;
            C01410dw.A01(r42, AnonymousClass007.A05);
            Map.Entry entry = (Map.Entry) ((Iterator) r42.A01[r42.A00 - 1]).next();
            C01410dw.A02(r42, entry.getValue());
            C01410dw.A02(r42, new AnonymousClass0eS((String) entry.getKey()));
            return;
        }
        int i2 = r4.A03;
        if (i2 == 0) {
            i2 = r4.A0E();
        }
        if (i2 == 13) {
            i = 9;
        } else if (i2 == 12) {
            i = 8;
        } else if (i2 == 14) {
            i = 10;
        } else {
            throw new IllegalStateException(AnonymousClass006.A09("Expected a name but was ", AnonymousClass14K.A00(r4.A0G()), r4.A0K()));
        }
        r4.A03 = i;
    }
}
