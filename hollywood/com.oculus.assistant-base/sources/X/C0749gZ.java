package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.gZ  reason: case insensitive filesystem */
public final class C0749gZ implements AbstractC0105Aj {
    public final /* synthetic */ C00608k A00;

    public C0749gZ(C00608k r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00608k r3 = this.A00;
        synchronized (r3) {
            r3.A03.A01(new C0824iU());
            r3.A03.A01(new C0809hx(EnumC00528b.INACTIVE));
            r3.A03.A01(new C0807hv(true));
            C00949y.A00.A01();
            r3.A01 = 0;
            r3.A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
            r3.A06 = OacrConstants.AUTO_SPEECH_DOMAIN;
            r3.A08.A00();
        }
    }
}
