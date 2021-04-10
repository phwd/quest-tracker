package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.ArrayList;
import java.util.Collections;

/* renamed from: X.gv  reason: case insensitive filesystem */
public final class C0769gv implements AbstractC0105Aj {
    public final /* synthetic */ C00658s A00;

    public C0769gv(C00658s r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00658s r1 = this.A00;
        ArrayList arrayList = r1.A0C;
        if (!arrayList.isEmpty()) {
            r1.A05 += Collections.frequency(arrayList, r1.A0A);
        }
        C0112Aq.A00().A01(new C0777h6(r1.A08, r1.A09, r1.A02, r1.A03, r1.A06, r1.A01, r1.A04, r1.A07, r1.A00, r1.A05, r1.A0B));
        r1.A08 = OacrConstants.AUTO_SPEECH_DOMAIN;
        r1.A09 = OacrConstants.AUTO_SPEECH_DOMAIN;
        r1.A02 = 0;
        r1.A04 = 0;
        r1.A07 = 0;
        r1.A00 = 0;
        r1.A03 = 0;
        r1.A06 = 0;
        r1.A01 = 0;
        r1.A05 = 0;
        r1.A0C = new ArrayList();
        r1.A0B = new ArrayList();
        r1.A0E = new boolean[0];
        r1.A0D = false;
    }
}
