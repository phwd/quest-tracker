package X;

import android.os.Bundle;
import com.oculus.assistant.service.AssistantService;

/* renamed from: X.Xa  reason: case insensitive filesystem */
public final /* synthetic */ class RunnableC0423Xa implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$_tjdFXCHxt7iPY9FC74Nk7kOOes";
    public final /* synthetic */ Bundle A00;
    public final /* synthetic */ AssistantService A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ String A03;

    public /* synthetic */ RunnableC0423Xa(AssistantService assistantService, String str, String str2, Bundle bundle) {
        this.A01 = assistantService;
        this.A02 = str;
        this.A03 = str2;
        this.A00 = bundle;
    }

    public final void run() {
        AssistantService assistantService = this.A01;
        String str = this.A02;
        String str2 = this.A03;
        Bundle bundle = this.A00;
        AssistantService.A05(assistantService);
        assistantService.A0S.A42(str, str2, bundle);
    }
}
