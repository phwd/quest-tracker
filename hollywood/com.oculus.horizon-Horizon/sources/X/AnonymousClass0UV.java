package X;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0UV  reason: invalid class name */
public class AnonymousClass0UV extends AbstractC08940z0 {
    @Override // X.AbstractC08940z0
    public final void A00(C09120zR r4) throws IOException {
        int i;
        if (r4 instanceof AnonymousClass0X1) {
            AnonymousClass0X1 r42 = (AnonymousClass0X1) r4;
            AnonymousClass0X1.A01(r42, AnonymousClass007.A0E);
            Map.Entry entry = (Map.Entry) ((Iterator) r42.A01[r42.A00 - 1]).next();
            AnonymousClass0X1.A02(r42, entry.getValue());
            AnonymousClass0X1.A02(r42, new C03090c6((String) entry.getKey()));
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
            throw new IllegalStateException(AnonymousClass006.A07("Expected a name but was ", AnonymousClass0zT.A00(r4.A0G()), r4.A0K()));
        }
        r4.A03 = i;
    }
}
