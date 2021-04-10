package X;

import com.oculus.assistant.service.AssistantService;
import java.util.Set;

/* renamed from: X.xb  reason: case insensitive filesystem */
public final /* synthetic */ class C1343xb implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1343xb(AssistantService assistantService) {
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
            C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logFirstTranscription");
            YO yo = yp.A01;
            if (yo != null) {
                Set set = yo.A02;
                YQ yq = YQ.ALREADY_LOGGED_TRANSCRIPTION;
                if (!YR.A02(set, yq)) {
                    YR.A00(set, yq);
                    yo.A01.markerPoint(yo.A00(), "transcription_first");
                }
            }
            String str = ((C0811hz) ak.A2L()).A00;
            AssistantService.A0A(assistantService, str);
            assistantService.A0Y.A07(str, true);
        }
    }
}
