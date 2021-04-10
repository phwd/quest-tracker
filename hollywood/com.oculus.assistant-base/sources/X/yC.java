package X;

import android.os.Bundle;

public final class yC implements AbstractC0105Aj {
    public final /* synthetic */ Y4 A00;

    public yC(Y4 y4) {
        this.A00 = y4;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y4 y4 = this.A00;
        C0514bB.A01(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        C1204vJ vJVar = (C1204vJ) A2L;
        C1204vJ vJVar2 = new C1204vJ(vJVar.A01, vJVar.A00);
        Bundle bundle = new Bundle();
        bundle.putString("errorType", vJVar2.A01);
        bundle.putString("errorMessage", vJVar2.A00);
        bundle.putString("interactionId", C00799i.A00.mInteractionId);
        y4.A0A.A01(new C1256wC("DictationErrorMessage", bundle, y4.A0C));
    }
}
