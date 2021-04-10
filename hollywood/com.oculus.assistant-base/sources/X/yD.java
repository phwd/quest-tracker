package X;

import android.os.Bundle;

public final class yD implements AbstractC0105Aj {
    public final /* synthetic */ Y4 A00;

    public yD(Y4 y4) {
        this.A00 = y4;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y4 y4 = this.A00;
        C0514bB.A01(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        C0806hu huVar = (C0806hu) A2L;
        Bundle bundle = new Bundle();
        bundle.putString("transcriptionResponse", huVar.A01);
        bundle.putString("interactionId", huVar.A00);
        y4.A0A.A01(new C1256wC("DictationPartialResponseMessage", bundle, y4.A0E));
    }
}
