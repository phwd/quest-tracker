package X;

import com.oculus.assistant.service.AssistantService;

public final /* synthetic */ class XP implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$719DIXtENg7p75wvj0YrKNfOLlI";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ XP(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        AssistantService assistantService = this.A00;
        AssistantService.A05(assistantService);
        C00799i.A00.logServiceEvent("voice_selection_shown");
        HandlerC0422Wz.A06.A09(assistantService.A0S, true);
    }
}
