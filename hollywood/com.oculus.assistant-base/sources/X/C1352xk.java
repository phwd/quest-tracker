package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xk  reason: case insensitive filesystem */
public final /* synthetic */ class C1352xk implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1352xk(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        switch (((i0) ak.A2L()).A00.intValue()) {
            case 2:
                C0441Yl.A00().A02();
                return;
            case 3:
                C0441Yl.A00().A04();
                if (assistantService.A0k) {
                    assistantService.A0a.A04("response_required");
                    assistantService.A0K();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
