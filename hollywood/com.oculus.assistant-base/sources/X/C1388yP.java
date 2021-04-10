package X;

import java.util.ArrayList;

/* renamed from: X.yP  reason: case insensitive filesystem */
public final class C1388yP implements AbstractC0105Aj {
    public final /* synthetic */ YJ A00;

    public C1388yP(YJ yj) {
        this.A00 = yj;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        YJ yj;
        ArrayList arrayList;
        C0514bB.A01(ak, "event");
        C1260wG wGVar = (C1260wG) ak.A2L();
        C0387Va va = wGVar.A00;
        String str = va.A01;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -733140117) {
                if (hashCode == 1802210438 && str.equals("TTSSpeechAudioMessage")) {
                    yj = this.A00;
                    arrayList = yj.A06;
                } else {
                    return;
                }
            } else if (str.equals("TTSSpeechStateMessage")) {
                yj = this.A00;
                arrayList = yj.A07;
            } else {
                return;
            }
            if (arrayList.remove(va)) {
                yj.A04.A01(new C1258wE(wGVar.A00));
            }
        }
    }
}
