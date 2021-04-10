package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xZ  reason: case insensitive filesystem */
public final /* synthetic */ class C1341xZ implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1341xZ(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        if (assistantService.A1G.get()) {
            return;
        }
        if (assistantService.A0j || assistantService.A0X.A00) {
            YP yp = assistantService.A0a;
            C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logFinalTranscription");
            YO yo = yp.A01;
            if (yo != null) {
                yo.A01.markerPoint(yo.A00(), "transcription_final");
            }
            String str = ((C0806hu) ak.A2L()).A01;
            AssistantService.A0A(assistantService, str);
            assistantService.A0Y.A07(str, false);
        }
    }
}
