package X;

import com.oculus.assistant.service.AssistantService;
import com.oculus.assistant.service.api.attention.AssistantInteractionState;

/* renamed from: X.Xe  reason: case insensitive filesystem */
public final /* synthetic */ class RunnableC0427Xe implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$srJfbsNvjT1zYRYIQu1d_jLRlEg";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ RunnableC0427Xe(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        this.A00.A0Y.A04(AssistantInteractionState.INACTIVE);
    }
}
