package X;

import com.oculus.assistant.service.AssistantService;
import com.oculus.assistant.service.api.attention.AssistantErrorType;

/* renamed from: X.xP  reason: case insensitive filesystem */
public final /* synthetic */ class C1331xP implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1331xP(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        if (!assistantService.A1G.get()) {
            AssistantErrorType assistantErrorType = AssistantErrorType.UNKNOWN;
            try {
                assistantErrorType = AssistantErrorType.valueOf(((C0805ht) ak.A2L()).A00.name());
            } catch (IllegalArgumentException unused) {
            }
            AssistantService.A09(assistantService, assistantErrorType);
        }
    }
}
