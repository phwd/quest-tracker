package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xR  reason: case insensitive filesystem */
public final /* synthetic */ class C1333xR implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1333xR(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        assistantService.A1E.safeExecute(new RunnableC0428Xf(assistantService));
    }
}
