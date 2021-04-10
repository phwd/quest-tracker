package X;

import android.text.TextUtils;
import com.oculus.assistant.service.AssistantService;
import com.oculus.assistant.service.api.attention.AssistantErrorType;

/* renamed from: X.xQ  reason: case insensitive filesystem */
public final /* synthetic */ class C1332xQ implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1332xQ(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        if (!assistantService.A1G.get()) {
            w1 w1Var = ((C1261wH) ak.A2L()).A00;
            assistantService.A0Q = w1Var;
            if (w1Var.A00() == null || TextUtils.isEmpty(assistantService.A0Q.A00().mUserId)) {
                assistantService.A0Y.A03(AssistantErrorType.NOT_LOGGED_IN);
                C00799i.A00.logError("not_logged_in");
                C0139Dd.A0A("AssistantService", "Can't activate assistant.  User not logged in.");
                assistantService.stopSelf();
                return;
            }
            assistantService.A1D.safeExecute(new XI(assistantService));
        }
    }
}
