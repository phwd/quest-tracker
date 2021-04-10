package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9b  reason: invalid class name and case insensitive filesystem */
public final /* synthetic */ class RunnableC00739b implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$uiRomLUKdmNfuNYkbBofpL44_x8";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ C00829m A01;
    public final /* synthetic */ String A02;

    public /* synthetic */ RunnableC00739b(AssistantLogger assistantLogger, String str, C00829m r3) {
        this.A00 = assistantLogger;
        this.A02 = str;
        this.A01 = r3;
    }

    public final void run() {
        this.A00.logTranscriptionAnalytics(this.A02, this.A01);
    }
}
