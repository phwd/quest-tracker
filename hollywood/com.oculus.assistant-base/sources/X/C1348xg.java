package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xg  reason: case insensitive filesystem */
public final /* synthetic */ class C1348xg implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1348xg(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        AssistantService.SafeInitializationManager safeInitializationManager = assistantService.A1E;
        safeInitializationManager.safeExecute(new XS(assistantService, ak));
        if (AssistantService.A0G(assistantService, "transcription") || Z4.A02()) {
            safeInitializationManager.safeExecute(new XJ(assistantService));
        } else {
            assistantService.A1C.safeExecute(new XZ(assistantService));
        }
    }
}
