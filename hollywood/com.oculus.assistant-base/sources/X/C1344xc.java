package X;

import com.oculus.assistant.R;
import com.oculus.assistant.service.AssistantService;
import com.oculus.assistant.service.api.attention.AssistantErrorType;

/* renamed from: X.xc  reason: case insensitive filesystem */
public final /* synthetic */ class C1344xc implements AbstractC0105Aj {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1344xc(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AssistantService assistantService = this.A00;
        assistantService.A1E.safeExecute(new RunnableC0427Xe(assistantService));
        C0112Aq aq = assistantService.A18;
        aq.A01(new C1204vJ(AssistantErrorType.PRIVACY_NOT_ACCEPTED.toString(), assistantService.getString(R.string.error_privacy_not_accepted_dictation)));
        aq.A04(z7.class, assistantService.A0O);
    }
}
