package X;

import java.io.IOException;

/* renamed from: X.0W0  reason: invalid class name */
public class AnonymousClass0W0 extends AnonymousClass0D7 {
    @Override // X.AnonymousClass0D7
    public final void A00(AnonymousClass0Fo r4) throws IOException {
        int i;
        int i2 = r4.A03;
        if (i2 == 0) {
            i2 = r4.A0B();
        }
        if (i2 == 13) {
            i = 9;
        } else if (i2 == 12) {
            i = 8;
        } else if (i2 == 14) {
            i = 10;
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("Expected a name but was ", AnonymousClass0GA.A00(r4.A0D()), r4.A0G()));
        }
        r4.A03 = i;
    }
}
