package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import com.google.common.base.Present;

/* renamed from: X.fd  reason: case insensitive filesystem */
public final class C0697fd implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0697fd(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00929w.A00.A00("AssistantOnDeviceTtsOnStartSynthesisMessage", ai.A00);
        C0113Ar ar = ai.A00;
        C0139Dd.A09("AssistantClientPlatform", ar.toString());
        C0785hJ hJVar = C00949y.A00;
        Long valueOf = Long.valueOf(RealtimeSinceBootClock.A00.now() - ar.A03);
        if (valueOf != null) {
            hJVar.A03(new Present(valueOf));
            return;
        }
        throw null;
    }
}
