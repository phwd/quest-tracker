package X;

import java.io.IOException;

/* renamed from: X.0W8  reason: invalid class name */
public class AnonymousClass0W8 extends AnonymousClass0Bd<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r1, Number number) throws IOException {
        r1.A0C(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Number A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() == AnonymousClass007.A0I) {
            r3.A0L();
            return null;
        }
        try {
            return Byte.valueOf((byte) r3.A0A());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0XQ(e);
        }
    }
}
