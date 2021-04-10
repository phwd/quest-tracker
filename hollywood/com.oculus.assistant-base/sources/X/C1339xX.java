package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xX  reason: case insensitive filesystem */
public final /* synthetic */ class C1339xX implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1339xX(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        assistantService.A1E.safeExecute(new RunnableC0425Xc(assistantService));
    }
}
