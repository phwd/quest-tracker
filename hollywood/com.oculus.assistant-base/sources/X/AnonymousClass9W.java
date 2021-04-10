package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9W  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9W implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$g0wm1huu9vVtOptCVvAb2W6NiOc";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;

    public /* synthetic */ AnonymousClass9W(AssistantLogger assistantLogger, String str) {
        this.A00 = assistantLogger;
        this.A01 = str;
    }

    public final void run() {
        this.A00.logAttentionSystem(this.A01);
    }
}
