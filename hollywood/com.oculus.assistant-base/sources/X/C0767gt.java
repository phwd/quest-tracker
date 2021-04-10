package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.gt  reason: case insensitive filesystem */
public final class C0767gt implements AbstractC0105Aj {
    public final /* synthetic */ C00658s A00;

    public C0767gt(C00658s r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00658s r2 = this.A00;
        if (r2.A08.equals(OacrConstants.AUTO_SPEECH_DOMAIN)) {
            r2.A08 = ((C0770gw) ak.A2L()).A02;
        }
        if (r2.A02 == 0) {
            r2.A02 = ((C0770gw) ak.A2L()).A00;
        }
        r2.A0B.add(Long.valueOf(((C0770gw) ak.A2L()).A01));
    }
}
