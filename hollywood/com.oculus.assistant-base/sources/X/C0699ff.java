package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import com.google.common.base.Present;
import java.util.concurrent.TimeUnit;

/* renamed from: X.ff  reason: case insensitive filesystem */
public final class C0699ff implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0699ff(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00929w.A00.A00("AssistantOnDeviceTtsOnStartMessage", ai.A00);
        C0113Ar ar = ai.A00;
        C0139Dd.A09("AssistantClientPlatform", ar.toString());
        Long valueOf = Long.valueOf(RealtimeSinceBootClock.A00.now() - ar.A03);
        if (valueOf != null) {
            Present present = new Present(valueOf);
            hG hGVar = C00879r.A00;
            EnumC00909u r0 = EnumC00909u.RESPONSE_PREPARED;
            hGVar.A00.markerPoint(hGVar.A02, r0.getPointName(), C0785hJ.A00(present), TimeUnit.MILLISECONDS);
            return;
        }
        throw null;
    }
}
