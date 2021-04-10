package X;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Rz  reason: case insensitive filesystem */
public class C0142Rz extends AbstractC0184Uj {
    @Override // X.AbstractC0184Uj
    public final void A00(lk lkVar) throws IOException {
        int i;
        if (lkVar instanceof TO) {
            TO to = (TO) lkVar;
            TO.A01(to, AnonymousClass07.A04);
            Map.Entry entry = (Map.Entry) ((Iterator) to.A01[to.A00 - 1]).next();
            TO.A02(to, entry.getValue());
            TO.A02(to, new U1((String) entry.getKey()));
            return;
        }
        int i2 = lkVar.A03;
        if (i2 == 0) {
            i2 = lkVar.A0E();
        }
        if (i2 == 13) {
            i = 9;
        } else if (i2 == 12) {
            i = 8;
        } else if (i2 == 14) {
            i = 10;
        } else {
            throw new IllegalStateException(AnonymousClass06.A05("Expected a name but was ", mj.A00(lkVar.A0G()), lkVar.A0K()));
        }
        lkVar.A03 = i;
    }
}
