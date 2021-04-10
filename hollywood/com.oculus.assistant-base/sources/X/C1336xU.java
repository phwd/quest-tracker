package X;

import com.oculus.assistant.service.AssistantService;
import com.oculus.assistant.service.api.attention.AssistantErrorType;

/* renamed from: X.xU  reason: case insensitive filesystem */
public final /* synthetic */ class C1336xU implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1336xU(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        assistantService.A0Y.A03(AssistantErrorType.INCOMPATIBLE_DEVICE);
        C00799i.A00.logError("incompatible_device");
        assistantService.stopSelf();
    }
}
