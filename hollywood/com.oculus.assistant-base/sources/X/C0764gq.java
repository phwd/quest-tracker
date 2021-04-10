package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.ArrayList;

/* renamed from: X.gq  reason: case insensitive filesystem */
public final class C0764gq implements AbstractC0105Aj {
    public final /* synthetic */ C00658s A00;

    public C0764gq(C00658s r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00658s r4 = this.A00;
        r4.A08 = OacrConstants.AUTO_SPEECH_DOMAIN;
        r4.A09 = OacrConstants.AUTO_SPEECH_DOMAIN;
        r4.A02 = 0;
        r4.A04 = 0;
        r4.A03 = 0;
        r4.A01 = 0;
        r4.A05 = 0;
        r4.A07 = 0;
        r4.A06 = 0;
        r4.A0C = new ArrayList();
        r4.A0B = new ArrayList();
        r4.A0E = new boolean[0];
        r4.A0D = false;
        C0112Aq.A00().A01(new h4(((C0773gz) ak.A2L()).A00, r4.A09));
    }
}
