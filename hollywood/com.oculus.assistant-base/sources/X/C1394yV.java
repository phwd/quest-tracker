package X;

import java.util.ArrayList;

/* renamed from: X.yV  reason: case insensitive filesystem */
public final class C1394yV implements AbstractC0105Aj {
    public final /* synthetic */ YM A00;

    public C1394yV(YM ym) {
        this.A00 = ym;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ak, "event");
        ArrayList arrayList = new ArrayList(1);
        AnonymousClass8E r4 = new AnonymousClass8E();
        C1223vd vdVar = (C1223vd) ak.A2L();
        r4.A03 = vdVar.A00;
        r4.A01 = new C1393yU(this, ak);
        String str = vdVar.A02;
        if (str != null && str.length() > 0) {
            r4.A01(str);
        }
        r4.A05 = vdVar.A03;
        arrayList.add(r4.A00());
        YM ym = this.A00;
        AnonymousClass8J A09 = ym.A00.A09("VoiceSDKManager", vdVar.A01);
        C0514bB.A01(A09, "assistantClientPlatform.…egistry(TAG, appId, null)");
        A09.A03(arrayList);
    }
}
