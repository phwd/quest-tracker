package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9c  reason: invalid class name and case insensitive filesystem */
public final /* synthetic */ class RunnableC00749c implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$vq5RWYgqaI7gzYejYMh3IwTGyHc";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ Exception A01;
    public final /* synthetic */ String A02;

    public /* synthetic */ RunnableC00749c(AssistantLogger assistantLogger, String str, Exception exc) {
        this.A00 = assistantLogger;
        this.A02 = str;
        this.A01 = exc;
    }

    public final void run() {
        this.A00.logException(this.A02, this.A01);
    }
}
