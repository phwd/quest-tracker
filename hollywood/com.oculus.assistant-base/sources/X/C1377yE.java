package X;

import android.os.Bundle;

/* renamed from: X.yE  reason: case insensitive filesystem */
public final class C1377yE implements AbstractC0105Aj {
    public final /* synthetic */ Y4 A00;

    public C1377yE(Y4 y4) {
        this.A00 = y4;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y4 y4 = this.A00;
        C0514bB.A01(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        C1205vK vKVar = new C1205vK((int) (((C0808hw) A2L).A00 * ((float) 100)));
        Bundle bundle = new Bundle();
        bundle.putInt("micVolume", vKVar.A00);
        bundle.putString("interactionId", C00799i.A00.mInteractionId);
        y4.A0A.A01(new C1256wC("DictationMicVolumeMessage", bundle, y4.A0F));
    }
}
