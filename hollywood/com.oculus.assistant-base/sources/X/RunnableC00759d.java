package X;

import com.facebook.assistant.clientplatform.logger.AssistantLogger;

/* renamed from: X.9d  reason: invalid class name and case insensitive filesystem */
public final /* synthetic */ class RunnableC00759d implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.logger.-$$Lambda$AssistantLogger$xRMGHXgGthcfhR9iu2ze959ev-8";
    public final /* synthetic */ AssistantLogger A00;
    public final /* synthetic */ String A01;

    public /* synthetic */ RunnableC00759d(AssistantLogger assistantLogger, String str) {
        this.A00 = assistantLogger;
        this.A01 = str;
    }

    public final void run() {
        this.A00.logServiceEvent(this.A01);
    }
}
