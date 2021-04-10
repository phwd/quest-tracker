package X;

import java.io.InputStream;

/* renamed from: X.gM  reason: case insensitive filesystem */
public final class C0737gM implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0737gM(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0740gP gPVar = this.A00;
        C0801hp hpVar = (C0801hp) ak.A2L();
        InputStream inputStream = hpVar.A01;
        BN valueOf = BN.valueOf(hpVar.A00.name());
        C00568g r2 = gPVar.A0N;
        synchronized (r2) {
            r2.A06.set(true);
            r2.A05 = inputStream;
        }
        gPVar.A08 = valueOf;
    }
}
