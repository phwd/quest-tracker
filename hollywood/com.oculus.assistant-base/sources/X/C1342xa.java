package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xa  reason: case insensitive filesystem */
public final /* synthetic */ class C1342xa implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1342xa(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        if (!assistantService.A1G.get()) {
            YB yb = assistantService.A0Y;
            float f = ((C0808hw) ak.A2L()).A00;
            if (!yb.A0A.A02(new C1365xx(f))) {
                yb.A0B.A02(new C1361xt(f));
            }
        }
    }
}
