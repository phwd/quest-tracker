package X;

import com.facebook.assistant.clientplatform.clientstate.AssistantErrorType;
import com.facebook.common.time.RealtimeSinceBootClock;

/* renamed from: X.fg  reason: case insensitive filesystem */
public final class C0700fg implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0700fg(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00929w.A00.A00("AssistantOnDeviceTtsOnErrorMessage", ai.A00);
        C0113Ar ar = ai.A00;
        hG hGVar = C00879r.A00;
        long currentMonotonicTimestamp = hGVar.A00.currentMonotonicTimestamp();
        AssistantErrorType assistantErrorType = AssistantErrorType.TTS_FAILED;
        long now = currentMonotonicTimestamp - (RealtimeSinceBootClock.A00.now() - ar.A03);
        hGVar.A00.markerAnnotate(hGVar.A02, EnumC00899t.ERROR_TYPE.getExtraName(), assistantErrorType.name());
        hGVar.A09(87, now);
    }
}
