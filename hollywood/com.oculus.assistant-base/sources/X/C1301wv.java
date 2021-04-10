package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.wv  reason: case insensitive filesystem */
public final class C1301wv implements AW {
    public final /* synthetic */ String A00;
    public final /* synthetic */ String A01;

    public C1301wv(String str, String str2) {
        this.A01 = str;
        this.A00 = str2;
    }

    @Override // X.AW
    public final boolean A4v(Object obj) {
        C1351xj xjVar = (C1351xj) obj;
        C0514bB.A02(xjVar, "listener");
        String str = this.A01;
        String str2 = this.A00;
        AssistantService assistantService = xjVar.A00;
        if (str.hashCode() != -764559218 || !str.equals("ATTENTION_SYSTEM_CONTROL") || str2.hashCode() != 826717779 || !str2.equals("hide-attention")) {
            return false;
        }
        YP yp = assistantService.A0a;
        C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "cancelInteraction");
        YO yo = yp.A01;
        if (yo != null) {
            yo.A03(4);
        }
        yp.A01 = null;
        AssistantService.A06(assistantService);
        return false;
    }
}
