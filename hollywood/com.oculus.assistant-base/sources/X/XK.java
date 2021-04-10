package X;

import android.content.Intent;
import com.oculus.assistant.service.AssistantDumpService;
import com.oculus.assistant.service.AssistantService;

public final /* synthetic */ class XK implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$3cD3bhMzKT20-_BkqQ3hIvLIhVs";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ XK(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        AssistantService assistantService = this.A00;
        assistantService.startService(new Intent(assistantService, AssistantDumpService.class));
    }
}
