package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9a  reason: invalid class name and case insensitive filesystem */
public final /* synthetic */ class RunnableC00729a implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$sbUQbpzQyfBC1vdEuPRvr-Ph3kU";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;

    public /* synthetic */ RunnableC00729a(AssistantLogger assistantLogger, String str) {
        this.A00 = assistantLogger;
        this.A01 = str;
    }

    public final void run() {
        this.A00.logStateChanged(this.A01);
    }
}
