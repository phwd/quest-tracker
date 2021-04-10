package X;

import java.io.IOException;

/* renamed from: X.0XZ  reason: invalid class name */
public class AnonymousClass0XZ extends AnonymousClass0Bd<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Number number) throws IOException {
        if (number == null) {
            r2.A0A();
        } else {
            r2.A0E(number.toString());
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Number A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return Long.valueOf(r3.A0C());
        }
        r3.A0L();
        return null;
    }
}
