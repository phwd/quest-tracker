package X;

import com.oculus.assistant.service.AssistantService;

/* renamed from: X.Xf  reason: case insensitive filesystem */
public final /* synthetic */ class RunnableC0428Xf implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$tX8HgMxTE5BHE_aq_kMjyr4l5Og";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ RunnableC0428Xf(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        AssistantService assistantService = this.A00;
        HandlerC0422Wz.A07(true);
        if (C0398Vv.A03()) {
            C0139Dd.A09("AssistantService", "Assistant action ignored. In game");
        } else {
            assistantService.A1E.safeExecute(RunnableC0426Xd.A00);
        }
        assistantService.A0R = null;
    }
}
