package X;

import java.io.InputStream;

/* renamed from: X.ga  reason: case insensitive filesystem */
public final class C0750ga implements AbstractC0105Aj {
    public final /* synthetic */ C00608k A00;

    public C0750ga(C00608k r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0139Dd.A09("KeyboardAssistant", "mAudioSubscriber onEvent");
        C00608k r5 = this.A00;
        C0801hp hpVar = (C0801hp) ak.A2L();
        InputStream inputStream = hpVar.A01;
        BN valueOf = BN.valueOf(hpVar.A00.name());
        synchronized (r5) {
            C00568g r2 = r5.A08;
            synchronized (r2) {
                r2.A06.set(true);
                r2.A05 = inputStream;
            }
            r5.A04 = valueOf;
        }
    }
}
