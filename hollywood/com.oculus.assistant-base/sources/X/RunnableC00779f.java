package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9f  reason: invalid class name and case insensitive filesystem */
public final /* synthetic */ class RunnableC00779f implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$sTW8FUqEFWUzcGm75rrZZYDwuSo";
    public final /* synthetic */ AssistantLogger A00;

    public /* synthetic */ RunnableC00779f(AssistantLogger assistantLogger) {
        this.A00 = assistantLogger;
    }

    public final void run() {
        this.A00.stopTranscription();
    }
}
