package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9O  reason: invalid class name */
public final /* synthetic */ class AnonymousClass9O implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$UKS3_4QCmRliC-w0ZtMjKZQlk4M";
    public final /* synthetic */ AssistantLogger A00;

    public /* synthetic */ AnonymousClass9O(AssistantLogger assistantLogger) {
        this.A00 = assistantLogger;
    }

    public final void run() {
        this.A00.mLogDatabase = new C00809k(BX.A00()).getWritableDatabase();
    }
}
