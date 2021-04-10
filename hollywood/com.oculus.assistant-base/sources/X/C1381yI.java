package X;

import java.util.ArrayList;

/* renamed from: X.yI  reason: case insensitive filesystem */
public final class C1381yI implements AbstractC0105Aj {
    public final /* synthetic */ Y4 A00;

    public C1381yI(Y4 y4) {
        this.A00 = y4;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y4 y4;
        ArrayList arrayList;
        C0514bB.A01(ak, "event");
        C1260wG wGVar = (C1260wG) ak.A2L();
        C0387Va va = wGVar.A00;
        String str = va.A01;
        if (str != null) {
            switch (str.hashCode()) {
                case -880710779:
                    if (str.equals("DictationMicVolumeMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0F;
                        arrayList.remove(va);
                        break;
                    } else {
                        return;
                    }
                case 895271855:
                    if (str.equals("DictationFinalResponseMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0E;
                        arrayList.remove(va);
                        break;
                    } else {
                        return;
                    }
                case 1324739765:
                    if (str.equals("DictationStateMessage")) {
                        y4 = this.A00;
                        if (!y4.A0G.remove(va)) {
                            return;
                        }
                    } else {
                        return;
                    }
                    break;
                case 1399790430:
                    if (str.equals("DictationErrorMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0C;
                        arrayList.remove(va);
                        break;
                    } else {
                        return;
                    }
                case 2060476548:
                    if (str.equals("DictationPartialResponseMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0D;
                        arrayList.remove(va);
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            y4.A0A.A01(new C1258wE(wGVar.A00));
        }
    }
}
