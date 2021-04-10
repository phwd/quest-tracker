package X;

import java.util.ArrayList;

/* renamed from: X.yG  reason: case insensitive filesystem */
public final class C1379yG implements AbstractC0105Aj {
    public final /* synthetic */ Y4 A00;

    public C1379yG(Y4 y4) {
        this.A00 = y4;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y4 y4;
        ArrayList arrayList;
        C0514bB.A01(ak, "event");
        C1255wB wBVar = (C1255wB) ak.A2L();
        C0387Va va = wBVar.A00;
        String str = va.A01;
        if (str != null) {
            switch (str.hashCode()) {
                case -880710779:
                    if (str.equals("DictationMicVolumeMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0F;
                        break;
                    } else {
                        return;
                    }
                case 895271855:
                    if (str.equals("DictationFinalResponseMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0E;
                        break;
                    } else {
                        return;
                    }
                case 1324739765:
                    if (str.equals("DictationStateMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0G;
                        break;
                    } else {
                        return;
                    }
                case 1399790430:
                    if (str.equals("DictationErrorMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0C;
                        break;
                    } else {
                        return;
                    }
                case 2060476548:
                    if (str.equals("DictationPartialResponseMessage")) {
                        y4 = this.A00;
                        arrayList = y4.A0D;
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            arrayList.add(va);
            y4.A0A.A01(new C1257wD(wBVar.A00));
        }
    }
}
