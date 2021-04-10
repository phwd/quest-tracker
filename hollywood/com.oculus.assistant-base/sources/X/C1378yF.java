package X;

import android.os.Bundle;

/* renamed from: X.yF  reason: case insensitive filesystem */
public final class C1378yF implements AbstractC0105Aj {
    public final /* synthetic */ Y4 A00;

    public C1378yF(Y4 y4) {
        this.A00 = y4;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y4 y4 = this.A00;
        C0514bB.A01(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        Bundle bundle = new Bundle();
        bundle.putString("transcriptionState", ((C0809hx) A2L).A00.name());
        bundle.putString("interactionId", C00799i.A00.mInteractionId);
        y4.A0A.A01(new C1256wC("DictationStateMessage", bundle, y4.A0G));
    }
}
