package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xe  reason: case insensitive filesystem */
public final /* synthetic */ class C1346xe implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1346xe(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        if (!assistantService.A1G.get()) {
            YP yp = assistantService.A0a;
            C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logResponse");
            YO yo = yp.A01;
            if (yo != null) {
                yo.A01.markerPoint(yo.A00(), "RESPONSE");
            }
            assistantService.A0Y.A05(((C0803hr) ak.A2L()).A00);
        }
    }
}
